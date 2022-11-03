import javax.swing.*;
import java.sql.*;

public class Listar {

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
}
