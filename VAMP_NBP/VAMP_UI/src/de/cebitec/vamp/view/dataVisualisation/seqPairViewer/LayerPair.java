package de.cebitec.vamp.view.dataVisualisation.seqPairViewer;

import de.cebitec.vamp.view.dataVisualisation.alignmentViewer.BlockI;
import de.cebitec.vamp.view.dataVisualisation.alignmentViewer.LayerI;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rolf Hilker
 */
public class LayerPair implements LayerI {
    private ArrayList<BlockI> blocks;

    public LayerPair(){
        blocks = new ArrayList<BlockI>();
    }

    @Override
    public void addBlock(BlockI block) {
        blocks.add(block);
    }

    @Override
    public String toString(){
        
        //start und stop hier
        StringBuilder sb = new StringBuilder();
        for(BlockI b : blocks){
            sb.append(b.toString());
        }

        return sb.toString();
    }

    @Override
    public Iterator<BlockI> getBlockIterator() {
        return blocks.iterator();
    }
}
