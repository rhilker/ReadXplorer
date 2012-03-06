package de.cebitec.vamp.databackend.connector;

import de.cebitec.vamp.databackend.GenomeRequest;
import de.cebitec.vamp.databackend.CoverageThread;
import de.cebitec.vamp.databackend.FieldNames;

import de.cebitec.vamp.databackend.SQLStatements;
import de.cebitec.vamp.databackend.dataObjects.PersistantDiff;
import de.cebitec.vamp.databackend.dataObjects.PersistantMapping;
import de.cebitec.vamp.databackend.dataObjects.PersistantReferenceGap;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.databackend.GenericSQLQueries;
import de.cebitec.vamp.databackend.dataObjects.DiscreteCountingDistribution;
import de.cebitec.vamp.databackend.dataObjects.PersistantSeqPairGroup;
import de.cebitec.vamp.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddoppmeier, rhilker
 */
public class TrackConnector {


    private ArrayList<String> associatedTrackNames;
    private int trackID;
    private int genomeSize;
    private CoverageThread coverageThread;
    private Connection con;
    private ArrayList<Integer> trackIds ;
    private static int FIXED_INTERVAL_LENGTH = 1000;

    protected TrackConnector(PersistantTrack track) {
       associatedTrackNames = new ArrayList<String>();
        associatedTrackNames.add( track.getDescription());
        trackID = track.getId();
        con = ProjectConnector.getInstance().getConnection();
        genomeSize = this.getRefGenLength();

        List<PersistantTrack> tracks = new ArrayList<PersistantTrack>(1);
        tracks.add(track);
        startCoverageThread(tracks);
    }

    protected TrackConnector(int id, List<PersistantTrack> tracks) {
        if (tracks.size() > 2) {
            throw new UnsupportedOperationException("More than two tracks not supported yet.");
        }
        this.trackID = id;
        con = ProjectConnector.getInstance().getConnection();
        genomeSize = this.getRefGenLength();

        startCoverageThread(tracks);
    }

    private void startCoverageThread(List<PersistantTrack> tracks) {
        trackIds = new ArrayList<Integer>(tracks.size());
        associatedTrackNames = new ArrayList<String>();
        for (PersistantTrack track : tracks) {
            associatedTrackNames.add(track.getDescription());
            trackIds.add(track.getId());
        }

        coverageThread = new CoverageThread(trackIds);
        coverageThread.start();
    }

