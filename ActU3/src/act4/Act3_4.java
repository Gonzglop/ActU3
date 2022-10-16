package act4;

import java.sql.*;

/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */

public class Act3_4 {

    public static void main(String[] args) {
        try {
            //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "user_libro_add", "pwd_libro_add");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "root", "FFversus13");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            final String sqlQuery = "SELECT * FROM CLIENTES";

            ResultSet rs = s.executeQuery(sqlQuery);

            rs.last();
            System.out.println("Nº registros: " + rs.getRow());

            int i = 1;
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
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