import java.sql.*;

public class muestraConsulta {

    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/concesionario", "root", "root");
            Statement s = c.createStatement();

            final String sqlQuery = "SELECT (CL.NOMBRE,CL.APELLIDOS,CH.MARCA) FROM CLIENTE CL,COCHE CH " +
                    "WHERE CH.MARCA = SMART AND CL.DNI = CH.DNI_CLIENTE";

            ResultSet rs = s.executeQuery(sqlQuery);
            int i = 1;
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("Nombre: " + rs.getString("NOMBRE"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("Marca:" + rs.getString("MARCA"));
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
