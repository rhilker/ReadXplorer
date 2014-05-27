/* 
 * Copyright (C) 2014 Rolf Hilker
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
package de.cebitec.readXplorer.view.dialogMenus;

import de.cebitec.readXplorer.api.objects.JobPanel;
import de.cebitec.readXplorer.databackend.ParametersReadClasses;
import de.cebitec.readXplorer.util.GeneralUtils;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 * A visual wizard job panel. It offers to select read mapping classes and
 * unique or all mapped reads for any further processing.
 *
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class SelectReadClassVisualPanel extends JobPanel {
    
    private static final long serialVersionUID = 1L;
    private boolean usingADBTrack;
    private final String wizardName;
    private byte minMappingQual;

    /**
     * A visual wizard job panel. It offers to select read mapping classes and
     * unique or all mapped reads for any further processing.
     * @param wizardName the name of the corresponding wizard
     */
    public SelectReadClassVisualPanel(String wizardName) {
        this.wizardName = wizardName;
        this.usingADBTrack = false;
        this.initComponents();
        this.loadLastParameterSelection();
        this.minMappingQualityField.getDocument().addDocumentListener(this.createDocumentListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descriptionScrollPane = new javax.swing.JScrollPane();
        decriptionTextArea = new javax.swing.JTextArea();
        checkBoxPerfect = new javax.swing.JCheckBox();
        checkBoxBestMatch = new javax.swing.JCheckBox();
        checkBoxCommon = new javax.swing.JCheckBox();
        checkBoxUnique = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        minMappingQualLabel = new javax.swing.JLabel();
        minMappingQualityField = new javax.swing.JTextField();

        decriptionTextArea.setEditable(false);
        decriptionTextArea.setBackground(new java.awt.Color(240, 240, 240));
        decriptionTextArea.setColumns(20);
        decriptionTextArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        decriptionTextArea.setLineWrap(true);
        decriptionTextArea.setRows(5);
        decriptionTextArea.setText(org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.decriptionTextArea.text")); // NOI18N
        decriptionTextArea.setWrapStyleWord(true);
        descriptionScrollPane.setViewportView(decriptionTextArea);

        checkBoxPerfect.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(checkBoxPerfect, org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.checkBoxPerfect.text")); // NOI18N
        checkBoxPerfect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxPerfectActionPerformed(evt);
            }
        });

        checkBoxBestMatch.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(checkBoxBestMatch, org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.checkBoxBestMatch.text")); // NOI18N
        checkBoxBestMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxBestMatchActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxCommon, org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.checkBoxCommon.text")); // NOI18N
        checkBoxCommon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxCommonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxUnique, org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.checkBoxUnique.text")); // NOI18N
        checkBoxUnique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxUniqueActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        org.openide.awt.Mnemonics.setLocalizedText(minMappingQualLabel, org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.minMappingQualLabel.text")); // NOI18N

        minMappingQualityField.setText(org.openide.util.NbBundle.getMessage(SelectReadClassVisualPanel.class, "SelectReadClassVisualPanel.minMappingQualityField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxBestMatch)
                            .addComponent(checkBoxCommon)
                            .addComponent(checkBoxPerfect))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxUnique)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minMappingQualityField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minMappingQualLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBoxPerfect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBoxBestMatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBoxCommon)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkBoxUnique)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(minMappingQualLabel)
                                    .addComponent(minMappingQualityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxCommonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCommonActionPerformed
        if (this.checkBoxCommon.isSelected()) {
            this.checkBoxUnique.setSelected(false);
        }
        this.checkBoxUnique.setEnabled(!this.checkBoxCommon.isSelected() && !this.usingADBTrack);
        this.isRequiredInfoSet();
    }//GEN-LAST:event_checkBoxCommonActionPerformed

    private void checkBoxUniqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxUniqueActionPerformed
        if (this.checkBoxUnique.isSelected()) {
            this.checkBoxCommon.setSelected(false);
        }
        this.checkBoxCommon.setEnabled(!this.checkBoxUnique.isSelected());
        this.isRequiredInfoSet();
    }//GEN-LAST:event_checkBoxUniqueActionPerformed

    private void checkBoxPerfectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPerfectActionPerformed
        this.isRequiredInfoSet();
    }//GEN-LAST:event_checkBoxPerfectActionPerformed

    private void checkBoxBestMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxBestMatchActionPerformed
        this.isRequiredInfoSet();
    }//GEN-LAST:event_checkBoxBestMatchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxBestMatch;
    private javax.swing.JCheckBox checkBoxCommon;
    private javax.swing.JCheckBox checkBoxPerfect;
    private javax.swing.JCheckBox checkBoxUnique;
    private javax.swing.JTextArea decriptionTextArea;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel minMappingQualLabel;
    private javax.swing.JTextField minMappingQualityField;
    // End of variables declaration//GEN-END:variables

    /**
     * @return <cc>true</cc>, if at least one read class is selected, 
     * <cc>false</cc> otherwise
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = 
                   this.checkBoxPerfect.isSelected()
                || this.checkBoxBestMatch.isSelected()
                || this.checkBoxCommon.isSelected();
        
        if (GeneralUtils.isValidByteInput(minMappingQualityField.getText())) {
            this.minMappingQual = Byte.parseByte(minMappingQualityField.getText());
        } else {
            isValidated = false;
        }
        firePropertyChange(ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated);
        return isValidated;
    }
    
    /**
     * @return Minimum phred scaled mapping quality.
     */
    public ParametersReadClasses getReadClassParams() {
        return new ParametersReadClasses(
                this.checkBoxPerfect.isSelected(), 
                this.checkBoxBestMatch.isSelected(), 
                this.checkBoxCommon.isSelected(), 
                this.checkBoxUnique.isSelected(), 
                this.minMappingQual);
    }
    
    @Override
    public String getName() {
        return "Read Classification Selection";
    }

    /**
     * Updates the checkboxes for the read classes with the globally stored
     * settings for this wizard. If no settings were stored, the default
     * configuration is chosen.
     */
    private void loadLastParameterSelection() {
        Preferences pref = NbPreferences.forModule(Object.class);
        boolean isPerfectSelected = pref.get(wizardName + SelectReadClassWizardPanel.PROP_PERFECT_SELECTED, "1").equals("1");
        boolean isBestMatchSelected = pref.get(wizardName + SelectReadClassWizardPanel.PROP_BEST_MATCH_SELECTED, "1").equals("1");
        boolean isCommonMatchSelected = pref.get(wizardName + SelectReadClassWizardPanel.PROP_COMMON_MATCH_SELECTED, "0").equals("1");
        boolean isUniqueSelected = pref.get(wizardName + SelectReadClassWizardPanel.PROP_UNIQUE_SELECTED, "0").equals("1");
        this.checkBoxPerfect.setSelected(isPerfectSelected);
        this.checkBoxBestMatch.setSelected(isBestMatchSelected);
        this.checkBoxCommon.setSelected(isCommonMatchSelected);
        this.checkBoxUnique.setSelected(isUniqueSelected);
        this.minMappingQualityField.setText(pref.get(wizardName + SelectReadClassWizardPanel.PROP_MIN_MAPPING_QUAL, "0"));
    }
    
}
