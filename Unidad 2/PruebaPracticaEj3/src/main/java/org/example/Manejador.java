package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

/**
 * Manejador de elementos XML
 */
public class Manejador extends DefaultHandler {

    ArrayList lista = new ArrayList();
    Videojuego videojuego;
    StringBuilder buffer = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        videojuego = new Videojuego();

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(ch.equals("Activa")) {

        }
    }
}
