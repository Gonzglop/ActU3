import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loteria extends JFrame {

    public static void main(String[] args) {
        Loteria app = new Loteria();
        app.setVisible(true);
    }

    public Loteria() {
        super("LoteriaApp");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton btnMuestraResumen = new JButton("Mostrar resumen");
        btnMuestraResumen.addActionListener(e -> muestraResumen());
        panel.add(btnMuestraResumen);

        JButton btnCompruebaNumero = new JButton("Comprobar número");
        btnCompruebaNumero.addActionListener(e -> compruebaNumero());
        panel.add(btnCompruebaNumero);

        add(panel);
    }

    private void muestraResumen() {
        String param = "resumen";
        try {
            JsonObject jsonObject = obtieneJson(param);
            // Comprueba si hay error
            if (jsonObject.get("error").getAsInt()==0){
                //Procesa la información obtenida del resumen y la muestra
                muestraResumenProcesadoInfo(jsonObject);
            }else {
                JOptionPane.showMessageDialog(this, "Se ha producido un error.");
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Error obteniendo los resultados.");
        }
    }

    private void compruebaNumero() {
        String param = JOptionPane.showInputDialog(this, "Introduce un número entre 0 y 99999:");
        try {
            JsonObject jsonObject = obtieneJson(param);
            // Comprueba si hay error
            if (jsonObject.get("error").getAsInt()==0){
                //Procesa la información obtenida al comprobar el número y la muestra
                compruebaNumeroProcesadoInfo(jsonObject);
            }else {
                JOptionPane.showMessageDialog(this, "Número introducido no válido.");
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Error obteniendo los resultados.");
        }
    }

    private void muestraResumenProcesadoInfo(JsonObject jsonObject) {
        //Procesa fecha
        String timestamp = obtieneFecha(jsonObject);
        //Procesa estado
        String status = obtieneEstado(jsonObject);
        //Procesa URL de descarga PDF
        String pdfURL = (jsonObject.get("pdfURL").toString()).replace("\"", "");
        // Procesa Premios
        int premio1 = jsonObject.get("premio1").getAsInt();
        int premio2 = jsonObject.get("premio2").getAsInt();
        int premio3 = jsonObject.get("premio3").getAsInt();
        //Procesa extracciones
        String extracciones4cifras = (jsonObject.get("extracciones4cifras").toString()).replace("\"", " ");
        String extracciones3cifras = (jsonObject.get("extracciones3cifras").toString()).replace("\"", " ");
        String extracciones2cifras = (jsonObject.get("extracciones2cifras").toString()).replace("\"", " ");
        String reintegros = jsonObject.get("reintegros").toString();

        String results = "";
        results += "Fecha: "+ timestamp + "\n"
                + "Estado del sorteo: " + status + "\n"
                + "Enlace PDF: " + pdfURL + "\n"
                + "1º Premio: " + premio1 + "\n"
                + "2º Premio: " + premio2 + "\n"
                + "3º Premio: " + premio3 + "\n"
                + "Extracciones de 4 cifras: " + extracciones4cifras + "\n"
                + "Extracciones de 3 cifras: " + extracciones3cifras + "\n"
                + "Extracciones de 2 cifras: " + extracciones2cifras + "\n"
                + "Reintegros: " + reintegros ;

        JOptionPane.showMessageDialog(this, results);
    }

    private void compruebaNumeroProcesadoInfo(JsonObject jsonObject) {
        //Procesa número
        int numero = jsonObject.get("numero").getAsInt();
        //Procesa premio
        int premio = jsonObject.get("premio").getAsInt();
        //Procesa fecha
        String timestamp = obtieneFecha(jsonObject);
        //Procesa estado
        String status = obtieneEstado(jsonObject);

        //Resultados
        String results = "";
        results += "Número: "+ numero + "\n"
                + "Premio: " + premio + "€\n"
                + "Fecha: " + timestamp + "\n"
                + "Estado del sorteo: " + status ;
        JOptionPane.showMessageDialog(this, results);
    }

    private String obtieneEstado(JsonObject jsonObject) {
        int estadoSorteo = jsonObject.get("status").getAsInt();
        return switch (estadoSorteo) {
            case 0 -> "El sorteo no ha comenzado aún. Todos los números aparecerán como no premiados.";
            case 1 -> "El sorteo ha empezado. La lista de números premiados se va cargando poco a poco. Un número premiado podría llegar a tardar unos minutos en aparecer.";
            case 2 -> "El sorteo ha terminado y la lista de números y premios debería ser la correcta aunque, tomada al oído, no podemos estar seguros de ella.";
            case 3 -> "El sorteo ha terminado y existe una lista oficial en PDF.";
            case 4 -> "El sorteo ha terminado y la lista de números y premios está basada en la oficial. De todas formas, recuerda que la única lista oficial es la que publica la ONLAE y deberías comprobar todos tus números contra ella.";
            default -> "Estado del sorteo desconocido";
        };
    }

    private JsonObject obtieneJson(String param) throws IOException, InterruptedException {
        Gson gson = new Gson();

        HttpClient cliente = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.elpais.com/ws/LoteriaNinoPremiados?n=" + param))
                .headers("Content-type", "Text/plain")
                .setHeader("User-Agent", "Mozilla/5.0")
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body().substring(response.body().indexOf('=')+1);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        return jsonObject;
    }

    private String obtieneFecha(JsonObject jsonObject) {
        Date fecha = new Date();
        fecha.setTime(jsonObject.get("timestamp").getAsLong() * 1000);
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z").format(fecha);
        return timestamp;
    }
}