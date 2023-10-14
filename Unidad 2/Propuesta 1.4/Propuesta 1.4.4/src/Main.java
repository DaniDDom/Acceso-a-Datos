import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner para preguntar por la cadena al usuario.
        Scanner teclado = new Scanner(System.in);
        // Archivo a modificar.
        String archivo = "cadena.txt";
        // Cadena a buscar.
        String cadena;
        // Linea
        String linea;
        // Indicador número linea.
        int contadorLinea = 1;
        // Bandera para devolver si hemos encontrado o no la cadena.
        boolean encontrada = false;

        System.out.println("Introduzca la cadena de texto a buscar");
        cadena = teclado.nextLine().toLowerCase();
        try {
            // Declaramos el lector.
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            // Mientras el archivo siga teniendo lineas las mostramos por pantalla.
            while ((linea = lector.readLine()) != null) {
                if (linea.toLowerCase().contains(cadena + "")) {
                    System.out.println("Linea " + contadorLinea + ": " + linea);
                    encontrada = true;
                }
                contadorLinea++;
            }

            // Cerramos el lector.
            lector.close();

            // En caso de no haber encontrado la cadena.
            if(!encontrada)  {
                System.out.println("No se ha encontrado el texto en el archivo.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no ha sido encontrado. Asegurese de indicar la ruta adecuada.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}