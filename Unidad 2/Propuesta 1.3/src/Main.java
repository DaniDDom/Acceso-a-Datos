import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Declaramos el lector
        FileInputStream lector = null;
        // Declaramos el escritor
        FileOutputStream escritor = null;
        try{
            lector = new FileInputStream("entrada.dat");
            escritor = new FileOutputStream("salida.dat");
            int i = 0;
            while((i = lector.read()) != -1) {
                escritor.write(new byte[]{(byte) i},i,128);
                i += 128;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");;
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");;
        }
    }
    }
