import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private InputStreamReader isr;
    private BufferedInputStream bis;
    private DataInputStream dis;


    
    public SocketTCPServer (int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }
    
    public void start() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        bis = new BufferedInputStream(is);
        dis = new DataInputStream(bis);
        System.out.println("(Servidor) Conexión establecida.");
    }
    
    public void stop() throws IOException {
        System.out.println("(Servidor) Cerrando conexiones...");
        dis.close();
        bis.close();
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }
      
    public static void main(String[] args) {
        try {
            SocketTCPServer servidor = new SocketTCPServer(49171);
            servidor.start();
            System.out.println("Mensaje del cliente: " + servidor.dis.read());
            servidor.os.write(200);
            servidor.stop();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    } 
}
