package org.ivanplehanov.nasa.api.download;

import org.ivanplehanov.nasa.api.entity.InformationObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component("downloader")
public class DownloaderImpl implements Downloader{

    public void downloadFileNasa(InformationObject nasaObject) {
        String[] url = nasaObject.getUrl().split("/");
        String fileName = url[url.length - 1];
        Path path = Path.of("./src/main/resources/NASAFiles/" + fileName);

        try (InputStream in = new URL(nasaObject.getUrl()).openStream()) {
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
