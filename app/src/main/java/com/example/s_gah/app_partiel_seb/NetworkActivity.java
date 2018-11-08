package com.example.s_gah.app_partiel_seb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkActivity extends AppCompatActivity {

    public String TAG = "NetworkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                loadFlux();
            }
        });
        background.start();
    }

    private void loadFlux(){
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL("http://voyage2.corellis.eu/api/v2/homev2?lat=43.14554197717751&lon=6.00.246207789145&offset=0");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            String line = convertStreamToString(in);
            //JSONArray jsonArray = new JSONArray(line);
            JSONObject jsonObject = new JSONObject(line);
            Log.i(TAG,"Number of entries "+jsonObject.length());
            Log.i(TAG,"Le JSON : "+jsonObject);
        }
        catch (MalformedURLException e){
            Log.e(TAG,'1'+e.toString());
        }
        catch (IOException e){
            Log.e(TAG,'2'+e.toString());
        }
        catch (Exception e){
            Log.e(TAG,'3'+e.toString());
        }
        finally {
            urlConnection.disconnect();
        }
    }

    private String convertStreamToString(InputStream is){
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try{
            while ((line = rd.readLine()) != null){
                total.append(line);
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Stream Exception",Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }
}