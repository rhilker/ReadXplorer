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

package de.cebitec.readxplorer.ui.options;


import java.io.File;
import javax.swing.JFileChooser;
import org.openide.util.NbPreferences;


final class ExternalProgramsPanel extends javax.swing.JPanel {

    private final ExternalProgramsOptionsPanelController controller;
    public static final String BIOPROSPECTOR_LOCATION = "bioprospector location";
    public static final String SEQLOGO_LOCATION = "seqlogo location";
    private JFileChooser fc;


    ExternalProgramsPanel( ExternalProgramsOptionsPanelController controller ) {
        this.controller = controller;
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

        jLabel1 = new javax.swing.JLabel();
        bioProspectorLocationTF = new javax.swing.JTextField();
        bioProspectorLocationButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        seqLogoLocationTF = new javax.swing.JTextField();
        seqLogoLocationButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.jLabel1.text")); // NOI18N

        bioProspectorLocationTF.setText(org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.bioProspectorLocationTF.text")); // NOI18N
        bioProspectorLocationTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bioProspectorLocationTFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(bioProspectorLocationButton, org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.bioProspectorLocationButton.text")); // NOI18N
        bioProspectorLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bioProspectorLocationButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.jLabel2.text")); // NOI18N

        seqLogoLocationTF.setText(org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.seqLogoLocationTF.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(seqLogoLocationButton, org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.seqLogoLocationButton.text")); // NOI18N
        seqLogoLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seqLogoLocationButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ExternalProgramsPanel.class, "ExternalProgramsPanel.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bioProspectorLocationTF, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(seqLogoLocationTF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seqLogoLocationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bioProspectorLocationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bioProspectorLocationTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bioProspectorLocationButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(seqLogoLocationTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seqLogoLocationButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bioProspectorLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bioProspectorLocationButtonActionPerformed
        fc = new JFileChooser();

        int returnVal = fc.showOpenDialog( this );

        if( returnVal == JFileChooser.APPROVE_OPTION ) {
            if( fc.getSelectedFile().canExecute() ) {
                this.bioProspectorLocationTF.setText( fc.getSelectedFile().getAbsolutePath() );
            }
        }

    }//GEN-LAST:event_bioProspectorLocationButtonActionPerformed

    private void seqLogoLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seqLogoLocationButtonActionPerformed
        fc = new JFileChooser();

        int returnVal = fc.showOpenDialog( this );

        if( returnVal == JFileChooser.APPROVE_OPTION ) {
            if( fc.getSelectedFile().canExecute() ) {
                this.seqLogoLocationTF.setText( fc.getSelectedFile().getAbsolutePath() );
            }
        }
    }//GEN-LAST:event_seqLogoLocationButtonActionPerformed

    private void bioProspectorLocationTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bioProspectorLocationTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bioProspectorLocationTFActionPerformed


    void load() {
        this.seqLogoLocationTF.setText( NbPreferences.forModule( Object.class ).get( ExternalProgramsPanel.SEQLOGO_LOCATION, "" ) );
        this.bioProspectorLocationTF.setText( NbPreferences.forModule( Object.class ).get( ExternalProgramsPanel.BIOPROSPECTOR_LOCATION, "" ) );
    }


    void store() {
        NbPreferences.forModule( Object.class ).put( ExternalProgramsPanel.BIOPROSPECTOR_LOCATION, this.bioProspectorLocationTF.getText() );
        NbPreferences.forModule( Object.class ).put( ExternalProgramsPanel.SEQLOGO_LOCATION, this.seqLogoLocationTF.getText() );
    }


    boolean valid() {
        // TODO check whether form is consistent and complete
        File bioPros = new File( this.bioProspectorLocationTF.getText() );
        boolean bioProsCheck = false;
        File seqlogo = new File( this.seqLogoLocationTF.getText() );
        boolean seqLogoCheck = false;
        if( bioPros.exists() && bioPros.isFile() && bioPros.canExecute() ) {
            bioProsCheck = true;
        }
        if( seqlogo.exists() && seqlogo.isFile() && seqlogo.canExecute() ) {
            seqLogoCheck = true;
        }
        return bioProsCheck && seqLogoCheck;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bioProspectorLocationButton;
    private javax.swing.JTextField bioProspectorLocationTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton seqLogoLocationButton;
    private javax.swing.JTextField seqLogoLocationTF;
    // End of variables declaration//GEN-END:variables
}
