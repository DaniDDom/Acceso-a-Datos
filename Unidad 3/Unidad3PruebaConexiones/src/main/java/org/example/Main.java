package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       Connection conn = null;

       try {
           Class.forName("org.postgresql.Driver");
           String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
           String user = "postgres";
           String pwd = "iesbelen";

           conn = DriverManager.getConnection(url,user,pwd);
           System.out.println("Conexión establecida");
       } catch(ClassNotFoundException e) {
           System.out.println("No se pudo cargar el controlador");
           throw new RuntimeException(e);
       } catch (SQLException e) {
           System.out.println("Error al conectar a la BD");
           throw new RuntimeException(e);
       } finally {
           if(conn != null) {
               try {
                   conn.close();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
       }
    }
}