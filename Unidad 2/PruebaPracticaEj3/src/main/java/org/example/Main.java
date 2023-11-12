package org.example;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase representa la clase principal.
 */
public class Main {

    /**
     * Método principal
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        File videojuegos = new File("videojuegos.xml");
        Manejador manejador = new Manejador();
        parser.parse(videojuegos, manejador);
        manejador.mostrarVideojuegos();

    }


}