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
import org.biojava.bio.program.gff.GFFDocumentHandler;
import org.biojava.bio.program.gff.GFFParser;
import org.biojava.bio.program.gff.GFFRecord;
import org.biojava.utils.ParserException;


/**
 * Parser to fetch all available sequence identifiers from a GFF 2 file.
 * <p>
 * @author marie-theres, rhilker
 */
public class BioJavaGff2IdParser implements IdParserI {


    private final List<Observer> observers = new ArrayList<>();
    private final List<String> seqIds = new ArrayList<>();


    /**
     * Fetches all available sequence identifiers from a GFF 2 file.
     * <p>
     * @param gff2File the GFF 2 file to read from
     * <p>
     * @return the list of sequence identifiers contained in the given file
     * <p>
     * @throws ParsingException
     */
    @Override
    public List<String> getSequenceIds( final File gff2File ) throws ParsingException {
        seqIds.clear();
        try( BufferedReader br = new BufferedReader( new FileReader( gff2File ) ) ) {

            GFFParser gff2Parser = new GFFParser();
            GFFHandler handler = new GFFHandler();

            gff2Parser.parse( br, handler );

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


    public class GFFHandler implements GFFDocumentHandler {

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
        public void recordLine( final GFFRecord gffr ) {
            if( !seqIds.contains( gffr.getSeqName() ) ) {
                seqIds.add( gffr.getSeqName() );
            }
        }


    }

}
