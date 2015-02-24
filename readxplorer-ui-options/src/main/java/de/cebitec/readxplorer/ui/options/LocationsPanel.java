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
package de.cebitec.readxplorer.ui.options;

import de.cebitec.readxplorer.utils.Properties;
import de.cebitec.readxplorer.utils.filechooser.ReadXplorerFileChooser;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.openide.util.NbPreferences;

/**
 * A panel displaying options for important locations needed by ReadXplorer.
 *
 * @author Rolf Hilker <rolf.hilker at mikrobio.med.uni-giessen.de>
 */
final class LocationsPanel extends OptionsPanel {

    private static final long serialVersionUID = 1L;

    private final LocationsOptionsPanelController controller;
    private final Preferences pref;
    private String selectedDbLink;


    /**
     * A panel displaying options for important locations needed by ReadXplorer.
     * @param controller The controller
     */
    LocationsPanel( LocationsOptionsPanelController controller ) {
        this.controller = controller;
        this.pref = NbPreferences.forModule( Object.class );
        initComponents();
        initAdditionalComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }


    private void initAdditionalComponents() {
        dbOwnTextField.addFocusListener( new FocusListener() {


            @Override
            public void focusGained( FocusEvent e ) {
                //nothing to do
            }


            @Override
            public void focusLost( FocusEvent e ) {
                selectedDbLink = dbOwnTextField.getText();
                dbOwnRadioButton.setSelected( true );
                boolean isValid = valid();
                if ( !isValid ) {
                    JOptionPane.showMessageDialog( new JPanel(), "The entered text is not a valid URL!",
                                                   "URL parsing error", JOptionPane.ERROR_MESSAGE );
                }
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dbButtonGroup = new javax.swing.ButtonGroup();
        tempImportDirLabel = new javax.swing.JLabel();
        tempImportDirField = new javax.swing.JTextField();
        chooseTmpImportDirButton = new javax.swing.JButton();
        dbExpasyRadioButton = new javax.swing.JRadioButton();
        dbKeggRadioButton = new javax.swing.JRadioButton();
        dbOwnRadioButton = new javax.swing.JRadioButton();
        dbOwnTextField = new javax.swing.JTextField();
        proteinDbLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        dbBrendaRadioButton = new javax.swing.JRadioButton();
        dbEc2pdbRadioButton = new javax.swing.JRadioButton();
        dbPriamRadioButton = new javax.swing.JRadioButton();
        dbIntEnzRadioButton = new javax.swing.JRadioButton();
        dbMetaCycRadioButton = new javax.swing.JRadioButton();
        validateUrlButton = new javax.swing.JButton();
        validateUrlTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(tempImportDirLabel, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.tempImportDirLabel.text")); // NOI18N

        tempImportDirField.setEditable(false);
        tempImportDirField.setText(org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.tempImportDirField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chooseTmpImportDirButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.chooseTmpImportDirButton.text")); // NOI18N
        chooseTmpImportDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTmpImportDirButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbExpasyRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbExpasyRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbExpasyRadioButton.text")); // NOI18N
        dbExpasyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbExpasyRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbKeggRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbKeggRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbKeggRadioButton.text")); // NOI18N
        dbKeggRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbKeggRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbOwnRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbOwnRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbOwnRadioButton.text")); // NOI18N
        dbOwnRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbOwnRadioButtonActionPerformed(evt);
            }
        });

        dbOwnTextField.setText(org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbOwnTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(proteinDbLabel, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.proteinDbLabel.text")); // NOI18N

        dbButtonGroup.add(dbBrendaRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbBrendaRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbBrendaRadioButton.text")); // NOI18N
        dbBrendaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbBrendaRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbEc2pdbRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbEc2pdbRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbEc2pdbRadioButton.text")); // NOI18N
        dbEc2pdbRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbEc2pdbRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbPriamRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbPriamRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbPriamRadioButton.text")); // NOI18N
        dbPriamRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbPriamRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbIntEnzRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbIntEnzRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbIntEnzRadioButton.text")); // NOI18N
        dbIntEnzRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbIntEnzRadioButtonActionPerformed(evt);
            }
        });

        dbButtonGroup.add(dbMetaCycRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(dbMetaCycRadioButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.dbMetaCycRadioButton.text")); // NOI18N
        dbMetaCycRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbMetaCycRadioButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(validateUrlButton, org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.validateUrlButton.text")); // NOI18N
        validateUrlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateUrlButtonActionPerformed(evt);
            }
        });

        validateUrlTextField.setBackground(new java.awt.Color(240, 240, 240));
        validateUrlTextField.setText(org.openide.util.NbBundle.getMessage(LocationsPanel.class, "LocationsPanel.validateUrlTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dbOwnRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dbOwnTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tempImportDirField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooseTmpImportDirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(validateUrlButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(validateUrlTextField))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dbMetaCycRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbIntEnzRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbPriamRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbEc2pdbRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbBrendaRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbKeggRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dbExpasyRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(proteinDbLabel)
                                .addComponent(tempImportDirLabel))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tempImportDirLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempImportDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseTmpImportDirButton))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(proteinDbLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbExpasyRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbKeggRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbBrendaRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbEc2pdbRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbPriamRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbIntEnzRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbMetaCycRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dbOwnTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbOwnRadioButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validateUrlButton)
                    .addComponent(validateUrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseTmpImportDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTmpImportDirButtonActionPerformed
        ReadXplorerFileChooser fc = new ReadXplorerFileChooser( null, null ) {
            private static final long serialVersionUID = 1L;


            @Override
            public void save( String fileLocation ) {
                throw new UnsupportedOperationException( "Not supported." );
            }


            @Override
            public void open( String fileLocation ) {

                // file chosen
                tempImportDirField.setText( fileLocation );
            }


        };

        fc.setDirectoryProperty( Properties.TMP_IMPORT_DIR );
        fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        fc.openFileChooser( ReadXplorerFileChooser.OPEN_DIALOG );
    }//GEN-LAST:event_chooseTmpImportDirButtonActionPerformed

    private void dbExpasyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbExpasyRadioButtonActionPerformed
        selectedDbLink = Properties.DB_EXPASY;
    }//GEN-LAST:event_dbExpasyRadioButtonActionPerformed

    private void dbKeggRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbKeggRadioButtonActionPerformed
        selectedDbLink = Properties.DB_KEGG;
    }//GEN-LAST:event_dbKeggRadioButtonActionPerformed

