
package de.cebitec.vamp.transcriptionAnalyses;

import de.cebitec.vamp.databackend.ResultTrackAnalysis;
import de.cebitec.vamp.databackend.dataObjects.PersistantFeature;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.transcriptionAnalyses.dataStructures.DetectedFeatures;
import de.cebitec.vamp.transcriptionAnalyses.dataStructures.TransStartUnannotated;
import de.cebitec.vamp.transcriptionAnalyses.dataStructures.TranscriptionStart;
import de.cebitec.vamp.util.GeneralUtils;
import de.cebitec.vamp.util.SequenceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container for all data belonging to a transcription start site detection
 * result.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class TssDetectionResult extends ResultTrackAnalysis<ParameterSetTSS> {
    
    private List<TranscriptionStart> results;
    private List<String> promotorRegions;
    
    /**
     * Container for all data belonging to a transcription start site detection
     * result.
     * @param results the results of the TSS detection
     * @param trackList the list of tracks, for which the TSS detection was carried out
     * @param combineTracks true, if the tracks are combined, false otherwise 
     */
    public TssDetectionResult(List<TranscriptionStart> results, Map<Integer, PersistantTrack> trackList, boolean combineTracks) {//, PersistantTrack currentTrack) {
        super(trackList, combineTracks);
        this.results = results;
    }

    /**
     * @return The results of the TSS detection
     */
    public List<TranscriptionStart> getResults() {
        return results;
    }

    /**
     * @return Promotor regions of the detected TSS 
     */
    public List<String> getPromotorRegions() {
        return promotorRegions;
    }

    /**
     * Sets the promotor regions of the detected TSS 
     * @param promotorRegions Promotor regions of the detected TSS 
     */
    public void setPromotorRegions(List<String> promotorRegions) {
        this.promotorRegions = promotorRegions;
    }
    
    /**
     * @return creates and returns the list of transcription start site descriptions 
     * for the columns of the table.
     */
    @Override
    public List<List<String>> dataColumnDescriptions() {
        List<List<String>> allSheetDescriptions = new ArrayList<>();
        List<String> dataColumnDescriptions = new ArrayList<>();
        
        dataColumnDescriptions.add("Position");
        dataColumnDescriptions.add("Track");
        dataColumnDescriptions.add("Strand");
        dataColumnDescriptions.add("No Read Starts");
        dataColumnDescriptions.add("Coverage Increase");
        dataColumnDescriptions.add("Coverage Increase %");
        dataColumnDescriptions.add("Correct Start Feature");
        dataColumnDescriptions.add("Correct Start Feature Start");
        dataColumnDescriptions.add("Correct Start Feature Stop");
        dataColumnDescriptions.add("Next Upstream Feature");
        dataColumnDescriptions.add("Next Upstream Feature Start");
        dataColumnDescriptions.add("Next Upstream Feature Stop");
        dataColumnDescriptions.add("Next Downstream Feature");
        dataColumnDescriptions.add("Next Downstream Feature Start");
        dataColumnDescriptions.add("Next Downstream Feature Stop");
        dataColumnDescriptions.add("Novel Transcript");
        dataColumnDescriptions.add("Transcript Stop");
        dataColumnDescriptions.add("70bp Upstream of Start");
        
        allSheetDescriptions.add(dataColumnDescriptions);

        //add tss detection statistic sheet header
        List<String> statisticColumnDescriptions = new ArrayList<>();
        statisticColumnDescriptions.add("Transcription Start Site Detection Parameter and Statistics Table");

        allSheetDescriptions.add(statisticColumnDescriptions);
        
        return allSheetDescriptions;
    }

    /**
     * @return creates and returns the list of transcription start rows belonging 
     * to the transcription start site table.
     */
    @Override
    public List<List<List<Object>>> dataToExcelExportList() {
        List<List<List<Object>>> tSSExport = new ArrayList<>();
        List<List<Object>> tSSResults = new ArrayList<>();
        
        for (int i = 0; i < results.size(); ++i) {      
            TranscriptionStart tss = results.get(i);
            List<Object> tssRow = new ArrayList<>();
            
            tssRow.add(tss.getPos());
            tssRow.add(this.getTrackEntry(tss.getTrackId(), true));
            tssRow.add(tss.isFwdStrand() ? SequenceUtils.STRAND_FWD_STRING : SequenceUtils.STRAND_REV_STRING);
            tssRow.add(tss.getReadStartsAtPos());
            tssRow.add(tss.getCoverageIncrease());
            tssRow.add(tss.getPercentIncrease());
            
            DetectedFeatures detFeatures = tss.getDetFeatures();
            this.addFeatureRows(detFeatures.getCorrectStartFeature(), tssRow);
            this.addFeatureRows(detFeatures.getUpstreamFeature(), tssRow);
            this.addFeatureRows(detFeatures.getDownstreamFeature(), tssRow);
            
            if (tss instanceof TransStartUnannotated) {
                TransStartUnannotated unannoStart = (TransStartUnannotated) tss;
                tssRow.add("yes");
                tssRow.add(unannoStart.getDetectedStop());
            } else {
                tssRow.add("-");
                tssRow.add("-");
            }
           
            tssRow.add(promotorRegions.get(i));
            
            tSSResults.add(tssRow);
        }
        
        tSSExport.add(tSSResults);
        
        
        //create statistics sheet
        ParameterSetTSS tssParameters = (ParameterSetTSS) this.getParameters();
        List<List<Object>> statisticsExportData = new ArrayList<>();

        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(
                "Transcription start site detection statistics for tracks:", 
                GeneralUtils.generateConcatenatedString(this.getTrackNameList(), 0)));
        
        statisticsExportData.add(ResultTrackAnalysis.createSingleElementTableRow("")); //placeholder between title and parameters

        statisticsExportData.add(ResultTrackAnalysis.createSingleElementTableRow("Transcription start site detection parameters:"));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Minimum number of read starts:", 
                tssParameters.getMinNoReadStarts()));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Minimum percent of coverage increase:", 
                tssParameters.getMinPercentIncrease()));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Maximum low coverage read start count:", 
                tssParameters.getMaxLowCovReadStarts()));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Minimum low coverage read starts:", 
                tssParameters.getMinLowCovReadStarts()));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Detect novel transcripts?", 
                tssParameters.isPerformUnannotatedTranscriptDet() ? "yes" : "no"));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow("Minimum transcript extension coverage:", 
                tssParameters.getMinTranscriptExtensionCov()));
        
        statisticsExportData.add(ResultTrackAnalysis.createSingleElementTableRow("")); //placeholder between parameters and statistics

        statisticsExportData.add(ResultTrackAnalysis.createSingleElementTableRow("Transcription start site statistics:"));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_TOTAL, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_TOTAL)));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_CORRECT, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_CORRECT)));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_UPSTREAM, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_UPSTREAM)));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_DOWNSTREAM, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_DOWNSTREAM)));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_FWD, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_FWD)));
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_REV, 
                getStatsMap().get(ResultPanelTranscriptionStart.TSS_REV)));
        
        int noUnannotatedTrans = this.getStatsMap().get(ResultPanelTranscriptionStart.TSS_NOVEL);
        String unannotatedTransValue = noUnannotatedTrans
                == ResultPanelTranscriptionStart.UNUSED_STATISTICS_VALUE ? "-" : String.valueOf(noUnannotatedTrans);
        statisticsExportData.add(ResultTrackAnalysis.createTwoElementTableRow(ResultPanelTranscriptionStart.TSS_NOVEL, 
                unannotatedTransValue));

        tSSExport.add(statisticsExportData);
        
        return tSSExport;
    }

    @Override
    public List<String> dataSheetNames() {
        List<String> sheetNames = new ArrayList<>();
        sheetNames.add("Transcription Analysis Table");
        sheetNames.add("Parameters and Statistics");
        return sheetNames;
    }
    
    /**
     * Adds the rows corresponding to a feature to the given tssRow (name, 
     * start, stop).
     * @param feature the feature to add. In case it is null the row receives 
     * "-" entries.
     * @param tssRow the row to which the data should be added
     */
    private void addFeatureRows(PersistantFeature feature, List<Object> tssRow) {
        if (feature != null) {
            tssRow.add(feature.toString());
            tssRow.add(feature.isFwdStrand() ? feature.getStart() : feature.getStop());
            tssRow.add(feature.isFwdStrand() ? feature.getStop() : feature.getStart());
        } else {
            tssRow.add("-");
            tssRow.add("-");
            tssRow.add("-");
        }
    }
    
}