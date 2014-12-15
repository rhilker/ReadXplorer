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

package de.cebitec.readxplorer.transcriptionanalyses.dataStructures;


import de.cebitec.readxplorer.databackend.dataObjects.TrackResultEntry;
import java.util.ArrayList;
import java.util.List;


/**
 * Data structure for storing operons. Operons consist of a list of
 * OperonAdjacencies, since each operon can contain more than two genes.
 * <p>
 * @author MKD, rhilker
 * <p>
 */
public class Operon extends TrackResultEntry {

    private List<OperonAdjacency> operonAdjacencies;


    /**
     * Data structure for storing operons. Operons consist of a list of
     * OperonAdjacencies, since each operon can contain more than two genes.
     * <p>
     * @param trackId The id of the track whose reads are analyzed here
     */
    public Operon( int trackId ) {
        super( trackId );
        this.operonAdjacencies = new ArrayList<>();
    }


    /**
     * @return the operon adjacencies of this operon
     */
    public List<OperonAdjacency> getOperonAdjacencies() {
        return this.operonAdjacencies;
    }


    /**
     * @param operon the operon adjacencies to associate with this operon
     *               object.
     */
    public void setOperonAdjacencies( List<OperonAdjacency> newOperonAdjacencys ) {
        this.operonAdjacencies = newOperonAdjacencys;
    }


    /**
     * Remove all operon adjacencies associated with this operon object.
     */
    public void clearOperonAdjacencyList() {
        this.operonAdjacencies.removeAll( this.operonAdjacencies );
    }


    /**
     * Adds the operon adjacency to the list of OperonAdjacencies.
     * <p>
     * @param operonAdjacency
     */
    public void addOperonAdjacency( OperonAdjacency operonAdjacency ) {
        this.operonAdjacencies.add( operonAdjacency );
    }


    /**
     * Adds the operon adjacencies to the end of the list of OperonAdjacencies.
     * <p>
     * @param operonAdjacencies
     */
    public void addAllOperonAdjacencies( List<OperonAdjacency> operonAdjacencies ) {
        this.operonAdjacencies.addAll( operonAdjacencies );
    }


}
