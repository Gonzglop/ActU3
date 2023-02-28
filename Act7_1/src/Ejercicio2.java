import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class Ejercicio2 {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLLECTION = "/db/colecciones";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final String QUERY1 = "for $book in //Libro[Ano=2002] order by $book/Titulo return $book/Titulo";
    private static final String QUERY2 = "//Libro[count(Autores/Autor) > 1]/Titulo";
    private static final String QUERY3 = "//Prestamo/Libro/Titulo | //Prestamo/Libro/Autores/Autor[1]/Nombre | //Prestamo/Libro/Paginas";
    private static final String QUERY4 = "for $reader in //Lector let $pages := sum(//Prestamo[Lector=$reader]/Libro/Paginas) return concat($reader, ': ', $pages)";

    public static void main(String[] args) throws Exception {

        // Registra el driver de la base de datos
        Class cl = Class.forName(DatabaseImpl.class.getName());
        DatabaseManager.registerDatabase((org.xmldb.api.Database) cl.newInstance());

        // Conecta con la colección
        Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
        col.setProperty(OutputKeys.INDENT, "yes");

        // Ejecuta la primera consulta
        executeQuery(col, QUERY1, "libros_publicados_2002.txt");

        // Ejecuta la segunda consulta
        executeQuery(col, QUERY2, "libros_con_mas_de_un_autor.txt");

        // Ejecuta la tercera consulta
        executeQuery(col, QUERY3, "libros_prestados.txt");

        // Ejecuta la cuarta consulta
        executeQuery(col, QUERY4, "paginas_por_lector.txt");

        // Cierra la conexión
        col.close();
    }

    private static void executeQuery(Collection col, String query, String fileName) throws Exception {
        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");
        ResourceSet result = service.query(query);
        Resource res = result.getResource(0);
        XMLResource xmlRes = (XMLResource) res;
        String content = (String) xmlRes.getContent();
        Files.write(Paths.get(fileName), content.getBytes());
        System.out.println("Resultado de la consulta guardado en " + fileName);
    }
}
