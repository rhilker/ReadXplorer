package de.cebitec.readXplorer.transcriptomeAnalyses.main;

/**
 *
 * @author jritter
 */
public class PromotorSelectionPanel extends javax.swing.JPanel {
    /**
     * Creates new form PromotorSelectionPanel
     */
    public PromotorSelectionPanel() {
        initComponents();
        buttonGroup1.add(promotor);
        buttonGroup1.add(leaderless);
        buttonGroup1.add(rbsCB);
        promotor.setSelected(true);
    }
    
    public boolean isLeaderLessSelected() {
        if (promotor.isSelected()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        leaderless = new javax.swing.JRadioButton();
        promotor = new javax.swing.JRadioButton();
        rbsCB = new javax.swing.JRadioButton();

        org.openide.awt.Mnemonics.setLocalizedText(leaderless, org.openide.util.NbBundle.getMessage(PromotorSelectionPanel.class, "PromotorSelectionPanel.leaderless.text")); // NOI18N
        leaderless.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderlessActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(promotor, org.openide.util.NbBundle.getMessage(PromotorSelectionPanel.class, "PromotorSelectionPanel.promotor.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(rbsCB, org.openide.util.NbBundle.getMessage(PromotorSelectionPanel.class, "PromotorSelectionPanel.rbsCB.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leaderless)
                    .addComponent(promotor)
                    .addComponent(rbsCB))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leaderless)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(promotor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbsCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void leaderlessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderlessActionPerformed
    
    }//GEN-LAST:event_leaderlessActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton leaderless;
    private javax.swing.JRadioButton promotor;
    private javax.swing.JRadioButton rbsCB;
    // End of variables declaration//GEN-END:variables
}
