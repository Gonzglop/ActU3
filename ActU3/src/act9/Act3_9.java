package act9;

import java.sql.*;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */


public class Act3_9 {

    public static void main(String[] args) {
        String[][] datosClientes = {
                {"13579135G", "MOYA", null},
                {"24680246G", "SILVIA", "25865"},
                {"96307418R", "TORRES", "19273"}
        };
        try (Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/libro_ad", "root", "root");) {
            try (PreparedStatement sInsert = miConexion.prepareStatement("INSERT INTO CLIENTES1 (DNI,APELLIDOS,CP) VALUES (?,?,?)")) {
                miConexion.setAutoCommit(false);
                for (int nCli = 0; nCli < datosClientes.length; nCli++) {
                    for (int i = 0; i < datosClientes[nCli].length; i++) {
                        sInsert.setString(i + 1, datosClientes[nCli][i]);
                    }
                    sInsert.addBatch();
                }
                sInsert.executeBatch();
                miConexion.commit();
            } catch (SQLException e) {
                e.getErrorCode();
                try {
                    miConexion.rollback();
                    //System.err.println("Se hace ROLLBACK");
                } catch (SQLException er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }
}
//soportaLotes= c.getMetaData().supportsBatchUpdates(),

