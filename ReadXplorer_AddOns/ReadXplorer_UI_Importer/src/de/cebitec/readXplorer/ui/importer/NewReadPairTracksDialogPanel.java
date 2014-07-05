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
package de.cebitec.readXplorer.ui.importer;

import de.cebitec.readXplorer.api.objects.NewJobDialogI;
import de.cebitec.readXplorer.parser.ReferenceJob;
import de.cebitec.readXplorer.parser.common.ParserI;
import de.cebitec.readXplorer.parser.mappings.JokToBamDirectParser;
import de.cebitec.readXplorer.parser.mappings.MappingParserI;
import de.cebitec.readXplorer.parser.mappings.SamBamParser;
import de.cebitec.readXplorer.util.fileChooser.ReadXplorerFileChooser;
import de.cebitec.readXplorer.view.dialogMenus.ImportTrackBasePanel;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;

/**
 * Panel allowing for input of two read pair mapping files.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class NewReadPairTracksDialogPanel extends ImportTrackBasePanel implements NewJobDialogI {

    private static final long serialVersionUID = 776435254;
    private List<File> mappingFiles2;
    private ReferenceJob[] refGenJobs;
    private final JokToBamDirectParser jokToBamDirectParser;
    private final SamBamParser samBamDirectParser;
    private MappingParserI[] parsers;
    private int distance; //distance of the sequences in a read pair in bp
    private short deviation; //deviation allowed from that distance in %
    private byte orientation; //0 = fr, 1 = rf, 2 = ff/rr

    /** Panel allowing for input of two read pair mapping files. */
    public NewReadPairTracksDialogPanel() {
        this.refGenJobs = getReferenceJobs();
        // choose the default parser. first entry is shown in combobox by default
        this.jokToBamDirectParser = new JokToBamDirectParser();
        this.samBamDirectParser = new SamBamParser();
        this.parsers = new MappingParserI[] { this.samBamDirectParser , jokToBamDirectParser };
        this.setCurrentParser(parsers[0]);
        mappingFiles2 = new ArrayList<>();
        initComponents();
        this.alreadyImportedBox.setVisible(true);
        this.multiTrackScrollPane.setVisible(false);
        this.multiTrackListLabel.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        refGenBox = new javax.swing.JComboBox<>(refGenJobs);
        refGenLabel = new javax.swing.JLabel();
        mappingFile1Label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        chooseButton1 = new javax.swing.JButton();
        mappingFile1Field = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        parserComboBox = new javax.swing.JComboBox<>(parsers);
        mappingFile2Label = new javax.swing.JLabel();
        mappingFile2Field = new javax.swing.JTextField();
        chooseButton2 = new javax.swing.JButton();
        distanceField = new javax.swing.JTextField();
        deviationField = new javax.swing.JTextField();
        preferencesLabel = new javax.swing.JLabel();
        distanceLabel = new javax.swing.JLabel();
        deviationLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        orientation1Button = new javax.swing.JRadioButton();
        orientation3Button = new javax.swing.JRadioButton();
        orientation2Button = new javax.swing.JRadioButton();
        alreadyImportedBox = new javax.swing.JCheckBox();
        multiTrackScrollPane = new javax.swing.JScrollPane();
        multiTrackList = new javax.swing.JList<>();
        multiTrackListLabel = new javax.swing.JLabel();

        refGenLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.refGenLabel.text")); // NOI18N

        mappingFile1Label.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile1Label.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.jLabel1.text")); // NOI18N

        chooseButton1.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.chooseButton1.text")); // NOI18N
        chooseButton1.setToolTipText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.chooseButton1.toolTipText")); // NOI18N
        chooseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButton1ActionPerformed(evt);
            }
        });

        mappingFile1Field.setEditable(false);
        mappingFile1Field.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile1Field.text")); // NOI18N
        mappingFile1Field.setToolTipText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile1Field.toolTipText")); // NOI18N

        nameLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.nameLabel.text")); // NOI18N

        parserComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                if(value instanceof ParserI){
                    return super.getListCellRendererComponent(list, ((ParserI) value).getName(), index, isSelected, cellHasFocus);
                } else {
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });
        parserComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parserComboBoxActionPerformed(evt);
            }
        });

        mappingFile2Label.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile2Label.text")); // NOI18N

        mappingFile2Field.setEditable(false);
        mappingFile2Field.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile2Field.text")); // NOI18N
        mappingFile2Field.setToolTipText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.mappingFile2Field.toolTipText")); // NOI18N

        chooseButton2.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.chooseButton2.text")); // NOI18N
        chooseButton2.setToolTipText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.chooseButton2.toolTipText")); // NOI18N
        chooseButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButton2ActionPerformed(evt);
            }
        });

        distanceField.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.distanceField.text")); // NOI18N
        distanceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distanceFieldActionPerformed(evt);
            }
        });

        deviationField.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.deviationField.text")); // NOI18N
        deviationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deviationFieldActionPerformed(evt);
            }
        });

        preferencesLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.preferencesLabel.text")); // NOI18N

        distanceLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.distanceLabel.text")); // NOI18N

        deviationLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.deviationLabel.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.jLabel2.text")); // NOI18N

        buttonGroup1.add(orientation1Button);
        orientation1Button.setSelected(true);
        orientation1Button.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.orientation1Button.text")); // NOI18N
        orientation1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientation1ButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(orientation3Button);
        orientation3Button.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.orientation3Button.text")); // NOI18N
        orientation3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientation3ButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(orientation2Button);
        orientation2Button.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.orientation2Button.text")); // NOI18N
        orientation2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientation2ButtonActionPerformed(evt);
            }
        });

        alreadyImportedBox.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.alreadyImportedBox.text")); // NOI18N
        alreadyImportedBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alreadyImportedBoxActionPerformed(evt);
            }
        });

        multiTrackScrollPane.setViewportView(multiTrackList);

        multiTrackListLabel.setText(org.openide.util.NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewReadPairTracksDialogPanel.multiTrackListLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(nameLabel)
                            .addComponent(preferencesLabel)
                            .addComponent(mappingFile2Label)
                            .addComponent(mappingFile1Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parserComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(mappingFile2Field)
                                    .addComponent(mappingFile1Field))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chooseButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chooseButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(distanceLabel)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(distanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(deviationLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deviationField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(orientation1Button)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(orientation2Button)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(orientation3Button)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(refGenLabel)
                            .addComponent(multiTrackListLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alreadyImportedBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(refGenBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(multiTrackScrollPane))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mappingFile1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseButton1)
                    .addComponent(mappingFile1Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mappingFile2Label)
                    .addComponent(mappingFile2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preferencesLabel)
                    .addComponent(deviationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(distanceLabel)
                    .addComponent(deviationLabel)
                    .addComponent(distanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(orientation3Button)
                    .addComponent(orientation1Button)
                    .addComponent(orientation2Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refGenBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refGenLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alreadyImportedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multiTrackScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(multiTrackListLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButton1ActionPerformed
        this.getMappingFiles().clear();
        this.openFileChooser(true);
}//GEN-LAST:event_chooseButton1ActionPerformed

    private void parserComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parserComboBoxActionPerformed
        MappingParserI newparser = (MappingParserI) parserComboBox.getSelectedItem();
        if (getCurrentParser() != newparser) {
            setCurrentParser(newparser);
            this.getMappingFiles().clear();
            mappingFiles2.clear();
            mappingFile1Field.setText("");
            mappingFile2Field.setText("");
            nameField.setText("");
            this.multiTrackList.setModel(new DefaultListModel<String>());
            
            if (newparser instanceof SamBamParser) {
                this.alreadyImportedBox.setEnabled(true);
            } else {
                this.alreadyImportedBox.setEnabled(false);
                this.alreadyImportedBox.setSelected(false);
                this.setIsAlreadyImported(false);
            }
        }
}//GEN-LAST:event_parserComboBoxActionPerformed

    private void chooseButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButton2ActionPerformed
        this.openFileChooser(false);
    }//GEN-LAST:event_chooseButton2ActionPerformed

    private void deviationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deviationFieldActionPerformed
        this.setDeviation();
    }//GEN-LAST:event_deviationFieldActionPerformed

    private void distanceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distanceFieldActionPerformed
        this.setDistance();
    }//GEN-LAST:event_distanceFieldActionPerformed

    private void orientation1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientation1ButtonActionPerformed
        this.setOrientation("fr");
    }//GEN-LAST:event_orientation1ButtonActionPerformed

    private void orientation3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientation3ButtonActionPerformed
        this.setOrientation("ff/rr");
    }//GEN-LAST:event_orientation3ButtonActionPerformed

    private void orientation2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientation2ButtonActionPerformed
        this.setOrientation("rf");
    }//GEN-LAST:event_orientation2ButtonActionPerformed

    private void alreadyImportedBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alreadyImportedBoxActionPerformed
        this.setIsAlreadyImported(this.alreadyImportedBox.isSelected());
        if (this.isAlreadyImported()) {
            this.chooseButton2.setEnabled(false);
            if (!this.mappingFiles2.isEmpty()) {
                String title = NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "MSG_NewSeqPairTracksDialogPanel.TrackNumberInfo.Title");
                String msg = NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "MSG_NewSeqPairTracksDialogPanel.TrackNumberInfo");
                JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE);
                this.mappingFiles2.clear();
                this.mappingFile2Field.setText("");
                DefaultListModel<String> model = new DefaultListModel<>();
                this.fillMultipleImportTable(model, getMappingFiles(), "Mapping file 1 list:");
                if (!this.isAlreadyImported()) {
                    this.fillMultipleImportTable(model, mappingFiles2, "Mapping file 2 list:");
                }
                multiTrackList.setModel(model);
            }
        } else {
            this.chooseButton2.setEnabled(true);
        }
    }//GEN-LAST:event_alreadyImportedBoxActionPerformed

    
    /**
     * Opens a file chooser for selecting a read pair mapping file.
     * @param isFstFile true, if this is the first file, false if it is the second.
     */
    private void openFileChooser(final boolean isFstFile) {
        ReadXplorerFileChooser fc = new ReadXplorerFileChooser(getCurrentParser().getFileExtensions(), getCurrentParser().getInputFileDescription()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void save(String fileLocation) {
                throw new UnsupportedOperationException("Not supported.");
            }

            @Override
            public void open(String fileLocation) {
                
                // file chosen
                updateGuiForMultipleFiles(this.getSelectedFiles().length > 1, multiTrackScrollPane, multiTrackList, multiTrackListLabel, mappingFile1Field);
                if (useMultipleImport()) {
                    File[] files = this.getSelectedFiles();
                    if (isFstFile) {
                        getMappingFiles().clear();
                    } else {
                        mappingFiles2.clear();
                    }
                    
                    for (int i = 0; i < files.length; ++i) {
                        this.addFile(files[i]);
                    }

                    mappingFile1Field.setText(getMappingFiles().size() + " tracks to import");
                    mappingFile2Field.setText(mappingFiles2.size() + " tracks to import");
                    DefaultListModel<String> model = new DefaultListModel<>();
                    fillMultipleImportTable(model, getMappingFiles(), "Mapping file 1 list:");
                    fillMultipleImportTable(model, mappingFiles2, "Mapping file 2 list:");
                    multiTrackList.setModel(model);
                    nameField.setText("Note: each track gets its file name");
                } else {
                    File file = this.getSelectedFile();
                    this.addFile(file);

//                    String path = mappingFiles1 == null ? mappingFiles2.get(mappingFiles2.size() - 1).getAbsolutePath()
//                            : mappingFiles1.get(mappingFiles1.size() - 1).getAbsolutePath();
                }
            }

            /**
             * Adds a single file to the list of mapping files.
             */
            private void addFile(File file) {
                if (file.canRead()) {
                    if (isFstFile) {
                        getMappingFiles().add(file);
                        mappingFile1Field.setText(file.getAbsolutePath());
                        nameField.setText(file.getName());
                    } else {
                        mappingFiles2.add(file);
                        mappingFile2Field.setText(file.getAbsolutePath());
                        if (getMappingFiles().isEmpty()) {
                            nameField.setText(file.getName());
                        }
                    }
                } else {
                    Logger.getLogger(NewTrackDialogPanel.class.getName()).log(Level.WARNING, "Couldn't read file");
                }
            }
        };
        
        fc.setDirectoryProperty("NewSeqPairTrack.Filepath");
        fc.setMultiSelectionEnabled(true);
        fc.openFileChooser(ReadXplorerFileChooser.OPEN_DIALOG);
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alreadyImportedBox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chooseButton1;
    private javax.swing.JButton chooseButton2;
    private javax.swing.JTextField deviationField;
    private javax.swing.JLabel deviationLabel;
    private javax.swing.JTextField distanceField;
    private javax.swing.JLabel distanceLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField mappingFile1Field;
    private javax.swing.JLabel mappingFile1Label;
    private javax.swing.JTextField mappingFile2Field;
    private javax.swing.JLabel mappingFile2Label;
    private javax.swing.JList<String> multiTrackList;
    private javax.swing.JLabel multiTrackListLabel;
    private javax.swing.JScrollPane multiTrackScrollPane;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JRadioButton orientation1Button;
    private javax.swing.JRadioButton orientation2Button;
    private javax.swing.JRadioButton orientation3Button;
    private javax.swing.JComboBox<MappingParserI> parserComboBox;
    private javax.swing.JLabel preferencesLabel;
    private javax.swing.JComboBox<ReferenceJob> refGenBox;
    private javax.swing.JLabel refGenLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return true, if all required info for this read pair track job dialog is set,
     * false otherwise.
     */
    @Override
    public boolean isRequiredInfoSet() {
        if (getMappingFiles().isEmpty() && mappingFiles2.isEmpty() 
                || refGenBox.getSelectedItem() == null 
                || nameField.getText().isEmpty() 
                || !distanceHasValidInput()  
                || !deviationHasValidInput() 
                || !mappingFiles2.isEmpty() && this.isAlreadyImported()) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * @return true, if the distance is larger than 0, false otherwise
     */
    private boolean distanceHasValidInput() {
        this.setDistance();
        return this.distance > 0;
    }
    
    /**
     * @return true, if the deviation is >= 0, false otherwise
     */
    private boolean deviationHasValidInput() {
        this.setDeviation();
        return this.deviation >= 0;
    }

    /**
     * @return The first mapping file to import, may be null, but one of both
     * files has to be set always
     */
    public File getMappingFile1() {
        if (getMappingFiles().isEmpty()) {
            return null;
        } else {
            return getMappingFiles().get(0);
        }
    }

    /**
     * @return The second mapping file to import, may be null, but one of both
     * files has to be set always
     */
    public File getMappingFile2(){
        if (mappingFiles2.isEmpty()) {
            return null;
        } else {
            return this.mappingFiles2.get(0);
        }
    }
    
    /**
     * @return The complete list of mapping files2 to import for multiple data
     * set import with the same parameters at once
     */
    public List<File> getMappingFiles2() {
        return this.mappingFiles2;
    }

    /**
     * @return The name of this track job.
     */
    @Override
    public String getTrackName() {
        return nameField.getText();
    }

    /**
     * @return The reference genome associated with this track job.
     */
    @Override
    public ReferenceJob getReferenceJob() {
        return (ReferenceJob) refGenBox.getSelectedItem();
    }

    
    /**
     * @return Distance of the sequences in a read pair in bp (from start1 to stop2)
     */
    public int getDistance(){
        return this.distance;
    }
    
    /**
     * @return deviation allowed from that distance in %
     */
    public short getDeviation(){
        return this.deviation;
    }

    /**
     * @return  orientation of the reads: 0 means read1 = fwd, read2 = rev, 
     *          1 = rev, fwd, 2 = fwd, fwd or rev, rev
     */
    public byte getOrientation() {
        return this.orientation;
    }
    
    /**
     * @param jobs list of reference jobs which shall be imported now and thus
     * have to be available for the import of new tracks too.
     */
    public void setReferenceJobs(List<ReferenceJob> jobs) {
        refGenBox.setModel(new DefaultComboBoxModel<>(this.getReferenceJobs(jobs)));
    }
    
    /**
     * sets the average distance of the read pairs.
     */
    private void setDistance(){
        try {
            this.distance = Integer.valueOf(this.distanceField.getText());
        } catch (NumberFormatException e) {
            this.distanceField.setText(NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewSeqPairTracksDialogPanel.Number.Error"));
        }
    }
    
    /**
     * Sets the maximum deviation a read pair can have in percent of the sequence
     * pair distance.
     */
    private void setDeviation(){
        try {
            this.deviation = Short.valueOf(this.deviationField.getText());
        } catch (NumberFormatException e){
            this.deviationField.setText(NbBundle.getMessage(NewReadPairTracksDialogPanel.class, "NewSeqPairTracksDialogPanel.Number.Error"));
            this.deviation = -1;
        }
    }

    /**
     * @param orientString the orientation of the read pairs: "fr", "rf",
     * "ff/rr".
     */
    private void setOrientation(String orientString) {
        switch (orientString) {
            case "fr":      this.orientation = 0; break;
            case "rf":      this.orientation = 1; break;
            case "ff/rr":   this.orientation = 2; break;
        }
    }
}
