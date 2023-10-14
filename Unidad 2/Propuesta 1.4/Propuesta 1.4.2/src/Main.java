import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dani Dom
 */
public class Main {
    /**
     * Esta clase representa la principal del programa.
     * @param args
     */
    public static void main(String[] args) {

        // Los dos ficheros previamente ordenados, sustituir el nombre por los que se quieran comparar
        String fich1 = "fich1.txt";
        String fich2 = "fich2.txt";

        // Archivo de salida
        String fich3 = "combinado.txt";


        // Listas donde iran las palabras.
        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();
        List<String> listaCombinada = new ArrayList<>();

        // String para las lineas.
        String linea;

        // Contadores
        int contador1 = 0;
        int contador2 = 0;


        try {

            // Lectores, 1 para cada fichero.
            BufferedReader lector1 = new BufferedReader(new FileReader(fich1));
            BufferedReader lector2 = new BufferedReader(new FileReader(fich2));

            // Escritor para el archivo combinado.
            BufferedWriter escritor = new BufferedWriter(new FileWriter(fich3));


            // Añadimos a la lista las palabras, usando el espacio en blanco como separador.
            while ((linea = lector1.readLine()) != null) {
                Collections.addAll(lista1, linea.split(" "));
            }
            // Añadimos a la segunda lista las palabras, usando el espacio en blanco como separador.
            while ((linea = lector2.readLine()) != null) {
                Collections.addAll(lista2, linea.split(" "));
            }

            // Ordenamos en caso de que los archivos no lo estuvieran.
            Collections.sort(lista1);
            Collections.sort(lista2);

            // Mientras que ambas listas contengan palabras, se compararan.
            while (contador1 < lista1.size() && contador2 < lista2.size()) {

                // En caso de que la palabra de la lista 1 vaya delante, se añadira y aumentara el contador1.
                if (lista1.get(contador1).compareTo(lista2.get(contador2)) < 0) {
                    listaCombinada.add(lista1.get(contador1));
                    contador1++;
                    // En caso contrario se añadira a la lista 2 y el contador 2 sumara.
                } else {
                    listaCombinada.add(lista2.get(contador2));
                    contador2++;
                }
            }

            // En caso de que la lista 1 aun tenga miembros.
            while(contador1 < lista1.size()) {
                listaCombinada.add(lista1.get(contador1));
                contador1++;
            }

            // En caso de que la lista 2 aun tenga miembros.
            while(contador2 < lista2.size()) {
                listaCombinada.add(lista2.get(contador2));
                contador2++;
            }


            // Escribimos las palabras ordenadas en el archivo combinado de salida.
            for (String palabra: listaCombinada) {
                escritor.write(palabra + " ");
            }

            // Cerramos lectores y el escritor
            lector1.close();
            lector2.close();
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}