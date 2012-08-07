package de.cebitec.vamp.differentialExpression;

import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.util.Observer;
import de.cebitec.vamp.util.fileChooser.VampFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import org.apache.batik.swing.JSVGCanvas;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//de.cebitec.vamp.differentialExpression//DiffExpGrafics//EN",
autostore = false)
@TopComponent.Description(preferredID = "DiffExpGraficsTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "de.cebitec.vamp.differentialExpression.DiffExpGraficsTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_DiffExpGraficsAction",
preferredID = "DiffExpGraficsTopComponent")
@Messages({
    "CTL_DiffExpGraficsAction=DiffExpGrafics",
    "CTL_DiffExpGraficsTopComponent=Create graphics",
    "HINT_DiffExpGraficsTopComponent=This is a DiffExpGrafics window"
})
public final class DiffExpGraficsTopComponent extends TopComponent implements Observer, ItemListener {

    private PerformAnalysis perfAn;
    private JSVGCanvas svgCanvas;
    private ComboBoxModel cbm;
    private DefaultListModel<PersistantTrack> samplesA = new DefaultListModel<>();
    private DefaultListModel<PersistantTrack> samplesB = new DefaultListModel<>();
    private File currentlyDisplayed;

    public DiffExpGraficsTopComponent() {
        cbm = new DefaultComboBoxModel(PerformAnalysis.Plot.values());
        initComponents();
//        setName(Bundle.CTL_DiffExpGraficsTopComponent());
//        setToolTipText(Bundle.HINT_DiffExpGraficsTopComponent());
        svgCanvas = new JSVGCanvas();
        jPanel1.add(svgCanvas, BorderLayout.CENTER);

    }

    private void addResults(List<Group> groups) {
        groupComboBox.setModel(new DefaultComboBoxModel(groups.toArray()));
        List<PersistantTrack> tracks = perfAn.getSelectedTraks();
        for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            samplesA.addElement(persistantTrack);
            samplesB.addElement(persistantTrack);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        plotTypeComboBox = new javax.swing.JComboBox(cbm);
        jLabel2 = new javax.swing.JLabel();
        groupComboBox = new javax.swing.JComboBox();
        samplesALabel = new javax.swing.JLabel();
        samplesBLabel = new javax.swing.JLabel();
        plotButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        messages = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        samplesAList = new javax.swing.JList(samplesA);
        jScrollPane2 = new javax.swing.JScrollPane();
        samplesBList = new javax.swing.JList(samplesB);
        saveButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.jLabel1.text")); // NOI18N

        plotTypeComboBox.addItemListener(this);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(samplesALabel, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.samplesALabel.text")); // NOI18N
        samplesALabel.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(samplesBLabel, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.samplesBLabel.text")); // NOI18N
        samplesBLabel.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(plotButton, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.plotButton.text")); // NOI18N
        plotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(messages, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.messages.text")); // NOI18N

        samplesAList.setEnabled(false);
        jScrollPane1.setViewportView(samplesAList);

        samplesBList.setEnabled(false);
        jScrollPane2.setViewportView(samplesBList);

        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(DiffExpGraficsTopComponent.class, "DiffExpGraficsTopComponent.saveButton.text")); // NOI18N
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(groupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plotTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(messages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(samplesALabel))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(samplesBLabel)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(plotButton)
                    .addComponent(saveButton))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plotTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(samplesALabel)
                            .addComponent(samplesBLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(messages, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plotButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)
                        .addGap(0, 148, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed
        boolean inputValid = false;
        PerformAnalysis.Plot selectedPlot = (PerformAnalysis.Plot) plotTypeComboBox.getSelectedItem();
        int[] samplA = samplesAList.getSelectedIndices();
        int[] samplB = samplesBList.getSelectedIndices();

        if (selectedPlot == PerformAnalysis.Plot.Priors) {
            inputValid = true;
        } else {
            if (samplA.length != samplB.length) {
                inputValid = true;
            } else {
                for (int i = 0; i < samplA.length; i++) {
                    if (samplA[i] != samplB[i]) {
                        inputValid = true;
                        break;
                    }
                }
            }
        }
        if (inputValid) {
            try {
                messages.setText("");
                plotButton.setEnabled(false);
                saveButton.setEnabled(false);
                currentlyDisplayed = perfAn.plot(selectedPlot, ((Group) groupComboBox.getSelectedItem()), samplA, samplB);
                svgCanvas.setURI(currentlyDisplayed.toURI().toString());
                svgCanvas.setVisible(true);
                svgCanvas.repaint();
                saveButton.setEnabled(true);
                plotButton.setEnabled(true);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        } else {
            messages.setText("Samples A and B must not be the same!");
        }

    }//GEN-LAST:event_plotButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        new VampFileChooser(VampFileChooser.SAVE_DIALOG, new String[]{"svg"}, "SVG") {

            @Override
            public void save(String fileLocation) {
                Path from = currentlyDisplayed.toPath();
                Path to = FileSystems.getDefault().getPath(fileLocation, "");
                try {
                    Path outputFile = Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                    messages.setText("SVG image saved to "+outputFile.toString());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

            @Override
            public void open(String fileLocation) {
            }
        };
    }//GEN-LAST:event_saveButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox groupComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel messages;
    private javax.swing.JButton plotButton;
    private javax.swing.JComboBox plotTypeComboBox;
    private javax.swing.JLabel samplesALabel;
    private javax.swing.JList samplesAList;
    private javax.swing.JLabel samplesBLabel;
    private javax.swing.JList samplesBList;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // store your settings here
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // read your settings according to their version here
    }

    @Override
    public void update(Object args) {
        this.perfAn = (PerformAnalysis) args;
        addResults(perfAn.getGroups());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        PerformAnalysis.Plot item = (PerformAnalysis.Plot) e.getItem();
        if (item == PerformAnalysis.Plot.MACD || item == PerformAnalysis.Plot.Posteriors) {
            samplesAList.setEnabled(true);
            samplesALabel.setEnabled(true);
            samplesBList.setEnabled(true);
            samplesBLabel.setEnabled(true);
        } else {
            samplesAList.setEnabled(false);
            samplesALabel.setEnabled(false);
            samplesBList.setEnabled(false);
            samplesBLabel.setEnabled(false);
        }
    }
}
