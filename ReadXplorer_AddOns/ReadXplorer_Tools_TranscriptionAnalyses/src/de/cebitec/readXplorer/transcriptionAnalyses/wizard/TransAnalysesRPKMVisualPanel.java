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
 * Panel for showing all available options for the RPKM calculation.
 *
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class TransAnalysesRPKMVisualPanel extends JobPanel {
    
    private static final long serialVersionUID = 1L;
    private int minRPKMValue;
    private int maxRPKMValue;

    /**
     * Panel for showing all available options for the RPKM value calculation.
     */
    public TransAnalysesRPKMVisualPanel() {
        initComponents();
        initAdditionalComponents();
    }

    @Override
    public String getName() {
        return "RPKM Calculation";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minRPKMValueField = new javax.swing.JTextField();
        minRPKMValueLabel = new javax.swing.JLabel();
        maxRPKMValueField = new javax.swing.JTextField();
        maxRPKMValueLabel = new javax.swing.JLabel();

        minRPKMValueField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesRPKMVisualPanel.class, "TransAnalysesRPKMVisualPanel.minRPKMValueField.text")); // NOI18N
        minRPKMValueField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minRPKMValueFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(minRPKMValueLabel, org.openide.util.NbBundle.getMessage(TransAnalysesRPKMVisualPanel.class, "TransAnalysesRPKMVisualPanel.minRPKMValueLabel.text")); // NOI18N

        maxRPKMValueField.setText(org.openide.util.NbBundle.getMessage(TransAnalysesRPKMVisualPanel.class, "TransAnalysesRPKMVisualPanel.maxRPKMValueField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maxRPKMValueLabel, org.openide.util.NbBundle.getMessage(TransAnalysesRPKMVisualPanel.class, "TransAnalysesRPKMVisualPanel.maxRPKMValueLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(minRPKMValueField)
                    .addComponent(maxRPKMValueField, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(minRPKMValueLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxRPKMValueLabel)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minRPKMValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minRPKMValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxRPKMValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxRPKMValueLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minRPKMValueFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minRPKMValueFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minRPKMValueFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField maxRPKMValueField;
    private javax.swing.JLabel maxRPKMValueLabel;
    private javax.swing.JTextField minRPKMValueField;
    private javax.swing.JLabel minRPKMValueLabel;
    // End of variables declaration//GEN-END:variables

    private void initAdditionalComponents() {
        this.minRPKMValue = Integer.parseInt(this.minRPKMValueField.getText());
        this.maxRPKMValue = Integer.parseInt(this.maxRPKMValueField.getText());
        
        this.minRPKMValueField.getDocument().addDocumentListener(this.createDocumentListener());
        this.maxRPKMValueField.getDocument().addDocumentListener(this.createDocumentListener());
    }
    
    /**
     * Checks if all required information to start the transcription start
     * analysis is set.
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = true;
        if (GeneralUtils.isValidNumberInput(minRPKMValueField.getText())) {
            this.minRPKMValue = Integer.parseInt(minRPKMValueField.getText());
        } else {
            isValidated = false;
        }
        if (GeneralUtils.isValidPositiveNumberInput(maxRPKMValueField.getText())) {
            this.maxRPKMValue = Integer.parseInt(maxRPKMValueField.getText());
        } else {
            isValidated = false;
        }

        firePropertyChange(ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated);
        return isValidated;
    }

    public int getMaxReadCount() {
        return maxRPKMValue;
    }

    public int getMinReadCount() {
        return minRPKMValue;
    }
}
