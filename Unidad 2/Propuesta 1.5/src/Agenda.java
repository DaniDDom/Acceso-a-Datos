import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase representa una agenda
 *
 * @author Dani Dom
 */
public class Agenda extends ArrayList<Contacto> {

    // Método que lee los contactos de la agenda.
    public void leerContactos(String fileName) throws IOException, ClassNotFoundException {
        File archivo = new File(fileName);

        // Comprobamos la existencia del archivo.
        if (archivo.exists()) {
                ObjectInputStream archivos = new ObjectInputStream(
                        new FileInputStream(archivo));
                int numObjetos = archivos.readInt();
                for (; numObjetos > 0; numObjetos--) {
                    add((Contacto) archivos.readObject());
                }
                archivos.close();
        }
    }

    public void escribeContactos(String fileName) throws IOException {

            ObjectOutputStream archivos = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            archivos.writeInt(size());
            for (Contacto c : this) {
                archivos.writeObject(c);
            }
            archivos.close();

    }
}
