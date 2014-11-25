package de.cebitec.vamp.parser.reference;

import de.cebitec.vamp.parser.ReferenceJob;
import de.cebitec.vamp.parser.common.ParsedFeature;
import de.cebitec.vamp.parser.common.ParsedReference;
import de.cebitec.vamp.parser.common.ParsingException;
import de.cebitec.vamp.parser.reference.Filter.FeatureFilter;
import de.cebitec.vamp.util.FeatureType;
import de.cebitec.vamp.util.Observer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.biojava.bio.BioException;
import org.biojava.bio.program.gff.GFFDocumentHandler;
import org.biojava.bio.program.gff.GFFParser;
import org.biojava.bio.program.gff.GFFRecord;
import org.biojava.utils.ParserException;
  

/**
 * A GFF 2 parser for parsing the sequence from a fasta file contained in the
 * ReferenceJob and the GFF2 annotations from the GFF2 file contained in the
 * ReferenceJob.
 *
 * @author marie-theres, Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class BioJavaGff2Parser extends FastaReferenceParser {
    
     // File extension used by Filechooser to choose files to be parsed by this parser
    private static final String[] fileExtension = new String[]{"gff", "gff2"};
    // name of this parser for use in ComboBoxes
    private static final String parserName = "GFF2 file";
    private static final String fileDescription = "GFF2 file";
    
    private List<Observer> observers = new ArrayList<>();
    private String errorMsg;

    /**
     * Parses the sequence from a fasta file contained in the ReferenceJob and
     * the GFF2 annotations from the GFF2 file contained in the ReferenceJob.
     * @param referenceJob the reference job containing the files
     * @param filter the feature filter to use (removes undesired features)
     * @return the parsed reference object with all parsed features
     * @throws ParsingException
     */
    @Override
    public ParsedReference parseReference(final ReferenceJob referenceJob, FeatureFilter filter) throws ParsingException {
        
        final ParsedReference refGenome = super.parseReference(referenceJob, filter);
        refGenome.setFeatureFilter(filter);
        refGenome.setHasSubFeatures(false);

        try (BufferedReader reader = new BufferedReader(new FileReader(referenceJob.getGffFile()))) {
            GFFParser gffParser = new GFFParser();
            
            gffParser.parse(reader, new GFFDocumentHandler() {
                
                @Override
                public void startDocument(String string) {
//                    registerObserver(refGenome);
                }

                @Override
                public void endDocument() {
//                    notifyObservers(ParsedReference.FINISHED);
//                    removeObserver(refGenome);
                }

                @Override
                public void commentLine(String string) {
                    //TODO: anything to do here? check for extra information?
                }

                @Override
                @SuppressWarnings("unchecked")
                public void recordLine(GFFRecord gffr) {
                    
                    String parsedType;
                    String locusTag = "unknown locus tag";
                    String geneName = "";
                    String product = "";
                    String ecNumber = "";
                    String identifier = "";
                    int start;
                    int stop;
                    int strand;
                    List<String> parentIds = new ArrayList<>();
                //    String name;
//                    int subStart;
//                    int subStop;
//                    String pos;
//                    String[] posArray;
                    
                    if (gffr.getSeqName().equals(refGenome.getName())) {

                        parsedType = gffr.getFeature();
                        start = gffr.getStart();
                        stop = gffr.getEnd();
                        strand = gffr.getStrand().getValue();
                        if (strand != -1 && strand != 1) {
                            sendErrorMsg("Strand of this feature is not valid: " + strand + " in feature with start: " + gffr.getStart());
                            return;
                        }
                        //phase can be used for translation within incomplete annotated genes. a given phase shows where to start in such a case
//                        int phase = gffr.getPhase(); //0, 1, 2 or -1, if not used
//                        if (phase >= 0 && phase <= 2) {
//                            if (strand == SequenceUtils.STRAND_FWD) {
//                                start += phase;
//                            } else {// rev strand
//                                stop -= phase;
//                            }
//                        } // else ignore phase as it is not used
                        
                        Map attributes = gffr.getGroupAttributes();
                        Iterator attrIt = attributes.keySet().iterator();
                        Object key;
                        String keyString;
                        Object value;
                        Object attribute;
                        String attrString;
                        
                        while (attrIt.hasNext()) {
                            key = attrIt.next();
                            if (key instanceof String) {
                                keyString = ((String) key);
                                value = attributes.get(keyString);
                                if (value instanceof List && !((List) value).isEmpty()) {
                                    attribute = ((List) value).get(0); //currently only one item per tag is supported, except for parent
                                    if (attribute instanceof String) {
                                        attrString = (String) attribute;
                                        switch (keyString) {
                                            case "ID":   locusTag = attrString; 
                                                         identifier = attrString; break;
                                            case "Name": geneName = attrString; break;
//                                            case "ORF": geneName = attrString; break;
//                                            case "CDS": geneName = attrString; break;
//                                            case "GENE": geneName = attrString; break;
//                                    case "Target": break; //other available fields according to gff3 definition
//                                    case "Gap": break;
//                                    case "Derives_from": break;
                                            case "Note": product = attrString; break;
//                                    case "Dbxref":  break;
//                                    case "Ontology_term": break;
//                                    case "Is_circular": break;
                                            default: ;
                                        }
                                        if (keyString.equalsIgnoreCase(parsedType)) {
                                            geneName = attrString;
                                        }
                                    }
                                    //process tags with multiple entries in a block
                                    switch (keyString) {
                                        case "Parent": parentIds = (List<String>) value; break;
                                    }
                                    
//                                    //process tags with multiple entries here
//                                    for (Object attr : (List) value) {
//                                        if (attr instanceof String) {
//                                            attrString = (String) attr;
//                                            switch (keyString) {
//                                                //separate multiple attributes here
//                                            }
//                                        }
//                                    }
                                }
                            }
                        }
                        
                        final String geneNameString = "geneName";
                        if (attributes.containsKey(geneNameString)) {
                            geneName = (String) attributes.get(geneNameString);
                        }
                        final String aliasString = "Alias";
                        if (attributes.containsKey(aliasString)) {
                            locusTag = (String) attributes.get(aliasString);
                        }
                        
                        FeatureType type = FeatureType.getFeatureType(parsedType);
                        if (type == FeatureType.UNDEFINED) {
                            sendErrorMsg(referenceJob.getFile().getName()
                                    + ": Using unknown feature type for " + parsedType);
                        }
                        
                        ParsedFeature currentFeature = new ParsedFeature(type, start, stop, strand, 
                                locusTag, product, ecNumber, geneName, null, parentIds, identifier);
                        refGenome.addFeature(currentFeature);
                    }
                    
                }
            });
            
        } catch (IOException | BioException | ParserException ex) {
            JOptionPane.showMessageDialog(new JPanel(), ex.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
            throw new ParsingException(ex);
        }

        return refGenome;
    }

    @Override
    public String getName() {
        return parserName;
    }

    @Override
    public String getInputFileDescription() {
        return fileDescription;
    }

    @Override
    public String[] getFileExtensions() {
        return fileExtension;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object data) {
        for (Observer observer : this.observers) {
            observer.update(data);
        }
    }

    /**
     * Method setting and sending the error msg to all observers.
     *
     * @param errorMsg the error msg to send
     */
    private void sendErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
        this.notifyObservers(this.errorMsg);
    }
}