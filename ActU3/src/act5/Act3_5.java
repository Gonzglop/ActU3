package act5;

import java.sql.*;

/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */

public class Act3_5 {

    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "root", "root");
            final String sqlQuery = "SELECT * FROM CLIENTES WHERE DNI = ?";
            PreparedStatement s = c.prepareStatement(sqlQuery,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = null;

            String[] dni = {"56789012B","78901234X","89012345E"};
            int cont = 1;

            for (int i = 0; i < dni.length; i++) {

                s.setString(1,dni[i]);
                rs = s.executeQuery();
                rs.first();

                System.out.println("[" + (cont++) + "]");
                System.out.println("Dni:" + rs.getString("DNI"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("CP: " + rs.getString("CP"));
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }
}