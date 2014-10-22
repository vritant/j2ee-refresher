/*
 * @author vritant
 */
package com.vritant.refresher.java.j2ee.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import com.vritant.refresher.java.j2ee.jaxb.Item;
import com.vritant.refresher.java.j2ee.jaxb.ItemReader;
import com.vritant.refresher.java.j2ee.jaxb.Items;

@Path("/items")
public class RestfulImplementation implements RestfulInterface {

    @EJB
    private static Items items = null;

    @Override
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Items getItems() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream iStream = classLoader.getResourceAsStream("resources/vritantItems.xml");) {
            if (items == null) {
                items = new ItemReader().getItems(iStream);
            }
        } catch (JAXBException | IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error in reading file!:" + e.getMessage());
        }
        return items;
    }

    @Override
    @PUT
    @Path("/add/{name}/{value}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Items putItem(@PathParam("name") String name, @PathParam("value") Integer value) {
        getItems();
        Item item = new Item();
        item.setText(name);
        item.setValue(value);
        items.getItems().add(item);
        return items;
    }

    @Override
    @POST
    @Path("/appendAndGet")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Items appendItem(@QueryParam("name") String name, @QueryParam("value") Integer value) {
        getItems();
        if (name != null && !name.isEmpty() && value != null) {
            Item item = new Item();
            item.setText(name);
            item.setValue(value);
            items.getItems().add(item);
        }
        return items;
    }
}
