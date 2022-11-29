import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    //Objetos específicos para el envío y recepción de cadenas de caracteres
    private DataInputStream dis;
    private DataOutputStream dos;
    
    public SocketTCPServer (int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }
    
    public void start() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(Servidor) Conexión establecida.");
    }
    
    public void stop() throws IOException {
        System.out.println("(Servidor) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }

    public void abrirCanalesDeTexto() throws IOException {
        System.out.println("(Servidor) Abriendo canales de texto...");
        //Canales de lectura
        dis = new DataInputStream(is);
        //Canales de escritura
        dos = new DataOutputStream(os);
        System.out.println("(Servidor) Canales de texto abiertos.");
    }

    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(Servidor) Cerrando canales de texto...");
        //Canales de lectura
        dis.close();
        //Canales de escritura
        dos.close();
        System.out.println("(Servidor) Canales de texto cerrados.");
    }

    public String leerRuta() throws IOException {
        System.out.println("(Servidor) Leyendo ruta...");
        String mensaje = dis.readUTF();
        System.out.println("(Servidor) Ruta leída.");
        System.out.println("(Servidor) RUTA RECIBIDA: " + mensaje);
        return mensaje;
    }

    public void enviarContenidoFichero(String mensaje) throws IOException {
        System.out.println("(Servidor) Enviando contenido del fichero...");
        dos.writeUTF(mensaje);
        System.out.println("(Servidor) Contenido del fichero enviado.");
    }

    public static StringBuilder leerFichero(String rutaRecibida) throws IOException {

        File f = new File(rutaRecibida);//declara fichero
        System.out.println("(Servidor) Leyendo fichero...");

        BufferedReader lectorFichero = new BufferedReader(new FileReader(f));

        StringBuilder texto = new StringBuilder("");
        String linea;

        while ((linea = lectorFichero.readLine()) != null){
            //System.out.println(linea);
            texto.append(linea+"\n");
        }

        lectorFichero.close();
        System.out.println("(Servidor) Fichero leído.");

        return texto;
    }


    public static void main(String[] args) {
        try {

            SocketTCPServer servidor = new SocketTCPServer(49171);
            servidor.start();
            servidor.abrirCanalesDeTexto();
            //Recepción de la ruta
            String mensajeRecibido = servidor.leerRuta();
            //leer archivo
            StringBuilder texto = leerFichero(mensajeRecibido);
            //Envío del contenido del fichero
            servidor.enviarContenidoFichero(texto.toString());
            servidor.cerrarCanalesDeTexto();
            servidor.stop();

        }catch (FileNotFoundException fn ){
            System.out.println("No se encuentra el fichero");
            fn.printStackTrace();

        } catch (IOException ioe) {
            System.out.println("Error de E/S ");
        ioe.printStackTrace();
        }
    }


}

/*
Desarrollar una aplicación en Java que permita leer un fichero de texto ubicado en otro ordenador a través de sockets. Los pasos del proceso serán los siguientes:
- Programa cliente: solicita al usuario el nombre de un fichero incluyendo su ruta completa dentro del sistema de archivos del servidor.
- Programa cliente: envía el nombre y la ruta del fichero al servidor.
- Programa servidor: lee el contenido del fichero y se lo envía al cliente.
- Programa cliente: muestra el contenido por pantalla.
 */