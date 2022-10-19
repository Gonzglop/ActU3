package act8;

import java.sql.*;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */


public class Act3_8 {

    public static void main(String[] args) {
        //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "user_libro_add", "pwd_libro_add");

        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/libro_ad", "root", "root");
             CallableStatement s = c.prepareCall("{call apellidos_cliente(?)}")) {

            s.setString(1, "78901234X");
            //s.setInt(2, 0);
            s.registerOutParameter(1, Types.CHAR);

            s.execute();

            ResultSet rs = s.getResultSet();

            int inout_long = s.getInt(1);
            System.out.println("=> inout_long: " + inout_long);

            while (rs.next()) {

                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}


/*
DELIMITER //
CREATE FUNCTION apellidos_cliente(in_dni CHAR(9))
RETURNS VARCHAR (32)
DETERMINISTIC
READS SQL DATA
BEGIN
DECLARE apell VARCHAR(32) DEFAULT NULL;
    SELECT APELLIDOS FROM CLIENTES WHERE DNI=in_dni INTO apell;
    RETURN apell;
END //
DELIMITER ;
 */

