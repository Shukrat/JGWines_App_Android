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
    Helper_ServerImageRequest imageRequest;
    Helper_JSONReader_Singleton jsonReader_singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_display);


        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        jsonReader_singleton.setContext(this);
        // Use helper to get json object and array from "wines" file
        JSONObject jsonObject = jsonReader_singleton.getJSONObjFromFile("wines");
        JSONArray jsonArray = jsonReader_singleton.getJSONArrayFromJSONObject(jsonObject, "wineDetailsKey");

        TextView wineTitle = (TextView) this.findViewById(R.id.wineName_WineDisplay);
        TextView vineyard = (TextView) this.findViewById(R.id.vineyardName_WineDisplay);
        ImageView label = (ImageView) this.findViewById(R.id.labelImage_WineDisplay);
        imageRequest = new Helper_ServerImageRequest(this, label);


        // Set jsonObject to the wine to be displayed - narrow down information being passed
        // Set content for Title, Vineyard, and Label Image
        try {
            jsonObject = jsonObject.getJSONObject("wines").getJSONObject(key);
            wineTitle.setText(jsonObject.getString("title"));
            vineyard.setText(jsonObject.getString("vineyard"));
            //String labelname = jsonObject.getString("label_image");
            imageRequest.execute(jsonObject.getString("label_image"));
            //label.setImageResource(getResources().getIdentifier("com.jgwines.JGWinesPortfolio:drawable/" + labelname, null, null));
        } catch (JSONException e){
            e.printStackTrace();
        }

        // Create RecyclerView panels for wine information from Tasting Notes thru Photos
        RecyclerView wineDetailsRecycler = (RecyclerView) this.findViewById(R.id.rvWineDisplay);
        Adapter_WineDetails winesAdapter = new Adapter_WineDetails(jsonObject, jsonArray);
        RecyclerView.LayoutManager winesLayoutManager = new LinearLayoutManager(getApplicationContext());
        wineDetailsRecycler.setAdapter(winesAdapter);
        wineDetailsRecycler.setLayoutManager(winesLayoutManager);

        //setContent(); // Use parsed information to create wine information (This is incredibly messy)
    }


    /*
    // This section is a mess, but I don't know how to make it not a mess yet
    // Creates a LinearLayout to put all the view in
    // Each view is generated only if the String for that section is anything other than ""
    // With this method, I am just using one activity, but changing its content when it loads
    // This way all that is necessary to update is the json file to add new wines
    public void setContent(){
        LinearLayout wineInfoLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        int ten = pixelConverter.getPixelfromDP(10);
        layoutParams.setMargins(ten, ten, ten, ten);
        wineInfoLayout.setLayoutParams(layoutParams);
        wineInfoLayout.setPadding(ten, ten, ten, ten);
        wineInfoLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView wineInfoScroll = (ScrollView) this.findViewById(R.id.wineInfo_WineDisplay);

        // Add text to Title TextView
        if(!title.equals("")){
            TextView titleTV = (TextView) findViewById(R.id.wineName_WineDisplay);
            titleTV.setText(title);}

        // Add Vineyard TextView
        if(!vineyard.equals("")){
            TextView vineyardTV = (TextView) findViewById(R.id.vineyardName_WineDisplay);
            vineyardTV.setText(vineyard);}

        // Add Label Image
        if(!label_image.equals("")){
            ImageView labelIV = new ImageView(this);
            labelIV.setImageResource(getResources().getIdentifier("com.jgwines.JGWinesPortfolio:drawable/" + label_image, null, null));
            wineInfoLayout.addView(labelIV);}

        // Add Short Description TextView
        if(!short_desc.equals("")){
            TextView short_descTV = new TextView(this);
            short_descTV.setText(short_desc);
            short_descTV.setPadding(5, 5, 5, 5);
            wineInfoLayout.addView(short_descTV);}

        // Add Extended Description TextView
        if(!extended_desc.equals("")){
            TextView extended_descTV = new TextView(this);
            extended_descTV.setText(extended_desc);
            extended_descTV.setPadding(5, 5, 5, 5);
            wineInfoLayout.addView(extended_descTV);}

        // Add Tasting Notes TextView
        if(!tasting_notes.equals("")){
            TextView tasting_notesTV = new TextView(this);
            tasting_notesTV.setText(tasting_notes);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Tasting Notes:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(tasting_notesTV);}

        // Add Region Image
        if(!region_image.equals("")){
            ImageView regionIV = new ImageView(this);
            regionIV.setImageResource(getResources().getIdentifier("com.jgwines.JGWinesPortfolio:drawable/" + region_image, null, null));
            wineInfoLayout.addView(regionIV);}

        // Add Region TextView
        if(!region.equals("")){
            TextView regionTV = new TextView(this);
            regionTV.setText(region);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Region:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(regionTV);}

        // Add Classification TextView
        if(!classification.equals("")){
            TextView classificationTV = new TextView(this);
            classificationTV.setText(classification);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Classification:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(classificationTV);}

        // Add Production Area TextView
        if(!production_area.equals("")){
            TextView production_areaTV = new TextView(this);
            production_areaTV.setText(production_area);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Area of Production:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(production_areaTV);}

        // Add Grape TextView
        if(!grape.equals("")){
            TextView grapeTV = new TextView(this);
            grapeTV.setText(grape);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Grape Variety:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(grapeTV);}

        // Add Soil TextView
        if(!soil.equals("")){
            TextView soilTV = new TextView(this);
            soilTV.setText(soil);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Soil:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(soilTV);}

        // Add Vine Age TextView
        if(!vine_age.equals("")){
            TextView vine_ageTV = new TextView(this);
            vine_ageTV.setText(vine_age);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Average Age of Vines:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(vine_ageTV);}

        // Add Plant Spacing TextView
        if(!plant_spacing.equals("")){
            TextView plant_spacingTV = new TextView(this);
            plant_spacingTV.setText(plant_spacing);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Spacing of Plants:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(plant_spacingTV);}

        // Add Trellis System TextView
        if(!trellis_system.equals("")){
            TextView trellis_systemTV = new TextView(this);
            trellis_systemTV.setText(trellis_system);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Trellising System:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(trellis_systemTV);}

        // Add Production Per Acre TextView
        if(!production_per_acre.equals("")){
            TextView production_per_acreTV = new TextView(this);
            production_per_acreTV.setText(production_per_acre);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Production Per Acre:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(production_per_acreTV);}

        // Add Technique TextView
        if(!technique.equals("")){
            TextView techniqueTV = new TextView(this);
            techniqueTV.setText(technique);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Vinification Technique:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(techniqueTV);}

        // Add Maturation TextView
        if(!maturation.equals("")){
            TextView maturationTV = new TextView(this);
            maturationTV.setText(maturation);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Maturation:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(maturationTV);}

        // Add Acidity TextView
        if(!acidity.equals("")){
            TextView acidityTV = new TextView(this);
            acidityTV.setText(acidity);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Total Acidity:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(acidityTV);}

        // Add pH TextView
        if(!ph.equals("")){
            TextView phTV = new TextView(this);
            phTV.setText(ph);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("pH:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(phTV);}

        // Add Sugar Content TextView
        if(!sugar_content.equals("")){
            TextView sugar_contentTV = new TextView(this);
            sugar_contentTV.setText(sugar_content);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Residual Sugar:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(sugar_contentTV);}

        // Add Alcohol TextView
        if(!alcohol.equals("")){
            TextView alcoholTV = new TextView(this);
            alcoholTV.setText(alcohol);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Alcohol:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(alcoholTV);}

        // Add Cases Produced TextView
        if(!cases_produced.equals("")){
            TextView cases_producedTV = new TextView(this);
            cases_producedTV.setText(cases_produced);

            TextView sectionLabel = new TextView(this);
            sectionLabel.setText("Total Cases Produced:");
            sectionLabel.setTypeface(Typeface.DEFAULT_BOLD);
            wineInfoLayout.addView(sectionLabel);
            wineInfoLayout.addView(cases_producedTV);}

        // Add Photos Images

        wineInfoScroll.addView(wineInfoLayout);
    }
    */
}
