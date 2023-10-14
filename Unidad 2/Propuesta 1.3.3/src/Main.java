
import java.io.*;

public class Main {
    private static int calcularInt(int inicio, int fin , byte[] encabezado, int potencia) {
        int valor = 0;
        for (int i = inicio; i < fin; i++) {
            valor += (encabezado[i] & 0xFF) * potencia;
            potencia *= 256;
        }
        return valor;
    }
    public static void main(String[] args) {

        FileInputStream archivo;
        try {
            archivo = new FileInputStream("Splash.bmp");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] encabezado = new byte[54];
        int ancho = 0;
        int alto = 0;
        int size = 0;
        int potencia = 1;
        boolean comprimido = false;

        try {
            archivo.read(encabezado, 0, 54);
            archivo.close();
        } catch (IOException e) {
            System.out.println("El archivo no existe");
        }


        size = calcularInt(2,6, encabezado, potencia);
        ancho = calcularInt(18,22,encabezado,potencia);
        alto = calcularInt(22,26,encabezado,potencia);

        System.out.println("El tamaño del archivo es: " + size);
        System.out.println("El ancho del archivo es: " + ancho);
        System.out.println("La altura del archivo es: " + alto);

    }


}