//package de.cebitec.vamp.parser.reference;
//
//import de.cebitec.vamp.api.objects.FeatureType;
//import de.cebitec.vamp.parser.ReferenceJob;
//import de.cebitec.vamp.parser.common.ParsedAnnotation;
//import de.cebitec.vamp.parser.common.ParsedReference;
//import de.cebitec.vamp.parser.common.ParsedSubAnnotation;
//import de.cebitec.vamp.parser.common.ParsingException;
//import de.cebitec.vamp.parser.reference.Filter.AnnotationFilter;
//import de.cebitec.vamp.util.Observer;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.biojava.bio.seq.DNATools;
//import org.biojava.bio.seq.Feature;
//import org.biojava.bio.seq.io.SymbolTokenization;
//import org.biojava.bio.symbol.Location;
//import org.biojavax.Namespace;
//import org.biojavax.Note;
//import org.biojavax.RichObjectFactory;
//import org.biojavax.bio.seq.RichFeature;
//import org.biojavax.bio.seq.RichSequence;
//import org.biojavax.bio.seq.io.*;
//
//
///**
// *
// * @author -Rolf Hilker-
// */
//public class BioJavaToBedConverter {
//    
//        /** Use this for initializing an embl parser. */
//    public static final int EMBL = 1;
//    /** Use this for initializing a genbank parser. */
//    public static final int GENBANK = 2;
//    
//     // Fileextension used by Filechooser to choose files to be parsed by this parser
//    private static final String[] fileExtensionEmbl = new String[]{"embl"};
//    private static final String[] fileExtensionGbk = new String[]{"gbk", "gb", "genbank"};
//    // name of this parser for use in ComboBoxes
//    private static final String parserNameEmbl = "BioJava EMBL";
//    private static final String parserNameGbk = "BioJava GenBank";
//    private static final String fileDescriptionEmbl = "EMBL file";
//    private static final String fileDescriptionGbk = "GenBank file";
//    
//    private String[] fileExtension;
//    private String parserName;
//    private String fileDescription;
//    
//    private final RichSequenceFormat seqFormat;
//    
//    private ArrayList<Observer> observers = new ArrayList<Observer>();
//    private String errorMsg;
//    
//    public BioJavaToBedConverter(int type) {
//        if (type == BioJavaParser.EMBL){
//            this.fileExtension = fileExtensionEmbl;
//            this.parserName = parserNameEmbl;
//            this.fileDescription = fileDescriptionEmbl;
//            this.seqFormat = new EMBLFormat();
//            
//        } else { //for your info: if (type == BioJavaParser.GENBANK){
//            this.fileExtension = fileExtensionGbk;
//            this.parserName = parserNameGbk;
//            this.fileDescription = fileDescriptionGbk;
//            this.seqFormat = new GenbankFormat();
//        }
//    }
//
//    @Override
//    public void convertReference(ReferenceJob referenceJob, AnnotationFilter filter) throws ParsingException {
//        
//        
//        outf = open('test/annotation/vitis_vinifera.bed', 'w')
//    header = """track name=vitVinGenes description="V. vinifera cpdna genes" itemRgb=On\n"""
//    outf.write(header)
//    for record in SeqIO.parse(open("test/annotation/vitis_vinifera.gb", "rU"), "genbank") :
//        for feature in record.features:
//            if feature.type == 'gene':
//                start = feature.location.start.position
//                stop = feature.location.end.position
//                try:
//                    name = feature.qualifiers['gene'][0]
//                except:
//                    # some features only have a locus tag
//                    name = feature.qualifiers['locus_tag'][0]
//                if feature.strand < 0:
//                    strand = "-"
//                else:
//                    strand = "+"
//                bed_line = "cpdna\t{0}\t{1}\t{2}\t1000\t{3}\t{0}\t{1}\t65,105,225\n".format(start, stop, name, strand)
//                outf.write(bed_line)
//    outf.close()
//                
//        refGenome.setAnnotationFilter(filter);
//        //at first store all eonxs in one data structure and add them to the ref genome at the end
//        List<ParsedAnnotation> exons = new ArrayList<ParsedAnnotation>();
//
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Start reading file  \"{0}\"", referenceJob.getFile());
//        try {
//
//            BufferedReader in = new BufferedReader(new FileReader(referenceJob.getFile()));
//            BufferedWriter out = new BufferedWriter(new FileWriter(ReferenceJob.getFile() + ".bed"));
//            Namespace ns = RichObjectFactory.getDefaultNamespace();
//            SymbolTokenization dna = DNATools.getDNA().getTokenization("token");
//            RichSequenceBuilderFactory factory = RichSequenceBuilderFactory.THRESHOLD;
//
//            RichStreamReader seqIter = new RichStreamReader(in, seqFormat, dna, factory, ns);
//
//            // take only the first sequence from file, if exists
//            while (seqIter.hasNext()) {
//                RichSequence seq = null;
//                try {
//                    seq = seqIter.nextRichSequence();
////                    this.sendErrorMsg("rich seq set");
//                    
//                    out.append("track name=" + referenceJob.getName() + " description=" + referenceJob.getDescription() + "\n");
//                    refGenome.setDescription(referenceJob.getDescription());
//                    refGenome.setName(referenceJob.getName());
//                    refGenome.setTimestamp(referenceJob.getTimestamp());
//                    refGenome.setSequence(seq.seqString());
//
//                    // iterate through all annotations
//                    Iterator<Feature> featIt = seq.getFeatureSet().iterator();
//                    while (featIt.hasNext()) {
//                        RichFeature annotation = (RichFeature) featIt.next();
//
//                        // attributes of annotation that should be stored
//                        String parsedType = null;
//                        String locusTag = "unknown locus tag";
//                        String product = null;
//                        int start = 0;
//                        int stop = 0;
//                        int strand = 0;
//                        String ecNumber = null;
//                        String geneName = null;
//                        List<ParsedSubAnnotation> subAnnotations = new ArrayList<ParsedSubAnnotation>();
//                        Location location = annotation.getLocation();
//
//                        parsedType = annotation.getType();
//                        start = location.getMin();
//                        stop = location.getMax();
//                        if (start >= stop) {
//                            this.sendErrorMsg("Start bigger than stop in " + referenceJob.getFile().getAbsolutePath() 
//                                    + ". Found start: " + start + ", stop: " + stop + ". Annotation ignored.");
//                            continue;
//                        }
//                        try {
//                            strand = this.determineStrand(annotation, referenceJob);
//                        } catch (IllegalStateException e) {
//                            this.sendErrorMsg(e.getMessage());
//                            continue;
//                        }
//
//                        //Determine annotation tags
//                        Iterator<Note> noteIter = annotation.getRichAnnotation().getNoteSet().iterator();
//                        while (noteIter.hasNext()) {
//                            Note note = noteIter.next();
//                            String name = note.getTerm().getName();
//                            String value = note.getValue();
//
//                            if (name.equals("locus_tag")) {
//                                locusTag = value;
//                            } else if (name.equalsIgnoreCase("locus")) {
//                                locusTag = value;
//                            } else if (name.equalsIgnoreCase("name") && locusTag.equals("unknown locus tag")) {
//                                locusTag = value;
//                            } else if (name.equals("product")) {
//                                product = value;
//                            } else if (name.equals("EC_number")) {
//                                ecNumber = value;
//                            } else if (name.equals("gene")) {
//                                geneName = value;
//                            }
//                        }
//
//                        /* 
//                         * If the type of the annotation is unknown to vamp (see below),
//                         * an undefined type is used.
//                         */
//                        FeatureType type = FeatureType.UNDEFINED;
//
//                        // look for known types
//                        if (parsedType.equalsIgnoreCase("CDS")) {
//                            type = FeatureType.CDS;
//                        } else if (parsedType.equalsIgnoreCase("repeat_unit")) {
//                            type = FeatureType.REPEAT_UNIT;
//                        } else if (parsedType.equalsIgnoreCase("rRNA")) {
//                            type = FeatureType.RRNA;
//                        } else if (parsedType.equalsIgnoreCase("source")) {
//                            type = FeatureType.SOURCE;
//                        } else if (parsedType.equalsIgnoreCase("tRNA")) {
//                            type = FeatureType.TRNA;
//                        } else if (parsedType.equalsIgnoreCase("misc_RNA")) {
//                            type = FeatureType.MISC_RNA;
//                        } else if (parsedType.equalsIgnoreCase("miRNA")) {
//                            type = FeatureType.MIRNA;
//                        } else if (parsedType.equalsIgnoreCase("gene")) {
//                            type = FeatureType.GENE;
//                        } else if (parsedType.equalsIgnoreCase("mRNA")) {
//                            type = FeatureType.MRNA;
//                        } else if (parsedType.equalsIgnoreCase("exon")) {
//                            type = FeatureType.EXON;
//                            System.out.println("exon found"); //if exon is within range of lastGene = belongs to it
//                            
//                            exons.add(new ParsedAnnotation(type, start, stop, strand, locusTag, product, ecNumber, geneName, subAnnotations));
//                            continue;
//                        } else {
//                            this.sendErrorMsg(referenceJob.getFile().getName()
//                                    + ": Using unknown annotation type for " + parsedType);
//                        }
//                        
//
//                        /*
//                         * for eukaryotic organism its important to see the single cds/exons
//                         * to exclude introns
//                         * if we choose min and max we get the first pos of the first cds/exon
//                         * of one gene and the last position of the last cds/exon and we can't
//                         * see exon intron structure
//                         */
//                        //check annotation for subannotations
//                        if (location.toString().contains("join")) {
//                            Iterator<Location> subAnnotationIter = location.blockIterator();
//                            int subStart = -1;
//                            int subStop = -1;
//                            while (subAnnotationIter.hasNext()) {
//
//                                String pos = subAnnotationIter.next().toString();
//                                //array always contains at least 2 entries
//                                String[] posArray = pos.split("\\..");
//                                subStart = Integer.parseInt(posArray[0]);
//                                subStop = Integer.parseInt(posArray[1]);
//                                subAnnotations.add(new ParsedSubAnnotation(subStart, subStop, type));
//                            }
//                        }
//
//                        //TODO: filter unknown annotations, if a known annotation exists with same locus! best to do not here
//                        ParsedAnnotation currentAnnotation = new ParsedAnnotation(type, start, stop, strand, locusTag, product, ecNumber, geneName, subAnnotations);
//                        refGenome.addAnnotation(currentAnnotation);
////                        if (currentAnnotation.getType() == FeatureType.GENE){
////                            lastGenes.add(currentAnnotation);
////                        }
//
//                    }
//                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "File successfully read");
//                } catch (Exception ex) {
//                    this.sendErrorMsg(ex.getMessage());
//                    seqIter.nextSequence();
//                }
//
//            }
//
//        } catch (Exception ex) {
//            this.sendErrorMsg(ex.getMessage());
//        }
//        refGenome.addSubAnnotations(exons);
//        return refGenome;
//        
//    }
//    @Override
//    public String getParserName() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public String[] getFileExtensions() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public String getInputFileDescription() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void registerObserver(Observer observer) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void removeObserver(Observer observer) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void notifyObservers(Object data) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    
//    
//    
//}
