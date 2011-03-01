package de.cebitec.vamp.tools.twoTrackViewer;

import de.cebitec.vamp.controller.ViewController;
import de.cebitec.vamp.view.dataVisualisation.basePanel.BasePanelFactory;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.ReferenceViewer;
import de.cebitec.vamp.view.dialogPanels.OpenTrackPanel;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Utilities;

// TODO this should be something like a serviceprovider and "overwrite" the standard opentrackaction
public final class OpenDoubleTrackAction implements ActionListener {

    private final ReferenceViewer context;

    public OpenDoubleTrackAction(ReferenceViewer context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        OpenTrackPanel otp = new OpenTrackPanel(context.getReference().getId());

        DialogDescriptor dialogDescriptor = new DialogDescriptor(otp, "Open Two Tracks");
        Dialog openRefGenDialog = DialogDisplayer.getDefault().createDialog(dialogDescriptor);
        openRefGenDialog.setVisible(true);

        // check if two tracks were selected
        while (!dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION) || otp.getSelectedTracks().size() != 2){
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Please select exactly TWO tracks.", NotifyDescriptor.INFORMATION_MESSAGE));
            openRefGenDialog.setVisible(true);
        }
        ViewController viewCon = Utilities.actionsGlobalContext().lookup(ViewController.class);
        BasePanelFactory factory = viewCon.getBasePanelFac();
        factory.getMultipleTracksBasePanel(otp.getSelectedTracks(), context.getReference());
    }
}