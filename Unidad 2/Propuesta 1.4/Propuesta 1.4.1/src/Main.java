import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Declaración Scanner
        Scanner teclado = new Scanner(System.in);

        // Archivo que generaremos.
        String fichero = "fichero.txt";

        // Declaración del objeto para escribir.
        PrintWriter escritor = null;

        // Bandera para marcar la sobreescritura.
        boolean mantiene = false;

        // Conteo nº lineas, uso del long para evitar casteo en el metodo lines.
        long numLineas = 1;

        if (new File(fichero).exists()) {
            System.out.println("¿Deseas sobreescribir el archivo?: (S) Para confirmar, (N) para denegar.");

            String respuesta = teclado.nextLine();

            while ((!respuesta.toUpperCase().equals("S")) && (!respuesta.toUpperCase().equals("N"))) {
                System.out.println("Responda 'S' o 'N'");
                respuesta = teclado.nextLine();
            }
            if (respuesta.toUpperCase().equals("N")) {
                mantiene = true;
                try {
                    numLineas = Files.lines(Paths.get(fichero)).count() + 1;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            escritor = new PrintWriter(new BufferedWriter(new FileWriter(fichero,mantiene)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Introduzca la oración que desea añador, pulse enter cuando haya acabado: ");
        String oracion = teclado.nextLine();
        while(!oracion.isBlank()) {
            escritor.printf("%d %s", numLineas++,oracion);
            escritor.println();
            oracion = teclado.nextLine();
        }
        System.out.println("Oraciones guardadas correctamente.");
        if (escritor != null) {
            escritor.close();
        }
    }

}