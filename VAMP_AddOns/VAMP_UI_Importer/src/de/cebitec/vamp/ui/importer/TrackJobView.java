package de.cebitec.vamp.ui.importer;

import de.cebitec.vamp.parser.TrackJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Shows a table containing the current track jobs to import.
 *
 * @author ddoppmeier
 */
public class TrackJobView extends javax.swing.JPanel implements ListSelectionListener {

    private List<TrackJob> tracks;
    public final static long serialVersionUID = 774292377;
    private boolean hasJobs;

    /** Creates new form TaskViewerTemplate */
    public TrackJobView() {
        tracks = new ArrayList<TrackJob>();
        initComponents();
    }

    public TrackJob getSelectedItem() {
        return tracks.get(trackTable.getSelectedRow());
    }

    public void add(TrackJob trackJob){
        DefaultTableModel model = (DefaultTableModel) trackTable.getModel();
        model.addRow(new Object[]{
            trackJob.getFile().getName(),
            trackJob.getDescription(),
            trackJob.getRefGen().getDescription()});
        tracks.add(trackJob);

        if (!hasJobs){
            hasJobs = true;
            firePropertyChange(ImportSetupCard.PROP_HAS_JOBS, null, hasJobs);
        }
    }

    public void remove(TrackJob trackJob){
        int index = tracks.indexOf(trackJob);
        tracks.remove(trackJob);

        DefaultTableModel model = (DefaultTableModel) trackTable.getModel();
        model.removeRow(index);

        if (tracks.isEmpty()){
            hasJobs = false;
            firePropertyChange(ImportSetupCard.PROP_HAS_JOBS, null, hasJobs);
        }
    }

    public List<TrackJob> getJobs(){
        return tracks;
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
        trackTable = new javax.swing.JTable();
        trackTable.getSelectionModel().addListSelectionListener(this);

        setPreferredSize(new java.awt.Dimension(400, 300));

        trackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File", "Description", "Reference"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trackTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(trackTable);
        trackTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.file")); // NOI18N
        trackTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.description")); // NOI18N
        trackTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(TrackJobView.class, "TrackJobView.trackTable.reference")); // NOI18N

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
    private javax.swing.JTable trackTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel model = (ListSelectionModel) e.getSource();
        if(model.isSelectionEmpty()){
            firePropertyChange(ImportSetupCard.PROP_JOB_SELECTED, null, Boolean.FALSE);
        } else {
            firePropertyChange(ImportSetupCard.PROP_JOB_SELECTED, null, Boolean.TRUE);
        }
    }

    public boolean IsRowSelected(){
        ListSelectionModel model = trackTable.getSelectionModel();
        return !model.isSelectionEmpty();
    }

}
