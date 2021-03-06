/*
 * Copyright (C) 2015 Lukas Jelonek - Lukas.Jelonek at computational.bio.uni-giessen.de
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
package de.cebitec.common.parser.data.genbank;

/**
 *
 * @author Lukas Jelonek - Lukas.Jelonek at computational.bio.uni-giessen.de
 */
public class Version {
    private String compoundAccession;
    private String giAccession;

    public Version(String compoundAccession, String giAccession) {
        this.compoundAccession = compoundAccession;
        this.giAccession = giAccession;
    }

    public String getCompoundAccession() {
        return compoundAccession;
    }

    public void setCompoundAccession(String compoundAccession) {
        this.compoundAccession = compoundAccession;
    }

    public String getGiAccession() {
        return giAccession;
    }

    public void setGiAccession(String giAccession) {
        this.giAccession = giAccession;
    }

}
