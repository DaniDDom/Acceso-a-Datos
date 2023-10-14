import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner
        Scanner teclado = new Scanner(System.in);
        // Archivo a leer
        String archivo = "archivo.txt";

        // Linea para mostrar por pantalla
        String linea;

        // Contador de lineas
        int contador = 0;

        // Lector
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            // Mientras tenga lineas que las pase por pantalla y aumente contador.
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
                contador++;


                // Detenerse cada 23 lineas.

                if (contador % 23 == 0) {
                    System.out.println("Presione alguna tecla para continuar...");
                    teclado.nextLine();
                }
            }

            //Cierre de lector.
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}