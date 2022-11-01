import javax.swing.*;
import java.sql.*;

public class Metodos {

    public static void createTable(Connection c, String sglTable) {
        try {
            Statement s = c.createStatement();
            s.execute(sglTable);
            s.close();
            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido crear la tabla.");
        }
    }


    public static void insert(Connection c, String sqlInsert, String[][] array) {

        try {
            PreparedStatement ps = c.prepareStatement(sqlInsert);
            c.setAutoCommit(false);

            for (int nReg = 0; nReg < array.length; nReg++) {
                for (int i = 0; i < array[nReg].length; i++) {
                    ps.setString(i + 1, array[nReg][i]);
                }
                ps.addBatch();
            }
            ps.executeBatch();

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


    public static void selectFrom(Connection c, String sqlQuery) {

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int i = 0;

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
            s.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
        }
    }


    public static void updatePS(Connection c,String sqlUpdate, String newData) {
        int i = 0;
        int nFil;

        try {
            PreparedStatement ps = c.prepareStatement(sqlUpdate);
            ps.setString(++i, newData);
            nFil = ps.executeUpdate();

            ps.close();

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.\n"+ nFil +" filas modificadas.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
        }
    }


    public static void update(Connection c, String sqlUpdate) {
        int nFil;

        try {
            Statement s = c.createStatement();
            nFil = s.executeUpdate(sqlUpdate);

            s.close();
            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.\n"+ nFil +" filas modificadas.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
        }
        return;
    }
}
