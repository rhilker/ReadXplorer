package vamp.view.dataVisualisation.trackViewer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import vamp.databackend.connector.ProjectConnector;
import vamp.databackend.connector.RunConnector;
import vamp.databackend.connector.TrackConnector;

/**
 *
 * @author ddoppmeier
 */
public class TrackStatisticsPanel extends javax.swing.JPanel {

    private final static long serialVersionUID = 1239345;

    private TrackConnector trackCon;

    /** Creates new form TrackNavigator */
    public TrackStatisticsPanel() {
        initComponents();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Do not use empty Constructor for this object! Standard constructor is only meant for GUI builder means");
    }

    public TrackStatisticsPanel(TrackConnector trackCon){
        this.trackCon = trackCon;
        initComponents();
        computeStats();
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
        uniqueReadsLabel = new javax.swing.JLabel();
        mappedReadsLabel = new javax.swing.JLabel();
        nonMappedReads = new javax.swing.JLabel();
        allReadsAbsLabel = new javax.swing.JLabel();
        perfectMappingsLabel = new javax.swing.JLabel();
        bestMatchMappingsLabel = new javax.swing.JLabel();
        nErrorMappingsLabel = new javax.swing.JLabel();
        uniqueReadsAbsLabel = new javax.swing.JLabel();
        mappedReadsAbsLabel = new javax.swing.JLabel();
        nonMappedReadsAbsLabel = new javax.swing.JLabel();
        perfectMappingsAbsLabel = new javax.swing.JLabel();
        bestMatchMappingsAbsLabel = new javax.swing.JLabel();
        nErrorMappingsAbsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        perfectPercentage = new javax.swing.JLabel();
        bmPercentage = new javax.swing.JLabel();
        nerrorPercentage = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(171, 200));

        allReadsLabel.setText("Reads:");

        uniqueReadsLabel.setText("Unique:");

        mappedReadsLabel.setText("Mapped:");

        nonMappedReads.setText("Non-mapped:");

        allReadsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        perfectMappingsLabel.setText("Perfect:");

        bestMatchMappingsLabel.setText("Best-Match:");

        nErrorMappingsLabel.setText("Mappings:");

        uniqueReadsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        mappedReadsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        nonMappedReadsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        perfectMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        bestMatchMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        nErrorMappingsAbsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel1.setText("cov. perfect:");

        jLabel2.setText("cov. bm:");

        jLabel3.setText("cov. compl.:");

