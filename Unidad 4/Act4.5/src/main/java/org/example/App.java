package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class App {
    static Session abrirSession() throws Exception {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = null;

        if (session == null) {
            throw new Exception("Error abriendo la sesión");
        } else {
            System.out.println("Conectado.");
            t = session.beginTransaction();
            Person person = new Person("Pepe");
            Phone phone1 = new Phone("1234567");
            Phone phone2 = new Phone("12341567");
            person.addPhone(phone1);
            person.addPhone(phone2);
            session.persist(person);
            session.flush();
            person.removePhone(phone1);
            t.commit();
        }

        return session;
    }

    public static void main(String[] args) throws Exception {
        abrirSession();

    }
}
