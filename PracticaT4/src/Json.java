import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
public class Json {
    public void gestionaJson(String json) throws Exception {
        // Parseo del JSON
        JSONObject jsonObject = new JSONObject(json);
        JSONArray hitsArray = jsonObject.getJSONArray("hits");
        // Último objeto del array
        JSONObject lastHit = hitsArray.getJSONObject(hitsArray.length() - 1);
        // URL de la última imagen
        String imageUrl = lastHit.getString("largeImageURL");
        // Descarga de la imagen
        int length;
        byte[] buffer = new byte[2048];
        URL urlImage = new URL(imageUrl);
        InputStream inputStream = urlImage.openStream();
        FileOutputStream fileOutputStream = new FileOutputStream("imagenPrueba.jpg");//G:\Mi unidad\imagen.jpg
        while ((length = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, length);
        }
        inputStream.close();
        fileOutputStream.close();
    }
}