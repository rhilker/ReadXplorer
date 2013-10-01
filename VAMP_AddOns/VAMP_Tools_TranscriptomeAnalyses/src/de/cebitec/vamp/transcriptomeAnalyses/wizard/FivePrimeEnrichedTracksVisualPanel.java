/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cebitec.vamp.transcriptomeAnalyses.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class FivePrimeEnrichedTracksVisualPanel extends JPanel implements ActionListener {

    public static final String ACTION_COMMAND_TSSCHECKBOX = "tssCheck";
    private String tssDetectionText = "Leaderless and antisense detection is based on the transcriptional start site detection (TSS). That is why "
            + "you have to choose at least TSS-detection. ...";
    private String motifSearchText = "Motif seatch can be provide, if there are transcriptional start site known.";

    /**
     * Creates new form FivePrimeEnrichedTracksVisualPanel
     */
    public FivePrimeEnrichedTracksVisualPanel() {
        initComponents();
        jScrollPane1.setBorder(BorderFactory.createTitledBorder("TSS-detection"));
        jTextArea1.setEditable(false);
        jTextArea1.setText(tssDetectionText);
        jTextArea3.setText(motifSearchText);
        jTextArea3.setEditable(false);
        jScrollPane3.setBorder(BorderFactory.createTitledBorder("Motif-search"));

        tssCheckBox.addActionListener(this);
        tssCheckBox.setActionCommand(ACTION_COMMAND_TSSCHECKBOX);
        leaderlessCheckBox.setEnabled(false);
        antisenseCheckBox.setEnabled(false);
    }

    @Override
    public String getName() {
        return "Analyses for 5'-enriched Tracks";
    }

    public boolean isTSSSelected() {
        return tssCheckBox.isSelected();
    }

    public boolean isRBSSelected() {
        return rbsCheckBox.isSelected();
    }

    public boolean isPromotorSelected() {
        return promotorCheckBox.isSelected();
    }

    public boolean isAntisenseSelected() {
        return antisenseCheckBox.isSelected();
    }

    public boolean isLeaderLessSelected() {
        return leaderlessCheckBox.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tssCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        rbsCheckBox = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        promotorCheckBox = new javax.swing.JCheckBox();
        leaderlessCheckBox = new javax.swing.JCheckBox();
        antisenseCheckBox = new javax.swing.JCheckBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(tssCheckBox, org.openide.util.NbBundle.getMessage(FivePrimeEnrichedTracksVisualPanel.class, "FivePrimeEnrichedTracksVisualPanel.tssCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(rbsCheckBox, org.openide.util.NbBundle.getMessage(FivePrimeEnrichedTracksVisualPanel.class, "FivePrimeEnrichedTracksVisualPanel.rbsCheckBox.text")); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        org.openide.awt.Mnemonics.setLocalizedText(promotorCheckBox, org.openide.util.NbBundle.getMessage(FivePrimeEnrichedTracksVisualPanel.class, "FivePrimeEnrichedTracksVisualPanel.promotorCheckBox.text")); // NOI18N
        promotorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promotorCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(leaderlessCheckBox, org.openide.util.NbBundle.getMessage(FivePrimeEnrichedTracksVisualPanel.class, "FivePrimeEnrichedTracksVisualPanel.leaderlessCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(antisenseCheckBox, org.openide.util.NbBundle.getMessage(FivePrimeEnrichedTracksVisualPanel.class, "FivePrimeEnrichedTracksVisualPanel.antisenseCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(promotorCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leaderlessCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tssCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(antisenseCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tssCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(leaderlessCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(antisenseCheckBox))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(promotorCheckBox))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void promotorCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promotorCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_promotorCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox antisenseCheckBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JCheckBox leaderlessCheckBox;
    private javax.swing.JCheckBox promotorCheckBox;
    private javax.swing.JCheckBox rbsCheckBox;
    private javax.swing.JCheckBox tssCheckBox;
    // End of variables declaration//GEN-END:variables

    /**
     * Controls that antisense and leaderless analyses can be selectable only if
     * tss-analysis is selected.
     * 
     * @param e not in use. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ACTION_COMMAND_TSSCHECKBOX)) {
            if (leaderlessCheckBox.isEnabled() == false && antisenseCheckBox.isEnabled() == false) {
                leaderlessCheckBox.setEnabled(true);
                antisenseCheckBox.setEnabled(true);
            } else {
                leaderlessCheckBox.setEnabled(false);
                antisenseCheckBox.setEnabled(false);
            }
        }
    }
}
