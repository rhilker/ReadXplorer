package de.cebitec.vamp.transcriptomeAnalyses.datastructure;

import de.cebitec.vamp.databackend.dataObjects.PersistantFeature;
import de.cebitec.vamp.databackend.dataObjects.TrackResultEntry;

/**
 * Data structure for storing a gene start with position, strand, initial
 * coverage (coverage directly before predicted gene start) and start coverage
 * (coverage at predicted gene start).
 *
 * @author -Rolf Hilker-, modified by -jritter-
 */
public class TranscriptionStart extends TrackResultEntry {

    private int pos;
    private boolean isFwdStrand;
    private int readStarts;
    private double relCount;
    private int[] countsBeforeStart = new int[10];
//    private DetectedFeatures detFeatures;
    private PersistantFeature detectedGene;
    private int offset;
    private int dist2start, dist2stop;
    private PersistantFeature nextGene;
    private int nextOffset;
    private String sequence;
    private int[] beforeCounts;
//    fdata = forward[i] + ";" + rel_count + ";" + before + feature.getFeatureName() + ";" + j;
//    fdata += ";" + dist2start + ";" + dist2stop;
//    fdata += ";" + forwardCDSs.get(i + j + l - end).get(0) + ";" + l;

    /**
     * Data structure for storing a gene start.
     *
     * @param pos The position at which the gene start was detected
     * @param isFwdStrand true, if the transcript start was detected on the fwd
     * strand, false otherwise.
     * @param readStarts The number of read starts at the detected tss position
     * @param percentIncrease The coverage at the detected gene start position
     * (getPos()).
     * @param coverageIncrease the coverage increase from the position before
     * the TSS to the detected TSS position
     * @param detFeatures object containing the features associated to this
     * predicted gene start
     */
    public TranscriptionStart(int pos, boolean isFwdStrand, int readStarts, DetectedFeatures detFeatures, int trackId) {
        super(trackId);
        this.pos = pos;
        this.isFwdStrand = isFwdStrand;
        this.readStarts = readStarts;
    }

    /**
     * Data structure for storing a gene start.
     *
     * @param pos The position at which the gene start was detected
     * @param isFwdStrand true, if the transcript start was detected on the fwd
     * strand, false otherwise.
     * @param readStarts The number of read starts at the detected tss position
     * @param relCount
     * @param detectedGene
     * @param offset
     * @param dist2start
     * @param dist2stop
     * @param nextGene
     * @param nextOffset
     * @param sequence
     * @param trackId
     */
    public TranscriptionStart(int pos, boolean isFwdStrand, int readStarts, double relCount, int[] before, PersistantFeature detectedGene, int offset, int dist2start, int dist2stop, PersistantFeature nextGene, int nextOffset, String sequence, int trackId) {
        super(trackId);
        this.pos = pos;
        this.isFwdStrand = isFwdStrand;
        this.readStarts = readStarts;
        this.relCount = relCount;
        this.detectedGene = detectedGene;
        this.offset = offset;
        this.dist2start = dist2start;
        this.dist2stop = dist2stop;
        this.nextGene = nextGene;
        this.nextOffset = nextOffset;
        this.sequence = sequence;
        this.beforeCounts = before;
    }

    public int[] getBeforeCounts() {
        return beforeCounts;
    }

    /**
     * @return The position at which the gene start was detected
     */
    public int getPos() {
        return this.pos;
    }

    /**
     * @return true, if the transcript start was detected on the fwd strand,
     * false otherwise.
     */
    public boolean isFwdStrand() {
        return this.isFwdStrand;
    }

    public boolean isIsFwdStrand() {
        return isFwdStrand;
    }

    public void setIsFwdStrand(boolean isFwdStrand) {
        this.isFwdStrand = isFwdStrand;
    }

    public int getReadStarts() {
        return readStarts;
    }

    public void setReadStarts(int readStarts) {
        this.readStarts = readStarts;
    }

    public double getRelCount() {
        return relCount;
    }

    public void setRelCount(double relCount) {
        this.relCount = relCount;
    }

    public int[] getCountsBeforeStart() {
        return countsBeforeStart;
    }

    public void setCountsBeforeStart(int[] countsBeforeStart) {
        this.countsBeforeStart = countsBeforeStart;
    }

    public PersistantFeature getDetectedGene() {
        return detectedGene;
    }

    public void setDetectedGene(PersistantFeature detectedGene) {
        this.detectedGene = detectedGene;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getDist2start() {
        return dist2start;
    }

    public void setDist2start(int dist2start) {
        this.dist2start = dist2start;
    }

    public int getDist2stop() {
        return dist2stop;
    }

    public void setDist2stop(int dist2stop) {
        this.dist2stop = dist2stop;
    }

    public PersistantFeature getNextGene() {
        return nextGene;
    }

    public void setNextGene(PersistantFeature nextGene) {
        this.nextGene = nextGene;
    }

    public int getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(int nextOffset) {
        this.nextOffset = nextOffset;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
