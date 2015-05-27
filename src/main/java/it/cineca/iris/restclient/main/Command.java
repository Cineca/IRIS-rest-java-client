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
package it.cineca.iris.restclient.main;

import it.cineca.iris.ir.rest.model.CommunityRestDTO;
import it.cineca.iris.ir.rest.model.CommunityRestPageDTO;
import it.cineca.iris.ir.rest.model.DCInputSetRestDTO;
import it.cineca.iris.ir.rest.model.ItemRestDTO;
import it.cineca.iris.ir.rest.model.ItemRestPageDTO;
import it.cineca.iris.ir.rest.model.RecordANCERivistaRestDTO;
import it.cineca.iris.ir.rest.model.RmPersonRestDTO;
import it.cineca.iris.ir.rest.search.model.AnceSearchRestDTO;
import it.cineca.iris.ir.rest.search.model.RestSearchCriteria;
import it.cineca.iris.ir.rest.search.model.RestSortCriteria;
import it.cineca.iris.ir.rest.search.model.SearchRestDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author pmeriggi
 *
 */
public class Command {
	
	
    
    public static void main(String[] argv) throws IOException, NoSuchAlgorithmException, KeyManagementException {
    	PropertiesReader reader = new PropertiesReader();
    	Properties prop = reader.getProperties();
    	
    	String restBaseURI = prop.getProperty("BASE_URI"),
    			pathIR = prop.getProperty("PATH_IR"),
    			pathRM = prop.getProperty("PATH_RM"),
    			username = prop.getProperty("USERNAME"),
    			password = prop.getProperty("PASSWORD");
    	
        if (username == null || password == null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            if (username == null) {
            	System.out.print("Enter Username:");
            	username = br.readLine();
            }
            
            if (password == null) {
            	System.out.print("Enter Password:");
                password = br.readLine();
            }
        }
        
        RESTIRClient cl = new RESTIRClient(restBaseURI,pathIR,pathRM,username,password);
        Response response = cl.echo();
        
        String test = response.readEntity(String.class);

        System.out.println("Check: "+test);
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Read Communities");
        response = cl.communities();
        test = response.readEntity(String.class);
        System.out.println("Community JSON: "+test);
        
        ObjectMapper mapper = new ObjectMapper();
   
        CommunityRestPageDTO communities = mapper.readValue(test, CommunityRestPageDTO.class);

        System.out.println("Community handle: "+communities.getRestResourseDTOList().get(0).getHandle());
        System.out.println("Community name: "+communities.getRestResourseDTOList().get(0).getName());
        System.out.println("Community id: "+communities.getRestResourseDTOList().get(0).getId());
        System.out.println("Community next: "+communities.getNext());

        System.out.println("Read Community: 1");
        response = cl.community("1");
        test = response.readEntity(String.class);
        System.out.println("Community JSON: "+test);
   
        CommunityRestDTO community = mapper.readValue(test, CommunityRestDTO.class);

        System.out.println("Community handle: "+community.getHandle());
        System.out.println("Community name: "+community.getName());
        System.out.println("Community id: "+community.getId());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Read Item: 65523");
        response = cl.itemAll("65523");
        test = response.readEntity(String.class);
        System.out.println("Item JSON: "+test);
        
        ItemRestDTO item = mapper.readValue(test, ItemRestDTO.class);
        System.out.println("Item Handle: "+item.getHandle());
        System.out.println("Item last modify date: "+item.getLastModified());
        System.out.println("Collection Id: "+item.getCollection().getId());
        System.out.println("ISSN: " + item.getLookupValues().get("jissn"));
        
        response = cl.itemWithMetadata("328325");
        test = response.readEntity(String.class);
        System.out.println("Item + metadata JSON: "+test);
        
        item = mapper.readValue(test, ItemRestDTO.class);
        System.out.println("Item handle: "+item.getHandle());
        System.out.println("Item title: "+item.getMetadata().get("dc.title").get(0).getValue());
        
        response = cl.item("328325");
        test = response.readEntity(String.class);
        System.out.println("Item JSON: "+test);
        
        item = mapper.readValue(test, ItemRestDTO.class);
        System.out.println("Item Handle: "+item.getHandle());
        
        response = cl.itemWithMetadata("328325");
        test = response.readEntity(String.class);
        System.out.println("Item + metadata JSON: "+test);
        
        item = mapper.readValue(test, ItemRestDTO.class);
        System.out.println("Item handle: "+ item.getHandle());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Search Item");
        SearchRestDTO itemSearchDTO = new SearchRestDTO();
        itemSearchDTO.setOffset(4);
        itemSearchDTO.setLimit(2);
        itemSearchDTO.setExpand("");
        
        RestSearchCriteria searchCriteriaLM = new RestSearchCriteria();
        searchCriteriaLM.setColumn("last_modified");
        searchCriteriaLM.setOperation(">");
        searchCriteriaLM.setValue("21/04/2015");
        
        RestSearchCriteria searchCriteriaSnap = new RestSearchCriteria();
        searchCriteriaSnap.setColumn("snapshot");
        searchCriteriaSnap.setOperation("=");
        searchCriteriaSnap.setValue("0");
        
        ArrayList<RestSearchCriteria> searchList = new ArrayList<>();
        searchList.add(searchCriteriaLM);
        searchList.add(searchCriteriaSnap);
        itemSearchDTO.setSearchColsCriteria(searchList);
        
        RestSortCriteria sortCriteria = new RestSortCriteria();
        sortCriteria.setAsc(true);
        sortCriteria.setColumn("last_modified");
        
        ArrayList<RestSortCriteria> sortList = new ArrayList<>();
        sortList.add(sortCriteria);
        itemSearchDTO.setSortingColsCriteria(sortList);
        
        response = cl.items(itemSearchDTO);
        test = response.readEntity(String.class);
        ItemRestPageDTO items = mapper.readValue(test, ItemRestPageDTO.class);
        System.out.println("id:" + items.getRestResourseDTOList().get(0).getId());
        System.out.println("next:" + items.getNext());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Inputform: 1");
        response = cl.inputFormAll("1");
        test = response.readEntity(String.class);
        System.out.println(test);
        DCInputSetRestDTO inputform = mapper.readValue(test, DCInputSetRestDTO.class);
        System.out.println("Input form name:"+inputform.getFormName());
        System.out.println("Elemento con dropdown-> Pagina: "+inputform.getRows().get(2).getPageNumber());
        System.out.println("Elemento con dropdown-> qualificatore: "+inputform.getRows().get(2).getDcQualifier());
        System.out.println("Elemento con dropdown-> nome: "+inputform.getRows().get(2).getDcElement());
        System.out.println("Elemento con dropdown-> tipo input: "+inputform.getRows().get(2).getInputType());
        System.out.println("Elemento con dropdown-> nome elenco valori sulla value pair: "+inputform.getRows().get(2).getValueListName());
        System.out.println("------------------------------------------------------------");
        System.out.println("Valuepair-> "+inputform.getValuePairs().get(0).getName());
        System.out.println("Valuepair-> Valore su db 1: "+inputform.getValuePairs().get(0).getValuePairs().get(0).getKey());
        System.out.println("Valuepair-> Valore visualizzato all'utente 1: "+inputform.getValuePairs().get(0).getValuePairs().get(0).getValue());
        System.out.println("Valuepair-> Valore su db 2:  "+inputform.getValuePairs().get(0).getValuePairs().get(1).getKey());
        System.out.println("Valuepair-> Valore visualizzato all'utente 2: "+inputform.getValuePairs().get(0).getValuePairs().get(1).getValue());

        System.out.println("-----------------------------------------------------------");
        System.out.println("Ance search: journal76139");
        AnceSearchRestDTO anceSearchDTO = new AnceSearchRestDTO();
        anceSearchDTO.setCrisId("journal76139");
        response = cl.journals(anceSearchDTO);
        test = response.readEntity(String.class);
        System.out.println("id:" + test);
        List<RecordANCERivistaRestDTO> journals = mapper.readValue(test, new TypeReference<List<RecordANCERivistaRestDTO>>(){});
        System.out.println("id:" + journals.get(0).getISSN());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Ance by cris id: journal76139");
        response = cl.journal("journal76139");
        test = response.readEntity(String.class);
        System.out.println("id:" + test);
        RecordANCERivistaRestDTO journal = mapper.readValue(test,RecordANCERivistaRestDTO.class);
        System.out.println("id:" + journal.getISSN());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("Author by cris id: rp11042");
        response = cl.person("rp11042");
        test = response.readEntity(String.class);
        System.out.println("Person JSON:" + test);
        RmPersonRestDTO person = mapper.readValue(test, RmPersonRestDTO.class);
        System.out.println(person.lastName);
       
        cl.close();
    }
}
