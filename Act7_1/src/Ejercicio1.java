import org.exist.xmldb.CollectionManagementServiceImpl;
import org.exist.xmldb.EXistResource;
import org.exist.xmldb.XmldbURI;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;

import java.io.File;
import java.io.FileWriter;

public class Ejercicio1 {
    
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLLECTION = "/db/colecciones";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final String OUTPUT_DIR = "./output/";

    public static void main(String[] args) throws Exception {
        
        // Crea el directorio de salida si no existe
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
        
        // Conecta con la colección
        Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
        
        // Extrae las colecciones 'Libros', 'Prestamos' y 'Autores'
        String[] collectionNames = new String[] { "Libros", "Prestamos", "Autores" };
        for (String collectionName : collectionNames) {
            String xquery = "collection('" + collectionName + "')";
            ResourceSet result = col.getResource(xquery);
            int count = result.getSize();
            for (int i = 0; i < count; i++) {
                Resource res = result.getResource(i);
                String fileName = OUTPUT_DIR + collectionName.toLowerCase() + i + ".xml";
                System.out.println("Guardando " + res.getId() + " en " + fileName);
                XMLResource xmlRes = (XMLResource) res;
                File outputFile = new File(fileName);
                FileWriter writer = new FileWriter(outputFile);
                writer.write((String) xmlRes.getContent());
                writer.close();
            }
        }
        
        // Cierra la conexión
        col.close();
    }
}
