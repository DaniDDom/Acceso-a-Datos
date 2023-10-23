import org.w3c.dom.ls.LSOutput;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Esta clase representa la clase principal
 *
 * @author Dani Dom
 */
public class Main {

    /**
     * Instancia de Scanner para recabar datos.
     */
    private static Scanner teclado = new Scanner((System.in));

    private static Plantilla listaJugadores = new Plantilla();

    private static String nombreArchivo = "jugadores.txt";

    /**
     * Menu básico que nos devuelve una opción elegida.
     *
     * @return
     */
    private static int menu() {
        System.out.println("MENU");
        System.out.println("=========");
        System.out.println("1. Añadir jugador.");
        System.out.println("2. Mostrar lista jugadores.");
        System.out.println("3. Buscar jugador:");
        System.out.println("0. Salir");
        while (!teclado.hasNextInt()) {
            System.out.println("Elija una opción entre 0 y 3");
            teclado.next();
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    /**
     * Método para crear un jugador y nos devuelve ese objeto.
     *
     * @return
     */
    public static Jugador crearJugador() {

        // Creación de nuevo jugador.
        Jugador jugador = new Jugador();

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
     * @param jugador
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
    public static Jugador busquedaJugador() {

        // Criterio de busqueda de nombre.
        String busquedaNombre;
        // Criterio de busqueda de Apodo.
        String busquedaApodo;
        // Criterio de busqueda de Dorsal.
        String busquedaDorsal;

        System.out.println("Introduzca el nombre a buscar: ");
        busquedaNombre = teclado.nextLine();
        System.out.println("Introduzca el apodo a buscar: ");
        busquedaApodo = teclado.nextLine();
        System.out.println("Introduzca el dorsal a buscar: ");
        busquedaDorsal = teclado.nextLine();


        // Recorrido de la lista para comprobar según los criterios.
        for (Jugador j : listaJugadores) {
            String busqueda = j.getNombre() + j.getApodo() + j.getDorsal();
            if (busqueda.contains(busquedaNombre) && busqueda.contains(busquedaApodo) && busqueda.contains(busquedaDorsal)) {
                return j;
            }
        }
        return null;
    }


    /**
     *
     * Método principal
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // Leemos el archivo.
        listaJugadores.leerJugadores(nombreArchivo);

        // Valor de la opción elegida.
        int opcion = menu();

        // Bucle del menu.
        while (opcion != 0) {
            switch (opcion) {
                case 1 -> listaJugadores.add(crearJugador());
                case 2 -> {

                    for (Jugador j : listaJugadores) {
                        mostrarJugadores(j);
                    }
                }
                case 3 -> {
                    Jugador j = new Jugador();
                    j = busquedaJugador();

                    if (j != null) {
                        System.out.println("Jugador encontrado.");
                        mostrarJugadores(j);
                    } else {
                        System.out.println("No existe jugador con ese criterio de busqueda.");
                    }
                }
            }
            opcion = menu();
        }

        // Escritura del archivo
        listaJugadores.escribirJugadores(nombreArchivo);

        ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("jugadores.obj"));
    }
}
