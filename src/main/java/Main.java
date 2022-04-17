import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    private static final String REMOTE_SERVICE_URL = "https://api.nasa.gov/planetary/apod?api_key=";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        String token = "vBnsVZosMJgCzV897tsZr8nsj7LPOsMJXPNYqlwn";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URL + token);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            NASA nasaToDay = mapper.readValue(response.getEntity().getContent(), new TypeReference<NASA>() {
            });
            String[] url = nasaToDay.getUrl().split("/");
            String fileName = url[url.length - 1];
            downloadFileNasa(nasaToDay.getUrl(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFileNasa(String url, String fileName) {
        Path path = Path.of("./src/main/resources/NASAFiles/" + fileName);

        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
