package act1;
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
public class Act3_1 {

    public static void main(String[] args) {
        try {
            //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "user_libro_add", "pwd_libro_add");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "root", "FFversus13");
            Statement s = c.createStatement();

            final String sqlQuery = "SELECT * FROM CLIENTES";
            final String sqlDelete = "DELETE FROM CLIENTES WHERE APELLIDOS = 'LAMIQUIZ'";
            final String sqlUpdate = "UPDATE CLIENTES SET APELLIDOS = 'ROJAS' WHERE APELLIDOS = 'HOJAS'";

            System.out.println("---ESTADO INICIAL---");
            printQuery(s, sqlQuery);

            int delete = s.executeUpdate(sqlDelete);
            int update = s.executeUpdate(sqlUpdate);

            System.out.println("\n\n\n---ESTADO FINAL---");
            printQuery(s, sqlQuery);

        } catch (SQLException e) {
            System.err.println("Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }

    private static void printQuery(Statement s, String sqlQuery) throws SQLException {
        ResultSet rs = s.executeQuery(sqlQuery);
        int i = 1;
        while (rs.next()) {
            System.out.println("[" + (i++) + "]");
            System.out.println("Dni:" + rs.getString("DNI"));
            System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
            System.out.println("CP: " + rs.getString("CP"));
        }
        rs.close();
    }
}