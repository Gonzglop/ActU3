
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import org.exist.xmldb.EXistResource;

public class App {

    private static final String URI = "admin@xmldb:exist://localhost:8080/eXist/xmlrpc";
    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String COLLECTION = "/db/colecciones/";
    private static final String RESOURCE = "colecciones.xml";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) throws Exception {

        // Registra el driver de la base de datos
        Class cl = Class.forName(DRIVER);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // Conecta con la colección y obtiene el recurso
        Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
        XMLResource res = (XMLResource) col.getResource(RESOURCE);

        // Imprime el contenido del recurso
        System.out.println(res.getContent());

        // Cierra la conexión
        col.close();
    }
}
