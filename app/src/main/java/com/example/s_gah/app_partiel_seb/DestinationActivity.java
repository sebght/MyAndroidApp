package com.example.s_gah.app_partiel_seb;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DestinationActivity extends ListActivity {

    List<Destination> mesDestinations = new ArrayList<Destination>();
    Double distance;
    String media;
    String title;
    Double latitude;
    Double longitude;
    String webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonReceived = getIntent().getStringExtra("data");
        mesDestinations.clear();
        webview="www.lequipe.fr";
        latitude=43.14554197717752;
        longitude=6.00246207789143;
        try{
            JSONObject jsonObjectReceived = new JSONObject(jsonReceived);
            JSONArray myPlaces= jsonObjectReceived.getJSONArray("data");
            Log.i("Pass_parametre",myPlaces.getJSONObject(0).getString("type") + mesDestinations.toString());
            for (int i=0; i<myPlaces.length(); i++){
                String myImageUrl= "";
                Double myDistance=0.0;
                if(myPlaces.getJSONObject(i).getString("type").contains("POI")){
                    Log.i("Pass_parametre","POI" + mesDestinations.toString());
                    if (myPlaces.getJSONObject(i).has("distance")){
                        myDistance= myPlaces.getJSONObject(i).getDouble("distance");
                    }
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    if (myPlaces.getJSONObject(i).getJSONObject("location").getJSONObject("coords").length()!=0) {
                        latitude= myPlaces.getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                        longitude=myPlaces.getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                    }
                    mesDestinations.add(new Destination(myImageUrl,myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myDistance,latitude,longitude,webview));
                }
                else if(myPlaces.getJSONObject(i).getString("type").contains("RESTAURANT")){
                    Log.i("Pass_parametre","Restaurant" + mesDestinations.toString());
                    if (myPlaces.getJSONObject(i).has("distance")){
                        myDistance= myPlaces.getJSONObject(i).getDouble("distance");
                    }
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    if (myPlaces.getJSONObject(i).getString("web").length()!=0 && myPlaces.getJSONObject(i).getString("web")!= null) {
                        webview= myPlaces.getJSONObject(i).getString("web");
                    }
                    mesDestinations.add(new Destination(myImageUrl,myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myDistance, latitude,longitude,webview));
                }
                else if(myPlaces.getJSONObject(i).getString("type").contains("HOTEL")){
                    Log.i("Pass_parametre","Hotel" + mesDestinations.toString());
                    if (myPlaces.getJSONObject(i).has("distance")){
                        myDistance= myPlaces.getJSONObject(i).getDouble("distance");
                    }
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    if (myPlaces.getJSONObject(i).getString("web").length()!=0 && myPlaces.getJSONObject(i).getString("web")!= null) {
                        webview= myPlaces.getJSONObject(i).getString("web");
                    }
                    mesDestinations.add(new Destination(myImageUrl,myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myDistance, latitude,longitude,webview));
                }
                else if(myPlaces.getJSONObject(i).getString("type").contains("CITY")){
                    if (myPlaces.getJSONObject(i).has("distance")){
                        myDistance= myPlaces.getJSONObject(i).getDouble("distance");
                    }
                    if (myPlaces.getJSONObject(i).getString("media").length()!=0 && myPlaces.getJSONObject(i).getString("media")!= null) {
                        myImageUrl= myPlaces.getJSONObject(i).getString("media");
                    }
                    else {
                        myImageUrl = "https://static.thenounproject.com/png/628760-200.png";
                    }
                    mesDestinations.add(new Destination(myImageUrl,myPlaces.getJSONObject(i).getString("type"),myPlaces.getJSONObject(i).getString("display"),
                            myDistance, latitude,longitude,webview));
                }
            }
        }catch(JSONException e){
            Log.i("Pass_parametre", "CECI NE DEVRAIT PAS ARRIVER");
            e.printStackTrace();
        }
        ListView myListView = getListView();
        DestinationAdapter adapter = new DestinationAdapter(this, mesDestinations);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination map = (Destination) getListView().getItemAtPosition(position);
                webview=map.getWebview();
                title=map.getTitre();
                switch (map.getType()){
                    case "POI":
                        Intent i = new Intent(DestinationActivity.this,MapsActivity.class);
                        i.putExtra("latitude",latitude);
                        i.putExtra("longitude",longitude);
                        i.putExtra("title",title);
                        startActivity(i);
                        break;
                    case "CITY":
                        break;
                    default:
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(webview));
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
