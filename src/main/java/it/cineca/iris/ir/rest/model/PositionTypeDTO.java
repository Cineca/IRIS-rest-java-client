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
public class PositionTypeDTO {
    public final String uniqueIdentifier;
    public final String uuid;
    public final long id;
    public final String description;
    public final String displayValue;
    public final String startDate;
    public final OrganizationUnitType organizationUnitType;
    public final GaSourceIdentifier gaSourceIdentifiers[];

    @JsonCreator
    public PositionTypeDTO(@JsonProperty("uniqueIdentifier") String uniqueIdentifier, @JsonProperty("uuid") String uuid, @JsonProperty("id") long id, @JsonProperty("description") String description, @JsonProperty("displayValue") String displayValue, @JsonProperty("startDate") String startDate, @JsonProperty("organizationUnitType") OrganizationUnitType organizationUnitType, @JsonProperty("gaSourceIdentifiers") GaSourceIdentifier[] gaSourceIdentifiers){
        this.uniqueIdentifier = uniqueIdentifier;
        this.uuid = uuid;
        this.id = id;
        this.description = description;
        this.displayValue = displayValue;
        this.startDate = startDate;
        this.organizationUnitType = organizationUnitType;
        this.gaSourceIdentifiers = gaSourceIdentifiers;
    }

    public static final class OrganizationUnitType {
        public final String displayValue;

        @JsonCreator
        public OrganizationUnitType(@JsonProperty("displayValue") String displayValue){
            this.displayValue = displayValue;
        }
    }
}

