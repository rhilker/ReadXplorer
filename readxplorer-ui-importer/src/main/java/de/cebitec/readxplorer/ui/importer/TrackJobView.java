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

package de.cebitec.readxplorer.ui.importer;


import de.cebitec.readxplorer.parser.TrackJob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 * Shows a table containing the current track jobs to import.
 * <p>
 * @author ddoppmeier, rhilker
 */
public class TrackJobView extends javax.swing.JPanel implements
        ListSelectionListener {

    public static final long serialVersionUID = 774292377;

    private final List<TrackJob> tracks;
    private boolean hasJobs;


    /**
     * Creates new form TaskViewerTemplate
     */
    public TrackJobView() {
        tracks = new ArrayList<>();
        initComponents();
    }


    /**
     * @return the selected track job in the track job table
     */
    public TrackJob getSelectedItem() {
        return tracks.get( trackTable.getSelectedRow() );
    }


    /**
     * Adds a track job to this view
     * <p>
     * @param trackJob the track job to add
     */
    public void add( TrackJob trackJob ) {
        DefaultTableModel model = (DefaultTableModel) trackTable.getModel();
        model.addRow( new Object[]{
            trackJob.getFile().getName(),
            trackJob.getDescription(),
            trackJob.getRefGen().getDescription(),
            trackJob.isAlreadyImported() } );

        this.tracks.add( trackJob );
        if( !this.hasJobs ) {
            this.hasJobs = true;
            firePropertyChange( ImportSetupCard.PROP_HAS_JOBS, null, this.hasJobs );
        }
    }


    /**
     * Removes the given track job from the list of track jobs
     * <p>
     * @param trackJob the track job to remove
     */
    public void remove( TrackJob trackJob ) {
        int index = this.tracks.indexOf( trackJob );
        this.tracks.remove( trackJob );

        DefaultTableModel model = (DefaultTableModel) trackTable.getModel();
        model.removeRow( index );

        if( this.tracks.isEmpty() ) {
            this.hasJobs = false;
            firePropertyChange( ImportSetupCard.PROP_HAS_JOBS, null, hasJobs );
        }
    }


    /**
     * @return the list of all track jobs added to this track job view
     */
    public List<TrackJob> getJobs() {
        return Collections.unmodifiableList( tracks );
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        trackTable = new javax.swing.JTable();
        trackTable.getSelectionModel().addListSelectionListener(this);

        setPreferredSize(new java.awt.Dimension(400, 300));

        trackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File", "Description", "Reference", "Already imported"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trackTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(trackTable);
        if (trackTable.getColumnModel().getColumnCount() > 0) {
            trackTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.file")); // NOI18N
            trackTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.description")); // NOI18N
            trackTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.reference")); // NOI18N
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable trackTable;
    // End of variables declaration//GEN-END:variables


    @Override
    public void valueChanged( ListSelectionEvent e ) {
        ListSelectionModel model = (ListSelectionModel) e.getSource();
        if( model.isSelectionEmpty() ) {
            firePropertyChange( ImportSetupCard.PROP_JOB_SELECTED, null, Boolean.FALSE );
        } else {
            firePropertyChange( ImportSetupCard.PROP_JOB_SELECTED, null, Boolean.TRUE );
        }
    }


    /**
     * @return true, if at least one row is selected, false otherwise
     */
    public boolean isRowSelected() {
        ListSelectionModel model = trackTable.getSelectionModel();
        return !model.isSelectionEmpty();
    }


}
