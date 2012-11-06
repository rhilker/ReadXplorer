package de.cebitec.vamp.databackend.dataObjects;

/**
 * Contains the basic functionality of a persistant result.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class PersistantResult {
    
    private int lowerBound;
    private int upperBound;

    /**
     * Common functionality of a persistant result.
     * @param lowerBound the lower bound of the interval of this request
     * @param upperBound the upper bound of the interval of this request
     */
    public PersistantResult(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    /**
     * @return the lower bound of the interval of this request
     */
    public int getLowerBound() {
        return this.lowerBound;
    }

    /**
     * @return the upper bound of the interval of this request
     */
    public int getUpperBound() {
        return this.upperBound;
    }
    
}
