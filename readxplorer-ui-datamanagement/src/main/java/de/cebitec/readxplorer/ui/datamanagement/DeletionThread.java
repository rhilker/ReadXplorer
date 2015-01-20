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

package de.cebitec.readxplorer.ui.datamanagement;


import de.cebitec.centrallookup.CentralLookup;
import de.cebitec.readxplorer.databackend.connector.ProjectConnector;
import de.cebitec.readxplorer.databackend.connector.StorageException;
import de.cebitec.readxplorer.parser.ReferenceJob;
import de.cebitec.readxplorer.parser.TrackJob;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;


/**
 * Thread for the deletion of tracks and references from the ReadXplorer DB.
 *
 * @author ddoppmeier
 */
public class DeletionThread extends SwingWorker<Object, Object> {

    private final List<ReferenceJob> references;
    private final List<TrackJob> tracks;
    private final Set<ReferenceJob> invalidGens;
    private final InputOutput io;
    private final ProgressHandle ph;
    private int workunits;


    /**
     * Thread for the deletion of tracks and references from the ReadXplorer DB.
     * <p>
     * @param references the list of references to delete
     * @param tracks     the list of tracks to delete
     */
    public DeletionThread( List<ReferenceJob> references, List<TrackJob> tracks ) {
        super();
        this.references = references;
        this.tracks = tracks;

        invalidGens = new HashSet<>();
        io = IOProvider.getDefault().getIO( NbBundle.getMessage( DeletionThread.class, "DeletionThread.ouptut.name" ), false );
        ph = ProgressHandleFactory.createHandle( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.progress.name" ) );
        workunits = references.size() + tracks.size();

    }


    @Override
    protected Object doInBackground() {

        CentralLookup.getDefault().add( this );
        try {
            io.getOut().reset();
        }
        catch( IOException ex ) {
            Exceptions.printStackTrace( ex );
        }
        io.select();

        // when deleting only one item there would be always 100% otherwise
        ph.start( workunits == 1 ? 2 : workunits );
        workunits = 0;

        Logger.getLogger( DeletionThread.class.getName() ).log( Level.INFO, "Starting deletion of data" );

        if( !tracks.isEmpty() ) {
            io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.start.track" ) + ":" );
            ph.progress( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.progress.track" ), workunits );
            for( TrackJob t : tracks ) {
                ph.progress( ++workunits );
                try {
                    ProjectConnector.getInstance().deleteTrack( t.getID() );
                    io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.completed.before" ) + " \"" + t.getDescription() + "\" " + NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.completed.after" ) );

                }
                catch( StorageException ex ) {
                    io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.failed.before" ) + " \"" + t.getDescription() + "\" " + NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.failed.after" ) );
                    // if this track fails, do not delete runs and genomes that are referenced by this track
                    //  invalidRuns.add(t.getRunJob());
                    invalidGens.add( t.getRefGen() );
                    Logger.getLogger( DeletionThread.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
            io.getOut().println( "" );
        }

        if( !references.isEmpty() ) {
            io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.start.ref" ) + ":" );
            ph.progress( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.progress.ref" ), workunits );
            for( ReferenceJob referenceJob : references ) {
                ph.progress( ++workunits );
                if( invalidGens.contains( referenceJob ) ) {
                    io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.error.before" ) + " \"" + referenceJob.getDescription() + "\" " + NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.error.after" ) );
                }
                else {
                    try {
                        ProjectConnector.getInstance().deleteGenome( referenceJob.getID() );
                        io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.completed.before" ) + " \"" + referenceJob.getDescription() + "\" " + NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.completed.after" ) );
                    }
                    catch( StorageException ex ) {
                        io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.failed.before" ) + " \"" + referenceJob.getDescription() + "\" " + NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.failed.after" ) );
                        Logger.getLogger( DeletionThread.class.getName() ).log( Level.SEVERE, null, ex );
                    }
                }
            }
            io.getOut().println( "" );
        }

        Logger.getLogger( DeletionThread.class.getName() ).log( Level.INFO, "Completed Deletion of Data" );

        return null;
    }


    @Override
    protected void done() {
        super.done();
        ph.progress( workunits );
        io.getOut().println( NbBundle.getMessage( DeletionThread.class, "MSG_DeletionThread.deletion.finished" ) );
        io.getOut().close();
        ph.finish();

        CentralLookup.getDefault().remove( this );
    }


}
