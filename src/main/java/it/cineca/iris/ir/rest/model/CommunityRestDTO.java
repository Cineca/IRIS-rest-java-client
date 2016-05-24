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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author pmeriggi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommunityRestDTO extends DSpaceObject {

    private BitstreamRestDTO logo;
    private CommunityRestDTO parentCommunity;
    private List<CommunityRestDTO> subcommunities = new ArrayList<CommunityRestDTO>();
    private List<CollectionRestDTO> collections = new ArrayList<CollectionRestDTO>();

    private String copyrightText, introductoryText, shortDescription, sidebarText;

    private Integer countItems;

    public BitstreamRestDTO getLogo() {
        return logo;
    }

    public void setLogo(BitstreamRestDTO logo) {
        this.logo = logo;
    }

    public CommunityRestDTO getParentCommunity() {
        return parentCommunity;
    }

    public void setParentCommunity(CommunityRestDTO parentCommunity) {
        this.parentCommunity = parentCommunity;
    }

    public List<CommunityRestDTO> getSubcommunities() {
        return subcommunities;
    }

    public void setSubcommunities(List<CommunityRestDTO> subcommunities) {
        this.subcommunities = subcommunities;
    }

    public List<CollectionRestDTO> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionRestDTO> collections) {
        this.collections = collections;
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

    public Integer getCountItems() {
        return countItems;
    }

    public void setCountItems(Integer countItems) {
        this.countItems = countItems;
    }
    
}
