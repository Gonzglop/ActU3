import java.sql.*;
import javax.swing.JOptionPane;

public class Menu {

    private static int select = -1; //opción elegida del usuario

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(LeeConfig.generaRutaConex(), LeeConfig.usuario, LeeConfig.password)) {

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
                        Metodos.createTable(c, SQL.tableCliente);
                        break;

                    case 2:
                        Metodos.createTable(c, SQL.tableCoche);
                        break;

                    case 3:
                        Metodos.insert(c, SQL.insertClientes, Datos.arrayClientes);
                        break;

                    case 4:
                        Metodos.insert(c, SQL.insertCoches, Datos.arrayCoches);
                        break;

                    case 5:
                        Metodos.selectFrom(c, SQL.queryCliente);
                        break;

                    case 6:
                        Metodos.selectFrom(c, SQL.queryCoche);
                        break;

                    case 7:
                        Metodos.update(c, SQL.updateCliente);
                        break;

                    case 8:
                        Metodos.updatePS(c, SQL.updateClientePs, Datos.dni);
                        break;

                    case 9:
                        Metodos.updatePS(c, SQL.updateCochePs, Datos.matricula);
                        break;

                    case 10:
                        Metodos.selectFrom(c, SQL.queryComp);
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
}