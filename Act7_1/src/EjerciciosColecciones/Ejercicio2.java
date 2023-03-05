package EjerciciosColecciones;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Ejercicio2 {

    public static void ejecutaFicheroXQ(String ficheroXQ) throws IOException {
        String url = "http://localhost:8080/exist/rest/db/colecciones/" + ficheroXQ;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String xmlContent = EntityUtils.toString(entity);
        System.out.println(xmlContent);

        EntityUtils.consume(entity);
        response.close();
        client.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Consulta 1:");
        ejecutaFicheroXQ("consulta1.xq");
        
        System.out.println("\nConsulta 2:");
        ejecutaFicheroXQ("consulta2.xq");
        
        System.out.println("\nConsulta 3:");
        ejecutaFicheroXQ("consulta3.xq");
        
        System.out.println("\nConsulta 4:");
        ejecutaFicheroXQ("consulta4.xq");
    }
}
