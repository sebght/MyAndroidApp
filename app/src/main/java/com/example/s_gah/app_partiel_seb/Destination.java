package com.example.s_gah.app_partiel_seb;

public class Destination {
    private String type;
    private String titre;
    private Double distance;

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

    public Destination(String type,String titre, Double distance) {
        this.type = type;
        this.titre = titre;
        this.distance = distance;
    }
}
