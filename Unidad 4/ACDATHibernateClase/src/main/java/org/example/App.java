package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{


    static Session abrirSession() throws Exception {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session == null) {
            throw new Exception("Error abriendo la sesión");
        } else {
            System.out.println("Conectado.");
        }

        return session;
    }


    public static void main( String[] args ) throws Exception {

        Session session = abrirSession();

    }
}
