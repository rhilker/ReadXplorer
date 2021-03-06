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

package de.cebitec.readxplorer.tools.snpdetection;


import de.cebitec.readxplorer.ui.TopComponentExtended;
import de.cebitec.readxplorer.ui.TopComponentHelper;
import javax.swing.JPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;


/**
 * Top component which displays SNP detection tabs.
 * <p>
 * @author jwinneba, rhilker
 */
@ConvertAsProperties( dtd = "-//de.cebitec.readxplorer.tools.snpdetection//SNP_Detection//EN",
                      autostore = false )
@TopComponent.Description( preferredID = "SnpDetectionTopComponent",
                           iconBase = "de/cebitec/readxplorer/tools/snpdetection/snpDetection.png",
                           persistenceType = TopComponent.PERSISTENCE_ALWAYS )
@TopComponent.Registration( mode = "output", openAtStartup = false )
@ActionID( category = "Window", id = "de.cebitec.readxplorer.tools.snpdetection.SnpDetectionTopComponent" )
@ActionReference( path = "Menu/Window", position = 950 )
@TopComponent.OpenActionRegistration( displayName = "#CTL_SNP_DetectionAction",
                                      preferredID = "SnpDetectionTopComponent" )
public final class SnpDetectionTopComponent extends TopComponentExtended {

    private static final long serialVersionUID = 1L;


    @NbBundle.Messages( {
        "CTL_SNP_DetectionTopComp=SNP Detection Window",
        "HINT_SNP_DetectionTopComp=This is a SNP Detection window" } )
    public SnpDetectionTopComponent() {
        initComponents();
        setName( Bundle.CTL_SNP_DetectionTopComp() );
        setToolTipText( Bundle.HINT_SNPDetectionTopComp() );
        putClientProperty( TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE );
        TopComponentHelper.setupContainerListener( snpTabs, preferredID() );
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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


    void writeProperties( java.util.Properties p ) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty( "version", "1.0" );
        // store your settings here
    }


    void readProperties( java.util.Properties p ) {
        String version = p.getProperty( "version" );
        // read your settings according to their version here
    }


    /**
     * This method needs to be called in order to open a new tab for snp
     * detection.
     * <p>
     * @param panelName               Name of the panel to open
     * @param snpDetectionResultPanel The panel to display in the new tab
     */
    public void openDetectionTab( String panelName, JPanel snpDetectionResultPanel ) {
        TopComponentHelper.openTableTab( snpTabs, panelName, snpDetectionResultPanel );
    }


    /**
     * @return true, if this component already contains other components, false
     *         otherwise.
     */
    public boolean hasComponents() {
        return this.snpTabs.getComponentCount() > 0;
    }


}
