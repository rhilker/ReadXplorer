package de.cebitec.vamp.correlationAnalysis;

import de.cebitec.vamp.util.TabWithCloseX;
import de.cebitec.vamp.view.TopComponentExtended;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.ReferenceViewer;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.logging.Logger;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays a result of the correlation analysis.
 * @author Evgeny Anisiforov <evgeny at cebitec.uni-bielefeld.de>
 */
@ConvertAsProperties(
    dtd = "-//de.cebitec.vamp.correlationAnalysis//CorrelationResult//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "CorrelationResultTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "de.cebitec.vamp.correlationAnalysis.CorrelationResultTopComponent")
@ActionReference(path = "Menu/Window"/* , position = 951*/)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_CorrelationResultAction",
preferredID = "CorrelationResultTopComponent")
@Messages({
    "CTL_CorrelationResultAction=CorrelationResult",
    "CTL_CorrelationResultTopComponent=Correlation Analysis Window",
    "HINT_CorrelationResultTopComponent="
})
public final class CorrelationResultTopComponent extends TopComponentExtended {
    
    private static CorrelationResultTopComponent instance;
    private static final String PREFERRED_ID = "CorrelationResultTopComponent";
    
    
    public CorrelationResultTopComponent() {
        initComponents();
        setName(Bundle.CTL_CorrelationResultTopComponent());
        setToolTipText(Bundle.HINT_CorrelationResultTopComponent());
        
        resultTabs.addContainerListener(new ContainerListener() {

            @Override
            public void componentAdded(ContainerEvent e) {
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (resultTabs.getTabCount() == 0) {
                    WindowManager.getDefault().findTopComponent(PREFERRED_ID).close();
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

        resultTabs = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane resultTabs;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized CorrelationResultTopComponent getDefault() {
        if (instance == null) {
            instance = new CorrelationResultTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the SNP_DetectionTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized CorrelationResultTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(CorrelationResultTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof CorrelationResultTopComponent) {
            return (CorrelationResultTopComponent) win;
        }
        Logger.getLogger(CorrelationResultTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }
    
    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }
    
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
    
    /**
     * This method needs to be called in order to open a new tab for snp detection.
     * @param referenceViewer the reference viewer for which the snp detection should be carried out.
     * @param trackIds the list of track ids (associated to the reference viewer) for which the snp 
     *          detection should be carried out.
     */
    public CorrelationResultPanel openResultTab(ReferenceViewer referenceViewer) {
        CorrelationResultPanel resultView = new CorrelationResultPanel();
        resultView.setBoundsInfoManager(referenceViewer.getBoundsInformationManager());
        String title = "Correlation Analysis";
        resultTabs.addTab(title, resultView);
        resultTabs.setTabComponentAt(resultTabs.getTabCount() - 1, new TabWithCloseX(resultTabs));
        return resultView;
    }
    
}
