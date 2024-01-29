package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "Paises", schema = "public", catalog = "ACDATJPA")
public class PaisesJPAEntity {
    private int idPais;
    private String nombre;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPais")
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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

        PaisesJPAEntity that = (PaisesJPAEntity) o;

        if (idPais != that.idPais) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPais;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
