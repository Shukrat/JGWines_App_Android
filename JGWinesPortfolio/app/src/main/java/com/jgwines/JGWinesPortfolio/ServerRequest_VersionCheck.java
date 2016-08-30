package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Shukrat on 8/29/2016.
 */
public class ServerRequest_VersionCheck extends AsyncTask<String, Void, String> {
    private Exception exception;
    private Context mContext;
    Helper_JSONReader_Singleton jsonReader_singleton;

    public ServerRequest_VersionCheck(Context context){
        mContext = context;
        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
    }

    protected String doInBackground(String... strings) {
        String text = "";
        String checkFor = strings[0];

        try {
            String url = "http://10.0.2.2:8080/" + checkFor + ".json";
            URL myurl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");

            }
            conn.disconnect();

            JSONObject temp = new JSONObject(sb.toString());
            int version = temp.getInt("version");
            if(jsonReader_singleton.getJSONObjFromFile("version").getInt("version") != version){
                downloadUpdatedContent();
            }
            else{ Log.v("INFORMATION", "Versions Match, doing nothing");}
        } catch (IOException|JSONException e){
            e.printStackTrace();
        }

        return text;
    }

    private void downloadUpdatedContent(){
        Log.v("INFORMATION", "Version mismatch, downloading new content");
        String[] downloads = {"regions", "tastings", "version", "wines"};
        for(int i = 0; i < downloads.length; i++){
            String tempRequest = downloads[i];


        }
    }

    protected void onPostExecute(String thing) {
        // TODO: check this.exception
        // TODO: do something with the feed

    }
}
