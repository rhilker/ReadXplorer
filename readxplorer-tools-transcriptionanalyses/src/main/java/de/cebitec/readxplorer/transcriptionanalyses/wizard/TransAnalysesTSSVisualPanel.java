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

package de.cebitec.readxplorer.transcriptionanalyses.wizard;


import de.cebitec.readxplorer.api.objects.JobPanel;
import de.cebitec.readxplorer.ui.dialogmenus.ChangeListeningWizardPanel;
import de.cebitec.readxplorer.utils.GeneralUtils;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_ANALYSIS_DIRECTION;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_AUTO_TSS_PARAMS;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MAX_FEATURE_DISTANCE;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MAX_LEADERLESS_DISTANCE;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MAX_LOW_COV_INIT_COUNT;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MIN_LOW_COV_INC;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MIN_PERCENT_INCREASE;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MIN_TOTAL_INCREASE;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_MIN_TRANSCRIPT_EXTENSION_COV;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_UNANNOTATED_TRANSCRIPT_DET;
import static de.cebitec.readxplorer.transcriptionanalyses.wizard.TranscriptionAnalysesWizardIterator.PROP_WIZARD_NAME;


/**
 * Panel for showing all available options for the transcription start site
 * detection.
 * <p>
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public final class TransAnalysesTSSVisualPanel extends JobPanel {

    private static final long serialVersionUID = 1L;

    private int minTotalIncrease;
    private int minTotalPercentIncrease;
    private int maxLowCovInitialCount;
    private int minLowCovIncrease;
    private int minTranscriptExtensionCov;
    private int maxLeaderlessDistance;
    private int maxFeatureDistance;
    private boolean detectUnannotatedTranscripts = true;
    private boolean tssAutomatic = false;


    /**
     * Panel for showing all available options for the transcription start site
     * detection.
     */
    public TransAnalysesTSSVisualPanel() {
        this.initComponents();
        this.initAdditionalComponents();
    }


    @Override
    public String getName() {
        return "TSS Analysis Parameters";
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        transcriptionStartAutomaticBox = new javax.swing.JCheckBox();
        minTotalIncreaseField = new javax.swing.JTextField();
        minTotalIncreaseLabel = new javax.swing.JLabel();
        minPercentIncreaseField = new javax.swing.JTextField();
        minPercentIncreaseLabel = new javax.swing.JLabel();
        additionalOptionPanel = new javax.swing.JPanel();
        maxInitialCountField = new javax.swing.JTextField();
        maxInitialCountLabel = new javax.swing.JLabel();
        minLowCovCountField = new javax.swing.JTextField();
        minLowCovCountLabel = new javax.swing.JLabel();
        addRestrictionLabel = new javax.swing.JLabel();
        unannotatedTranscriptsBox = new javax.swing.JCheckBox();
        transcriptExtensionField = new javax.swing.JTextField();
        transcriptExtensionLabel = new javax.swing.JLabel();
        maxLeaderlessDistanceField = new javax.swing.JTextField();
        transcriptExtensionLabel1 = new javax.swing.JLabel();
        analysisDirectionLabel = new javax.swing.JLabel();
        dirFwdRadioButton = new javax.swing.JRadioButton();
        dirRevRadioButton = new javax.swing.JRadioButton();
        analysisDirectionSeparator = new javax.swing.JSeparator();
        maxFeatureDistField = new javax.swing.JTextField();
        maxFeatureDistLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(transcriptionStartAutomaticBox, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.transcriptionStartAutomaticBox.text")); // NOI18N
        transcriptionStartAutomaticBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transcriptionStartAutomaticBoxActionPerformed(evt);
            }
        });

        minTotalIncreaseField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minTotalIncreaseField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(minTotalIncreaseLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minTotalIncreaseLabel.text")); // NOI18N

        minPercentIncreaseField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minPercentIncreaseField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(minPercentIncreaseLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minPercentIncreaseLabel.text")); // NOI18N

        additionalOptionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        maxInitialCountField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.maxInitialCountField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxInitialCountLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.maxInitialCountLabel.text")); // NOI18N

        minLowCovCountField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minLowCovCountField.text")); // NOI18N
        minLowCovCountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minLowCovCountFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(minLowCovCountLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.minLowCovCountLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addRestrictionLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.addRestrictionLabel.text")); // NOI18N

        javax.swing.GroupLayout additionalOptionPanelLayout = new javax.swing.GroupLayout(additionalOptionPanel);
        additionalOptionPanel.setLayout(additionalOptionPanelLayout);
        additionalOptionPanelLayout.setHorizontalGroup(
            additionalOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(additionalOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(additionalOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addRestrictionLabel)
                    .addGroup(additionalOptionPanelLayout.createSequentialGroup()
                        .addComponent(maxInitialCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxInitialCountLabel))
                    .addGroup(additionalOptionPanelLayout.createSequentialGroup()
                        .addComponent(minLowCovCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minLowCovCountLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        additionalOptionPanelLayout.setVerticalGroup(
            additionalOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, additionalOptionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addRestrictionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(additionalOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxInitialCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxInitialCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(additionalOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minLowCovCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minLowCovCountLabel))
                .addGap(7, 7, 7))
        );

        unannotatedTranscriptsBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(unannotatedTranscriptsBox, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.unannotatedTranscriptsBox.text")); // NOI18N
        unannotatedTranscriptsBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unannotatedTranscriptsBoxActionPerformed(evt);
            }
        });

        transcriptExtensionField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.transcriptExtensionField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(transcriptExtensionLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.transcriptExtensionLabel.text")); // NOI18N

        maxLeaderlessDistanceField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.maxLeaderlessDistanceField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(transcriptExtensionLabel1, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.transcriptExtensionLabel1.text")); // NOI18N
        transcriptExtensionLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.transcriptExtensionLabel1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(analysisDirectionLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.analysisDirectionLabel.text")); // NOI18N

        buttonGroup1.add(dirFwdRadioButton);
        dirFwdRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(dirFwdRadioButton, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.dirFwdRadioButton.text")); // NOI18N

        buttonGroup1.add(dirRevRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dirRevRadioButton, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.dirRevRadioButton.text")); // NOI18N

        maxFeatureDistField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.maxFeatureDistField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxFeatureDistLabel, org.openide.util.NbBundle.getMessage(TransAnalysesTSSVisualPanel.class, "TransAnalysesTSSVisualPanel.maxFeatureDistLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(analysisDirectionSeparator)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transcriptionStartAutomaticBox)
                            .addComponent(analysisDirectionLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dirFwdRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dirRevRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maxFeatureDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxFeatureDistLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(transcriptExtensionField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(transcriptExtensionLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minPercentIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minPercentIncreaseLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minTotalIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minTotalIncreaseLabel))
                            .addComponent(unannotatedTranscriptsBox)
                            .addComponent(additionalOptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maxLeaderlessDistanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(transcriptExtensionLabel1)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analysisDirectionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dirFwdRadioButton)
                    .addComponent(dirRevRadioButton))
                .addGap(3, 3, 3)
                .addComponent(analysisDirectionSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(transcriptionStartAutomaticBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxFeatureDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxFeatureDistLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minTotalIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minTotalIncreaseLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minPercentIncreaseLabel)
                    .addComponent(minPercentIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalOptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unannotatedTranscriptsBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transcriptExtensionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transcriptExtensionLabel))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxLeaderlessDistanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transcriptExtensionLabel1))
                .addContainerGap(127, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void transcriptionStartAutomaticBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transcriptionStartAutomaticBoxActionPerformed
        this.performTssAutoBoxAction();
    }//GEN-LAST:event_transcriptionStartAutomaticBoxActionPerformed


    /**
     * Updates the gui according to the button selection and stores the
     * tssAutomatic parameter.
     */
    private void performTssAutoBoxAction() {
        this.tssAutomatic = this.transcriptionStartAutomaticBox.isSelected();
        this.minPercentIncreaseField.setEnabled( !tssAutomatic );
        this.minTotalIncreaseField.setEnabled( !tssAutomatic );
        this.maxInitialCountField.setEnabled( !tssAutomatic );
        this.minLowCovCountField.setEnabled( !tssAutomatic );
    }

    private void unannotatedTranscriptsBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unannotatedTranscriptsBoxActionPerformed
        this.detectUnannotatedTranscripts = this.unannotatedTranscriptsBox.isSelected();
        if( this.detectUnannotatedTranscripts ) {
            this.transcriptExtensionField.setEnabled( true );
        }
        else {
            this.transcriptExtensionField.setEnabled( false );
        }
    }//GEN-LAST:event_unannotatedTranscriptsBoxActionPerformed

    private void minLowCovCountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minLowCovCountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minLowCovCountFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addRestrictionLabel;
    private javax.swing.JPanel additionalOptionPanel;
    private javax.swing.JLabel analysisDirectionLabel;
    private javax.swing.JSeparator analysisDirectionSeparator;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dirFwdRadioButton;
    private javax.swing.JRadioButton dirRevRadioButton;
    private javax.swing.JTextField maxFeatureDistField;
    private javax.swing.JLabel maxFeatureDistLabel;
    private javax.swing.JTextField maxInitialCountField;
    private javax.swing.JLabel maxInitialCountLabel;
    private javax.swing.JTextField maxLeaderlessDistanceField;
    private javax.swing.JTextField minLowCovCountField;
    private javax.swing.JLabel minLowCovCountLabel;
    private javax.swing.JTextField minPercentIncreaseField;
    private javax.swing.JLabel minPercentIncreaseLabel;
    private javax.swing.JTextField minTotalIncreaseField;
    private javax.swing.JLabel minTotalIncreaseLabel;
    private javax.swing.JTextField transcriptExtensionField;
    private javax.swing.JLabel transcriptExtensionLabel;
    private javax.swing.JLabel transcriptExtensionLabel1;
    private javax.swing.JCheckBox transcriptionStartAutomaticBox;
    private javax.swing.JCheckBox unannotatedTranscriptsBox;
    // End of variables declaration//GEN-END:variables


    /**
     * Initializes all additional stuff and components of this panel needed at
     * startup.
     */
    private void initAdditionalComponents() {

        this.minTotalIncrease = Integer.parseInt( this.minTotalIncreaseField.getText() );
        this.minTotalPercentIncrease = Integer.parseInt( this.minPercentIncreaseField.getText() );
        this.maxLowCovInitialCount = Integer.parseInt( this.maxInitialCountField.getText() );
        this.minLowCovIncrease = Integer.parseInt( this.minLowCovCountField.getText() );
        this.minTranscriptExtensionCov = Integer.parseInt( this.transcriptExtensionField.getText() );
        this.maxLeaderlessDistance = Integer.parseInt( this.maxLeaderlessDistanceField.getText() );
        this.maxFeatureDistance = Integer.parseInt( this.maxFeatureDistField.getText() );

        this.minTotalIncreaseField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.minPercentIncreaseField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.maxInitialCountField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.minLowCovCountField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.transcriptExtensionField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.maxLeaderlessDistanceField.getDocument().addDocumentListener( this.createDocumentListener() );
        this.maxFeatureDistField.getDocument().addDocumentListener( this.createDocumentListener() );

        this.loadLastParameterSelection();
    }


    /**
     * Loads the last selected parameters into the component.
     */
    private void loadLastParameterSelection() {
        Preferences pref = NbPreferences.forModule( Object.class );
        tssAutomatic = pref.get( PROP_WIZARD_NAME + PROP_AUTO_TSS_PARAMS, "0" ).equals( "1" );
        String minTotalIncreaseString = pref.get( PROP_WIZARD_NAME + PROP_MIN_TOTAL_INCREASE, minTotalIncreaseField.getText() );
        String minPercentIncreaseString = pref.get( PROP_WIZARD_NAME + PROP_MIN_PERCENT_INCREASE, minPercentIncreaseField.getText() );
        String maxInitialLowCov = pref.get( PROP_WIZARD_NAME + PROP_MAX_LOW_COV_INIT_COUNT, maxInitialCountField.getText() );
        String minInitialLowCov = pref.get( PROP_WIZARD_NAME + PROP_MIN_LOW_COV_INC, minLowCovCountField.getText() );
        detectUnannotatedTranscripts = pref.get( PROP_WIZARD_NAME + PROP_UNANNOTATED_TRANSCRIPT_DET, "1" ).equals( "1" );
        String minExtensionCov = pref.get( PROP_WIZARD_NAME + PROP_MIN_TRANSCRIPT_EXTENSION_COV, transcriptExtensionField.getText() );
        String maxLeaderlessDist = pref.get( PROP_WIZARD_NAME + PROP_MAX_LEADERLESS_DISTANCE, maxLeaderlessDistanceField.getText() );
        String maxFeatureDist = pref.get( PROP_WIZARD_NAME + PROP_MAX_FEATURE_DISTANCE, maxFeatureDistField.getText() );
        boolean fwdAnalysisDirection = pref.get( PROP_WIZARD_NAME + PROP_ANALYSIS_DIRECTION, "1" ).equals( "1" );

        transcriptionStartAutomaticBox.setSelected( tssAutomatic );
        unannotatedTranscriptsBox.setSelected( detectUnannotatedTranscripts );
        dirFwdRadioButton.setSelected( fwdAnalysisDirection );
        dirRevRadioButton.setSelected( !fwdAnalysisDirection );
        minTotalIncreaseField.setText( minTotalIncreaseString );
        minPercentIncreaseField.setText( minPercentIncreaseString );
        maxInitialCountField.setText( maxInitialLowCov );
        minLowCovCountField.setText( minInitialLowCov );
        transcriptExtensionField.setText( minExtensionCov );
        maxLeaderlessDistanceField.setText( maxLeaderlessDist );
        maxFeatureDistField.setText( maxFeatureDist );

        this.performTssAutoBoxAction();
    }


    /**
     * Checks if all required information to start the transcription start
     * analysis is set.
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = true;
        if( GeneralUtils.isValidPositiveNumberInput( minTotalIncreaseField.getText() ) ) {
            this.minTotalIncrease = Integer.parseInt( minTotalIncreaseField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidPositiveNumberInput( minPercentIncreaseField.getText() ) ) {
            this.minTotalPercentIncrease = Integer.parseInt( minPercentIncreaseField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidNumberInput( maxInitialCountField.getText() ) ) {
            this.maxLowCovInitialCount = Integer.parseInt( maxInitialCountField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidPositiveNumberInput( minLowCovCountField.getText() ) ) {
            this.minLowCovIncrease = Integer.parseInt( minLowCovCountField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidPositiveNumberInput( transcriptExtensionField.getText() ) ) {
            this.minTranscriptExtensionCov = Integer.parseInt( transcriptExtensionField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidPositiveNumberInput( maxLeaderlessDistanceField.getText() ) ) {
            this.maxLeaderlessDistance = Integer.parseInt( maxLeaderlessDistanceField.getText() );
        }
        else {
            isValidated = false;
        }
        if( GeneralUtils.isValidPositiveNumberInput( maxFeatureDistField.getText() ) ) {
            this.maxFeatureDistance = Integer.parseInt( maxFeatureDistField.getText() );
        }
        else {
            isValidated = false;
        }

        firePropertyChange( ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated );
        return isValidated;
    }


    public int getMinTotalIncrease() {
        return minTotalIncrease;
    }


    public int getMinTotalPercentIncrease() {
        return minTotalPercentIncrease;
    }


    public int getMaxLowCovInitialCount() {
        return maxLowCovInitialCount;
    }


    public int getMinLowCovIncrease() {
        return minLowCovIncrease;
    }


    public int getMinTranscriptExtensionCov() {
        return minTranscriptExtensionCov;
    }


    public int getMaxLeaderlessDistance() {
        return maxLeaderlessDistance;
    }


    public int getMaxFeatureDistance() {
        return maxFeatureDistance;
    }


    /**
     * @return true, if unannotated transcripts should be detected, false
     *         otherwise.
     */
    public boolean getDetectUnannotatedTranscripts() {
        return detectUnannotatedTranscripts;
    }


    /**
     * @return true, if the transcription start site detection's autmatic
     *         parameter estimation should be used
     */
    public boolean isTssAutomatic() {
        return tssAutomatic;
    }


    /**
     * @return <code>true</code>, if the analysis direction for the TSS is fwd,
     *         <code>false</code> if the analysis direction is rev. This means either
     *         only genes on the fwd or genes on the reverse strand can be assigned to
     *         TSS.
     */
    public boolean isFwdDirectionSelected() {
        return dirFwdRadioButton.isSelected();
    }


    /**
     * Set all components belonging to the analysis direction options visible
     * or not.
     * <p>
     * @param isVisible <code>true</code> if the components shall be visible,
     *                  <code>true</code> if not.
     */
    public void setDirectionOptionsVisible( boolean isVisible ) {
        analysisDirectionLabel.setVisible( isVisible );
        dirFwdRadioButton.setVisible( isVisible );
        dirRevRadioButton.setVisible( isVisible );
        analysisDirectionSeparator.setVisible( isVisible );
    }


}
