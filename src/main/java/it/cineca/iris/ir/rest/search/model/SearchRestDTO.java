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
package it.cineca.iris.ir.rest.search.model;

import java.util.List;

/**
 * 
 * @author pmeriggi
 *
 */
public class SearchRestDTO {

    private List<RestSearchCriteria> searchColsCriteria;
    private List<RestSortCriteria> sortingColsCriteria;
    private Integer offset;
    private Integer limit;
    private String expand;
    private String operator;

    public List<RestSearchCriteria> getSearchColsCriteria() {
        return searchColsCriteria;
    }

    public void setSearchColsCriteria(List<RestSearchCriteria> searchColsCriteria) {
        this.searchColsCriteria = searchColsCriteria;
    }

    public List<RestSortCriteria> getSortingColsCriteria() {
        return sortingColsCriteria;
    }

    public void setSortingColsCriteria(List<RestSortCriteria> sortingColsCriteria) {
        this.sortingColsCriteria = sortingColsCriteria;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

	public String getOperator() {
		if (operator == null) {
			operator = "AND";
		}
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
    public SearchRestDTO() {
        super();
    }

    public SearchRestDTO(List<RestSearchCriteria> searchColsCriteria, List<RestSortCriteria> sortingColsCriteria,
            Integer offset, Integer limit, String expand) {
        super();
        this.searchColsCriteria = searchColsCriteria;
        this.sortingColsCriteria = sortingColsCriteria;
        this.offset = offset;
        this.limit = limit;
        this.expand = expand == null ? "" : expand;
    }
    
    public SearchRestDTO(List<RestSearchCriteria> searchColsCriteria, List<RestSortCriteria> sortingColsCriteria,
            Integer offset, Integer limit, String expand, String operator) {
        super();
        this.searchColsCriteria = searchColsCriteria;
        this.sortingColsCriteria = sortingColsCriteria;
        this.offset = offset;
        this.limit = limit;
        this.expand = expand == null ? "" : expand;
        if (operator!=null) {
	        if (operator.equalsIgnoreCase("or")) {
				this.operator = "OR";
			} else {
				this.operator = "AND";
			}
        } else {
        	this.operator = "AND";
        }
    }
}
