import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreaEstructura {
    public static void createTable(Connection c,String dropNotas,String dropAlumnos,String dropAsig, String sqlAlumnos, String sqlAsignaturas, String sqlNotas, String sqlAlterTableDni, String sqlAlterTableCod) {
        try {
            Statement s = c.createStatement();

            c.setAutoCommit(false);

            s.executeUpdate(dropNotas);
            s.executeUpdate(dropAlumnos);
            s.executeUpdate(dropAsig);

            s.execute(sqlAlumnos);
            s.execute(sqlAsignaturas);
            s.execute(sqlNotas);
            s.execute(sqlAlterTableDni);
            s.execute(sqlAlterTableCod);

            c.commit();

            s.close();
            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido crear la tabla.");
            try {
                c.rollback();
                JOptionPane.showMessageDialog(null, "Se hace ROLLBACK.");

            } catch (SQLException er) {
                JOptionPane.showMessageDialog(null, "ERROR haciendo ROLLBACK.");
            }
        }
    }
}
