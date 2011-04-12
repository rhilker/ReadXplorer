package de.cebitec.vamp.view.dataVisualisation;

import de.cebitec.vamp.parser.output.OutputParser;
import de.cebitec.vamp.util.SequenceUtils;
import de.cebitec.vamp.util.externalTools.RNAFoldCaller;
import de.cebitec.vamp.util.externalTools.RNAFolderException;
import de.cebitec.vamp.util.fileChooser.FastaFileChooser;
import de.cebitec.vamp.view.dataVisualisation.abstractViewer.AbstractViewer;
import de.cebitec.vamp.view.dataVisualisation.abstractViewer.SequenceBar;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 * Listener for highlighting areas on a sequence bar.
 * Note that classes with a HighlightAreaListener have to implement IHighlightable
 *
 * @author Rolf Hilker
 */
public class HighlightAreaListener extends MouseAdapter implements ClipboardOwner {

    private static final int HEIGHT = 12;

    private final SequenceBar parentComponent;
    private final int baseLineY;
    private final int offsetY;
    private AbstractViewer grandparentViewer;
    private int startX;
    private boolean keepPainted;
    private boolean freezeRect;
    private Rectangle highlightRect;
    private boolean fwdStrand;

    private int seqStart;
    private int seqEnd;

    /**
     * @param graphic the graphics object to paint on
     * @param offsetY the y offset from the middle, which determines where to start painting the
     * highlighting rectangle
     */
    public HighlightAreaListener(final SequenceBar parentComponent, final int baseLineY,
                final int offsetY, final AbstractViewer grandparentViewer){
        this.parentComponent = parentComponent;
        this.baseLineY = baseLineY;
        this.offsetY = offsetY;
        this.grandparentViewer = grandparentViewer;
        this.startX = -1;
        this.keepPainted = false;
        this.freezeRect = false;
        this.fwdStrand = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        boolean inRect = false;
        if (highlightRect != null){
            final int x = e.getX();
            inRect = x > highlightRect.x && x < highlightRect.x + highlightRect.width;
        }

        if (this.keepPainted && !inRect){
            this.keepPainted = false;
            this.freezeRect = false;
            this.setHighlightRectangle(null);
        } else
        if (inRect) {
            this.showPopUp(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            this.freezeRect = false;
            this.keepPainted = true;
            double baseWidth = this.grandparentViewer.getBaseWidth();
            this.startX = (int) (Math.round(e.getX()/baseWidth)*baseWidth);
            int yPos = this.baseLineY - 7;
            this.fwdStrand = e.getY() <= this.baseLineY;
            yPos = this.fwdStrand ? yPos - this.offsetY : yPos + this.offsetY;

            this.setHighlightRectangle(new Rectangle(this.startX, yPos, 2, HEIGHT));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.freezeRect = true;
        if (!this.keepPainted){
            this.setHighlightRectangle(null);
            this.freezeRect = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /* update rectangle according to new mouse position & start position
         * only x value of mouse event is important! */
        if (!this.freezeRect){
            double baseWidth = this.grandparentViewer.getBaseWidth();
            int x = (int) (Math.round(e.getX()/baseWidth)*baseWidth);
            int xPos = x <= this.startX ? x : this.startX;
            int yPos = this.baseLineY - 7;
            yPos = e.getY() <= this.baseLineY ? yPos - this.offsetY : yPos + this.offsetY;

            this.setHighlightRectangle(new Rectangle(xPos, yPos, Math.abs(x - this.startX), HEIGHT));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        for (MouseMotionListener mml : grandparentViewer.getMouseMotionListeners()){
            mml.mouseMoved(e);
            parentComponent.setToolTipText(grandparentViewer.getToolTipText());
        }
    }

    /**
     * Opens the pop up menu showing all available options for the highlighted
     * rectangle.
     * @param e method to be called after a click, so this is the mouse event resulting from that click
     */
    private void showPopUp(MouseEvent e) {

        if ((e.getButton() == MouseEvent.BUTTON3) || (e.isPopupTrigger())) {
            JPopupMenu popUp = new JPopupMenu();

            //add copy option
            JMenuItem copyItem = new JMenuItem(NbBundle.getMessage(HighlightAreaListener.class, "HighlightListener_Copy"));
            copyItem.addActionListener(new ActionListener() {

                @Override//
                public void actionPerformed(ActionEvent e) {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(new StringSelection(HighlightAreaListener.this.getMarkedSequence()), HighlightAreaListener.this);
                }
            });
            popUp.add(copyItem);

            //add store as fasta file option
            JMenuItem storeItem = new JMenuItem(NbBundle.getMessage(HighlightAreaListener.class, "HLA_StoreFasta"));
            storeItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String output = this.generateFastaFromFeature();
                    FastaFileChooser storeFastaFileChoser = new FastaFileChooser("fasta", output);
                }

                /**
                 * Generates a string ready for output in a fasta file.
                 */
                private String generateFastaFromFeature() {
                    String selSequence = HighlightAreaListener.this.getMarkedSequence();
                    String header = "Copied sequence from:".concat(String.valueOf(seqStart)).concat(" to ")
                            .concat(String.valueOf(seqEnd));
                    return OutputParser.generateFasta(selSequence, header);
                }
            });
            popUp.add(storeItem);

            //add calculate secondary structure option
            JMenuItem calcItem = new JMenuItem(NbBundle.getMessage(HighlightAreaListener.class, "HighlightListener_SecondaryStruct"));
            calcItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String selSequence = HighlightAreaListener.this.getMarkedSequence();
                    try {
                        String foldedSequence = RNAFoldCaller.callRNAFolder(selSequence);
                        //TODO: grafische darstellung in vamp anschließen
                        //würde dafür kleines extra tab oder fenster öffnen (tab wohl sinnvoller)
                        System.out.println(foldedSequence);

                    } catch (RNAFolderException ex) {
                        NotifyDescriptor nd = new NotifyDescriptor.Message(ex.getMessage(), NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notify(nd);
                    }
                }
            });
            popUp.add(calcItem);

            popUp.show((JComponent) HighlightAreaListener.this.parentComponent, e.getX(), e.getY());
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //do nothing
    }

    /**
     * Sets the current rectangle both in this class and in the parent component.
     * @param rectangle
     */
    private void setHighlightRectangle(final Rectangle rectangle) {
        this.highlightRect = rectangle;
        this.parentComponent.setHighlightRectangle(this.highlightRect);
    }

    /**
     * Returns the highlighted sequence.
     * @return the highlighted sequence
     */
    private String getMarkedSequence() {
        BoundsInfo bounds = grandparentViewer.getBoundsInfo();
        final double baseWidth = grandparentViewer.getBaseWidth();
        final String seq = parentComponent.getPersistantReference().getSequence();
        int logleft = bounds.getLogLeft() - 1 + (int) (Math.round((highlightRect.x - grandparentViewer.getHorizontalMargin()) / baseWidth));
        int logright = logleft + (int) (Math.round(highlightRect.width / baseWidth));
        logleft = logleft < 0 ? 0 : logleft;
        logright = logright > seq.length() ? seq.length() : logright;
        String selSequence = seq.substring(logleft, logright);
        if (!fwdStrand) {
            selSequence = SequenceUtils.complementDNA(SequenceUtils.reverseString(selSequence));
        }
        this.seqStart = logleft+1;
        this.seqEnd = logright;
        return selSequence;
    }
}
