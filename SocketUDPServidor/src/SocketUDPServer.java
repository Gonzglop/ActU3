import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class SocketUDPServer {

    public static void main(String[] args) {
        final int PUERTO = 49171;
        byte[] buffer = new byte[64];
        DatagramSocket socket;
        DatagramPacket mensajeCliente;
        ByteArrayInputStream bais;
        DataInputStream dis;
        BufferedWriter fichero;
        String strMensajeCliente = "";

        try {
            System.out.println("Iniciado el servidor UDP");
            socket = new DatagramSocket(PUERTO);

            System.out.println("Esperando la información del cliente...");
            mensajeCliente = new DatagramPacket(buffer, buffer.length);

            fichero = new BufferedWriter(new FileWriter("FicheroTexto.txt"));

            while (!strMensajeCliente.equals("FIN")){
                socket.receive(mensajeCliente);
                bais = new ByteArrayInputStream(mensajeCliente.getData());
                dis = new DataInputStream(bais);
                strMensajeCliente = dis.readUTF();

                System.out.println("Texto recibido: " + strMensajeCliente);

                if (!strMensajeCliente.equals("FIN")){
                    fichero.write(strMensajeCliente);
                    fichero.newLine();
                }
            }
            fichero.close();

            socket.close();
            System.out.println("Cerrando el servidor UDP");

        } catch (FileNotFoundException fn ){
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
    }
}
