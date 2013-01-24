/*
 * ResultPanelFilteredFeatures.java
 *
 * Created on 27.01.2012, 14:31:15
 */
package de.cebitec.vamp.transcriptionAnalyses;

import de.cebitec.vamp.databackend.dataObjects.PersistantFeature;
import de.cebitec.vamp.exporter.excel.ExcelExportFileChooser;
import de.cebitec.vamp.transcriptionAnalyses.dataStructures.FilteredFeature;
import de.cebitec.vamp.view.dataVisualisation.BoundsInfoManager;
import java.util.HashMap;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Panel showing a result of an analysis filtering for features with a 
 * min and max certain readcount.
 * 
 * @author -Rolf Hilker-
 */
public class ResultPanelFilteredFeatures extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;

    private BoundsInfoManager bim;
    private FilteredFeaturesResult filterFeaturesResult;
    private HashMap<String, Integer> filterStatisticsMap;
    
    public static final String FEATURES_TOTAL = "Total number of filtered features";
    
    /**
     * Panel showing a result of an analysis filtering for features with a
     * min and max certain readcount.
     */
    public ResultPanelFilteredFeatures() {
        initComponents();
        this.filterStatisticsMap = new HashMap<>();
        this.filterStatisticsMap.put(FEATURES_TOTAL, 0);
        
        DefaultListSelectionModel model = (DefaultListSelectionModel) this.filteredFeaturesTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                showFilteredAnnoPosition();
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

        filteredFeaturesPane = new javax.swing.JScrollPane();
        filteredFeaturesTable = new javax.swing.JTable();
        exportButton = new javax.swing.JButton();
        parametersLabel = new javax.swing.JLabel();
        statisticsButton = new javax.swing.JButton();

        filteredFeaturesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Feature", "Start", "Stop"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        filteredFeaturesPane.setViewportView(filteredFeaturesTable);
        filteredFeaturesTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.filteredFeaturesTable.columnModel.title0")); // NOI18N
        filteredFeaturesTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.filteredFeaturesTable.columnModel.title3")); // NOI18N
        filteredFeaturesTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.filteredFeaturesTable.columnModel.title4")); // NOI18N

        exportButton.setText(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.exportButton.text")); // NOI18N
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        parametersLabel.setText(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.parametersLabel.text")); // NOI18N

        statisticsButton.setText(org.openide.util.NbBundle.getMessage(ResultPanelFilteredFeatures.class, "ResultPanelFilteredFeatures.statisticsButton.text")); // NOI18N
        statisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(parametersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statisticsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton))
            .addComponent(filteredFeaturesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(filteredFeaturesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(statisticsButton)
                    .addComponent(parametersLabel)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        FilteredFeaturesColumns filteredFeaturesData = new FilteredFeaturesColumns(this.filterFeaturesResult, this.filterStatisticsMap);
        ExcelExportFileChooser fileChooser = new ExcelExportFileChooser(new String[]{"xls"}, "xls", filteredFeaturesData); 
    }//GEN-LAST:event_exportButtonActionPerformed

    private void statisticsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsButtonActionPerformed
        JOptionPane.showMessageDialog(this, new FilterFeaturesStatsPanel(filterStatisticsMap), "Feature Filtering Statistics", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_statisticsButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportButton;
    private javax.swing.JScrollPane filteredFeaturesPane;
    private javax.swing.JTable filteredFeaturesTable;
    private javax.swing.JLabel parametersLabel;
    private javax.swing.JButton statisticsButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Updates the navigator bar of all viewers to the start position of the selected feature.
     */
    private void showFilteredAnnoPosition() {
        DefaultListSelectionModel model = (DefaultListSelectionModel) this.filteredFeaturesTable.getSelectionModel();
        int selectedView = model.getLeadSelectionIndex();
        int selectedModel = this.filteredFeaturesTable.convertRowIndexToModel(selectedView);
        PersistantFeature feature = (PersistantFeature) this.filteredFeaturesTable.getModel().getValueAt(selectedModel, 0);
        int pos = feature.isFwdStrand() ? feature.getStart() : feature.getStop();

        bim.navigatorBarUpdated(pos);
    }

    public void setBoundsInfoManager(BoundsInfoManager boundsInformationManager) {
        this.bim = boundsInformationManager;
    }

    /**
     * Adds a list of filtered features to this panel contained in the result object.
     * @param filterFeaturesResult a filtering for features result 
     */
    public void addFilteredFeatures(FilteredFeaturesResult filterFeaturesResult) {
        final int nbColumns = 3;
        
        if (this.filterFeaturesResult == null) {
            this.filterFeaturesResult = filterFeaturesResult;
        } else {
            this.filterFeaturesResult.getResults().addAll(filterFeaturesResult.getResults());
        }
        
        DefaultTableModel model = (DefaultTableModel) this.filteredFeaturesTable.getModel();        

        for (FilteredFeature filteredAnno : this.filterFeaturesResult.getResults()) {
            
            Object[] rowData = new Object[nbColumns];
            rowData[0] = filteredAnno.getFilteredFeature();
            rowData[1] = filteredAnno.getFilteredFeature().isFwdStrand() ? "Fwd" : "Rev";
            rowData[2] = filteredAnno.getReadCount();

            model.addRow(rowData);
        }

        TableRowSorter<TableModel> sorter = new TableRowSorter<>();
        this.filteredFeaturesTable.setRowSorter(sorter);
        sorter.setModel(model);
        
        ParameterSetFilteredFeatures parameters = (ParameterSetFilteredFeatures) filterFeaturesResult.getParameters();
        this.parametersLabel.setText(org.openide.util.NbBundle.getMessage(ResultPanelTranscriptionStart.class,
                "ResultPanelFilteredFeatures.parametersLabel.text", parameters.getMinNumberReads(), parameters.getMaxNumberReads()));
        
        this.filterStatisticsMap.put(FEATURES_TOTAL, this.filterStatisticsMap.get(FEATURES_TOTAL) + filterFeaturesResult.getResults().size());
    }
    
    /**
     * @return the number of features filtered during the associated analysis
     */
    public int getResultSize() {
        return this.filterFeaturesResult.getResults().size();
    }
}
