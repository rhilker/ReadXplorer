package de.cebitec.vamp.differentialExpression;

import de.cebitec.vamp.util.fileChooser.VampFileChooser;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//de.cebitec.vamp.differentialExpression//DeSeqGrafics//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "DeSeqGraficsTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "de.cebitec.vamp.differentialExpression.DeSeqGraficsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_DeSeqGraficsAction",
preferredID = "DeSeqGraficsTopComponent")
@Messages({
    "CTL_DeSeqGraficsAction=DeSeqGrafics",
    "CTL_DeSeqGraficsTopComponent=Create graphics",
    "HINT_DeSeqGraficsTopComponent=This is a DeSeqGrafics window"
})
public final class DeSeqGraficsTopComponent extends TopComponent {

    private AnalysisHandler analysisHandler;
    private JSVGCanvas svgCanvas;
    private ComboBoxModel cbm;
    private File currentlyDisplayed;
    private AnalysisHandler.Tool tool;

    public DeSeqGraficsTopComponent() {
    }
    
    public DeSeqGraficsTopComponent(AnalysisHandler handler, AnalysisHandler.Tool tool) {
        analysisHandler = handler;
        this.tool = tool;
        cbm = new DefaultComboBoxModel(SimpleTestAnalysisHandler.Plot.values());
        initComponents();
        setupGrafics();
    }

    public DeSeqGraficsTopComponent(AnalysisHandler handler, boolean moreThanTwoConditions, AnalysisHandler.Tool tool) {
        analysisHandler = handler;
        this.tool = tool;
        cbm = new DefaultComboBoxModel(DeSeqAnalysisHandler.Plot.getValues(moreThanTwoConditions));
        initComponents();
        setupGrafics();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        plotType = new javax.swing.JComboBox();
        plotButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        messages = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DeSeqGraficsTopComponent.class, "DeSeqGraficsTopComponent.jLabel1.text")); // NOI18N

        plotType.setModel(cbm);

        org.openide.awt.Mnemonics.setLocalizedText(plotButton, org.openide.util.NbBundle.getMessage(DeSeqGraficsTopComponent.class, "DeSeqGraficsTopComponent.plotButton.text")); // NOI18N
        plotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(DeSeqGraficsTopComponent.class, "DeSeqGraficsTopComponent.saveButton.text")); // NOI18N
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(messages, org.openide.util.NbBundle.getMessage(DeSeqGraficsTopComponent.class, "DeSeqGraficsTopComponent.messages.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(plotType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plotButton)
                    .addComponent(saveButton)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(messages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plotType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(plotButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(messages, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 332, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed
        try {
            messages.setText("");
            plotButton.setEnabled(false);
            saveButton.setEnabled(false);
            if (tool == AnalysisHandler.Tool.DeSeq) {
                DeSeqAnalysisHandler.Plot selectedPlot = (DeSeqAnalysisHandler.Plot) plotType.getSelectedItem();
                currentlyDisplayed = ((DeSeqAnalysisHandler) analysisHandler).plot(selectedPlot);
            } else {
                SimpleTestAnalysisHandler.Plot selectedPlot = (SimpleTestAnalysisHandler.Plot) plotType.getSelectedItem();
                currentlyDisplayed = ((SimpleTestAnalysisHandler) analysisHandler).plot(selectedPlot);
            }
            svgCanvas.setURI(currentlyDisplayed.toURI().toString());
            svgCanvas.setVisible(true);
            svgCanvas.repaint();
        } catch (IOException ex) {
            Date currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "{0}: " + ex.getMessage(), currentTimestamp);
            JOptionPane.showMessageDialog(null, "Can't create the temporary svg file!", "Gnu R Error", JOptionPane.WARNING_MESSAGE);
        } catch (GnuR.PackageNotLoadableException ex) {
            Date currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "{0}: " + ex.getMessage(), currentTimestamp);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Gnu R Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_plotButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        new VampFileChooser(VampFileChooser.SAVE_DIALOG, new String[]{"svg"}, "svg") {
            @Override
            public void save(String fileLocation) {
                Path from = currentlyDisplayed.toPath();
                Path to = FileSystems.getDefault().getPath(fileLocation, "");
                try {
                    Path outputFile = Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                    messages.setText("SVG image saved to " + outputFile.toString());
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel messages;
    private javax.swing.JButton plotButton;
    private javax.swing.JComboBox plotType;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void setupGrafics() {
        setName(Bundle.CTL_DeSeqGraficsTopComponent());
        setToolTipText(Bundle.HINT_DeSeqGraficsTopComponent());
        svgCanvas = new JSVGCanvas();
        jPanel1.add(svgCanvas, BorderLayout.CENTER);
        svgCanvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderListener() {
            @Override
            public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
                progressBar.setIndeterminate(true);
            }

            @Override
            public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
                progressBar.setIndeterminate(false);
                progressBar.setValue(100);
                saveButton.setEnabled(true);
                plotButton.setEnabled(true);
            }

            @Override
            public void documentLoadingCancelled(SVGDocumentLoaderEvent e) {
            }

            @Override
            public void documentLoadingFailed(SVGDocumentLoaderEvent e) {
                messages.setText("Could not load SVG file. Please try again.");
            }
        });
    }
}
