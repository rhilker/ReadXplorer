/*
 * GeneStartsResultPanel.java
 *
 * Created on 27.01.2012, 14:31:03
 */
package de.cebitec.vamp.transcriptionAnalyses;

import de.cebitec.vamp.databackend.dataObjects.PersistantFeature;
import de.cebitec.vamp.util.SequenceUtils;
import de.cebitec.vamp.view.dataVisualisation.BoundsInfoManager;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author -Rolf Hilker-
 */
public class GeneStartsResultPanel extends javax.swing.JPanel {

    private BoundsInfoManager bim;
    private List<GeneStart> geneStarts;
    
    /** Creates new form GeneStartsResultPanel */
    public GeneStartsResultPanel() {
        initComponents();
        
        DefaultListSelectionModel model = (DefaultListSelectionModel) this.geneStartTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                showGeneStartPosition();
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
        geneStartTable = new javax.swing.JTable();
        exportButton = new javax.swing.JButton();

        geneStartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Position", "Strand", "Initial Coverage", "Start Coverage", "Coverage Increase", "Coverage Increase %", "Correct Feature", "Next Upstream Feature", "Next Downstream Feature"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(geneStartTable);
        geneStartTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title0")); // NOI18N
        geneStartTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title1")); // NOI18N
        geneStartTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title2")); // NOI18N
        geneStartTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title3")); // NOI18N
        geneStartTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title4")); // NOI18N
        geneStartTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title5")); // NOI18N
        geneStartTable.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title6")); // NOI18N
        geneStartTable.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title7")); // NOI18N
        geneStartTable.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.geneStartTable.columnModel.title8")); // NOI18N

        exportButton.setText(org.openide.util.NbBundle.getMessage(GeneStartsResultPanel.class, "GeneStartsResultPanel.exportButton.text")); // NOI18N
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportButton))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportButton;
    private javax.swing.JTable geneStartTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void addGeneStarts(List<GeneStart> geneStarts) {
        final int nbColumns = 9;
        this.geneStarts = geneStarts;
        DefaultTableModel model = (DefaultTableModel) geneStartTable.getModel();        

        for (GeneStart geneStart : this.geneStarts) {
            
            String strand = geneStart.getStrand() == SequenceUtils.STRAND_FWD ? "Fwd" : "Rev";
            
            Object[] rowData = new Object[nbColumns];
            rowData[0] = geneStart.getPos();
            rowData[1] = strand;
            rowData[2] = geneStart.getInitialCoverage();
            rowData[3] = geneStart.getStartCoverage();
            rowData[4] = geneStart.getStartCoverage() - geneStart.getInitialCoverage();
            if (geneStart.getInitialCoverage() > 0) {
                rowData[5] = (int) (((double) geneStart.getStartCoverage() / (double) geneStart.getInitialCoverage()) * 100.0) - 100;
            } else {
                rowData[5] = Integer.MAX_VALUE;
            }
            
            DetectedFeatures detFeatures = geneStart.getDetFeatures();
            PersistantFeature feature = detFeatures.getCorrectStartFeature();
            rowData[6] = feature != null ? PersistantFeature.getFeatureName(feature) : "-";
            feature = detFeatures.getUpstreamFeature();
            rowData[7] = feature != null ? PersistantFeature.getFeatureName(feature) : "-";
            feature = detFeatures.getDownstreamFeature();
            rowData[8] = feature != null ? PersistantFeature.getFeatureName(feature) : "-";

            model.addRow(rowData);
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
            this.geneStartTable.setRowSorter(sorter);
            sorter.setModel(model);
        }
    }
    
    
    private void showGeneStartPosition() {
        DefaultListSelectionModel model = (DefaultListSelectionModel) this.geneStartTable.getSelectionModel();
        int selectedView = model.getLeadSelectionIndex();
        int selectedModel = this.geneStartTable.convertRowIndexToModel(selectedView);
        int pos = (Integer) this.geneStartTable.getModel().getValueAt(selectedModel, 0);

        bim.navigatorBarUpdated(pos);
    }
    
    public void setBoundsInfoManager(BoundsInfoManager boundsInformationManager) {
        this.bim = boundsInformationManager;
    }

}
