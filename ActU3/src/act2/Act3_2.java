package act2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */

/*
Al utilizar getInt() el programa se ejecuta con normalidad,
 pero cuando el valor es 'null' lo imprimirá en pantalla como '0'.
*/
public class Act3_2 {

    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "root", "root");
            Statement s = c.createStatement();

            final String sqlQuery = "SELECT * FROM CLIENTES";

            ResultSet rs = s.executeQuery(sqlQuery);
            int i = 1;
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("Dni:" + rs.getString("DNI"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("CP: " + rs.getInt("CP"));
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