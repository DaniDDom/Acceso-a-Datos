package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);

    private static int menu() {

        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1. - Mostrar lista de asignaturas.");
        System.out.println("2. - Añadir asignatura.");
        System.out.println("3. - Modificar tabla.");
        System.out.println("4. - Creación tabla cursos con 2 valores.");
        System.out.println("5. - Modificación tabla asignaturas.");
        System.out.println("0. - Salir");
        System.out.println();

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
        String modificacionAsignaturas = "ALTER TABLE asignaturas ADD COLUMN curso INTEGER REFERENCES cursos(codigo)";
        while (option != 0) {

            switch (option) {
                case 1 -> miBD.DisplaySubjectList();
                case 2 -> {
                    Boolean salir = false;

                    while (!salir) {

                        teclado.nextLine();
                        System.out.println("Introduzca el simbolo '-' cuando desee parar.");
                        System.out.println("¿Nombre de la asignatura?");
                        String subjectName = teclado.nextLine();
                        if (subjectName.equals("-")) {
                            System.out.println("Saliendo....");
                            salir = true;
                        } else {
                            System.out.println("¿Año?");
                            int subjectYear = teclado.nextInt();
                            System.out.println();
                            miBD.addSubject("Asignaturas", subjectName, subjectYear);
                        }
                    }
                }
                case 3 -> miBD.alterTable(SQLsentence);
                case 4 -> {
                    System.out.println("Va a crear la tabla cursos y a introducir valores en ella: ");

                    miBD.crearTabla("CREATE TABLE cursos (codigo SERIAL PRIMARY KEY, nombre VARCHAR(90))");
                    miBD.addSubject("cursos", "Desarrollo de aplicaciones multiplataforma");
                    miBD.addSubject("cursos", "Desarrollo Web");
                }
                case 5 -> miBD.alterTable(modificacionAsignaturas);
            }
            option = menu();

        }
    }
}