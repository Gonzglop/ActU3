import org.exist.util.DatabaseConfigurationException;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.XMLDBException;
import org.exist.xmldb.EXistResource;

public class XPathExample {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java XPathExample collection-path xpath-query");
            return;
        }

        String collectionPath = args[0];
        String xpathQuery = args[1];

        try {
            // initialize database driver
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            // access collection and execute XPath query
            Collection col = null;
            try {
                col = DatabaseManager.getCollection(URI + collectionPath);
                XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                xpqs.setProperty("indent", "yes");
                ResourceSet result = xpqs.query(xpathQuery);
                ResourceIterator i = result.getIterator();
                while (i.hasMoreResources()) {
                    Resource res = null;
                    try {
                        res = i.nextResource();
                        System.out.println(res.getContent());
                    } finally {
                        // cleanup resource
                        if (res != null) {
                            try {
                                ((EXistResource) res).freeResources();
                            } catch (XMLDBException xe) {
                                xe.printStackTrace();
                            }
                        }
                    }
                }
            } finally {
                // cleanup collection
                if (col != null) {
                    try {
                        col.close();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException  e) {
            e.printStackTrace();
        }
    }
}
