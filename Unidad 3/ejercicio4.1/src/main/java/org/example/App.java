package org.example;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Scanner teclado = new Scanner(System.in);
    private static int menu() {

        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1. - Mostrar lista de asignaturas.");
        System.out.println("2. - Añadir asignatura.");
        System.out.println("3. - Modificar tabla.");
        System.out.println("0. - Salir");

        while(teclado.hasNextInt()) {
            System.out.println("Elige una opción entre 0 y 3.");
            teclado.next();
        }

        return 0;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
