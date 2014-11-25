package de.cebitec.vamp.mapping;

import de.cebitec.vamp.mapping.api.MappingApi;
import java.util.Iterator;
import java.util.List;
import org.openide.util.NbBundle;

/**
 * Display an overview of the selected paramets before the actual start of
 * the mapping process. The user will be able to return to the parameters
 * by clicking the "back" button.
 * 
 * @author Evgeny Anisiforov <evgeny at cebitec.uni-bielefeld.de>
 */
public class OverviewCard extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;

    /** Creates new form OverviewCard */
    public OverviewCard() {
        initComponents();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(OverviewCard.class, "CTL_OverviewCard.name");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        overviewTextArea = new javax.swing.JTextArea();

        overviewTextArea.setColumns(20);
        overviewTextArea.setRows(5);
        jScrollPane1.setViewportView(overviewTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea overviewTextArea;
    // End of variables declaration//GEN-END:variables

    void showGenereateOverview(String reference, String source, String mappingParam) {
        overviewTextArea.setText("Mapping script:\n");
        overviewTextArea.append(MappingApi.getMapperPath()+"\n"+"\n");
        
        overviewTextArea.append("Reference file:\n");
        overviewTextArea.append(reference+"\n"+"\n");
        
        overviewTextArea.append("Reads file:\n");
        overviewTextArea.append(source+"\n"+"\n");
        
        overviewTextArea.append("With additional parameters:\n");
        overviewTextArea.append(mappingParam+"\n"+"\n");
        
    }

}