package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Manejador de elementos XML
 */
public class Manejador extends DefaultHandler {

    private ArrayList<Videojuego> lista = new ArrayList();

    private Videojuego juego;

    private Boolean activa;

    private StringBuilder buffer = new StringBuilder();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


        switch (qName) {
            case "Titulo" -> {
                buffer.delete(0, buffer.length());
                juego = new Videojuego();
                lista.add(juego);
            }
            case "Semilla" -> buffer.delete(0, buffer.length());
            case "Palabras_clave" -> buffer.delete(0, buffer.length());
            case "Estado" -> buffer.delete(0,buffer.length());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Titulo" -> juego.setTitulo(buffer.toString());
            case "Semilla" -> juego.setSemilla(buffer.toString());
            case "Estado" -> {
                if (buffer.toString().equals("Activa")) {
                    juego.setEstado(Boolean.TRUE);
                } else {
                    juego.setEstado(Boolean.FALSE);
                }
            }
            case "Palabras_clave" -> juego.setPalabras_clave(buffer.toString());

        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }
    public void mostrarVideojuegos() {
        for (Videojuego juego:lista) {
            if(juego.getEstado())
                System.out.println(juego.toString());
        }
    }

}


