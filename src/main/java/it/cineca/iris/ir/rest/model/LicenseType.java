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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pmeriggi
 */
public class LicenseType {

    private Integer licenseTypeId;

    private String licenseId;

    private String licenseUri;

    private String rdf;

    private String licenserdf;

    private Map<String, LicenseHtml> localeCreativeCommonsHtmlMap;

    private String licenseImage;

    public Integer getLicenseTypeId() {
        return licenseTypeId;
    }

    public void setLicenseTypeId(Integer licenseTypeId) {
        this.licenseTypeId = licenseTypeId;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseUri() {
        return licenseUri;
    }

    public void setLicenseUri(String licenseUri) {
        this.licenseUri = licenseUri;
    }

    public String getRdf() {
        return rdf;
    }

    public void setRdf(String rdf) {
        this.rdf = rdf;
    }

    public String getLicenserdf() {
        return licenserdf;
    }

    public void setLicenserdf(String licenserdf) {
        this.licenserdf = licenserdf;
    }

    public Map<String, LicenseHtml> getLocaleCreativeCommonsHtmlMap() {
        return localeCreativeCommonsHtmlMap;
    }

    public void setLocaleCreativeCommonsHtmlMap(Map<String, LicenseHtml> localeCreativeCommonsHtmlMap) {
        this.localeCreativeCommonsHtmlMap = localeCreativeCommonsHtmlMap;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public LicenseType() {
        super();
        localeCreativeCommonsHtmlMap = new HashMap<>();
    }
}
