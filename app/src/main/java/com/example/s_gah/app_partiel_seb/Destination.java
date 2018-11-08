package com.example.s_gah.app_partiel_seb;

public class Destination {
    private String titre;
    private String distance;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String auteur) {
        this.distance = distance;
    }

    public Destination(String titre, String distance) {
        this.titre = titre;
        this.distance = distance;
    }
}
