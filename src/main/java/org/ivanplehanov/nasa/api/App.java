package org.ivanplehanov.nasa.api;

import org.ivanplehanov.nasa.api.configuration.MyConfig;
import org.ivanplehanov.nasa.api.download.Downloader;
import org.ivanplehanov.nasa.api.download.DownloaderImpl;
import org.ivanplehanov.nasa.api.entity.InformationObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Downloader downloader = context.getBean("downloader", Downloader.class);
        Communication communication = context.getBean("communication", Communication.class);
        InformationObject nasaToDay = communication.getNasaInformationObject();

        downloader.downloadFileNasa(nasaToDay);

    }
}
