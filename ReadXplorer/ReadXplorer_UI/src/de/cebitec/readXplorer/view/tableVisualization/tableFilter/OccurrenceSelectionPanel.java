package de.cebitec.readXplorer.view.tableVisualization.tableFilter;

import de.cebitec.readXplorer.util.GeneralUtils;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;

/**
 * Create a dialog window for the OccurrenceFilter.
 * 
 * @author Margarita Steinhauer, Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public class OccurrenceSelectionPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;


    /**
     * Creates a dialog window for the OccurrenceFilter.
     */
    public OccurrenceSelectionPanel() {
        initComponents();
    }
    
     /**
     * This method regulates the activity of the buttons.
     * The field should only be able to work if the allOccurrenceButton
     * is not selected.
     */
    private void buttonAction() {
        if (allTracksButton.isSelected()) {
            trackNumberField.setEditable(false);
        }
        if (minimumOccurrenceButton.isSelected()
                || maximumOccurrenceButton.isSelected()) {
            trackNumberField.setEditable(true);
        }
    }
    
    /**
     * Getter for trackNumberField
     * @return trackNumberField contains the information about the max and min 
     * occurrence of an event in a certain track given by the user.
     */
    public javax.swing.JTextField getOccurrenceField() {
        return trackNumberField;
    }
    
    /**
     * Getter for selected Buttons.
     * @return String "all" if the user selects all in the dialog, "min" for 
     * min or "max" for a max occurrence.
     */
    public String getSelectedButton() {
    
        String selectedButton;
        if (allTracksButton.isSelected()) {
            selectedButton = "all";
        } else if (minimumOccurrenceButton.isSelected()) {
            selectedButton = "min";
        } else { //if (maximumOccurrenceButton.isSelected()) {
            selectedButton = "max";
        }
        return selectedButton;
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        occurranceSelectionGroup = new javax.swing.ButtonGroup();
        trackNumberField = new javax.swing.JTextField();
        minimumOccurrenceButton = new javax.swing.JRadioButton();
        maximumOccurrenceButton = new javax.swing.JRadioButton();
        allTracksButton = new javax.swing.JRadioButton();
        trackNumberLabel = new javax.swing.JLabel();
        filterLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        explanationTextPane = new javax.swing.JTextArea();

        trackNumberField.setText(org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.trackNumberField.text")); // NOI18N
        trackNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                trackNumberFieldKeyReleased(evt);
            }
        });

        occurranceSelectionGroup.add(minimumOccurrenceButton);
        minimumOccurrenceButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(minimumOccurrenceButton, org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.minimumOccurrenceButton.text")); // NOI18N
        minimumOccurrenceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimumOccurrenceButtonActionPerformed(evt);
            }
        });

        occurranceSelectionGroup.add(maximumOccurrenceButton);
        org.openide.awt.Mnemonics.setLocalizedText(maximumOccurrenceButton, org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.maximumOccurrenceButton.text")); // NOI18N
        maximumOccurrenceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximumOccurrenceButtonActionPerformed(evt);
            }
        });

        occurranceSelectionGroup.add(allTracksButton);
        org.openide.awt.Mnemonics.setLocalizedText(allTracksButton, org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.allTracksButton.text")); // NOI18N
        allTracksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTracksButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(trackNumberLabel, org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.trackNumberLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(filterLabel, org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.filterLabel.text")); // NOI18N

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        explanationTextPane.setBackground(new java.awt.Color(240, 240, 240));
        explanationTextPane.setColumns(20);
        explanationTextPane.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        explanationTextPane.setLineWrap(true);
        explanationTextPane.setRows(5);
        explanationTextPane.setText(org.openide.util.NbBundle.getMessage(OccurrenceSelectionPanel.class, "OccurrenceSelectionPanel.explanationTextPane.text")); // NOI18N
        explanationTextPane.setWrapStyleWord(true);
        explanationTextPane.setPreferredSize(new java.awt.Dimension(164, 34));
        jScrollPane1.setViewportView(explanationTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trackNumberLabel)
                            .addComponent(filterLabel))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minimumOccurrenceButton)
                            .addComponent(allTracksButton)
                            .addComponent(maximumOccurrenceButton)
                            .addComponent(trackNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(minimumOccurrenceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maximumOccurrenceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allTracksButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trackNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trackNumberLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minimumOccurrenceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimumOccurrenceButtonActionPerformed
        buttonAction();
    }//GEN-LAST:event_minimumOccurrenceButtonActionPerformed

    private void maximumOccurrenceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximumOccurrenceButtonActionPerformed
        buttonAction();
    }//GEN-LAST:event_maximumOccurrenceButtonActionPerformed

    private void allTracksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTracksButtonActionPerformed
        buttonAction();
    }//GEN-LAST:event_allTracksButtonActionPerformed

    @NbBundle.Messages("MSG_PosNumber=Please insert only positive number values.")
    private void trackNumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trackNumberFieldKeyReleased
        if (!GeneralUtils.isValidPositiveNumberInput(trackNumberField.getText())) {
            JOptionPane.showMessageDialog(null, Bundle.MSG_PosNumber());
            trackNumberField.setText("");
        }
    }//GEN-LAST:event_trackNumberFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allTracksButton;
    private javax.swing.JTextArea explanationTextPane;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton maximumOccurrenceButton;
    private javax.swing.JRadioButton minimumOccurrenceButton;
    private javax.swing.ButtonGroup occurranceSelectionGroup;
    private javax.swing.JTextField trackNumberField;
    private javax.swing.JLabel trackNumberLabel;
    // End of variables declaration//GEN-END:variables


   
}
