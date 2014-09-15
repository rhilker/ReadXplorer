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
package de.cebitec.readXplorer.util;

import de.cebitec.readXplorer.util.classification.Classification;
import de.cebitec.readXplorer.util.classification.ComparisonClass;
import de.cebitec.readXplorer.util.classification.MappingClass;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.NbPreferences;

/**
 *
 * @author Rolf Hilker <rhilker at mikrobio.med.uni-giessen.de>
 */
public class ColorUtils {
    
    private ColorUtils() {
    }
    
    /**
     * Calculates and returns an updated color for the saturation and brigthness
     * of the given color according to the given value in relation to the given
     * maximum value.
     * @param currentValue The value to represent by saturation and brigthness
     * @param maxValue The maximum accepted value possible for the currentValue
     * @param origColor The original color to update
     * @return The color with the updated saturation and brigthness 
     * corresponding to the given phred score.
     */
    public static Color getAdaptedColor(int currentValue, int maxValue, Color origColor) {
        float[] hsbColorValues = Color.RGBtoHSB(origColor.getRed(), origColor.getGreen(), origColor.getBlue(), null);
        float sAndB = ColorProperties.MIN_SATURATION_AND_BRIGTHNESS + currentValue * ColorUtils.getSaturationAndBrightnessPerUnit(maxValue);
        return Color.getHSBColor(hsbColorValues[0], sAndB, sAndB);
    }
    
    /**
     * Calculates the saturation and brigthness value of a color per given unit.
     * @param numberOfUnits number of units to be represented in brightness
     * and saturation
     * @return The saturation and brigthness value of a color per given unit.
     */
    public static float getSaturationAndBrightnessPerUnit(int numberOfUnits) {
        return ColorProperties.SPAN_SATURATION_AND_BRIGTHNESS / numberOfUnits;
    }
    
    /**
     * Creates a map of all classifications, which currently support coloring,
     * to their default color.
     * @return The map of classifications to their color
     */
    public static Map<Classification, Color> getDefaultColorMap() {
        Map<Classification, Color> classToColorMap = new HashMap<>();
        classToColorMap.put(MappingClass.COMMON_MATCH, ColorProperties.COMMON_MATCH);
        classToColorMap.put(MappingClass.BEST_MATCH, ColorProperties.BEST_MATCH);
        classToColorMap.put(MappingClass.SINGLE_BEST_MATCH, ColorProperties.BEST_MATCH_SINGLE);
        classToColorMap.put(MappingClass.PERFECT_MATCH, ColorProperties.PERFECT_MATCH);
        classToColorMap.put(MappingClass.SINGLE_PERFECT_MATCH, ColorProperties.PERFECT_MATCH_SINGLE);
        classToColorMap.put(ComparisonClass.DIFF_COVERAGE, ColorProperties.COV_DIFF_COLOR);
        classToColorMap.put(ComparisonClass.TRACK1_COVERAGE, ColorProperties.TRACK1_COLOR);
        classToColorMap.put(ComparisonClass.TRACK2_COVERAGE, ColorProperties.TRACK2_COLOR);
        return classToColorMap;
    }
    
    /**
     * Creates a map of all standard mapping classifications to their current
     * color selected by the user or the default color, if the user color is not
     * available.
     * @return The map of classifications to their color
     */
    public static Map<Classification, Color> updateMappingClassColors() {
        Map<Classification, Color> classToColorMap = new HashMap<>();
        Map<Classification, Color> defaultColorMap = ColorUtils.getDefaultColorMap();
        for (MappingClass mappingClass : MappingClass.values()) {
            classToColorMap.put(mappingClass, ColorUtils.initColor(mappingClass, defaultColorMap.get(mappingClass)));
        }
        return classToColorMap;
    }
    
    /**
     * Creates a map of all comparison classifications to their current color
     * selected by the user or the default color, if the user color is not
     * available.
     * @return The map of classifications to their color
     */
    public static Map<Classification, Color> updateComparisonClassColors() {
        Map<Classification, Color> classToColorMap = new HashMap<>();
        Map<Classification, Color> defaultColorMap = ColorUtils.getDefaultColorMap();
        for (ComparisonClass classType : ComparisonClass.values()) {
            classToColorMap.put(classType, ColorUtils.initColor(classType, defaultColorMap.get(classType)));
        }
        return classToColorMap;
    }
    
    /**
     * Creates a map of all classifications, which currently support coloring,
     * to their current color selected by the user or the default color, if the
     * user color is not available.
     * @return The map of classifications to their color
     */
    public static Map<Classification, Color> updateClassificationColors() {
        Map<Classification, Color> classToColorMap = ColorUtils.updateMappingClassColors();
        classToColorMap.putAll(ColorUtils.updateComparisonClassColors());
        return classToColorMap;
    }
    
    /**
     * Initializes the color of a class type.
     * @param classType The class type whose color needs to be set
     * @param defaultColor The default color for this class type
     * @return The color of the given class type.
     */
    public static Color initColor(Classification classType, Color defaultColor) {
        String colorRGB = NbPreferences.forModule(Object.class).get(classType.getTypeString(), "");
        if (!colorRGB.isEmpty()) { //otherwise default color is kept
            return new Color(Integer.parseInt(colorRGB));
        } else {
            return defaultColor;
        }
    }

}
