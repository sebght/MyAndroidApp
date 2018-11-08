package com.example.s_gah.app_partiel_seb;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DestinationActivity extends AppCompatActivity {

    List<Destination> mesDestinations = new ArrayList<Destination>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.RemplirLaBibliotheque();

        ListView myListView = getListView();
        DestinationAdapter adapter = new DestinationAdapter(this, mesDestinations);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void RemplirLaBibliotheque() {
        mesDestinations.clear();
        mesDestinations.add(new Destination("Starcraft 2 : Les diables du ciel", "William-C Dietz"));
        mesDestinations.add(new Destination("L'art du développement Android", "Mark Murphy"));
        mesDestinations.add(new Destination("Le seuil des ténèbres", "Karen Chance"));
        mesDestinations.add(new Destination("Starcraft 2 : Les diables du ciel", "William-C Dietz"));
        mesDestinations.add(new Destination("L'art du développement Android", "Mark Murphy"));
        mesDestinations.add(new Destination("Le seuil des ténèbres", "Karen Chance"));
        mesDestinations.add(new Destination("Starcraft 2 : Les diables du ciel", "William-C Dietz"));
        mesDestinations.add(new Destination("L'art du développement Android", "Mark Murphy"));
        mesDestinations.add(new Destination("Le seuil des ténèbres", "Karen Chance"));
    }
}
