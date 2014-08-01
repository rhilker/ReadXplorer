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
package de.cebitec.readXplorer.transcriptionAnalyses.wizard;

import de.cebitec.readXplorer.api.objects.JobPanel;
import de.cebitec.readXplorer.util.GeneralUtils;
import de.cebitec.readXplorer.view.dialogMenus.ChangeListeningWizardPanel;

/**
 * Panel for showing all available options for the operon detection.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public final class TransAnalysesOperonVisualPanel extends JobPanel {
   
    private static final long serialVersionUID = 1L;
    
    private int minSpanningReads;
    private boolean isAutoParamEstimation;

    /**
     * Panel for showing all available options for the operon detection.
     */
    public TransAnalysesOperonVisualPanel() {
        initComponents();
        this.initAdditionalComponents();
    }

    @Override
    public String getName() {
        return "Operon Detection Parameters";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        operonDetectionAutomaticBox = new javax.swing.JCheckBox();
        spanningReadsLabel = new javax.swing.JLabel();
        spanningReadsField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(operonDetectionAutomaticBox, org.openide.util.NbBundle.getMessage(TransAnalysesOperonVisualPanel.class, "TransAnalysesOperonVisualPanel.operonDetectionAutomaticBox.text")); // NOI18N
        operonDetectionAutomaticBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operonDetectionAutomaticBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(spanningReadsLabel, org.openide.util.NbBundle.getMessage(TransAnalysesOperonVisualPanel.class, "TransAnalysesOperonVisualPanel.spanningReadsLabel.text")); // NOI18N

        spanningReadsField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesOperonVisualPanel.class, "TransAnalysesOperonVisualPanel.spanningReadsField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spanningReadsField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spanningReadsLabel))
                    .addComponent(operonDetectionAutomaticBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operonDetectionAutomaticBox)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spanningReadsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spanningReadsLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void operonDetectionAutomaticBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operonDetectionAutomaticBoxActionPerformed
        this.isAutoParamEstimation = this.operonDetectionAutomaticBox.isSelected();
        if (this.isAutoParamEstimation) {
            this.spanningReadsField.setEnabled(false);
        } else {
            this.spanningReadsField.setEnabled(true);
        }
    }//GEN-LAST:event_operonDetectionAutomaticBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox operonDetectionAutomaticBox;
    private javax.swing.JTextField spanningReadsField;
    private javax.swing.JLabel spanningReadsLabel;
    // End of variables declaration//GEN-END:variables

    private void initAdditionalComponents() {
        this.minSpanningReads = Integer.parseInt(this.spanningReadsField.getText());
        this.spanningReadsField.getDocument().addDocumentListener(this.createDocumentListener());
    }
    
    /**
     * Checks if all required information to start the transcription start
     * analysis is set.
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = true;
        if (GeneralUtils.isValidPositiveNumberInput(spanningReadsField.getText())) {
            this.minSpanningReads = Integer.parseInt(spanningReadsField.getText());
        } else {
            isValidated = false;
        }

        firePropertyChange(ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated);
        return isValidated;
    }

    /**
     * @return the minimum number of spanning reads between two features
     * to assign them to an operon
     */
    public int getMinSpanningReads() {
        return this.minSpanningReads;
    }

    /**
     * @return true, if the operon detection's autmatic  parameter estimation 
     * should be used
     */
    public boolean isOperonAutomatic() {
        return this.isAutoParamEstimation;
    }
    

}
