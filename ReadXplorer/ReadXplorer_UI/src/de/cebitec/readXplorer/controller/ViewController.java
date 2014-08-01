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
package de.cebitec.readXplorer.controller;

import de.cebitec.centrallookup.CentralLookup;
import de.cebitec.readXplorer.api.ApplicationFrameI;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantReference;
import de.cebitec.readXplorer.databackend.dataObjects.PersistantTrack;
import de.cebitec.readXplorer.util.Properties;
import de.cebitec.readXplorer.view.dataVisualisation.BoundsInfoManager;
import de.cebitec.readXplorer.view.dataVisualisation.BoundsInfoManagerFactory;
import de.cebitec.readXplorer.view.dataVisualisation.MousePositionListener;
import de.cebitec.readXplorer.view.dataVisualisation.abstractViewer.AbstractViewer;
import de.cebitec.readXplorer.view.dataVisualisation.basePanel.BasePanel;
import de.cebitec.readXplorer.view.dataVisualisation.basePanel.BasePanelFactory;
import de.cebitec.readXplorer.view.dataVisualisation.trackViewer.TrackViewer;
import de.cebitec.readXplorer.view.dialogMenus.OpenRefGenPanel;
import de.cebitec.readXplorer.view.dialogMenus.OpenTracksVisualPanel;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/**
 * Controls the view for one <code>ApplicationFrameI</code>.
 *
 * @author ddoppmeier
 */
public class ViewController implements MousePositionListener {

    private List<MousePositionListener> mousePosListener;
    private BoundsInfoManager boundsManager;
    private BasePanelFactory basePanelFac;

    private PersistantReference currentRefGen;
    private BasePanel genomeViewer;
    private Map<PersistantTrack, BasePanel> trackToPanel;
    private List<BasePanel> currentTracks= new ArrayList<>();
    
    private BoundsInfoManagerFactory boundsInfoManagerFactory;
    
    private ApplicationFrameI app;

    /**
     * Controls the view for one <code>ApplicationFrameI</code>.
     * @param app the <code>ApplicationFrameI</code> interface implementation,
     * for which the view controller is created.
     */
    public ViewController(ApplicationFrameI app){
        this.app = app;

        mousePosListener = new ArrayList<>();

        trackToPanel = new HashMap<>();
        registerInLookup();
        this.boundsInfoManagerFactory = new BoundsInfoManagerFactory();
    }
    
    /**
     * Registers this <code>ViewController</code> in the 
     * <code>CentralLookup</code>.
     */
    private void registerInLookup(){
        CentralLookup.getDefault().add(this);
    }
    
    /**
     * Opens a reference viewer for the given reference genome.
     * @param genome the genome for which a viewer shall be opened.
     */
    public void openGenome(PersistantReference genome) {
        currentRefGen = genome;
        
        boundsManager = this.boundsInfoManagerFactory.get(currentRefGen);
        basePanelFac = new BasePanelFactory(boundsManager, this);
        genomeViewer = basePanelFac.getRefViewerBasePanel(currentRefGen);
        getApp().showRefGenPanel(genomeViewer);
    }
    
    /**
     * Handles the opening of a reference genome viewer. First the list of
     * reference sequences is shown, and after a selection was made, the
     * corresponding reference viewer is opened.
     * @return true, if a reference genome is selected and OK was clicked in the dialog, false otherwise
     */
    public boolean openRefGen() {
        OpenRefGenPanel orgp = new OpenRefGenPanel();
        DialogDescriptor dialogDescriptor = new DialogDescriptor(orgp, "Open Reference");
        Dialog openRefGenDialog = DialogDisplayer.getDefault().createDialog(dialogDescriptor);
        openRefGenDialog.setVisible(true);

        boolean canOpenRefViewer = orgp.getSelectedReference() != null && dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION);
        if (canOpenRefViewer) {
            this.openGenome(orgp.getSelectedReference());
        }
        
