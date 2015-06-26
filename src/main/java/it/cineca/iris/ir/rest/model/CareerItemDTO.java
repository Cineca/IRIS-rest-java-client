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
 * 
 * @author pmeriggi
 * 
 */
public class CareerItemDTO {
    public final String type;
    public final boolean temporary;
    public final Integer id;
    public final String discriminator;
    public final String startDate;
    public final String endDate;
    public final OrganizationUnitDTO organizationUnit;
    public final PositionTypeDTO positionType;

    @JsonCreator
    public CareerItemDTO(@JsonProperty("type") String type, @JsonProperty("temporary") boolean temporary, @JsonProperty("id") Integer id, @JsonProperty("discriminator") String discriminator, @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate, @JsonProperty("organizationUnit") OrganizationUnitDTO organizationUnit, @JsonProperty("positionType") PositionTypeDTO positionType){
        this.type = type;
        this.temporary = temporary;
        this.id = id;
        this.discriminator = discriminator;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizationUnit = organizationUnit;
        this.positionType = positionType;
    }
}
