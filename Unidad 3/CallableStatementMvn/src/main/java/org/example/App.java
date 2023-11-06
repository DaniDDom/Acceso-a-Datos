package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App {
        static final String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        static final String user = "postgres";
        static final String password = "iesbelen";

        public static void main(String[] args) throws ClassNotFoundException {

            try (Connection conn = DriverManager.getConnection(url,user,password)) {
                CallableStatement statement = conn.prepareCall("{call asignaturasAnuales(1)}");
                ResultSet rs = statement.executeQuery();
                System.out.println("Código" + "\t" + "Nombre");
                System.out.println("-----------------------");
                while(rs.next()) {
                    System.out.println((rs.getString(1) + "\t"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
}