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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author pmeriggi
 *
 */
public class CollectionRestDTO extends DSpaceObject {

    //Relationships
    private BitstreamRestDTO logo;
    private List<CommunityRestDTO> parentCommunities = new ArrayList<CommunityRestDTO>();

    //Collection-Metadata
    private String license;
    private String copyrightText, introductoryText, shortDescription, sidebarText;

    //Calculated
    private Integer numberItems;
    
    //Input form id active on collection
    private Integer inputformActiveId;

    public BitstreamRestDTO getLogo() {
        return logo;
    }

    public void setLogo(BitstreamRestDTO logo) {
        this.logo = logo;
    }

    public List<CommunityRestDTO> getParentCommunities() {
        return parentCommunities;
    }

    public void setParentCommunities(List<CommunityRestDTO> parentCommunities) {
        this.parentCommunities = parentCommunities;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCopyrightText() {
        return copyrightText;
    }

    public void setCopyrightText(String copyrightText) {
        this.copyrightText = copyrightText;
    }

    public String getIntroductoryText() {
        return introductoryText;
    }

    public void setIntroductoryText(String introductoryText) {
        this.introductoryText = introductoryText;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSidebarText() {
        return sidebarText;
    }

    public void setSidebarText(String sidebarText) {
        this.sidebarText = sidebarText;
    }

    public Integer getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(Integer numberItems) {
        this.numberItems = numberItems;
    }

    public Integer getInputformActiveId() {
		return inputformActiveId;
	}

	public void setInputformActiveId(Integer inputformActiveId) {
		this.inputformActiveId = inputformActiveId;
	}
	
	@JsonIgnore
	public ContainerDTO getContainerDTO() {
		ContainerDTO container = new ContainerDTO();
		
		container.setHandle(this.getHandle());
		container.setId(this.getId());
		container.setName(this.getName());
		
		return container;
	}

	public CollectionRestDTO() {
    }

}
