/*
 * SNP_DetectionResultPanel.java
 *
 * Created on 24-Feb-2011, 09:51:49
 */

package de.cebitec.vamp.tools.snp;

import de.cebitec.vamp.objects.Snp;
import de.cebitec.vamp.view.dataVisualisation.BoundsInfoManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joern
 */
public class SNP_DetectionResultPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private BoundsInfoManager bim;
    private List<Snp> snps;

    /** Creates new form SNP_DetectionResultPanel */
    public SNP_DetectionResultPanel() {
        initComponents();
        snps = new ArrayList<Snp>();
        DefaultListSelectionModel model = (DefaultListSelectionModel) jTable1.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                showSnpPosition();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        exportButton = new javax.swing.JButton();

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Position", "Base", "Count", "%", "% Variation  at Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.jTable1.columnModel.title4")); // NOI18N

        exportButton.setText(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.exportButton.text")); // NOI18N
        exportButton.setActionCommand(org.openide.util.NbBundle.getMessage(SNP_DetectionResultPanel.class, "SNP_DetectionResultPanel.exportButton.actionCommand")); // NOI18N
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addComponent(exportButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        NewExportDialog ed = new NewExportDialog(null, true);
        ed.setSnps(snps);
        ed.setVisible(true);
}//GEN-LAST:event_exportButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportButton;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void addSNPs(List<Snp> snps) {
        this.snps = snps;
        for (Snp snp : snps) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object[] rowData = new Object[5];
            rowData[0] = snp.getPosition();
            rowData[1] = snp.getBase();
            rowData[2] = snp.getCount();
            rowData[3] = snp.getPercentage();
            rowData[4] = snp.getVariationPercentag();
            model.addRow(rowData);
        }
    }

    public void setBoundsInfoManager(BoundsInfoManager boundsInformationManager) {
        this.bim = boundsInformationManager;
    }

    private void showSnpPosition() {
        DefaultListSelectionModel model = (DefaultListSelectionModel) jTable1.getSelectionModel();
        int selectedView = model.getLeadSelectionIndex();
        int selectedModel = jTable1.convertRowIndexToModel(selectedView);
        int position = (Integer) jTable1.getModel().getValueAt(selectedModel, 0);
        bim.navigatorBarUpdated(position);
    }

}
