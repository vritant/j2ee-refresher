/*
 * @author vritant
 */
package com.vritant.refresher.java.j2ee.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ItemReader {

    public Items getItems(InputStream in) throws JAXBException {
        Items items = null;
        JAXBContext context;
        context = JAXBContext.newInstance(Items.class);
        Unmarshaller um = context.createUnmarshaller();
        items = (Items) um.unmarshal(in);
        return items;
    }

    public static void addItems(OutputStream out, Items items) throws JAXBException {

        JAXBContext context;
        context = JAXBContext.newInstance(Items.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(items, System.out);
        m.marshal(items, out);
    }

    // helps test marshalling JAXB and writes source resource too!!
    public static void main(String[] args) {

        try {
            Items items = new Items();
            items.setOwner("Vritant");
            List<Item> myItems = new ArrayList<Item>();

            Item item1 = new Item();
            item1.setText("item1");
            item1.setValue(3);
            myItems.add(item1);
            Item item2 = new Item();
            item2.setText("item2");
            item2.setValue(5);
            myItems.add(item2);
            items.setItems(myItems);

            OutputStream os = new FileOutputStream(new File("src/resources/vritantItems.xml"));
            addItems(os, items);

        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
