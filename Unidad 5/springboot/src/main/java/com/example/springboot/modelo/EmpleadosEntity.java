package com.example.springboot.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "public", catalog = "empleados")
public class EmpleadosEntity {
    private int empno;
    private String nombre;
    private String puesto;
    private Integer depno;
    private DepartamentosEntity departamentosByDepno;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "empno")
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
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
    @Column(name = "puesto")
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Basic
    @Column(name = "depno")
    public Integer getDepno() {
        return depno;
    }

    public void setDepno(Integer depno) {
        this.depno = depno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadosEntity that = (EmpleadosEntity) o;
        return empno == that.empno && Objects.equals(nombre, that.nombre) && Objects.equals(puesto, that.puesto) && Objects.equals(depno, that.depno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, nombre, puesto, depno);
    }

    @ManyToOne
    @JoinColumn(name = "depno", referencedColumnName = "depno", insertable = false, updatable = false)
    public DepartamentosEntity getDepartamentosByDepno() {
        return departamentosByDepno;
    }

    public void setDepartamentosByDepno(DepartamentosEntity departamentosByDepno) {
        this.departamentosByDepno = departamentosByDepno;
    }
}
