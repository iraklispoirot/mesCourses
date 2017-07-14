package com.mescourses.appli.mescourses.Gps;

/**
 * Created by student on 14-07-17.
 */
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RequestGeocoding extends AsyncTask<String, Void, Position> {
    private static final String URL_BASE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String URL_KEY = "AIzaSyDn89a5ErfMuBXIn-ZaRSllyKHTlAHyNjg";
    private String URL_address;


    //region Callback
    public interface IRequestGeocodage {
        void receiveGeocodage(int id, Position position);
    }

    //private Context contextLiv;
    private IRequestGeocodage callback;
    //private Activity      myActivity ;

    public void setCallback(IRequestGeocodage callback) {
        this.callback = callback;
    }
    //endregion

    @Override
    protected Position doInBackground(String... params) {
        if (params == null || params.length != 1) {
            return null;
        }

        String query = params[0].replace(" ", "+");
        String request = URL_BASE + query + "&key=" + URL_KEY;

        HttpURLConnection connect = null;
        try {
            URL url = new URL(request);

            connect = (HttpURLConnection) url.openConnection();


            int responseCode = connect.getResponseCode();
            if (responseCode != 200) {
                Log.e("CONNECT", "Error : " + responseCode);
                return null;
            }
            InputStreamReader input = new InputStreamReader(connect.getInputStream());
            BufferedReader reader = new BufferedReader(input);

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            JSONObject reponse = new JSONObject(data.toString());
            JSONArray results = reponse.getJSONArray("results");
            JSONObject result = results.getJSONObject(0);
            JSONObject geometry = result.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double latitude = location.getDouble("lat");
            double longitude = location.getDouble("lng");
            Log.e("TESTPOSITION", "lat=" + latitude);
            Log.e("TESTPOSITION", "lng=" + longitude);

            return new Position(latitude,longitude);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                connect.disconnect();
            }
        }

        return null;
    }



}
