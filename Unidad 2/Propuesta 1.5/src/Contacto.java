import java.io.Serializable;

/**
 * Esta clase representa un contacto de la agenda.
 * @author Dani Dom
 */
public class Contacto implements Serializable {

    /** Nombre del contacto */
    private String nombre ;
    /** Apellidos del contacto */
    private String apellidos;
    /** Teléfono de contacto */
    private String telefono;
    /** E-mail de contacto */
    private String email;
    /** Descripción corta del contacto */
    private String descripcion;

    /** Constructor por defecto */
    public Contacto(){}

    /** Constructor con atributos */
    public Contacto(String nombre, String apellidos, String telefono, String email, String descripcion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
