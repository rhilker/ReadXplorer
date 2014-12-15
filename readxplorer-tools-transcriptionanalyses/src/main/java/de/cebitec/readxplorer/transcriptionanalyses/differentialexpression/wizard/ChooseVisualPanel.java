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


import de.cebitec.readxplorer.transcriptionanalyses.differentialexpression.DeAnalysisHandler;
import de.cebitec.readxplorer.transcriptionanalyses.differentialexpression.DeAnalysisHandler.Tool;
import de.cebitec.readxplorer.transcriptionanalyses.differentialexpression.GnuR;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;


public final class ChooseVisualPanel extends JPanel {

    private final ComboBoxModel cbm = new DefaultComboBoxModel( DeAnalysisHandler.Tool.usableTools() );


    /**
     * Creates new form ChooseVisualPanel
     */
    public ChooseVisualPanel() {
        initComponents();
        if( !GnuR.SecureGnuRInitiliser.isGnuRSetUpCorrect() ) {
            jriErrorText.setText( "GNU R is not installed.\nOnly the ExpressTest and the count table export can be used\nas long as no GNU R is installed." );
        }
        else {
            if( !GnuR.SecureGnuRInitiliser.isGnuRInstanceFree() ) {
                jriErrorText.setText( "GNU R instance is already in use.\nOnly the ExpressTest and the count table export can be used\nas long as an other GNU R based test is opened." );
            }
        }
    }


    public DeAnalysisHandler.Tool getSelectedTool() {
        return (Tool) cbm.getSelectedItem();
    }


    @Override
    public String getName() {
        return "Choose analysis software";
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
        jComboBox1 = new javax.swing.JComboBox(cbm);
        jScrollPane2 = new javax.swing.JScrollPane();
        jriErrorText = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(510, 390));
        setPreferredSize(new java.awt.Dimension(510, 390));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(jTextArea1.getFont());
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(ChooseVisualPanel.class, "ChooseVisualPanel.jTextArea1.text_1")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);

        jriErrorText.setEditable(false);
        jriErrorText.setBackground(new java.awt.Color(240, 240, 240));
        jriErrorText.setColumns(20);
        jriErrorText.setRows(5);
        jScrollPane2.setViewportView(jriErrorText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jriErrorText;
    // End of variables declaration//GEN-END:variables
}
