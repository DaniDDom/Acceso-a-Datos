package org.example;

/**
 * Esta clase representa un videojuego.
 */

public class Videojuego {
    private String titulo;
    private String semilla;
    private String estado;
    private String tipo_de_recoleccion;
    private String Frecuencia;
    private String Profundidad;
    private String Tamanyo;
    private String palabras_clave;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSemilla() {
        return semilla;
    }

    public void setSemilla(String semilla) {
        this.semilla = semilla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_de_recoleccion() {
        return tipo_de_recoleccion;
    }

    public void setTipo_de_recoleccion(String tipo_de_recoleccion) {
        this.tipo_de_recoleccion = tipo_de_recoleccion;
    }

    public String getFrecuencia() {
        return Frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        Frecuencia = frecuencia;
    }

    public String getProfundidad() {
        return Profundidad;
    }

    public void setProfundidad(String profundidad) {
        Profundidad = profundidad;
    }

    public String getTamanyo() {
        return Tamanyo;
    }

    public void setTamanyo(String tamanyo) {
        Tamanyo = tamanyo;
    }

    public String getPalabras_clave() {
        return palabras_clave;
    }

    public void setPalabras_clave(String palabras_clave) {
        this.palabras_clave = palabras_clave;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    private String materia;

}
