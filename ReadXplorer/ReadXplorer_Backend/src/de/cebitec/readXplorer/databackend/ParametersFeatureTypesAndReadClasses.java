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
package de.cebitec.readXplorer.databackend;

import de.cebitec.readXplorer.util.classification.FeatureType;
import java.util.Set;

/**
 *
 * @author Rolf Hilker <rolf.hilker at mikrobio.med.uni-giessen.de>
 */
public class ParametersFeatureTypesAndReadClasses extends ParametersFeatureTypes {
    
    private ParametersReadClasses parametersReadClasses;

    public ParametersFeatureTypesAndReadClasses(Set<FeatureType> selFeatureTypes, 
            ParametersReadClasses parametersReadClasses) {
        super(selFeatureTypes);
        this.parametersReadClasses = parametersReadClasses;
    }

    /**
     * @return The read class parameters associated to this parameter set.
     */
    public ParametersReadClasses getReadClassParams() {
        return parametersReadClasses;
    }

//    public void setParametersReadClasses(ParametersReadClasses parametersReadClasses) {
//        this.parametersReadClasses = parametersReadClasses;
//    }
    
    
    
}
