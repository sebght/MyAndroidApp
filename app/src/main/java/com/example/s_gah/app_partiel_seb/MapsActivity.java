package com.example.s_gah.app_partiel_seb;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitude;
    Double longitude;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        latitude = getIntent().getDoubleExtra("latitude",0.0);
        longitude = getIntent().getDoubleExtra("longitude",0.0);
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_maps);
        TextView txt = (TextView) findViewById(R.id.title_maps);
        String urlString = "https://static.thenounproject.com/png/628760-200.png";
        ImageView image1=(ImageView) findViewById(R.id.img1);
        ImageView image2=(ImageView) findViewById(R.id.img2);
        ImageView image3=(ImageView) findViewById(R.id.img3);
        Picasso.get().load(urlString).into(image1);
        Picasso.get().load(urlString).into(image2);
        Picasso.get().load(urlString).into(image3);
        txt.setText(title);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng customLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(customLocation).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(customLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),10.0f));
    }
}
