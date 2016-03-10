package com.comet.foursquare_abdulrauf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by abdul on 11/11/15.
 */
public class ForPhotos  extends AsyncTask<String,Bitmap,Void>{

    @Override
    protected Void doInBackground(String... params) {

        String photourl = "https://api.foursquare.com/v2/venues/"+params[0]+"/photos?v=20151110&client_id="+params[1]+"&client_secret="+params[2];

        try{
            String str1;
            StringBuilder stringBuilder1 = new StringBuilder();
            URL url1 = new URL(photourl);
            Bitmap bitmap;
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));

            while ((str1 = reader1.readLine()) != null) {
                stringBuilder1.append(str1 + "\n");
            }

            JSONObject jsonObject10 = new JSONObject(stringBuilder1.toString());

            JSONArray jsonArray1 = jsonObject10.getJSONObject("response").getJSONObject("photos").getJSONArray("items");

            JSONObject json = jsonArray1.getJSONObject(0).getJSONObject("source");

            String prefix = json.getString("prefix");
            String suffix = json.getString("suffix");
            String complURL = prefix + "110x100" +suffix;


            URL url2 = new URL(complURL);
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            InputStream inputStream = connection2.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);

            publishProgress(bitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onProgressUpdate(Bitmap... values) {
        super.onProgressUpdate(values);
    }

}
