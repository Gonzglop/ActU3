import java.sql.*;

public class insertaDatos {


    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "FFversus13");
            PreparedStatement sInsertCliente = c.prepareStatement("INSERT INTO CLIENTE(DNI,NOMBRE,APELLIDOS,DIRECCION,EMAIL,TELEFONO) VALUES (?,?,?,?,?,?)");
            PreparedStatement sInsertCoche = c.prepareStatement("INSERT INTO COCHE(MATRICULA,MODELO,MARCA,COLOR,DNI_CLIENTE) VALUES (?,?,?,?,?)");

            int i = 0;

            sInsertCliente.setString(++i, "54320198V");
            sInsertCliente.setString(++i, "Pablo");
            sInsertCliente.setString(++i, "Carvajal");
            sInsertCliente.setString(++i, "Calle Serrano 6");
            sInsertCliente.setString(++i, "pablo@gmail.com");
            sInsertCliente.setString(++i, "666888777");
            sInsertCliente.executeUpdate();

            sInsertCliente.setString(i=1, "23420198X");
            sInsertCliente.setString(++i, "Juan");
            sInsertCliente.setString(++i, "Castro");
            sInsertCliente.setString(++i, "Calle Omar 2");
            sInsertCliente.setString(++i, "juan@gmail.com");
            sInsertCliente.setString(++i, "666888555");
            sInsertCliente.executeUpdate();

            i = 0;

            sInsertCoche.setString(++i, "9098DFD");
            sInsertCoche.setString(++i, "FORFOUR");
            sInsertCoche.setString(++i, "SMART");
            sInsertCoche.setString(++i, "AZUL");
            sInsertCoche.setString(++i, "54320198V");
            sInsertCoche.executeUpdate();

            sInsertCoche.setString(i=1, "1414ASD");
            sInsertCoche.setString(++i, "YARIS");
            sInsertCoche.setString(++i, "TOYOTA");
            sInsertCoche.setString(++i, "BLANCO");
            sInsertCoche.setString(++i, "23420198X");
            sInsertCoche.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }
}
