import javax.swing.*;
import java.io.*;
import java.net.*;

public class SocketUDPClient {

    public static void main(String[] args) {

        final int PUERTO_SERVIDOR = 49171;
        byte[] buffer = new byte[256];
        InetAddress hostServidor;
        DatagramSocket socketUDP;
        DatagramPacket mensaje;
        DatagramPacket mensajeServidor;
        ByteArrayOutputStream baos;
        DataOutputStream dos;
        ByteArrayInputStream bais;
        DataInputStream dis;
        String clave;
        String strMensajeServidor;
        int timeout;


        try {
            System.out.println("Iniciado el cliente UDP");
            socketUDP = new DatagramSocket();
            timeout = 5000;
            socketUDP.setSoTimeout(timeout);

            System.out.println("Estableciendo parámetros de conexión...");
            hostServidor = InetAddress.getByName("localhost");

            System.out.println("Enviando información....");
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            System.out.println("Pidiendo palabra clave al usuario....");
            clave = JOptionPane.showInputDialog("Introduce la palabra clave:");

            dos.writeUTF(clave);
            mensaje = new DatagramPacket(baos.toByteArray(), baos.size(), hostServidor, PUERTO_SERVIDOR);

            System.out.println("Enviando palabra clave.");
            socketUDP.send(mensaje);
            dos.close();

            mensajeServidor = new DatagramPacket(buffer, buffer.length);
            System.out.println("Recibiendo mensaje del Servidor.");
            socketUDP.receive(mensajeServidor);
            System.out.println("Recibido mensaje del Servidor.");

            bais = new ByteArrayInputStream(mensajeServidor.getData());
            dis = new DataInputStream(bais);
            strMensajeServidor = dis.readUTF();
            System.out.println("Texto recibido: " + strMensajeServidor);

            socketUDP.close();
            System.out.println("Cerrando el cliente UDP");

        } catch (SocketTimeoutException se) {
            System.err.println("Se ha excedido el tiempo de espera.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
