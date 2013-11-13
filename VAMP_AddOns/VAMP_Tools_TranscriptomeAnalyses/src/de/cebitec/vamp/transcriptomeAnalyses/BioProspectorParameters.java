/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.cebitec.vamp.transcriptomeAnalyses;

import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import org.openide.util.NbPreferences;

/**
 *
 * @author jritter
 */
public class BioProspectorParameters extends javax.swing.JPanel {

    private File workingDir;

    /**
     * Creates new form BioProspectorParameters
     */
    public BioProspectorParameters() {
        initComponents();

        this.secondMotifWidthTF.setEnabled(false);
        this.maxGapTF.setEnabled(false);
        this.minGapTF.setEnabled(false);
        this.twoMotifsBlockIsPalindromeCB.setEnabled(false);
        this.wantMoreDegenerateSitesCB.setEnabled(false);
    }

    public int getMinusTenMotifWidth() {
        return Integer.valueOf(this.minusTenMotifWidthTF.getText());
    }
    
    public Integer getThirtyFiveMotifWidth() {
        return Integer.valueOf(this.thirtyfiveMotifWidthTF.getText());
    }

    public int getSecondMotifWidth() {
        if (this.secondMotifWidthTF.isEnabled()) {
            return Integer.valueOf(this.secondMotifWidthTF.getText());
        } else {
            return 0;

        }
    }

    public int getNoOfTimesTrying() {
        return Integer.valueOf(this.noOfTimesTryingTF.getText());
    }

    public int getNoOfTopMotifs() {
        return Integer.valueOf(this.noOfTopMotifTF.getText());
    }

    public int getMaxGap() {
        if (this.maxGapTF.isEnabled()) {
        return Integer.valueOf(this.maxGapTF.getText());
        } else {
            return 0;
        }
    }

    public int getMinGap() {
        if (this.minGapTF.isEditable()) {
        return Integer.valueOf(this.minGapTF.getText());
        } else {
            return 0;
        }
    }

    public boolean expectingTwoMitifBlocks() {
        return this.expectingTwoMotifsCB.isSelected();
    }

