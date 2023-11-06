import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase representa la plantilla de jugadores.
 */
public class Plantilla extends ArrayList<Jugador> {


    /**
     * Método para leer el fichero de los jugadores.
     * @param nombreArchivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void leerJugadores(String nombreArchivo) throws IOException, ClassNotFoundException {

        // Lector.
        try (BufferedReader lector = new BufferedReader(new FileReader("jugadores.txt"))) {
        }catch(Exception e) {
            System.out.println("Archivo no encontrado.");
        }
    }

    /**
     * Método para escribir el fichero de los jugadores.
     * @param fileName
     * @throws IOException
     */
    public void escribirJugadores(String fileName) throws IOException {


        // Escritor.
        PrintWriter documentos = null;
        documentos = new PrintWriter(new BufferedWriter(new FileWriter("jugadores.txt",Boolean.TRUE)));
        documentos.write(size());

        // Recorrido de la lista para grabar los nombres.
        for (Jugador j: this) {

            documentos.printf(" Jugador: " + j.toString());
            documentos.println();
        }
        documentos.close();

    }
}


