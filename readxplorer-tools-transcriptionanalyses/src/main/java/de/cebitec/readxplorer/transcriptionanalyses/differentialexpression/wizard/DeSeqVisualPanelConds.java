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


import de.cebitec.readxplorer.databackend.dataObjects.PersistentTrack;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;


public final class DeSeqVisualPanelConds extends JPanel {

    private final DefaultListModel<PersistentTrack> trackListModel = new DefaultListModel<>();
    private final DefaultListModel<PersistentTrack> conditionOneModel = new DefaultListModel<>();
    private final DefaultListModel<PersistentTrack> conditionTwoModel = new DefaultListModel<>();
    private List<PersistentTrack> selectedTracks = new ArrayList<>();
    private String[] conds;
    private final List<Integer> groupA = new ArrayList<>();
    private final List<Integer> groupB = new ArrayList<>();


    /**
     * Creates new form DeSeqVisualPanelConds
     */
    public DeSeqVisualPanelConds() {
        initComponents();
    }


    @Override
    public String getName() {
        return "Select conditions";
    }


    public void updateTrackList( List<PersistentTrack> selectedTracks ) {
        if( !this.selectedTracks.equals( selectedTracks ) ) {
            this.selectedTracks = new ArrayList<>(selectedTracks );
            trackListModel.clear();
            conditionOneModel.clear();
            conditionTwoModel.clear();
            groupA.clear();
            groupB.clear();
            conds = new String[selectedTracks.size()];
            for( PersistentTrack persistentTrack : selectedTracks ) {
                trackListModel.addElement( persistentTrack );
            }
        }
    }


    public boolean conditionsComplete() {
        for( String cond : conds ) {
            if( cond == null ) {
                return false;
            }
        }
        return true;
    }


    public boolean workingWithoutReplicates() {
        return !(conditionOneModel.getSize() > 1 && conditionTwoModel.getSize() > 1);
    }


    public String[] getConditions() {
        return conds;
    }


    public List<Integer> getGroupA() {
        return groupA;
    }


    public List<Integer> getGroupB() {
        return groupB;
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
        trackList = new javax.swing.JList();
        addToConditionOne = new javax.swing.JButton();
        addToConditionTwo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        conditionOneList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        conditionTwoList = new javax.swing.JList();
        removeFromConditionOne = new javax.swing.JButton();
        removeFromConditionTwo = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel1.text_1")); // NOI18N

        trackList.setModel(trackListModel);
        jScrollPane1.setViewportView(trackList);

        org.openide.awt.Mnemonics.setLocalizedText(addToConditionOne, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.addToConditionOne.text_1")); // NOI18N
        addToConditionOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToConditionOneActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addToConditionTwo, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.addToConditionTwo.text_1")); // NOI18N
        addToConditionTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToConditionTwoActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel2.text_1")); // NOI18N

        conditionOneList.setModel(conditionOneModel);
        jScrollPane2.setViewportView(conditionOneList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel3.text_1")); // NOI18N

        conditionTwoList.setModel(conditionTwoModel);
        jScrollPane3.setViewportView(conditionTwoList);

        org.openide.awt.Mnemonics.setLocalizedText(removeFromConditionOne, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.removeFromConditionOne.text_1")); // NOI18N
        removeFromConditionOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromConditionOneActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeFromConditionTwo, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.removeFromConditionTwo.text_1")); // NOI18N
        removeFromConditionTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromConditionTwoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeFromConditionOne, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addToConditionOne, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addToConditionTwo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(removeFromConditionTwo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(addToConditionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromConditionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addToConditionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromConditionTwo)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToConditionOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToConditionOneActionPerformed
        List<PersistentTrack> tracks = trackList.getSelectedValuesList();
        for( PersistentTrack persistentTrack : tracks ) {
            trackListModel.removeElement( persistentTrack );
            conditionOneModel.addElement( persistentTrack );
            conds[selectedTracks.indexOf( persistentTrack )] = "ONE";
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupA.add( (Integer) (selectedTracks.indexOf( persistentTrack ) + 1) );
        }
    }//GEN-LAST:event_addToConditionOneActionPerformed

    private void addToConditionTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToConditionTwoActionPerformed
        List<PersistentTrack> tracks = trackList.getSelectedValuesList();
        for( PersistentTrack persistentTrack : tracks ) {
            trackListModel.removeElement( persistentTrack );
            conditionTwoModel.addElement( persistentTrack );
            conds[selectedTracks.indexOf( persistentTrack )] = "TWO";
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupB.add( (Integer) (selectedTracks.indexOf( persistentTrack ) + 1) );
        }
    }//GEN-LAST:event_addToConditionTwoActionPerformed

    private void removeFromConditionOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromConditionOneActionPerformed
        List<PersistentTrack> tracks = conditionOneList.getSelectedValuesList();
        for( PersistentTrack persistentTrack : tracks ) {
            conditionOneModel.removeElement( persistentTrack );
            trackListModel.addElement( persistentTrack );
            conds[selectedTracks.indexOf( persistentTrack )] = null;
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupA.remove( (Integer) (selectedTracks.indexOf( persistentTrack ) + 1) );
        }
    }//GEN-LAST:event_removeFromConditionOneActionPerformed

    private void removeFromConditionTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromConditionTwoActionPerformed
        List<PersistentTrack> tracks = conditionTwoList.getSelectedValuesList();
        for( PersistentTrack persistentTrack : tracks ) {
            conditionTwoModel.removeElement( persistentTrack );
            trackListModel.addElement( persistentTrack );
            conds[selectedTracks.indexOf( persistentTrack )] = null;
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupB.remove( (Integer) (selectedTracks.indexOf( persistentTrack ) + 1) );
        }
    }//GEN-LAST:event_removeFromConditionTwoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToConditionOne;
    private javax.swing.JButton addToConditionTwo;
    private javax.swing.JList conditionOneList;
    private javax.swing.JList conditionTwoList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton removeFromConditionOne;
    private javax.swing.JButton removeFromConditionTwo;
    private javax.swing.JList trackList;
    // End of variables declaration//GEN-END:variables
}
