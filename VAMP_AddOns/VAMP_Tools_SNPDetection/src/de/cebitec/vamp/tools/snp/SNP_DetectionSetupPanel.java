/*
 * SNP_DetectionSetupPanel.java
 *
 * Created on 23.02.2011, 16:08:09
 */

package de.cebitec.vamp.tools.snp;

import de.cebitec.centrallookup.CentralLookup;
import de.cebitec.vamp.databackend.connector.ProjectConnector;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.databackend.dataObjects.SnpData;
import de.cebitec.vamp.databackend.dataObjects.SnpI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.SwingWorker;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.RequestProcessor.Task;
import org.openide.util.TaskListener;

/**
 *
 * @author jwinneba, rhilker
 */
public class SNP_DetectionSetupPanel extends javax.swing.JPanel {

    public static final String PROP_SNPS_LOADED = "snpsLoaded";
    
    private static final long serialVersionUID = 1L;
    private ProjectConnector proCon;
    private List<Integer> trackIds;
    private SnpData snpData;
    private int referenceId;


    /** Creates new form SNP_DetectionSetupPanel */
    public SNP_DetectionSetupPanel(int referenceId) {
        initComponents();
        this.referenceId = referenceId;
        this.snpData = new SnpData(new ArrayList<SnpI>(), new HashMap<Integer, String>());
        this.proCon = ProjectConnector.getInstance();
    }
    
    private class SnpWorker extends SwingWorker<SnpData, Object> {

        private int percent;
        private int num;
        private ProgressHandle ph;

        SnpWorker(int percent, int num) {
            this.percent = percent;
            this.num = num;
            this.ph = ProgressHandleFactory.createHandle(NbBundle.getMessage(SnpWorker.class, "MSG_SNPWorker.progress.name"));
        }

        @Override
        protected SnpData doInBackground() {
            CentralLookup.getDefault().add(this);

            ph.start();
            List<SnpI> snps = proCon.findSNPs(percent, num, trackIds);
            snpData = new SnpData(snps, proCon.getRefGenomeConnector(referenceId).getAssociatedTrackNames());
            return snpData;
        }

        @Override
        protected void done() {
            CentralLookup.getDefault().remove(this);

            ph.finish();
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchButton = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        absNum = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        searchButton.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionSetupPanel.class, "SNP_DetectionSetupPanel.searchButton.text")); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jSpinner1.setValue(90);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionSetupPanel.class, "SNP_DetectionSetupPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionSetupPanel.class, "SNP_DetectionSetupPanel.jLabel3.text")); // NOI18N

        absNum.setFont(new java.awt.Font("Dialog", 1, 12));
        absNum.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        absNum.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionSetupPanel.class, "SNP_DetectionSetupPanel.absNum.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionSetupPanel.class, "SNP_DetectionSetupPanel.jTextArea1.text")); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(absNum)
                    .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(absNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String absN = absNum.getText();
        if(isValidNumer(absN)){
            final int num = Integer.parseInt(absN);
            final int percent = (Integer) jSpinner1.getValue();

            RequestProcessor rp = new RequestProcessor("SNP Threads", 2);
            final Task snpTask = rp.post(new SnpWorker(percent, num));
            snpTask.addTaskListener(new TaskListener() {

                @Override
                public void taskFinished(org.openide.util.Task task) {
                    firePropertyChange(PROP_SNPS_LOADED, null, snpData);
                    snpTask.removeTaskListener(this);
                    searchButton.setEnabled(true);
                }
            });
            searchButton.setEnabled(false);
        } else {
            NotifyDescriptor nd = new NotifyDescriptor.Message(NbBundle.getMessage(SNP_DetectionSetupPanel.class, "MSG_SNP_DetectionSetupPanel.error"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }

    }//GEN-LAST:event_searchButtonActionPerformed

    private boolean isValidNumer(String num){
        try{
            int tmp = Integer.parseInt(num);
            if(tmp > 0){
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex){
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField absNum;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the SnpData object containing all detected SNPs after a SNP detection
     * was carried out.
     */
    public SnpData getSnps() {
        return snpData;
    }
    
    /**
     * Set the track ids for which the snps should be detected.
     * @param trackIds the list of track ids defining the tracks to detect snps for.
     */
    public void setTrackIds(List<Integer> trackIds){
        this.trackIds = trackIds;
    }

    
}