    public Collection<PersistantMapping> getMappings(int from, int to) {
        HashMap<Long, PersistantMapping> mappings = new HashMap<Long, PersistantMapping>();
        if (from < to && from >0 && to>0) {
            try {

                //determine readlength
                //TODO: ensure this is only calculated when track id or db changed!
//                PreparedStatement fetchReadlength = con.prepareStatement(SQLStatements.GET_CURRENT_READLENGTH);
//                fetchReadlength.setLong(1, trackID);
//                ResultSet rsReadlength = fetchReadlength.executeQuery();
//
//                int readlength = 1000;
//                final int spacer = 10;
//                if (rsReadlength.next()){
//                    int start = rsReadlength.getInt(FieldNames.MAPPING_START);
//                    int stop = rsReadlength.getInt(FieldNames.MAPPING_STOP);
//                    readlength = stop - start + spacer;
//                }
//                fetchReadlength.close();


                //mapping processing
                PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_MAPPINGS_FROM_INTERVAL_FOR_TRACK);
                fetch.setLong(1, from - FIXED_INTERVAL_LENGTH);
                fetch.setLong(2, to);
                fetch.setLong(3, from);
                fetch.setLong(4, to + FIXED_INTERVAL_LENGTH);
                fetch.setLong(5, trackID);
                fetch.setLong(6, from);
                fetch.setLong(7, to);

                ResultSet rs = fetch.executeQuery();
                while (rs.next()) {
                    // mapping data
                    int mappingID = rs.getInt(FieldNames.MAPPING_ID);
                    int sequenceID = rs.getInt(FieldNames.MAPPING_SEQUENCE_ID);
                    int mappingTrack = rs.getInt(FieldNames.MAPPING_TRACK);
                    int start = rs.getInt(FieldNames.MAPPING_START);
                    int stop = rs.getInt(FieldNames.MAPPING_STOP);
                    byte direction = rs.getByte(FieldNames.MAPPING_DIRECTION);
                    boolean isForwardStrand = (direction == 1 ? true : false);
                    int count = rs.getInt(FieldNames.MAPPING_NUM_OF_REPLICATES);
                    int errors = rs.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                    int bestMapping = rs.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                    boolean isBestMapping = (bestMapping == 1 ? true : false);
                    PersistantMapping m = new PersistantMapping(mappingID, start, stop, mappingTrack, direction, count, errors, sequenceID, isBestMapping);

                    // add new mapping if not exists
                    if (!mappings.containsKey(m.getId())) {
                        mappings.put(m.getId(), m);
                    }

                    // diff data
                    String baseString = rs.getString(FieldNames.DIFF_BASE);
                    int position = rs.getInt(FieldNames.DIFF_POSITION);
                    int type = rs.getInt(FieldNames.DIFF_TYPE);
                    int gapOrder = rs.getInt(FieldNames.DIFF_ORDER);

                    // diff data may be null, if mapping has no diffs
                    if (baseString != null) {
                        char base = baseString.charAt(0);
                        if (type == 1) {
                            PersistantDiff d = new PersistantDiff(position, base, isForwardStrand, count);
                            mappings.get(m.getId()).addDiff(d);
                        } else if (type == 0) {
                            PersistantReferenceGap g = new PersistantReferenceGap(position, base, gapOrder, isForwardStrand, count);
                            mappings.get(m.getId()).addGenomeGap(g);
                        } else {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "found unknown type for diff in database {0}", type);
                        }
                    }
                }

                fetch.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex.getStackTrace());
            }
        }
        return mappings.values();
    }

    /**
     * Handles a coverage request. This means the request containig the sender
     * of the request (the object that wants to receive the coverage) is handed
     * over to the CoverageThread, who will carry out the request as soon
     * as possible. Afterwards the coverage result is handed over to the receiver.
     * @param request the coverage request including the receiving object
     */
    public void addCoverageRequest(GenomeRequest request) {
        coverageThread.addRequest(request);
    }

    public Collection<PersistantDiff> getDiffsForInterval(int from, int to) {

        ArrayList<PersistantDiff> diffs = new ArrayList<PersistantDiff>();
        if (from < to) {
            try {

                PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_DIFFS_IN_TRACK_FOR_INTERVAL);
                fetch.setInt(1, from);
                fetch.setInt(2, to);
                fetch.setLong(3, trackID);

                ResultSet rs = fetch.executeQuery();
                while (rs.next()) {
                    int position = rs.getInt(FieldNames.DIFF_POSITION);
                    char base = rs.getString(FieldNames.DIFF_BASE).charAt(0);
                    byte direction = rs.getByte(FieldNames.MAPPING_DIRECTION);
                    boolean isForwardStrand = (direction == 1 ? true : false);
                    int count = rs.getInt(FieldNames.MAPPING_NUM_OF_REPLICATES);

                    diffs.add(new PersistantDiff(position, base, isForwardStrand, count));
                }

            } catch (SQLException ex) {
                Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, ex.getMessage());
                Logger.getLogger(TrackConnector.class.getName()).log(Level.INFO, null, ex);
            }
        }

        return diffs;
    }

    public Collection<PersistantReferenceGap> getExtendedReferenceGapsForIntervalOrderedByMappingID(int from, int to) {

        Collection<PersistantReferenceGap> gaps = new ArrayList<PersistantReferenceGap>();
        if (from < to) {
            try {
                PreparedStatement fetchGaps = con.prepareStatement(SQLStatements.FETCH_GENOME_GAPS_IN_TRACK_FOR_INTERVAL);
                fetchGaps.setInt(1, from);
                fetchGaps.setInt(2, to);
                fetchGaps.setLong(3, trackID);

                ResultSet rs = fetchGaps.executeQuery();
                while (rs.next()) {
                    char base = rs.getString(FieldNames.DIFF_BASE).charAt(0);
                    int position = rs.getInt(FieldNames.DIFF_POSITION);
                    int order = rs.getInt(FieldNames.DIFF_ORDER);
                    boolean isForwardStrand = (rs.getByte(FieldNames.MAPPING_DIRECTION) == 1 ? true : false);
                    int count = rs.getInt(FieldNames.MAPPING_NUM_OF_REPLICATES);

                    gaps.add(new PersistantReferenceGap(position, base, order, isForwardStrand, count));
                }

            } catch (SQLException ex) {
                Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, ex.getMessage());
                Logger.getLogger(TrackConnector.class.getName()).log(Level.INFO, null, ex);
            }
        }

        return gaps;
    }

    public int getNumOfReads() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_READS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfReadsCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_READS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueSequences() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_SEQUENCES_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueSequencesCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_SEQUENCES_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueBmMappings() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_BM_MAPPINGS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueBmMappingsCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_BM_MAPPINGS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfMappings() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_MAPPINGS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfMappingsCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_MAPPINGS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueMappings() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_MAPPINGS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueMappingsCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_SINGLETON_MAPPINGS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfSeqPairs() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_SEQ_PAIRS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfPerfectSeqPairs() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_PERFECT_SEQ_PAIRS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniqueSeqPairs() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_SEQ_PAIRS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfUniquePerfectSeqPairs() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_PERFECT_SEQ_PAIRS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfSingleMappings() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_SINGLE_MAPPINGS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfPerfectUniqueMappings() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_PERFECT_MAPPINGS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
    }

    public int getNumOfPerfectUniqueMappingsCalculate() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_PERFECT_MAPPINGS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
    }
    
    public int getCoveredPerfectPos() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_PERFECT_COVERAGE_OF_GENOME, SQLStatements.GET_NUM, con, trackID);
    }

    public double getPercentRefGenPerfectCovered() {
        return this.getCoveredPerfectPos() / genomeSize * 100;
    }

    public double getPercentRefGenPerfectCoveredCalculate() {
        double absValue = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_OF_PERFECT_POSITIONS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
        return absValue / genomeSize * 100;

    }

    public int getCoveredBestMatchPos() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_BM_COVERAGE_OF_GENOME, SQLStatements.GET_NUM, con, trackID);
    }
    
    public double getPercentRefGenBmCovered() {
        return this.getCoveredBestMatchPos() / genomeSize * 100;
    }

    public double getPercentRefGenBmCoveredCalculate() {
        double absValue = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_BM_COVERAGE_OF_GENOME_CALCULATE, SQLStatements.GET_NUM, con, trackID);
        return absValue / genomeSize * 100;
    }

    public int getCoveredCommonMatchPos() {
        return GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_COMPLETE_COVERAGE_OF_GENOME, SQLStatements.GET_NUM, con, trackID);
    }
        
    public double getPercentRefGenNErrorCovered() {
        return this.getCoveredCommonMatchPos() / genomeSize * 100;
    }

    public double getPercentRefGenNErrorCoveredCalculate() {
        double absValue = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_COVERED_POSITIONS, SQLStatements.GET_NUM, con, trackID);
        return absValue / genomeSize * 100;
    }

    public HashMap<Integer, Integer> getCoverageInfosOfTrack(int from, int to) {
        PreparedStatement fetch;
        HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
        int coverage;
        int position;
        if (from < to && from >0 && to >0) {
            try {

                fetch = con.prepareStatement(SQLStatements.FETCH_COVERAGE_FOR_TRACK);

                fetch.setLong(1, trackID);
                fetch.setLong(2, from);
                fetch.setLong(3, to);
                ResultSet rs = fetch.executeQuery();
                while (rs.next()) {
                    position = rs.getInt(FieldNames.COVERAGE_POSITION);
                    coverage = rs.getInt(FieldNames.COVERAGE_N_MULT);
                    positionMap.put(position, coverage);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return positionMap;
    }

    public void setStatistics(int numMappings, int numUniqueMappings, int numUniqueSeq,
            int numPerfectMappings, int numBestMatchMappings, double coveragePerf, double coverageBM,
            double coverageComplete, int numReads) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing track statistics");
        try {
            int hasTrack = GenericSQLQueries.getIntegerFromDB(SQLStatements.CHECK_FOR_TRACK_IN_STATS_CALCULATE, SQLStatements.GET_NUM, con, trackID);

            if (hasTrack == 0) {
                PreparedStatement insertStatistics = con.prepareStatement(SQLStatements.INSERT_STATISTICS);

                int id = (int) GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_STATISTICS_ID, con);
                id++;
                int covPerf = (int) (coveragePerf / 100 * genomeSize);
                int covBM = (int) (coverageBM / 100 * genomeSize);
                int covComplete = (int) (coverageComplete / 100 * genomeSize);
                // store track in table
                insertStatistics.setLong(1, id);
                insertStatistics.setLong(2, trackID);
                insertStatistics.setInt(3, numMappings);
                insertStatistics.setInt(4, numPerfectMappings);
                insertStatistics.setInt(5, numBestMatchMappings);
                insertStatistics.setInt(6, numUniqueMappings);
                insertStatistics.setInt(7, covPerf);
                insertStatistics.setInt(8, covBM);
                insertStatistics.setInt(9, covComplete);
                insertStatistics.setInt(10, numUniqueSeq);
                insertStatistics.setInt(11, numReads);
                insertStatistics.execute();
                insertStatistics.close();
            } else {
                PreparedStatement updateStatistics = con.prepareStatement(SQLStatements.UPDATE_STATISTICS);
                int id = (int) GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_STATISTICS_ID, con);
                id++;
                int covPerf = (int) (coveragePerf / 100 * genomeSize);
                int covBM = (int) (coverageBM / 100 * genomeSize);
                int covComplete = (int) (coverageComplete / 100 * genomeSize);
                // store track in table
                updateStatistics.setInt(1, numMappings);
                updateStatistics.setInt(2, numPerfectMappings);
                updateStatistics.setInt(3, numBestMatchMappings);
                updateStatistics.setInt(4, numUniqueMappings);
                updateStatistics.setInt(5, covPerf);
                updateStatistics.setInt(6, covBM);
                updateStatistics.setInt(7, covComplete);
                updateStatistics.setInt(8, numUniqueSeq);
                updateStatistics.setInt(9, numReads);
                updateStatistics.setLong(10, trackID);
                updateStatistics.executeUpdate();
                updateStatistics.close();
            }



        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing track statistics");

    }

    public void addSeqPairStatistics(int numSeqPairs, int numPerfectSeqPairs, int numUniqueSeqPairs,
            int numUniquePerfectSeqPairs, int numSingleMappings) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing sequence pair statistics");
        try {
            PreparedStatement addSeqPairStats = con.prepareStatement(SQLStatements.ADD_SEQPAIR_STATISTICS);
            PreparedStatement latestID = con.prepareStatement(SQLStatements.GET_LATEST_STATISTICS_ID);

            // get latest id for track
            long id = 0;
            ResultSet rs = latestID.executeQuery();
            if (rs.next()) {
                id = rs.getLong("LATEST_ID");
            }
            latestID.close();
            id++;

            addSeqPairStats.setLong(1, id);
            addSeqPairStats.setInt(2, numSeqPairs);
            addSeqPairStats.setInt(3, numPerfectSeqPairs);
            addSeqPairStats.setInt(4, numUniqueSeqPairs);
            addSeqPairStats.setInt(5, numUniquePerfectSeqPairs);
            addSeqPairStats.setInt(6, numSingleMappings);
            addSeqPairStats.execute();
            addSeqPairStats.close();

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing sequence pair statistics");

    }

    public int getTrackID() {
        return trackID;
    }

    public String getAssociatedTrackName() {
        return associatedTrackNames.get(0);
    }

    public ArrayList<String> getAssociatedTrackNames() {
        return associatedTrackNames;
    }

    public ArrayList<Integer> getTrackIds() {
        return trackIds;
    }


/* pruefe coverage links und rechts des Diffs -> soll nicht abfallen,
     * stetige Readabdeckung
     * TODO: try to incorporate continuous coverage as optional in snp detection
     */
    private boolean isCoverageContinuous(int position, double coverage) {
        boolean isContinous = true;
        try {
            //Die Coverage der naechsten 5 Basen links und rechts des SNPs wird verglichen
            int fromPos = ((position - 5) < 0) ? 0 : (position - 5);
            int toPos = ((position + 5) > this.genomeSize) ? this.genomeSize : (position + 5);
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_COVERAGE_FOR_INTERVAL_OF_TRACK);
            fetch.setLong(3, trackID);
            fetch.setLong(1, fromPos);
            fetch.setLong(2, toPos);

            ResultSet rs = fetch.executeQuery();


            while (rs.next()) {
                int covPosition = rs.getInt(FieldNames.COVERAGE_POSITION);
                int forwardCov = rs.getInt(FieldNames.COVERAGE_BM_FW_MULT);
                int reverseCov = rs.getInt(FieldNames.COVERAGE_BM_RV_MULT);
                int neighbourCov = forwardCov + reverseCov;
                double deviation = (Math.abs(neighbourCov - coverage)) / coverage;
                if (deviation >= 0.5) {
                    isContinous = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isContinous;
    }

    private int getRefGenLength() {
        int refGenID = 0;
        try {
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_GENOMEID_FOR_TRACK);
            fetch.setLong(1, trackID);

            ResultSet rs = fetch.executeQuery();
            if (rs.next()) {
                refGenID = rs.getInt(FieldNames.TRACK_REFERENCE_ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        int refGenLength = 0;
        try {
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_GENOME_LENGTH);
            fetch.setInt(1, refGenID);

            ResultSet rs = fetch.executeQuery();
            if (rs.next()) {
                refGenLength = rs.getInt("LENGTH");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return refGenLength;

    }

    public CoverageThread getCoverageThread() {
        return this.coverageThread;
    }

    public int getNumOfSeqPairsCalculate() {
        return -1; //TODO: implement seq pair stats calculate
    }

    public int getNumOfPerfectSeqPairsCalculate() {
        return -1;
    }

    public int getNumOfUniqueSeqPairsCalculate() {
        return -1;
    }

    public int getNumOfUniquePerfectSeqPairsCalculate() {
        return -1;
    }

    public int getNumOfSingleMappingsCalculate() {
        return -1;
    }

    /**
     * Fetches all sequence pair mappings for the given interval and typeFlag.
     * @param from start position of the currently viewed interval
     * @param to stop position of the currently viewed interval
     * @param trackID2 the track id of the second track to which the currently viewed
     *                  sequence paris belong
     * @param typeFlag flagging which data to retrieve using Properties.SEQ_PAIRS, Properties.SINGLE_MAPPINGS, Properties.BOTH
     * @return the collection of sequence pair mappings for the given interval and typeFlag
     */
    public Collection<PersistantSeqPairGroup> getSeqPairMappings(int from, int to, int trackID2, byte typeFlag) {
        HashMap<Long, PersistantSeqPairGroup> seqPairs = new HashMap<Long, PersistantSeqPairGroup>();
        if (from < to) {

            try {

//                //determine readlength
//                //TODO: ensure this is only calculated when track id or db changed!
//                PreparedStatement fetchReadlength = con.prepareStatement(SQLStatements.GET_CURRENT_READLENGTH);
//                fetchReadlength.setLong(1, trackID);
//                ResultSet rsReadlength = fetchReadlength.executeQuery();
//
//                int readlength = 1000;
//                final int spacer = 10;
//                if (rsReadlength.next()) {
//                    int start = rsReadlength.getInt(FieldNames.MAPPING_START);
//                    int stop = rsReadlength.getInt(FieldNames.MAPPING_STOP);
//                    readlength = stop - start + spacer;
//                }
//                fetchReadlength.close();

                //sequence pair processing
                
                if (typeFlag != Properties.SINGLE_MAPPINGS && typeFlag != Properties.NONE) {

                    //we need both statements, because an "OR" query for mapping1_id OR mapping2_id is incredibly slow...
                    PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_SEQ_PAIRS_W_REPLICATES_FOR_INTERVAL);
                    PreparedStatement fetch2 = con.prepareStatement(SQLStatements.FETCH_SEQ_PAIRS_W_REPLICATES_FOR_INTERVAL2);
                    fetch.setLong(1, from - FIXED_INTERVAL_LENGTH); // 101 000 - 1000 = 100 000
                    fetch.setLong(2, to); // 101 100
                    fetch.setLong(3, from); // 101 000
                    fetch.setLong(4, to + FIXED_INTERVAL_LENGTH); // 101 100 + 1000 = 102 100
                    fetch.setLong(5, trackID);
                    fetch.setLong(6, trackID2);

                    fetch2.setLong(1, from - FIXED_INTERVAL_LENGTH);
                    fetch2.setLong(2, to);
                    fetch2.setLong(3, from);
                    fetch2.setLong(4, to + FIXED_INTERVAL_LENGTH);
                    fetch2.setLong(5, trackID);
                    fetch2.setLong(6, trackID2);

                    ResultSet rs = fetch.executeQuery();
                    while (rs.next()) {

                        // mapping data
                        int mappingID = rs.getInt("MAPPING_ID");
                        int sequenceID = rs.getInt(FieldNames.MAPPING_SEQUENCE_ID);
                        int mappingTrack = rs.getInt(FieldNames.MAPPING_TRACK);
                        int start = rs.getInt(FieldNames.MAPPING_START);
                        int stop = rs.getInt(FieldNames.MAPPING_STOP);
                        byte direction = rs.getByte(FieldNames.MAPPING_DIRECTION);
                        int count = rs.getInt("MAPPING_REP");
                        int errors = rs.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                        int bestMapping = rs.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                        boolean isBestMapping = (bestMapping == 1 ? true : false);
                        long seqPairID = rs.getLong("ORIG_PAIR_ID");
                        long mapping1Id = rs.getLong(FieldNames.SEQ_PAIR_MAPPING1_ID);
                        long mapping2Id = rs.getLong(FieldNames.SEQ_PAIR_MAPPING2_ID);
                        byte seqPairType = rs.getByte(FieldNames.SEQ_PAIR_TYPE);
                        int seqPairReplicates = rs.getInt(FieldNames.SEQ_PAIR_NUM_OF_REPLICATES);
                        PersistantMapping mapping = new PersistantMapping(mappingID, start, stop, mappingTrack, direction, count, errors, sequenceID, isBestMapping);

                        // add new seqPair if not exists
                        if (!seqPairs.containsKey(seqPairID)) {
                            PersistantSeqPairGroup newGroup = new PersistantSeqPairGroup();
                            newGroup.setSeqPairId(seqPairID);
                            seqPairs.put(seqPairID, newGroup);
                        }
                        seqPairs.get(seqPairID).addPersistantMapping(mapping, seqPairType, mapping1Id, mapping2Id, seqPairReplicates);

                    }

                    ResultSet rs3 = fetch2.executeQuery();
                    while (rs3.next()) {

                        // mapping data
                        int mappingID = rs3.getInt("MAPPING_ID");
                        int sequenceID = rs3.getInt(FieldNames.MAPPING_SEQUENCE_ID);
                        int mappingTrack = rs3.getInt(FieldNames.MAPPING_TRACK);
                        int start = rs3.getInt(FieldNames.MAPPING_START);
                        int stop = rs3.getInt(FieldNames.MAPPING_STOP);
                        byte direction = rs3.getByte(FieldNames.MAPPING_DIRECTION);
                        int count = rs3.getInt("MAPPING_REP");
                        int errors = rs3.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                        int bestMapping = rs3.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                        boolean isBestMapping = (bestMapping == 1 ? true : false);
                        long seqPairID = rs3.getLong("ORIG_PAIR_ID");
                        long mapping1Id = rs3.getLong(FieldNames.SEQ_PAIR_MAPPING1_ID);
                        long mapping2Id = rs3.getLong(FieldNames.SEQ_PAIR_MAPPING2_ID);
                        byte seqPairType = rs3.getByte(FieldNames.SEQ_PAIR_TYPE);
                        int seqPairReplicates = rs3.getInt(FieldNames.SEQ_PAIR_NUM_OF_REPLICATES);
                        PersistantMapping mapping = new PersistantMapping(mappingID, start, stop, mappingTrack, direction, count, errors, sequenceID, isBestMapping);

                        // add new seqPair if not exists
                        if (!seqPairs.containsKey(seqPairID)) {
                            PersistantSeqPairGroup newGroup = new PersistantSeqPairGroup();
                            newGroup.setSeqPairId(seqPairID);
                            seqPairs.put(seqPairID, newGroup);
                        }
                        seqPairs.get(seqPairID).addPersistantMapping(mapping, seqPairType, mapping1Id, mapping2Id, seqPairReplicates);

                    }

                    fetch.close();
                    fetch2.close();
                }

                //single mapping processing
                if (typeFlag != Properties.SEQ_PAIRS && typeFlag != Properties.NONE) {

                    PreparedStatement fetchSingleReads = con.prepareStatement(SQLStatements.FETCH_SEQ_PAIRS_PIVOT_DATA_FOR_INTERVAL);
                    fetchSingleReads.setLong(1, from - FIXED_INTERVAL_LENGTH);
                    fetchSingleReads.setLong(2, to);
                    fetchSingleReads.setLong(3, from);
                    fetchSingleReads.setLong(4, to + FIXED_INTERVAL_LENGTH);
                    fetchSingleReads.setLong(5, trackID);
                    fetchSingleReads.setLong(6, trackID2);

                    ResultSet rs2 = fetchSingleReads.executeQuery();
                    while (rs2.next()) {

                        // mapping data
                        int mappingID = rs2.getInt("MAPPING_ORIG_ID");
                        int sequenceID = rs2.getInt(FieldNames.MAPPING_SEQUENCE_ID);
                        int mappingTrack = rs2.getInt(FieldNames.MAPPING_TRACK);
                        int start = rs2.getInt(FieldNames.MAPPING_START);
                        int stop = rs2.getInt(FieldNames.MAPPING_STOP);
                        byte direction = rs2.getByte(FieldNames.MAPPING_DIRECTION);
                        int count = rs2.getInt(FieldNames.MAPPING_NUM_OF_REPLICATES);
                        int errors = rs2.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                        int bestMapping = rs2.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                        boolean isBestMapping = (bestMapping == 1 ? true : false);
                        long seqPairID = rs2.getLong(FieldNames.SEQ_PAIR_PIVOT_SEQ_PAIR_ID);
                        PersistantMapping mapping = new PersistantMapping(mappingID, start, stop, mappingTrack, direction, count, errors, sequenceID, isBestMapping);

                        // add to seqPair container
                        if (!seqPairs.containsKey(seqPairID)) {
                            PersistantSeqPairGroup newGroup = new PersistantSeqPairGroup();
                            newGroup.setSeqPairId(seqPairID);
                            seqPairs.put(seqPairID, newGroup);
                        }
                        seqPairs.get(seqPairID).addPersistantMapping(mapping, Properties.TYPE_UNPAIRED_PAIR, -1, -1, -1);

                    }

                    fetchSingleReads.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }



        return seqPairs.values();
    }

    /**
     * @return The sequence pair id belonging to the track connectors track id or <code>0</code> if this
     * track is not a sequence pair track.
     */
    public Integer getSeqPairToTrackID() {
        int value = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_SEQ_PAIR_TO_TRACK_ID, SQLStatements.GET_NUM, con, trackID);
        return value;
    }

    /**
     * @param seqPairId the sequence pair id to get the second track id for
     * @return the second track id of a sequence pair beyond this track connectors track id
     */
    public int getTrackIdToSeqPairId(int seqPairId) {
        int num = 0;
        try {
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_TRACK_ID_TO_SEQ_PAIR_ID);
            fetch.setLong(1, seqPairId);
            fetch.setLong(2, trackID);

            ResultSet rs = fetch.executeQuery();
            if (rs.next()) {
                num = rs.getInt(FieldNames.TRACK_ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLStatements.class.getName()).log(Level.SEVERE, null, ex);
        }

        return num;
    }

    /**
     * Returns all mappings belonging to a sequence pair
     * @param seqPairId sequence id to search for
     * @return all data belonging to this sequence pair id (all mappings and pair replicates)
     */
    public PersistantSeqPairGroup getMappingsForSeqPairId(long seqPairId) {

        PersistantSeqPairGroup seqPairData = new PersistantSeqPairGroup();
        seqPairData.setSeqPairId(seqPairId);
        try {

            //sequence pair processing
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_MAPPINGS_FOR_SEQ_PAIR_ID);
            PreparedStatement fetch2 = con.prepareStatement(SQLStatements.FETCH_MAPPINGS_FOR_SEQ_PAIR_ID2);
            fetch.setLong(1, seqPairId);
            fetch2.setLong(1, seqPairId);

            ResultSet rs = fetch.executeQuery();
            while (rs.next()) {

                // mapping data
                long mappingId = rs.getLong(FieldNames.MAPPING_ID);
                int start = rs.getInt(FieldNames.MAPPING_START);
                int stop = rs.getInt(FieldNames.MAPPING_STOP);
                byte direction = rs.getByte(FieldNames.MAPPING_DIRECTION);
                int count = rs.getInt("MAPPING_REPLICATES");
                int errors = rs.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                int bestMapping = rs.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                boolean isBestMapping = (bestMapping == 1 ? true : false);
                long mapping1Id = rs.getLong(FieldNames.SEQ_PAIR_MAPPING1_ID);
                long mapping2Id = rs.getLong(FieldNames.SEQ_PAIR_MAPPING2_ID);
                byte seqPairType = rs.getByte(FieldNames.SEQ_PAIR_TYPE);
                int seqPairReplicates = rs.getInt(FieldNames.SEQ_PAIR_NUM_OF_REPLICATES);

                PersistantMapping mapping = new PersistantMapping((int) mappingId, start, stop, -1, direction, count, errors, -1, isBestMapping);
                seqPairData.addPersistantMapping(mapping, seqPairType, mapping1Id, mapping2Id, seqPairReplicates);

            }
            fetch.close();

            ResultSet rs2 = fetch2.executeQuery();
            while (rs2.next()) {

                // mapping data
                long mappingId = rs2.getLong(FieldNames.MAPPING_ID);
                int start = rs2.getInt(FieldNames.MAPPING_START);
                int stop = rs2.getInt(FieldNames.MAPPING_STOP);
                byte direction = rs2.getByte(FieldNames.MAPPING_DIRECTION);
                int count = rs2.getInt("MAPPING_REPLICATES");
                int errors = rs2.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                int bestMapping = rs2.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                boolean isBestMapping = (bestMapping == 1 ? true : false);
                long mapping1Id = rs2.getLong(FieldNames.SEQ_PAIR_MAPPING1_ID);
                long mapping2Id = rs2.getLong(FieldNames.SEQ_PAIR_MAPPING2_ID);
                byte seqPairType = rs2.getByte(FieldNames.SEQ_PAIR_TYPE);
                int seqPairReplicates = rs2.getInt(FieldNames.SEQ_PAIR_NUM_OF_REPLICATES);

                PersistantMapping mapping = new PersistantMapping((int) mappingId, start, stop, -1, direction, count, errors, -1, isBestMapping);
                seqPairData.addPersistantMapping(mapping, seqPairType, mapping1Id, mapping2Id, seqPairReplicates);

            }
            fetch2.close();

            //single mapping processing
            PreparedStatement fetchSingleReads = con.prepareStatement(SQLStatements.FETCH_SINGLE_MAPPINGS_FOR_SEQ_PAIR_ID);
            fetchSingleReads.setLong(1, seqPairId);

            rs = fetchSingleReads.executeQuery();
            while (rs.next()) {

                // mapping data
                int start = rs.getInt(FieldNames.MAPPING_START);
                int stop = rs.getInt(FieldNames.MAPPING_STOP);
                byte direction = rs.getByte(FieldNames.MAPPING_DIRECTION);
                int count = rs.getInt(FieldNames.MAPPING_NUM_OF_REPLICATES);
                int errors = rs.getInt(FieldNames.MAPPING_NUM_OF_ERRORS);
                int bestMapping = rs.getInt(FieldNames.MAPPING_IS_BEST_MAPPING);
                boolean isBestMapping = (bestMapping == 1 ? true : false);
                long mapping1Id = rs.getLong(FieldNames.SEQ_PAIR_PIVOT_MAPPING_ID);
                PersistantMapping mapping = new PersistantMapping((int) mapping1Id, start, stop, -1, direction, count, errors, -1, isBestMapping);

                seqPairData.addPersistantMapping(mapping, Properties.TYPE_UNPAIRED_PAIR, -1, -1, -1);

            }

            fetchSingleReads.close();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return seqPairData;

    }

    /**
     * Fetches a {@link DiscreteCountingDistribution} for this track.
     * @param the type of distribution either Properties.COVERAGE_INCREASE_DISTRIBUTION
     * or Properties.COVERAGE_INC_PERCENT_DISTRIBUTION
     * @return a {@link DiscreteCountingDistribution} for this track.
     */
    public DiscreteCountingDistribution getCoverageIncreaseDistribution(byte type) {
        DiscreteCountingDistribution coverageDistribution = new DiscreteCountingDistribution();
        coverageDistribution.setType(type);
        
        try {
            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_COVERAGE_DISTRIBUTION);
            fetch.setInt(1, this.trackID);
            fetch.setByte(2, type);

            ResultSet rs = fetch.executeQuery();
            while (rs.next()) {
                int coverageIntervalId = rs.getInt(FieldNames.COVERAGE_DISTRIBUTION_COV_INTERVAL_ID);
                int count = rs.getInt(FieldNames.COVERAGE_DISTRIBUTION_INC_COUNT);
                coverageDistribution.setCountForIndex(coverageIntervalId, count);
            }
            rs.close();
            fetch.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        return coverageDistribution;
    }
    
    /**
     * Sets the coverage increase distribution {@link DiscreteCountingDistribution} for this track.
     * @param distribution the coverage increase distribution 
     *          {@link DiscreteCountingDistribution} for this track.
     */
    public void insertCoverageDistribution(DiscreteCountingDistribution distribution) {
        
        int[] covDistribution = distribution.getDiscreteCountingDistribution();
        try {
            PreparedStatement insert = con.prepareStatement(SQLStatements.INSERT_COVERAGE_DISTRIBUTION);
            
            for (int i = 0; i < covDistribution.length; ++i) {
                insert.setInt(1, this.trackID);
                insert.setByte(2, distribution.getType());
                insert.setInt(3, i);
                insert.setInt(4, covDistribution[i]);
                insert.addBatch();
            }
            
            insert.executeBatch();
            insert.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
