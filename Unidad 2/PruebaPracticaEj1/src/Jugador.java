import java.io.Serializable;

/**
 * Esta clase representa un jugador.
 * @author Dani Dom
 */
public class Jugador implements Serializable {

    /** Nombre del jugador */
    private String nombre;
    /** Apodo del jugador */
    private String apodo;

    /** Puesto del jugador */
    private String puesto;
    /** Dorsal del jugador */
    private String dorsal;
    /** Descripción del jugador */
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Jugador(String nombre, String apodo, String puesto, String dorsal, String descripcion) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.puesto = puesto;
        this.dorsal = dorsal;
        this.descripcion = descripcion;
    }

    public Jugador() {
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", puesto='" + puesto + '\'' +
                ", dorsal=" + dorsal +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
