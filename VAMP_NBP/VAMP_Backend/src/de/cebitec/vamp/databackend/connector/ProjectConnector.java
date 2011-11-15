package de.cebitec.vamp.databackend.connector;

import de.cebitec.vamp.databackend.FieldNames;
import de.cebitec.vamp.databackend.GenericSQLQueries;
import de.cebitec.vamp.databackend.H2SQLStatements;
import de.cebitec.vamp.databackend.MySQLStatements;
import de.cebitec.vamp.databackend.SQLStatements;
import de.cebitec.vamp.databackend.dataObjects.PersistantReference;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
//import de.cebitec.vamp.databackend.dataObjects.PersistentRun;
//import de.cebitec.vamp.parser.common.ParsedReadname;
//import de.cebitec.vamp.parser.common.ParsedRun;
//import java.util.Collection;
import de.cebitec.vamp.parser.common.CoverageContainer;
import de.cebitec.vamp.parser.common.ParsedDiff;
import de.cebitec.vamp.parser.common.ParsedFeature;
import de.cebitec.vamp.parser.common.ParsedMapping;
import de.cebitec.vamp.parser.common.ParsedSeqPairContainer;
import de.cebitec.vamp.parser.common.ParsedReference;
import de.cebitec.vamp.parser.common.ParsedReferenceGap;
import de.cebitec.vamp.parser.common.ParsedTrack;
import de.cebitec.vamp.parser.common.ParsedSeqPairMapping;
import de.cebitec.vamp.util.Pair;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbc.JdbcSQLException;

/**
 * Responsible for the connection between user interface and data base.
 * Contains the methods to communicate with the data base.
 *
 * @author ddoppmeier
 */
public class ProjectConnector {

    /* !!!!!!!!!!!!
     * Note that all parts belonging to the RUN domain have been commented out!
     * !!!!!!!!!!!!
     */
    private static ProjectConnector dbConnector;
    private Connection con;
    private String url;
    private String user;
    private String password;
    private String adapter;
    private HashMap<Long, TrackConnector> trackConnectors;
//    private HashMap<Long, RunConnector> runConnectors;
    private HashMap<Long, ReferenceConnector> refConnectors;
    private static final int BATCH_SIZE = 100000;
    //private final static int SEQUENCE_BATCH_SIZE = BATCH_SIZE;
    //private final static int READNAME_BATCH_SIZE = BATCH_SIZE;
    private final static int FEATURE_BATCH_SIZE = BATCH_SIZE;
    private final static int COVERAGE_BATCH_SIZE = BATCH_SIZE;
    private final static int MAPPING_BATCH_SIZE = BATCH_SIZE;
    private static final int SEQPAIR_BATCH_SIZE = BATCH_SIZE;
    private static final int SEQPAIR_PIVOT_BATCH_SIZE = BATCH_SIZE + 25000;
    private final static int DIFF_BATCH_SIZE = BATCH_SIZE;
    private final static String VARCHAR_20 = "VARCHAR(20)";
    private final static String BIGINT_UNSIGNED = "BIGINT UNSIGNED";
    //private int coveragePerf = 0;
    //private int coverageBM = 0;
    //private int coverageComplete = 0;
    private int mappings = 0;
    private int perfectMappings = 0;
    private int bmMappings = 0;
    private int numReads = 0;
    private int currentTrackID = -1;

    private ProjectConnector() {
        trackConnectors = new HashMap<Long, TrackConnector>();
//        runConnectors = new HashMap<Long, RunConnector>();
        refConnectors = new HashMap<Long, ReferenceConnector>();
    }

    private void cleanUp() {
        trackConnectors.clear();
//       runConnectors.clear();
        refConnectors.clear();
    }

    public static synchronized ProjectConnector getInstance() {
        if (dbConnector == null) {
            dbConnector = new ProjectConnector();
        }
        return dbConnector;
    }

    public List<PersistantTrack> getTracks() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Reading track data from database");
        ArrayList<PersistantTrack> tracks = new ArrayList<PersistantTrack>();

        try {
            PreparedStatement fetchTracks = con.prepareStatement(SQLStatements.FETCH_TRACKS);
            ResultSet rs = fetchTracks.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(FieldNames.TRACK_ID);
                String description = rs.getString(FieldNames.TRACK_DESCRIPTION);
                Timestamp date = rs.getTimestamp(FieldNames.TRACK_TIMESTAMP);
                int refGenID = rs.getInt(FieldNames.TRACK_REFERENCE_ID);
                //               Long runID = rs.getLong(FieldNames.TRACK_RUN);
                tracks.add(new PersistantTrack(id, description, date, refGenID));//, runID));
            }

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        return tracks;
    }

    public boolean isConnected() {
        if (con != null) {
            try {
                return con.isValid(0);
            } catch (SQLException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void disconnect() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Closing database connection");
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con = null;
            this.cleanUp();
        }
    }

    public void connect(String adapter, String hostname, String database, String user, String password) throws SQLException, JdbcSQLException {
        if (adapter.equalsIgnoreCase("mysql")) {
            this.adapter = adapter;
            this.url = "jdbc:" + adapter + "://" + hostname + "/" + database;
            this.user = user;
            this.password = password;
            this.connect(url, user, password);
            this.setupMySQLDatabase();
        } else {
            this.adapter = adapter;
            this.url = "jdbc:" + adapter + ":" + database + ";FILE_LOCK=SERIALIZED;MULTI_THREADED=1";

            this.connectH2DataBase(url);
            this.setupDatabaseH2();

        }

    }

    private void connect(String url, String user, String password) throws SQLException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Connecting to database");
        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(true);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Successfully connected to database");
    }

    private void connectH2DataBase(String url) throws SQLException, JdbcSQLException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Connecting to database");
        con = DriverManager.getConnection(url);
        con.setAutoCommit(true);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Successfully connected to database");
    }

    private void setupDatabaseH2() {

        try {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Setting up tables and indices if not existent");

            con.setAutoCommit(false);
            //create tables if not exist yet
            con.prepareStatement(H2SQLStatements.SETUP_REFERENCE_GENOME).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_DIFFS).executeUpdate();
            con.prepareStatement(H2SQLStatements.INDEX_DIFF).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_COVERAGE).executeUpdate();
            con.prepareStatement(H2SQLStatements.INDEX_COVERAGE).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_FEATURES).executeUpdate();
            con.prepareStatement(H2SQLStatements.INDEX_FEATURES).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_MAPPINGS).executeUpdate();
            con.prepareStatement(H2SQLStatements.INDEX_MAPPINGS).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_TRACKS).execute();
            con.prepareStatement(H2SQLStatements.INDEX_TRACKS).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_SEQ_PAIRS).execute();
            con.prepareStatement(H2SQLStatements.INDEX_SEQ_PAIRS).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_SEQ_PAIR_REPLICATES).execute();
            con.prepareStatement(H2SQLStatements.INDEX_SEQ_PAIR_REPLICATES).executeUpdate();
            con.prepareStatement(H2SQLStatements.SETUP_SEQ_PAIR_PIVOT).execute();
            con.prepareStatement(H2SQLStatements.INDEX_SEQ_PAIR_PIVOT).executeUpdate();
            con.prepareStatement(SQLStatements.SETUP_STATISTICS).executeUpdate();

