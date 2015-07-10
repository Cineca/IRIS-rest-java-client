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

import it.cineca.iris.ir.rest.search.model.AnceSearchRestDTO;
import it.cineca.iris.ir.rest.search.model.SearchIdsRestDTO;
import it.cineca.iris.ir.rest.search.model.SearchRestDTO;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * 
 * @author pmeriggi
 *
 */
public class RESTIRClient {

    private WebTarget webTarget;
    private final Client client;
    private final String baseURI;
    private final String pathIR;
    private final String pathRM;

    public RESTIRClient(String baseURI, String pathIR, String pathRM, String username, String password) {
        this.client = javax.ws.rs.client.ClientBuilder.newClient().register(new Authenticator(username, password));
        
        this.baseURI = baseURI;
        this.pathIR = pathIR;
        this.pathRM = pathRM;
    }

    public Response echoIR() {
        this.webTarget = this.client.target(baseURI+pathIR).path("echo");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response echoRM() {
        this.webTarget = this.client.target(baseURI+pathRM).path("echo");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response communities() {
        this.webTarget = this.client.target(baseURI+pathIR).path("communities");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response community(String number) {
        this.webTarget = this.client.target(baseURI+pathIR).path("communities/" + number);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response collections() {
        this.webTarget = this.client.target(baseURI+pathIR).path("collections");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response item(String number) {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/" + number);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response itemAll(String number) {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/" + number).queryParam("expand", "all");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response itemsAll(Integer limit, Integer offset) {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/").queryParam("expand", "all").queryParam("limit", limit).queryParam("offset", offset);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response items(Integer limit, Integer offset) {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/").queryParam("limit", limit).queryParam("offset", offset);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response itemWithMetadata(String number) {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/" + number).queryParam("expand", "metadata");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response inputFormAll(String number) {
        this.webTarget = this.client.target(baseURI+pathIR).path("inputforms/" + number).queryParam("expand", "all");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response items(SearchRestDTO searchDTO) throws IOException {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/search");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonSearchDTO = ow.writeValueAsString(searchDTO);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").post(Entity.entity(jsonSearchDTO, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response itemIds(SearchIdsRestDTO searchDTO) throws IOException {
        this.webTarget = this.client.target(baseURI+pathIR).path("items/ids");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonSearchDTO = ow.writeValueAsString(searchDTO);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").post(Entity.entity(jsonSearchDTO, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response journals(AnceSearchRestDTO searchDTO) throws IOException {
        this.webTarget = this.client.target(baseURI+pathIR).path("ance/search");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonSearchDTO = ow.writeValueAsString(searchDTO);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").post(Entity.entity(jsonSearchDTO, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }

    public Response journal(String number) throws IOException {
        this.webTarget = this.client.target(baseURI+pathIR).path("ance/" + number);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    
    public Response personById(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyid/" + id);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response personByCris(String crisId) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyrpid/" + crisId);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positionsById(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyid/" + id + "/positions");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positionsByCris(String crisId) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyrpid/" + crisId + "/positions");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positioncurrentById(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyid/" + id + "/positioncurrent");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positioncurrentByCris(String crisId) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("personsbyrpid/" + crisId + "/positioncurrent");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response person(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("persons/" + id);

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positions(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("persons/" + id + "/positions");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public Response positioncurrent(String id) throws IOException {
        this.webTarget = this.client.target(baseURI+pathRM).path("persons/" + id + "/positioncurrent");

        Response response = this.webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("scope", "ROLE_ADMIN").get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response;
    }
    
    public void close() {
        this.client.close();
    }
}