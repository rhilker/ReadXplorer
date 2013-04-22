/*
 * NewPositionTableDialog.java
 *
 * Created on 07.12.2011, 11:50:08
 */
package de.cebitec.vamp.ui.importer;

import de.cebitec.vamp.api.objects.NewJobDialogI;
import de.cebitec.vamp.databackend.connector.ProjectConnector;
import de.cebitec.vamp.databackend.connector.ReferenceConnector;
import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.parser.ReferenceJob;
import de.cebitec.vamp.parser.TrackJob;
import de.cebitec.vamp.parser.common.ParserI;
import de.cebitec.vamp.parser.mappings.JokParser;
import de.cebitec.vamp.parser.mappings.MappingParserI;
import de.cebitec.vamp.parser.mappings.SamBamParser;
import de.cebitec.vamp.parser.mappings.SamBamStepParser;
import de.cebitec.vamp.util.fileChooser.VampFileChooser;
import de.cebitec.vamp.view.dialogMenus.ImportTrackBasePanel;
import java.awt.Component;
import java.util.List;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

/**
 * The dialog panel for importing only position tables for an existing track.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class NewPositionTableDialog extends ImportTrackBasePanel implements NewJobDialogI {

    private static final long serialVersionUID = 774275254;
    private ReferenceJob[] refGenJobs;
    private MappingParserI[] parsers = new MappingParserI[]{new JokParser(), new SamBamParser(), new SamBamStepParser()};
    private int stepSize = 0;
    private static final int maxVal = 1000000000;
    private static final int minVal = 10000;
    private static final int step = 1000;
    private static final int defaultVal = 300000;

    
    /** The dialog panel for importing only position tables for an existing track. */
    public NewPositionTableDialog() {
        this.refGenJobs = this.getReferenceJobs();
        this.initComponents();
        // choose the default parser. first entry is shown in combobox by default
        this.setTrackJobs((ReferenceJob) this.refGenCombo.getSelectedItem());
        setCurrentParser(parsers[0]);
        this.setStepwiseField(false);
        stepSizeSpinner.setModel(new SpinnerNumberModel(defaultVal, minVal, maxVal, step));
        JFormattedTextField txt = ((JSpinner.NumberEditor) stepSizeSpinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    @Override
    public boolean isRequiredInfoSet() {
        if (getMappingFile() == null || refGenCombo.getSelectedItem() == null || parentTrackCombo.getSelectedItem() == null) {
            return false;
        } else {
            return true;
        }
    }

    
    public TrackJob getParentTrack() {
        return (TrackJob) this.parentTrackCombo.getSelectedItem();
    }

    /**
     * @return The reference genome associated with this track job.
     */
    @Override
    public ReferenceJob getReferenceJob() {
        return (ReferenceJob) this.refGenCombo.getSelectedItem();
    }

    
    public Integer getStepSize() {
        this.stepSize = (Integer) this.stepSizeSpinner.getValue();
        return this.stepSize;
    }

    
    private void setStepwiseField(boolean setFields) {
        this.stepSizeLabel.setVisible(setFields);
        this.stepSizeSpinner.setVisible(setFields);
    }    
    
    private void setTrackJobs(ReferenceJob refJob) {
        
        if (refJob != null) {
            ReferenceConnector refCon = ProjectConnector.getInstance().getRefGenomeConnector(refJob.getID());
            List<PersistantTrack> persTracks = refCon.getAssociatedTracks();
            TrackJob[] tracks = new TrackJob[persTracks.size()];

            for (int i = 0; i < tracks.length; ++i) {
                PersistantTrack track = persTracks.get(i);
                tracks[i] = new TrackJob(track.getId(), !track.getFilePath().isEmpty(), 
                        null, track.getDescription(), refJob, null, true, track.getTimestamp());
            }

            this.parentTrackCombo.setModel(new DefaultComboBoxModel<>(tracks));
            this.parentTrackCombo.setEnabled(true);
        } else {
            this.parentTrackCombo.setEnabled(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        parserCombo = new javax.swing.JComboBox<>(parsers);
        mappingFileField = new javax.swing.JTextField();
        mappingFileLabel = new javax.swing.JLabel();
        parentTrackLabel = new javax.swing.JLabel();
        stepSizeSpinner = new javax.swing.JSpinner();
        stepSizeLabel = new javax.swing.JLabel();
        refGenLabel = new javax.swing.JLabel();
        refGenCombo = new javax.swing.JComboBox<>(refGenJobs);
        openMappingButton = new javax.swing.JButton();
        parentTrackCombo = new javax.swing.JComboBox<>();

        jLabel1.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialogNew.jLabel1.text")); // NOI18N

        parserCombo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                if(value instanceof ParserI){
                    return super.getListCellRendererComponent(list, ((ParserI) value).getName(), index, isSelected, cellHasFocus);
                } else {
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });
        parserCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parserComboActionPerformed(evt);
            }
        });

        mappingFileField.setEditable(false);
        mappingFileField.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialogNew.mappingFileField.text")); // NOI18N

        mappingFileLabel.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialog.mappingFileLabel.text")); // NOI18N

        parentTrackLabel.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialog.parentTrackLabel.text")); // NOI18N

        stepSizeLabel.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialog.stepSizeLabel.text")); // NOI18N

        refGenLabel.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialog.refGenLabel.text")); // NOI18N

        refGenCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refGenComboActionPerformed(evt);
            }
        });

        openMappingButton.setText(org.openide.util.NbBundle.getMessage(NewPositionTableDialog.class, "NewPositionTableDialogNew.chooseButton.text")); // NOI18N
        openMappingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMappingButtonActionPerformed(evt);
            }
        });

        parentTrackCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentTrackComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(mappingFileLabel)
                    .addComponent(parentTrackLabel)
                    .addComponent(stepSizeLabel)
                    .addComponent(refGenLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refGenCombo, 0, 329, Short.MAX_VALUE)
                    .addComponent(parserCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 329, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mappingFileField, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openMappingButton))
                    .addComponent(parentTrackCombo, 0, 329, Short.MAX_VALUE)
                    .addComponent(stepSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mappingFileLabel)
                    .addComponent(mappingFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openMappingButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refGenCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refGenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentTrackCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentTrackLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stepSizeLabel))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
        
        private void openMappingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMappingButtonActionPerformed
            this.getMappingFiles().clear();
            VampFileChooser fc = new VampFileChooser(getCurrentParser().getFileExtensions(), getCurrentParser().getInputFileDescription()) {
                private static final long serialVersionUID = 1L;

                @Override
                public void save(String fileLocation) {
                    throw new UnsupportedOperationException("Not supported.");
                }

                @Override
                public void open(String fileLocation) {

                    // file chosen
                    addFile(this.getSelectedFile(), mappingFileField);
                }
            };
            
            fc.setDirectoryProperty("NewPositionTable.Filepath");
            fc.openFileChooser(VampFileChooser.OPEN_DIALOG);
    }//GEN-LAST:event_openMappingButtonActionPerformed

    private void parserComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parserComboActionPerformed
        MappingParserI newparser = (MappingParserI) parserCombo.getSelectedItem();
        if (getCurrentParser() != newparser) {
            setCurrentParser(newparser);
            getMappingFiles().clear();
            mappingFileField.setText("");
            setStepwiseField((newparser instanceof SamBamStepParser));
        }
    }//GEN-LAST:event_parserComboActionPerformed

    private void refGenComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refGenComboActionPerformed
        this.setTrackJobs((ReferenceJob) this.refGenCombo.getSelectedItem());
    }//GEN-LAST:event_refGenComboActionPerformed

    private void parentTrackComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentTrackComboActionPerformed

    }//GEN-LAST:event_parentTrackComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField mappingFileField;
    private javax.swing.JLabel mappingFileLabel;
    private javax.swing.JButton openMappingButton;
    private javax.swing.JComboBox<TrackJob> parentTrackCombo;
    private javax.swing.JLabel parentTrackLabel;
    private javax.swing.JComboBox<MappingParserI> parserCombo;
    private javax.swing.JComboBox<ReferenceJob> refGenCombo;
    private javax.swing.JLabel refGenLabel;
    private javax.swing.JLabel stepSizeLabel;
    private javax.swing.JSpinner stepSizeSpinner;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTrackName() {
        throw new UnsupportedOperationException("This panel has a parent track, not only a name!");
    }
}
