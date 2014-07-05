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
package de.cebitec.readXplorer.differentialExpression.wizard;

import de.cebitec.readXplorer.databackend.dataObjects.PersistantTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public final class DeSeqVisualPanelConds extends JPanel {

    private DefaultListModel<PersistantTrack> trackListModel = new DefaultListModel<>();
    private DefaultListModel<PersistantTrack> conditionOneModel = new DefaultListModel<>();
    private DefaultListModel<PersistantTrack> conditionTwoModel = new DefaultListModel<>();
    private List<PersistantTrack> selectedTracks = new ArrayList<>();
    private String[] conds;
    private List<Integer> groupA = new ArrayList<>();
    private List<Integer> groupB = new ArrayList<>();

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

    public void updateTrackList(List<PersistantTrack> selectedTracks) {
        if (!this.selectedTracks.equals(selectedTracks)) {
            this.selectedTracks = selectedTracks;
            trackListModel.clear();
            conditionOneModel.clear();
            conditionTwoModel.clear();
            groupA.clear();
            groupB.clear();
            conds = new String[selectedTracks.size()];
            for (Iterator<PersistantTrack> it = selectedTracks.iterator(); it.hasNext();) {
                PersistantTrack persistantTrack = it.next();
                trackListModel.addElement(persistantTrack);
            }
        }
    }

    public boolean conditionsComplete() {
        for (int i = 0; i < conds.length; i++) {
            if (conds[i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean workingWithoutReplicates() {
        if (conditionOneModel.getSize() > 1 && conditionTwoModel.getSize() > 1) {
            return false;
        } else {
            return true;
        }
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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel1.text")); // NOI18N

        trackList.setModel(trackListModel);
        jScrollPane1.setViewportView(trackList);

        org.openide.awt.Mnemonics.setLocalizedText(addToConditionOne, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.addToConditionOne.text")); // NOI18N
        addToConditionOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToConditionOneActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addToConditionTwo, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.addToConditionTwo.text")); // NOI18N
        addToConditionTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToConditionTwoActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel2.text")); // NOI18N

        conditionOneList.setModel(conditionOneModel);
        jScrollPane2.setViewportView(conditionOneList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.jLabel3.text")); // NOI18N

        conditionTwoList.setModel(conditionTwoModel);
        jScrollPane3.setViewportView(conditionTwoList);

        org.openide.awt.Mnemonics.setLocalizedText(removeFromConditionOne, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.removeFromConditionOne.text")); // NOI18N
        removeFromConditionOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromConditionOneActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeFromConditionTwo, org.openide.util.NbBundle.getMessage(DeSeqVisualPanelConds.class, "DeSeqVisualPanelConds.removeFromConditionTwo.text")); // NOI18N
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addToConditionTwo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addToConditionOne, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(removeFromConditionOne)
                            .addComponent(removeFromConditionTwo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToConditionOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToConditionOneActionPerformed
        List<PersistantTrack> tracks = trackList.getSelectedValuesList();
        for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            trackListModel.removeElement(persistantTrack);
            conditionOneModel.addElement(persistantTrack);
            conds[selectedTracks.indexOf(persistantTrack)] = "ONE";
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupA.add((Integer) (selectedTracks.indexOf(persistantTrack) + 1));
        }
    }//GEN-LAST:event_addToConditionOneActionPerformed

    private void addToConditionTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToConditionTwoActionPerformed
        List<PersistantTrack> tracks = trackList.getSelectedValuesList();
        for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            trackListModel.removeElement(persistantTrack);
            conditionTwoModel.addElement(persistantTrack);
            conds[selectedTracks.indexOf(persistantTrack)] = "TWO";
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupB.add((Integer) (selectedTracks.indexOf(persistantTrack) + 1));
        }
    }//GEN-LAST:event_addToConditionTwoActionPerformed

    private void removeFromConditionOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromConditionOneActionPerformed
        List<PersistantTrack> tracks = conditionOneList.getSelectedValuesList();
        for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            conditionOneModel.removeElement(persistantTrack);
            trackListModel.addElement(persistantTrack);
            conds[selectedTracks.indexOf(persistantTrack)] = null;
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupA.remove((Integer) (selectedTracks.indexOf(persistantTrack) + 1));
        }
    }//GEN-LAST:event_removeFromConditionOneActionPerformed

    private void removeFromConditionTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromConditionTwoActionPerformed
        List<PersistantTrack> tracks = conditionTwoList.getSelectedValuesList();
        for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            conditionTwoModel.removeElement(persistantTrack);
            trackListModel.addElement(persistantTrack);
            conds[selectedTracks.indexOf(persistantTrack)] = null;
            //Java indexing starts at 0 but these values will be passed directly to
            //Gnu R and should correspond to the columns of the count data. Therefore
            //you have to add one to the index used by Java.
            groupB.remove((Integer) (selectedTracks.indexOf(persistantTrack) + 1));
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
