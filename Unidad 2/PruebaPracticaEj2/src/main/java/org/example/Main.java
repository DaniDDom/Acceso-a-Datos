package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Esta clase representa la clase principal.
 * @author Dani Dom
 */

public class Main {

    /**
     * Método principal de la clase.
     * @param args
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        // Array donde guardaremos los jugadores.
        ArrayList<Jugador> plantilla = new ArrayList<>();

        // Contexto de donde sacaremos la información.

        JAXBContext contexto = JAXBContext.newInstance(Jugador.class);

        // Instanciamos el escritor.
        Marshaller escritor = contexto.createMarshaller();

        // Rellenamos la plantilla.
        plantilla = (ArrayList<Jugador>) contexto.createUnmarshaller().unmarshal(new FileReader("jugadores.obj"));

        // Damos formato de salida.
        escritor.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Guardamos.
        escritor.marshal(plantilla,new File("jugadores.xml"));



    }
}