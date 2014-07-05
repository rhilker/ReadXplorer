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

import de.cebitec.readXplorer.util.Properties;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

final class ViewerPanel extends OptionsPanel {
    private static final long serialVersionUID = 1L;

    private final ViewerOptionsPanelController controller;
    private final Preferences pref;
    

    ViewerPanel(ViewerOptionsPanelController controller) {
        this.controller = controller;
        this.pref = NbPreferences.forModule(Object.class);
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heightButtonGroup = new javax.swing.ButtonGroup();
        descriptionLabel = new javax.swing.JLabel();
        heightTinyRadioButton = new javax.swing.JRadioButton();
        heightMediumRadioButton = new javax.swing.JRadioButton();
        heightMaxRadioButton = new javax.swing.JRadioButton();
        autoScalingBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(descriptionLabel, org.openide.util.NbBundle.getMessage(ViewerPanel.class, "ViewerPanel.descriptionLabel.text")); // NOI18N

        heightButtonGroup.add(heightTinyRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(heightTinyRadioButton, org.openide.util.NbBundle.getMessage(ViewerPanel.class, "ViewerPanel.heightTinyRadioButton.text")); // NOI18N

        heightButtonGroup.add(heightMediumRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(heightMediumRadioButton, org.openide.util.NbBundle.getMessage(ViewerPanel.class, "ViewerPanel.heightMediumRadioButton.text")); // NOI18N

        heightButtonGroup.add(heightMaxRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(heightMaxRadioButton, org.openide.util.NbBundle.getMessage(ViewerPanel.class, "ViewerPanel.heightMaxRadioButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(autoScalingBox, org.openide.util.NbBundle.getMessage(ViewerPanel.class, "ViewerPanel.autoScalingBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(autoScalingBox)
                    .addComponent(heightMaxRadioButton)
                    .addComponent(heightMediumRadioButton)
                    .addComponent(heightTinyRadioButton)
                    .addComponent(descriptionLabel))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heightTinyRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heightMediumRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heightMaxRadioButton)
                .addGap(18, 18, 18)
                .addComponent(autoScalingBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    void load() {
        int height = pref.getInt(Properties.VIEWER_HEIGHT, Properties.DEFAULT_HEIGHT);
        switch (height) {
            case Properties.MAX_HEIGHT :    
                heightMaxRadioButton.setSelected(true);     break;
            case Properties.SMALL_HEIGHT :  
                heightTinyRadioButton.setSelected(true);    break;
            default :            
                heightMediumRadioButton.setSelected(true);  break;
        }
        this.autoScalingBox.setSelected(pref.getBoolean(Properties.VIEWER_AUTO_SCALING, false));
    }

    @Override
    void store() {
        int height;
        if (heightMaxRadioButton.isSelected()) {
            height = Properties.MAX_HEIGHT;
        } else if (heightMediumRadioButton.isSelected()) {
            height = Properties.DEFAULT_HEIGHT;
        } else {
            height = Properties.SMALL_HEIGHT;
        }
        pref.putInt(Properties.VIEWER_HEIGHT, height);
        pref.putBoolean(Properties.VIEWER_AUTO_SCALING, this.autoScalingBox.isSelected());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoScalingBox;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.ButtonGroup heightButtonGroup;
    private javax.swing.JRadioButton heightMaxRadioButton;
    private javax.swing.JRadioButton heightMediumRadioButton;
    private javax.swing.JRadioButton heightTinyRadioButton;
    // End of variables declaration//GEN-END:variables
}
