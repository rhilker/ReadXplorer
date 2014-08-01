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
package de.cebitec.readXplorer.correlationAnalysis;

import de.cebitec.readXplorer.correlationAnalysis.CorrelationAnalysisAction.CorrelationCoefficient;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.util.NbBundle;

/**
 * GUI Card for selection of the parameters for the correlation analysis
 * @author Evgeny Anisiforov <evgeny at cebitec.uni-bielefeld.de>
 */
public class ParameterSelectionCard extends javax.swing.JPanel {
    /** 
     * Creates new form TrimSelectionCard
     */
    public ParameterSelectionCard() {
        initComponents();
        
        this.intervalLengthSlider.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                firePropertyChange(CorrelationAnalysisAction.PROP_INTERVALLENGTH, e.getOldValue(), e.getNewValue());
            }
        });
        
        this.minimumCorrelationSlider.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                firePropertyChange(CorrelationAnalysisAction.PROP_MINCORRELATION, e.getOldValue(), e.getNewValue());
            }
        });
        
        this.minimumPeakCoverageSlider.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                firePropertyChange(CorrelationAnalysisAction.PROP_MINPEAKCOVERAGE, e.getOldValue(), e.getNewValue());
            }
        });
        
        this.correlationMethodCombo.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                firePropertyChange(CorrelationAnalysisAction.PROP_CORRELATIONCOEFFICIENT , e.getOldValue(), e.getNewValue());
            }
        });
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        intervalLengthLabel = new javax.swing.JLabel();
        minimumCorrelationLabel = new javax.swing.JLabel();
        intervalLengthSlider = new javax.swing.JSlider();
        minimumCorrelationSlider = new javax.swing.JSlider();
        minimumCorrelationLabel1 = new javax.swing.JLabel();
        minimumPeakCoverageSlider = new javax.swing.JSlider();
        intervalLengthLabel1 = new javax.swing.JLabel();
        correlationMethodCombo = new javax.swing.JComboBox();

        org.openide.awt.Mnemonics.setLocalizedText(intervalLengthLabel, org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.intervalLengthLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(minimumCorrelationLabel, org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.minimumCorrelationLabel.text")); // NOI18N

        intervalLengthSlider.setMajorTickSpacing(500);
        intervalLengthSlider.setMaximum(1000);
        intervalLengthSlider.setMinorTickSpacing(50);
        intervalLengthSlider.setPaintLabels(true);
        intervalLengthSlider.setPaintTicks(true);
        intervalLengthSlider.setSnapToTicks(true);
        intervalLengthSlider.setToolTipText(org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.intervalLengthSlider.toolTipText")); // NOI18N
        intervalLengthSlider.setValue(100);

        minimumCorrelationSlider.setMajorTickSpacing(20);
        minimumCorrelationSlider.setMinorTickSpacing(10);
        minimumCorrelationSlider.setPaintLabels(true);
        minimumCorrelationSlider.setPaintTicks(true);
        minimumCorrelationSlider.setSnapToTicks(true);
        minimumCorrelationSlider.setToolTipText(org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.minimumCorrelationSlider.toolTipText")); // NOI18N
        minimumCorrelationSlider.setValue(20);

        org.openide.awt.Mnemonics.setLocalizedText(minimumCorrelationLabel1, org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.minimumCorrelationLabel1.text")); // NOI18N

        minimumPeakCoverageSlider.setMajorTickSpacing(20);
        minimumPeakCoverageSlider.setMaximum(200);
        minimumPeakCoverageSlider.setMinorTickSpacing(10);
        minimumPeakCoverageSlider.setPaintLabels(true);
        minimumPeakCoverageSlider.setPaintTicks(true);
        minimumPeakCoverageSlider.setSnapToTicks(true);
        minimumPeakCoverageSlider.setToolTipText(org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.minimumPeakCoverageSlider.toolTipText")); // NOI18N
        minimumPeakCoverageSlider.setValue(20);

        org.openide.awt.Mnemonics.setLocalizedText(intervalLengthLabel1, org.openide.util.NbBundle.getMessage(ParameterSelectionCard.class, "ParameterSelectionCard.intervalLengthLabel1.text")); // NOI18N

        correlationMethodCombo.setModel(new javax.swing.DefaultComboBoxModel(new CorrelationCoefficient[] { CorrelationCoefficient.PEARSON, CorrelationCoefficient.SPEARMAN }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimumPeakCoverageSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(minimumCorrelationSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(intervalLengthSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minimumCorrelationLabel1)
                            .addComponent(intervalLengthLabel)
                            .addComponent(intervalLengthLabel1)
                            .addComponent(correlationMethodCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minimumCorrelationLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(intervalLengthLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correlationMethodCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(minimumCorrelationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimumCorrelationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minimumCorrelationLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimumPeakCoverageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(intervalLengthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intervalLengthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public int getIntervalLength() {
        return this.intervalLengthSlider.getValue();
    }
    
    public int getMinimumCorrelation() {
        return this.minimumCorrelationSlider.getValue();
    }
    
        
    @Override
    public String getName() {
        return NbBundle.getMessage(ParameterSelectionCard.class, "CTL_ParameterSelectionCard.name");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox correlationMethodCombo;
    private javax.swing.JLabel intervalLengthLabel;
    private javax.swing.JLabel intervalLengthLabel1;
    private javax.swing.JSlider intervalLengthSlider;
    private javax.swing.JLabel minimumCorrelationLabel;
    private javax.swing.JLabel minimumCorrelationLabel1;
    private javax.swing.JSlider minimumCorrelationSlider;
    private javax.swing.JSlider minimumPeakCoverageSlider;
    // End of variables declaration//GEN-END:variables

    public Integer getMinimumPeakCoverage() {
        return this.minimumPeakCoverageSlider.getValue();
    }
    
    public Object getCorrelationMethod() {
        return this.correlationMethodCombo.getSelectedItem();
    }
}
