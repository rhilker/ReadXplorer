package de.cebitec.readXplorer.transcriptomeAnalyses.main;

import java.util.HashMap;
import org.openide.util.NbBundle;

/**
 * Panel for showing the the statistics of a TSS detection result.
 *
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class TssDetectionStatsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Object> tssStatisticsMap;

    /**
     * Panel for showing the the statistics of a TSS detection result.
     *
     * @param tssStatisticsMap the snp results statistics to display
     */
    public TssDetectionStatsPanel(HashMap<String, Object> tssStatisticsMap) {
        this.tssStatisticsMap = tssStatisticsMap;
        initComponents();
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

        tssDetectionStatsScrollpane = new javax.swing.JScrollPane();
        tssDetectionStatsTable = new javax.swing.JTable();

        tssDetectionStatsTable.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.String.class, java.lang.Object.class
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
        tssDetectionStatsScrollpane.setViewportView(tssDetectionStatsTable);
        tssDetectionStatsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(TssDetectionStatsPanel.class, "TssDetectionStatsPanel.tssDetectionStatsTable.columnModel.title0_1")); // NOI18N
        tssDetectionStatsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(TssDetectionStatsPanel.class, "TssDetectionStatsPanel.tssDetectionStatsTable.columnModel.title1_1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tssDetectionStatsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tssDetectionStatsScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane tssDetectionStatsScrollpane;
    private javax.swing.JTable tssDetectionStatsTable;
    // End of variables declaration//GEN-END:variables

    private void initAdditionalComponents() {
        tssDetectionStatsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
            {ResultPanelTranscriptionStart.TSS_TOTAL, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_TOTAL))},
            {ResultPanelTranscriptionStart.TSS_CORRECT, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_CORRECT))},
            {ResultPanelTranscriptionStart.TSS_FWD, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_FWD))},
            {ResultPanelTranscriptionStart.TSS_REV, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_REV))},
            {ResultPanelTranscriptionStart.TSS_LEADERLESS, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_LEADERLESS))},
            {ResultPanelTranscriptionStart.TSS_INTERNAL, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_INTERNAL))},
            {ResultPanelTranscriptionStart.TSS_PUTATIVE_ANTISENSE, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.TSS_PUTATIVE_ANTISENSE))},
            {ResultPanelTranscriptionStart.MAPPINGS_COUNT, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.MAPPINGS_COUNT))},
            {ResultPanelTranscriptionStart.MAPPINGS_MEAN_LENGTH, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.MAPPINGS_MEAN_LENGTH))},
            {ResultPanelTranscriptionStart.MAPPINGS_MILLION, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.MAPPINGS_MILLION))},
            {ResultPanelTranscriptionStart.BACKGROUND_THRESHOLD, String.valueOf(this.tssStatisticsMap.get(ResultPanelTranscriptionStart.BACKGROUND_THRESHOLD))},},
                new String[]{
            NbBundle.getMessage(TssDetectionStatsPanel.class, "TssDetectionStatsPanel.tssDetectionStatsTable.columnModel.title0"),
            NbBundle.getMessage(TssDetectionStatsPanel.class, "TssDetectionStatsPanel.tssDetectionStatsTable.columnModel.title1")
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
