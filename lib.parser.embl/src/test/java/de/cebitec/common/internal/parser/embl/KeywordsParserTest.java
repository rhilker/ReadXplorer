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
package de.cebitec.common.internal.parser.embl;

import de.cebitec.common.parser.data.embl.Keywords;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Lukas Jelonek - Lukas.Jelonek at computational.bio.uni-giessen.de
 */
public class KeywordsParserTest {

    @Test
    public void testKeywordsParser() {
        String emptyKeywords = ".";

        KeywordsParser p = new KeywordsParser();
        Keywords parse = p.parse(emptyKeywords);
        assertThat(parse.getKeywords(), empty());
        assertThat(p.write(parse), equalTo(emptyKeywords));

        String someKeywords = "beta-glucosidase.";
        Keywords parse2 = p.parse(someKeywords);
        assertThat(parse2.getKeywords(), contains("beta-glucosidase"));
        assertThat(p.write(parse2), equalTo(someKeywords));
    }
}
