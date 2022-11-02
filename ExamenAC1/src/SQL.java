public class SQL {
    final static String tableCliente = "CREATE TABLE CLIENTE(" +
            "DNI CHAR(9) NOT NULL," +
            "NOMBRE VARCHAR(50) NOT NULL," +
            "APELLIDOS VARCHAR(50) NOT NULL," +
            "DIRECCION VARCHAR(50) ," +
            "EMAIL VARCHAR(50) ," +
            "TELEFONO VARCHAR(9) ," +
            "PRIMARY KEY(DNI)" +
            ");";
    final static String tableCoche = "CREATE TABLE COCHE(" +
            "MATRICULA CHAR(7) NOT NULL," +
            "MODELO VARCHAR(50) NOT NULL," +
            "MARCA VARCHAR(50) NOT NULL," +
            "COLOR VARCHAR(50)," +
            "DNI_CLIENTE CHAR(9)," +
            "PRIMARY KEY(MATRICULA)," +
            "FOREIGN KEY FK_DNI_CLIENTES (DNI_CLIENTE) REFERENCES CLIENTE (DNI)" +
            ");";
    final static String insertClientes = "INSERT INTO CLIENTE(DNI,NOMBRE,APELLIDOS,DIRECCION,EMAIL,TELEFONO) VALUES (?,?,?,?,?,?)";

    final static String insertCoches = "INSERT INTO COCHE(MATRICULA,MODELO,MARCA,COLOR,DNI_CLIENTE) VALUES (?,?,?,?,?)";

    final static String queryCliente = "SELECT * FROM CLIENTE";

    final static String queryCoche = "SELECT * FROM COCHE";

    final static String updateCliente = "UPDATE CLIENTE SET APELLIDOS = 'López' WHERE DNI = '54320198V'";

    final static String updateClientePs = "UPDATE CLIENTE SET TELEFONO = '611460677' WHERE DNI = ?";

    final static String updateCochePs = "UPDATE COCHE SET MARCA = 'SMART' WHERE MATRICULA = ?";

    final static String queryComp = "SELECT CL.NOMBRE,CL.APELLIDOS,CH.MARCA FROM CLIENTE CL,COCHE CH WHERE CH.MARCA ='SMART' AND CL.DNI = CH.DNI_CLIENTE";
}
