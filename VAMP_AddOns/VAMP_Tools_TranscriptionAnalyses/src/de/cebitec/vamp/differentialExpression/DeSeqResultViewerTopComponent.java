package de.cebitec.vamp.differentialExpression;

import de.cebitec.vamp.util.Observer;
import de.cebitec.vamp.util.fileChooser.VampFileChooser;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//de.cebitec.vamp.differentialExpression//DeSeqResultViewer//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "DeSeqResultViewerTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "de.cebitec.vamp.differentialExpression.DeSeqResultViewerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_DeSeqResultViewerAction",
preferredID = "DeSeqResultViewerTopComponent")
@Messages({
    "CTL_DeSeqResultViewerAction=DeSeqResultViewer",
    "CTL_DeSeqResultViewerTopComponent=Differential expression analysis - results",
    "HINT_DeSeqResultViewerTopComponent=This is a DeSeqResultViewer window"
})
public final class DeSeqResultViewerTopComponent extends TopComponent implements Observer, ItemListener {

    private String[] columnNames;
    private TableModel tm;
    private ComboBoxModel cbm;
    private ArrayList<TableModel> tableModels = new ArrayList<>();
    private DeSeqAnalysisHandler deySeqAnalysisHandler;
    private DeSeqGraficsTopComponent deSeqGraficsTopComponent;

    public DeSeqResultViewerTopComponent() {
    }

    public DeSeqResultViewerTopComponent(AnalysisHandler handler) {
        this.deySeqAnalysisHandler = (DeSeqAnalysisHandler) handler;
        tm = new DefaultTableModel();
        cbm = new DefaultComboBoxModel(new String[]{"Fitting group one", "Fitting group two", "probability values"});

        initComponents();
        setName(Bundle.CTL_DeSeqResultViewerTopComponent());
        setToolTipText(Bundle.HINT_DeSeqResultViewerTopComponent());

        jProgressBar1.setIndeterminate(true);
    }

    private void addResults(boolean moreThanTwoConditions) {
        List<DeSeqAnalysisHandler.Result> results = deySeqAnalysisHandler.getResults();

        for (Iterator<DeSeqAnalysisHandler.Result> it = results.iterator(); it.hasNext();) {
            DeSeqAnalysisHandler.Result currentResult = it.next();
            TableModel tmpTableModel = new DefaultTableModel(currentResult.getTableContents(), currentResult.getColnames());
            tableModels.add(tmpTableModel);
        }

        resultsTable.setModel(tableModels.get(0));

        resultsComboBox.setEnabled(true);
        resultsTable.setEnabled(true);
        saveTableButton.setEnabled(true);
        createGraficsButton.setEnabled(true);
        jLabel1.setEnabled(true);
        jLabel2.setEnabled(false);
        jProgressBar1.setIndeterminate(false);
        jProgressBar1.setValue(100);
        jProgressBar1.setEnabled(false);

    }

    private void changeShownTable(int element) {
        resultsTable.setModel(tableModels.get(element));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        resultsComboBox = new javax.swing.JComboBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        saveTableButton = new javax.swing.JButton();
        createGraficsButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DeSeqResultViewerTopComponent.class, "DeSeqResultViewerTopComponent.jLabel1.text")); // NOI18N
        jLabel1.setEnabled(false);

        resultsComboBox.setModel(cbm);
        resultsComboBox.setEnabled(false);
        resultsComboBox.addItemListener(this);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DeSeqResultViewerTopComponent.class, "DeSeqResultViewerTopComponent.jLabel2.text")); // NOI18N

        resultsTable.setModel(tm);
        resultsTable.setEnabled(false);
        jScrollPane1.setViewportView(resultsTable);

        org.openide.awt.Mnemonics.setLocalizedText(saveTableButton, org.openide.util.NbBundle.getMessage(DeSeqResultViewerTopComponent.class, "DeSeqResultViewerTopComponent.saveTableButton.text")); // NOI18N
        saveTableButton.setEnabled(false);
        saveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTableButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(createGraficsButton, org.openide.util.NbBundle.getMessage(DeSeqResultViewerTopComponent.class, "DeSeqResultViewerTopComponent.createGraficsButton.text")); // NOI18N
        createGraficsButton.setEnabled(false);
        createGraficsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGraficsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                        .addComponent(saveTableButton)
                        .addGap(18, 18, 18)
                        .addComponent(createGraficsButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(resultsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(saveTableButton)
                        .addComponent(createGraficsButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTableButtonActionPerformed
        new VampFileChooser(VampFileChooser.SAVE_DIALOG, new String[]{"csv"}, "csv") {
            @Override
            public void save(String fileLocation) {
                deySeqAnalysisHandler.saveResultsAsCSV(resultsComboBox.getSelectedIndex(), fileLocation);
            }

            @Override
            public void open(String fileLocation) {
            }
        };
    }//GEN-LAST:event_saveTableButtonActionPerformed

    private void createGraficsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGraficsButtonActionPerformed
        deSeqGraficsTopComponent = new DeSeqGraficsTopComponent(deySeqAnalysisHandler);
        deySeqAnalysisHandler.registerObserver(deSeqGraficsTopComponent);
        deSeqGraficsTopComponent.open();
        deSeqGraficsTopComponent.requestActive();
    }//GEN-LAST:event_createGraficsButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createGraficsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox resultsComboBox;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton saveTableButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        deySeqAnalysisHandler.removeObserver(this);
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

    @Override
    public void update(Object args) {
        addResults((boolean) args);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String item = (String) e.getItem();
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED) {
            changeShownTable(resultsComboBox.getSelectedIndex());
        }
    }
}
