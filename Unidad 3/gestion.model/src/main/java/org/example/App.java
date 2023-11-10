package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {

        miBD bd = new miBD("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/InstitutoFP","postgres", "iesbelen");
        bd.conectar();
        bd.insertar("Asignaturas", "nombre","anyo" ,"Markup Languages", 1);
    }


}
