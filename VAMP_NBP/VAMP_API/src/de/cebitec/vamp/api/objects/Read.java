package de.cebitec.vamp.api.objects;


/*
 * This object represents the data to use to get informations
 * about reads that match to different positions
 * Since the run domain is excluded and reads are not stored anymore this class is deprecated.
 *
 * @author jstraube
 */
@Deprecated
public class Read {
    private String readname; //maybe change to readID?
    private int position;
    private int errors;
    private int isBestMapping;

    public Read(String readname, int position, int errors, int isBestMapping){
        this.readname = readname;
        this.position = position;
        this.errors = errors;
        this.isBestMapping = isBestMapping;
    }
/*
 * @return returns the Readname
 */
    public String getReadname() {
        return readname;
    }
/*
 * @return returns the number of errors of the read
 */
    public int getErrors() {
        return errors;
    }
/*
 * @return returns the read position
 */
    public int getPosition() {
        return position;
    }
/*
 * @return returns wheater this read is a best mapping
 */
    public int getisBestMapping() {
        return isBestMapping;
    }
/*
 * @return returns the read infos as a string
 */
    @Override
    public String toString(){
        return "read: "+readname+"\tposition: "+position+"\terrors: "+errors+"%\tis best mapping.: "+isBestMapping;
    }

}
