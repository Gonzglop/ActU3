import org.exist.xmldb.XmldbURI;
import org.exist.xmldb.DatabaseInstanceManager;
import org.exist.xmldb.DatabaseInstanceManagerImpl;
import org.exist.xmldb.XQueryService;
import org.exist.xmldb.XMLResource;
import org.exist.xmldb.XMLResourceUpdater;
import org.exist.collections.Collection;
import org.exist.storage.DBBroker;
import org.xmldb.api.base.*;
import java.io.File;

public class Ejercicio3 {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String COLLECTION = "/db/colecciones/";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) throws Exception {

        // Registra el driver de la base de datos
        Class cl = Class.forName(DRIVER);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // Conecta con la colección
        Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);

        // Actualiza la publicacion=2007 para insertar un nuevo nodo evaluacion
        String consulta1 = "update insert <evaluacion>10</evaluacion> as last into /colecciones/Libros/Libro[publicacion=2007]";
        XQueryService servicio1 = (XQueryService) col.getService("XQueryService", "1.0");
        ResourceSet resultado1 = servicio1.query(consulta1);
        System.out.println("Resultado de la consulta 1: " + resultado1.getSize() + " recursos actualizados");

        // Actualiza la publicacion=2005 para cambiar el valor del nodo paginas
        String consulta2 = "update value /colecciones/Libros/Libro[publicacion=2005]/paginas with 700";
        XQueryService servicio2 = (XQueryService) col.getService("XQueryService", "1.0");
        ResourceSet resultado2 = servicio2.query(consulta2);
        System.out.println("Resultado de la consulta 2: " + resultado2.getSize() + " recursos actualizados");

        // Actualiza el nombre del nodo inicio a fechainicio en cada documento de la colección Prestamos
        String consulta3 = "update rename /colecciones/Prestamos/*:inicio as 'fechainicio'";
        XQueryService servicio3 = (XQueryService) col.getService("XQueryService", "1.0");
        ResourceSet resultado3 = servicio3.query(consulta3);
        System.out.println("Resultado de la consulta 3: " + resultado3.getSize() + " recursos actualizados");

        // Elimina el nodo direccion de la tabla Prestamos
        String consulta4 = "update delete /colecciones/Prestamos/*:direccion";
        XQueryService servicio4 = (XQueryService) col.getService("XQueryService", "1.0");
        ResourceSet resultado4 = servicio4.query(consulta4);
        System.out.println("Resultado de la consulta 4: " + resultado4.getSize() + " recursos actualizados");

        // Cierra la conexión con la colección
        col.close();
    }
}