import javax.swing.*;
import java.sql.*;

public class RegistraNota {
    public static void insertNota(Connection c, String sqlInsert) {
        String dni = "4448242";
        String nombreAsig = "Sist. Informáticos";
        int nota = 1;

        try {
            PreparedStatement ps = c.prepareStatement(sqlInsert);

            c.setAutoCommit(false);

            int i = 1;

            ps.setString(i++, dni);
            ps.setString(i++, nombreAsig);
            ps.setInt(i++, nota);

            ps.executeUpdate();

            c.commit();

            ps.close();

            JOptionPane.showMessageDialog(null, "Datos insertados con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido insertar los datos.");

            try {
                c.rollback();
                JOptionPane.showMessageDialog(null, "Se hace ROLLBACK.");

            } catch (SQLException er) {
                JOptionPane.showMessageDialog(null, "ERROR haciendo ROLLBACK.");
            }
        }
    }

}