//           con.prepareStatement(H2SQLStatements.SETUP_RUN).execute();
//            con.prepareStatement(H2SQLStatements.SETUP_SEQUENCE).executeUpdate();
//            con.prepareStatement(H2SQLStatements.INDEX_SEQUENCE).executeUpdate();
//           con.prepareStatement(H2SQLStatements.SETUP_READS).execute();
//           con.prepareStatement(H2SQLStatements.INDEX_READS).executeUpdate();

            this.checkDBStructure();

            con.commit();
            con.setAutoCommit(true);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Finished creating tables and indices if not existent before");

        } catch (SQLException ex) {
            this.rollbackOnError(this.getClass().getName(), ex);
        }
    }

    private void setupMySQLDatabase() {

        try {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Setting up tables and indices if not existent");

            con.setAutoCommit(false);
            //create tables if not exist yet
            con.prepareStatement(MySQLStatements.SETUP_REFERENCE_GENOME).executeUpdate();
            con.prepareStatement(MySQLStatements.SETUP_DIFFS).executeUpdate();
            con.prepareStatement(MySQLStatements.SETUP_COVERAGE).executeUpdate();
            con.prepareStatement(MySQLStatements.SETUP_FEATURES).executeUpdate();
            con.prepareStatement(MySQLStatements.SETUP_MAPPINGS).executeUpdate();
            con.prepareStatement(MySQLStatements.SETUP_TRACKS).execute();
            con.prepareStatement(MySQLStatements.SETUP_SEQ_PAIRS).execute();
            con.prepareStatement(MySQLStatements.SETUP_SEQ_PAIR_REPLICATES).execute();
            con.prepareStatement(MySQLStatements.SETUP_SEQ_PAIR_PIVOT).execute();
            con.prepareStatement(SQLStatements.SETUP_STATISTICS).execute();
//           con.prepareStatement(SQLStatements.SETUP_RUN).execute();
//            con.prepareStatement(SQLStatements.SETUP_SEQUENCE).executeUpdate();
//            con.prepareStatement(SQLStatements.SETUP_READS).execute();

            this.checkDBStructure();

            con.commit();
            con.setAutoCommit(true);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Finished creating tables and indices if not existent before");

        } catch (SQLException ex) {
            this.rollbackOnError(this.getClass().getName(), ex);
        }
    }

    /**
     * Any additional columns which were added to existing tables in newer 
     * VAMP versions should be checked by this method to ensure correct database 
     * structure and avoiding errors when SQL statements request one of these
     * columns, which are not existent in older databases.
     */
    private void checkDBStructure() {

        //remove statics table (replaced by statistics table)
        try {
            con.prepareStatement(SQLStatements.DROP_TABLE_STATICS).execute();
        } catch (SQLException ex) {
            this.rollbackOnError(this.getClass().getName(), ex);
        }

        //stats table
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUMBER_OF_UNIQUE_SEQ, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUMBER_READS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUM_SEQUENCE_PAIRS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUM_PERFECT_SEQUENCE_PAIRS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUM_UNIQUE_SEQUENCE_PAIRS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUM_UNIQUE_PERFECT_SEQUENCE_PAIRS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_STATISTICS, FieldNames.STATISTICS_NUM_SINGLE_MAPPINGS, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }

        //add column GENE for features
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_FEATURES, FieldNames.FEATURE_GENE, VARCHAR_20)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }

        //add sequence pair id column in tracks if not existent
        try {
            con.prepareStatement(GenericSQLQueries.genAddColumnString(
                    FieldNames.TABLE_TRACKS, FieldNames.TRACK_SEQUENCE_PAIR_ID, BIGINT_UNSIGNED)).execute();
        } catch (SQLException ex) {
            this.checkRollback(ex);
        }
    }

    /**
     * Checks if a rollback is needed or if the SQLException originated from a
     * duplicate column name error.
     * @param ex SQL exception to check
     */
    private void checkRollback(SQLException ex) {
        if (!ex.getMessage().contains("Duplicate column name")) {
            this.rollbackOnError(this.getClass().getName(), ex);
        }
    }

    public void rollbackOnError(String className, Exception ex) {

        Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, "Error occured. Trying to recover", ex);
        try {
            if (!con.isClosed()) {
                //connection is still open. try rollback
                con.rollback();
                Logger.getLogger(ProjectConnector.class.getName()).log(Level.INFO, "Successfully rolled back");
            } else {
                //connection was closed before, open a new one
                this.connect(url, user, password);
            }

        } catch (SQLException ex1) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.INFO, "Rollback failed", ex1);
        }
    }

    /*
     * Method only for development of database and testing of import functionality.
     * Never use this in productive environment.
     */
    private void deleteAllTables() {
        try {
            con.setAutoCommit(false);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Deleting all database tables");
            ResultSet rs = con.prepareStatement("show tables").executeQuery();
            while (rs.next()) {
                String table = rs.getString(1);
                con.prepareStatement("drop table " + table).executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Successfully deleted all data");
        } catch (SQLException ex) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, "Deletion of data failed", ex);
        }
    }

//    private void lockRunDomainTables() {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start locking run domain tables");
//        try {
//            Statement lock = con.createStatement();
//            lock.execute(SQLStatements.LOCK_TABLE_RUN_DOMAIN);
//            con.commit();
//            lock.close();
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done locking run domain tables");
//    }
    /**
     * Unlocks tables in mysql fashion.
     */
    private void unlockTables() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start unlocking tables");
        try {
            con.setAutoCommit(false);
            Statement unlock = con.createStatement();
            unlock.execute(MySQLStatements.UNLOCK_TABLES);
            unlock.close();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done unlocking tables");
    }