    public int isTwoMotifBlockPalindrome() {
        if (this.twoMotifsBlockIsPalindromeCB.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int isOnlyFwdExamination() {
        if (this.onlyFwdNeedExamineCB.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }

    public int everySeqHasMotif() {
        if (this.everySeqContainsMotifCB.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getInputFilePath() {
        return this.workingDirectoryTF.getText();
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File inputFile) {
        this.workingDir = inputFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        expectingTwoMotifsCB = new javax.swing.JCheckBox();
        noOfTopMotifTF = new javax.swing.JTextField();
        noOfTimesTryingTF = new javax.swing.JTextField();
        minusTenMotifWidthTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        twoMotifSettingsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        twoMotifsBlockIsPalindromeCB = new javax.swing.JCheckBox();
        wantMoreDegenerateSitesCB = new javax.swing.JCheckBox();
        maxGapTF = new javax.swing.JTextField();
        minGapTF = new javax.swing.JTextField();
        secondMotifWidthTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        workingDirectoryTF = new javax.swing.JTextField();
        startFileChooserButton = new javax.swing.JButton();
        everySeqContainsMotifCB = new javax.swing.JCheckBox();
        onlyFwdNeedExamineCB = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        thirtyfiveMotifWidthTF = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(expectingTwoMotifsCB, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.expectingTwoMotifsCB.text")); // NOI18N
        expectingTwoMotifsCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expectingTwoMotifsCBActionPerformed(evt);
            }
        });

        noOfTopMotifTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.noOfTopMotifTF.text")); // NOI18N

        noOfTimesTryingTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.noOfTimesTryingTF.text")); // NOI18N

        minusTenMotifWidthTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.minusTenMotifWidthTF.text")); // NOI18N
        minusTenMotifWidthTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusTenMotifWidthTFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(twoMotifsBlockIsPalindromeCB, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.twoMotifsBlockIsPalindromeCB.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(wantMoreDegenerateSitesCB, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.wantMoreDegenerateSitesCB.text")); // NOI18N

        maxGapTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.maxGapTF.text")); // NOI18N

        minGapTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.minGapTF.text")); // NOI18N

        secondMotifWidthTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.secondMotifWidthTF.text")); // NOI18N

        javax.swing.GroupLayout twoMotifSettingsPanelLayout = new javax.swing.GroupLayout(twoMotifSettingsPanel);
        twoMotifSettingsPanel.setLayout(twoMotifSettingsPanelLayout);
        twoMotifSettingsPanelLayout.setHorizontalGroup(
            twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(twoMotifSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(twoMotifsBlockIsPalindromeCB)
                .addGap(18, 18, 18)
                .addComponent(wantMoreDegenerateSitesCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(twoMotifSettingsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addGap(35, 35, 35)
                    .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(minGapTF, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(maxGapTF, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(secondMotifWidthTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(149, Short.MAX_VALUE)))
        );
        twoMotifSettingsPanelLayout.setVerticalGroup(
            twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, twoMotifSettingsPanelLayout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(twoMotifsBlockIsPalindromeCB)
                    .addComponent(wantMoreDegenerateSitesCB))
                .addGap(24, 24, 24))
            .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(twoMotifSettingsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(secondMotifWidthTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maxGapTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(twoMotifSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minGapTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addContainerGap(63, Short.MAX_VALUE)))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel7.text")); // NOI18N

        workingDirectoryTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.workingDirectoryTF.text")); // NOI18N
        workingDirectoryTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workingDirectoryTFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(startFileChooserButton, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.startFileChooserButton.text")); // NOI18N
        startFileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startFileChooserButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(everySeqContainsMotifCB, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.everySeqContainsMotifCB.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(onlyFwdNeedExamineCB, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.onlyFwdNeedExamineCB.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.jLabel8.text")); // NOI18N

        thirtyfiveMotifWidthTF.setText(org.openide.util.NbBundle.getMessage(BioProspectorParameters.class, "BioProspectorParameters.thirtyfiveMotifWidthTF.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twoMotifSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(workingDirectoryTF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startFileChooserButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expectingTwoMotifsCB)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(noOfTopMotifTF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(noOfTimesTryingTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(everySeqContainsMotifCB)
                                .addGap(39, 39, 39)
                                .addComponent(onlyFwdNeedExamineCB))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(minusTenMotifWidthTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(thirtyfiveMotifWidthTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(minusTenMotifWidthTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(thirtyfiveMotifWidthTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(noOfTimesTryingTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(noOfTopMotifTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(everySeqContainsMotifCB)
                    .addComponent(onlyFwdNeedExamineCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(expectingTwoMotifsCB)
                .addGap(18, 20, Short.MAX_VALUE)
                .addComponent(twoMotifSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(workingDirectoryTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startFileChooserButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minusTenMotifWidthTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusTenMotifWidthTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusTenMotifWidthTFActionPerformed

    private void expectingTwoMotifsCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expectingTwoMotifsCBActionPerformed

        if (!this.expectingTwoMitifBlocks()) {
            this.secondMotifWidthTF.setEnabled(false);
            this.maxGapTF.setEnabled(false);
            this.minGapTF.setEnabled(false);
            this.twoMotifsBlockIsPalindromeCB.setEnabled(false);
            this.wantMoreDegenerateSitesCB.setEnabled(false);
        } else {
            this.secondMotifWidthTF.setEnabled(true);
            this.maxGapTF.setEnabled(true);
            this.minGapTF.setEnabled(true);
            this.twoMotifsBlockIsPalindromeCB.setEnabled(true);
            this.wantMoreDegenerateSitesCB.setEnabled(true);
        }
    }//GEN-LAST:event_expectingTwoMotifsCBActionPerformed

    private void workingDirectoryTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workingDirectoryTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workingDirectoryTFActionPerformed

    private void startFileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startFileChooserButtonActionPerformed
        Preferences prefs = NbPreferences.forModule(Object.class);
        String currentDirPath = prefs.get(de.cebitec.readxplorer.transcriptomeAnalyses.enums.Preferences.CURRENT_DIR.toString(), null);
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.workingDirectoryTF.setText(fc.getSelectedFile().getAbsolutePath().toString());
            this.setWorkingDir(fc.getSelectedFile());
            prefs.put(de.cebitec.readxplorer.transcriptomeAnalyses.enums.Preferences.CURRENT_DIR.toString(), fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_startFileChooserButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox everySeqContainsMotifCB;
    private javax.swing.JCheckBox expectingTwoMotifsCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField maxGapTF;
    private javax.swing.JTextField minGapTF;
    private javax.swing.JTextField minusTenMotifWidthTF;
    private javax.swing.JTextField noOfTimesTryingTF;
    private javax.swing.JTextField noOfTopMotifTF;
    private javax.swing.JCheckBox onlyFwdNeedExamineCB;
    private javax.swing.JTextField secondMotifWidthTF;
    private javax.swing.JButton startFileChooserButton;
    private javax.swing.JTextField thirtyfiveMotifWidthTF;
    private javax.swing.JPanel twoMotifSettingsPanel;
    private javax.swing.JCheckBox twoMotifsBlockIsPalindromeCB;
    private javax.swing.JCheckBox wantMoreDegenerateSitesCB;
    private javax.swing.JTextField workingDirectoryTF;
    // End of variables declaration//GEN-END:variables
}
