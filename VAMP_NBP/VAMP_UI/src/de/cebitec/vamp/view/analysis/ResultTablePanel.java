package de.cebitec.vamp.view.analysis;

import de.cebitec.vamp.databackend.ResultTrackAnalysis;
import de.cebitec.vamp.view.dataVisualisation.BoundsInfoManager;

/**
 * This panel is a basic result panel for showing a PersistantResult.
 * It shall be used for creating any kind of result panels for analysis 
 * functions.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public abstract class ResultTablePanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    private BoundsInfoManager bim;

    /**
     * This panel is a result panel for showing a PersistantResult. It shall be
     * used for creating any kind of result panels for analysis functions.
     */
    public ResultTablePanel() {
        initComponents();
    }
    
    /**
     * Set the bounds info manager needed for updating the currently shown
     * position. 
     * @param boundsInformationManager the bounds info manager belonging to this
     * analysis result
     */
    public void setBoundsInfoManager(BoundsInfoManager boundsInformationManager) {
        this.bim = boundsInformationManager;
    }
    
    /**
     * @return The bounds info manager needed for updating the currently shown
     * position. 
     */
    protected BoundsInfoManager getBoundsInfoManager() {
        return this.bim;
    }
    
    /**
     * Adds the data from the given Result object to the data already
     * available in this result panel. All statistics etc. are also updated.
     * @param newResult the new result to add
     */
    public abstract void addResult(final ResultTrackAnalysis newResult);
    
    /**
     * @return The size of the result contained in this panel.
     */
    public abstract int getResultSize();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}