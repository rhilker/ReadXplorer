package de.cebitec.vamp.parser;

import java.io.File;
import java.sql.Timestamp;

/**
 *
 * @author ddoppmeier
 */
public interface Job {

    public String getName();

    public File getFile();

//    public ParserI getParser();

    public String getDescription();

    public Timestamp getTimestamp();

    public Long getID();

}