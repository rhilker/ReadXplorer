package de.cebitec.readXplorer.coverageAnalysis;

import de.cebitec.vamp.databackend.dataObjects.PersistantResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for all data belonging to a coverage analysis result. Also converts
 * a all data into the format readable for the ExcelExporter. Generates all
 * three, the sheet names, headers and data to write.
 *
 * @author Tobias Zimmermann, Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public class CoverageIntervalContainer extends PersistantResult {
    private static final long serialVersionUID = 1L;

    private List<CoverageInterval> coverageInfo;
    private List<CoverageInterval> coverageInfoRev;

   /**
     * Container for all data belonging to a coverage analysis result. Also
     * converts a all data into the format readable for the ExcelExporter.
     * Generates all three, the sheet names, headers and data to write.
     */
    public CoverageIntervalContainer() {
        this.coverageInfo = new ArrayList<>();
        this.coverageInfoRev = new ArrayList<>();
    }

    /**
     * Container for all data belonging to a coverage analysis result. Also
     * converts a all data into the format readable for the ExcelExporter.
     * Generates all three, the sheet names, headers and data to write.
     * @param coverageIntervals set the coverage intervals of this result
     * directly, if only one array for both strands needs to be added
     */
    public CoverageIntervalContainer(List<CoverageInterval> coverageIntervals) {
        this.coverageInfo = coverageIntervals;
        this.coverageInfoRev = new ArrayList<>();
    }

    /**
     * Container for all data belonging to a coverage analysis result. Also
     * converts a all data into the format readable for the ExcelExporter.
     * Generates all three, the sheet names, headers and data to write.
     * @param coverageInfoFwd set the forward coverage intervals of this result
     * directly
     * @param coverageInfoRev set the reverse coverage intervals of this result
     * directly
     */
    public CoverageIntervalContainer(List<CoverageInterval> coverageInfoFwd, List<CoverageInterval> coverageInfoRev) {
        this(coverageInfoFwd);
        this.coverageInfoRev = coverageInfoRev;
    }

    /**
     * @return The size of both coverage interval arrays = result size
     */
    public int getResultSize(){
        return this.coverageInfo.size() + this.coverageInfoRev.size();
    }
    
    /**
     * @return the fwd or complete (if coverage of both strands was summed) 
     * coverage interval list
     */
    public List<CoverageInterval> getCoverageIntervals() {
        return coverageInfo;
    }

    /**
     * @param coverageIntervals the fwd or complete (if coverage of both strands
     * was summed) coverage interval list to set
     */
    public void setIntervalsSumOrFwd(List<CoverageInterval> coverageIntervals) {
        this.coverageInfo = coverageIntervals;
    }

    /**
     * @return the rev coverage interval list
     */
    public List<CoverageInterval> getCoverageIntervalsRev() {
        return coverageInfoRev;
    }

    /**
     * @param coverageIntervalsRev the rev coverage interval list to set
     */
    public void setIntervalsRev(List<CoverageInterval> coverageIntervalsRev) {
        this.coverageInfoRev = coverageIntervalsRev;
    }
}