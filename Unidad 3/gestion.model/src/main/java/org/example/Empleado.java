package org.example;

public class Empleado {
    private static int codigo = 1;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;

    Departamento dpto ;

    public Empleado(String nif, String nombre, String apellido1, String apellido2) {
        this.codigo = codigo++;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dpto = new Departamento();
    }

    public Empleado() {
    }
}
