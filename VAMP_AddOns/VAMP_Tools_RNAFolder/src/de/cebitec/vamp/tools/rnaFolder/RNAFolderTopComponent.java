package de.cebitec.vamp.tools.rnaFolder;

import de.cebitec.vamp.tools.rnaFolder.rnamovies.MoviePane;
import de.cebitec.vamp.tools.rnaFolder.rnamovies.actions.Export;
import de.cebitec.vamp.util.TabWithCloseX;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component displaying a tabbed pane for visualizing folded RNAs (with the
 * aid of RNAFold and RNAMovies).
 */
@ConvertAsProperties(dtd = "-//de.cebitec.vamp.tools.rnaFolder//RNAFolder//EN",
autostore = false)
public final class RNAFolderTopComponent extends TopComponent {

    private static RNAFolderTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "RNAFolderTopComponent";
    
    List<MoviePane> rnaMovieList = new ArrayList<MoviePane>();

    public RNAFolderTopComponent() {
        initComponents();
        this.setName(NbBundle.getBundle(RNAFolderController.class).getString("RNAFolderViewer.Name"));
        this.rnaTabbedPane.
        setName(NbBundle.getMessage(RNAFolderTopComponent.class, "CTL_RNAFolderTopComponent"));
        setToolTipText(NbBundle.getMessage(RNAFolderTopComponent.class, "HINT_RNAFolderTopComponent"));
        //this.openAtTabPosition(this.get);
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rnaTabbedPane = new JTabbedPaneWithNewRemoveAction();
        exportButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        org.openide.awt.Mnemonics.setLocalizedText(exportButton, org.openide.util.NbBundle.getMessage(RNAFolderTopComponent.class, "RNAFolderTopComponent.exportButton.text")); // NOI18N
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(exportButton)
                .addContainerGap())
            .addComponent(rnaTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(exportButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rnaTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        Export exportAction = new Export((MoviePane) this.rnaTabbedPane.getSelectedComponent(), this.rnaTabbedPane.getTitleAt(this.rnaTabbedPane.getSelectedIndex()));
        exportAction.actionPerformed(evt);
    }//GEN-LAST:event_exportButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportButton;
    private javax.swing.JTabbedPane rnaTabbedPane;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     * @return the single RNAFolderTopComponent instance
     */
    public static synchronized RNAFolderTopComponent getDefault() {
        if (instance == null) {
            instance = new RNAFolderTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the RNAFolderTopComponent instance. Never call {@link #getDefault} directly!
     * @return the single RNAFolderTopComponent instance
     */
    public static synchronized RNAFolderTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(RNAFolderTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof RNAFolderTopComponent) {
            return (RNAFolderTopComponent) win;
        }
        Logger.getLogger(RNAFolderTopComponent.class.getName()).warning(
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
        if (this.rnaMovieList.isEmpty()){
            this.exportButton.setEnabled(false);
        }
    }

    @Override
    public void componentClosed() {
        this.rnaTabbedPane.removeAll();
        this.exportButton.setEnabled(false);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // store your settings here
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

    /**
     * Adds a tab to the pane with the given tabHeader and sets the rnaMovie object into the tab.
     * @param rnaMovie the rnaMovie to be shown in the tab
     * @param tabHeader the name of the reference the folded sequence originates from
     */
    public void openRNAMovie(MoviePane rnaMovie, String tabHeader) {
        this.rnaTabbedPane.addTab(tabHeader, rnaMovie);
        this.rnaTabbedPane.setTabComponentAt(this.rnaTabbedPane.getTabCount()-1, new TabWithCloseX(this.rnaTabbedPane));
        this.rnaTabbedPane.setSelectedIndex(this.rnaTabbedPane.getTabCount()-1);
        this.open();
        this.requestActive();
        this.validate();
        rnaMovie.zoomFit();
        this.updateExportButton();
    }

    /**
     * Checks whether the export button should be activated or not.
     */
    private void updateExportButton() {
        if (this.rnaTabbedPane.getTabCount() > 0){
            this.exportButton.setEnabled(true);
        } else {
            this.exportButton.setEnabled(false);
        }
    }
    
    /**
     * Tabbed pane with additional functionality. When a tab is closed with remove(index)
     * it also removes the corresponding movie from the movie list.
     */
    private class JTabbedPaneWithNewRemoveAction extends JTabbedPane {
        
        /**
         * Removes the tab and component which corresponds to the specified index.
         * Additionally removes the closed rnaMovie from the list of movies.
         *
         * @param index the index of the component to remove from the 
         *          <code>tabbedpane</code>
         * @exception IndexOutOfBoundsException if index is out of range 
         *            (index < 0 || index >= tab count)
         * @see #addTab
         * @see #removeTabAt  
         */
        @Override
        public void remove(int index) {
            removeTabAt(index);
            RNAFolderTopComponent.this.updateExportButton();            
        }
    }
}
