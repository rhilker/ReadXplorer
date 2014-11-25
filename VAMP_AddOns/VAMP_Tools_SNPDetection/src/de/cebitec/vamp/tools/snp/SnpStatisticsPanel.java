package de.cebitec.vamp.tools.snp;

import java.util.Map;
import org.openide.util.NbBundle;

/**
 * Panel for showing the the statistics of a SNP detection result.
 *
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class SnpStatisticsPanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private final Map<String, Integer> snpStatsMap;

    /**
     * Creates a new panel for showing the the statistics of a SNP detection result.
     * @param snpStatsMap the snp results statistics to display
     */
    public SnpStatisticsPanel(Map<String, Integer> snpStatsMap) {
        this.snpStatsMap = snpStatsMap;
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

        snpTypeScrollpane = new javax.swing.JScrollPane();
        snpTypeTable = new javax.swing.JTable();
        snpEffectScrollpane = new javax.swing.JScrollPane();
        snpEffectTable = new javax.swing.JTable();

        snpTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "SNP type"
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
        snpTypeScrollpane.setViewportView(snpTypeTable);
        snpTypeTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpTypeTable.columnModel.title0")); // NOI18N

        snpEffectTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "SNP effect"
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
        snpEffectScrollpane.setViewportView(snpEffectTable);
        snpEffectTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpEffectTable.columnModel.title0")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snpTypeScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
            .addComponent(snpEffectScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(snpEffectScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(snpTypeScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane snpEffectScrollpane;
    private javax.swing.JTable snpEffectTable;
    private javax.swing.JScrollPane snpTypeScrollpane;
    private javax.swing.JTable snpTypeTable;
    // End of variables declaration//GEN-END:variables

    private void initAdditionalComponents() {
        snpEffectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {SNP_DetectionResultPanel.SNPS_TOTAL, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_TOTAL))},
                {SNP_DetectionResultPanel.SNPS_INTERGENEIC, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_INTERGENEIC))},
                {SNP_DetectionResultPanel.SNPS_SYNONYMOUS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_SYNONYMOUS))},
                {SNP_DetectionResultPanel.SNPS_CHEMIC_NEUTRAL, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_CHEMIC_NEUTRAL))},
                {SNP_DetectionResultPanel.SNPS_MISSSENSE, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_MISSSENSE))},
                {SNP_DetectionResultPanel.SNPS_AA_INSERTIONS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_AA_INSERTIONS))},
                {SNP_DetectionResultPanel.SNPS_AA_DELETIONS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_AA_DELETIONS))}
            },
            new String [] {
                NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpEffectTable.columnModel.title0"), 
                NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpEffectTable.columnModel.title1")
                
            }
        ) {
            private static final long serialVersionUID = 1L;
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        snpTypeTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {SNP_DetectionResultPanel.SNPS_SUBSTITUTIONS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_SUBSTITUTIONS))},
                    {SNP_DetectionResultPanel.SNPS_INSERTIONS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_INSERTIONS))},
                    {SNP_DetectionResultPanel.SNPS_DELETIONS, String.valueOf(this.snpStatsMap.get(SNP_DetectionResultPanel.SNPS_DELETIONS))}
                },
                new String[]{
                    NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpTypeTable.columnModel.title0"), 
                    NbBundle.getMessage(SnpStatisticsPanel.class, "SnpStatisticsPanel.snpTypeTable.columnModel.title1")
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
