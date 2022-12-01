import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {
    
    private ServerSocket serverSocket;
    
    public SocketTCPServer (int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("(Servidor) Esperando conexiones...");

        while (true) {
            Socket socket = serverSocket.accept();
            new GestorProcesos(socket).empezar();
        }
    }

    public static void main(String[] args) {
        try {
            SocketTCPServer servidor = new SocketTCPServer(49171);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}