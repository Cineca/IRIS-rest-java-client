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

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * 
 * @author pmeriggi
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BitstreamRestDTO extends DSpaceObject {

    private Integer sequenceId;
    private String bundleName;
    private String description;
    private String format;
    private String mimeType;
    private Long sizeBytes;
    private DSpaceObject parentObject;
    private String retrieveLink;
    private CheckSum checkSum;

    private LicenseType licenseType;

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setSizeBytes(Long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public void setParentObject(DSpaceObject parentObject) {
        this.parentObject = parentObject;
    }

    public void setRetrieveLink(String retrieveLink) {
        this.retrieveLink = retrieveLink;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Long getSizeBytes() {
        return sizeBytes;
    }

    public String getRetrieveLink() {
        return retrieveLink;
    }

    public DSpaceObject getParentObject() {
        return parentObject;
    }

    public CheckSum getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(CheckSum checkSum) {
        this.checkSum = checkSum;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public BitstreamRestDTO() {
        super();
    }

}
