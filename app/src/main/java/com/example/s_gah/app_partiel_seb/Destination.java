package com.example.s_gah.app_partiel_seb;

import java.util.HashMap;

public class Destination {
    private String image;
    private String type;
    private String titre;
    private Double distance;
    private Double latitude;
    private Double longitude;
    private String webview;

    public String getWebview() {
        return webview;
    }

    public void setWebview(String webview) {
        this.webview = webview;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String titre) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Destination(String image, String type,String titre, Double distance,Double latitude,Double longitude, String webview) {
        this.image = image;
        this.type = type;
        this.titre = titre;
        this.distance = distance;
        this.latitude=latitude;
        this.longitude=longitude;
        this.webview=webview;
    }
}
