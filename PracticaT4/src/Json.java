import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
public class Json {
    public void downloadImage(String json) {

        try {

            // Parseo del JSON
            JSONObject jsonObject = new JSONObject(json);
            JSONArray hitsArray = jsonObject.getJSONArray("hits");
            // Último objeto del array
            JSONObject lastHit = hitsArray.getJSONObject(hitsArray.length() - 1);
            // URL de la última imagen
            String imageUrl = lastHit.getString("largeImageURL");

            // Descarga de la imagen
            System.out.println("Iniciando descarga de la imagen...");
            int length;
            byte[] buffer = new byte[2048];
            URL urlImage = new URL(imageUrl);
            InputStream inputStream = urlImage.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream("imagen.jpg");

            while ((length = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }

            inputStream.close();
            fileOutputStream.close();
            System.out.println("Imagen descargada con éxito.");

        } catch (JSONException | IOException e) {
            System.out.println("No se ha podido descargar la imagen.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}