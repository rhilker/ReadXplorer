package de.cebitec.vamp.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all global accessible util methods.
 * 
 * @author Rolf Hilker
 */
public final class SequenceUtils {

    /** Indicates that something is located on the forward strand (1). */
    public static final byte STRAND_FWD = 1;
    /** Indicates that something is located on the reverse strand (-1). */
    public static final byte STRAND_REV = -1;
    
    /** String for tagging positions or anything else as not having a gene with "No gene".*/
    public static final String NO_GENE = "No gene";
    
    private SequenceUtils(){
        //do not instantiate
    }

    /**
     * Reverses a string.
     * @param string the string to reverse
     * @return the reversed string
     */
    public static String reverseString(final String string) {
        StringBuilder revString = new StringBuilder(string);
        revString.reverse();
        return revString.toString();
    }

    /**
     * Complements a sequence String. Requires only lower case characters!
     * @param sequence the string to complement
     * @return the complemented string
     */
    public static String complementDNA(final String sequence){
        StringBuilder complement = new StringBuilder(sequence.length());
        char currChar;
        for (int i = 0; i < sequence.length(); i++) {
            currChar = sequence.charAt(i);

            switch (currChar){
                case 'c': complement = complement.append('g'); break;
                case 'g': complement = complement.append('c'); break;
                case 't': complement = complement.append('a'); break;
                case 'a': complement = complement.append('t'); break;
                default : complement = complement.append(currChar);
            }
        }
        return complement.toString();
    }


    /**
     * Produces the reverse complement of a dna sequence.
     * @param sequence the dna sequence to reverse and complement
     * @return the reversed and complemented dna sequence
     */
    public static String getReverseComplement(String sequence) {
        StringBuilder revCompSeq = new StringBuilder();
        for (int i = sequence.length() - 1; i >= 0; --i) {
            char base = sequence.charAt(i);
            base = SequenceUtils.getDnaComplement(base, sequence);
            revCompSeq.append(base);
        }
        return revCompSeq.toString();
    }




    /**
     * Produces the complement of a single base. For error handling
     * also the whole sequence has to be passed. Returns only upper
     * case values.
     * A = T
     * G = C
     * N = N
     * _ = _
     * @param base the base to complement
     * @param sequence the sequence the base originates from or an empty string if the sequence is not accessible
     * @return the complemented base
     */
    public static char getDnaComplement(char base, String sequence) {
        base = Character.toUpperCase(base);
        char comp = ' ';
        switch (base) {
                case 'C': comp = 'G'; break;
                case 'G': comp = 'C'; break;
                case 'T': comp = 'A'; break;
                case 'A': comp = 'T'; break;
                case 'N': comp = 'N'; break;
                case '_': comp = '_'; break;
                default : Logger.getLogger(SequenceUtils.class.getName()).log(Level.SEVERE, 
                        "Found unknown char {0}!Sequence: {1}", new Object[]{base, sequence});
            }

        return comp;
    }
    
    /**
     * Checks if the input sequence is a valid DNA string (!not RNA!).
     * @param sequence input sequence to check
     * @return <code>true</code> if it is a valid DNA string, <code>false</code> otherwise
     */
    public static boolean isValidDnaString(String sequence) {
        return sequence.matches("[acgtnACGTN]+");
    }
}
