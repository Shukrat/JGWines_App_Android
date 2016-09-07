package com.jgwines.JGWinesPortfolio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity_WineDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_display);


        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Helper_JSONReader_Singleton jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        jsonReader_singleton.setContext(this);
        // Use helper to get json object and array from "wines" file
        JSONObject jsonObject = jsonReader_singleton.getJSONObjFromFile("wines");
        JSONArray jsonArray = jsonReader_singleton.getJSONArrayFromJSONObject(jsonObject, "wineDetailsKey");

        TextView wineTitle = (TextView) this.findViewById(R.id.wineName_WineDisplay);
        TextView vineyard = (TextView) this.findViewById(R.id.vineyardName_WineDisplay);
        ImageView label = (ImageView) this.findViewById(R.id.labelImage_WineDisplay);

        Helper_ServerImageRequest imageRequest = new Helper_ServerImageRequest(label);

        // Set jsonObject to the wine to be displayed - narrow down information being passed
        // Set content for Title, Vineyard, and Label Image
        try {
            jsonObject = jsonObject.getJSONObject("wines").getJSONObject(key);
            wineTitle.setText(jsonObject.getString("title"));
            vineyard.setText(jsonObject.getString("vineyard"));
            imageRequest.execute(jsonObject.getString("label_image"));
        } catch (JSONException e){
            e.printStackTrace();
        }

        // Create RecyclerView panels for wine information from Tasting Notes thru Photos
        RecyclerView wineDetailsRecycler = (RecyclerView) this.findViewById(R.id.rvWineDisplay);
        Adapter_WineDetails winesAdapter = new Adapter_WineDetails(jsonObject, jsonArray);
        RecyclerView.LayoutManager winesLayoutManager = new LinearLayoutManager(getApplicationContext());
        wineDetailsRecycler.setAdapter(winesAdapter);
        wineDetailsRecycler.setLayoutManager(winesLayoutManager);
    }
}
