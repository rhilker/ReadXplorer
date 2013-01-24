package de.cebitec.vamp.transcriptionAnalyses;

import java.util.HashMap;
import org.openide.util.NbBundle;

/**
 * Panel for showing the the statistics of a result of filtered features.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class FilterFeaturesStatsPanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private HashMap<String, Integer> filteredFeaturesStatsMap;

    /**
     * Creates new form FilterFeaturesStatsPanel
     * @param filteredFeaturesStatsMap statistics to display
     */
    public FilterFeaturesStatsPanel(HashMap<String, Integer> filteredFeaturesStatsMap) {
        this.filteredFeaturesStatsMap = filteredFeaturesStatsMap;
        this.initComponents();
        this.initAdditionalComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterFeatureStatsScrollpane = new javax.swing.JScrollPane();
        filterFeatureStatsTable = new javax.swing.JTable();

        filterFeatureStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        filterFeatureStatsScrollpane.setViewportView(filterFeatureStatsTable);
        filterFeatureStatsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(FilterFeaturesStatsPanel.class, "FilterFeaturesStatsPanel.filterFeatureStatsTable.columnModel.title0")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterFeatureStatsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterFeatureStatsScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane filterFeatureStatsScrollpane;
    private javax.swing.JTable filterFeatureStatsTable;
    // End of variables declaration//GEN-END:variables
    
    private void initAdditionalComponents() {
        filterFeatureStatsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {ResultPanelFilteredFeatures.FEATURES_TOTAL, String.valueOf(this.filteredFeaturesStatsMap.get(ResultPanelFilteredFeatures.FEATURES_TOTAL))}
                },
                new String[]{
                    NbBundle.getMessage(FilterFeaturesStatsPanel.class, "FilterFeaturesStatsPanel.filterFeatureStatsTable.columnModel.title0"),
                    NbBundle.getMessage(FilterFeaturesStatsPanel.class, "FilterFeaturesStatsPanel.filterFeatureStatsTable.columnModel.title1")
                }) {
            private static final long serialVersionUID = 1L;
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

}
