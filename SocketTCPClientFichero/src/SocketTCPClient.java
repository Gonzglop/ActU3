import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketTCPClient {

    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    //Objetos específicos para el envío y recepción de cadenas de caracteres
    private DataInputStream dis;
    private DataOutputStream dos;
    
    public SocketTCPClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Estableciento conexión...");
        socket = new Socket(serverIP, serverPort);
        os = socket.getOutputStream();
        is = socket.getInputStream();
        System.out.println("(Cliente) Conexión establecida.");
    }

    public void stop() throws IOException {
        System.out.println("(Cliente) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Conexiones cerradas.");
    }

    public void abrirCanalesDeTexto() throws IOException {
        System.out.println("(Cliente) Abriendo canales de texto...");
        //Canales de lectura
        dis = new DataInputStream(is);
        //Canales de escritura
        dos = new DataOutputStream(os);
        System.out.println("(Cliente) Canales de texto abiertos.");
    }

    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(Cliente) Cerrando canales de texto...");
        //Canales de lectura
        dis.close();
        //Canales de escritura
        dos.close();
        System.out.println("(Cliente) Canales de texto cerrados.");
    }

    public String leerContenidoFichero() throws IOException {
        System.out.println("(Cliente) Leyendo mensaje...");
        String mensaje = dis.readUTF();
        System.out.println("(Cliente) Mensaje leído.");
        return mensaje;
    }

    public String solicitarNombreFichero() throws IOException {
        System.out.println("(Cliente) Solicitando información al usuario...");

        String directorio = "C:\\Users\\usuario\\Desktop";
        String archivo = JOptionPane.showInputDialog("Introduce el nombre" +
                " de un archivo ubicado en la siguiente ruta: " + directorio);

        System.out.println("(Cliente) Información recibida.");

        return directorio+"\\"+archivo;
    }

    public void enviarRuta(String mensaje) throws IOException {
        System.out.println("(Cliente) Enviando mensaje...");
        dos.writeUTF(mensaje);
        System.out.println("(Cliente) Mensaje enviado.");
    }
    public static void main(String[] args) {
        SocketTCPClient cliente = new SocketTCPClient("127.0.0.1",49171);
        try {

            cliente.start();
            cliente.abrirCanalesDeTexto();
            //solicita ruta archivo al usuario
            String ruta = cliente.solicitarNombreFichero();
            //envía ruta al servidor
            cliente.enviarRuta(ruta);
            //recepción y lectura del contenido del fichero
            String contenidoFichero = cliente.leerContenidoFichero();
            //imprime el contenido del fichero por pantalla
            System.out.println("(Cliente) CONTENIDO DEL ARCHIVO:\n\n" + contenidoFichero);
            cliente.cerrarCanalesDeTexto();
            cliente.stop();

        }catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
