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

package de.cebitec.readxplorer.databackend.dataobjects;


/**
 * Base class for result entries for any kind of analyses carried out on a
 * specific track data set.
 *
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class TrackResultEntry {

    private final int trackId;


    /**
     * Base class for result entries for any kind of analyses carried out on a
     * specific track data set.
     * <p>
     * @param trackId The track id of the track to which this result entry
     *                belongs
     */
    public TrackResultEntry( int trackId ) {
        this.trackId = trackId;
    }


    /**
     * @return The track id of the track to which this result entry belongs
     */
    public int getTrackId() {
        return trackId;
    }


}
