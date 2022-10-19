import java.sql.*;

public class muestraDatos {

    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/concesionario", "root", "root");
            Statement s = c.createStatement();

            final String sqlQueryCliente = "SELECT * FROM CLIENTE";
            final String sqlQueryCoche = "SELECT * FROM COCHE";

            ResultSet rs = s.executeQuery(sqlQueryCliente);
            System.out.println("CLIENTES:");
            int i = 1;
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("Dni:" + rs.getString("DNI"));
                System.out.println("Nombre: " + rs.getString("NOMBRE"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("Dirección:" + rs.getString("DIRECCION"));
                System.out.println("E-mail: " + rs.getString("EMAIL"));
                System.out.println("Teléfono: " + rs.getString("TELEFONO"));
            }
            rs.close();

            rs = s.executeQuery(sqlQueryCoche);
            i = 1;
            System.out.println("COCHES:");
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("Matricula:" + rs.getString("MATRICULA"));
                System.out.println("Modelo: " + rs.getString("MODELO"));
                System.out.println("Marca: " + rs.getString("MARCA"));
                System.out.println("Color:" + rs.getString("COLOR"));
                System.out.println("Dni cliente: " + rs.getString("DNI_CLIENTE"));
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
