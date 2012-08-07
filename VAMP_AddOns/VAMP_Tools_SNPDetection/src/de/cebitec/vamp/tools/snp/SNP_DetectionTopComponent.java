package de.cebitec.vamp.tools.snp;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import de.cebitec.vamp.databackend.dataObjects.SnpData;
import de.cebitec.vamp.util.TabWithCloseX;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.ReferenceViewer;
import java.awt.CardLayout;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays SNP detection tabs.
 * 
 * @author jwinneba, rhilker
 */
@ConvertAsProperties(dtd = "-//de.cebitec.vamp.tools.snp//SNP_Detection//EN",
autostore = false)
@TopComponent.Description(preferredID = "SNP_DetectionTopComponent",
iconBase = "de/cebitec/vamp/tools/snp/snpDetection.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "de.cebitec.vamp.tools.snp.SNP_DetectionTopComponent")
@ActionReference(path = "Menu/Window", position = 950)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SNP_DetectionAction",
preferredID = "SNP_DetectionTopComponent")
public final class SNP_DetectionTopComponent extends TopComponent {

    private static final long serialVersionUID = 1L;
    private static SNP_DetectionTopComponent instance;
    /** path to the icon used by the component and its open action */
    private static final String PREFERRED_ID = "SNP_DetectionTopComponent";
    

    public SNP_DetectionTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(SNP_DetectionTopComponent.class, "CTL_SNP_DetectionTopComponent"));
        setToolTipText(NbBundle.getMessage(SNP_DetectionTopComponent.class, "HINT_SNP_DetectionTopComponent"));
        // add listener to close TopComponent when no tabs are shown
        snpTabs.addContainerListener(new ContainerListener() {

            @Override
            public void componentAdded(ContainerEvent e) {
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (snpTabs.getTabCount() == 0) {
                    WindowManager.getDefault().findTopComponent(PREFERRED_ID).close();
                }
            }
        });
    }

    /**
     * Creates a complete snp detection panel, that is used in the JTabbedPane.
     * The <code>TrackViewer</code> instance is used to set up the setup and
     * result panels.
     *
     * @param referenceViewer the reference viewer for which the snp detection should be carried out.
     * @param trackIds the list of track ids for which the snp detection should be carried out. They 
     * all have to belong to the reference genome set in the reference viewer
     * @return complete snp detection panel
     */
    private javax.swing.JPanel getSnpDetectionPanel(ReferenceViewer referenceViewer, List<Integer> trackIds, final int tabId) {
        // initialise components
        final JPanel snpDetectionPanel = new JPanel();
        SNP_DetectionSetupPanel setupPanel = new SNP_DetectionSetupPanel();
        final SNP_DetectionResultPanel resultPanel = new SNP_DetectionResultPanel();
        //add reference sequence for amino acid mutation detection
        resultPanel.setReferenceGenome(referenceViewer.getReference());

        // assign the track Ids
        setupPanel.setTrackIds(trackIds);
        resultPanel.setBoundsInfoManager(referenceViewer.getBoundsInformationManager());

        // listen on changes of the search
        setupPanel.addPropertyChangeListener(SNP_DetectionSetupPanel.PROP_SNPS_LOADED, new PropertyChangeListener() {

            @Override
            @SuppressWarnings("unchecked")
            public void propertyChange(PropertyChangeEvent evt) {
                SnpData snpData = (SnpData) evt.getNewValue();
                resultPanel.addSNPs(snpData);
                ((CardLayout) snpDetectionPanel.getLayout()).show(snpDetectionPanel, "results");
                if (snpData.getSnpList().size() > 0) {
                    String title = "SNP Detection for selected tracks ("
                            + snpData.getSnpList().size() + " hits)";
                    snpTabs.setTitleAt(tabId, title);
                }
            }
        });

        // setup the layout
        snpDetectionPanel.setLayout(new java.awt.CardLayout());
        snpDetectionPanel.add(setupPanel, "setup");
        snpDetectionPanel.add(resultPanel, "results");

        return snpDetectionPanel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        snpTabs = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snpTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snpTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane snpTabs;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized SNP_DetectionTopComponent getDefault() {
        if (instance == null) {
            instance = new SNP_DetectionTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the SNP_DetectionTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized SNP_DetectionTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(SNP_DetectionTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof SNP_DetectionTopComponent) {
            return (SNP_DetectionTopComponent) win;
        }
        Logger.getLogger(SNP_DetectionTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
        snpTabs.removeAll();
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
    protected String preferredID() {
        return PREFERRED_ID;
    }

    /**
     * This method needas to be called in order to open a new tab for snp detection.
     * @param referenceViewer the reference viewer for which the snp detection should be carried out.
     * @param trackIds the list of track ids (associated to the reference viewer) for which the snp 
     *          detection should be carried out.
     */
    public void openDetectionTab(ReferenceViewer referenceViewer, List<Integer> trackIds) {
        String title = "SNP Detection for selected tracks";
        JPanel snpDetectionPanel = this.getSnpDetectionPanel(referenceViewer, trackIds, snpTabs.getTabCount());
        snpTabs.addTab(title, snpDetectionPanel);
        snpTabs.setTabComponentAt(snpTabs.getTabCount() - 1, new TabWithCloseX(snpTabs));
    }
}
