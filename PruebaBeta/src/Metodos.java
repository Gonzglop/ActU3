import javax.swing.*;
import java.sql.*;

public class Metodos {

    public static void createTable(Statement s, String sqlCli) {
        try {
            s.execute(sqlCli);
            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido crear la tabla.");
        }
    }


    public static void insert(Connection c, PreparedStatement ps, int i, String insertClientes, String[][] arrayClientes) {

        try {
            ps = c.prepareStatement(insertClientes);
            c.setAutoCommit(false);

            for (int nReg = 0; nReg < arrayClientes.length; nReg++) {

                for (i = 0; i < arrayClientes[nReg].length; i++) {

                    ps.setString(i + 1, arrayClientes[nReg][i]);
                }
                ps.addBatch();
            }
            ps.executeBatch();

            c.commit();
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


    public static void selectFrom(Statement s, ResultSet rs, int i, String queryCliente) {

        try {
            rs = s.executeQuery(queryCliente);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {

                String registro = "";

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                    registro += "\n" + rsmd.getColumnName(j) + ": " + rs.getString(j);
                }
                JOptionPane.showMessageDialog(null,
                        "[" + (++i) + "]" + registro
                );
            }
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
        }
    }


    public static void updatePS(PreparedStatement ps, Connection c, int i, String updateCochePs, String matricula) {

        try {
            ps = c.prepareStatement(updateCochePs);
            ps.setString(++i, matricula);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
        }
    }


    public static void update(Statement s, String updateCliente) {
        try {
            s.executeUpdate(updateCliente);

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
        }
        return;
    }
}
