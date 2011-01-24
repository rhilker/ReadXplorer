package de.cebitec.vamp.ui.dataAdministration;

import de.cebitec.vamp.parser.TrackJobs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ddoppmeier
 */
public class TrackView extends javax.swing.JPanel implements TableModelListener{

    private static final long serialVersionUID = 762498252;

    private List<TrackJobs> jobs;
    private List<TrackJobs> jobs2del;
    private Boolean hasCheckedJobs;

    /** Creates new form MappingView */
    public TrackView() {
        initComponents();
        jobs = new ArrayList<TrackJobs>();
    }

    public void setTrackJobs(List<TrackJobs> trackJobs){
        this.jobs = trackJobs;
        clearTableRows();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (TrackJobs trackJob : trackJobs) {
            model.addRow(new Object[]{false, trackJob.getDescription(), trackJob.getTimestamp()});
        }
    }

    public List<TrackJobs> getJobs2Del(){
        jobs2del = new ArrayList<TrackJobs>();

        for (int row = 0; row <= jTable1.getRowCount()-1; row++) {
            if ((Boolean) jTable1.getValueAt(row, 0)){
                jobs2del.add(jobs.get(row));
            }
        }
        return jobs2del;
    }

    private void clearTableRows(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        while (model.getRowCount() > 0){
            model.removeRow(model.getRowCount()-1);
        }
    }

    private void checkColumnSelection() {
        List<Boolean> selection = new ArrayList<Boolean>();

        for (int row = 0; row <= jTable1.getRowCount()-1; row++) {
            selection.add((Boolean) jTable1.getValueAt(row, 0));
        }

        hasCheckedJobs = selection.contains(Boolean.TRUE) ? Boolean.TRUE : Boolean.FALSE;
        firePropertyChange("hasCheckedJobs", null, hasCheckedJobs);
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
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Delete", "Description", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getModel().addTableModelListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (row >= 0 && column >= 0) {
            TrackJobs trackJob = jobs.get(row);
            DefaultTableModel model = (DefaultTableModel) e.getSource();
            boolean selected = (Boolean) model.getValueAt(row, column);

            if (selected) {
                // unregister dependencies
                trackJob.getRefGen().unregisterTrackwithoutRunJob(trackJob);
            } else {
                // re-register dependencies
                trackJob.getRefGen().registerTrackWithoutRunJob(trackJob);
                // deselect refgen
                firePropertyChange("deselect", null, trackJob.getRefGen());
            }
        }
        checkColumnSelection();
    }

}
