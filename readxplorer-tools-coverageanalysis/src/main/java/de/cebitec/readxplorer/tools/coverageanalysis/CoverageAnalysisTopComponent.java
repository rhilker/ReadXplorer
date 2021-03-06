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

package de.cebitec.readxplorer.tools.coverageanalysis;


import de.cebitec.readxplorer.ui.TopComponentExtended;
import de.cebitec.readxplorer.ui.TopComponentHelper;
import javax.swing.JPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;


/**
 * TopComponent of the coverage analysis. It displays all results of all carried
 * out coverage analyses.
 * <p>
 * @author Tobias Zimmermann, Rolf Hilker
 * <rolf.hilker at mikrobio.med.uni-giessen.de>
 */
@ConvertAsProperties(
         dtd = "-//CoverageAnalysis//CoverageAnalysisTopComponent//EN",
         autostore = false )
@TopComponent.Description(
         preferredID = "CoverageAnalysisTopComponent",
         //iconBase="SET/PATH/TO/ICON/HERE",
         persistenceType = TopComponent.PERSISTENCE_ALWAYS )
@TopComponent.Registration( mode = "output", openAtStartup = false )
@ActionID( category = "Window", id = "CoverageAnalysis.CoverageAnalysisTopComponent" )
@ActionReference( path = "Menu/Window" /* , position = 333 */ )
@TopComponent.OpenActionRegistration(
         displayName = "#CTL_CoverageAnalysisTopComponent",
         preferredID = "CoverageAnalysisTopComponent" )
@Messages( {
    "CTL_CoverageAnalysisTopComponent=Coverage Analysis Window",
    "HINT_CoverageAnalysisTopComponent=This is a Coverage Analysis Result window"
} )
public final class CoverageAnalysisTopComponent extends TopComponentExtended {

    private static final long serialVersionUID = 1L;


    /**
     * TopComponent of the coverage analysis. It displays all results of all
     * carried out coverage analyses.
     */
    public CoverageAnalysisTopComponent() {
        initComponents();
        setName( Bundle.CTL_CoverageAnalysisTopComponent() );
        setToolTipText( Bundle.HINT_CoverageAnalysisTopComponent() );
        putClientProperty( TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE );
        TopComponentHelper.setupContainerListener( coverageAnalysisTabbedPanel, preferredID() );
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coverageAnalysisTabbedPanel = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverageAnalysisTabbedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverageAnalysisTabbedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane coverageAnalysisTabbedPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // add custom code on component opening
    }


    @Override
    public void componentClosed() {
        // add custom code on component closing
    }


    void writeProperties( java.util.Properties p ) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty( "version", "1.0" );
        // store your settings
    }


    void readProperties( java.util.Properties p ) {
        String version = p.getProperty( "version" );
        // read your settings according to their version
    }


    /**
     * This method needs to be called in order to open a new tab for a covered
     * feature detection. Make sure to call {@link setAnalysisContext()} first
     * in order to display the correct context for the analysis result.
     * <p>
     * @param panelName   title of the new tab to create
     * @param resultPanel the panel to place in the new tab
     */
    public void openAnalysisTab( String panelName, JPanel resultPanel ) {
        TopComponentHelper.openTableTab( coverageAnalysisTabbedPanel, panelName, resultPanel );
    }


    /**
     * @return true, if this component already contains other components, false
     *         otherwise.
     */
    public boolean hasComponents() {
        return this.coverageAnalysisTabbedPanel.getComponentCount() > 0;
    }


}
