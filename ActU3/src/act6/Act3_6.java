package act6;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */


public class Act3_6 {

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/libro_ad", "root", "root")) {
            try (PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?);")) {

                c.setAutoCommit(false);

                int i = 0;

                sInsert.setString(++i, "54320198V");
                sInsert.setString(++i, "CARVAJAL");
                sInsert.setString(++i, "10109");
                sInsert.executeUpdate();

                sInsert.setString(i = 1, "76543210S");
                sInsert.setString(++i, "MARQUEZ");
                sInsert.setString(++i, "46987");
                sInsert.executeUpdate();

                sInsert.setString(i = 1, "90123456A");
                sInsert.setString(++i, "MOLINA");
                sInsert.setString(++i, "35153");
                sInsert.executeUpdate();

                c.commit();

            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    c.rollback();
                    System.err.println("Se hace ROLLBACK");

                } catch (SQLException er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                    er.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }
}
    /*
CREATE TABLE CLIENTES1 (DNI CHAR(9) NOT NULL,APELLIDOS VARCHAR(32) NOT NULL, CP INTEGER, PRIMARY KEY (DNI));
     */
