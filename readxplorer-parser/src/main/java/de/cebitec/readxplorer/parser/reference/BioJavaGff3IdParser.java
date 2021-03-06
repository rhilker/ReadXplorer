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

package de.cebitec.readxplorer.parser.reference;


import de.cebitec.readxplorer.parser.common.ParsingException;
import de.cebitec.readxplorer.utils.Observer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.biojava.bio.BioException;
import org.biojava.bio.program.gff3.GFF3DocumentHandler;
import org.biojava.bio.program.gff3.GFF3Parser;
import org.biojava.bio.program.gff3.GFF3Record;
import org.biojava.ontology.Ontology;
import org.biojava.utils.ParserException;


/**
 * Parser to fetch all available sequence identifiers from a GFF 3 file.
 * <p>
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class BioJavaGff3IdParser implements IdParserI {

    private final ArrayList<Observer> observers = new ArrayList<>();
    private final List<String> seqIds = new ArrayList<>();


    /**
     * Fetches all available sequence identifiers from a GFF 3 file.
     * <p>
     * @param gff3File the GFF 3 file to read from
     * <p>
     * @return the list of sequence identifiers contained in the given file
     * <p>
     * @throws ParsingException
     */
    @Override
    public List<String> getSequenceIds( final File gff3File ) throws ParsingException {

        seqIds.clear();

        try( BufferedReader br = new BufferedReader( new FileReader( gff3File ) ) ) {

            GFF3Parser gff3Parser = new GFF3Parser();
            GFF3Handler handler = new GFF3Handler();
            gff3Parser.parse( br, handler, new Ontology.Impl( "Ontologyname", "name of ontology" ) );

        } catch( IOException | BioException | ParserException ex ) {
            this.notifyObservers( ex );
        }

        return Collections.unmodifiableList( seqIds );

    }


    @Override
    public void registerObserver( final Observer observer ) {
        this.observers.add( observer );
    }


    @Override
    public void removeObserver( final Observer observer ) {
        this.observers.remove( observer );
    }


    @Override
    public void notifyObservers( final Object data ) {
        for( Observer observer : this.observers ) {
            observer.update( data );
        }
    }


    private class GFF3Handler implements GFF3DocumentHandler {

        @Override
        public void startDocument( String string ) {
        }


        @Override
        public void endDocument() {
        }


        @Override
        public void commentLine( String string ) {
        }


        @Override
        public void recordLine( final GFF3Record gffr ) {
            if( !seqIds.contains( gffr.getSequenceID() ) ) {
                seqIds.add( gffr.getSequenceID() );
            }
        }


    }

}
