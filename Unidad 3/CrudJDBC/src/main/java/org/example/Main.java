package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);

    private static int menu() {

        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1. - Crear empleado/Departamento");
        System.out.println("2. - Mostrar empleado/Departamento");
        System.out.println("3. - Modificar empleado/Departamento");
        System.out.println("4. - Eliminar empleado/Departamento");
        System.out.println("0. - Salir");
        System.out.println();

        int opcion = -1;

        try {
            System.out.print("Elige una opción entre 0 y 4: ");
            opcion = teclado.nextInt();

            if (opcion < 0 || opcion > 4) {
                System.out.println("Opción no válida. Debe ser un número entre 0 y 4.");
                opcion = -1;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, introduce un número.");
        } finally {
            teclado.nextLine();
        }

        return opcion;
    }

    private static int comprobar(miBD miBD, String tabla, String tipoNumero, int depNumero) throws SQLException {
        int conteo = 0;
        Connection con = miBD.conectar();
        PreparedStatement comprobar = con.prepareStatement("SELECT COUNT(*) FROM " + tabla + " WHERE " + tipoNumero + " = ?");
        comprobar.setInt(1, depNumero);
        try (ResultSet resultSet = comprobar.executeQuery()) {
            if (resultSet.next()) {
                conteo = resultSet.getInt(1);
            }
        }
        return conteo;
    }

    private static void insertarDatos(miBD miBD, String tabla) throws SQLException {
        int numero = 0;
        int conteo = 0;
        String usuario;
        String tipoNumero;
        if (tabla.equalsIgnoreCase("departamentos")) {
            usuario = "departamento";
            tipoNumero = "depno";
        } else {
            usuario = "empleado";
            tipoNumero = "empno";
        }
        do {
            try {
                System.out.println("Inserte el número de " + usuario + " a asignar:");
                numero = teclado.nextInt();
                conteo = comprobar(miBD, tabla, tipoNumero, numero);

                if (conteo != 0) {
                    System.out.println("El número de " + usuario + " ya existe. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un valor numérico válido.");
                teclado.nextLine();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (conteo != 0);
        teclado.nextLine();
        System.out.println("Ingrese el nombre del " + usuario);
        String departamento = teclado.nextLine();

        if (tabla.equalsIgnoreCase("departamentos")) {
            System.out.println("Ingrese la ubicación del departamento.");
        } else {
            System.out.println("Ingrese el puesto del empleado");
        }
        String ubicacion = teclado.nextLine();
        miBD.insertar(tabla, numero, departamento, ubicacion);
    }

    private static void actualizarDepartamento(miBD miBD, int numeroDepartamento, String nuevoNombre, String nuevaUbicacion) throws SQLException {
        Connection con = miBD.conectar();
        String sql = "UPDATE departamentos SET nombre = ?, ubicacion = ? WHERE depno = ?";

        try (PreparedStatement updateStatement = con.prepareStatement(sql)) {

            updateStatement.setString(1, nuevoNombre);
            updateStatement.setString(2, nuevaUbicacion);
            updateStatement.setInt(3, numeroDepartamento);
            updateStatement.executeUpdate();
        }
        con.close();
    }

    public static void main(String[] args) throws SQLException {

        miBD miBD = new miBD("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/Empleados", "postgres", "iesbelen");
        int option = menu();
        while (option != 0) {
            switch (option) {
                case 1 -> {
                    String tabla = "";
                    do {
                        System.out.println("¿Qué desea insertar, un empleado(E) o un departamento(D)");
                        tabla = teclado.nextLine();
                    } while (!tabla.equalsIgnoreCase("D") && !tabla.equalsIgnoreCase("E"));
                    if ("D".equalsIgnoreCase(tabla)) {
                        insertarDatos(miBD, "departamentos");
                    } else if ("E".equalsIgnoreCase(tabla)) {
                        insertarDatos(miBD, "empleados");
                    }
                }
                case 2 -> {
                    String tabla = "";
                    do {
                        System.out.println("¿Qué desea consultar, un empleado(E), un departamento(D), todos los empleados(TE) o todos los departamentos(TD)?");
                        tabla = teclado.nextLine();
                    } while (!tabla.equalsIgnoreCase("D") && !tabla.equalsIgnoreCase("E") && !tabla.equalsIgnoreCase("TE") && !tabla.equalsIgnoreCase("TD"));
                    if ("TD".equalsIgnoreCase(tabla)) {
                        miBD.consultas("Select * from departamentos");
                    } else if ("TE".equalsIgnoreCase(tabla)) {
                        miBD.consultas("Select * from empleados");
                    } else if ("D".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del departamento a mostrar.");
                        int depNumero = teclado.nextInt();
                        int conteo = comprobar(miBD, "departamentos", "depno", depNumero);
                        if (conteo != 0) {
                            miBD.consultas("Select * from departamentos where depno = " + depNumero);
                        } else {
                            System.out.println("Ese departamento no existe.");
                        }
                    } else if ("E".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del empleado a mostrar.");
                        int empNumero = teclado.nextInt();
                        int conteo = comprobar(miBD, "empleados", "empno", empNumero);
                        if (conteo != 0) {
                            miBD.consultas("Select * from empleados where empno = " + empNumero);
                        } else {
                            System.out.println("Ese empleado no existe.");
                        }
                    }
                }
                case 3 -> {
                    String tabla;
                    do {
                        System.out.println("¿Qúé desea modificar, un empleado(E) o un departamento(D)?");
                        tabla = teclado.nextLine();
                    } while (!tabla.equalsIgnoreCase("D") && !tabla.equalsIgnoreCase("E"));
                    if ("D".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del departamento a modificar.");
                        int depNumero = teclado.nextInt();
                        int conteo = comprobar(miBD, "departamentos", "depno", depNumero);
                        if (conteo != 0) {
                            System.out.println("Estas son las propiedades del departamento antes de su modificación.");
                            miBD.consultas("Select * from departamentos where depno = " + depNumero);
                            teclado.nextLine();
                            System.out.println("Ingrese el nuevo nombre del departamento.");
                            String departamento = teclado.nextLine();
                            System.out.println("Facilite la nueva ubicación");
                            String ubicacion = teclado.nextLine();
                            actualizarDepartamento(miBD, depNumero, departamento, ubicacion);
                        } else {
                            System.out.println("Ese departamento no existe.");
                        }
                    }
                    if ("E".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del empleado a modificar.");
                        int depNumero = teclado.nextInt();
                        int conteo = comprobar(miBD, "empleados", "empno", depNumero);
                        if (conteo != 0) {
                            System.out.println("Estas son los atributos del empleado antes de su modificación.");
                            miBD.consultas("Select * from empleados where empno = " + depNumero);
                            teclado.nextLine();
                            System.out.println("Ingrese el nuevo nombre del empleado.");
                            String departamento = teclado.nextLine();
                            System.out.println("Facilite el nuevo puesto.");
                            String ubicacion = teclado.nextLine();
                            actualizarDepartamento(miBD, depNumero, departamento, ubicacion);
                        } else {
                            System.out.println("Ese empleado no existe.");
                        }
                    }
                }
                case 4 -> {
                    String tabla;
                    String respuesta;
                    do {
                        System.out.println("¿Qúé desea eliminar, un empleado(E) o un departamento(D)?");
                        tabla = teclado.nextLine();
                    } while (!tabla.equalsIgnoreCase("D") && !tabla.equalsIgnoreCase("E"));
                    if ("D".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del departamento a eliminar.");
                        int depNumero = teclado.nextInt();
                        teclado.nextLine();
                        int conteo = comprobar(miBD, "departamentos", "depno", depNumero);
                        if (conteo != 0) {
                            System.out.println("Estos son los datos del departamento a eliminar:");
                            miBD.consultas("Select * from departamentos where depno = " + depNumero);
                            do {
                                System.out.println("¿Desea continuar? Y/N");
                                respuesta = teclado.nextLine();
                            } while (!respuesta.equalsIgnoreCase("Y") && !respuesta.equalsIgnoreCase("N"));
                            if ("Y".equalsIgnoreCase(respuesta)) {
                                miBD.eliminar("DELETE FROM departamentos WHERE depno = " + depNumero);
                            } else if ("N".equalsIgnoreCase(respuesta)) {
                                System.out.println("Parece que cambio de opinión, vuelva cuando sepa que hacer.");
                            }
                        } else {
                            System.out.println("Ese departamento no existe.");
                        }
                    } else if ("E".equalsIgnoreCase(tabla)) {
                        System.out.println("Inserte el número del empleado a eliminar.");
                        int depNumero = teclado.nextInt();
                        teclado.nextLine();
                        int conteo = comprobar(miBD, "empleados", "empno", depNumero);
                        if (conteo != 0) {
                            System.out.println("Estos son los datos del empleado a eliminar:");
                            miBD.consultas("Select * from empleados where empno = " + depNumero);
                            do {
                                System.out.println("¿Desea continuar? Y/N");
                                respuesta = teclado.nextLine();
                            } while (!respuesta.equalsIgnoreCase("Y") && !respuesta.equalsIgnoreCase("N"));
                            if ("Y".equalsIgnoreCase(respuesta)) {
                                miBD.eliminar("DELETE FROM empleados WHERE empno = " + depNumero);
                            } else if ("N".equalsIgnoreCase(respuesta)) {
                                System.out.println("Parece que cambio de opinión, vuelva cuando sepa que hacer.");
                            }
                        } else {
                            System.out.println("Ese empleado no existe.");
                        }
                    }
                }
            }
            option = menu();
        }
        System.out.println("\nGracias por usar nuestro software.");
    }
}