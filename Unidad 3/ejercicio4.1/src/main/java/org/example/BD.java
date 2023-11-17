package org.example;

import java.sql.*;
import java.util.Scanner;

public class BD {
    private String user;
    private String pwd;
    private String url;

    private String nombre;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public BD(String user, String pwd, String url, String nombre) {
        this.user = user;
        this.pwd = pwd;
        this.url = url;
        this.nombre = nombre;
    }


    private void alterTable(String sentencia) throws SQLException {
        Connection con = this.conectar();
        Statement statement = con.createStatement();
        int rows = statement.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Filas afectadas: %d\n", rows);
    }

    private void addSubject(Scanner teclado) throws SQLException {
        Connection con = this.conectar();
        if (teclado.hasNextLine()) teclado.nextLine();
        System.out.println("¿Nombre de la asignatura?");
        String subjectName = teclado.nextLine();
        System.out.println("¿Año?");
        int subjectYear = teclado.nextInt();
        System.out.println("¿Código de curso?");
        int courseCode = teclado.nextInt();
        System.out.println();
        Statement statement = con.createStatement();
        StringBuilder sqlSentence = new StringBuilder();
        sqlSentence.append("INSERT INTO subjects VALUES ");
        sqlSentence.append(" (DEFAULT, '");
        sqlSentence.append(subjectName);
        sqlSentence.append("', '");
        sqlSentence.append(subjectYear);
        sqlSentence.append("', '");
        sqlSentence.append(courseCode);
        sqlSentence.append("');");
        int rows = statement.executeUpdate(sqlSentence.toString(), Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Filas añadidas: %d \n", rows);
    }

    private void DisplaySubjectList() throws SQLException {
        Connection con = this.conectar();
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM subjects ORDER BY code";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Código" + "\t" + "Nombre" + "\t" + "Año");
        System.out.println("----------------------------------------------");
        while(rs.next()) {
            System.out.println(rs.getString(1) + "\t " + rs.getString(2) +  "("+ rs.getString(3)+ ")");
        }
    }

    public Connection conectar() throws SQLException {
        Connection con = null;
        try {
            Class.forName(nombre);

            try {
                con = DriverManager.getConnection(url, user, pwd);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el controlador");
        }
        return con;
    }

    public void cerrarConexion(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
