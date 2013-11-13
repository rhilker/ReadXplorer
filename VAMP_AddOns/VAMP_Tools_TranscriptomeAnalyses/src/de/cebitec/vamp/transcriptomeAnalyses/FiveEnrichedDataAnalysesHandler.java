/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cebitec.vamp.transcriptomeAnalyses;

import de.cebitec.readxplorer.transcriptomeAnalyses.enums.AnalysisStatus;
import de.cebitec.vamp.databackend.ParametersReadClasses;
import de.cebitec.vamp.databackend.connector.TrackConnector;
import de.cebitec.vamp.databackend.dataObjects.DataVisualisationI;
import de.cebitec.vamp.databackend.dataObjects.PersistantFeature;
import de.cebitec.vamp.databackend.dataObjects.PersistantMapping;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.util.GeneralUtils;
import de.cebitec.vamp.util.Observable;
import de.cebitec.vamp.util.Pair;
import de.cebitec.vamp.util.Properties;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.ReferenceViewer;
import de.cebitec.vamp.view.dialogMenus.SaveTrackConnectorFetcherForGUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jritter
 */
public class FiveEnrichedDataAnalysesHandler extends Thread implements Observable, DataVisualisationI {

    private TrackConnector trackConnector;
    private PersistantTrack selectedTrack;
    private List<PersistantMapping> mappings;
    private Integer refGenomeID;
    private double fraction;
    private List<de.cebitec.vamp.util.Observer> observer = new ArrayList<>();
    private int[] region2Exclude;
    protected HashMap<Integer, List<Integer>> forwardCDSs, reverseCDSs;
    private Statistics stats;
    private double backgroundCutoff;
    private ParameterSetFiveEnrichedAnalyses parameters;
    private GenomeFeatureParser featureParser;
    private TssDetection tssDetection;
    private OperonDetection operonDetection;
    private ResultPanelTranscriptionStart transcriptionStartResultPanel;
    private final ReferenceViewer refViewer;
    private TranscriptomeAnalysesTopComponentTopComponent transcAnalysesTopComp;
    private HashMap<Integer, PersistantTrack> trackMap;
    /**
     * Key: featureID , Value: PersistantFeature
     */
    private HashMap<Integer, PersistantFeature> allRegionsInHash;

    public FiveEnrichedDataAnalysesHandler(PersistantTrack selectedTrack, Integer refGenomeID, ParameterSetFiveEnrichedAnalyses parameterset, ReferenceViewer refViewer, TranscriptomeAnalysesTopComponentTopComponent transcAnalysesTopComp, HashMap<Integer, PersistantTrack> trackMap) {

        this.selectedTrack = selectedTrack;
        this.refGenomeID = refGenomeID;
        this.fraction = parameterset.getFraction();
        this.parameters = parameterset;
        this.refViewer = refViewer;
        this.transcAnalysesTopComp = transcAnalysesTopComp;
        this.trackMap = trackMap;
    }

    private void startAnalysis() {

        TrackConnector connector = null;
        try {
            connector = (new SaveTrackConnectorFetcherForGUI()).getTrackConnector(selectedTrack);
        } catch (SaveTrackConnectorFetcherForGUI.UserCanceledTrackPathUpdateException ex) {
            JOptionPane.showMessageDialog(null, "You did not complete the track path selection. The track panel cannot be opened.", "Error resolving path to track", JOptionPane.INFORMATION_MESSAGE);
        }
        this.featureParser = new GenomeFeatureParser(connector);
        this.featureParser.parseFeatureInformation(this.featureParser.getGenomeFeatures());

        // Initiation of important structures
        this.region2Exclude = this.featureParser.getRegion2Exclude();
        this.forwardCDSs = this.featureParser.getForwardCDSs();
        this.reverseCDSs = this.featureParser.getReverseCDSs();
        this.allRegionsInHash = this.featureParser.getAllRegionsInHash();


        // geting Mappings and calculate statistics on mappings.
        try {
            this.trackConnector = (new SaveTrackConnectorFetcherForGUI()).getTrackConnector(this.selectedTrack);
            this.stats = new Statistics(featureParser.getRefSeqLength(), this.fraction, this.forwardCDSs, this.reverseCDSs, this.allRegionsInHash, this.region2Exclude);
            de.cebitec.vamp.databackend.AnalysesHandler handler = new de.cebitec.vamp.databackend.AnalysesHandler(trackConnector, this, "Collecting coverage data of track number "
                    + this.selectedTrack.getId(), new ParametersReadClasses(true, false, false, false)); // TODO: ParameterReadClasses noch in den Wizard einbauen und die parameter hier mit übergeben!
            handler.setMappingsNeeded(true);
            handler.setDesiredData(Properties.REDUCED_MAPPINGS);
            handler.registerObserver(this.stats);
            handler.startAnalysis();
        } catch (SaveTrackConnectorFetcherForGUI.UserCanceledTrackPathUpdateException ex) {
            JOptionPane.showMessageDialog(null, "The path of one of the selected tracks could not be resolved. The analysis will be canceled now.", "Error resolving path to track", JOptionPane.INFORMATION_MESSAGE);
            notifyObservers(AnalysisStatus.ERROR);
            this.interrupt();
        }

        // Next Steps in showData method!
    }

    @Override
    public void registerObserver(de.cebitec.vamp.util.Observer observer) {
        this.observer.add(observer);
    }

