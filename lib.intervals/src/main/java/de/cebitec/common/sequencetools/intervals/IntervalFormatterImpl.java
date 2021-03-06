/*
 * Copyright (C) 2013 Lukas Jelonek <ljelonek at cebitec.uni-bielefeld.de>
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
package de.cebitec.common.sequencetools.intervals;

import java.util.List;

/**
 *
 * @author Lukas Jelonek <ljelonek at cebitec.uni-bielefeld.de>
 */
class IntervalFormatterImpl<T extends Number> implements IntervalFormatter<T> {

    private List<IntervalFormatter<T>> partFormatters;
    private final Interval.Type type;

    IntervalFormatterImpl(List<IntervalFormatter<T>> list, Interval.Type type) {
        this.partFormatters = list;
        this.type = type;
    }

    @Override
    public String format(Interval interval) {
        StringBuilder sb = new StringBuilder();
        if (type != null) {
            interval = interval.as(type);
        }
        for (IntervalFormatter intervalFormatter : partFormatters) {
            sb.append(intervalFormatter.format(interval));
        }
        return sb.toString();
    }
}
