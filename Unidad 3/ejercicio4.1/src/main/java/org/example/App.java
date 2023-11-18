package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class App {
    private static Scanner teclado = new Scanner(System.in);

    private static int menu() {

        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1. - Mostrar lista de asignaturas.");
        System.out.println("2. - Añadir asignatura.");
        System.out.println("3. - Modificar tabla.");
        System.out.println("0. - Salir");

        while (!teclado.hasNextInt()) {
            System.out.println("Elige una opción entre 0 y 3.");
            teclado.next();
        }

        return (teclado.nextInt());
    }

    public static void main(String[] args) throws SQLException {
        BD miBD = new BD("postgres", "iesbelen", "jdbc:postgresql://localhost:5432/InstitutoFP", "org.postgresql.Driver");
        int option = menu();
        String SQLsentence = "ALTER TABLE asignaturas ADD COLUMN horas INTEGER";
        while(option != 0) {
            switch(option) {
                case 1 -> miBD.DisplaySubjectList();
                case 2 -> miBD.addSubject(teclado);
                case 3 -> miBD.alterTable(SQLsentence);
            }
            option = menu();
        }
    }
}
