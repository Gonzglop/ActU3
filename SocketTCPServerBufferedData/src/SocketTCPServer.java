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

    public String leerMensajeDeTexto() throws IOException {
        System.out.println("(Servidor) Leyendo mensaje...");
        String mensaje = dis.readUTF();
        System.out.println("(Servidor) Mensaje leído.");
        return mensaje;
    }

    public void enviarMensajeDeTexto(String mensaje) throws IOException {
        System.out.println("(Servidor) Enviando mensaje...");
        dos.writeUTF(mensaje);
        System.out.println("(Servidor) Mensaje enviado.");
    }
      
    public static void main(String[] args) {
        try {

            SocketTCPServer servidor = new SocketTCPServer(49171);
            servidor.start();
            servidor.abrirCanalesDeTexto();
            //Recepción del mensaje del cliente
            String mensajeRecibido = servidor.leerMensajeDeTexto();
            System.out.println("(Servidor) Mensaje recibido: " + mensajeRecibido);
            //Envío del mensaje al cliente
            servidor.enviarMensajeDeTexto("Mensaje enviado desde el servidor");
            servidor.cerrarCanalesDeTexto();
            servidor.stop();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }
    } 
}
