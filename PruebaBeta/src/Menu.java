import java.sql.*;
import javax.swing.JOptionPane;

public class Menu {

    static int select = -1; //opción elegida del usuario

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(LeerConfig.generaRutaConex(), LeerConfig.usuario, LeerConfig.password)){

            Statement s = c.createStatement();
            PreparedStatement sInsert;
            ResultSet rs;
            int i;

            //Mientras la opción elegida sea 0, preguntamos al usuario
            while (select != 0) {
                //Try catch para evitar que el programa termine si hay un error

                String lectura = JOptionPane.showInputDialog(null, "Elige una opción:\n" +
                        "\n1. Crear tabla CLIENTE" +
                        "\n2. Crear tabla COCHE" +
                        "\n3. Insertar datos en la tabla CLIENTE" +
                        "\n4. Insertar datos en la tabla COCHE" +
                        "\n5. Recuperar datos de la tabla CLIENTE" +
                        "\n6. Recuperar datos de la tabla COCHE" +
                        "\n7. Actualizar tabla CLIENTE" +
                        "\n8. Actualizar tabla CLIENTE utilizando sentencias preparadas" +
                        "\n9. Actualizar tabla COCHE utilizando sentencias preparadas" +
                        "\n10.Listar el nombre de los clientes que compraron un tipo de coche determinado" +
                        "\n\n0. Salir\n");
                //Recoger una variable por consola
                try {
                    select = Integer.parseInt(lectura);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "No has introducido un número, inténtalo otra vez.");
                    select = -1;
                }

                //Switch case en Java
                switch (select) {
                    case 1:
                        final String sqlCli = "CREATE TABLE CLIENTE(" +
                                "DNI CHAR(9) NOT NULL," +
                                "NOMBRE VARCHAR(50) NOT NULL," +
                                "APELLIDOS VARCHAR(50) NOT NULL," +
                                "DIRECCION VARCHAR(50) ," +
                                "EMAIL VARCHAR(50) ," +
                                "TELEFONO VARCHAR(9) ," +
                                "PRIMARY KEY(DNI)" +
                                ");";
                        sqlCreate(s, sqlCli);
                        break;

                    case 2:
                        final String sqlCoche = "CREATE TABLE COCHE(" +
                                "MATRICULA CHAR(7) NOT NULL," +
                                "MODELO VARCHAR(50) NOT NULL," +
                                "MARCA VARCHAR(50) NOT NULL," +
                                "COLOR VARCHAR(50)," +
                                "DNI_CLIENTE CHAR(9)," +
                                "PRIMARY KEY(MATRICULA)," +
                                "FOREIGN KEY FK_DNI_CLIENTES (DNI_CLIENTE) REFERENCES CLIENTE (DNI)" +
                                ");";

                        sqlCreate(s, sqlCoche);
                        break;

                    case 3:

                        try {
                            PreparedStatement sInsertCliente = c.prepareStatement("INSERT INTO CLIENTE(DNI,NOMBRE,APELLIDOS,DIRECCION,EMAIL,TELEFONO) VALUES (?,?,?,?,?,?)");
                            c.setAutoCommit(false);

                            i = 0;
                            sInsertCliente.setString(++i, "54320198V");
                            sInsertCliente.setString(++i, "Pablo");
                            sInsertCliente.setString(++i, "Carvajal");
                            sInsertCliente.setString(++i, "Calle Serrano 6");
                            sInsertCliente.setString(++i, "pablo@gmail.com");
                            sInsertCliente.setString(++i, "666888777");
                            sInsertCliente.executeUpdate();
                            i = 0;
                            sInsertCliente.setString(++i, "23420198X");
                            sInsertCliente.setString(++i, "Juan");
                            sInsertCliente.setString(++i, "Castro");
                            sInsertCliente.setString(++i, "Calle Omar 2");
                            sInsertCliente.setString(++i, "juan@gmail.com");
                            sInsertCliente.setString(++i, "666888555");
                            sInsertCliente.executeUpdate();

                            c.commit();
                            JOptionPane.showMessageDialog(null, "Datos insertados con éxito.");


                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se han podido insertar los datos.");

                            try {
                                c.rollback();
                                JOptionPane.showMessageDialog(null, "Se hace ROLLBACK.");

                            } catch (SQLException er) {
                                JOptionPane.showMessageDialog(null, "ERROR haciendo ROLLBACK.");
                            }
                        }
                        break;

                    case 4:

                        try {
                            PreparedStatement sInsertCoche = c.prepareStatement("INSERT INTO COCHE(MATRICULA,MODELO,MARCA,COLOR,DNI_CLIENTE) VALUES (?,?,?,?,?)");
                            c.setAutoCommit(false);

                            i = 0;
                            sInsertCoche.setString(++i, "9098DFD");
                            sInsertCoche.setString(++i, "FORFOUR");
                            sInsertCoche.setString(++i, "SMART");
                            sInsertCoche.setString(++i, "AZUL");
                            sInsertCoche.setString(++i, "54320198V");
                            sInsertCoche.executeUpdate();
                            i = 0;
                            sInsertCoche.setString(++i, "1414ASD");
                            sInsertCoche.setString(++i, "YARIS");
                            sInsertCoche.setString(++i, "TOYOTA");
                            sInsertCoche.setString(++i, "BLANCO");
                            sInsertCoche.setString(++i, "23420198X");
                            sInsertCoche.executeUpdate();

                            c.commit();
                            JOptionPane.showMessageDialog(null, "Datos insertados con éxito.");


                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se han podido insertar los datos.");

                            try {
                                c.rollback();
                                JOptionPane.showMessageDialog(null, "Se hace ROLLBACK.");

                            } catch (SQLException er) {
                                JOptionPane.showMessageDialog(null, "ERROR haciendo ROLLBACK.");
                            }
                        }
                        break;

                    case 5:
                        final String sqlQueryCliente = "SELECT * FROM CLIENTE";
                        try {
                            rs = s.executeQuery(sqlQueryCliente);
                            i = 1;
                            while (rs.next()) {
                                JOptionPane.showMessageDialog(null,
                                        "[" + (i++) + "]" +
                                                "\nDni:" + rs.getString("DNI") +
                                                "\nNombre: " + rs.getString("NOMBRE") +
                                                "\nApellidos: " + rs.getString("APELLIDOS") +
                                                "\nDirección:" + rs.getString("DIRECCION") +
                                                "\nE-mail: " + rs.getString("EMAIL") +
                                                "\nTeléfono: " + rs.getString("TELEFONO")
                                );
                            }
                            rs.close();

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
                        }
                        break;

                    case 6:
                        final String sqlQueryCoche = "SELECT * FROM COCHE";

                        try {
                            rs = s.executeQuery(sqlQueryCoche);
                            i = 0;
                            while (rs.next()) {
                                JOptionPane.showMessageDialog(null,
                                        "[" + (++i) + "]" +
                                                "\nMatricula:" + rs.getString("MATRICULA") +
                                                "\nModelo: " + rs.getString("MODELO") +
                                                "\nMarca: " + rs.getString("MARCA") +
                                                "\nColor:" + rs.getString("COLOR") +
                                                "\nDni cliente: " + rs.getString("DNI_CLIENTE")
                                );
                            }
                            rs.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
                        }
                        break;

                    case 7:
                        try {
                            final String sqlUpdate = "UPDATE CLIENTE SET APELLIDOS = 'López' WHERE DNI = '54320198V'";
                            int update = s.executeUpdate(sqlUpdate);

                            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
                        }
                        break;

                    case 8:
                        try {
                            sInsert = c.prepareStatement("UPDATE CLIENTE SET TELEFONO = '611460677' WHERE DNI = ?");
                            sInsert.setString(1, "54320198V");
                            sInsert.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
                        }
                        break;

                    case 9:
                        try {
                            sInsert = c.prepareStatement("UPDATE COCHE SET MARCA = 'SMART' WHERE MATRICULA = ?");
                            sInsert.setString(1, "1414ASD");
                            sInsert.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se han podido actualizar los datos.");
                        }

                        break;

                    case 10:
                        final String sqlQuery = "SELECT CL.NOMBRE,CL.APELLIDOS,CH.MARCA FROM CLIENTE CL,COCHE CH WHERE CH.MARCA ='SMART' AND CL.DNI = CH.DNI_CLIENTE";

                        try {
                            rs = s.executeQuery(sqlQuery);
                            i = 1;
                            while (rs.next()) {
                                JOptionPane.showMessageDialog(null,
                                        "[" + (i++) + "]" +
                                                "\nNombre: " + rs.getString("NOMBRE") +
                                                "\nApellidos: " + rs.getString("APELLIDOS") +
                                                "\nMarca:" + rs.getString("MARCA")
                                );
                            }
                            rs.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error: No se ha podido ejecutar la consulta.");
                        }
                        break;

                    case 0:
                        JOptionPane.showMessageDialog(null, "¡Adios!");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Número no reconocido");
                        break;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: SQL");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha conseguido conectar con la BBDD");
            e.printStackTrace();
        }
    }

    private static void sqlCreate(Statement s, String sqlCli) {
        try {
            s.execute(sqlCli);
            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha podido crear la tabla.");
        }
    }
}