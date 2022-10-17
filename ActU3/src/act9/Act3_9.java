package act9;

import java.sql.*;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */


public class Act3_9 {

    public static void main(String[] args) {
        //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "user_libro_add", "pwd_libro_add");

        String[][] datosClientes = {
                {"13579135G", "MOYA", null},
                {"24680246G", "SILVA", "25865"},
                {"96307418R", "TORRES", "19273"}
        };

        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "root", "FFversus13");) {
            try (PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?)")) {

                c.setAutoCommit(false);

                for (int nCli = 0; nCli < datosClientes.length; nCli++) {

                    for (int i = 0; i < datosClientes[nCli].length; i++) {

                        sInsert.setString(i + 1, datosClientes[nCli][i]);
                    }
                    sInsert.addBatch();
                }

                sInsert.executeBatch();

                c.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.rollback();
                } catch (Exception er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                    er.printStackTrace(System.err);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }
}


