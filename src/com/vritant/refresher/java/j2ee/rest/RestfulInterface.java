/*
 * @author vritant
 */
package com.vritant.refresher.java.j2ee.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vritant.refresher.java.j2ee.jaxb.Items;

public interface RestfulInterface {

    // http://localhost:8080/refresher-j2ee/rest/items/
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Items getItems();

    // curl -X PUT
    // http://localhost:8080/refresher-j2ee/rest/items/add/another/8
    @PUT
    @Path("/add/{name}/{value}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Items putItem(@PathParam("name") String name, @PathParam("value") Integer value);

    // curl -X POST
    // http://localhost:8080/refresher-j2ee/rest/items/appendAndGet?name=names\&value=5
    @POST
    @Path("/appendAndGet")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Items appendItem(@QueryParam("name") String name, @QueryParam("value") Integer value);
}
