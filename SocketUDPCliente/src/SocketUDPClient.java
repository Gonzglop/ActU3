import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SocketUDPClient {

    public static void main(String[] args) {
        final int PUERTO_SERVIDOR = 49171;
        DatagramSocket socketUDP;
        DatagramPacket mensaje;
        ByteArrayOutputStream baos;
        DataOutputStream dos;

        try {
            System.out.println("Iniciado el cliente UDP");
            socketUDP = new DatagramSocket();

            System.out.println("Estableciendo parámetros de conexión...");
            InetAddress hostServidor = InetAddress.getByName("localhost");

            System.out.println("Enviando información....");
            for (int i = 0; i < 10000; i++) {
                baos = new ByteArrayOutputStream();
                dos = new DataOutputStream(baos);

                dos.writeUTF("Mensaje: " + i);
                mensaje = new DatagramPacket(baos.toByteArray(), baos.size(), hostServidor, PUERTO_SERVIDOR);
                socketUDP.send(mensaje);
                dos.close();

            }
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeUTF("FIN");
            mensaje = new DatagramPacket(baos.toByteArray(), baos.size(), hostServidor, PUERTO_SERVIDOR);
            socketUDP.send(mensaje);
            dos.close();

            socketUDP.close();
            System.out.println("Cerrando el cliente UDP");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
