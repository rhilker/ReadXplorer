/*
 * Copyright (C) 2015 Institute for Bioinformatics and Systems Biology, University Giessen, Germany
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

package bio.comp.jlu.readxplorer.tools.gasv.wizard;

import bio.comp.jlu.readxplorer.tools.gasv.ParametersGASVMain;
import de.cebitec.readxplorer.api.objects.JobPanel;
import de.cebitec.readxplorer.ui.dialogmenus.ChangeListeningWizardPanel;
import de.cebitec.readxplorer.utils.GeneralUtils;


/**
 * Creates a new panel to configure the GASVMain options.
 * <p>
 * @author Rolf Hilker <rolf.hilker at mikrobio.med.uni-giessen.de>
 */
public final class GASVMainVisualPanel extends JobPanel {

    private static final long serialVersionUID = 1L;

    private ParametersGASVMain params;


    /**
     * Creates a new panel to configure the GASVMain options.
     * <p>
     * @param gASVMainParams Initial GASVMain parameter set.
     */
    public GASVMainVisualPanel( ParametersGASVMain gASVMainParams ) {
        this.params = gASVMainParams;
        initComponents();

        minClusterSizeField.getDocument().addDocumentListener( createDocumentListener() );
        maxClusterSizeField.getDocument().addDocumentListener( createDocumentListener() );
        maxCliqueSizeField.getDocument().addDocumentListener( createDocumentListener() );
        maxReadPairsField.getDocument().addDocumentListener( createDocumentListener() );
    }


    /**
     * @return The current parameter selection of the panel.
     */
    public ParametersGASVMain getGASVMainParams() {
        return params;
    }


    /**
     * The parameter selection to use for the initial state of the panel.
     * <p>
     * @param gasvMainParams The last parameter selection.
     */
    public void setLastParameterSelection( ParametersGASVMain gasvMainParams ) {
        updateComponentsForParams( gasvMainParams );
        this.params = gasvMainParams;
    }


    /**
     * Updates all components according to the currently set parameter values.
     * <p>
     * @param gasvMainParams The GASVMain parameter set to apply.
     */
    private void updateComponentsForParams( ParametersGASVMain gasvMainParams ) {
        minClusterSizeField.setText( String.valueOf( gasvMainParams.getMinClusterSize() ) );
        maxClusterSizeField.setText( String.valueOf( gasvMainParams.getMaxClusterSize() ) );
        maxReadPairsField.setText( String.valueOf( gasvMainParams.getMaxReadPairs() ) );
        maxCliqueSizeField.setText( String.valueOf( gasvMainParams.getMaxCliqueSize() ) );

        switch( gasvMainParams.getOutputType() ) {
            case ParametersGASVMain.OUT_READS:
                outputReadsButton.setSelected( true );
                break;
            case ParametersGASVMain.OUT_REGIONS:
                outputRegionsButton.setSelected( true );
                break;
            case ParametersGASVMain.OUT_STANDARD: //fallthrough to default
            default:
                outputStandardButton.setSelected( true );
        }

        maxSubClustersBox.setSelected( gasvMainParams.isMaxSubClusters() );
        nonreciprocalBox.setSelected( gasvMainParams.isNonreciprocal() );
        headerlessBox.setSelected( gasvMainParams.isHeaderless() );
        fasterBox.setSelected( gasvMainParams.isFast() );
        verboseBox.setSelected( gasvMainParams.isVerbose() );
        debugBox.setSelected( gasvMainParams.isDebug() );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "GASVMain configuration";
    }


