import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Ejercicio1 {

    public static void main(String[] args) throws Exception {
        // Cargar el archivo XML
        File inputFile = new File("colecciones.xml");
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(inputFile);

        // Extraer los nodos de cada colección
        Element biblioteca = document.getRootElement();
        Element libros = biblioteca.getChild("libros");
        Element prestamos = biblioteca.getChild("prestamos");
        List<Element> autores = libros.getChildren("libro");

        // Guardar cada libro en un archivo
        int contador = 1;
        for (Element libro : libros.getChildren("libro")) {
            String nombreArchivo = "libro" + contador + ".xml";
            guardarArchivo(libro, nombreArchivo);
            contador++;
        }

        // Guardar cada préstamo en un archivo
        contador = 1;
        for (Element entrada : prestamos.getChildren("entrada")) {
            String nombreArchivo = "prestamo" + contador + ".xml";
            guardarArchivo(entrada, nombreArchivo);
            contador++;
        }

        // Guardar cada autor en un archivo
        contador = 1;
        for (Element autor : autores) {
            String nombreArchivo = "autor" + contador + ".xml";
            // Crear un nuevo elemento con los datos del autor
            Element autorNuevo = new Element("autor");
            autorNuevo.addContent(autor.getChild("nombre").clone());
            autorNuevo.addContent(autor.getChild("nacionalidad").clone());
            autorNuevo.addContent(autor.getChild("fechaNacimiento").clone());

            // Guardar el nuevo elemento en un archivo
            guardarArchivo(autorNuevo, nombreArchivo);
            contador++;
        }
    }

    // Método para guardar un nodo en un archivo
    private static void guardarArchivo(Element nodo, String nombreArchivo) throws Exception {
        Document documento = new Document(nodo);
        FileOutputStream salida = new FileOutputStream(nombreArchivo);
        org.jdom2.output.XMLOutputter xmlOutput = new org.jdom2.output.XMLOutputter();
        xmlOutput.setFormat(org.jdom2.output.Format.getPrettyFormat());
        xmlOutput.output(documento, salida);
    }
}