        return canOpenRefViewer;
    }

    /**
     * Closes all tracks, which are currently open for the reference viewer of
     * this top component and the reference viewer.
     */
    public void closeRefGen() {

        // unregister from listeners and remove
        genomeViewer.close();
        mousePosListener.remove(genomeViewer);
        getApp().removeRefGenPanel(genomeViewer);
        genomeViewer = null;
        currentRefGen = null;
        basePanelFac = null;
        boundsManager = null;

    }
    
    /**
     * opens the given track on the current genome
     * @param tracks the tracks belonging to the current reference genome
     */
    public void openTracksOnCurrentGenome(Collection<PersistantTrack> tracks) {
        for (PersistantTrack track : tracks) {
            // create basepanel
            final BasePanel trackPanel = basePanelFac.getTrackBasePanel(track, currentRefGen);
            if (trackPanel != null) {
                currentTracks.add(trackPanel);
                trackToPanel.put(track, trackPanel);

                // show the panel and the track
                getApp().showTrackPanel(trackPanel);
                
                trackPanel.addPrefListener(new PreferenceChangeListener() {

                    @Override
                    public void preferenceChange(final PreferenceChangeEvent evt) {
                        AbstractViewer viewer = trackPanel.getViewer();
                        if (evt.getKey().equals(Properties.VIEWER_HEIGHT)) {

                            int height = Integer.parseInt(evt.getNewValue());
                            trackPanel.setSize(trackPanel.getSize().width, height);
                            trackPanel.setPreferredSize(new Dimension(trackPanel.getPreferredSize().width, height));
                            trackPanel.setMaximumSize(new Dimension(trackPanel.getMaximumSize().width, height));
                            Component comp = (Component) getApp();
                            comp.paintAll(comp.getGraphics());
                            viewer.updatePhysicalBounds();
                            viewer.setNewDataRequestNeeded(true);
                            viewer.boundsChangedHook();
                        } else if (evt.getKey().equals(Properties.VIEWER_AUTO_SCALING)) {
                            if (viewer instanceof TrackViewer) {
                                ((TrackViewer) viewer).setAutomaticScaling(evt.getNewValue().equals("true"));
                            }
                        }
                    }
                });
            }
        }
    }
    
    /**
     * Opens a dialog with all available tracks for the current reference genome.
     * After selecting a track, the associated track viewer is opened.
     */
    public void openTrack() {
        OpenTracksVisualPanel otp = new OpenTracksVisualPanel(currentRefGen.getId());
        DialogDescriptor dialogDescriptor = new DialogDescriptor(otp, "Open Track");
        Dialog openTrackDialog = DialogDisplayer.getDefault().createDialog(dialogDescriptor);
        openTrackDialog.setVisible(true);

        if (dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION) && !otp.getSelectedTracks().isEmpty()) {
            if (otp.isCombineTracks()) {
                ViewController viewCon = Utilities.actionsGlobalContext().lookup(ViewController.class);
                BasePanelFactory factory = viewCon.getBasePanelFac();
                factory.getMultipleTracksBasePanel(otp.getSelectedTracks(), currentRefGen, otp.isCombineTracks());
            } else {
                this.openTracksOnCurrentGenome(otp.getSelectedTracks());
            }
        } else if (dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION) && otp.getSelectedTracks().isEmpty()) {
            String msg = NbBundle.getMessage(ViewController.class, "CTL_OpenTrackInfo",
                    "No track selected. To open a track, at least one track has to be selected.");
            String title = NbBundle.getMessage(ViewController.class, "CTL_OpenTrackInfoTitle", "Info");
            JOptionPane.showMessageDialog(genomeViewer, msg, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Opens a dialog with all available tracks for the current reference
     * genome. After selecting exactly two tracks, the associated double track
     * viewer is opened.
     */
    @Messages({ "DT_OpenTitle=Open Double Track Viewer",
                "DT_ErrorMsg=Please select exactly TWO tracks for the Double Track Viewer!"})
    public void openDoubleTrack() {        
        OpenTracksVisualPanel otp = new OpenTracksVisualPanel(currentRefGen.getId());       
        otp.setCombineTracksEnabled(false);

        DialogDescriptor dialogDescriptor = new DialogDescriptor(otp, Bundle.DT_OpenTitle());
        Dialog openRefGenDialog = DialogDisplayer.getDefault().createDialog(dialogDescriptor);
        openRefGenDialog.setVisible(true);

        // check if two tracks were selected
        boolean okSelected = false;
        if (dialogDescriptor.getValue().equals(DialogDescriptor.OK_OPTION) && otp.getSelectedTracks().size() == 2) {
            okSelected = true;
        } else if (!(dialogDescriptor.getValue().equals(DialogDescriptor.CANCEL_OPTION)
                || dialogDescriptor.getValue().equals(DialogDescriptor.CLOSED_OPTION))) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(Bundle.DT_ErrorMsg(),
                    NotifyDescriptor.INFORMATION_MESSAGE));
            this.openDoubleTrack();
        }
        if (okSelected) {
            BasePanelFactory factory = this.getBasePanelFac();
            factory.getMultipleTracksBasePanel(otp.getSelectedTracks(), currentRefGen, false);
        }
    }

    /**
     * @return The list of currently opened track base panels.
     */
    public List<BasePanel> getOpenTracks(){
        return currentTracks ;
    }

    /**
     * Replacement for <code>closeTrack</code> so the application does not need
     * to know <code>PersistantTrack</code> or <code>BasePanel</code>.
     * @param trackPanel The base panel to close
     */
    public void closeTrackPanel(JPanel trackPanel) {
        BasePanel trackBasePanel = (BasePanel) trackPanel;
        currentTracks.clear();
        getApp().closeTrackPanel(trackBasePanel);
        trackBasePanel.close();
        mousePosListener.remove(trackBasePanel);
        trackToPanel.values().remove(trackBasePanel);
    }

    /**
     * Open a double track viewer which already has two selected tracks.
     * @param tp the double track viewer base panel
     */
    public void openTrack2(BasePanel tp) {
        getApp().showTrackPanel(tp);
        currentTracks.add(tp);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setCurrentMousePosition(int logPos) {
        for (MousePositionListener c : mousePosListener) {
            c.setCurrentMousePosition(logPos);
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void setMouseOverPaintingRequested(boolean requested) {
        for (MousePositionListener c : mousePosListener) {
            c.setMouseOverPaintingRequested(requested);
        }
    }

    /**
     * @return The bounds manager associated to this view controller.
     */
    public BoundsInfoManager getBoundsManager() {
        return boundsManager;
    }

    /**
     * @return The reference genome associated with this view controller.
     */
    public PersistantReference getCurrentRefGen() {
        return currentRefGen;
    }

    /**
     * @return true, if this view controller already has a reference, false 
     * otherwise.
     */
    public boolean hasRefGen() {
        return currentRefGen != null;
    }

    /**
     * @return A displayable name of this reference.
     */
    public String getDisplayName() {
        return currentRefGen.getName() + ": " + currentRefGen.getDescription();
    }

    /**
     * Adds a mouse position listener to this view controller.
     * @param listener the listener to add
     */
    public void addMousePositionListener(MousePositionListener listener) {
        mousePosListener.add(listener);
    }

    /**
     * Removes a mouse position listener from this view controller.
     * @param listener the listener to add
     */
    public void removeMousePositionListener(MousePositionListener listener) {
        if (mousePosListener.contains(listener)) {
            mousePosListener.remove(listener);
        }
    }

    /**
     * @return The {@link ApplicationFrameI} associated with this view 
     * controller.
     */
    private ApplicationFrameI getApp() {
        return app;
    }

    /**
     * @return The {@link BasePanelFactory} associated with this view 
     * controller. This factory generates all viewers.
     */
    public BasePanelFactory getBasePanelFac() {
        return basePanelFac;
    }

}
