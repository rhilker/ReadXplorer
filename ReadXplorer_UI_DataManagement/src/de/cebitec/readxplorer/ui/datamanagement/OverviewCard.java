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

package de.cebitec.readxplorer.ui.datamanagement;


import de.cebitec.readxplorer.parser.ReferenceJob;
import de.cebitec.readxplorer.parser.TrackJob;
import java.util.List;
import org.openide.util.NbBundle;


/**
 *
 * @author ddoppmeier
 */
public class OverviewCard extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;


    /**
     * Creates new form OverviewCard
     */
    public OverviewCard() {
        initComponents();
    }


    public void showGenereateOverview( List<ReferenceJob> scheduledRefGenJobs, List<TrackJob> scheduledTrackJobs ) {
        overviewTextArea.setText( "" );

        if( !scheduledRefGenJobs.isEmpty() ) {
            overviewTextArea.append( "References:\n" );
            for( ReferenceJob r : scheduledRefGenJobs ) {
                overviewTextArea.append( r.getName() + "\n" );
                overviewTextArea.append( "\t" + r.getDescription() + "\n" );
            }
            overviewTextArea.append( "\n" );
        }

        if( !scheduledTrackJobs.isEmpty() ) {
            overviewTextArea.append( "Tracks:\n" );
            for( TrackJob r : scheduledTrackJobs ) {
                overviewTextArea.append( r.getDescription() + "\n" );
            }
            overviewTextArea.append( "\n" );
        }
    }


    @Override
    public String getName() {
        return NbBundle.getMessage( OverviewCard.class, "CTL_OverviewCard.name" );
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        overviewTextArea = new javax.swing.JTextArea();

        overviewTextArea.setColumns(20);
        overviewTextArea.setRows(5);
        jScrollPane1.setViewportView(overviewTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea overviewTextArea;
    // End of variables declaration//GEN-END:variables

}
