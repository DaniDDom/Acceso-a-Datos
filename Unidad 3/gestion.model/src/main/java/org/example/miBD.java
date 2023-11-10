package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class miBD {

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


    public miBD(String nombre, String url, String user, String pwd) {

        this.nombre = nombre;
        this.user = user;
        this.pwd = pwd;
        this.url = url;
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
    public void insertar(int atributo1, String atributo2, int atributo3,int atributo4) throws SQLException {
        Connection con = this.conectar();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO SUBJECTS (atributo1, atributo2, atributo3, atributo4) VALUES(?,?,?,?)");
        pstmt.setInt(1, atributo1);
        pstmt.setString(2,atributo2);
        pstmt.setInt(3,atributo3);
        pstmt.setInt(4,atributo4);
        pstmt.executeUpdate();
        cerrarConexion(con);
    }
    public void insertar(String tabla , String columna1, String columna2, String valor1, int valor2) throws SQLException {
        Connection con = this.conectar();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tabla + "(" + columna1+ ", " +columna2+ ") VALUES(?,?)");
        pstmt.setString(1,valor1);
        pstmt.setInt(2,valor2);
        pstmt.executeUpdate();
        cerrarConexion(con);
    }
}
