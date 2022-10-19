import java.sql.*;

public class actualizaDatos {
    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/concesionario", "root", "root");
            Statement s = c.createStatement();

            final String sqlUpdate = "UPDATE CLIENTE SET APELLIDOS = 'López' WHERE DNI = '54320198V'";

            int update = s.executeUpdate(sqlUpdate);


        } catch (SQLException e) {
            System.err.println("Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }
}
