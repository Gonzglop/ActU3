package act7;

import java.sql.*;
/*
 * @author Gonzalo López
 * @date 16/10/22
 * @version 1.0
 */

public class Act3_7_2 {

    public static void main(String[] args) {
        //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libro_ad", "user_libro_add", "pwd_libro_add");
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/libro_ad", "root", "root")) {
            try (PreparedStatement sInsertFact = c.prepareStatement("INSERT INTO FACTURAS (DNI_CLIENTE) VALUES (?);",
                    PreparedStatement.RETURN_GENERATED_KEYS);

                 PreparedStatement sInsertLineaFact = c.prepareStatement("INSERT INTO LINEAS_FACTURA (NUM_FACTURA,LINEA_FACTURA, CONCEPTO, CANTIDAD) VALUES (?,?,?,?);")) {

                c.setAutoCommit(false);

                int i = 1;

                sInsertFact.setString(i++, "78901234X");
                sInsertFact.executeUpdate();
                //ResultSet rs = sInsertFact.getGeneratedKeys();
                rs.next();
                int numFact = rs.getInt(1);

                int lineaFact = 1;

                i = 1;

                sInsertLineaFact.setInt(i++, numFact);
                sInsertLineaFact.setInt(i++, lineaFact++);
                sInsertLineaFact.setString(i++, "TUERCAS");
                sInsertLineaFact.setInt(i++, 25);
                sInsertLineaFact.executeUpdate();

                i = 1;

                sInsertLineaFact.setInt(i++, numFact);
                sInsertLineaFact.setInt(i++, lineaFact++);
                sInsertLineaFact.setString(i++, "TORNILLOS");
                sInsertLineaFact.setInt(i++, 250);
                sInsertLineaFact.executeUpdate();

                c.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.rollback();
                    System.err.println("Se hace ROLLBACK");
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
//ResultSet rs = sInsertFact.getGeneratedKeys();

/*
CREATE TABLE FACTURAS(
NUM_FACTURA INTEGER AUTO_INCREMENT NOT NULL,
DNI_CLIENTE CHAR(9) NOT NULL,
PRIMARY KEY(NUM_FACTURA),
FOREIGN KEY FK_FACT_DNI_CLIENTES (DNI_CLIENTE) REFERENCES CLIENTES(DNI)
);
 */

/*
CREATE TABLE LINEAS_FACTURA(
NUM_FACTURA INTEGER NOT NULL,
LINEA_FACTURA SMALLINT NOT NULL,
CONCEPTO VARCHAR(32) NOT NULL,
CANTIDAD SMALLINT NOT NULL,
PRIMARY KEY(NUM_FACTURA,LINEA_FACTURA),
FOREIGN KEY FK_LINEAFACT_NUM_FACTURA(NUM_FACTURA) REFERENCES FACTURAS (NUM_FACTURA)
);
 */
