package EjerciciosColecciones;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;

public class Ejercicio3 {

    private static Collection obtenColeccion(String nomCol) throws Exception {
        DatabaseImpl dbDriver;
        Collection col;
        dbDriver = (DatabaseImpl) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
        DatabaseManager.registerDatabase(dbDriver);
        col = DatabaseManager.getCollection(
                "xmldb:exist://localhost:8080/exist/xmlrpc/db" + nomCol,
                "admin", "admin");
        return col;
    }

    public static void ejecutaFicheroXQ(String ficheroXQ) throws IOException {
        String url = "http://localhost:8080/exist/rest/db/colecciones/" + ficheroXQ;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String xmlContent = EntityUtils.toString(entity);
        System.out.println(xmlContent);

        EntityUtils.consume(entity);
        response.close();
        client.close();
    }

    public static void consultar(XPathQueryService serv, String consulta) throws XMLDBException, FileNotFoundException, TransformerConfigurationException, TransformerException, IOException {
        ResourceSet resultSet = serv.query(consulta);
        ResourceIterator iter = resultSet.getIterator();
        while (iter.hasMoreResources()) {
            XMLResource res = (XMLResource) iter.nextResource();
            // Obtener el contenido del documento como una cadena de texto
            String xmlContent = res.getContent().toString();
            // Imprimir el contenido en la consola
            System.out.println(xmlContent);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Ejecutando modificación 1:");
        ejecutaFicheroXQ("modificacion1.xq");
        
        System.out.println("\nEjecutando modificación 2:");
        ejecutaFicheroXQ("modificacion2.xq");
        
        System.out.println("\nEjecutando modificación 3:");
        ejecutaFicheroXQ("modificacion3.xq");
        
        System.out.println("\nEjecutando modificación 4:");
        ejecutaFicheroXQ("modificacion4.xq");

        Collection col = null;
        try {

            col = obtenColeccion("/colecciones");
            XPathQueryService serv = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            System.out.println("Comprobación 1:");
            consultar(serv, "//libro[@publicacion ='2007']");

            System.out.println("\nComprobación 2:");
            consultar(serv, "//libro[@publicacion='2005']/paginas");
            
            System.out.println("\nComprobación 3:");
            consultar(serv, "//fechainicio");
            
            System.out.println("\nComprobación 4:");
            consultar(serv, "//lector");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

}
