import java.io.IOException;
import java.util.Scanner;

/**
 * Esta clase representa el programa principal
 *
 * @author Dani Dom
 */
public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static Agenda listaContactos;
    private static final String fileName = "contactos.obj";

    // Menu basico.
    public static int menu() {


        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1. - Añadir contacto.");
        System.out.println("2. - Mostrar lista.");
        System.out.println("3. - Buscar contacto.");
        System.out.println("0. - Terminar.");
        while (!teclado.hasNextInt())
        {
            System.out.println("Elija una opción entre 0 y 3: ");
            teclado.next();
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;

    }


    //Solicitamos datos para la creación del contacto
    public static Contacto crearContacto() {

        // Contacto donde guardaremos los datos.
        Contacto contacto = new Contacto();

        System.out.print("Nombre: ");
        contacto.setNombre(teclado.nextLine());
        System.out.print("Apellidos: ");
        contacto.setApellidos(teclado.nextLine());
        System.out.print("Teléfono: ");
        contacto.setTelefono(teclado.nextLine());
        System.out.print("E-mail: ");
        contacto.setEmail(teclado.nextLine());
        System.out.print("Descripción: ");
        contacto.setDescripcion(teclado.nextLine());
        System.out.println();

        return contacto;

    }

    // Muestra los datos del contacto.
    public static void mostrarContactos(Contacto contacto) throws InterruptedException {
        System.out.println("Nombre: " + contacto.getNombre());
        Thread.sleep(500);
        System.out.println("Apellidos: " + contacto.getApellidos());
        Thread.sleep(500);
        System.out.println("Teléfono: " + contacto.getTelefono());
        Thread.sleep(500);
        System.out.println("E-mail: " + contacto.getEmail());
        Thread.sleep(500);
        System.out.println("Descripción: " + contacto.getDescripcion());
        Thread.sleep(500);
        System.out.println();
    }

    public static Contacto busquedaContacto() {
        String opcionBusqueda;
        do {
            System.out.println("Indique si desea buscar por (N)ombre o (T)eléfono: ");
            opcionBusqueda = teclado.next().toLowerCase();
        } while (!opcionBusqueda.equals("n") && !opcionBusqueda.equals("t"));
        System.out.println("Dato a buscar: ");
        String criterioBusqueda = teclado.next();
        for (Contacto c : listaContactos) {
            String busqueda = opcionBusqueda.equals(("n")) ? c.getNombre() + c.getApellidos() : c.getTelefono();
            if (busqueda.contains(criterioBusqueda)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        listaContactos = new Agenda();
        listaContactos.leerContactos(fileName);
        int opcion = menu();
        while (opcion != 0) {
            switch (opcion) {
                case 1 -> listaContactos.add(crearContacto());
                case 2 -> {
                    for (Contacto contacto : listaContactos) {
                        mostrarContactos(contacto);
                    }
                }
                case 3 -> {
                    Contacto c = busquedaContacto();
                    if (c != null)
                        mostrarContactos(c);
                    else
                        System.out.println("Contacto no encontrado.");
                }
            }
            opcion = menu();
        }
        listaContactos.escribeContactos(fileName);
    }
}