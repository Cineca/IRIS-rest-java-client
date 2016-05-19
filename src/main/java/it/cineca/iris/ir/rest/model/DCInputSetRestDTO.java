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
public class DCInputSetRestDTO extends ResourceObject {

    private String formName;
    private Integer numberPages;
    private List<DCInputSetRowRestDTO> rows;
    private List<DCInputSetValuePairsRestDTO> valuePairs;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Integer getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(Integer numberPages) {
        this.numberPages = numberPages;
    }

    public List<DCInputSetRowRestDTO> getRows() {
        return rows;
    }

    public void setRows(List<DCInputSetRowRestDTO> rows) {
        this.rows = rows;
    }

    public List<DCInputSetValuePairsRestDTO> getValuePairs() {
        return valuePairs;
    }

    public void setValuePairs(List<DCInputSetValuePairsRestDTO> valuePairs) {
        this.valuePairs = valuePairs;
    }

    public DCInputSetRestDTO() {
        super();
    }

}
