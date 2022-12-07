import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SocketUDPServer {

    public static void main(String[] args) {
        final int PUERTO = 49171;
        byte[] buffer = new byte[256];
        DatagramSocket socket;
        DatagramPacket mensajeCliente;
        DatagramPacket mensajeFecha;
        ByteArrayInputStream bais;
        DataInputStream dis;
        String strMensajeCliente;
        ByteArrayOutputStream baos;
        DataOutputStream dos;

        try {
            System.out.println("Iniciado el servidor UDP");
            socket = new DatagramSocket(PUERTO);

            System.out.println("Esperando la información del cliente...");
            mensajeCliente = new DatagramPacket(buffer, buffer.length);

            while (true) {

                socket.receive(mensajeCliente);
                bais = new ByteArrayInputStream(mensajeCliente.getData());
                dis = new DataInputStream(bais);
                strMensajeCliente = dis.readUTF();

                System.out.println("Texto recibido: " + strMensajeCliente);

                if (strMensajeCliente.equals("CLAVE")) {

                    Date date = Date.from(Instant.now());
                    System.out.println("Fecha actual:");
                    System.out.println(date);

                    baos = new ByteArrayOutputStream();
                    dos = new DataOutputStream(baos);
                    dos.writeUTF(date.toString());
                    mensajeFecha = new DatagramPacket(baos.toByteArray(), baos.size(), mensajeCliente.getAddress(), mensajeCliente.getPort());
                    socket.send(mensajeFecha);
                    dos.close();
                }else {
                    System.out.println("Texto clave incorrecto, no se devolverá la fecha al cliente.");
                }
            }
            //socket.close();
            //System.out.println("Cerrando el servidor UDP");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
    }
}
