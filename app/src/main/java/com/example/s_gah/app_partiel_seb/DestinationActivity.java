package com.example.s_gah.app_partiel_seb;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonReceived = getIntent().getStringExtra("data");
        mesDestinations.clear();
        try{
            JSONObject jsonObjectReceived = new JSONObject(jsonReceived);
            Log.i("Pass_parametre","Le JSON: "+jsonObjectReceived);
            for (int i=0;i<jsonObjectReceived.getJSONArray("data").length();i++){
                String type = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("type");
                title = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("display");
                if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("distance")){
                    distance = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getDouble("distance");
                    if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("media")){
                        media = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("media");
                        if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                            latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                            longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                        }else latitude=1.1;
                    }
                    else {
                        media="https://i.imgur.com/SWM3zmL.jpg";
                        if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                            latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                            longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                        }else latitude=1.1;
                    }
                    mesDestinations.add(new Destination(media,type,title,distance,latitude,longitude));
                    Log.i("Pass_parametre",media+" . "+type+" . "+title+" . "+distance.toString());
                }
                else {
                    if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("media")){
                        media = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("media");
                        if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                            latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                            longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                        }else latitude=1.1;
                    }
                    else {
                        media="https://i.imgur.com/SWM3zmL.jpg";
                        if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                            latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                            longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                        }else latitude=1.1;
                    }
                    mesDestinations.add(new Destination(media,type,title,0.0,latitude,longitude));
                    Log.i("Pass_parametre",media+" . "+type+" . "+title+" . no distance available");
                }
            }
            Log.i("Pass_parametre",mesDestinations.toString());
        }
        catch (Exception e){
            Log.e("Pass_parametre",e.toString());
        }
        ListView myListView = getListView();
        DestinationAdapter adapter = new DestinationAdapter(this, mesDestinations);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination map = (Destination) getListView().getItemAtPosition(position);
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
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lequipe.fr"));
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
