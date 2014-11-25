package de.cebitec.vamp.parser.output;

import de.cebitec.vamp.parser.TrackJob;
import de.cebitec.vamp.util.Benchmark;
import de.cebitec.vamp.util.Observable;
import de.cebitec.vamp.util.Observer;
import de.cebitec.vamp.util.Pair;
import de.cebitec.vamp.util.SamUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMFileWriter;
import net.sf.samtools.SAMFormatException;
import net.sf.samtools.SAMRecordIterator;
import org.openide.util.NbBundle;

/**
 * Sorts a sam or bam file according to the specified SamFileHeader.SortOrder
 * and sets the new sorted file as the file in the given TrackJob.
 * 
 * @author Rolf Hilker <rhilker at cebitec.uni-bielefeld.de>
 */
public class SamBamSorter implements Observable {
    
    private List<Observer> observers;

    /**
     * Sorts a sam or bam file according to the specified
     * SamFileHeader.SortOrder and sets the new sorted file as the file in the
     * given TrackJob.
     */
    public SamBamSorter() {
        this.observers = new ArrayList<>();
    }
    
    /**
     * Sorts a sam or bam file according to the specified
     * SamFileHeader.SortOrder and sets the new sorted file as the file in the
     * trackJob.
     * @param trackJob track job containing the file to sort
     * @param sortOrder the sort order to use
     * @param sortOrderMsg the string representation of the sort order for
     * status messages
     * @return true, if the sorting was successful, false otherwise
     */
    public boolean sortSamBam(TrackJob trackJob, SAMFileHeader.SortOrder sortOrder, String sortOrderMsg) {
        boolean success = true;
        this.notifyObservers(NbBundle.getMessage(SamBamSorter.class, "MSG_SamBamSorter.sort.Start", sortOrderMsg));
        long start = System.currentTimeMillis();
        String msg;
        Pair<SAMFileWriter, File> writerAndFile = null;

        try (SAMFileReader samBamReader = new SAMFileReader(trackJob.getFile())) {
            SAMRecordIterator samItor = samBamReader.iterator();
            SAMFileHeader header = samBamReader.getFileHeader();
            samBamReader.setValidationStringency(SAMFileReader.ValidationStringency.LENIENT);
            header.setSortOrder(sortOrder);
            writerAndFile = SamUtils.createSamBamWriter(trackJob.getFile(), header, false, sortOrderMsg);
            SAMFileWriter writer = writerAndFile.getFirst();
            while (samItor.hasNext()) {
                try {
                    writer.addAlignment(samItor.next());
                } catch (SAMFormatException e) {
                    if (!e.getMessage().contains("MAPQ should be 0")) {
                        this.notifyObservers(e.getMessage());
                    } //all reads with the "MAPQ should be 0" error are just ordinary unmapped reads and thus ignored  
                }
            }
            samItor.close();
            writer.close();

            trackJob.setFile(writerAndFile.getSecond());

            msg = NbBundle.getMessage(SamBamSorter.class, "MSG_SamBamSorter.sort.Finish", sortOrderMsg);
        } catch (Exception e) {
            if (writerAndFile != null) {
                trackJob.setFile(writerAndFile.getSecond());
            } else {
                trackJob.setFile(new File(trackJob.getFile(), sortOrderMsg));
            }
            success = false;
            this.notifyObservers(e.getMessage());
            msg = NbBundle.getMessage(SamBamSorter.class, "MSG_SamBamSorter.sort.Failed", trackJob.getFile());
        }
        long finish = System.currentTimeMillis();
        this.notifyObservers(Benchmark.calculateDuration(start, finish, msg));
        return success;
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
}