package org.example;

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

        while (!teclado.hasNextInt()) {
            System.out.println("Elige una opción entre 0 y 4.");
            teclado.nextInt();
        }

        return (teclado.nextInt());
    }
    public static void main(String[] args) {

        miBD miBD = new miBD("postgres", "iesbelen", "jdbc:postgresql://localhost:5432/empleados", "org.postgresql.Driver");
        int option = menu();
        while (option != 0) {
            switch (option) {
                case 1 -> {
                    String tabla = "";
                    do {
                        System.out.println("¿Qué desea insertar, un empleado(E) o un departamento(D)");
                        tabla = teclado.next();
                    } while (!tabla.equalsIgnoreCase("D") && !tabla.equalsIgnoreCase("E"));

                }
            }

            option = menu();
        }
    }
}