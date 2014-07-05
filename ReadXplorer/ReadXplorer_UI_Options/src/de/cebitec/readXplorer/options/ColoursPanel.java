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
package de.cebitec.readXplorer.options;

import de.cebitec.readXplorer.util.ColorProperties;
import java.awt.Color;
import javax.swing.JColorChooser;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbPreferences;

final class ColoursPanel extends OptionsPanel {

    private static final long serialVersionUID = 1L;
    private final ColoursOptionsPanelController controller;

    ColoursPanel(ColoursOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uniformColorationCheckBox = new javax.swing.JCheckBox();
        bestMatchLabel = new javax.swing.JLabel();
        commonMatchButton = new javax.swing.JButton();
        commonMatchLabel = new javax.swing.JLabel();
        perfectMatchButton = new javax.swing.JButton();
        perfectMatchLabel = new javax.swing.JLabel();
        uniformColButton = new javax.swing.JButton();
        bestMatchButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        uniformColorLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(uniformColorationCheckBox, org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.uniformColorationCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(bestMatchLabel, org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.bestMatchLabel.text")); // NOI18N

        commonMatchButton.setBackground(new java.awt.Color(255, 51, 0));
        commonMatchButton.setToolTipText(org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.commonMatchButton.toolTipText")); // NOI18N
        commonMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commonMatchButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(commonMatchLabel, org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.commonMatchLabel.text")); // NOI18N

        perfectMatchButton.setBackground(new java.awt.Color(0, 204, 0));
        perfectMatchButton.setToolTipText(org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.perfectMatchButton.toolTipText")); // NOI18N
        perfectMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfectMatchButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(perfectMatchLabel, org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.perfectMatchLabel.text")); // NOI18N

        uniformColButton.setBackground(new java.awt.Color(51, 51, 255));
        uniformColButton.setToolTipText(org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.uniformColButton.toolTipText")); // NOI18N
        uniformColButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniformColButtonActionPerformed(evt);
            }
        });

        bestMatchButton.setBackground(new java.awt.Color(255, 255, 0));
        bestMatchButton.setToolTipText(org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.bestMatchButton.toolTipText")); // NOI18N
        bestMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bestMatchButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(uniformColorLabel, org.openide.util.NbBundle.getMessage(ColoursPanel.class, "ColoursPanel.uniformColorLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(commonMatchLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(perfectMatchLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bestMatchLabel))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commonMatchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(bestMatchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(perfectMatchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uniformColorLabel)
                        .addGap(59, 59, 59)
                        .addComponent(uniformColButton, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uniformColorationCheckBox)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(perfectMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bestMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(perfectMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bestMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commonMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commonMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uniformColorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(uniformColButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uniformColorationCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void commonMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commonMatchButtonActionPerformed
        displayColorChooserForButton(commonMatchButton);
}//GEN-LAST:event_commonMatchButtonActionPerformed

    private void perfectMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfectMatchButtonActionPerformed
        displayColorChooserForButton(perfectMatchButton);
}//GEN-LAST:event_perfectMatchButtonActionPerformed

    private void uniformColButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniformColButtonActionPerformed
        displayColorChooserForButton(uniformColButton);
}//GEN-LAST:event_uniformColButtonActionPerformed

    private void bestMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bestMatchButtonActionPerformed
        displayColorChooserForButton(bestMatchButton);
}//GEN-LAST:event_bestMatchButtonActionPerformed

    void load() {
        // read settings and initialize GUI
        String bestMatchRGB = NbPreferences.forModule(Object.class).get("bestMatchColour", "");
        if (bestMatchRGB.isEmpty()){
            bestMatchButton.setBackground(ColorProperties.BEST_MATCH);
        }
        else {
            bestMatchButton.setBackground(new Color(Integer.parseInt(bestMatchRGB)));
        }
        String commonMatchRGB = NbPreferences.forModule(Object.class).get("commonMatchColour", "");
        if (commonMatchRGB.isEmpty()){
            commonMatchButton.setBackground(ColorProperties.COMMON_MATCH);
        }
        else {
            commonMatchButton.setBackground(new Color(Integer.parseInt(commonMatchRGB)));
        }
        String perfectMatchRGB = NbPreferences.forModule(Object.class).get("perfectMatchColour", "");
        if (perfectMatchRGB.isEmpty()){
            perfectMatchButton.setBackground(ColorProperties.PERFECT_MATCH);
        }
        else {
            perfectMatchButton.setBackground(new Color(Integer.parseInt(perfectMatchRGB)));
        }
        String uniformRGB = NbPreferences.forModule(Object.class).get("uniformColour", "");
        if (uniformRGB.isEmpty()){
            uniformColButton.setBackground(Color.BLUE);
        }
        else {
            uniformColButton.setBackground(new Color(Integer.parseInt(uniformRGB)));
        }
        uniformColorationCheckBox.setSelected(NbPreferences.forModule(Object.class).getBoolean("uniformDesired", false));
    }

    void store() {
        // store modified settings
        NbPreferences.forModule(Object.class).put("bestMatchColour", Integer.toString(bestMatchButton.getBackground().getRGB()));
        NbPreferences.forModule(Object.class).put("commonMatchColour", Integer.toString(commonMatchButton.getBackground().getRGB()));
        NbPreferences.forModule(Object.class).put("perfectMatchColour", Integer.toString(perfectMatchButton.getBackground().getRGB()));
        NbPreferences.forModule(Object.class).put("uniformColour", Integer.toString(uniformColButton.getBackground().getRGB()));
        NbPreferences.forModule(Object.class).putBoolean("uniformDesired", uniformColorationCheckBox.isSelected());
    }

    private void displayColorChooserForButton(javax.swing.JButton button){
        JColorChooser colChooser = new JColorChooser();
        DialogDescriptor d =  new DialogDescriptor(colChooser,"Choose a color");
        DialogDisplayer.getDefault().createDialog(d).setVisible(true);
        if(d.getValue().equals(DialogDescriptor.OK_OPTION)){
            button.setBackground(colChooser.getColor());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bestMatchButton;
    private javax.swing.JLabel bestMatchLabel;
    private javax.swing.JButton commonMatchButton;
    private javax.swing.JLabel commonMatchLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton perfectMatchButton;
    private javax.swing.JLabel perfectMatchLabel;
    private javax.swing.JButton uniformColButton;
    private javax.swing.JLabel uniformColorLabel;
    private javax.swing.JCheckBox uniformColorationCheckBox;
    // End of variables declaration//GEN-END:variables
}
