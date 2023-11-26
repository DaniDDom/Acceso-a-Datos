package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String url = "jdbc:postgresql://localhost:5432/Empleados";
    static final String user = "postgres";
    static final String password = "iesbelen";

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduzca el puesto");
            String puesto = teclado.nextLine();
            CallableStatement statementPuesto = con.prepareCall("{call lista_empleados_puesto('" + puesto + "')}");
            ResultSet rs = statementPuesto.executeQuery();
            System.out.println("Código Nombre " + "\t\tDepartamento");
            System.out.println("-----------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%6d %-15s %2d\n", rs.getInt(1), rs.getString(2), rs.getInt(4));
            }
            System.out.println("Introduzca el departamento");
            int nombre = teclado.nextInt();
            CallableStatement statementDpto = con.prepareCall("{call lista_emp_dpto('" + nombre + "')}");
            ResultSet rsDpto = statementDpto.executeQuery();
            System.out.println("Código Nombre " + "\t\tDepartamento");
            System.out.println("-----------------------------------------------------");
            while (rsDpto.next()) {
                System.out.printf("%6d %-15s %2d\n", rs.getInt(1), rs.getString(2), rs.getInt(4));
            }
            System.out.println("Introduzca el patrón");
            String patron = teclado.nextLine();
            CallableStatement statementNombre = con.prepareCall("{call lista_emp_nombre('" + patron + "')}");
            ResultSet rsNombre = statementDpto.executeQuery();
            System.out.println("Código Nombre " + "\t\tDepartamento");
            System.out.println("-----------------------------------------------------");
            while (rsNombre.next()) {
                System.out.printf("%6d %-15s %2d\n", rs.getInt(1), rs.getString(2), rs.getInt(4));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}