        perfectPercentage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        bmPercentage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        nerrorPercentage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(progressbar, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(perfectMappingsLabel)
                            .addComponent(allReadsLabel)
                            .addComponent(mappedReadsLabel)
                            .addComponent(nonMappedReads)
                            .addComponent(uniqueReadsLabel)
                            .addComponent(nErrorMappingsLabel)
                            .addComponent(bestMatchMappingsLabel)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nerrorPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(bmPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(perfectPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(perfectMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(allReadsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(uniqueReadsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(mappedReadsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(nonMappedReadsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(nErrorMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(bestMatchMappingsAbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allReadsLabel)
                    .addComponent(allReadsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uniqueReadsLabel)
                    .addComponent(uniqueReadsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mappedReadsLabel)
                    .addComponent(mappedReadsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nonMappedReads)
                    .addComponent(nonMappedReadsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nErrorMappingsLabel)
                    .addComponent(nErrorMappingsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bestMatchMappingsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bestMatchMappingsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(perfectMappingsLabel)
                    .addComponent(perfectMappingsAbsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(perfectPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bmPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nerrorPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allReadsAbsLabel;
    private javax.swing.JLabel allReadsLabel;
    private javax.swing.JLabel bestMatchMappingsAbsLabel;
    private javax.swing.JLabel bestMatchMappingsLabel;
    private javax.swing.JLabel bmPercentage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel mappedReadsAbsLabel;
    private javax.swing.JLabel mappedReadsLabel;
    private javax.swing.JLabel nErrorMappingsAbsLabel;
    private javax.swing.JLabel nErrorMappingsLabel;
    private javax.swing.JLabel nerrorPercentage;
    private javax.swing.JLabel nonMappedReads;
    private javax.swing.JLabel nonMappedReadsAbsLabel;
    private javax.swing.JLabel perfectMappingsAbsLabel;
    private javax.swing.JLabel perfectMappingsLabel;
    private javax.swing.JLabel perfectPercentage;
    private javax.swing.JProgressBar progressbar;
    private javax.swing.JLabel uniqueReadsAbsLabel;
    private javax.swing.JLabel uniqueReadsLabel;
    // End of variables declaration//GEN-END:variables


        private void computeStats(){
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                RunConnector runC = ProjectConnector.getInstance().getRunConnector(trackCon.getRunId(),trackCon.getTrackID());

                int numUniqueReads = runC.getNumberOfUniqueSequences();
                if(numUniqueReads == 0 ){
                numUniqueReads = runC.getNumberOfUniqueSequencesCalculate();

                }
                int numOfReads = runC.getNumberOfReads();
                if(numOfReads == 0 ){
                numOfReads = runC.getNumberOfReadsCalculate();
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Updating static information");
                runC.updateTableStatics(numOfReads, numUniqueReads);
                }


                int numOfMappedUniqueReads = trackCon.getNumOfMappedSequences();

                if(numOfMappedUniqueReads == 0){
                    numOfMappedUniqueReads = trackCon.getNumOfMappedSequencesCalculate();
                }
                int numOfNonMappedUniqueReads = numUniqueReads - numOfMappedUniqueReads;

                int numOfMappings = trackCon.getNumOfUniqueMappings();
                if(numOfMappings == 0){
                   numOfMappings = trackCon.getNumOfUniqueMappingsCalculate();
                }
                int numOfPerfectMappings = trackCon.getNumOfPerfectUniqueMappings();
                if(numOfPerfectMappings == 0){
                    numOfPerfectMappings = trackCon.getNumOfPerfectUniqueMappingsCalculate();
                }

                int numOfBestMatchMappings = trackCon.getNumOfUniqueBmMappings();
                if(numOfBestMatchMappings == 0){
                    numOfBestMatchMappings = trackCon.getNumOfUniqueBmMappingsCalculate();
                }

                double percentagePerfectCovered = trackCon.getPercentRefGenPerfectCovered();
                if(percentagePerfectCovered ==0){
                    percentagePerfectCovered = trackCon.getPercentRefGenPerfectCoveredCalculate();
                }
                double percentageBMCovered = trackCon.getPercentRefGenBmCovered();
                if(percentageBMCovered == 0){
                    percentageBMCovered = trackCon.getPercentRefGenBmCoveredCalculate();
                }
                double percentageNErrorCovered = trackCon.getPercentRefGenNErrorCovered();
                     if(percentageNErrorCovered ==0){
                  percentageNErrorCovered = trackCon.getPercentRefGenNErrorCoveredCalculate();
                  trackCon.setStatics(numOfMappings, numOfPerfectMappings, numOfBestMatchMappings, numOfMappedUniqueReads, percentagePerfectCovered, percentageBMCovered, percentageNErrorCovered,numOfReads, numUniqueReads);
                }

                String perfectCov = String.format("%.2f%%", percentagePerfectCovered);
                String bmCov = String.format("%.2f%%", percentageBMCovered);
                String nErrorCov = String.format("%.2f%%", percentageNErrorCovered);



                allReadsAbsLabel.setText(String.valueOf(numOfReads));
                uniqueReadsAbsLabel.setText(String.valueOf(numUniqueReads));
                mappedReadsAbsLabel.setText(String.valueOf(numOfMappedUniqueReads));
                nonMappedReadsAbsLabel.setText(String.valueOf(numOfNonMappedUniqueReads));

                nErrorMappingsAbsLabel.setText(String.valueOf(numOfMappings));
                perfectMappingsAbsLabel.setText(String.valueOf(numOfPerfectMappings));
                bestMatchMappingsAbsLabel.setText(String.valueOf(numOfBestMatchMappings));

                perfectPercentage.setText(perfectCov);
                bmPercentage.setText(bmCov);
                nerrorPercentage.setText(nErrorCov);
                statsFinished();


            }
        }){
        };
        progressbar.setIndeterminate(true);

        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    private void statsFinished(){
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


}
