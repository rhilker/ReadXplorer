/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cebitec.vamp.transcriptomeAnalyses.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public final class DataSetChoiceVisualPanel extends JPanel implements ActionListener {

    private String choosenDataSet = null;

    /**
     * Creates new form DataSetChoiceVisualPanel
     */
    public DataSetChoiceVisualPanel() {
        initComponents();

        wholeGenomeTrackCheckBox.addActionListener(this);
        wholeGenomeTrackCheckBox.setActionCommand(TranscriptomeAnalysisWizardIterator.PROP_WHOLEGENOME_DATASET);
        fiveEnrichedTrackCheckBox.addActionListener(this);
        fiveEnrichedTrackCheckBox.setActionCommand(TranscriptomeAnalysisWizardIterator.PROP_FIVEPRIME_DATASET);
    }

    @Override
    public String getName() {
        return "Data type selection";
    }

    public boolean isWholeGenomeTrack() {
        return this.wholeGenomeTrackCheckBox.isSelected();
    }

    public boolean isFifeEnrichedTrack() {
        return this.fiveEnrichedTrackCheckBox.isSelected();
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
        wholeGenomeTrackCheckBox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        fiveEnrichedTrackCheckBox = new javax.swing.JCheckBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(DataSetChoiceVisualPanel.class, "DataSetChoiceVisualPanel.jTextArea1.text")); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(wholeGenomeTrackCheckBox, org.openide.util.NbBundle.getMessage(DataSetChoiceVisualPanel.class, "DataSetChoiceVisualPanel.wholeGenomeTrackCheckBox.text")); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText(org.openide.util.NbBundle.getMessage(DataSetChoiceVisualPanel.class, "DataSetChoiceVisualPanel.jTextArea2.text")); // NOI18N
        jScrollPane2.setViewportView(jTextArea2);

        org.openide.awt.Mnemonics.setLocalizedText(fiveEnrichedTrackCheckBox, org.openide.util.NbBundle.getMessage(DataSetChoiceVisualPanel.class, "DataSetChoiceVisualPanel.fiveEnrichedTrackCheckBox.text")); // NOI18N
        fiveEnrichedTrackCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveEnrichedTrackCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(wholeGenomeTrackCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(fiveEnrichedTrackCheckBox)))
                        .addGap(0, 159, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wholeGenomeTrackCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fiveEnrichedTrackCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fiveEnrichedTrackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveEnrichedTrackCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fiveEnrichedTrackCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox fiveEnrichedTrackCheckBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JCheckBox wholeGenomeTrackCheckBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(TranscriptomeAnalysisWizardIterator.PROP_WHOLEGENOME_DATASET)) {
            if (((JCheckBox) e.getSource()).isSelected()) {
                fiveEnrichedTrackCheckBox.setSelected(false);
            }
        }

        if (e.getActionCommand().equals(TranscriptomeAnalysisWizardIterator.PROP_FIVEPRIME_DATASET)) {
            if (((JCheckBox) e.getSource()).isSelected()) {
                wholeGenomeTrackCheckBox.setSelected(false);
            }
        }
    }
}
