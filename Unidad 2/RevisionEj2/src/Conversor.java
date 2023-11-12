import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta clase representa un conversor de elementos.
 * @author Dani Dom
 */
public class Conversor {


    // Método Conversor de objeto a XML.

    public void convertirObjetoAXML(ArrayList<Jugador> listaJugadores) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream("jugadores.xml"))) {
            encoder.writeObject(listaJugadores);
            System.out.println("Lista de jugadores convertida a XML con éxito.");
        } catch (IOException ex) {
            System.out.println("Error al convertir la lista de jugadores a XML: " + ex.getMessage());
        }
    }

    // Método Conversor de XML a Objeto.
    public void convertirXMLAObjeto(ArrayList<Jugador> listaJugadores) {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("jugadores.xml"))) {
            listaJugadores.clear();
            listaJugadores.addAll((ArrayList<Jugador>) decoder.readObject());
            System.out.println("Lista de jugadores cargada desde XML con éxito.");
        } catch (IOException ex) {
            System.out.println("Error al cargar la lista de jugadores desde XML: " + ex.getMessage());
        }
    }

}
