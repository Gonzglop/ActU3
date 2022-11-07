import java.io.*;
public class EscribeFich {
    public static void escribeDatos(String aux) {
        try{
            BufferedWriter fichero = new BufferedWriter (new FileWriter("resultado.txt"));
            fichero.write(aux);
            fichero.close();
        }
        catch (FileNotFoundException fn ){
            System.out.println("No se encuentra el fichero");}
        catch (IOException io) {
            System.out.println("Error de E/S ");}
    }
}