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

package de.cebitec.readxplorer.ui.visualisation.reference;


import de.cebitec.readxplorer.api.enums.FeatureType;
import de.cebitec.readxplorer.ui.TopComponentExtended;
import de.cebitec.readxplorer.ui.datavisualisation.BoundsInfo;
import de.cebitec.readxplorer.ui.datavisualisation.MousePositionListener;
import de.cebitec.readxplorer.ui.datavisualisation.abstractviewer.AbstractViewer;
import de.cebitec.readxplorer.ui.datavisualisation.referenceviewer.ReferenceViewer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;


/**
 * Top component which displays something.
 */
@ConvertAsProperties( dtd = "-//de.cebitec.readxplorer.ui.visualisation.reference//ReferenceInterval//EN", autostore = false )
public final class ReferenceIntervalTopComp extends TopComponentExtended
        implements LookupListener, MousePositionListener {

    private static final Logger LOG = Logger.getLogger( ReferenceIntervalTopComp.class.getName() );

    private static final long serialVersionUID = 1L;
    private static ReferenceIntervalTopComp instance;
    private Result<ReferenceViewer> result;
    private boolean showCurrentPosition = true;
    /**
     * path to the icon used by the component and its open action
     */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "ReferenceIntervalTopComp";


    public ReferenceIntervalTopComp() {
        this.initComponents();
        this.setName( NbBundle.getMessage( ReferenceIntervalTopComp.class, "CTL_ReferenceIntervalTopComp" ) );
        this.setToolTipText( NbBundle.getMessage( ReferenceIntervalTopComp.class, "HINT_ReferenceIntervalTopComp" ) );
//      this.setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        this.putClientProperty( TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE );
        this.putClientProperty( TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE );
        this.putClientProperty( TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE );

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        intervalFromField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        intervalToField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        statisticsList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        currentPosField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        codonSelector = new de.cebitec.readxplorer.ui.visualisation.reference.CodonSelector();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel1.toolTipText")); // NOI18N

        intervalFromField.setEditable(false);
        intervalFromField.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.intervalFromField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel2.toolTipText")); // NOI18N

        intervalToField.setEditable(false);
        intervalToField.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.intervalToField.toolTipText")); // NOI18N

        statisticsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { null };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        statisticsList.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.statisticsList.toolTipText")); // NOI18N
        jScrollPane1.setViewportView(statisticsList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel5.text")); // NOI18N
        jLabel5.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel5.toolTipText")); // NOI18N

        currentPosField.setEditable(false);
        currentPosField.setToolTipText(org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.currentPosField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(ReferenceIntervalTopComp.class, "ReferenceIntervalTopComp.jLabel6.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(codonSelector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentPosField)
                            .addComponent(intervalToField)
                            .addComponent(intervalFromField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intervalFromField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intervalToField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(currentPosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codonSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.cebitec.readxplorer.ui.visualisation.reference.CodonSelector codonSelector;
    private javax.swing.JTextField currentPosField;
    private javax.swing.JTextField intervalFromField;
    private javax.swing.JTextField intervalToField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> statisticsList;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files
     * only, i.e. deserialization routines; otherwise you could get a
     * non-deserialized instance. To obtain the singleton instance, use
     * {@link #findInstance}.
     * <p>
     * @return the singleton instance of this class
     */
    public static synchronized ReferenceIntervalTopComp getDefault() {
        if( instance == null ) {
            instance = new ReferenceIntervalTopComp();
        }
        return instance;
    }


    /**
     * Obtain the ReferenceIntervalTopComp instance. Never call
     * {@link #getDefault} directly!
     * <p>
     * @return the singleton instance of this class
     */
    public static synchronized ReferenceIntervalTopComp findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent( PREFERRED_ID );
        if( win == null ) {
            LOG.warning( "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system." );
            return getDefault();
        }
        if( win instanceof ReferenceIntervalTopComp ) {
            return (ReferenceIntervalTopComp) win;
        }
        LOG.warning( "There seem to be multiple components with the '" + PREFERRED_ID +
                     "' ID. That is a potential source of errors and unexpected behavior." );
        return getDefault();
    }


    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }


    @Override
    public void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult( ReferenceViewer.class );
        result.addLookupListener( this );
        resultChanged( new LookupEvent( result ) );
    }


    @Override
    public void resultChanged( LookupEvent ev ) {
        for( ReferenceViewer referenceViewer : result.allInstances() ) {
            this.codonSelector.setGenomeViewer( referenceViewer );

            // update visible feature list
            this.showFeatureStatsForInterval( referenceViewer.getFeatureStats() );

            // update intervall
            BoundsInfo boundsInfo = referenceViewer.getBoundsInfo();
            setInterval( boundsInfo.getLogLeft(), boundsInfo.getLogRight() );

            // register listeners so every change occurs
            referenceViewer.addPropertyChangeListener( ReferenceViewer.PROP_FEATURE_STATS_CHANGED, new PropertyChangeListener() {

                @Override
                public void propertyChange( PropertyChangeEvent evt ) {
                    // update visible feature list
                    @SuppressWarnings( "unchecked" )
                    Map<FeatureType, Integer> featureStats = (Map<FeatureType, Integer>) evt.getNewValue();
                    ReferenceIntervalTopComp.this.showFeatureStatsForInterval( featureStats );

                    // update intervall
                    BoundsInfo boundsInfo = ((AbstractViewer) evt.getSource()).getBoundsInfo();
                    setInterval( boundsInfo.getLogLeft(), boundsInfo.getLogRight() );
                }


            } );
            referenceViewer.addPropertyChangeListener( ReferenceViewer.PROP_MOUSEPOSITION_CHANGED, new PropertyChangeListener() {

                @Override
                public void propertyChange( PropertyChangeEvent evt ) {
                    setCurrentMousePosition( (Integer) evt.getNewValue() );
                }


            } );
            referenceViewer.addPropertyChangeListener( ReferenceViewer.PROP_MOUSEOVER_REQUESTED, new PropertyChangeListener() {

                @Override
                public void propertyChange( PropertyChangeEvent evt ) {
                    setMouseOverPaintingRequested( (Boolean) evt.getNewValue() );
                }


            } );
        }
    }


    @Override
    public void componentClosed() {
        result.removeLookupListener( this );
    }


    void writeProperties( java.util.Properties p ) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty( "version", "1.0" );
        // store your settings
    }


    Object readProperties( java.util.Properties p ) {
        if( instance == null ) {
            instance = this;
        }
        instance.readPropertiesImpl( p );
        return instance;
    }


    private void readPropertiesImpl( java.util.Properties p ) {
        String version = p.getProperty( "version" );
        // read your settings according to their version
    }


    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }


    private void setInterval( int from, int to ) {
        intervalFromField.setText( String.valueOf( from ) );
        intervalToField.setText( String.valueOf( to ) );
    }


    private void showFeatureStatsForInterval( Map<FeatureType, Integer> featureStats ) {
        statisticsList.removeAll();
        DefaultListModel<String> model = new DefaultListModel<>();

        Set<FeatureType> keys = featureStats.keySet();
        for( FeatureType type : keys ) {
            String typeS = type.getString();
            model.addElement( typeS + ": " + featureStats.get( type ) );
        }
        statisticsList.setModel( model );
    }


    @Override
    public void setCurrentMousePosition( int logPos ) {
        if( showCurrentPosition ) {
            currentPosField.setText( String.valueOf( logPos ) );
        } else {
            currentPosField.setText( "" );
        }
    }


    @Override
    public void setMouseOverPaintingRequested( boolean requested ) {
        showCurrentPosition = requested;
        if( !showCurrentPosition ) {
            currentPosField.setText( "" );
        }
    }


}
