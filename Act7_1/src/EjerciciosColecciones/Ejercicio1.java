package EjerciciosColecciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.base.XMLDBException;

public class Ejercicio1 {

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

    public static void importarDocumentos(XPathQueryService serv, String carpeta, String consulta, String prefijo) throws XMLDBException, FileNotFoundException, TransformerConfigurationException, TransformerException, IOException {
        ResourceSet resultSet = serv.query(consulta);

        File librosDir = new File(carpeta);
        if (!librosDir.exists()) {
            librosDir.mkdir();
        }

        int i = 1;
        ResourceIterator iter = resultSet.getIterator();
        while (iter.hasMoreResources()) {
            XMLResource res = (XMLResource) iter.nextResource();
            String fileName = prefijo + i + ".xml";
            File file = new File(librosDir, fileName);
            FileOutputStream out = new FileOutputStream(file);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(res.getContentAsDOM());
            StreamResult streamResult = new StreamResult(out);
            transformer.transform(source, streamResult);
            out.close();
            i++;
        }
    }

    public static void main(String[] args) {
        Collection col = null;
        try {
            col = obtenColeccion("/colecciones");
            XPathQueryService serv = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            importarDocumentos(serv, "XML", "/", "coleccion");
            importarDocumentos(serv, "Libros", "//libros/*", "libro");
            importarDocumentos(serv, "Prestamos", "//prestamos/*", "prestamo");
            importarDocumentos(serv, "Autores", "//autor", "autor");

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