//    private void storeRun(ParsedRun run) throws StorageException {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing run data");
//
//        try {
//            long runID = this.getLatestIDFromDB(SQLStatements.GET_LATEST_RUN_ID);
//            PreparedStatement insertRun = con.prepareStatement(SQLStatements.INSERT_RUN);
//            run.setID(runID);
//
//            insertRun.setLong(1, runID);
//            insertRun.setString(2, run.getDescription());
//            insertRun.setTimestamp(3, run.getTimestamp());
//            insertRun.execute();
//
//            insertRun.close();
//            fetchRunID.close();
//
//            con.commit();
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done storing run data");
//    }
//    private void storeRunH2(ParsedRun run) throws StorageException {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing run data");
//
//        try {
//            long runID = this.getLatestIDFromDB(H2SQLStatements.GET_LATEST_RUN_ID);
//            run.setID(runID);
//            PreparedStatement insertRun = con.prepareStatement(H2SQLStatements.INSERT_RUN);
//
//            insertRun.setLong(1, runID);
//            insertRun.setString(2, run.getDescription());
//            insertRun.setTimestamp(3, run.getTimestamp());
//            insertRun.execute();
//
//            insertRun.close();
//            fetchRunID.close();
//
//            con.commit();
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done storing run data");
//    }
//    private void storeSequences(ParsedRun run) {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing unique sequences");
//
//        try {
//            long seqID = this.getLatestIDFromDB(SQLStatements.GET_LATEST_SEQUENCE_ID);
//            PreparedStatement insertSequence = con.prepareStatement(SQLStatements.INSERT_SEQUENCE);
//
//            // store sequences
//            Collection<ParsedReadname> reads = run.getReads();
//            int batchCounter = 1;
//            Iterator<ParsedReadname> it = reads.iterator();
//            while( it.hasNext()) {
//                batchCounter++;
//                ParsedReadname read = it.next();
//                seqID++;
//                read.setID(seqID);
//                insertSequence.setLong(1, seqID);
//                insertSequence.setLong(2, run.getID());
//                insertSequence.addBatch();
//
//                if (batchCounter == SEQUENCE_BATCH_SIZE) {
//                    insertSequence.executeBatch();
//                   batchCounter = 1;
//                }
//              //  it.remove();
//            }
//
//            insertSequence.executeBatch();
//
//            con.commit();
//            insertSequence.close();
//            latestSeqID.close();
//
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done storing unique sequences");
//    }
//    private void storeReads(ParsedRun run) {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing readnames");
//        try {
//            long readID = this.getLatestIDFromDB(SQLStatements.GET_LATEST_READ_ID);
//            PreparedStatement insertRead = con.prepareStatement(SQLStatements.INSERT_READ);
//
//            int batchCounter = 1;
//            Iterator<ParsedReadname> readMapIt = run.getReads().iterator();
//            while (readMapIt.hasNext()) {
//                ParsedReadname readMap = readMapIt.next();
//                Iterator<String> readIt = readMap.getReads().iterator();
//                while( readIt.hasNext()) {
//                    batchCounter++;
//                    readID++;
//                    insertRead.setLong(1, readID);
//                    insertRead.setString(2, readIt.next());
//                    insertRead.setLong(3, readMap.getID());
//                    insertRead.addBatch();
//
//                    if (batchCounter == READNAME_BATCH_SIZE) {
//                          batchCounter = 1;
//                        insertRead.executeBatch();
//                    }
//                   // readIt.remove();
//                }
//              //  readMapIt.remove();
//            }
//
//            insertRead.executeBatch();
//            con.commit();
//            insertRead.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "done storing readnames");
//    }
//    public long addRun(ParsedRun run) throws StorageException {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Start storing run \"{0}to {1}", new Object[]{run.getDescription(), adapter});
//
//        if (adapter.equalsIgnoreCase("mysql")) {
//            try {
//                con.setAutoCommit(false);
//                this.lockRunDomainTables();
//                this.disableRunIndices();
//                storeRun(run);
//                storeSequences(run);
//                storeReads(run);
//                run.deleteMap();
//                this.enableRunIndices();
//                this.unlockTables();
//                con.setAutoCommit(true);
//
//            } catch (SQLException ex) {
//                ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//                throw new StorageException(ex);
//            } catch (Exception ex) {
//                throw new StorageException(ex);
//            }
//        } else {
//            try {
//                con.setAutoCommit(false);
//                //storeRunH2(run);
//                storeSequences(run);
//                storeReads(run);
//                run.deleteMap();
//                con.setAutoCommit(true);
//
//            } catch (SQLException ex) {
//                ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//                throw new StorageException(ex);
//            } catch (Exception ex) {
//                throw new StorageException(ex);
//            }
//
//        }
//
//        return run.getID();
//    }
//    private void disableRunIndices() {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Start disabling run domain indexing");
//        try {
//
//            PreparedStatement run = con.prepareStatement(SQLStatements.DISABLE_RUN_INDICES);
//            PreparedStatement sequence = con.prepareStatement(SQLStatements.DISABLE_SEQUENCE_INDICES);
//            PreparedStatement read = con.prepareStatement(SQLStatements.DISABLE_READNAMES_INDICES);
//
//            run.execute();
//            sequence.execute();
//            read.execute();
//
//            con.commit();
//
//            run.close();
//            sequence.close();
//            read.close();
//
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "disabled run data domain indexing");
//    }
//    private void enableRunIndices() {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start enabling run data domain indexing");
//        try {
//
//            PreparedStatement run = con.prepareStatement(SQLStatements.ENABLE_RUN_INDICES);
//            PreparedStatement sequence = con.prepareStatement(SQLStatements.ENABLE_SEQUENCE_INDICES);
//            PreparedStatement read = con.prepareStatement(SQLStatements.ENABLE_READNAMES_INDICES);
//
//            run.execute();
//            sequence.execute();
//            read.execute();
//
//            con.commit();
//
//            run.close();
//            sequence.close();
//            read.close();
//
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "enabled run data domain indexing");
//    }
    private void disableReferenceIndices() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start disabling reference data domain indexing...");
        this.disableDomainIndices(MySQLStatements.DISABLE_REFERENCE_INDICES, null);
        this.disableDomainIndices(MySQLStatements.DISABLE_FEATURE_INDICES, null);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done disabling reference data domain indexing");
    }

    private void enableReferenceIndices() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start enabling reference data domain indexing...");
        this.enableDomainIndices(MySQLStatements.ENABLE_REFERENCE_INDICES, null);
        this.enableDomainIndices(MySQLStatements.ENABLE_FEATURE_INDICES, null);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done enabling reference data domain indexing");
    }

    private void storeGenome(ParsedReference reference) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing reference sequence data...");
        try {
            int id = (int) GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_REFERENCE_ID, con);
            reference.setID(id);
            PreparedStatement insertGenome = con.prepareStatement(SQLStatements.INSERT_REFGENOME);

            // store reference data
            insertGenome.setLong(1, reference.getID());
            insertGenome.setString(2, reference.getName());
            insertGenome.setString(3, reference.getDescription());
            insertGenome.setString(4, reference.getSequence());
            insertGenome.setTimestamp(5, reference.getTimestamp());
            insertGenome.execute();

            con.commit();

            insertGenome.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done inserting reference sequence data");
    }

    private void storeFeatures(ParsedReference reference) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start inserting features...");
        try {
            long id = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_FEATURE_ID, con);
            PreparedStatement insertFeature = con.prepareStatement(SQLStatements.INSERT_FEATURE);

            int batchCounter = 1;
            Iterator<ParsedFeature> it = reference.getFeatures().iterator();
            while (it.hasNext()) {
                batchCounter++;
                ParsedFeature f = it.next();
                insertFeature.setLong(1, id);
                insertFeature.setLong(2, reference.getID());
                insertFeature.setInt(3, f.getType());
                insertFeature.setInt(4, f.getStart());
                insertFeature.setInt(5, f.getStop());
                insertFeature.setString(6, f.getLocusTag());
                insertFeature.setString(7, f.getProduct());
                insertFeature.setString(8, f.getEcNumber());
                insertFeature.setInt(9, f.getStrand());
                insertFeature.setString(10, f.getGeneName());
                insertFeature.addBatch();
                id++;
                if (batchCounter == FEATURE_BATCH_SIZE) {
                    batchCounter = 1;
                    insertFeature.executeBatch();
                }
                //  it.remove();
            }

            insertFeature.executeBatch();
            insertFeature.close();


        } catch (SQLException ex) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
        }


        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done inserting features");
    }

    private void lockReferenceDomainTables() {
        this.lockDomainTables(MySQLStatements.LOCK_TABLE_REFERENCE_DOMAIN, "reference");
    }

    public int addRefGenome(ParsedReference reference) throws StorageException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Start storing reference sequence  \"{0}\"", reference.getName());

        try {
            con.setAutoCommit(false);

            if (adapter.equalsIgnoreCase("mysql")) {
                this.lockReferenceDomainTables();
                this.disableReferenceIndices();
            }

            this.storeGenome(reference);
            this.storeFeatures(reference);

            if (adapter.equalsIgnoreCase("mysql")) {
                this.enableReferenceIndices();
                this.unlockTables();
            }

            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "finished storing reference sequence \"{0}\"", reference.getName());
        return reference.getID();
    }

    private void storeCoverage(ParsedTrack track) {
        if (!track.isStepwise() | track.getParsedMappingContainer().isLastMappingContainer()) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing coverage information...");
            try {
                PreparedStatement insertCoverage = con.prepareStatement(SQLStatements.INSERT_COVERAGE);
                // get the latest used coverage id
                int id = -1;
                id = (int) GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_COVERAGE_ID, con);

                // insert coverage for track
                int batchCounter = 1;
                CoverageContainer cov = track.getCoverageContainer();
                Iterator<Integer> covsIt = cov.getCoveredPositions().iterator();
                while (covsIt.hasNext()) {
                    batchCounter++;
                    int pos = covsIt.next();
               
                        insertCoverage.setLong(1, id);
                        insertCoverage.setLong(2, track.getID());
                        insertCoverage.setInt(3, pos);
                        insertCoverage.setInt(4, cov.getBestMappingForwardCoverage(pos));
                        insertCoverage.setInt(5, cov.getNumberOfBestMappingsForward(pos));
                        insertCoverage.setInt(6, cov.getBestMappingReverseCoverage(pos));
                        insertCoverage.setInt(7, cov.getNumberOfBestMappingsReverse(pos));
                        insertCoverage.setInt(8, cov.getZeroErrorMappingsForwardCoverage(pos));
                        insertCoverage.setInt(9, cov.getNumberOfZeroErrorMappingsForward(pos));
                        insertCoverage.setInt(10, cov.getZeroErrorMappingsReverseCoverage(pos));
                        insertCoverage.setInt(11, cov.getNumberOfZeroErrorMappingsReverse(pos));
                        insertCoverage.setInt(12, cov.getNErrorMappingsForwardCoverage(pos));
                        insertCoverage.setInt(13, cov.getNumberOfNErrorMappingsForward(pos));
                        insertCoverage.setInt(14, cov.getNErrorMappingsReverseCoverage(pos));
                        insertCoverage.setInt(15, cov.getNumberOfNErrorMappingsReverse(pos));
                        insertCoverage.addBatch();
                        if (batchCounter == COVERAGE_BATCH_SIZE) {
                            batchCounter = 0;
                            insertCoverage.executeBatch();
                        }
                    
                    id++;
                }
                insertCoverage.executeBatch();
                insertCoverage.close();
                cov.clear();
            } catch (SQLException ex) {
                ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
            }
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing coverage information");
        }
    }

    private int storeTrack(ParsedTrack track, long refGenID) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing track data...");
        int id = -1;
        try {
            if (track.isFirstTrack() | !track.isStepwise()) {
                id = (int) GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_TRACK_ID, con);
                PreparedStatement insertTrack = con.prepareStatement(SQLStatements.INSERT_TRACK);

                // store track in table
                insertTrack.setLong(1, id);
                insertTrack.setLong(2, refGenID);
                insertTrack.setString(3, track.getDescription());
                insertTrack.setTimestamp(4, track.getTimestamp());
                //insertTrack.setLong(5, runID);
                insertTrack.execute();

                insertTrack.close();
                currentTrackID = id;
                track.setID(id);
            } else {
                track.setID(currentTrackID);
            }
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing track data");
        return id;
    }

    private void storeStatistics(ParsedTrack track) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing statistic information..");

        int noUniqueSeq = 0;
        int noUniqueMappings = 0;
        int coveragePerf = 0;
        int coverageBM = 0;
        int coverageComplete = 0;
        int numReads = 0;
        long trackID = track.getID();

        try {
            HashMap<Integer, Integer> mappingInfos = track.getParsedMappingContainer().getMappingInformations();
            mappings += mappingInfos.get(1);
            perfectMappings += mappingInfos.get(2);
            bmMappings += mappingInfos.get(3);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "...can't get the list");
        }
        try {
            if (!track.isStepwise() | track.getParsedMappingContainer().isLastMappingContainer()) {
                            // get latest id for track
            long id = -1;
            id = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_STATISTICS_ID, con);

                coveragePerf = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_OF_PERFECT_POSITIONS_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
                coverageBM = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_BM_COVERED_POSITION_FOR_TRACK, SQLStatements.GET_NUM, con, trackID);
                coverageComplete = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_COVERED_POSITIONS, SQLStatements.GET_NUM, con, trackID);
                noUniqueMappings = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_SINGLETON_MAPPINGS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
                noUniqueSeq = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_UNIQUE_SEQUENCES_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
                numReads = GenericSQLQueries.getIntegerFromDB(SQLStatements.FETCH_NUM_READS_FOR_TRACK_CALCULATE, SQLStatements.GET_NUM, con, trackID);
                PreparedStatement insertStatics = con.prepareStatement(SQLStatements.INSERT_STATISTICS);
                // store track in table
                insertStatics.setLong(1, id);
                insertStatics.setLong(2, trackID);
                insertStatics.setInt(3, mappings);
                insertStatics.setInt(4, perfectMappings);
                insertStatics.setInt(5, bmMappings);
                insertStatics.setInt(6, noUniqueMappings);
                insertStatics.setInt(7, coveragePerf);
                insertStatics.setInt(8, coverageBM);
                insertStatics.setInt(9, coverageComplete);
                insertStatics.setInt(10, noUniqueSeq);
                insertStatics.setInt(11, numReads);
                insertStatics.execute();
                insertStatics.close();
                mappings = 0;
                perfectMappings = 0;
                bmMappings = 0;
                numReads = 0;
            }

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing statistic information");
    }



    private void storeSeqPairTrackStatistics(ParsedSeqPairContainer seqPairContainer) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing sequence pair statistics...");

        long numSeqPairs = seqPairContainer.getNumOfSeqPairs();
        long numPerfSeqPairs = seqPairContainer.getNumOfPerfectSPs();
        long numUniqueSeqPairs = seqPairContainer.getNumOfUniqueSPs();
        long numUniquePerfSeqPairs = seqPairContainer.getNumUniquePerfectSPs();
        long numSingleMappings = seqPairContainer.getNumOfSingleMappings();

        for (int i = 0; i < 2; ++i) {
            try {
                PreparedStatement addStatistics = con.prepareStatement(SQLStatements.ADD_SEQPAIR_STATISTICS);
                long id = i == 0 ? seqPairContainer.getTrackId1() : seqPairContainer.getTrackId2();

                // store track in table
                addStatistics.setLong(1, numSeqPairs);
                addStatistics.setLong(2, numPerfSeqPairs);
                addStatistics.setLong(3, numUniqueSeqPairs);
                addStatistics.setLong(4, numUniquePerfSeqPairs);
                addStatistics.setLong(5, numSingleMappings);
                addStatistics.setLong(6, id);
                addStatistics.execute();

                addStatistics.close();

            } catch (SQLException ex) {
                ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
            }
        }


        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing track data");
    }

    private void storeMappings(ParsedTrack track) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing mapping data...");
        try {
            long mappingID = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_MAPPING_ID, con);
            //TODO: test cache or increase cache temporarily to improve performance: 
            //SELECT * FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME = 'info.CACHE_MAX_SIZE'
            //jdbc:h2:~/test;CACHE_SIZE=65536;
            PreparedStatement insertMapping = con.prepareStatement(SQLStatements.INSERT_MAPPING);

            // start storing the mappings
            int batchCounter = 1;
            //sequence ids can be the same in different tracks, track id has to be checked then
            Iterator<Integer> sequenceIDIterator = track.getParsedMappingContainer().getMappedSequenceIDs().iterator();
            while (sequenceIDIterator.hasNext()) { //sequence ids aller unterschiedlichen readsequenzen = unique reads
                int sequenceID = sequenceIDIterator.next();
                List<ParsedMapping> c = track.getParsedMappingContainer().getParsedMappingGroupBySeqID(sequenceID).getMappings();
                Iterator<ParsedMapping> mappingsIt = c.iterator(); //eine mapping group, alle gleiche seq id, untersch. pos
                while (mappingsIt.hasNext()) {
                    ParsedMapping m = mappingsIt.next(); //einzelnes mapping
                    m.setID(mappingID); //from now on id is set and can be used for sequence pairs for example!

                    insertMapping.setLong(1, mappingID); //einzigartig
                    insertMapping.setInt(2, m.getStart()); //einzigartig im zusammenhang
                    insertMapping.setInt(3, m.getStop()); //einzigartig im zusammenhang
                    insertMapping.setInt(4, (m.isBestMapping() ? 1 : 0));
                    insertMapping.setInt(5, m.getCount()); //count der gleichen seq (seq id) in reads
                    insertMapping.setByte(6, m.getDirection());
                    insertMapping.setInt(7, m.getErrors());
                    insertMapping.setInt(8, sequenceID); // mappings der gleichen seq an anderer stelle enthalten sie auch +
                    insertMapping.setLong(9, track.getID()); //gleichen count

                    insertMapping.addBatch();

                    if (batchCounter == MAPPING_BATCH_SIZE) {
                        insertMapping.executeBatch();
                        batchCounter = 0;
                    }

                    mappingID++;
                    batchCounter++;
                }
            }
            insertMapping.executeBatch();

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing mapping data");
    }

    private void storeDiffs(ParsedTrack track) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start inserting diff data...");

        try {
            long diffID = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_DIFF_ID, con);
            PreparedStatement insertDiff = con.prepareStatement(SQLStatements.INSERT_DIFF);
            PreparedStatement insertGap = con.prepareStatement(SQLStatements.INSERT_GAP);

            // byte flags for diffs
            byte gap = 0;
            byte diff = 1;

            // insert data
            int batchCounter = 0;
            Iterator<Integer> sequenceIDIterator = track.getParsedMappingContainer().getMappedSequenceIDs().iterator();
            while (sequenceIDIterator.hasNext()) {
                int sequenceID = sequenceIDIterator.next();
                List<ParsedMapping> c = track.getParsedMappingContainer().getParsedMappingGroupBySeqID(sequenceID).getMappings();
                Iterator<ParsedMapping> mappingsIt = c.iterator();

                // iterate mappings
                while (mappingsIt.hasNext()) {
                    ParsedMapping m = mappingsIt.next();

                    // iterate diffs (non-gap variation)
                    Iterator<ParsedDiff> diffsIt = m.getDiffs().iterator();
                    while (diffsIt.hasNext()) {

                        batchCounter++;

                        ParsedDiff d = diffsIt.next();
                        insertDiff.setLong(1, diffID++);
                        insertDiff.setLong(2, m.getID());
                        insertDiff.setString(3, Character.toString(d.getBase()));
                        insertDiff.setLong(4, d.getPosition());
                        insertDiff.setByte(5, diff);

                        insertDiff.addBatch();

                        if (batchCounter == DIFF_BATCH_SIZE) {
                            insertDiff.executeBatch();
                            batchCounter = 0;
                        }
                    }

                    // iterate gaps
                    Iterator<ParsedReferenceGap> gapIt = m.getGenomeGaps().iterator();
                    while (gapIt.hasNext()) {
                        batchCounter++;

                        ParsedReferenceGap g = gapIt.next();
                        insertGap.setLong(1, diffID++);
                        insertGap.setLong(2, m.getID());
                        insertGap.setString(3, Character.toString(g.getBase()));
                        insertGap.setLong(4, g.getAbsPos());
                        insertGap.setByte(5, gap);
                        insertGap.setInt(6, g.getOrder());

                        insertGap.addBatch();

                        if (batchCounter == DIFF_BATCH_SIZE) {
                            insertGap.executeBatch();
                            batchCounter = 0;
                        }
                    }
                }
            }

            insertDiff.executeBatch();
            insertGap.executeBatch();

            insertDiff.close();
            insertGap.close();

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done inserting diff data");
    }

    /**
     * Locks all tables involved when adding a track in mysql fashion.
     */
    private void lockTrackDomainTables() {
        this.lockDomainTables(MySQLStatements.LOCK_TABLE_TRACK_DOMAIN, "track");
    }

    /**
     * Locks all tables involved when adding seq pair data in mysql fashion.
     */
    private void lockSeqPairDomainTables() {
        this.lockDomainTables(MySQLStatements.LOCK_TABLE_SEQUENCE_PAIRS_DOMAIN, "seq pair");
    }

    /**
     * Locks all tables declared by the lock sql statement.
     * @param lockStatement sql statement to lock some tables
     * @param domainName name of the domain to lock for logging
     */
    private void lockDomainTables(String lockStatement, String domainName) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start locking {0} domain tables...", domainName);
        try {
            PreparedStatement lock = con.prepareStatement(lockStatement);
            lock.execute();
            lock.close();
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done locking {0} domain tables...", domainName);
    }

    public int addTrack(ParsedTrack track, long refGenID, boolean seqPairs) throws StorageException {

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Preparing statements for storing track data");

        if (adapter.equalsIgnoreCase("mysql")) {
            this.lockTrackDomainTables();
            this.disableTrackDomainIndices();
        }

        this.storeTrack(track, refGenID);
        this.storeCoverage(track);
        this.storeMappings(track);
        this.storeDiffs(track);
        this.storeStatistics(track);

        if (adapter.equalsIgnoreCase("mysql")) {
            this.enableTrackDomainIndices();
            this.unlockTables();
        }

        if (!seqPairs) {
            track.clear();
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Track \"{0}\" has been stored successfully", track.getDescription());

        return track.getID();
    }

    private void disableTrackDomainIndices() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "started disabling track data domain indices");
        this.disableDomainIndices(MySQLStatements.DISABLE_COVERAGE_INDICES, null);
        this.disableDomainIndices(MySQLStatements.DISABLE_TRACK_INDICES, null);
        this.disableDomainIndices(MySQLStatements.DISABLE_MAPPING_INDICES, null);
        this.disableDomainIndices(MySQLStatements.DISABLE_DIFF_INDICES, null);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "finished disabling track data domain indices");
    }

    private void enableTrackDomainIndices() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "started enabling track data domain indices");
        this.enableDomainIndices(MySQLStatements.ENABLE_COVERAGE_INDICES, null);
        this.enableDomainIndices(MySQLStatements.ENABLE_TRACK_INDICES, null);
        this.enableDomainIndices(MySQLStatements.ENABLE_MAPPING_INDICES, null);
        this.enableDomainIndices(MySQLStatements.ENABLE_DIFF_INDICES, null);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "finished enabling track data domain indices");
    }

    /**
     * Disables domain indices in mysql fashion.
     * @param sqlStatement mysql statement to disable domain indices
     * @param domainName name of the domain to disable, if not needed here, pass <code>null</code>
     */
    private void disableDomainIndices(String sqlStatement, String domainName) {
        if (domainName != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "started disabling {0} data domain indices", domainName);
        }
        try {
            PreparedStatement disableDomainIndices = con.prepareStatement(sqlStatement);
            disableDomainIndices.execute();
            disableDomainIndices.close();
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        if (domainName != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "finished disabling {0} data domain indices", domainName);
        }
    }

    /**
     * Enables domain indices in mysql fashion.
     * @param sqlStatement mysql statement to enable domain indices
     * @param domainName name of the domain to enable, if not needed here, pass <code>null</code>
     */
    private void enableDomainIndices(String sqlStatement, String domainName) {
        if (domainName != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "started enabling {0} data domain indices", domainName);
        }
        try {
            PreparedStatement enableDomainIndices = con.prepareStatement(sqlStatement);
            enableDomainIndices.execute();
            enableDomainIndices.close();
        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }

        if (domainName != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "finished enabling {0} data domain indices", domainName);
        }
    }

    private void disableSeqPairDomainIndices() {
        this.disableDomainIndices(MySQLStatements.DISABLE_SEQUENCE_PAIR_INDICES, "seq pair");
    }

    private void enableSeqPairDomainIndices() {
        this.enableDomainIndices(MySQLStatements.ENABLE_SEQUENCE_PAIR_INDICES, "seq pair");
    }

    public ReferenceConnector getRefGenomeConnector(long refGenID) {
        // only return new object, if no suitable connector was created before
        if (!refConnectors.containsKey(refGenID)) {
            refConnectors.put(refGenID, new ReferenceConnector(refGenID));
        }
        return refConnectors.get(refGenID);
    }

