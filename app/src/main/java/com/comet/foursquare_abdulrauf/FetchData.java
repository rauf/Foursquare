package com.comet.foursquare_abdulrauf;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by abdul on 21/11/15.
 */

class FetchData extends AsyncTask<String,CustomListItem,Void> {

    private CustomListAdapter customListAdapter1;
    Context context;

    //self added
    public FetchData(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        customListAdapter1 =  (CustomListAdapter) ((MainActivity)context).listView.getAdapter();
        customListAdapter1.clear();
    }

    @Override
    protected Void doInBackground(String... params) {


        String str ;

        final String CLIENT_ID="FKETQOHIPSZZ1XU4AMN2RQ020ADZDNTT3DKNA1WWVQSELZEW";
        final String CLIENT_PASSWORD="PESWB30LR2QD1L5H4NVWC4VGYRGXOEGGU44MPTO0E1LRXJY4";
        final String Latitude = "28.61";
        final String Longitude ="77.23";
        HttpURLConnection connection = null;
        StringBuilder builder = new StringBuilder();

        String completeURL ="https://api.foursquare.com/v2/venues/search?ll="+Latitude+","+Longitude+"&client_id="+CLIENT_ID+"&client_secret="+CLIENT_PASSWORD+"&v=20151110&limit=20&venuePhotos=1&query="+params[0];
        try {

            URL url = new URL(completeURL);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }

            final JSONObject jsonObject = new JSONObject(builder.toString());

            JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");
            int size = jsonArray.length();

            for (int i = 0; i < size; i++) {

                int checkins = 0;
                String title = "title";
                String address = "No address details";
                String hereNow = "no. of people here";

                JSONObject object = jsonArray.getJSONObject(i);

                //name
                if(object.has("name")) {
                    title = object.getString("name");
                }

                //address in place of description
                if (object.getJSONObject("location").has("formattedAddress")) {
                    JSONArray array = object.getJSONObject("location").getJSONArray("formattedAddress");

                    StringBuilder stringBuilder = new StringBuilder();

                    if(array.get(0) != null) {
                        stringBuilder.append(array.getString(0));
                    }

                    address = stringBuilder.toString();

                }
                //hereNow in place of place
                if(object.has("hereNow")) {
                    hereNow = object.getJSONObject("hereNow").getString("summary");
                }
                //checkin count in place of rating

                if(object.has("stats")) {
                    String s = object.getJSONObject("stats").getString("checkinsCount");
                    checkins = Integer.parseInt(s);
                }

                //id
                final String id = object.getString("id");
                final Bitmap[] bitmap = new Bitmap[1];

                CustomListItem newItem = new CustomListItem(title,address,checkins,hereNow, bitmap[0]);
                publishProgress(newItem);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null)
                connection.disconnect();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(CustomListItem... values) {
        super.onProgressUpdate(values);
        customListAdapter1.add(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
