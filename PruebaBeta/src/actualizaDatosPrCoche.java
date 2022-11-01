import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class actualizaDatosPrCoche {
    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "FFversus13")) {
            try (PreparedStatement sInsert = c.prepareStatement("UPDATE COCHE SET MARCA = 'SMART' WHERE MATRICULA = ?")) {

                sInsert.setString(1, "1414ASD");

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
