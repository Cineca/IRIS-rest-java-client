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
 *
 */
public class RmPersonRestDTO {
    public final String type;
    public final String uniqueIdentifier;
    public final String uuid;
    public final long id;
    public final String crisId;
    public final String firstName;
    public final String lastName;
    public final AddressRestSetDTO addressSet[];
    public final ContactSetRestDTO contactSet[];
    public final PersonElementSetRestDTO personElementSet[];
    public final ParentPersonLinkElementSetRestDTO parentPersonLinkElementSet[];
    public final ChildPersonLinkElementSetRestDTO childPersonLinkElementSet[];
    public final DateMapRestDTO dateMap[];
    public final GaDictionaryMapRestDTO gaDictionaryMap[];
    public final GaSourceIdentifier gaSourceIdentifiers[];
    public final UserSetRestDTO userSet[];

    @JsonCreator
    public RmPersonRestDTO(@JsonProperty("type") String type, @JsonProperty("uniqueIdentifier") String uniqueIdentifier, @JsonProperty("uuid") String uuid, @JsonProperty("id") long id, @JsonProperty("crisId") String crisId, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("addressSet") AddressRestSetDTO[] addressSet, @JsonProperty("contactSet") ContactSetRestDTO[] contactSet, @JsonProperty("personElementSet") PersonElementSetRestDTO[] personElementSet, @JsonProperty("parentPersonLinkElementSet") ParentPersonLinkElementSetRestDTO[] parentPersonLinkElementSet, @JsonProperty("childPersonLinkElementSet") ChildPersonLinkElementSetRestDTO[] childPersonLinkElementSet, @JsonProperty("dateMap") DateMapRestDTO[] dateMap, @JsonProperty("gaDictionaryMap") GaDictionaryMapRestDTO[] gaDictionaryMap, @JsonProperty("gaSourceIdentifier") GaSourceIdentifier[] gaSourceIdentifiers, @JsonProperty("userSet") UserSetRestDTO[] userSet){
        this.type = type;
        this.uniqueIdentifier = uniqueIdentifier;
        this.uuid = uuid;
        this.id = id;
        this.crisId = crisId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressSet = addressSet;
        this.contactSet = contactSet;
        this.personElementSet = personElementSet;
        this.parentPersonLinkElementSet = parentPersonLinkElementSet;
        this.childPersonLinkElementSet = childPersonLinkElementSet;
        this.dateMap = dateMap;
        this.gaDictionaryMap = gaDictionaryMap;
        this.gaSourceIdentifiers = gaSourceIdentifiers;
        this.userSet = userSet;
    }
    
    /**
     * Get Codice Fiscale
     * 
     * @return
     */
	public String getCF() {
		String result = null;
		boolean found = false;
		int i = 0;
		
		while (!found && i<personElementSet.length) {
			if ("codiceFiscale".equals(personElementSet[i].discriminator)) {
				PersonElementSetRestDTO element = personElementSet[i];
				if (element.stringMap.length>0) {
					result = element.stringMap[0].value;
					found = true;
				}
			}
			i++;
		}
		
		return result;
	}
	
	/**
	 * Get Cris Id
	 * 
	 * @return
	 */
	public String getCrisId() {
		return crisId;
	}
	
	public String getUsername() {
		String result = null;
		
		if (this.userSet != null && this.userSet.length > 0) {
			result = this.userSet[0].username;
		}
		
		return result;
	}
    
}