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

package de.cebitec.readxplorer.transcriptionanalyses;


import java.util.Map;
import org.openide.util.NbBundle;


/**
 * Panel for showing the the statistics of a result of the normalization
 * analysis.
 * <p>
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class NormalizationStatsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private final Map<String, Integer> normStatsMap;


    /**
     * Panel for showing the the statistics of a result of the normalization
     * analysis.
     * <p>
     * @param featureStatsMap statistics to display
     */
    public NormalizationStatsPanel( Map<String, Integer> featureStatsMap ) {
        this.normStatsMap = featureStatsMap;
        this.initComponents();
        this.initAdditionalComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        normStatsScrollpane = new javax.swing.JScrollPane();
        normStatsTable = new javax.swing.JTable();

        normStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Count Type", "Count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        normStatsScrollpane.setViewportView(normStatsTable);
        if (normStatsTable.getColumnModel().getColumnCount() > 0) {
            normStatsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(NormalizationStatsPanel.class, "NormalizationStatsPanel.normStatsTable.columnModel.title0")); // NOI18N
            normStatsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(NormalizationStatsPanel.class, "NormalizationStatsPanel.normStatsTable.columnModel.title1")); // NOI18N
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(normStatsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(normStatsScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane normStatsScrollpane;
    private javax.swing.JTable normStatsTable;
    // End of variables declaration//GEN-END:variables


    private void initAdditionalComponents() {
        normStatsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    { ResultPanelNormalization.TOTAL_MAPPINGS, this.normStatsMap.get( ResultPanelNormalization.TOTAL_MAPPINGS ) },
                    { ResultPanelNormalization.RETURNED_FEATURES, this.normStatsMap.get( ResultPanelNormalization.RETURNED_FEATURES ) },
                    { ResultPanelNormalization.FEATURES_TOTAL, this.normStatsMap.get( ResultPanelNormalization.FEATURES_TOTAL ) }
                },
                new String[]{
                    NbBundle.getMessage(NormalizationStatsPanel.class, "NormalizationStatsPanel.normStatsTable.columnModel.title0" ),
                    NbBundle.getMessage(NormalizationStatsPanel.class, "NormalizationStatsPanel.normStatsTable.columnModel.title1" )
                } ) {
                    private static final long serialVersionUID = 1L;
                    Class<?>[] types = new Class<?>[]{
                        java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false
                    };


                    @Override
                    public Class<?> getColumnClass( int columnIndex ) {
                        return types[columnIndex];
                    }


                    @Override
                    public boolean isCellEditable( int rowIndex, int columnIndex ) {
                        return canEdit[columnIndex];
                    }


                } );
    }


}
