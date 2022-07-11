package org.ivanplehanov.nasa.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InformationObject {

    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public InformationObject(@JsonProperty("copyright") String copyright,
                             @JsonProperty("date") String date,
                             @JsonProperty("explanation") String explanation,
                             @JsonProperty("hdurl") String hdurl,
                             @JsonProperty("media_type") String media_type,
                             @JsonProperty("service_version") String service_version,
                             @JsonProperty("title") String title,
                             @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public InformationObject() {
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "entity.NASA to day" +
                "\ncopright = " + copyright +
                "\ndate = " + date +
                "\nexplantion='" + explanation +
                "\nhdurl='" + hdurl +
                "\nmedia_type='" + media_type +
                "\nservice_version='" + service_version +
                "\ntitle='" + title +
                "\nurl='" + url;
    }
}
