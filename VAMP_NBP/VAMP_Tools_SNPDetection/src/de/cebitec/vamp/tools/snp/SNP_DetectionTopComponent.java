package de.cebitec.vamp.tools.snp;

import de.cebitec.vamp.api.objects.NewSnp;
import de.cebitec.vamp.api.objects.Snp;
import de.cebitec.vamp.util.TabWithCloseX;
import de.cebitec.vamp.view.dataVisualisation.trackViewer.TrackViewer;
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
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays SNP detection tabs.
 *
 * @author jwinneba
 */
@ConvertAsProperties(dtd = "-//de.cebitec.vamp.tools.snp//SNP_Detection//EN", autostore = false)
public final class SNP_DetectionTopComponent extends TopComponent {

    private static final long serialVersionUID = 1L;
    private static SNP_DetectionTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "de/cebitec/vamp/tools/snp/snpDetection.png";
    private static final String PREFERRED_ID = "SNP_DetectionTopComponent";

    public SNP_DetectionTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(SNP_DetectionTopComponent.class, "CTL_SNP_DetectionTopComponent"));
        setToolTipText(NbBundle.getMessage(SNP_DetectionTopComponent.class, "HINT_SNP_DetectionTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

        // add listener to close TopComponent when no tabs are shown
        snpTabs.addContainerListener(new ContainerListener() {

            @Override
            public void componentAdded(ContainerEvent e) {

            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (snpTabs.getTabCount() == 0){
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
     * @param trackViewer instance used for this panels panels.
     * @return complete snp detection panel
     */
    private javax.swing.JPanel getSnpDetectionPanel(TrackViewer trackViewer){
        // initialise components
        final JPanel snpDetectionPanel = new JPanel();
        SNP_DetectionSetupPanel setupPanel = new SNP_DetectionSetupPanel();
        final SNP_DetectionResultPanel resultPanel = new SNP_DetectionResultPanel();

        // assign the trackviewer
        setupPanel.setCon(trackViewer.getTrackCon());
        resultPanel.setBoundsInfoManager(trackViewer.getBoundsInformationManager());

        // listen on changes of the search
        setupPanel.addPropertyChangeListener(SNP_DetectionSetupPanel.PROP_SNPS_LOADED, new PropertyChangeListener() {

            @Override
            @SuppressWarnings("unchecked")
            public void propertyChange(PropertyChangeEvent evt) {
                resultPanel.addSNPs((List<Snp>) evt.getNewValue());
                ((CardLayout) snpDetectionPanel.getLayout()).show(snpDetectionPanel, "results");
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

        snpDetectionPanelTemplate = new javax.swing.JPanel();
        setupPanelTemplate = new de.cebitec.vamp.tools.snp.SNP_DetectionSetupPanel();
        resultPanelTemplate = new de.cebitec.vamp.tools.snp.SNP_DetectionResultPanel();
        snpTabs = new javax.swing.JTabbedPane();

        snpDetectionPanelTemplate.setLayout(new java.awt.CardLayout());
        snpDetectionPanelTemplate.add(setupPanelTemplate, "setup");
        snpDetectionPanelTemplate.add(resultPanelTemplate, "results");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snpTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(snpTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cebitec.vamp.tools.snp.SNP_DetectionResultPanel resultPanelTemplate;
    private de.cebitec.vamp.tools.snp.SNP_DetectionSetupPanel setupPanelTemplate;
    private javax.swing.JPanel snpDetectionPanelTemplate;
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
        // store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public void openDetectionTab(TrackViewer trackViewer){
        snpTabs.addTab(trackViewer.getTrackCon().getAssociatedTrackName(), getSnpDetectionPanel(trackViewer));
        snpTabs.setTabComponentAt(snpTabs.getTabCount()-1, new TabWithCloseX(snpTabs));
    }

}
