/*
 * Copyright (C) 2014 Lukas Jelonek <Lukas.Jelonek at computational.bio.uni-giessen.de>
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
package de.cebitec.common.geneticcodes.api;

/**
 * A translation engine translates a dna string to an amino acid string.
 *
 * @author Lukas Jelonek <Lukas.Jelonek at computational.bio.uni-giessen.de>
 */
public interface TranslationEngine {

    String translate(TranslationTable tt, String sequence);
}