//    /**
//     * @param runID
//     * @param trackID
//     * @return
//     * @deprecated Since the RUN domain has been exckluded from VAMP
//     */
//    @Deprecated
//    public RunConnector getRunConnector(long runID,long trackID) {
//        // only return new object, if no suitable connector was created before
//        if (!runConnectors.containsKey(runID)) {
//            runConnectors.put(runID, new RunConnector(runID,trackID));
//        }
//        return runConnectors.get(runID);
//    }
    public TrackConnector getTrackConnector(PersistantTrack track) {
        // only return new object, if no suitable connector was created before
        long trackID = track.getId();
        if (!trackConnectors.containsKey(trackID)) {
            trackConnectors.put(trackID, new TrackConnector(track));
        }
        return trackConnectors.get(trackID);
    }

    public TrackConnector getTrackConnector(List<PersistantTrack> tracks) {
        // makes sure the track id is not already used
        long id = 9999;
        for (PersistantTrack track : tracks) {
            id += track.getId();
        }
        // only return new object, if no suitable connector was created before
        trackConnectors.put(id, new TrackConnector(id, tracks));
        if (!trackConnectors.containsKey(id)) {
        }
        return trackConnectors.get(id);
    }

    public Connection getConnection() {
        return con;
    }

//    public List<PersistentRun> getRuns() {
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Reading run data from database");
//        ArrayList<PersistentRun> runs = new ArrayList<PersistentRun>();
//
//        try {
//            PreparedStatement fetchRuns = con.prepareStatement(SQLStatements.FETCH_RUNS);
//            ResultSet rs = fetchRuns.executeQuery();
//
//            while (rs.next()) {
//                int id = rs.getInt(FieldNames.RUN_ID);
//                String description = rs.getString(FieldNames.RUN_DESCRIPTION);
//                Timestamp date = rs.getTimestamp(FieldNames.RUN_TIMESTAMP);
//                runs.add(new PersistentRun(id, description, date));
//            }
//
//        } catch (SQLException ex) {
//            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
//        }
//
//        return runs;
//    }
    public List<PersistantReference> getGenomes() {

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Reading reference genome data from database");
        ArrayList<PersistantReference> refGens = new ArrayList<PersistantReference>();

        try {
            PreparedStatement fetch;

            fetch = con.prepareStatement(SQLStatements.FETCH_GENOMES);

            ResultSet rs = fetch.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(FieldNames.REF_GEN_ID);
                String description = rs.getString(FieldNames.REF_GEN_DESCRIPTION);
                String name = rs.getString(FieldNames.REF_GEN_NAME);
                String sequence = null;
                sequence = rs.getString(FieldNames.REF_GEN_SEQUENCE);
                Timestamp timestamp = rs.getTimestamp(FieldNames.REF_GEN_TIMESTAMP);
                refGens.add(new PersistantReference(id, name, description, sequence, timestamp));
            }

        } catch (SQLException e) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), e);
        }

        return refGens;
    }

    public void deleteTrack(long trackID) throws StorageException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Starting deletion of track with id \"{0}\"", trackID);
        try {
            con.setAutoCommit(false);

            PreparedStatement deleteDiffs = con.prepareStatement(SQLStatements.DELETE_DIFFS_FROM_TRACK);
            deleteDiffs.setLong(1, trackID);
            PreparedStatement deleteMappings = con.prepareStatement(SQLStatements.DELETE_MAPPINGS_FROM_TRACK);
            deleteMappings.setLong(1, trackID);
            PreparedStatement deleteCoverage = con.prepareStatement(SQLStatements.DELETE_COVERAGE_FROM_TRACK);
            deleteCoverage.setLong(1, trackID);
            PreparedStatement deleteStatistics = con.prepareStatement(SQLStatements.DELETE_STATISTIC_FROM_TRACK);
            deleteStatistics.setLong(1, trackID);
            PreparedStatement deleteTrack = con.prepareStatement(SQLStatements.DELETE_TRACK);
            deleteTrack.setLong(1, trackID);

            deleteDiffs.execute();
            deleteMappings.execute();
            deleteCoverage.execute();
            deleteStatistics.execute();
            deleteTrack.execute();

            con.commit();

            deleteDiffs.close();
            deleteMappings.close();
            deleteCoverage.close();
            deleteStatistics.close();
            deleteTrack.close();

            con.setAutoCommit(true);

        } catch (SQLException ex) {
            throw new StorageException(ex);
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Finished deletion of track \"{0}\"", trackID);
    }

