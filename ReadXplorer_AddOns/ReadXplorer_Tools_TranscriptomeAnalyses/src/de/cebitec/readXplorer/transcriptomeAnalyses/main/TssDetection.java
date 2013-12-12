package de.cebitec.readXplorer.transcriptomeAnalyses.main;

import de.cebitec.readXplorer.api.objects.AnalysisI;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantChromosome;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantFeature;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantReference;
import de.cebitec.readXplorer.transcriptomeAnalyses.datastructures.Antisense;
import de.cebitec.readXplorer.transcriptomeAnalyses.datastructures.NovelRegion;
import de.cebitec.readXplorer.transcriptomeAnalyses.datastructures.TranscriptionStart;
import de.cebitec.readXplorer.util.FeatureType;
import de.cebitec.readXplorer.util.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jritter
 */
public class TssDetection implements Observer, AnalysisI<List<TranscriptionStart>> {

    private List<TranscriptionStart> detectedTSS;
    private List<NovelRegion> detectedPutativeNewRegions;
    private List<Antisense> detectedPutativeAntisenseTSS;
    private int trackid;
    private final PersistantReference refGenome;

    public TssDetection(PersistantReference refGenome, int trackID) {
        this.refGenome = refGenome;
        this.detectedTSS = new ArrayList<>();
        this.detectedPutativeAntisenseTSS = new ArrayList<>();
        this.detectedPutativeNewRegions = new ArrayList<>();
        this.trackid = trackID;
    }

