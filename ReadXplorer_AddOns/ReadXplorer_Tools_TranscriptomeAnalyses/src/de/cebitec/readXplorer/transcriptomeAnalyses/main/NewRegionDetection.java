package de.cebitec.readXplorer.transcriptomeAnalyses.main;

import de.cebitec.readXplorer.api.objects.AnalysisI;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantChromosome;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantFeature;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantReference;
import de.cebitec.readXplorer.transcriptomeAnalyses.datastructures.NovelRegion;
import de.cebitec.readXplorer.transcriptomeAnalyses.datastructures.TranscriptionStart;
import de.cebitec.readXplorer.util.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jritter
 */
public class NewRegionDetection implements Observer, AnalysisI<List<NovelRegion>> {

    private List<NovelRegion> novelRegions;
    private final PersistantReference refGenome;
    private int trackid;

    public NewRegionDetection(PersistantReference refGenome, int trackID) {
        this.novelRegions = new ArrayList<>();
        this.refGenome = refGenome;
        this.trackid = trackID;
    }

    public void runningNewRegionsDetection(HashMap<Integer, List<Integer>> forwardCDSs,
            HashMap<Integer, List<Integer>> reverseCDSs, HashMap<Integer, PersistantFeature> allRegionsInHash,
            Statistics stats, ParameterSetWholeTranscriptAnalyses params) {


        // Key is flag and Value the count of this flag
        HashMap<Integer, Integer> dropdownsFwd = new HashMap<>();
        HashMap<Integer, Integer> dropdownsRev = new HashMap<>();
        NovelRegion newRegion = null;
        int[][] forward = stats.getForward(); // Array with startsite count information for forward mapping positions.
        int[][] reverse = stats.getReverse(); // Array with startsite count information for reverse mapping positions.
        int[][] fwdCov = stats.getFwdCoverage(); // Array with coverage counts of mappings in forward direction.
        int[][] revCov = stats.getRevCoverage(); // Array with coverage counts of mappings in reverse direction.
        double bg = stats.getBg(); // Background cutoff
        int minLengthBoundary = params.getMinLengthBoundary();
        for (PersistantChromosome chrom : refGenome.getChromosomes().values()) {
            int chromId = chrom.getId();
            int chromNo = chrom.getChromNumber();
            int chromLength = chrom.getLength();
            for (int i = 0; i < chromLength; i++) {

                int fwd_readstarts = forward[chromNo - 1][i];
                if (fwd_readstarts > bg) { // got through possible forward hits first
                    int j = 0;
                    int end = 0;

                    // check if the hits can be attributed to a region (up to 700bp downstream)
                    while (!forwardCDSs.containsKey(i + j - end)) {
                        if (j > 700) {
                            break;
                        }
                        if ((i + j) > chromLength) {
                            end = chromLength;
                        }
                        j++;
                    }
                    if (!forwardCDSs.containsKey(i + j - end)) {
//	    # if the count crosses the threshold far from a gene
                        int k = 0;
//		# search for the drop off
                        while (fwdCov[chromNo - 1][i + k - end] > bg) {
                            if ((i + k) > chromLength) {
                                end = chromLength;
                            }
                            k++;
                        }
                        int start = i;
                        int flag = i + k - end;
                        if (dropdownsFwd.containsKey(flag)) {
                            dropdownsFwd.put(flag, dropdownsFwd.get(flag) + 1);
                        } else {
                            dropdownsFwd.put(flag, 1);
                        }
//                        newRegion = new NovelRegion(true, flag, j, trackID);
//		new_regs{fwd}{flag}++;
                        int possibleStop = flag + 1;
//		# and report the likely transcript
                        String site = "intergenic";
                        if (reverseCDSs.containsKey(start) || reverseCDSs.containsKey(possibleStop)) {
                            site = "cis-antisense";
                        }

                        int lengthOfNewRegion = flag - i;
                        if (dropdownsFwd.get(flag) == 1 && lengthOfNewRegion >= minLengthBoundary) {
//                            push(@{$new_regs{out}{$start}}, "$pos\t+\t$fwd\t$site") unless ($new_regs{fwd}{$flag} > 1);
                            newRegion = new NovelRegion(true, start, possibleStop, site, (possibleStop - start), getSubSeq(chrom, true, start, possibleStop), false, false, trackid, chromId);
                            System.out.println(newRegion.toString());
                            novelRegions.add(newRegion);
                        }
                    }
                }
// #############################################################################

                int rev_readstarts = reverse[chromNo - 1][i];
                if (rev_readstarts > bg) {
                    int j = 0;
                    int end = 0;
                    while (!reverseCDSs.containsKey(end + i - j)) {
                        if (j > 700) {
                            break;
                        }
                        if ((i - j) == 0) {
                            end = chromLength;
                        }
                        j++;
                    }
                    if (!reverseCDSs.containsKey(end + i - j)) {
                        if (rev_readstarts > bg) {
                            int k = 0;
                            while (revCov[chromNo - 1][end + i - k] > bg) {
                                if ((i - k) == 0) {
                                    end = chromLength;
                                }
                                k++;
                            }
                            int start = i + 1;
                            int flag = end + i - k;
//                      $new_regs{rev}{flag}++;
                            if (dropdownsRev.containsKey(flag)) {
                                dropdownsRev.put(flag, dropdownsRev.get(flag) + 1);
                            } else {
                                dropdownsRev.put(flag, 1);
                            }
                            int possibleStop = flag + 1;
                            String site = "intergenic";
                            if (forwardCDSs.containsKey(start) || forwardCDSs.containsKey(possibleStop)) {
                                site = "cis-antisense";
                            }
                            int lengthOfNewRegion = i - flag;
                            if (dropdownsRev.get(flag) == 1 && lengthOfNewRegion >= minLengthBoundary) { // unless ($new_regs{rev}{$flag} > 1) {
//                          push(@{$new_regs{out}{$start}}, "$pos\t-\t$rev\t$site");
                                String reversedSeq = new StringBuffer(getSubSeq(chrom, false, possibleStop, start)).reverse().toString();
                                String revComplement = getComplement(reversedSeq);
                                newRegion = new NovelRegion(false, start, possibleStop, site, (start - possibleStop), revComplement, false, false, trackid, chromId);
                                novelRegions.add(newRegion);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(
                "The genome is running out of length!");
    }

    public List<NovelRegion> getNovelRegions() {
        return novelRegions;
    }

    public void setNovelRegions(List<NovelRegion> novelRegions) {
        this.novelRegions = novelRegions;
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

    /**
     * Gets a DNA String and complement it. A to T, T to A, G to C and C to G.
     *
     * @param seq is DNA String.
     * @return the compliment of seq.
     */
    private String getComplement(String seq) {
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

    @Override
    public void update(Object args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NovelRegion> getResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}