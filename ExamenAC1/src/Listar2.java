import javax.swing.*;
import java.sql.*;

public class Listar2 {

    public static void selectFrom(Connection c, String sqlQuery) {

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int i = 0;

            while (rs.next()) {
                int j = 1;
                JOptionPane.showMessageDialog(null,
                        "[" + (++i) + "]" +
                                "\n" + rsmd.getColumnName(j) + ": " + rs.getString(j++)+
                                "\n" + rsmd.getColumnName(j) + ": " + rs.getString(j++)+
                                "\n" + rsmd.getColumnName(j) + ": " + rs.getString(j++)+
                                "\n" + rsmd.getColumnName(j) + ": " + rs.getInt(j++)
                );
            }
            rs.close();
            s.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
        }
    }
}
