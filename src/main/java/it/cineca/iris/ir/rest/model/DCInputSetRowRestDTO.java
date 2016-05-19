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

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DCInputSetRowRestDTO extends DSpaceObject {

    private Integer pageNumber;
    private Integer rowNumber;
    private String dcElement = null;
    private String dcQualifier = null;
    private String valueListName = null;
    private String dcSchema = null;
    private String label = null;
    private String inputType = null;
    private Boolean required = false;
    private String warning = null;
    private Boolean repeatable = false;
    private String hint = null;
    private String visibility = null;
    private String readOnly = null;
    private Boolean noupdate = false;
    private List<String> typeBind = null;

    public String getDcElement() {
        return dcElement;
    }

    public void setDcElement(String dcElement) {
        this.dcElement = dcElement;
    }

    public String getDcQualifier() {
        return dcQualifier;
    }

    public void setDcQualifier(String dcQualifier) {
        this.dcQualifier = dcQualifier;
    }

    public String getValueListName() {
        return valueListName;
    }

    public void setValueListName(String valueListName) {
        this.valueListName = valueListName;
    }

    public String getDcSchema() {
        return dcSchema;
    }

    public void setDcSchema(String dcSchema) {
        this.dcSchema = dcSchema;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public Boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean isNoupdate() {
        return noupdate;
    }

    public void setNoupdate(Boolean noupdate) {
        this.noupdate = noupdate;
    }

    public List<String> getTypeBind() {
        return typeBind;
    }

    public void setTypeBind(List<String> typeBind) {
        this.typeBind = typeBind;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public DCInputSetRowRestDTO() {
        super();
    }
    
}
