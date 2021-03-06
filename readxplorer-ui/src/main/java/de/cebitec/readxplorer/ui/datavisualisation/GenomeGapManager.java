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

package de.cebitec.readxplorer.ui.datavisualisation;


import de.cebitec.readxplorer.databackend.dataobjects.ReferenceGap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * @author ddoppmeier
 * <p>
 * Holds the details about gaps in the reference genome.
 */
public class GenomeGapManager {

    private final int lowerBound;
    private final int upperBound;
    private Map<Integer, Integer> maxNumGapsPerOriginalPosition;
    private TreeMap<Integer, Integer> addedGapsOriginalPosition;
    private TreeMap<Integer, Integer> addedGapsAccumulatedPositions;
    private boolean accCalced;


    public GenomeGapManager( int lowerBound, int upperBound ) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        accCalced = false;
        initMaps();
    }


    private void initMaps() {
        maxNumGapsPerOriginalPosition = new TreeMap<>();
        fillMap( maxNumGapsPerOriginalPosition );

        addedGapsOriginalPosition = new TreeMap<>();
        fillMap( addedGapsOriginalPosition );
    }


    private void fillMap( Map<Integer, Integer> map ) {
        for( int i = lowerBound; i <= upperBound; i++ ) {
            map.put( i, 0 );
        }
    }


    /**
     * Add GenomeGaps from a PersistentMapping to this GenomeGapManager. Each
     * TreeSet of this Collection contains gaps for one position. Mulitple
     * values in the TreeSet indicate multiple genome gaps at one position in
     * the reference genome.
     * <p>
     * @param values
     */
    public void addGapsFromMapping( Map<Integer, Set<ReferenceGap>> values ) {
        Iterator<Integer> positionIt = values.keySet().iterator();
        while( positionIt.hasNext() ) {
            Integer pos = positionIt.next();
            if( !fitsIntoBounds( pos ) ) {
                // only count gaps in original visible area
                // be aware, that some gaps may be right from the upper bound, because
                // of gap introduced shifts
                continue;
            }
            Set<ReferenceGap> gapsPerPosition = values.get( pos );
            int numOfGapsPerPosition = gapsPerPosition.size();
            int oldValue = getNumOfGapsAt( pos );
            // if the current mapping has more gaps at current position,
            // store the new values
            if( oldValue < numOfGapsPerPosition ) {
                // set flag to false
                accCalced = false;
                maxNumGapsPerOriginalPosition.put( pos, numOfGapsPerPosition );
                // because the number of gaps at current position increases,
                // increase all gap counts at higher positions by difference
                int offset = numOfGapsPerPosition - oldValue;
                updateAddedGapsOriginalPosition( pos, offset );
            }
        }
    }


    public void addNumOfGapsAtPosition( int position, int numOfGaps ) {
        if( fitsIntoBounds( position ) ) {
            int oldValue = getNumOfGapsAt( position );
            if( oldValue < numOfGaps ) {
                accCalced = false;
                maxNumGapsPerOriginalPosition.put( position, numOfGaps );
                int offset = numOfGaps - oldValue;
                updateAddedGapsOriginalPosition( position, offset );
            }
        }

    }


    private void updateAddedGapsOriginalPosition( int position, int offset ) {
        Set<Integer> tailSet = addedGapsOriginalPosition.tailMap( position, false ).keySet();
        Iterator<Integer> biggerPositions = tailSet.iterator();
        while( biggerPositions.hasNext() ) {
            Integer biggerPos = biggerPositions.next();
            int value = addedGapsOriginalPosition.get( biggerPos );
            value += offset;
            addedGapsOriginalPosition.put( biggerPos, value );
        }
    }


    private void computeAccumulatedGaps() {

        addedGapsAccumulatedPositions = new TreeMap<>();
        int gapCounter = 1;
        for( int i = lowerBound; i <= upperBound; i++ ) {
            if( this.hasGapAt( i ) ) {
                int numOfGaps = this.getNumOfGapsAt( i );
                for( int j = 0; j < numOfGaps; j++ ) {
                    int newPos = i + gapCounter;
                    addedGapsAccumulatedPositions.put( newPos, gapCounter );
                    gapCounter++;
                }
            }
        }

        accCalced = true;
    }


    public int getNumOfGapsSmaller( int absPos ) {
        if( fitsIntoBounds( absPos ) ) {
            return addedGapsOriginalPosition.get( absPos );
        } else {
            // return gaps smaller than upperBound
            if( absPos > upperBound ) {
                Set<Integer> gaps = addedGapsOriginalPosition.keySet();
                if( gaps.isEmpty() ) {
                    return 0;
                } else {
                    return addedGapsOriginalPosition.get( Collections.max( gaps ) );
                }
            } else {
                return 0;
            }
        }
    }


    public int getAccumulatedGapsSmallerThan( int absPos ) {
        if( !accCalced ) {
            this.computeAccumulatedGaps();
        }
        int result;
        if( absPos > lowerBound ) {
            // get accumulated num of gaps of the nearest available smaller gap position
            Map.Entry<Integer, Integer> entry = addedGapsAccumulatedPositions.floorEntry( absPos ); // minus 1, because floorEntry(x) returns values equal to x, but we need strictly smaller than x
            if( entry == null ) {
                result = 0;
            } else {
                result = entry.getValue();
            }
        } else {
            result = 0;
        }
        return result;
    }


    public boolean hasGapAt( int absPos ) {
        if( !fitsIntoBounds( absPos ) ) {
            return false;
        } else {
            return maxNumGapsPerOriginalPosition.get( absPos ) != 0;
        }
    }


    public int getNumOfGapsAt( int absPos ) {
        if( !fitsIntoBounds( absPos ) ) {
            return 0;
        } else {
            return maxNumGapsPerOriginalPosition.get( absPos );
        }
    }


    private boolean fitsIntoBounds( int absPos ) {
        return absPos >= lowerBound && absPos <= upperBound;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "genome gap manager:\n" );
        sb.append( "lowerbound: " ).append( lowerBound ).append( ", upperbound: " ).append( upperBound ).append( "\n" );

        if( !accCalced ) {
            this.computeAccumulatedGaps();
        }

        sb.append( "position\tnumPerPosition\taddedOrig\taddedAcc\n" );
        int max = addedGapsAccumulatedPositions.lastKey();

        for( int i = lowerBound; i < max; i++ ) {
            int position = i;
            sb.append( String.valueOf( position ) ).append( "\t" );

            if( maxNumGapsPerOriginalPosition.containsKey( position ) ) {
                sb.append( maxNumGapsPerOriginalPosition.get( position ) ).append( "\t" );
            } else {
                sb.append( "#\t" );
            }

            if( addedGapsOriginalPosition.containsKey( position ) ) {
                sb.append( addedGapsOriginalPosition.get( position ) ).append( "\t" );
            } else {
                sb.append( "#\t" );
            }

            if( addedGapsAccumulatedPositions.containsKey( position ) ) {
                sb.append( addedGapsAccumulatedPositions.get( position ) ).append( "\n" );
            } else {
                sb.append( "#\n" );
            }

        }
        sb.append( "" );
        return sb.toString();
    }


}
