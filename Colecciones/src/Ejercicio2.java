
import net.sf.saxon.s9api.*;
public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        Processor processor = new Processor(false);
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XQueryExecutable executable1 = compiler.compile("doc('colecciones.xml')//Libro[Publicacion = 2002]/Titulo order by Titulo");
        XQueryExecutable executable2 = compiler.compile("doc('colecciones.xml')//Libro[count(Autores/Autor) > 1]/Titulo");
        XQueryExecutable executable3 = compiler.compile("for $prestamo in doc('colecciones.xml')//Prestamo\nreturn concat($prestamo/Libro/Titulo/text(), ' - ', $prestamo/Libro/Autores/Autor[1]/text(), ' - ', $prestamo/Libro/Paginas/text())");
        XQueryExecutable executable4 = compiler.compile("for $lector in distinct-values(doc('colecciones.xml')//Lector)\nreturn concat($lector, ' - ', sum(doc('colecciones.xml')//Prestamo[Lector = $lector]/Libro/Paginas))");

        XQueryEvaluator evaluator1 = executable1.load();
        XQueryEvaluator evaluator2 = executable2.load();
        XQueryEvaluator evaluator3 = executable3.load();
        XQueryEvaluator evaluator4 = executable4.load();

        // Ejecutar consulta 1
        XdmValue result1 = evaluator1.evaluate();
        System.out.println("Libros publicados en 2002, ordenados alfabéticamente:");
        for (XdmItem item : result1) {
            System.out.println(item.getStringValue());
        }
        System.out.println();

        // Ejecutar consulta 2
        XdmValue result2 = evaluator2.evaluate();
        System.out.println("Libros con más de un autor:");
        for (XdmItem item : result2) {
            System.out.println(item.getStringValue());
        }
        System.out.println();

        // Ejecutar consulta 3
        XdmValue result3 = evaluator3.evaluate();
        System.out.println("Información de libros prestados:");
        for (XdmItem item : result3) {
            System.out.println(item.getStringValue());
        }
        System.out.println();

        // Ejecutar consulta 4
        XdmValue result4 = evaluator4.evaluate();
        System.out.println("Total de páginas de libros prestados por cada lector:");
        for (XdmItem item : result4) {
            System.out.println(item.getStringValue());
        }
        System.out.println();
    }
}
