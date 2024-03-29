import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa la plantilla de jugadores.
 */
public class Plantilla extends ArrayList<Jugador> {


    /**
     * Método para leer el fichero de los jugadores.
     *
     * @param nombreArchivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void cargarJugadores(String nombreArchivo) {


        try {
            File archivo = new File("jugadores.txt");
            if (archivo.exists()) {
                BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] parametros = linea.split(";");
                    if (parametros.length == 5) {
                        Jugador jugador = new Jugador(parametros[0], parametros[1], parametros[2], parametros[3], parametros[4]);
                        add(jugador);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar la lista de jugadores, el archivo no existe.");
        }
    }


    /**
     * Método para escribir el fichero de los jugadores.
     *
     * @param nombreArchivo
     * @throws IOException
     */
    public void escribirJugadores(String nombreArchivo) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Jugador jugador : this) {
                String line = jugador.getNombre() + "; " + jugador.getApodo() + "; " +
                        jugador.getPuesto() + "; " + jugador.getDorsal() + "; " + jugador.getDescripcion();
                writer.write(line);
                writer.newLine();
            }

        }
    }

    public Jugador crearJugador(Scanner teclado) {

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



