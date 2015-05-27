/*
 *  Java Iris Rest Client, pratical example for use IRIS REST API
 * 
 *  Copyright (c) 2015, CINECA and third-party contributors as
 *  indicated by the @author tags or express copyright attribution
 *  statements applied by the authors.  All third-party contributions are
 *  distributed under license by CINECA.
 * 
 *  This copyrighted material is made available to anyone wishing to use, modify,
 *  copy, or redistribute it subject to the terms and conditions of the GNU
 *  Lesser General Public License v3 or any later version, as published 
 *  by the Free Software Foundation, Inc. <http://fsf.org/>.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 *  for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this distribution; if not, write to:
 *  Free Software Foundation, Inc.
 *  51 Franklin Street, Fifth Floor
 *  Boston, MA  02110-1301  USA
 */
package it.cineca.iris.ir.rest.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author pmeriggi
 */
public class PersonElementSetRestDTO {

    public final boolean temporary;
    public final String discriminator;
    public final GaDictionaryMapRestDTO gaDictionaryMap[];
    public final OrganizationUnitMap organizationUnitMap[];
    public final PersonMap personMap[];
    public final StringMap stringMap[];
    public final BooleanMap booleanMap[];
    public final IntegerMap integerMap[];
    public final NumberMap numberMap[];
    public final DateMap dateMap[];
    public final ClobMap clobMap[];

    @JsonCreator
    public PersonElementSetRestDTO(@JsonProperty("temporary") boolean temporary, @JsonProperty("discriminator") String discriminator, @JsonProperty("gaDictionaryMap") GaDictionaryMapRestDTO[] gaDictionaryMap, @JsonProperty("organizationUnitMap") OrganizationUnitMap[] organizationUnitMap, @JsonProperty("personMap") PersonMap[] personMap, @JsonProperty("stringMap") StringMap[] stringMap, @JsonProperty("booleanMap") BooleanMap[] booleanMap, @JsonProperty("integerMap") IntegerMap[] integerMap, @JsonProperty("numberMap") NumberMap[] numberMap, @JsonProperty("dateMap") DateMap[] dateMap, @JsonProperty("clobMap") ClobMap[] clobMap) {
        this.temporary = temporary;
        this.discriminator = discriminator;
        this.gaDictionaryMap = gaDictionaryMap;
        this.organizationUnitMap = organizationUnitMap;
        this.personMap = personMap;
        this.stringMap = stringMap;
        this.booleanMap = booleanMap;
        this.integerMap = integerMap;
        this.numberMap = numberMap;
        this.dateMap = dateMap;
        this.clobMap = clobMap;
    }

    public static final class OrganizationUnitMap {

        @JsonCreator
        public OrganizationUnitMap() {
        }
    }

    public static final class PersonMap {

        @JsonCreator
        public PersonMap() {
        }
    }

    public static final class StringMap {

        public final String key;
        public final String value;

        @JsonCreator
        public StringMap(@JsonProperty("key") String key, @JsonProperty("value") String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static final class BooleanMap {

        @JsonCreator
        public BooleanMap() {
        }
    }

    public static final class IntegerMap {

        @JsonCreator
        public IntegerMap() {
        }
    }

    public static final class NumberMap {

        @JsonCreator
        public NumberMap() {
        }
    }

    public static final class DateMap {

        public final String key;
        public final String value;

        @JsonCreator
        public DateMap(@JsonProperty("key") String key, @JsonProperty("value") String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static final class ClobMap {

        @JsonCreator
        public ClobMap() {
        }
    }
}
