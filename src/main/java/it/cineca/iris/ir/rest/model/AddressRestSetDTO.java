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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author pmeriggi
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRestSetDTO {

    public final boolean temporary;
    public final long id;
    public final String discriminator;
    public final String addressAt;
    public final String description;
    public final String postalCode;
    public final Country country;
    public final Place place;
    public final String placeString;
    public final String displayValue;

    @JsonCreator
    public AddressRestSetDTO(@JsonProperty("temporary") boolean temporary, @JsonProperty("id") long id, @JsonProperty("discriminator") String discriminator, @JsonProperty("addressAt") String addressAt, @JsonProperty("description") String description, @JsonProperty("postalCode") String postalCode, @JsonProperty("country") Country country, @JsonProperty("place") Place place, @JsonProperty("placeString") String placeString, @JsonProperty("displayValue") String displayValue) {
        this.temporary = temporary;
        this.id = id;
        this.discriminator = discriminator;
        this.addressAt = addressAt;
        this.description = description;
        this.postalCode = postalCode;
        this.country = country;
        this.place = place;
        this.placeString = placeString;
        this.displayValue = displayValue;
    }

    public static final class Country {

        public final String uniqueIdentifier;
        public final String uuid;
        public final String displayValue;

        @JsonCreator
        public Country(@JsonProperty("uniqueIdentifier") String uniqueIdentifier, @JsonProperty("uuid") String uuid, @JsonProperty("displayValue") String displayValue) {
            this.uniqueIdentifier = uniqueIdentifier;
            this.uuid = uuid;
            this.displayValue = displayValue;
        }
    }

    public static final class Place {

        public final String uniqueIdentifier;
        public final String uuid;
        public final String displayValue;

        @JsonCreator
        public Place(@JsonProperty("uniqueIdentifier") String uniqueIdentifier, @JsonProperty("uuid") String uuid, @JsonProperty("displayValue") String displayValue) {
            this.uniqueIdentifier = uniqueIdentifier;
            this.uuid = uuid;
            this.displayValue = displayValue;
        }
    }
}
