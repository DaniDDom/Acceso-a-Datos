package org.example;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private static int codigo = 1;
    private String nombre;
    private double presupuesto;

    List<Empleado> empleados;

    public Departamento(String nombre, double presupuesto) {
        this.empleados = new ArrayList<Empleado>();
        this.codigo = codigo++;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    public Departamento() {
    }
}
