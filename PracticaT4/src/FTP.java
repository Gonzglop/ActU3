import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class FTP {
    public void subirArchivo(String server, int port, String user, String password, String remoteFilePath, String localFilePath) {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);

            File localFile = new File(localFilePath);
            InputStream inputStream = new FileInputStream(localFile);

            System.out.println("Iniciando subida de archivo...");

            boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
            if (done) {
                System.out.println("Archivo subido con éxito");
            } else {
                System.out.println("Error al subir archivo");
            }
            inputStream.close();
            ftpClient.logout();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