    private void dbOwnRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbOwnRadioButtonActionPerformed
        selectedDbLink = dbOwnTextField.getText();
    }//GEN-LAST:event_dbOwnRadioButtonActionPerformed

    private void dbBrendaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbBrendaRadioButtonActionPerformed
        selectedDbLink = Properties.DB_BRENDA;
    }//GEN-LAST:event_dbBrendaRadioButtonActionPerformed

    private void dbEc2pdbRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbEc2pdbRadioButtonActionPerformed
        selectedDbLink = Properties.DB_EC2PDB;
    }//GEN-LAST:event_dbEc2pdbRadioButtonActionPerformed

    private void dbPriamRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbPriamRadioButtonActionPerformed
        selectedDbLink = Properties.DB_PRIAM;
    }//GEN-LAST:event_dbPriamRadioButtonActionPerformed

    private void dbIntEnzRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbIntEnzRadioButtonActionPerformed
        selectedDbLink = Properties.DB_INTENZ;
    }//GEN-LAST:event_dbIntEnzRadioButtonActionPerformed

    private void dbMetaCycRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbMetaCycRadioButtonActionPerformed
        selectedDbLink = Properties.DB_METACYC;
    }//GEN-LAST:event_dbMetaCycRadioButtonActionPerformed

    private void validateUrlButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateUrlButtonActionPerformed
        boolean isValid = valid();
        if ( isValid ) {
            validateUrlTextField.setBackground( Color.GREEN );
        } else {
            validateUrlTextField.setBackground( Color.RED );
        }
        validateUrlTextField.setText( isValid ? "The currently selected URL is valid!" : "The currently selected URL is NOT valid!");
    }//GEN-LAST:event_validateUrlButtonActionPerformed


    @Override
    void load() {
        tempImportDirField.setText( pref.get( Properties.TMP_IMPORT_DIR, System.getProperty( "java.io.tmpdir" ) ) );
        selectedDbLink = pref.get( Properties.PROTEIN_DB_LINK, Properties.DB_EXPASY );
        setDbButtonSelected();
    }


    /**
     * Identify the currently selected protein DB link and select the
     * corresponding radio button.
     */
    private void setDbButtonSelected() {
        dbExpasyRadioButton.setSelected( Properties.DB_EXPASY.equals( selectedDbLink ) );
        dbBrendaRadioButton.setSelected( Properties.DB_BRENDA.equals( selectedDbLink ) );
        dbEc2pdbRadioButton.setSelected( Properties.DB_EC2PDB.equals( selectedDbLink ) );
        dbIntEnzRadioButton.setSelected( Properties.DB_INTENZ.equals( selectedDbLink ) );
        dbKeggRadioButton.setSelected( Properties.DB_KEGG.equals( selectedDbLink ) );
        dbMetaCycRadioButton.setSelected( Properties.DB_METACYC.equals( selectedDbLink ) );
        dbPriamRadioButton.setSelected( Properties.DB_PRIAM.equals( selectedDbLink ) );
        if (dbButtonGroup.getSelection() == null) {
            dbOwnRadioButton.setSelected( true );
            dbOwnTextField.setText( selectedDbLink );
        }
    }


    @Override
    void store() {
        if (dbOwnRadioButton.isSelected()) {
            selectedDbLink = dbOwnTextField.getText();
        }

        pref.put( Properties.TMP_IMPORT_DIR, tempImportDirField.getText() );
        pref.put( Properties.PROTEIN_DB_LINK, selectedDbLink );
    }


    @Override
    public boolean valid() {
        boolean isValid = false;
        try {
            URL url = new URL( selectedDbLink );
            isValid = true;
        } catch( MalformedURLException ex ) {
//            selectedDbLink = Properties.DB_EXPASY;
        }
        return isValid;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseTmpImportDirButton;
    private javax.swing.JRadioButton dbBrendaRadioButton;
    private javax.swing.ButtonGroup dbButtonGroup;
    private javax.swing.JRadioButton dbEc2pdbRadioButton;
    private javax.swing.JRadioButton dbExpasyRadioButton;
    private javax.swing.JRadioButton dbIntEnzRadioButton;
    private javax.swing.JRadioButton dbKeggRadioButton;
    private javax.swing.JRadioButton dbMetaCycRadioButton;
    private javax.swing.JRadioButton dbOwnRadioButton;
    private javax.swing.JTextField dbOwnTextField;
    private javax.swing.JRadioButton dbPriamRadioButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel proteinDbLabel;
    private javax.swing.JTextField tempImportDirField;
    private javax.swing.JLabel tempImportDirLabel;
    private javax.swing.JButton validateUrlButton;
    private javax.swing.JTextField validateUrlTextField;
    // End of variables declaration//GEN-END:variables
}
