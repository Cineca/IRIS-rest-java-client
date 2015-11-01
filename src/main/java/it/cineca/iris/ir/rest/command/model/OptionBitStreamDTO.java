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
package it.cineca.iris.ir.rest.command.model;

import java.util.Date;

public class OptionBitStreamDTO {
	
	private String optionName = "";

	private String embargoDate = "";
		
	private String description = "";
	
	private Boolean sitodoc = false;
	
	private Integer licenseTypeId = null;
	
	private String note = null;
	
	private String typeallegato = null;
	
	private String reason = "";
	
	private Date embargoUntilDate;
	
	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getEmbargoDate() {
		return embargoDate;
	}

	public void setEmbargoDate(String embargoDate) {
		this.embargoDate = embargoDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getSitodoc() {
		return sitodoc;
	}

	public void setSitodoc(Boolean sitodoc) {
		this.sitodoc = sitodoc;
	}
	
	public Integer getLicenseTypeId() {
		return licenseTypeId;
	}

	public void setLicenseTypeId(Integer licenseTypeId) {
		this.licenseTypeId = licenseTypeId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTypeallegato() {
		return typeallegato;
	}

	public void setTypeallegato(String typeallegato) {
		this.typeallegato = typeallegato;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getEmbargoUntilDate() {
		return embargoUntilDate;
	}

	public void setEmbargoUntilDate(Date embargoUntilDate) {
		this.embargoUntilDate = embargoUntilDate;
	}

	public OptionBitStreamDTO() {
		super();
	}	
}
