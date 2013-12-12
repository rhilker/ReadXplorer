/*
 * ReferenceEditor.java
 *
 * Created on 13.01.2011, 11:38:35
 */
package de.cebitec.readXplorer.tools.referenceEditor;

import de.cebitec.readXplorer.databackend.dataObjects.ChromosomeObserver;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantReference;
import de.cebitec.readXplorer.util.SequenceUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.NotifyDescriptor;

/**
 * An editor showing a part of the currently viewed chromosome sequence. It 
 * allows to edit the sequence and copy or store it.
 *
 * @author jstraube, rhilker
 */
public class ReferenceEditor extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private PersistantReference refGen;
    public String currentRefGen;
    public String activeChromSeq;



    /** 
     * An editor showing a part of the currently viewed chromosome sequence. It
     * allows to edit the sequence and copy or store it.
     */
    public ReferenceEditor(PersistantReference reference) {
        this.setSize(300, 300);

        if (reference != null) {
            this.refGen = reference;
            this.setTitle(refGen.getName());
            ChromosomeObserver chromObserver = new ChromosomeObserver();
            activeChromSeq = refGen.getActiveChromSequence(chromObserver);
            refGen.getChromosome(refGen.getActiveChromId()).removeObserver(chromObserver);
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "PersistanceReference is null {0}!");
        }
        myInit();
        this.setLocation(100, 300);
        this.setVisible(true);
    }

    private void myInit() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fromSpinner = new javax.swing.JSpinner();
        toSpinner = new javax.swing.JSpinner();
        revComlementCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        genomeTextArea = new javax.swing.JTextArea();
        getSequenceButton = new javax.swing.JButton();

        // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel3.text")); // NOI18N

        revComlementCheckBox.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.revComlementCheckBox.text")); // NOI18N

        genomeTextArea.setColumns(20);
        genomeTextArea.setRows(5);
        jScrollPane1.setViewportView(genomeTextArea);

        getSequenceButton.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.getSequenceButton.text")); // NOI18N
        getSequenceButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getSequenceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addComponent(revComlementCheckBox)))
                .addGap(110, 110, 110)
                .addComponent(getSequenceButton))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(getSequenceButton)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(revComlementCheckBox)))
                .addContainerGap()));

        pack();

    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fromSpinner = new javax.swing.JSpinner();
        toSpinner = new javax.swing.JSpinner();
        revComlementCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        genomeTextArea = new javax.swing.JTextArea();
        getSequenceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.jLabel3.text")); // NOI18N

        revComlementCheckBox.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.revComlementCheckBox.text")); // NOI18N

        genomeTextArea.setColumns(20);
        genomeTextArea.setRows(5);
        jScrollPane1.setViewportView(genomeTextArea);

        getSequenceButton.setText(org.openide.util.NbBundle.getMessage(ReferenceEditor.class, "ReferenceEditor.getSequenceButton.text")); // NOI18N
        getSequenceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getSequenceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(toSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addComponent(revComlementCheckBox)))
                        .addGap(110, 110, 110)
                        .addComponent(getSequenceButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(getSequenceButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(revComlementCheckBox)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getSequenceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getSequenceButtonActionPerformed
        int from = (Integer) fromSpinner.getValue();
        int to = (Integer) toSpinner.getValue();

        if(from > 0 && to > 0 && from <= activeChromSeq.length() && to <activeChromSeq.length()&& from < to ){
            currentRefGen = activeChromSeq.substring(from -1, to);

            if(revComlementCheckBox.isSelected()){
               currentRefGen = reverseComplement(currentRefGen);
            }
            genomeTextArea.setLineWrap(true);
             genomeTextArea.setText("");
            genomeTextArea.setText(currentRefGen);
        }else{
             NotifyDescriptor nd = new NotifyDescriptor.Message("The values don't fit in the genome range", NotifyDescriptor.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_getSequenceButtonActionPerformed

        public String reverseComplement(String readSeq) {
        String revBase = "";
        for (int i = 0; i < readSeq.length(); i++) {
            Character base = readSeq.charAt(i);
            base = Character.toUpperCase(base);
            base = SequenceUtils.getDnaComplement(base);
            revBase += base;
        }
        return revBase;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner fromSpinner;
    private javax.swing.JTextArea genomeTextArea;
    private javax.swing.JButton getSequenceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox revComlementCheckBox;
    private javax.swing.JSpinner toSpinner;
    // End of variables declaration//GEN-END:variables

}
