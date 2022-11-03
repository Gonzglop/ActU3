import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CargaDatos {
    public static void insert(Connection c, String sqlInsert1, String sqlInsert2, String sqlInsert3, String sqlInsert4, String sqlInsert5, String sqlInsert6, String sqlInsert7, String sqlInsert8, String sqlInsert9, String sqlInsert10, String sqlInsert11, String sqlInsert12, String sqlInsert13, String sqlInsert14) {

        try {
            Statement s = c.createStatement();
            c.setAutoCommit(false);

            s.executeUpdate(sqlInsert1);
            s.executeUpdate(sqlInsert2);
            s.executeUpdate(sqlInsert3);
            s.executeUpdate(sqlInsert4);
            s.executeUpdate(sqlInsert5);
            s.executeUpdate(sqlInsert6);
            s.executeUpdate(sqlInsert7);
            s.executeUpdate(sqlInsert8);
            s.executeUpdate(sqlInsert9);
            s.executeUpdate(sqlInsert10);
            s.executeUpdate(sqlInsert11);
            s.executeUpdate(sqlInsert12);
            s.executeUpdate(sqlInsert13);
            s.executeUpdate(sqlInsert14);

            c.commit();
            s.close();

            JOptionPane.showMessageDialog(null, "Datos insertados con éxito. "); //+ nFil + "filas insertadas.");

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