    @Override
    public void removeObserver(de.cebitec.vamp.util.Observer observer) {
        this.observer.remove(observer);
        if (this.observer.isEmpty()) {
            this.interrupt();
        }
    }


    @Override
    public void run() {
        notifyObservers(AnalysisStatus.RUNNING);
        startAnalysis();
    }

    @Override
    public void notifyObservers(Object data) {
        List<de.cebitec.vamp.util.Observer> tmpObserver = new ArrayList<>(observer);
        for (Iterator<de.cebitec.vamp.util.Observer> it = tmpObserver.iterator(); it.hasNext();) {
            de.cebitec.vamp.util.Observer currentObserver = it.next();
            currentObserver.update(data);
        }
    }

    @Override
    public void showData(Object data) {
        Pair<Integer, String> dataTypePair = (Pair<Integer, String>) data;
        final int trackId = dataTypePair.getFirst();
        final String dataType = dataTypePair.getSecond();

        this.mappings = this.stats.getMappings();
        this.stats.parseMappings(this.mappings);
        this.backgroundCutoff = this.stats.calculateBackgroundCutoff(this.parameters.getFraction(), this.featureParser.getRefSeqLength());
        this.stats.setBg(this.backgroundCutoff);

        this.stats.initMappingsStatistics();
        if (parameters.isPerformTSSAnalysis()) {
            this.tssDetection = new TssDetection(this.trackConnector.getRefGenome().getSequence(), trackId);
            this.tssDetection.runningTSSDetection(this.featureParser.getRefSeqLength(), this.forwardCDSs, this.reverseCDSs,
                    this.allRegionsInHash, this.stats, this.parameters);
            
            String trackNames;
            if (transcriptionStartResultPanel == null) {
                transcriptionStartResultPanel = new ResultPanelTranscriptionStart();
                transcriptionStartResultPanel.setReferenceViewer(refViewer);
            }

            TSSDetectionResults tssResult = new TSSDetectionResults(this.stats, this.tssDetection.getResults(), getTrackMap());
            tssResult.setParameters(this.parameters);
            transcriptionStartResultPanel.addResult(tssResult);

            trackNames = GeneralUtils.generateConcatenatedString(tssResult.getTrackNameList(), 120);
            String panelName = "Detected TSSs for " + trackNames + " (" + transcriptionStartResultPanel.getResultSize() + " hits)";
            transcAnalysesTopComp.openAnalysisTab(panelName, transcriptionStartResultPanel);
        }

        if (parameters.isPerformAntisenseAnalysis()) {
//            this.tssDetection = new TssDetection(this.trackConnector.getRefGenome().getSequence(), trackId);
//            this.tssDetection.runningTSSDetection(this.featureParser.getRefSeqLength(), this.forwardCDSs, this.reverseCDSs,
//                    this.allRegionsInHash, this.stats, this.parameters);
//            
//            String trackNames;
//            if (transcriptionStartResultPanel == null) {
//                transcriptionStartResultPanel = new ResultPanelTranscriptionStart();
//                transcriptionStartResultPanel.setReferenceViewer(refViewer);
//            }
//
//            TSSDetectionResults tssResult = new TSSDetectionResults(this.stats, this.tssDetection.getResults(), getTrackMap());
//            tssResult.setParameters(this.parameters);
//            transcriptionStartResultPanel.addResult(tssResult);
//
//            trackNames = GeneralUtils.generateConcatenatedString(tssResult.getTrackNameList(), 120);
//            String panelName = "Detected TSSs for " + trackNames + " (" + transcriptionStartResultPanel.getResultSize() + " hits)";
//            transcAnalysesTopComp.openAnalysisTab(panelName, transcriptionStartResultPanel);
            
        }
        
        if (parameters.isPerformUnAnnotatedRegionsAnalysis()) {
//            this.tssDetection = new TssDetection(this.trackConnector.getRefGenome().getSequence(), trackId);
//            this.tssDetection.runningTSSDetection(this.featureParser.getRefSeqLength(), this.forwardCDSs, this.reverseCDSs,
//                    this.allRegionsInHash, this.stats, this.parameters);
//            
//            String trackNames;
//            if (transcriptionStartResultPanel == null) {
//                transcriptionStartResultPanel = new ResultPanelTranscriptionStart();
//                transcriptionStartResultPanel.setReferenceViewer(refViewer);
//            }
//
//            TSSDetectionResults tssResult = new TSSDetectionResults(this.stats, this.tssDetection.getResults(), getTrackMap());
//            tssResult.setParameters(this.parameters);
//            transcriptionStartResultPanel.addResult(tssResult);
//
//            trackNames = GeneralUtils.generateConcatenatedString(tssResult.getTrackNameList(), 120);
//            String panelName = "Detected TSSs for " + trackNames + " (" + transcriptionStartResultPanel.getResultSize() + " hits)";
//            transcAnalysesTopComp.openAnalysisTab(panelName, transcriptionStartResultPanel);
        }
        notifyObservers(AnalysisStatus.FINISHED);
    }

    public HashMap<Integer, List<Integer>> getForwardCDSs() {
        return forwardCDSs;
    }

    public HashMap<Integer, List<Integer>> getReverseCDSs() {
        return reverseCDSs;
    }

    public int[] getRegion2Exclude() {
        return region2Exclude;
    }

    public TssDetection getTssDetection() {
        return tssDetection;
    }

    public OperonDetection getOperonDetection() {
        return operonDetection;
    }

    public HashMap<Integer, PersistantTrack> getTrackMap() {
        return trackMap;
    }
}
