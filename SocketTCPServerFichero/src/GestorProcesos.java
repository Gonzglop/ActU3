import java.io.*;
import java.net.Socket;

public class GestorProcesos extends Thread {

    private final Socket socket;
    private InputStream is;
    private OutputStream os;
    //Objetos específicos para el envío y recepción de cadenas de caracteres
    private DataInputStream dis;
    private DataOutputStream dos;

    public GestorProcesos(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {
        try {
            empezar();
            abrirCanalesDeTexto();
            //Recepción de la ruta
            String mensajeRecibido = leerRuta();
            //leer archivo
            StringBuilder texto = leerFichero(mensajeRecibido);
            //Envío del contenido del fichero
            if (texto!=null){
                enviarContenidoFichero(texto.toString());
            }
            cerrarCanalesDeTexto();
            parar();
        } finally {
            os.close();
        }
    }

    public void empezar() {
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("(Servidor) Conexión establecida.");
    }

    public void parar() throws IOException {
        System.out.println("(Servidor) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }

    public void abrirCanalesDeTexto(){
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

    public static StringBuilder leerFichero(String rutaRecibida){

        StringBuilder texto = null;
        try {
            File f = new File(rutaRecibida);//declara fichero
            System.out.println("(Servidor) Leyendo fichero...");
            BufferedReader lectorFichero = new BufferedReader(new FileReader(f));
            texto = new StringBuilder();
            String linea;

            while ((linea = lectorFichero.readLine()) != null) {
                texto.append(linea).append("\n");
            }
            lectorFichero.close();
            System.out.println("(Servidor) Fichero leído.");

        } catch (IOException e) {
            System.err.println("(Servidor) Archivo no encontrado.");
        }
        return texto;
    }
}
