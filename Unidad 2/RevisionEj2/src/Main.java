import java.io.IOException;
import java.util.Scanner;

/**
 * Esta clase representa la clase principal
 *
 * @author Dani Dom
 */
public class Main {
    static Scanner teclado = new Scanner((System.in));
    /**
     * Instancia de Scanner para recabar datos.
     */


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
        System.out.println("4. Convertir de XML a OBJ");
        System.out.println("5. Convertir de OBJ a XML");
        System.out.println("0. Salir");
        while (!teclado.hasNextInt()) {
            System.out.println("Elija una opción entre 0 y 6");
            teclado.next();
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }









    /**
     * Método principal
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        // Instancia de objetos.
        Conversor conversor = new Conversor();
        Plantilla listaJugadores = new Plantilla();

        // Valor de la opción elegida.
        int opcion = menu();

        listaJugadores.cargarJugadores("jugadores.obj", listaJugadores);

        while (opcion != 0) {
            switch (opcion) {
                case 1 -> {
                    Jugador nuevo = listaJugadores.crearJugador(teclado);
                    listaJugadores.add(nuevo);
                }

                case 2 -> {
                    for (Jugador jugador : listaJugadores) {
                        listaJugadores.mostrarJugadores(jugador);
                    }
                }
                case 3 -> {
                    Jugador buscado = listaJugadores.buscarJugador(teclado, listaJugadores);
                    if (buscado != null) {
                        System.out.println("Jugador encontrado:");
                        listaJugadores.mostrarJugadores(buscado);
                    } else {
                        System.out.println("Jugador no encontrado.");
                    }
                }
                case 4 -> conversor.convertirXMLAObjeto(listaJugadores);
                case 5 -> conversor.convertirObjetoAXML(listaJugadores);
            }
            opcion = menu();
        }

        // Escribimos el archivo.
        listaJugadores.escribirJugadores("jugadores.obj");

    }
}



