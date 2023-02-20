import net.sf.saxon.s9api.*;

import javax.xml.transform.Source;

public class Ejercicio3 {

    public static void main(String[] args) throws Exception {
        Processor processor = new Processor(false);
        XQueryCompiler compiler = processor.newXQueryCompiler();

        // Insertar el nodo evaluacion y cuyo contenido sea 10 como último nodo en la publicacion=2007
        XQueryExecutable executable1 = compiler.compile("let $doc := doc('colecciones.xml')" +
                "for $publicacion in $doc//Publicacion[. = 2007]" +
                "return insert node <evaluacion>10</evaluacion> into $publicacion");

        // Sustituir el valor del nodo paginas con 700 en la publicacion=2005
        XQueryExecutable executable2 = compiler.compile("let $doc := doc('colecciones.xml')\n" +
                "for $publicacion in $doc//Publicacion[. = 2005]\n" +
                "return replace value of node $publicacion/../Paginas with 700");

        // Modificar el nombre del nodo inicio de cada documento de la colección Prestamos por fechainicio.
        XQueryExecutable executable3 = compiler.compile("let $doc := doc('colecciones.xml')\n" +
                "for $prestamo in $doc//Prestamo\n" +
                "let $fechaInicio := $prestamo/Inicio\n" +
                "return rename node $fechaInicio as 'fechainicio'");

        // Eliminar el nodo direccion de la tabla Prestamos
        XQueryExecutable executable4 = compiler.compile("let $doc := doc('colecciones.xml')\n" +
                "for $direccion in $doc//Prestamo/Direccion\n" +
                "return delete node $direccion");

        XQueryEvaluator evaluator1 = executable1.load();
        XQueryEvaluator evaluator2 = executable2.load();
        XQueryEvaluator evaluator3 = executable3.load();
        XQueryEvaluator evaluator4 = executable4.load();

        // Ejecutar modificación 1
        evaluator1.evaluate();

        // Ejecutar modificación 2
        evaluator2.evaluate();

        // Ejecutar modificación 3
        evaluator3.evaluate();

        // Ejecutar modificación 4
        evaluator4.evaluate();

        // Mostrar resultado después de aplicar modificaciones
        XQueryExecutable query = compiler.compile("doc('colecciones.xml')");
        XQueryEvaluator evaluator = query.load();
        XdmValue result = evaluator.evaluate();
        Serializer serializer = processor.newSerializer();
        serializer.setOutputProperty(Serializer.Property.METHOD, "xml");
        serializer.setOutputProperty(Serializer.Property.INDENT, "yes");
        serializer.setOutputStream(System.out);
        serializer.serialize((Source) result);
    }
}
