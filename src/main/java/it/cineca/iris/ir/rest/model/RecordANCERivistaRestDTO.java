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

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author pmeriggi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordANCERivistaRestDTO extends ResourceObject {

    private TitoliRivistaANCE titoli = new TitoliRivistaANCE();
    private String codice;
    private String ISSN;
    private String EISSN;
    private String paese;
    private String casaEditrice;
    private String validoDal;
    private String validoAl;
    private String natura;
    private String lingua;
    private String codiceCRIS;
    private Date dateSent;
    private String submitter;

    public TitoliRivistaANCE getTitoli() {
        return titoli;
    }

    public void setTitoli(TitoliRivistaANCE titoli) {
        this.titoli = titoli;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getEISSN() {
        return EISSN;
    }

    public void setEISSN(String EISSN) {
        this.EISSN = EISSN;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getCasaEditrice() {
        return casaEditrice;
    }

    public void setCasaEditrice(String casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public String getValidoDal() {
        return validoDal;
    }

    public void setValidoDal(String validoDal) {
        this.validoDal = validoDal;
    }

    public String getValidoAl() {
        return validoAl;
    }

    public void setValidoAl(String validoAl) {
        this.validoAl = validoAl;
    }

    public String getNatura() {
        return natura;
    }

    public void setNatura(String natura) {
        this.natura = natura;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getCodiceCRIS() {
        return codiceCRIS;
    }

    public void setCodiceCRIS(String codiceCRIS) {
        this.codiceCRIS = codiceCRIS;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public RecordANCERivistaRestDTO() {
        super();
    }
   
}
