package org.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;
import java.util.logging.Level;


/**
 * Hello world!
 */
public class App {
   static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {

        int opcion;

        String menuPrincipal = "------------- MENÚ PRINCIPAL -------------\n" +
                "1. Menú empleados\n" +
                "2. Menú departamentos\n" +
                "3. SALIR\n" +
                "-> ";


        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger =
                org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        DepartamentoDAO crudDepartamentos = new DepartamentoDAO(sessionFactory);
        EmpleadosDAO crudEmpleados = new EmpleadosDAO(sessionFactory);

        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Se inició la sesión");
        } else {
            System.out.println("Error abriendo la sesión");
        }
        do {

            System.out.println(menuPrincipal);
            opcion = teclado.nextInt() ;
            switch (opcion) {
                case 1 -> crudEmpleados.gestionarEmpleados();
                case 2 -> crudDepartamentos.gestionarDepartamentos();
                case 3 -> System.out.println("Cerrando aplicación");
            }
        } while (opcion != 3);

    }
}
