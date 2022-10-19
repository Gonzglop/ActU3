import java.sql.*;

public class creaTabla {
    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/concesionario", "root", "root");
            Statement s = c.createStatement();

            final String sqlCli = "CREATE TABLE CLIENTE(" +
                    "DNI CHAR(9) NOT NULL," +
                    "NOMBRE VARCHAR(50) NOT NULL," +
                    "APELLIDOS VARCHAR(50) NOT NULL," +
                    "DIRECCION VARCHAR(50) ," +
                    "EMAIL VARCHAR(50) ," +
                    "TELEFONO VARCHAR(9) ," +
                    "PRIMARY KEY(DNI)" +
                    ");";
            s.execute(sqlCli);

            final String sqlCoche = "CREATE TABLE COCHE(" +
                    "MATRICULA CHAR(7) NOT NULL," +
                    "MODELO VARCHAR(50) NOT NULL," +
                    "MARCA VARCHAR(50) NOT NULL," +
                    "COLOR VARCHAR(50)," +
                    "DNI_CLIENTE CHAR(9)," +
                    "PRIMARY KEY(MATRICULA)," +
                    "FOREIGN KEY FK_DNI_CLIENTES (DNI_CLIENTE) REFERENCES CLIENTE (DNI)" +
                    ");";

            s.execute(sqlCoche);


        } catch (SQLException e) {
            System.err.println("Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }

}
