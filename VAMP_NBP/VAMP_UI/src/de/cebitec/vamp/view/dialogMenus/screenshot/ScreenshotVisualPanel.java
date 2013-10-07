package de.cebitec.vamp.view.dialogMenus.screenshot;

import de.cebitec.vamp.view.TopComponentHelper;
import javax.swing.JPanel;
import org.openide.windows.TopComponent;

/**
 * Visual panel displaying options to store a screenshot of any TopComponent or
 * the whole application.
 * 
 * @author Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public final class ScreenshotVisualPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Visual panel displaying options to store a screenshot of any TopComponent or
     * the whole application.
     */
    public ScreenshotVisualPanel() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Select Screenshot Component";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descriptionLabel = new javax.swing.JLabel();
        topCompComboBox = new javax.swing.JComboBox<>();

        org.openide.awt.Mnemonics.setLocalizedText(descriptionLabel, org.openide.util.NbBundle.getMessage(ScreenshotVisualPanel.class, "ScreenshotVisualPanel.descriptionLabel.text")); // NOI18N

        topCompComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(TopComponentHelper.getAllOpenedTopComponents()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addComponent(topCompComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topCompComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JComboBox<TopComponent> topCompComboBox;
    // End of variables declaration//GEN-END:variables

    /**
     * @return The TopComponent selected in this panel.
     */
    public TopComponent getSelectedTopComponent() {
        return (TopComponent) this.topCompComboBox.getSelectedItem();
    }

}
