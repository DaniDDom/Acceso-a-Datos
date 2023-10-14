import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Declaración teclado
        Scanner teclado = new Scanner(System.in);
        String nombreImagen = teclado.next();
        byte[] encabezado = new byte[6];

        try {
            FileInputStream archivo = new FileInputStream(nombreImagen);
            archivo.read(encabezado);
                // Formula bytes BMP
            if (encabezado[0] == (byte) 0x42 && encabezado[1] == (byte) 0x4D) {
                System.out.println("Formato detectado BMP.");
                // Formula bytes GIF
            } else if (encabezado[0] == (byte) 0x47 && encabezado[1] == 0x49 && encabezado[2] == (byte) 0x46 && encabezado[3] == (byte) 0x38 && encabezado[5] == (byte) 0x61 && (encabezado[4] == (byte) 0x39 || encabezado[4] == (byte) 0x37)) {
                System.out.println("Formato detecatado: GIF.");
                // Formula bytes JPG
            } else if (encabezado[0] == (byte) 0xFF && encabezado[1] == (byte) 0xD8 && encabezado[2] == (byte) 0xFF) {
                System.out.println("Formato detectado: JPG.");
                // Formula bytes PNG
            } else if (encabezado[0] == (byte) 0x89 && encabezado[1] == (byte) 0x50&& encabezado[2] ==0x4E && encabezado[3]==(byte)0x47) {
                System.out.println("Formato detectado: PNG.");
            } else
                System.out.println("Formato no detectado");
        }  catch (IOException e) {
            System.out.println("No se pudo abrir el archivo: " + nombreImagen+ e.getMessage());;
        }

    }
}