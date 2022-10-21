package act8;

import javax.lang.model.element.TypeParameterElement;
import java.sql.*;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */


public class Act3_8 {

    public static void main(String[] args) {

        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/libro_ad", "root", "root");
             CallableStatement s = c.prepareCall("{? = call apellidos_cliente(?)}")) {

            s.registerOutParameter(1, Types.VARCHAR);
            s.setString(2, "78901234X");
            s.execute();

            System.out.println("Apellidos: " + s.getString(1));

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

