package de.cebitec.vamp.differentialExpression.wizard;

import de.cebitec.vamp.databackend.dataObjects.PersistantTrack;
import de.cebitec.vamp.differentialExpression.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class BaySeqVisualPanel3 extends JPanel implements ListSelectionListener {

    private DefaultListModel<PersistantTrack> trackListModel = new DefaultListModel<>();
    private DefaultListModel<String> groupListModel = new DefaultListModel<>();
    private List<Group> createdGroups = new ArrayList<>();
    private List<PersistantTrack> selectedTraks = null;
    private Integer[] currentGroupBeingCreated = null;
    private int currentGroupNumber = 1;
    private int selectedIndex = -1;

    /**
     * Creates new form BaySeqVisualPanel3
     */
    public BaySeqVisualPanel3() {
        initComponents();
    }

    public void updateTrackList(List<PersistantTrack> selectedTraks) {
        if (this.selectedTraks == null) {
            this.selectedTraks = selectedTraks;
            Integer[] defaultGroup = new Integer[selectedTraks.size()];
            StringBuilder strBuilder = new StringBuilder("{");
            for (Iterator<PersistantTrack> it = selectedTraks.iterator(); it.hasNext();) {
                PersistantTrack persistantTrack = it.next();
                defaultGroup[selectedTraks.indexOf(persistantTrack)] = currentGroupNumber;
                strBuilder.append(persistantTrack.getDescription());
                if (it.hasNext()) {
                    strBuilder.append(",");
                } else {
                    strBuilder.append("}");
                }
            }
            currentGroupNumber++;
            createdGroups.add(new Group(defaultGroup, strBuilder.toString()));
            infoText.setText("The group "+strBuilder.toString()+" is created automatically.");
        }
        trackListModel.clear();
        for (Iterator<PersistantTrack> it = selectedTraks.iterator(); it.hasNext();) {
            PersistantTrack persistantTrack = it.next();
            trackListModel.addElement(persistantTrack);
        }
    }

    @Override
    public String getName() {
        return "Create groups";
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
        groupCreationField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        createdGroupsList = new javax.swing.JList(groupListModel);
        jLabel2 = new javax.swing.JLabel();
        addGroupButton = new javax.swing.JButton();
        removeGroupButton = new javax.swing.JButton();
        infoText = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.jLabel1.text")); // NOI18N

        jScrollPane1.setViewportView(trackList);

        groupCreationField.setEditable(false);
        groupCreationField.setText(org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.groupCreationField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        createdGroupsList.addListSelectionListener(this);
        createdGroupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(createdGroupsList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addGroupButton, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.addGroupButton.text")); // NOI18N
        addGroupButton.setEnabled(false);
        addGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeGroupButton, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.removeGroupButton.text")); // NOI18N
        removeGroupButton.setEnabled(false);
        removeGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGroupButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(infoText, org.openide.util.NbBundle.getMessage(BaySeqVisualPanel3.class, "BaySeqVisualPanel3.infoText.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(addGroupButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(removeGroupButton))
                                            .addComponent(groupCreationField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(groupCreationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addGroupButton)
                            .addComponent(removeGroupButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoText, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (currentGroupBeingCreated == null) {
            currentGroupBeingCreated = new Integer[selectedTraks.size()];
        }
        if (!trackList.isSelectionEmpty()) {
            List<PersistantTrack> tracks = trackList.getSelectedValuesList();
            StringBuilder strBuilder = new StringBuilder(groupCreationField.getText() + "{");
            for (Iterator<PersistantTrack> it = tracks.iterator(); it.hasNext();) {
                PersistantTrack persistantTrack = it.next();
                currentGroupBeingCreated[selectedTraks.indexOf(persistantTrack)] = currentGroupNumber;
                strBuilder.append(persistantTrack.getDescription());
                trackListModel.removeElement(persistantTrack);
                if (it.hasNext()) {
                    strBuilder.append(",");
                } else {
                    strBuilder.append("}");
                }
            }
            groupCreationField.setText(strBuilder.toString());
            currentGroupNumber++;
        }
        if (trackListModel.isEmpty()) {
            addGroupButton.setEnabled(true);
            addButton.setEnabled(false);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void addGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupButtonActionPerformed

        createdGroups.add(new Group(currentGroupBeingCreated, groupCreationField.getText()));
        currentGroupBeingCreated = null;
        groupListModel.addElement(groupCreationField.getText());
        groupCreationField.setText("");
        updateTrackList(selectedTraks);
        addButton.setEnabled(true);
        addGroupButton.setEnabled(false);
    }//GEN-LAST:event_addGroupButtonActionPerformed

    private void removeGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGroupButtonActionPerformed
        createdGroups.remove(selectedIndex);
        groupListModel.remove(selectedIndex);
        selectedIndex = -1;
        removeGroupButton.setEnabled(false);
    }//GEN-LAST:event_removeGroupButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton addGroupButton;
    private javax.swing.JList createdGroupsList;
    private javax.swing.JTextField groupCreationField;
    private javax.swing.JLabel infoText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeGroupButton;
    private javax.swing.JList trackList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (selectedIndex != e.getFirstIndex()) {
            selectedIndex = e.getFirstIndex();
            removeGroupButton.setEnabled(true);
        }
    }

    public List<Group> getCreatedGroups() {
        return createdGroups;
    }

    public boolean noGroupCreated() {
        return createdGroups.isEmpty();
    }
}
