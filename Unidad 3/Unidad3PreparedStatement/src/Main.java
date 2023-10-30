import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "iesbelen";

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO asignaturas(nombre, anyo) VALUES (?,?)");
        pstmt.setString(1, "Formación y orientación laboral");
        pstmt.setInt(2, 1);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}