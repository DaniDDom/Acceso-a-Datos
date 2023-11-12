import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa la plantilla de jugadores.
 * @author Dani Dom
 */
public class Plantilla extends ArrayList<Jugador> {


    /**
     * Método para leer el fichero de los jugadores.
     *
     * @param nombreArchivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void cargarJugadores(String nombreArchivo, ArrayList<Jugador> plantilla) {



            File archivo = new File("jugadores.obj");
            if (archivo.exists()) {
                try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("jugadores.obj"))) {
                    plantilla = (ArrayList<Jugador>) entrada.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    archivo.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    /**
     * Método para escribir el fichero de los jugadores.
     *
     * @param nombreArchivo
     * @throws IOException
     */
    public void escribirJugadores(String nombreArchivo) throws IOException {

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(this);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método para crear un jugador y nos devuelve ese objeto.
     *
     * @return
     */
    public Jugador crearJugador(Scanner teclado) {

        // Creación de nuevo jugador.
        Jugador jugador = new Jugador();

        // Entrada de parametros.
        System.out.println("Introduzca el nombre del jugador: ");
        jugador.setNombre(teclado.nextLine());
        System.out.println("Introduzca el apodo del jugador: ");
        jugador.setApodo(teclado.nextLine());
        System.out.println("Introduzca el puesto del jugador: ");
        jugador.setPuesto(teclado.nextLine());
        System.out.println("Introduzca el dorsal del jugador: ");
        jugador.setDorsal(teclado.nextLine());
        System.out.println("Introduzca la descripción del jugador: ");
        jugador.setDescripcion(teclado.nextLine());
        return jugador;
    }


    /**
     * Método para mostrar jugadores.
     *
     * @throws InterruptedException
     */
    public static void mostrarJugadores(Jugador jugador) throws InterruptedException {


        System.out.println("Nombre del jugador: " + jugador.getNombre());
        Thread.sleep(1000);
        System.out.println("Apodo del jugador: " + jugador.getApodo());
        Thread.sleep(1000);
        System.out.println("Puesto del jugador: " + jugador.getPuesto());
        Thread.sleep(1000);
        System.out.println("Dorsal del jugador: " + jugador.getDorsal());
        Thread.sleep(1000);
        System.out.println("Descripción del jugador: " + jugador.getDescripcion());
        Thread.sleep(1000);
    }

    /**
     * Método para buscar jugadores.
     *
     * @return
     */
    public static Jugador buscarJugador(Scanner teclado, ArrayList<Jugador> plantilla) {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Apodo: ");
        String apodo = teclado.nextLine();
        System.out.print("Dorsal: ");
        String dorsal = teclado.nextLine();
        System.out.println();

        for (Jugador j : plantilla) {
            if (j.getNombre().contains(nombre) && j.getApodo().contains(apodo) && j.getDorsal().contains(dorsal)) {
                return j;
            }
        }
        return null;
    }

}



