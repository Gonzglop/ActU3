import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LeerConfig {
    static String usuario = "";
    static String password = "";
    static String servidor = "";
    static int puerto = 0;
    static String bd = "";
    static String url = "";

    public static void main(String[] args) {

        generaRutaConex();

        //System.out.printf("%s%n%s%n%s%n%d",usuario,password,servidor,puerto);
    }

    public static String generaRutaConex() {
        Properties configuracion = new Properties();

        try {
            configuracion.load(new FileInputStream("configuracion.props"));
            usuario = configuracion.getProperty("user");
            password = configuracion.getProperty("password");
            servidor = configuracion.getProperty("server");
            puerto = Integer.parseInt(configuracion.getProperty("port"));

            bd = "concesionario";
            url = "jdbc:mysql://"+servidor+":"+puerto+"/"+bd;

        } catch ( FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch ( IOException ioe) {
            ioe.printStackTrace();
        }
        return url;
    }
}