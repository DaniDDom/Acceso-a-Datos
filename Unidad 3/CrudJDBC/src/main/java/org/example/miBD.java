package org.example;

import java.sql.*;
import java.util.List;

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

    public void insertar(String nombreTabla, Object... valores) throws SQLException {
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

    public void crearTabla(String nombreTabla, List<String> parametros) throws SQLException {
        Connection con = this.conectar();
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + nombreTabla + " (");

        for (int i = 0; i < parametros.size(); i++) {
            sql.append(parametros.get(i));

            if (i < parametros.size() - 1) {
                sql.append(", ");
            }
        }

        sql.append(")");

        try (Statement statement = con.createStatement()) {
            statement.execute(sql.toString());
            System.out.println("Tabla creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cerrarConexion(con);
    }

    public void consultas(String sentencia) throws SQLException {
        Connection con = this.conectar();


            try (Statement statement = con.createStatement();
                 ResultSet resultSet = statement.executeQuery(sentencia)) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Imprimir nombres de columnas
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(String.format("%-15s", metaData.getColumnName(i)));
                }
                System.out.println();

                // Imprimir línea separadora
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print("--------------- ");
                }
                System.out.println();

                // Imprimir valores
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(String.format("%-15s", resultSet.getString(i)));
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con.close();
        }
    }



