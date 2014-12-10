/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.cebitec.readxplorer.vcf.handling.visualization;


import de.cebitec.readxplorer.ui.TopComponentExtended;
import de.cebitec.readxplorer.utils.TabWithCloseX;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;


/**
 * Top component which displays the VcFTable of VcfResult and the VcfView.
 * <p>
 * @author vetz
 */
@ConvertAsProperties(
         dtd = "-//de.cebitec.readxplorer.vcf.handling.visualization//Snp_VcfResultTopComponent//EN",
         autostore = false )
@TopComponent.Description(
         preferredID = "Snp_VcfResultTopComponent",
         //iconBase="SET/PATH/TO/ICON/HERE",
         persistenceType = TopComponent.PERSISTENCE_ALWAYS )
@TopComponent.Registration( mode = "output", openAtStartup = false )
@ActionID( category = "Window", id = "Snp_VcfResultTopComponent" )
@ActionReference( path = "Menu/Window" /*, position = 333 */ )
@TopComponent.OpenActionRegistration(
         displayName = "#CTL_VcfResultAction",
         preferredID = "Snp_VcfResultTopComponent"
)
@Messages( {
    "CTL_VcfResultAction=VcfResult",
    "CTL_VcfResultTopComponent=VcfResult Window",
    "HINT_VcfResultTopComponent=This is a VcfResult window"
} )
public final class Snp_VcfResultTopComponent extends TopComponentExtended {

    public static final String PREFERRED_ID = "Snp_VcfResultTopComponent";
    private static final long serialVersionUID = 1L;


    public Snp_VcfResultTopComponent() {
        initComponents();
        setName( Bundle.CTL_VcfResultTopComponent() );
        setToolTipText( Bundle.HINT_VcfResultTopComponent() );
        putClientProperty( TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE );  // from CA

        // add listener to close TopComponent when no tabs are shown
        // from AC
        this.vcfTabbedPane.addContainerListener( new ContainerListener() {
            @Override
            public void componentAdded( ContainerEvent e ) {
            }


            @Override
            public void componentRemoved( ContainerEvent e ) {
                if( vcfTabbedPane.getTabCount() == 0 ) {
                    WindowManager.getDefault().findTopComponent( PREFERRED_ID ).close();
                }
            }


        } );
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vcfTabbedPane = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vcfTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vcfTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane vcfTabbedPane;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }


    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }


    void writeProperties( java.util.Properties p ) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty( "version", "1.0" );
        // TODO store your settings
    }


    void readProperties( java.util.Properties p ) {
        String version = p.getProperty( "version" );
        // TODO read your settings according to their version
    }
// from AC


    public void openAnalysisTab( String panelName, JPanel resultPanel ) {
        this.vcfTabbedPane.add( panelName, resultPanel );
        this.vcfTabbedPane.setTabComponentAt( this.vcfTabbedPane.getTabCount()
                                              - 1, new TabWithCloseX( this.vcfTabbedPane ) );
        this.vcfTabbedPane.setSelectedIndex( this.vcfTabbedPane.getTabCount()
                                             - 1 );
    }


    /**
     * @return true, if this component already contains other components, false
     *         otherwise.
     */
    public boolean hasComponents() {
        return this.vcfTabbedPane.getComponentCount() > 0;
    }


}