    /**
     * Running the transcription start site detection.
     *
     * @param length Length of the reference genome.
     * @param forwardCDSs CDS information for forward regions in genome.
     * @param reverseCDSs CDS information for reverse regions in genome.
     * @param allRegionsInHash HashMap with all featureIDs and associated
     * features.
     * @param ratio User given ratio for minimum increase of start counts from
     * pos to pos + 1.
     * @param up Number of bases for sequence in upstream direction beginning
     * from TSS.
     * @param down Number of bases for sequence in downstream direction
     * beginning from TSS.
     * @param isLeaderlessDetection true for performing leaderless detection.
     * @param leaderlessRestirction Restriction of bases upstream and
     * downstream.
     * @param isExclusionOfInternalTss true for excluding internal TSS.
     * @param distanceForExcludingTss number restricting the distance between
     * TSS and detected gene.
     */
    public void runningTSSDetection(HashMap<Integer, List<Integer>> forwardCDSs, HashMap<Integer, List<Integer>> reverseCDSs, 
            HashMap<Integer, PersistantFeature> allRegionsInHash, Statistics stats, ParameterSetFiveEnrichedAnalyses parameters) {

        int ratio = parameters.getRatio();
        int up = parameters.getUpstreamRegion();
        int down = parameters.getDownstreamRegion();
        int leaderlessRestirction = parameters.getLeaderlessLimit();
        boolean isExclusionOfInternalTss = parameters.isExclusionOfInternalTSS();
        Integer distanceForExcludingTss = parameters.getExclusionOfTSSDistance();
        int keepingInternalTssDistance = parameters.getKeepingInternalTssDistance();
        int[][] forward = stats.getForward(); // Array with startsite count information for forward mapping positions.
        int[][] reverse = stats.getReverse(); // Array with startsite count information for reverse mapping positions.
        int[][] fwdCov = stats.getFwdCoverage(); // Array with coverage counts of mappings in forward direction.
        int[][] revCov = stats.getRevCoverage(); // Array with coverage counts of mappings in reverse direction.
        double mm = stats.getMm(); // Mappings per Million.
        double bg = stats.getBg(); // Background cutoff

        for (PersistantChromosome chrom : refGenome.getChromosomes().values()) {
            int chromId = chrom.getId();
            int chromNo = chrom.getChromNumber();
            int chromLength = chrom.getLength();
            for (int i = 0; i < chromLength; i++) {

                if ((forward[chromNo][i] > bg) || (reverse[chromNo][i] > bg)) { // background cutoff is passed
                    int f_before = forward[chromNo][i - 1] + 1;
                    int r_before = reverse[chromNo][i + 1] + 1;

                    int f_ratio = (forward[chromNo][i] + 1) / f_before;
                    int r_ratio = (reverse[chromNo][i] + 1) / r_before;

                if (f_ratio >= ratio) {

                    int offset = 0;
                    int end = 0;

                    // counting the offset as long as there is no featureID
                    while (!forwardCDSs.containsKey(i + offset - end)) {
                            if ((i + offset) > chromLength) {
                                end = chromLength;
                        }
                        offset++;
                    }

                    // the 10 count Positions before mapping starts
                    int[] beforeCountsFwd = new int[10];
                    for (int k = 0; k < 10; k++) {
                            int count = fwdCov[chromNo][i - (k + 1)];
                        beforeCountsFwd[k] = count;
                    }

                        double rel_count = forward[chromNo][i] / mm;

                        // TODO: here we have with forwardCDSs.get(i + j - end).get(0) the feature ID, now we have to get the name
                    PersistantFeature feature = allRegionsInHash.get(forwardCDSs.get(i + offset - end).get(0));
                    int dist2start = 0;
                    int dist2stop = 0;
                    boolean leaderless = false;
                    boolean cdsShift = false;
                    boolean putativeUnannotated = false;
                    boolean isFwd = true;
                    int startSubSeq = i - up;
                    int stopSubSeq = i + down;
                    int nextOffset = 0;
                        String startCodon;
                        String stopCodon;


                    // We have 4 possible cases here
                    // 1. Offset is 0 which means, that the TSS is a Leaderless (<=7bp downstream from feature start)  TSS 
                    // or 
                    // 2. TSS is an internal (>7bp downstream from feature start) TSS
                    // 3. Offset is < 7bp which is maybe a leaderless upstream
                    // 4. Offset is > 500bp, which maybe is a TSS for unannotated Transcript which have 
                    // to be blasted
                    // 
                    if (offset == 0) {
                        dist2start = i - feature.getStart();
                        dist2stop = feature.getStop() - i;

                        // check if leaderless (downstream)
                        if (dist2start < leaderlessRestirction) {
                            leaderless = true;
                                startCodon = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                stopCodon = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                TranscriptionStart tss = new TranscriptionStart(i, isFwd, forward[chromNo][i], rel_count,
                                        beforeCountsFwd, feature, offset, dist2start, dist2stop, null, nextOffset,
                                        getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq), leaderless, cdsShift,
                                        putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId); //TODO: check if it is internal
                            if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                detectedTSS.add(tss);
                            }
                            }

                            if (dist2start > leaderlessRestirction && isExclusionOfInternalTss == false) { // internal mapping or TSS for nextGene
                            // here we want to find the next gene because the startsite is inbetween a gene which is not a leaderles gene
                            int currentFeatureID = feature.getId();
                            int j = 1;
                            PersistantFeature nextFeature = allRegionsInHash.get(currentFeatureID + j);
                            while (feature.getLocus().equals(nextFeature.getLocus())) {
                                j++;
                                nextFeature = allRegionsInHash.get(currentFeatureID + j);
                            }
                            nextOffset = nextFeature.getStart() - i;

                            if (nextOffset < keepingInternalTssDistance) {
                                // putative the corresponding gene for TSS
                                if (nextFeature.isFwdStrand()) {
                                        startCodon = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                        stopCodon = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                        TranscriptionStart tss = new TranscriptionStart(i, isFwd, forward[chromNo][i], rel_count, 
                                                beforeCountsFwd, null, offset, dist2start, dist2stop, nextFeature, nextOffset, 
                                                getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq), leaderless, cdsShift, putativeUnannotated, 
                                                startCodon, stopCodon, false, this.trackid, chromId); //TODO: check if it is internal
                                    if (!nextFeature.getType().equals(FeatureType.RRNA) && !nextFeature.getType().equals(FeatureType.TRNA)) {
                                        detectedTSS.add(tss);
                                    }
                                }
                            } else { // here we check the case of putative unannotated feature or putative antisense
                                int distanceToNextGene = 1000; // hae to be set by the user! TODO!
                                if (nextOffset > distanceToNextGene) {
                                    NovelRegion nr = new NovelRegion(isFwd, i, nextOffset, trackid, chromId);
                                    detectedPutativeNewRegions.add(nr);
                                }
                                int newOffset = 0;
                                int newEnd = 0;

                                // counting the offset as long as there is no featureID
                                while (!reverseCDSs.containsKey(i + newOffset - newEnd)) {
                                    if ((i + newOffset) > chromLength) {
                                        newEnd = chromLength;
                                    }
                                    newOffset++;
                                }

                                PersistantFeature antisenseFeature = allRegionsInHash.get(reverseCDSs.get(i + newOffset - newEnd).get(0));
                                Antisense as = new Antisense(isFwd, trackid, chromId, "antisense", i);
                                detectedPutativeAntisenseTSS.add(as);
                            }
                        }
                    } else {

                        // leaderless in upstream direction
                        if (offset < leaderlessRestirction) {
                            leaderless = true;
                                startCodon = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                stopCodon = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                TranscriptionStart tss = new TranscriptionStart(i, isFwd, forward[chromNo][i], rel_count, beforeCountsFwd, feature, 
                                        offset, dist2start, dist2stop, null, nextOffset, getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq), leaderless, 
                                        cdsShift, putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId);//TODO: check if it is internal
                            if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                detectedTSS.add(tss);
                            }
                        } else {
                            if (offset < distanceForExcludingTss) {
                                    startCodon = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                    stopCodon = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                    TranscriptionStart tss = new TranscriptionStart(i, isFwd, forward[chromNo][i], rel_count, beforeCountsFwd, 
                                            feature, offset, dist2start, dist2stop, null, nextOffset, getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq), 
                                            leaderless, cdsShift, putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId);//TODO: check if it is internal
                                if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                    detectedTSS.add(tss);
                                }
                            } else {
                                // TODO maybe unannotated!
                            }
                        }
                    }
                }


                if (r_ratio >= ratio) {

                    int offset = 0;
                    int end = 0;

                    // counting the offset as long as there is no featureID
                    while (!reverseCDSs.containsKey(end + i - offset)) {
                        if ((i - offset) == 0) {
                                end = chromLength;
                        }
                        offset++;
                    }

                    int[] beforeCountRev = new int[10];
                    for (int k = 0; k < 10; k++) {
                            int count = revCov[chromNo][i + k + 1];
                        beforeCountRev[k] = count;
                    }

                        double rel_count = reverse[chromNo][i] / mm;

                    PersistantFeature feature = allRegionsInHash.get(reverseCDSs.get(end + i - offset).get(0));
                    int dist2start = 0;
                    int dist2stop = 0;
                    boolean leaderless = false;
                    boolean cdsShift = false;
                    boolean putativeUnannotated = false;
                    boolean isFwd = false;
                    int startSubSeq = i - down;
                    int stopSubSeq = i + up;
                    int nextOffset = 0;
                    String startCodon = "";
                    String stopCodon = "";

                    if (offset == 0) {
                        dist2start = feature.getStop() - i;
                        dist2stop = i - feature.getStart();

                        // check if leaderless (downstream)
                        if (dist2start < leaderlessRestirction) {
                            leaderless = true;
//                            if (leaderless && (leaderlessInitSeqDown.equals("GTG") || leaderlessInitSeqDown.equals("CTG") || leaderlessInitSeqDown.equals("TTG"))) {
//                                cdsShift = true;
//                            }
                                String startCodonRev = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                String stopCodonRev = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                            startCodon = Complement(startCodonRev);
                            stopCodon = Complement(stopCodonRev);
                                String reversedSeq = new StringBuffer(getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq)).reverse().toString();
                            String revComplement = Complement(reversedSeq);
                                TranscriptionStart tss = new TranscriptionStart(i, isFwd, reverse[chromNo][i], rel_count, beforeCountRev, 
                                        feature, offset, dist2start, dist2stop, null, nextOffset, revComplement, leaderless, cdsShift, 
                                        putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId); //TODO: check if it is internal
                            if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                detectedTSS.add(tss);
                            }
                        }

                        if (dist2start > leaderlessRestirction && isExclusionOfInternalTss == false) {

                            int currentFeatureID = feature.getId();
                            int j = 1;
                            PersistantFeature nextFeature = allRegionsInHash.get(currentFeatureID - j);
                            while (feature.getLocus().equals(nextFeature.getLocus())) {
                                j++;
                                nextFeature = allRegionsInHash.get(currentFeatureID - j);
                            }
                            nextOffset = i - nextFeature.getStop();

                            if (nextOffset < keepingInternalTssDistance) {
                                // puttative nextgene
                                if (!nextFeature.isFwdStrand()) {
                                        String startCodonRev = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                        String stopCodonRev = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                    startCodon = Complement(startCodonRev);
                                    stopCodon = Complement(stopCodonRev);
                                        String reversedSeq = new StringBuffer(getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq)).reverse().toString();
                                    String revComplement = Complement(reversedSeq);
                                        TranscriptionStart tss = new TranscriptionStart(i, isFwd, reverse[chromNo][i], rel_count, beforeCountRev, 
                                                null, offset, dist2start, dist2stop, nextFeature, nextOffset, revComplement, leaderless, cdsShift, 
                                                putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId); //TODO: check if it is internal
                                    if (!nextFeature.getType().equals(FeatureType.RRNA) && !nextFeature.getType().equals(FeatureType.TRNA)) {
                                        detectedTSS.add(tss);
                                    }
                                }
                            }
                            // TODO not yet needed!
