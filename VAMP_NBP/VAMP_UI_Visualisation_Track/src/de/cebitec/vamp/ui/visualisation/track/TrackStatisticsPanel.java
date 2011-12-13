package de.cebitec.vamp.ui.visualisation.track;

import de.cebitec.vamp.databackend.connector.ITrackConnector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ddoppmeier, rhilker
 */
public class TrackStatisticsPanel extends javax.swing.JPanel {

    private final static long serialVersionUID = 1239345;
    private ITrackConnector trackCon;

    /** Creates new form TrackNavigator */
    public TrackStatisticsPanel() {
        initComponents();
//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Do not use empty Constructor for this object! Standard constructor is only meant for GUI builder means");
    }

    public TrackStatisticsPanel(ITrackConnector trackCon) {
        this.trackCon = trackCon;
        this.initComponents();
        this.hideSeqPairLabels();
        this.computeStats();
    }

    /**
     * Sets a new track connector and recomputes the stats.
     * Use this method when switching to another track.
     * @param trackCon new track connector
     */
    public void setTrackConnector(ITrackConnector trackCon) {
        this.trackCon = trackCon;
        this.computeStats();
    }

    public void setTrackName(String name) {
        nameLabel.setText(name);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressbar = new javax.swing.JProgressBar();
        allReadsLabel = new javax.swing.JLabel();
        uniqueSequencesLabel = new javax.swing.JLabel();
        numUniqueMappingsAbsLabel = new javax.swing.JLabel();
        perfectMappingsLabel = new javax.swing.JLabel();
        bestMatchMappingsLabel = new javax.swing.JLabel();
        numMappingsLabel = new javax.swing.JLabel();
        uniqueSequencesAbsLabel = new javax.swing.JLabel();
        perfectMappingsAbsLabel = new javax.swing.JLabel();
        bestMatchMappingsAbsLabel = new javax.swing.JLabel();
        numMappingsAbsLabel = new javax.swing.JLabel();
        perfPercentageLabel = new javax.swing.JLabel();
        bmPercentageLabel = new javax.swing.JLabel();
        compPercentageLabel = new javax.swing.JLabel();
        perfectPercentage = new javax.swing.JLabel();
        bmPercentage = new javax.swing.JLabel();
        compPercentage = new javax.swing.JLabel();
        labelNameLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        numberReadsLabel = new javax.swing.JLabel();
        numberReadsAbsLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        numSeqPairsLabel = new javax.swing.JLabel();
        numSeqPairsAbsLabel = new javax.swing.JLabel();
        numPerfSeqPairsLabel = new javax.swing.JLabel();
        numPerfSeqPairsAbsLabel = new javax.swing.JLabel();
        numUniqSeqPairsLabel = new javax.swing.JLabel();
        numUniqSeqPairsAbsLabel = new javax.swing.JLabel();
        numPerfUniqSeqPairsLabel = new javax.swing.JLabel();
        numPerfUniqSeqPairsAbsLabel = new javax.swing.JLabel();
        singleMappings = new javax.swing.JLabel();
        singleMappingsLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Global Track Statistics"));
        setPreferredSize(new java.awt.Dimension(200, 400));

        allReadsLabel.setText("Singleton Mappings:");

        uniqueSequencesLabel.setText("Unique Seq:");

        numUniqueMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numUniqueMappingsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        perfectMappingsLabel.setText("Perfect:");

        bestMatchMappingsLabel.setText("Best-Match:");

        numMappingsLabel.setText("Mappings:");

        uniqueSequencesAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uniqueSequencesAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        perfectMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        perfectMappingsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bestMatchMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bestMatchMappingsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        numMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numMappingsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        perfPercentageLabel.setText("Perfect Coverage:");

        bmPercentageLabel.setText("Best Match Coverage:");

        compPercentageLabel.setText("Complete Coverage:");

        perfectPercentage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        perfectPercentage.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        bmPercentage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bmPercentage.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        compPercentage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        compPercentage.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelNameLabel.setText("Name:");

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        numberReadsLabel.setText("Number Reads:");

        numberReadsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numberReadsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sequence Pair Statistics"));

        numSeqPairsLabel.setText("Sequence Pairs:");

        numSeqPairsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numSeqPairsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        numPerfSeqPairsLabel.setText("Perfect Seq. Pairs:");

        numPerfSeqPairsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numPerfSeqPairsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        numUniqSeqPairsLabel.setText("Unique Seq. Pairs:");

        numUniqSeqPairsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numUniqSeqPairsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        numPerfUniqSeqPairsLabel.setText("Perf. Uniq. Seq. Pairs:");

        numPerfUniqSeqPairsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numPerfUniqSeqPairsAbsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        singleMappings.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        singleMappings.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        singleMappingsLabel.setText("Single Mappings:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(singleMappingsLabel)
                    .addComponent(numPerfSeqPairsLabel)
                    .addComponent(numSeqPairsLabel)
                    .addComponent(numUniqSeqPairsLabel)
                    .addComponent(numPerfUniqSeqPairsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numUniqSeqPairsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(numPerfUniqSeqPairsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(singleMappings, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(numSeqPairsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(numPerfSeqPairsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numSeqPairsLabel)
                    .addComponent(numSeqPairsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numPerfSeqPairsLabel)
                    .addComponent(numPerfSeqPairsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numUniqSeqPairsLabel)
                    .addComponent(numUniqSeqPairsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numPerfUniqSeqPairsLabel)
                    .addComponent(numPerfUniqSeqPairsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(singleMappings, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(singleMappingsLabel))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNameLabel)
                    .addComponent(numMappingsLabel)
                    .addComponent(uniqueSequencesLabel)
                    .addComponent(allReadsLabel)
                    .addComponent(numberReadsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberReadsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(uniqueSequencesAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(numUniqueMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(numMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(perfectMappingsLabel)
                            .addComponent(bestMatchMappingsLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bestMatchMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(perfectMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(compPercentageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bmPercentageLabel)
                            .addComponent(perfPercentageLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(perfectPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(bmPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(progressbar, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNameLabel)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numMappingsLabel)
                    .addComponent(numMappingsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allReadsLabel)
                    .addComponent(numUniqueMappingsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uniqueSequencesLabel)
                    .addComponent(uniqueSequencesAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberReadsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberReadsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bestMatchMappingsLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bestMatchMappingsAbsLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(perfectMappingsLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfectMappingsAbsLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfPercentageLabel)
                    .addComponent(perfectPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bmPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bmPercentageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compPercentageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allReadsLabel;
    private javax.swing.JLabel bestMatchMappingsAbsLabel;
    private javax.swing.JLabel bestMatchMappingsLabel;
    private javax.swing.JLabel bmPercentage;
    private javax.swing.JLabel bmPercentageLabel;
    private javax.swing.JLabel compPercentage;
    private javax.swing.JLabel compPercentageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelNameLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel numMappingsAbsLabel;
    private javax.swing.JLabel numMappingsLabel;
    private javax.swing.JLabel numPerfSeqPairsAbsLabel;
    private javax.swing.JLabel numPerfSeqPairsLabel;
    private javax.swing.JLabel numPerfUniqSeqPairsAbsLabel;
    private javax.swing.JLabel numPerfUniqSeqPairsLabel;
    private javax.swing.JLabel numSeqPairsAbsLabel;
    private javax.swing.JLabel numSeqPairsLabel;
    private javax.swing.JLabel numUniqSeqPairsAbsLabel;
    private javax.swing.JLabel numUniqSeqPairsLabel;
    private javax.swing.JLabel numUniqueMappingsAbsLabel;
    private javax.swing.JLabel numberReadsAbsLabel;
    private javax.swing.JLabel numberReadsLabel;
    private javax.swing.JLabel perfPercentageLabel;
    private javax.swing.JLabel perfectMappingsAbsLabel;
    private javax.swing.JLabel perfectMappingsLabel;
    private javax.swing.JLabel perfectPercentage;
    private javax.swing.JProgressBar progressbar;
    private javax.swing.JLabel singleMappings;
    private javax.swing.JLabel singleMappingsLabel;
    private javax.swing.JLabel uniqueSequencesAbsLabel;
    private javax.swing.JLabel uniqueSequencesLabel;
    // End of variables declaration//GEN-END:variables

    private void computeStats() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                //RunConnector runC = ProjectConnector.getInstance().getRunConnector(trackCon.getRunId(),trackCon.getTrackID());
                boolean recalculated = false;
                boolean recalcSeqPair = false;

                int numSeqPairs = trackCon.getNumOfSeqPairs();
                if (numSeqPairs == -1) {
//                        TrackStatisticsPanel.this.remove(numSeqPairsLabel);
//                        TrackStatisticsPanel.this.remove(numSeqPairsAbsLabel);
                    numSeqPairsLabel.setVisible(false);
                    numSeqPairsAbsLabel.setVisible(false);
                } else {
                    if (numSeqPairs == -2) {
                        numSeqPairs = trackCon.getNumOfSeqPairsCalculate();
                        recalcSeqPair = true;
                    }
                    numSeqPairsLabel.setVisible(true);
                    numSeqPairsAbsLabel.setVisible(true);
                    numSeqPairsAbsLabel.setText(String.valueOf(numSeqPairs));
                }

                int numPerfectSeqPairs = trackCon.getNumOfPerfectSeqPairs();
                if (numPerfectSeqPairs == -1) {
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsLabel);
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsAbsLabel);
                    numPerfSeqPairsLabel.setVisible(false);
                    numPerfSeqPairsAbsLabel.setVisible(false);
                } else {
                    if (numPerfectSeqPairs == -2) {
                        numPerfectSeqPairs = trackCon.getNumOfPerfectSeqPairsCalculate();
                        recalcSeqPair = true;
                    }
                    numPerfSeqPairsLabel.setVisible(true);
                    numPerfSeqPairsAbsLabel.setVisible(true);
                    numPerfSeqPairsAbsLabel.setText(String.valueOf(numPerfectSeqPairs));
                }

                int numUniqueSeqPairs = trackCon.getNumOfUniqueSeqPairs();
                if (numUniqueSeqPairs == -1) {
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsLabel);
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsAbsLabel);
                    numUniqSeqPairsLabel.setVisible(false);
                    numUniqSeqPairsAbsLabel.setVisible(false);
                } else {
                    if (numUniqueSeqPairs == -2) {
                        numUniqueSeqPairs = trackCon.getNumOfUniqueSeqPairsCalculate();
                        recalcSeqPair = true;
                    }
                    numUniqSeqPairsLabel.setVisible(true);
                    numUniqSeqPairsAbsLabel.setVisible(true);
                    numUniqSeqPairsAbsLabel.setText(String.valueOf(numUniqueSeqPairs));
                }

                int numUniquePerfectSeqPairs = trackCon.getNumOfUniquePerfectSeqPairs();
                if (numUniquePerfectSeqPairs == -1) {
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsLabel);
//                        TrackStatisticsPanel.this.remove(numPerfectSeqPairsAbsLabel);
                    numPerfUniqSeqPairsLabel.setVisible(false);
                    numPerfUniqSeqPairsAbsLabel.setVisible(false);
                } else {
                    if (numUniquePerfectSeqPairs == -2) {
                        numUniquePerfectSeqPairs = trackCon.getNumOfUniquePerfectSeqPairsCalculate();
                        recalcSeqPair = true;
                    }
                    numPerfUniqSeqPairsLabel.setVisible(true);
                    numPerfUniqSeqPairsAbsLabel.setVisible(true);
                    numPerfUniqSeqPairsAbsLabel.setText(String.valueOf(numUniquePerfectSeqPairs));
                }

                int numSingleMappings = trackCon.getNumOfSingleMappings();
                if (numSingleMappings == -1) {
//                        TrackStatisticsPanel.this.remove(numPerfUniqSeqPairsLabel);
//                        TrackStatisticsPanel.this.remove(numPerfUniqSeqPairsAbsLabel);
                    singleMappingsLabel.setVisible(false);
                    singleMappings.setVisible(false);
                } else {
                    if (numSingleMappings == -2) {
                        numSingleMappings = trackCon.getNumOfSingleMappingsCalculate();
                        recalcSeqPair = true;
                    }
                    singleMappingsLabel.setVisible(true);
                    singleMappings.setVisible(true);
                    singleMappings.setText(String.valueOf(numSingleMappings));
                }

                int numOfMappings = trackCon.getNumOfMappings();
                if (numOfMappings == -1) {
                    numOfMappings = trackCon.getNumOfMappingsCalculate();
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Updating statistic information");
                    recalculated = true;
                }
                numMappingsAbsLabel.setText(String.valueOf(numOfMappings));

                int numUniqueMappings = trackCon.getNumOfUniqueMappings();
                if (numUniqueMappings == -1) {
                    numUniqueMappings = trackCon.getNumOfUniqueMappingsCalculate();
                    recalculated = true;
                }
                numUniqueMappingsAbsLabel.setText(String.valueOf(numUniqueMappings));

                int numUniqueSequences = trackCon.getNumOfUniqueSequences();
                if (numUniqueSequences == -1) {
                    numUniqueSequences = trackCon.getNumOfUniqueSequencesCalculate();
                    recalculated = true;
                }
                uniqueSequencesAbsLabel.setText(String.valueOf(numUniqueSequences));

                numberReadsAbsLabel.setVisible(false);
                numberReadsLabel.setVisible(false);
                int numReads = trackCon.getNumOfReads();
                if (numReads == -1) {
                    numberReadsAbsLabel.setText("Not available"); 
                } else {
                    numberReadsAbsLabel.setText(String.valueOf(numReads));
                }

                int numOfPerfectMappings = trackCon.getNumOfPerfectUniqueMappings();
                if (numOfPerfectMappings == -1) {
                    numOfPerfectMappings = trackCon.getNumOfPerfectUniqueMappingsCalculate();
                    recalculated = true;
                }
                perfectMappingsAbsLabel.setText(String.valueOf(numOfPerfectMappings));

                int numOfBestMatchMappings = trackCon.getNumOfUniqueBmMappings();
                if (numOfBestMatchMappings == -1) {
                    numOfBestMatchMappings = trackCon.getNumOfUniqueBmMappingsCalculate();
                    recalculated = true;
                }
                bestMatchMappingsAbsLabel.setText(String.valueOf(numOfBestMatchMappings));

                double percentagePerfectCovered = trackCon.getPercentRefGenPerfectCovered();
                if (percentagePerfectCovered == -1) {
                    percentagePerfectCovered = trackCon.getPercentRefGenPerfectCoveredCalculate();
                    recalculated = true;
                }

                double percentageBMCovered = trackCon.getPercentRefGenBmCovered();
                if (percentageBMCovered == -1) {
                    percentageBMCovered = trackCon.getPercentRefGenBmCoveredCalculate();
                    recalculated = true;
                }

                double percentageNErrorCovered = trackCon.getPercentRefGenNErrorCovered();
                if (percentageNErrorCovered == -1) {
                    percentageNErrorCovered = trackCon.getPercentRefGenNErrorCoveredCalculate();
                    recalculated = true;
                }


                if (recalculated) {
                    trackCon.setStatistics(numOfMappings, numUniqueMappings, numUniqueSequences,
                            numOfPerfectMappings, numOfBestMatchMappings, percentagePerfectCovered,
                            percentageBMCovered, percentageNErrorCovered, numReads);
                }
                if (recalcSeqPair) {
                    trackCon.addSeqPairStatistics(numSeqPairs, numPerfectSeqPairs, numUniqueSeqPairs,
                            numUniquePerfectSeqPairs, numSingleMappings);
                }

                String perfectCov = String.format("%.2f%%", percentagePerfectCovered);
                String bmCov = String.format("%.2f%%", percentageBMCovered);
                String nErrorCov = String.format("%.2f%%", percentageNErrorCovered);

                perfectPercentage.setText(perfectCov);
                bmPercentage.setText(bmCov);
                compPercentage.setText(nErrorCov);
                statsFinished();
            }
        }) {
        };
        progressbar.setIndeterminate(true);

        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    private void statsFinished() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                progressbar.setIndeterminate(false);
                progressbar.setVisible(false);
            }
        });
    }

    public void close() {
        trackCon = null;
    }

    /**
     * Used for hiding all sequence pair associated labels until they are really
     * needed.
     */
    private void hideSeqPairLabels() {
        this.numberReadsLabel.setVisible(false);
        this.numberReadsAbsLabel.setVisible(false);

        this.numSeqPairsLabel.setVisible(false);
        this.numSeqPairsAbsLabel.setVisible(false);
        this.numPerfSeqPairsLabel.setVisible(false);
        this.numPerfSeqPairsAbsLabel.setVisible(false);
        this.numUniqSeqPairsLabel.setVisible(false);
        this.numUniqSeqPairsAbsLabel.setVisible(false);
        this.numPerfUniqSeqPairsLabel.setVisible(false);
        this.numPerfUniqSeqPairsAbsLabel.setVisible(false);
    }
}
