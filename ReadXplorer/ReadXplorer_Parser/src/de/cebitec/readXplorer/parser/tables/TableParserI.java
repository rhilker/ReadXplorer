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
package de.cebitec.readXplorer.parser.tables;

import de.cebitec.readXplorer.parser.common.ParserI;
import de.cebitec.readXplorer.parser.common.ParsingException;
import java.io.File;
import java.util.List;

/**
 * Interface for table parsers.
 * 
 * @author Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public interface TableParserI extends ParserI {
    
    /**
     * Parses a table into a list of lists of objects. The inner lists represent
     * the data of one row, while the outer list is the list of rows in the
     * table.
     * @return A table into a list of lists of objects. The inner lists
     * represent the data of one row, while the outer list is the list of rows
     * in the table.
     * @param fileToRead The file containing the table to parse.
     * @throws de.cebitec.readXplorer.parser.common.ParsingException
     */
    public List<List<?>> parseTable(File fileToRead) throws ParsingException;
    
}