//    public void deleteRun(long runID) throws StorageException {
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Starting deletion of run with id \"{0}\"", runID);
//        try {
//            con.setAutoCommit(false);
//
//            PreparedStatement deleteReads = con.prepareStatement(SQLStatements.DELETE_READS_FROM_RUN);
//            deleteReads.setLong(1, runID);
//            PreparedStatement deleteSeqs = con.prepareStatement(SQLStatements.DELETE_SEQUENCE_FROM_RUN);
//            deleteSeqs.setLong(1, runID);
//            PreparedStatement deleteRun = con.prepareStatement(SQLStatements.DELETE_RUN);
//            deleteRun.setLong(1, runID);
//
//            deleteReads.execute();
//            deleteSeqs.execute();
//            deleteRun.execute();
//
//            con.commit();
//
//            deleteReads.close();
//            deleteSeqs.close();
//            deleteRun.close();
//
//            con.setAutoCommit(true);
//
//        } catch (SQLException ex) {
//            throw new StorageException(ex);
//        }
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Finished deletion of run with id \"{0}\"", runID);
//    }
    public void deleteGenome(long refGenID) throws StorageException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Starting deletion of reference genome with id \"{0}\"", refGenID);
        try {
            con.setAutoCommit(false);

            PreparedStatement deleteFeatures = con.prepareStatement(SQLStatements.DELETE_FEATURES_FROM_GENOME);
            deleteFeatures.setLong(1, refGenID);
            PreparedStatement deleteGenome = con.prepareStatement(SQLStatements.DELETE_GENOME);
            deleteGenome.setLong(1, refGenID);

            deleteFeatures.execute();
            deleteGenome.execute();

            con.commit();

            deleteFeatures.close();
            deleteGenome.close();

            con.setAutoCommit(true);

        } catch (SQLException ex) {
            throw new StorageException(ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Finished deletion of reference genome with id \"{0}\"", refGenID);
    }

