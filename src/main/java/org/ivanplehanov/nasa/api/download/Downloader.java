package org.ivanplehanov.nasa.api.download;

import org.ivanplehanov.nasa.api.entity.InformationObject;

public interface Downloader {

    void downloadFileNasa(InformationObject nasaObject);

}
