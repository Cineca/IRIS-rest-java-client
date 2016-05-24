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
 * @author pmeriggi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactSetRestDTO {

    public final boolean temporary;
    public final long id;
    public final String discriminator;
    public final String description;
    public final boolean principal;

    @JsonCreator
    public ContactSetRestDTO(@JsonProperty("temporary") boolean temporary, @JsonProperty("id") long id, @JsonProperty("discriminator") String discriminator, @JsonProperty("description") String description, @JsonProperty("principal") boolean principal) {
        this.temporary = temporary;
        this.id = id;
        this.discriminator = discriminator;
        this.description = description;
        this.principal = principal;
    }
}
