/*
 * Copyright (C) 2014 Kai Bernd Stadermann <kstaderm at cebitec.uni-bielefeld.de>
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

package de.cebitec.readxplorer.transcriptionanalyses.differentialexpression.wizard;


import de.cebitec.readxplorer.databackend.dataobjects.PersistentTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;


public final class BaySeqVisualPanel2 extends JPanel {

    private static final long serialVersionUID = 1L;

    private List<PersistentTrack> selectedTracks = new ArrayList<>();
    private final DefaultListModel<PersistentTrack> trackListModel = new DefaultListModel<>();
    private int[] replicateStructure = new int[1];
    private int currentReplicateNumber = 1;


    /**
     * Creates new form bspVisualPanel2
     */
    public BaySeqVisualPanel2() {
        initComponents();
    }


    @Override
    public String getName() {
        return "Define replicates structure";
    }


    public void updateTrackList( List<PersistentTrack> selectedTracks ) {
        if( !this.selectedTracks.equals( selectedTracks ) ) {
            this.selectedTracks = new ArrayList<>( selectedTracks );
            resetTrackList();
        }

    }


    private void resetTrackList() {
        trackListModel.clear();
        for( PersistentTrack persistentTrack : selectedTracks ) {
            trackListModel.addElement( persistentTrack );
            replicateStructure = new int[1];
            replicateStructureField.setText( "" );
            addAsReplicates.setEnabled( true );
            removeReplicates.setEnabled( false );
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trackList = new javax.swing.JList(trackListModel);
        jLabel2 = new javax.swing.JLabel();
        addAsReplicates = new javax.swing.JButton();
        removeReplicates = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        replicateStructureField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel2.class, "BaySeqVisualPanel2.jLabel1.text_1")); // NOI18N

        jScrollPane1.setViewportView(trackList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel2.class, "BaySeqVisualPanel2.jLabel2.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addAsReplicates, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel2.class, "BaySeqVisualPanel2.addAsReplicates.text_1")); // NOI18N
        addAsReplicates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAsReplicatesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeReplicates, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel2.class, "BaySeqVisualPanel2.removeReplicates.text_1")); // NOI18N
        removeReplicates.setEnabled(false);
        removeReplicates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeReplicatesActionPerformed(evt);
            }
        });

        replicateStructureField.setEditable(false);
        replicateStructureField.setText(org.openide.util.NbBundle.getMessage(BaySeqVisualPanel2.class, "BaySeqVisualPanel2.replicateStructureField.text_1")); // NOI18N
        replicateStructureField.setBorder(null);
        jScrollPane2.setViewportView(replicateStructureField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addAsReplicates)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeReplicates))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAsReplicates)
                    .addComponent(removeReplicates))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addAsReplicatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAsReplicatesActionPerformed
        if( replicateStructure.length < 2 ) {
            replicateStructure = new int[selectedTracks.size()];
        }
        if( !trackList.isSelectionEmpty() ) {
            List<PersistentTrack> tracks = trackList.getSelectedValuesList();
            StringBuilder strBuilder = new StringBuilder( replicateStructureField.getText() + "{" );
            for( Iterator<PersistentTrack> it = tracks.iterator(); it.hasNext(); ) {
                PersistentTrack persistentTrack = it.next();
                replicateStructure[selectedTracks.indexOf( persistentTrack )] = currentReplicateNumber;
                strBuilder.append( persistentTrack.getDescription() );
                trackListModel.removeElement( persistentTrack );
                if( it.hasNext() ) {
                    strBuilder.append( "," );
                } else {
                    strBuilder.append( "}" );
                }
            }
            replicateStructureField.setText( strBuilder.toString() );
            currentReplicateNumber++;
        }
        removeReplicates.setEnabled( true );
        if( trackListModel.isEmpty() ) {
            addAsReplicates.setEnabled( false );
        }
    }//GEN-LAST:event_addAsReplicatesActionPerformed

    private void removeReplicatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeReplicatesActionPerformed
        removeReplicates.setEnabled( false );
        replicateStructureField.setText( "" );
        replicateStructure = new int[1];
        resetTrackList();
        addAsReplicates.setEnabled( true );
    }//GEN-LAST:event_removeReplicatesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAsReplicates;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeReplicates;
    private javax.swing.JTextField replicateStructureField;
    private javax.swing.JList<PersistentTrack> trackList;
    // End of variables declaration//GEN-END:variables


    public int[] getCreatedReplicates() {
        return replicateStructure;
    }


    public boolean noReplicatesCreated() {
        for( int i = 0; i < replicateStructure.length; i++ ) {
            if( replicateStructure[i] == 0 ) {
                return true;
            }
        }
        return false;
    }


}