    /** This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerlessBox = new javax.swing.JCheckBox();
        verboseBox = new javax.swing.JCheckBox();
        debugBox = new javax.swing.JCheckBox();
        fasterBox = new javax.swing.JCheckBox();
        minClusterSizeLabel = new javax.swing.JLabel();
        minClusterSizeField = new javax.swing.JTextField();
        maxClusterSizeLabel = new javax.swing.JLabel();
        maxClusterSizeField = new javax.swing.JTextField();
        maxCliqueSizeLabel = new javax.swing.JLabel();
        maxCliqueSizeField = new javax.swing.JTextField();
        maxReadPairsLabel = new javax.swing.JLabel();
        maxReadPairsField = new javax.swing.JTextField();
        maxSubClustersBox = new javax.swing.JCheckBox();
        outputModeLabel = new javax.swing.JLabel();
        outputStandardButton = new javax.swing.JRadioButton();
        outputReadsButton = new javax.swing.JRadioButton();
        outputRegionsButton = new javax.swing.JRadioButton();
        nonreciprocalBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        org.openide.awt.Mnemonics.setLocalizedText(headerlessBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.headerlessBox.text")); // NOI18N
        headerlessBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headerlessBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(verboseBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.verboseBox.text")); // NOI18N
        verboseBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verboseBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(debugBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.debugBox.text")); // NOI18N
        debugBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(fasterBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.fasterBox.text")); // NOI18N
        fasterBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fasterBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(minClusterSizeLabel, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.minClusterSizeLabel.text")); // NOI18N

        minClusterSizeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        minClusterSizeField.setText(org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.minClusterSizeField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxClusterSizeLabel, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxClusterSizeLabel.text")); // NOI18N

        maxClusterSizeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        maxClusterSizeField.setText(org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxClusterSizeField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxCliqueSizeLabel, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxCliqueSizeLabel.text")); // NOI18N

        maxCliqueSizeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        maxCliqueSizeField.setText(org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxCliqueSizeField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxReadPairsLabel, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxReadPairsLabel.text")); // NOI18N

        maxReadPairsField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        maxReadPairsField.setText(org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxReadPairsField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxSubClustersBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.maxSubClustersBox.text")); // NOI18N
        maxSubClustersBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxSubClustersBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(outputModeLabel, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.outputModeLabel.text")); // NOI18N

        buttonGroup1.add(outputStandardButton);
        outputStandardButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(outputStandardButton, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.outputStandardButton.text")); // NOI18N
        outputStandardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputStandardButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(outputReadsButton);
        org.openide.awt.Mnemonics.setLocalizedText(outputReadsButton, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.outputReadsButton.text")); // NOI18N
        outputReadsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputReadsButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(outputRegionsButton);
        org.openide.awt.Mnemonics.setLocalizedText(outputRegionsButton, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.outputRegionsButton.text")); // NOI18N
        outputRegionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputRegionsButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(nonreciprocalBox, org.openide.util.NbBundle.getMessage(GASVMainVisualPanel.class, "GASVMainVisualPanel.nonreciprocalBox.text")); // NOI18N
        nonreciprocalBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonreciprocalBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(minClusterSizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minClusterSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(maxClusterSizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maxClusterSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(maxCliqueSizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maxCliqueSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(maxReadPairsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maxReadPairsField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verboseBox)
                            .addComponent(nonreciprocalBox)
                            .addComponent(outputRegionsButton)
                            .addComponent(outputReadsButton)
                            .addComponent(fasterBox)
                            .addComponent(debugBox)
                            .addComponent(maxSubClustersBox)
                            .addComponent(outputModeLabel)
                            .addComponent(outputStandardButton)
                            .addComponent(headerlessBox))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minClusterSizeLabel)
                    .addComponent(minClusterSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxClusterSizeLabel)
                    .addComponent(maxClusterSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxCliqueSizeLabel)
                    .addComponent(maxCliqueSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxReadPairsLabel)
                    .addComponent(maxReadPairsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(outputModeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputStandardButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputReadsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputRegionsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxSubClustersBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nonreciprocalBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerlessBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fasterBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verboseBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debugBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void maxSubClustersBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxSubClustersBoxActionPerformed
        params.setIsMaxSubClusters( maxSubClustersBox.isSelected() );
    }//GEN-LAST:event_maxSubClustersBoxActionPerformed

    private void nonreciprocalBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonreciprocalBoxActionPerformed
        params.setIsNonreciprocal( nonreciprocalBox.isSelected() );
    }//GEN-LAST:event_nonreciprocalBoxActionPerformed

    private void headerlessBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headerlessBoxActionPerformed
        params.setIsHeaderless( headerlessBox.isSelected() );
    }//GEN-LAST:event_headerlessBoxActionPerformed

    private void fasterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fasterBoxActionPerformed
        params.setIsFast( fasterBox.isSelected() );
    }//GEN-LAST:event_fasterBoxActionPerformed

    private void verboseBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verboseBoxActionPerformed
        params.setIsVerbose( verboseBox.isSelected() );
    }//GEN-LAST:event_verboseBoxActionPerformed

    private void debugBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugBoxActionPerformed
        params.setIsDebug( debugBox.isSelected() );
    }//GEN-LAST:event_debugBoxActionPerformed

    private void outputStandardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputStandardButtonActionPerformed
        if( outputStandardButton.isSelected() ) {
            params.setOutputType( ParametersGASVMain.OUT_STANDARD );
        }
    }//GEN-LAST:event_outputStandardButtonActionPerformed

    private void outputReadsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputReadsButtonActionPerformed
        if( outputReadsButton.isSelected() ) {
            params.setOutputType( ParametersGASVMain.OUT_READS );
        }
    }//GEN-LAST:event_outputReadsButtonActionPerformed

    private void outputRegionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputRegionsButtonActionPerformed
        if( outputRegionsButton.isSelected() ) {
            params.setOutputType( ParametersGASVMain.OUT_REGIONS );
        }
    }//GEN-LAST:event_outputRegionsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox debugBox;
    private javax.swing.JCheckBox fasterBox;
    private javax.swing.JCheckBox headerlessBox;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField maxCliqueSizeField;
    private javax.swing.JLabel maxCliqueSizeLabel;
    private javax.swing.JTextField maxClusterSizeField;
    private javax.swing.JLabel maxClusterSizeLabel;
    private javax.swing.JTextField maxReadPairsField;
    private javax.swing.JLabel maxReadPairsLabel;
    private javax.swing.JCheckBox maxSubClustersBox;
    private javax.swing.JTextField minClusterSizeField;
    private javax.swing.JLabel minClusterSizeLabel;
    private javax.swing.JCheckBox nonreciprocalBox;
    private javax.swing.JLabel outputModeLabel;
    private javax.swing.JRadioButton outputReadsButton;
    private javax.swing.JRadioButton outputRegionsButton;
    private javax.swing.JRadioButton outputStandardButton;
    private javax.swing.JCheckBox verboseBox;
    // End of variables declaration//GEN-END:variables


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = true;

        if( GeneralUtils.isValidIntegerInput( minClusterSizeField.getText() ) &&
            GeneralUtils.isValidIntegerInput( maxClusterSizeField.getText() ) &&
            GeneralUtils.isValidIntegerInput( maxCliqueSizeField.getText() ) &&
            GeneralUtils.isValidIntegerInput( maxReadPairsField.getText() ) ) {

            params.setMinClusterSize( Integer.parseInt( minClusterSizeField.getText() ) );
            params.setMaxClusterSize( Integer.parseInt( maxClusterSizeField.getText() ) );
            params.setMaxCliqueSize( Integer.parseInt( maxCliqueSizeField.getText() ) );
            params.setMaxReadPairs( Integer.parseInt( maxReadPairsField.getText() ) );

        } else {
            isValidated = false;
        }

        firePropertyChange( ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated );
        return isValidated;
    }


}
