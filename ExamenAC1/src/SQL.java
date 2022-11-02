public class SQL {
    
    final static String dropTablesNotas = "drop table if EXISTS notas;";
    final static String dropTablesAlumnos ="drop table if EXISTS alumnos;";
    final static String dropTablesAsignaturas ="drop table if EXISTS asignaturas;";

    final static String tableAlumnos = "CREATE TABLE IF NOT EXISTS ALUMNOS ( DNI VARCHAR(10) NOT NULL primary key, APENOM VARCHAR(30), DIREC VARCHAR(30), POBLA  VARCHAR(15), TELEF  VARCHAR(10)  ) ;";
    final static String tableAsignaturas = "CREATE TABLE IF NOT EXISTS ASIGNATURAS(  COD int NOT NULL primary key, NOMBRE VARCHAR(25)) ;";
    final static String tableNotas ="CREATE TABLE IF NOT EXISTS NOTAS(  DNI VARCHAR(10) NOT NULL ,COD int NOT NULL ,NOTA int,primary key(DNI,COD));";

    /* Create Foreign Keys */
    final static String alterTableDni = "ALTER TABLE NOTAS ADD CONSTRAINT FKNOTASALUM FOREIGN KEY (DNI) REFERENCES ALUMNOS (DNI)	ON UPDATE CASCADE ON DELETE RESTRICT;";

    final static String alterTableCod ="ALTER TABLE NOTAS ADD CONSTRAINT FKNOTASASIG FOREIGN KEY (COD) REFERENCES ASIGNATURAS (COD) ON UPDATE CASCADE ON DELETE RESTRICT;";

    
    /* Rellenar Datos */
    final static String insertDatos1 = "INSERT IGNORE INTO ASIGNATURAS VALUES (1,'Prog. Leng. Estr.');";
    final static String insertDatos2 ="INSERT IGNORE INTO ASIGNATURAS VALUES (2,'Sist. Informáticos');";
    final static String insertDatos3 ="INSERT IGNORE INTO ASIGNATURAS VALUES (3,'Análisis');";
    final static String insertDatos4 ="INSERT IGNORE INTO ASIGNATURAS VALUES (4,'FOL');";
    final static String insertDatos5 ="INSERT IGNORE INTO ASIGNATURAS VALUES (5,'RET');";

    final static String insertDatos6 ="INSERT IGNORE INTO ALUMNOS VALUES ('12344345','Alcalde García, Elena', 'C/Las Matas, 24','Madrid','917766545');";

    final static String insertDatos7 ="INSERT IGNORE INTO ALUMNOS VALUES('4448242','Cerrato Vela, Luis', 'C/Mina 28 - 3A', 'Madrid','916566545');";

    final static String insertDatos8 ="INSERT  IGNORE INTO ALUMNOS VALUES('56882942','Díaz Fernández, María', 'C/Luis Vives 25', 'Móstoles','915577545');";

    final static String insertDatos9 ="INSERT IGNORE INTO NOTAS VALUES('12344345', 1,6);";
    final static String insertDatos10 ="INSERT IGNORE INTO NOTAS VALUES('12344345', 2,5);";

    final static String insertDatos11 ="INSERT IGNORE INTO NOTAS VALUES('4448242', 4,6);";
    final static String insertDatos12 ="INSERT IGNORE INTO NOTAS VALUES('4448242', 5,8);";

    final static String insertDatos13 ="INSERT IGNORE INTO NOTAS VALUES('56882942', 1,8);";
    final static String insertDatos14 ="INSERT IGNORE INTO NOTAS VALUES('56882942', 3,7);";

    /* Consultas */

    final static String queryAlumnos = "SELECT * FROM ALUMNOS";
    final static String queryNotas = "SELECT AL.DNI, AL.APENOM, A.NOMBRE, N.NOTA FROM ALUMNOS AL, ASIGNATURAS A, NOTAS N WHERE AL.DNI = N.DNI AND A.COD = N.COD;";
    final static String queryComp = "SELECT A.NOMBRE AS 'NOMBRE ASIGNATURA', COUNT(AL.DNI) AS 'ALUMNOS MATRICULADOS',\n" +
            " FORMAT((select count(*) from NOTAS where NOTA>=5 and cod=A.COD)*100/COUNT(N.NOTA),'P') AS 'APROBADOS %', MAX(N.NOTA) AS 'MÁXIMA NOTA'\n" +
            "FROM ALUMNOS AL,ASIGNATURAS A,NOTAS N\n" +
            "WHERE AL.DNI = N.DNI AND A.COD = N.COD\n" +
            "GROUP BY A.COD ;";

    final static String registroNota ="INSERT INTO NOTAS VALUES(?, (SELECT COD  FROM ASIGNATURAS WHERE NOMBRE = (?)),?) ;";
}



