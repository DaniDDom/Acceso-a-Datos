package org.example.entidades;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "empleados")
public class DepartamentosEntidades {
    private int depno;
    private String nombre;
    private String ubicacion;
    private Collection<EmpleadosEntidades> empleadosByDepno;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "depno")
    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ubicacion")
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentosEntidades that = (DepartamentosEntidades) o;
        return depno == that.depno && Objects.equals(nombre, that.nombre) && Objects.equals(ubicacion, that.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depno, nombre, ubicacion);
    }

    @OneToMany(mappedBy = "departamentosByDepno")
    public Collection<EmpleadosEntidades> getEmpleadosByDepno() {
        return empleadosByDepno;
    }

    public void setEmpleadosByDepno(Collection<EmpleadosEntidades> empleadosByDepno) {
        this.empleadosByDepno = empleadosByDepno;
    }
}
