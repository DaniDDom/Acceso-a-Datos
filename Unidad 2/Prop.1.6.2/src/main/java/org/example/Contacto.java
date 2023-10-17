package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Esta clase representa un contacto de la agenda.
 * @author Dani Dom
 */
@XmlRootElement
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

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    @XmlElement
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
