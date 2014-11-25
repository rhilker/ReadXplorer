package de.cebitec.vamp.tools.detailedViewer;

import de.cebitec.vamp.databackend.connector.TrackConnector;
import de.cebitec.vamp.view.dataVisualisation.trackViewer.TrackViewer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JList;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

public final class OpenDetailedViewer implements ActionListener {

    private final List<TrackViewer> context;

    public OpenDetailedViewer(List<TrackViewer> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        TrackViewer currentTrackViewer = null;
        if (context.size() > 1){
            JList trackList = new JList(context.toArray());
            DialogDescriptor.Confirmation dd = new DialogDescriptor.Confirmation(trackList, NbBundle.getMessage(OpenDetailedViewer.class, "CTL_OpenDetailedViewer"));
            dd.setOptionType(DialogDescriptor.OK_CANCEL_OPTION);
            DialogDisplayer.getDefault().notify(dd);
            if (dd.getValue().equals(DialogDescriptor.OK_OPTION) && !trackList.isSelectionEmpty()){
                currentTrackViewer = (TrackViewer) trackList.getSelectedValue();
            } else {
                return;
            }
        } else {
            // context cannot be emtpy, so no check here
            currentTrackViewer = context.get(0);
        }

        DetailedViewerTopComponent detailedViewer = new DetailedViewerTopComponent();
        detailedViewer.setTrackConnector((TrackConnector) currentTrackViewer.getTrackCon());
        detailedViewer.open();
    }
}
