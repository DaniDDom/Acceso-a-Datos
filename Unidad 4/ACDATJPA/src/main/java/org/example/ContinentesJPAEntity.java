package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "Continentes", schema = "public", catalog = "ACDATJPA")
public class ContinentesJPAEntity {
    private int idContinente;
    private String nombre;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "idContinente")
    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idPais) {
        this.idContinente = idPais;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentesJPAEntity that = (ContinentesJPAEntity) o;

        if (idContinente != that.idContinente) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContinente;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

}

