/* 
 * Copyright (C) 2014 Institute for Bioinformatics and Systems Biology, University Giessen, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.cebitec.readXplorer.coverageAnalysis;

import java.util.Map;
import org.openide.util.NbBundle;

/**
 * Statistics panel of the coverage analysis.
 *
 * @author Tobias Zimmermann, Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public class CoverageAnalysisStatsPanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private Map<String, Integer> featureStatsMap;

    /**
     * Statistics panel of the coverage analysis.
     * @param coveredStatisticsMap statistics map to display in this panel
     */
    public CoverageAnalysisStatsPanel(Map<String, Integer> coveredStatisticsMap) {
        initComponents();
        this.featureStatsMap = coveredStatisticsMap;
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

        coverageAnalysisStatsPane1 = new javax.swing.JScrollPane();
        coverageAnalysisStatsTable = new javax.swing.JTable();

        coverageAnalysisStatsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        coverageAnalysisStatsPane1.setViewportView(coverageAnalysisStatsTable);
        coverageAnalysisStatsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(CoverageAnalysisStatsPanel.class, "CoverageAnalysisStatsPanel.coverageAnalysisStatsTable.columnModel.title0")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverageAnalysisStatsPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverageAnalysisStatsPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane coverageAnalysisStatsPane1;
    private javax.swing.JTable coverageAnalysisStatsTable;
    // End of variables declaration//GEN-END:variables

     private void initAdditionalComponents() {
        coverageAnalysisStatsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                   
                    {ResultPanelCoverageAnalysis.NUMBER_INTERVALS, String.valueOf(this.featureStatsMap.get(ResultPanelCoverageAnalysis.NUMBER_INTERVALS))},
                    {ResultPanelCoverageAnalysis.MEAN_INTERVAL_LENGTH, String.valueOf(this.featureStatsMap.get(ResultPanelCoverageAnalysis.MEAN_INTERVAL_LENGTH))},
                    {ResultPanelCoverageAnalysis.MEAN_INTERVAL_COVERAGE, String.valueOf(this.featureStatsMap.get(ResultPanelCoverageAnalysis.MEAN_INTERVAL_COVERAGE))}
                },
                new String[]{
                    NbBundle.getMessage(CoverageAnalysisStatsPanel.class, "CoverageAnalysisStatsPanel.coveredAnalysisStatsTable.columnModel.title0"),
                    NbBundle.getMessage(CoverageAnalysisStatsPanel.class, "CoverageAnalysisStatsPanel.coveredAnalysisStatsTable.columnModel.title1")
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
