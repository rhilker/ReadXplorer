package de.cebitec.vamp.seqPairClassifier;

import net.sf.samtools.SAMRecord;

/**
 * A sequence pair of a direct access track containing two sam records. the id, 
 * the classification and the distance of the pair.
 * 
 * @author -Rolf Hilker-
 */
public class DirectSeqPair {

    private SAMRecord record1;
    private SAMRecord record2;
    private int seqPairId;
    private byte type;
    private int distance;
    
    /**
     * A sequence pair of a direct access track.
     * @param record1 first sam record of the pair
     * @param record2 second sam record of the pair
     * @param seqPairId the sequence pair id of this pair
     * @param type the type of this pair (select among 
     *      Properties.TYPE_PERFECT_PAIR, Properties.TYPE_...)
     * @param distance The distance of the two mappings of the pair
     */
    public DirectSeqPair(SAMRecord record1, SAMRecord record2, int seqPairId, byte type, int distance) {
        this.record1 = record1;
        this.record2 = record2;
        this.seqPairId = seqPairId;
        this.type = type;
        this.distance = distance;
    }

    /**
     * @return the first sam record of the pair
     */
    public SAMRecord getRecord1() {
        return this.record1;
    }

    /**
     * @return the second sam record of the pair
     */
    public SAMRecord getRecord2() {
        return this.record2;
    }

    /**
     * @return the sequence pair id of this pair
     */
    public int getSeqPairId() {
        return this.seqPairId;
    }

    /**
     * @return the type of this pair (among Properties.TYPE_PERFECT_PAIR, 
     *      Properties.TYPE_...)
     */
    public byte getType() {
        return this.type;
    }

    /**
     * @return The distance of the two mappings of the pair.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Update the sequence pair type, if it changes.
     * @param seqPairType the new sequence pair type
     */
    public void setType(byte seqPairType) {
        this.type = seqPairType;
    }
     
}