//                            else {
//                                // puttative Unannotated 
//                                String reversedSeq = new StringBuffer(getSubSeq(false, startSubSeq, stopSubSeq)).reverse().toString();
//                                String revComplement = ReverseComplement(reversedSeq);
//                                TranscriptionStart tss = new TranscriptionStart(i, false, reverse[i], rel_count, beforeCountRev, feature, offset, dist2start, dist2stop, null, 0, revComplement, leaderless, cdsShift, false, 1);
//                                detectedTSS.add(tss);
//                            }
                        }

                    } else {
                        if (offset < leaderlessRestirction) {
                            // Leaderless TSS upstream
                            leaderless = true;
//                            if (leaderless && (leaderlessInitSeqDown.equals("GTG") || leaderlessInitSeqDown.equals("CTG") || leaderlessInitSeqDown.equals("TTG"))) {
//                                cdsShift = true;
//                            }
                                String startCodonRev = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                String stopCodonRev = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                            startCodon = Complement(startCodonRev);
                            stopCodon = Complement(stopCodonRev);
                                String reversedSeq = new StringBuffer(getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq)).reverse().toString();
                            String revComplement = Complement(reversedSeq);
                                TranscriptionStart tss = new TranscriptionStart(i, isFwd, reverse[chromNo][i], rel_count, beforeCountRev, 
                                        feature, offset, dist2start, dist2stop, null, nextOffset, revComplement, leaderless, cdsShift, 
                                        putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId); //TODO: check if it is internal
                            if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                detectedTSS.add(tss);
                            }
                        } else {
                            // "normal" TSS
                            if (offset < distanceForExcludingTss) {
                                    String startCodonRev = getSubSeq(chrom, isFwd, feature.getStop() - 3, feature.getStop());
                                    String stopCodonRev = getSubSeq(chrom, isFwd, feature.getStart() - 1, feature.getStart() + 2);
                                startCodon = Complement(startCodonRev);
                                stopCodon = Complement(stopCodonRev);
                                    String reversedSeq = new StringBuffer(getSubSeq(chrom, isFwd, startSubSeq, stopSubSeq)).reverse().toString();
                                String revComplement = Complement(reversedSeq);
                                    TranscriptionStart tss = new TranscriptionStart(i, isFwd, reverse[chromNo][i], rel_count, beforeCountRev, 
                                            feature, offset, dist2start, dist2stop, null, nextOffset, revComplement, leaderless, cdsShift, 
                                            putativeUnannotated, startCodon, stopCodon, false, this.trackid, chromId);
                                if (!feature.getType().equals(FeatureType.RRNA) && !feature.getType().equals(FeatureType.TRNA)) {
                                    detectedTSS.add(tss);
                                }
                            } else {
                                // TODO maybe unannotated!
                            }
                        }
                    }

                }
            }
        }
    }
    }

    /**
     * Gets a DNA String and complement it. A to T, T to A, G to C and C to G.
     *
     * @param seq is DNA String.
     * @return the compliment of seq.
     */
    private String Complement(String seq) {
        char BASE_A = 'A';
        char BASE_C = 'C';
        char BASE_G = 'G';
        char BASE_T = 'T';
        String a = "A";
        String c = "C";
        String g = "G";
        String t = "T";
        String compliment = "";

        for (int i = 0; i < seq.length(); i++) {
            if (BASE_A == seq.charAt(i)) {
                compliment = compliment.concat(t);
            } else if (BASE_C == (seq.charAt(i))) {
                compliment = compliment.concat(g);

            } else if (BASE_G == seq.charAt(i)) {
                compliment = compliment.concat(c);

            } else if (BASE_T == seq.charAt(i)) {
                compliment = compliment.concat(a);
            }
        }

        return compliment;

    }

    /**
     * If the direction is reverse, the subsequence will be inverted.
     *
     * @param isFwd direction of sequence.
     * @param start start of subsequence.
     * @param stop stop of subsequence.
     * @return the subsequence.
     */
    private String getSubSeq(PersistantChromosome chrom, boolean isFwd, int start, int stop) {

        String seq = "";
        if (start > 0 && stop < chrom.getLength()) {
            seq = chrom.getSequence(this).substring(start, stop);
        }
        if (isFwd) {
            return seq;
        } else {
            String reversedSeq = new StringBuffer(seq).reverse().toString();
            return reversedSeq;
        }
    }

    @Override
    public void update(Object args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TranscriptionStart> getResults() {
        return detectedTSS;
    }

    public List<NovelRegion> getDetectedPutativeNewRegions() {
        return detectedPutativeNewRegions;
    }

    public List<Antisense> getDetectedPutativeAntisenseTSS() {
        return detectedPutativeAntisenseTSS;
    }
}
