/*
 * Copyright (C) 2015 Institute for Bioinformatics and Systems Biology, University Giessen, Germany
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

package bio.comp.jlu.readxplorer.cli.analyses;


import bio.comp.jlu.readxplorer.cli.filefilter.AnalysisFileFilter;
import de.cebitec.readxplorer.databackend.AnalysesHandler;
import de.cebitec.readxplorer.databackend.connector.ProjectConnector;
import de.cebitec.readxplorer.databackend.connector.TrackConnector;
import de.cebitec.readxplorer.databackend.dataobjects.PersistentReference;
import de.cebitec.readxplorer.databackend.dataobjects.PersistentTrack;
import de.cebitec.readxplorer.transcriptionanalyses.AnalysisTranscriptionStart;
import de.cebitec.readxplorer.transcriptionanalyses.AnalysisUnannotatedTransStart;
import de.cebitec.readxplorer.transcriptionanalyses.ParameterSetTSS;
import de.cebitec.readxplorer.transcriptionanalyses.TssDetectionResult;
import de.cebitec.readxplorer.transcriptionanalyses.datastructures.TranscriptionStart;
import de.cebitec.readxplorer.utils.Properties;
import de.cebitec.readxplorer.utils.SequenceUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jxl.write.WriteException;
import org.netbeans.api.sendopts.CommandException;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.SEVERE;


/**
 *
 * @author Oliver Schwengers <oliver.schwengers@computational.bio.uni-giessen.de
 */
public class TSSAnalysisCallable extends AnalysisCallable {

    private static final Logger LOG = Logger.getLogger( TSSAnalysisCallable.class.getName() );

    private final PersistentTrack persistentTrack;
    private final ParameterSetTSS parameterSet;


    public TSSAnalysisCallable( boolean verbosity, PersistentTrack persistentTrack, ParameterSetTSS parameterSetTSS ) {

        super( verbosity, "TSS" );

        this.persistentTrack = persistentTrack;
        this.parameterSet    = parameterSetTSS;

    }


    @Override
    public AnalysisResult call() throws Exception {

        try {

            File trackFile = new File( persistentTrack.getFilePath() );
            final String trackFileName = trackFile.getName();


            LOG.log( FINE, "start TSS analysis for {0}...", trackFileName );
            result.addOutput( "start analysis..." );
            final ProjectConnector pc = ProjectConnector.getInstance();
            final TrackConnector trackConnector = pc.getTrackConnector( persistentTrack );
            final AnalysisTranscriptionStart analysisTSS;
            if( parameterSet.isPerformUnannotatedTranscriptDet() ) {
                analysisTSS = new AnalysisUnannotatedTransStart( trackConnector, parameterSet );
            } else {
                analysisTSS = new AnalysisTranscriptionStart( trackConnector, parameterSet );
            }
            final ThreadingHelper threadingHelper = new ThreadingHelper(); // tricky work-around due to MVC blindness of the RX code :-)
            threadingHelper.start();

            AnalysesHandler analysisHandler = new AnalysesHandler( trackConnector, threadingHelper, "", parameterSet.getReadClassParams() );
                analysisHandler.registerObserver( analysisTSS );
                analysisHandler.setCoverageNeeded( true );
                analysisHandler.setDesiredData( Properties.READ_STARTS );
                analysisHandler.startAnalysis();

            threadingHelper.join(); // blocks until analysisHandler finishes its job
            Map<Integer, PersistentTrack> trackMap = new HashMap<>();
            trackMap.put( persistentTrack.getId(), persistentTrack );
            PersistentReference reference = pc.getRefGenomeConnector( persistentTrack.getRefGenID() ).getRefGenome();
            TssDetectionResult tssResult = new TssDetectionResult( analysisTSS.getResults(), parameterSet, trackMap, reference, false, 1, 0 );


            LOG.log( FINE, "store TSS results for {0}...", trackFileName );
            result.addOutput( "store results..." );
            File resultFile = new File( "tss-" + trackFileName + '.' + AnalysisFileFilter.SUFFIX );

            // prepare/write/set tssResult object
            processResultForExport( tssResult, persistentTrack.getRefGenID() );
            writeFile( resultFile, tssResult.dataSheetNames(), tssResult.dataColumnDescriptions(), tssResult.dataToExcelExportList() );
            result.setResultFile( resultFile );

        } catch( IOException | WriteException | InterruptedException ex ) {
            LOG.log( SEVERE, ex.getMessage(), ex );
            result.addOutput( "Error: " + ex.getMessage() );
        } catch( OutOfMemoryError ome ) {
            LOG.log( SEVERE, ome.getMessage(), ome );
            CommandException ce = new CommandException( 1, "ran out of memory!" );
            ce.initCause( ome );
            throw ce;
        }

        return result;

    }


    private void processResultForExport( TssDetectionResult tssResult, int refGenomeId ) {

        //Generating promoter regions for the TSS
        List<String> promoterRegions = new ArrayList<>();

        //get reference sequence for promoter regions
        PersistentReference ref = ProjectConnector.getInstance().getRefGenomeConnector( refGenomeId ).getRefGenome();

        //get the promoter region for each TSS
        int chromLength = ref.getActiveChromosome().getLength();
        for( TranscriptionStart transStart : tssResult.getResults() ) {
            final String promoter;
            if( transStart.isFwdStrand() ) {
                int promoterStart = transStart.getPos() - 70;
                promoterStart = promoterStart < 0 ? 0 : promoterStart;
                promoter = ref.getActiveChromSequence( promoterStart, transStart.getPos() );
            } else {
                int promoterStart = transStart.getPos() + 70;
                promoterStart = promoterStart > chromLength ? chromLength : promoterStart;
                promoter = SequenceUtils.getReverseComplement( ref.getActiveChromSequence( transStart.getPos(), promoterStart ) );
            }
            promoterRegions.add( promoter );
        }

        tssResult.setPromoterRegions( promoterRegions );

    }


}
