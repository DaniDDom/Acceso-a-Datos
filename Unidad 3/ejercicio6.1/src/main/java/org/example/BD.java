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


    public void alterTable(String sentencia) throws SQLException {
        Connection con = this.conectar();
        Statement statement = con.createStatement();
        int rows = statement.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Filas afectadas: %d\n", rows);
        statement.close();
        con.close();
    }

    public void addSubject(String nombreTabla, Object... valores) throws SQLException {
        Connection con = this.conectar();

        StringBuilder sql = new StringBuilder("INSERT INTO " + nombreTabla + " VALUES (DEFAULT");
        for (int i = 0; i < valores.length; i++) {
            sql.append(", ?");
        }
        sql.append(")");

        PreparedStatement pstmt = con.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < valores.length; i++) {
            pstmt.setObject(i + 1, valores[i]);
        }
        pstmt.executeUpdate();

        System.out.println("Los valores se insertaron correctamente.");
        pstmt.close();
        cerrarConexion(con);
    }

    public void crearTabla(String sentencia) throws SQLException {
        Connection con = this.conectar();
        try(PreparedStatement statement = con.prepareStatement(sentencia)) {
            statement.execute();
            System.out.println("Tabla creada con exito");
        }

        con.close();

    }
    public void DisplaySubjectList() throws SQLException {
        Connection con = this.conectar();
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM asignaturas ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Código" + "\t" + "Nombre" + "\t" + "Año");
        System.out.println("----------------------------------------------");
        while(rs.next()) {
            System.out.println(rs.getString(1) + "\t " + rs.getString(2) +  "("+ rs.getString(3)+ ")");
        }
        rs.close();
        statement.close();
        con.close();
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
