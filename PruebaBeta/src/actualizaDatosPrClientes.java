import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class actualizaDatosPrClientes {
    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/concesionario", "root", "root")) {
            try (PreparedStatement sInsert = c.prepareStatement("UPDATE CLIENTE SET TELEFONO = '611460677' WHERE DNI = ?")) {

                sInsert.setString(1, "54320198V");

                sInsert.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }
}