//    public List<String> getReadNamesForSequenceID(int id) {
//        List<String> names = new ArrayList<String>();
//
//        try {
//            PreparedStatement fetch = con.prepareStatement(SQLStatements.FETCH_READNAMES_FOR_SEQUENCE_ID);
//            fetch.setInt(1, id);
//
//            ResultSet rs = fetch.executeQuery();
//            while (rs.next()) {
//                names.add(rs.getString(FieldNames.READ_NAME));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//        return names;
//    }
    /**
     * Adds the sequence pair data and statistics to the database.
     * @param seqPairData sequence pair data container holding the data to store
     */
    public void addSeqPairData(ParsedSeqPairContainer seqPairData) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Preparing statements for storing sequence pair data for track data");

        if (adapter.equalsIgnoreCase("mysql")) {
            this.lockSeqPairDomainTables();
            this.disableSeqPairDomainIndices();
        }

        this.storeSeqPairTrackStatistics(seqPairData);
        this.storeSeqPairData(seqPairData);

        if (adapter.equalsIgnoreCase("mysql")) {
            this.enableSeqPairDomainIndices();
            this.unlockTables();
        }

        seqPairData.clear();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Sequence pair data for tracks \"{0}\" has been stored successfully");

    }

    private void storeSeqPairData(ParsedSeqPairContainer seqPairData) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "start storing sequence pair data...");
        try {
            long id = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_SEQUENCE_PAIR_ID, con);
            long seqPairId = GenericSQLQueries.getLatestIDFromDB(SQLStatements.GET_LATEST_SEQUENCE_PAIR_PAIR_ID, con);
            PreparedStatement insertSeqPair = con.prepareStatement(SQLStatements.INSERT_SEQ_PAIR);
            PreparedStatement insertReplicate = con.prepareStatement(SQLStatements.INSERT_SEQ_PAIR_REPLICATE);
            long interimPairId;

            // start storing the sequence pair data
            int batchCounter = 1;
            int replicateCounter = 1;
            HashMap<Pair<Long, Long>, ParsedSeqPairMapping> seqPairMap = seqPairData.getParsedSeqPairs();
            Iterator seqPairIterator = seqPairMap.keySet().iterator();
            while (seqPairIterator.hasNext()) {
                ParsedSeqPairMapping seqPair = seqPairMap.get((Pair<Long, Long>) seqPairIterator.next());
                interimPairId = seqPair.getSequencePairID();
                seqPair.setSequencePairID(interimPairId + seqPairId);
                //if seq pairs are needed later on we have to set:
                //seqPair.setID(id);

                insertSeqPair.setLong(1, id++); //table index, unique for pos of seq pair
                insertSeqPair.setLong(2, seqPair.getSequencePairID()); //same for all positions of this sequence pair
                insertSeqPair.setLong(3, seqPair.getMappingId1()); //id of fst mapping
                insertSeqPair.setLong(4, seqPair.getMappingId2()); // id of scnd mapping
                insertSeqPair.setByte(5, seqPair.getType()); //type of the sequence pair

                insertSeqPair.addBatch();

                if (batchCounter == SEQPAIR_BATCH_SIZE) {
                    insertSeqPair.executeBatch();
                    batchCounter = 0;
                }
                batchCounter++;

                //insert replicates
                if (seqPair.getReplicates() > 1) {
                    insertReplicate.setLong(1, seqPair.getSequencePairID());
                    insertReplicate.setLong(2, seqPair.getReplicates());

                    insertReplicate.addBatch();

                    if (replicateCounter == SEQPAIR_PIVOT_BATCH_SIZE) {
                        insertReplicate.executeBatch();
                        replicateCounter = 0;
                    }
                    replicateCounter++;
                }

            }
            insertSeqPair.executeBatch();
            insertReplicate.executeBatch();


            //storing mapping to pair id data
            PreparedStatement insertSeqPairPivot = con.prepareStatement(SQLStatements.INSERT_SEQ_PAIR_PIVOT);
            batchCounter = 1;
            long correctSeqPairId;
            List<Pair<Long, Long>> mappingToPairIdList = seqPairData.getMappingToPairIdList();
            Iterator mappingToPairIdIterator = mappingToPairIdList.iterator();
            while (mappingToPairIdIterator.hasNext()) {
                Pair<Long, Long> pair = (Pair<Long, Long>) mappingToPairIdIterator.next();
                interimPairId = pair.getSecond();
                correctSeqPairId = interimPairId + seqPairId;

                insertSeqPairPivot.setLong(1, pair.getFirst()); //mapping id
                insertSeqPairPivot.setLong(2, correctSeqPairId); //sequence pair id

                insertSeqPairPivot.addBatch();

                if (batchCounter == SEQPAIR_PIVOT_BATCH_SIZE) {
                    insertSeqPairPivot.executeBatch();
                    batchCounter = 0;
                }
                batchCounter++;
            }
            insertSeqPairPivot.executeBatch();

            insertSeqPair.close();
            insertSeqPairPivot.close();
            insertReplicate.close();

        } catch (SQLException ex) {
            ProjectConnector.getInstance().rollbackOnError(this.getClass().getName(), ex);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "...done storing sequence pair data");
    }

    /**
     * Sets the sequence pair id for both tracks belonging to one sequence pair.
     * @param track1Id track id of first track of the pair
     * @param track2Id track id of second track of the pair
     */
    public void setSeqPairIdsForTrackIds(long track1Id, long track2Id) {

        try {
            Integer seqPairId = 1; //not 0, because 0 is the value when a track is not a sequence pair track!
            PreparedStatement getLatestSeqPairId = con.prepareStatement(SQLStatements.GET_LATEST_TRACK_SEQUENCE_PAIR_ID);

            ResultSet rs = getLatestSeqPairId.executeQuery();
            if (rs.next()) {
                seqPairId = rs.getInt("LATEST_ID");
                if (seqPairId == null || seqPairId == 0) {
                    seqPairId = 1;
                }
            }

            PreparedStatement setSeqPairIds = con.prepareStatement(SQLStatements.INSERT_TRACK_SEQ_PAIR_ID);

            setSeqPairIds.setInt(1, seqPairId);
            setSeqPairIds.setLong(2, track1Id);
            setSeqPairIds.execute();

            setSeqPairIds.setInt(1, seqPairId);
            setSeqPairIds.setLong(2, track2Id);
            setSeqPairIds.execute();

            setSeqPairIds.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProjectConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //TODO: delete seqpairs
    //TODO: seqpair queries
}