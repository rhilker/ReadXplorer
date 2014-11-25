package de.cebitec.vamp.tools.snp;

import de.cebitec.vamp.api.objects.JobPanel;
import de.cebitec.vamp.util.GeneralUtils;
import de.cebitec.vamp.view.dialogMenus.ChangeListeningWizardPanel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Panel displaying all options for a SNP detection.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public final class SNPVisualPanel extends JobPanel {
    
    private static final long serialVersionUID = 1L;
    private Object minPercentage = 90;
    private int minMismatchBases = 15;
    private boolean useMainBase = true;

    /**
     * Panel displaying all options for a SNP detection.
     */
    public SNPVisualPanel() {
        initComponents();
        
        this.absNumText.getDocument().addDocumentListener(this.createDocumentListener());
        
        ((DefaultEditor) percentSpinner.getEditor()).getTextField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                isRequiredInfoSet();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isRequiredInfoSet();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
//                isRequiredInfoSet();
            }
        });
    }

    @Override
    public String getName() {
        return "SNP parameter setup";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        percentSpinner = new javax.swing.JSpinner(new SpinnerNumberModel());
        jPanel1 = new javax.swing.JPanel();
        absNumText = new javax.swing.JTextField();
        useMainBaseBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(SNPVisualPanel.class, "SNPVisualPanel.jTextArea1.text")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SNPVisualPanel.class, "SNPVisualPanel.jLabel2.text")); // NOI18N

        percentSpinner.setValue(minPercentage);
        percentSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                percentSpinnerStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        absNumText.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        absNumText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        absNumText.setText(String.valueOf(minMismatchBases));

        useMainBaseBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(useMainBaseBox, org.openide.util.NbBundle.getMessage(SNPVisualPanel.class, "SNPVisualPanel.useMainBaseBox.text")); // NOI18N
        useMainBaseBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useMainBaseBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SNPVisualPanel.class, "SNPVisualPanel.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(absNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(useMainBaseBox, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(absNumText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useMainBaseBox)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 76, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(percentSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(percentSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void percentSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_percentSpinnerStateChanged
        this.isRequiredInfoSet();
    }//GEN-LAST:event_percentSpinnerStateChanged

    private void useMainBaseBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useMainBaseBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useMainBaseBoxActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField absNumText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSpinner percentSpinner;
    private javax.swing.JCheckBox useMainBaseBox;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the minimum percentage of mismatches at a SNP position
     */
    public Object getMinPercentage() {
        return this.minPercentage;
    }

    /**
     * @return the minimum number of mismatches at a SNP position
     */
    public int getMinMismatchingBases() {
        return this.minMismatchBases;
    }

    /**
     * @return <cc>true</cc>, if the minMismatchBases count corresponds to the
     * count of the most frequent base at the current position. <cc>false</cc>,
     * if the minMismatchBases count corresponds to the overall mismatch count at
     * the current position.
     */
    public boolean isUseMainBase() {
        return this.useMainBase;
    }
    
    /**
     * Checks if all required information to start the SNP analysis is set.
     */
    @Override
    public boolean isRequiredInfoSet() {
        boolean isValidated = true;
        if (GeneralUtils.isValidPositiveNumberInput(absNumText.getText())) {
            this.minMismatchBases = Integer.parseInt(absNumText.getText());
        } else {
            isValidated = false;
        }
        JTextField spinnerField = ((DefaultEditor) percentSpinner.getEditor()).getTextField();
        String newString = spinnerField.getText();
        if (GeneralUtils.isValidPercentage(newString)) {
            this.minPercentage = this.percentSpinner.getValue();
        } else {
            isValidated = false;
        }
        this.useMainBase = this.useMainBaseBox.isSelected();
        
        firePropertyChange(ChangeListeningWizardPanel.PROP_VALIDATE, null, isValidated);
        return isValidated;
    }
    
}