package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shukrat on 8/26/2016.
 */
public class Adapter_WinesinRegion extends RecyclerView.Adapter<Adapter_WinesinRegion.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView wineinRegionTitle;
        public TextView wineinRegionVineyard;
        public Button wineinRegionButton;
        private Context mContext;
        private String tag;

        public ViewHolder(View itemView, Context context){
            super(itemView);
            mContext = context;

            wineinRegionTitle = (TextView) itemView.findViewById(R.id.wineName_AllWinesList);
            wineinRegionVineyard = (TextView) itemView.findViewById(R.id.wineVineyard_AllWinesList);
            wineinRegionButton = (Button) itemView.findViewById(R.id.detailsButton_AllWinesList);
            wineinRegionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            tag = (String) wineinRegionButton.getTag();
            Intent i = new Intent(mContext, Activity_WineDisplay.class);
            i.putExtra("key", tag);
            mContext.startActivity(i);
        }
    }

    private JSONObject winesJSON;
    private JSONArray key;
    private Context mContext;
    private ArrayList<String> wineinJSON = new ArrayList<String>();
    private ArrayList<String> wineTitles = new ArrayList<String>();
    private ArrayList<String> wineVineyards = new ArrayList<String>();

    public Adapter_WinesinRegion(JSONObject _allWinesJSON, String region, Context context){
        mContext = context;
        try{
            key = _allWinesJSON.getJSONArray("key");
            winesJSON = _allWinesJSON.getJSONObject("wines");

            for(int i = 0; i < key.length(); i++){
                String wine = key.getString(i);
                if(winesJSON.getJSONObject(wine).get("region").equals(region)){
                    wineinJSON.add(wine);
                    wineTitles.add((String) winesJSON.getJSONObject(wine).get("title"));
                    wineVineyards.add((String) winesJSON.getJSONObject(wine).get("vineyard"));
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public Adapter_WinesinRegion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newView = inflater.inflate(R.layout.item_wine, parent, false);
        return new ViewHolder(newView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_WinesinRegion.ViewHolder viewHolder, int position){
            String wineNameinJSON = wineinJSON.get(position);
            String title = wineTitles.get(position);
            String vineyard = wineVineyards.get(position);

            TextView wineName = viewHolder.wineinRegionTitle;
            TextView vineyardName = viewHolder.wineinRegionVineyard;
            Button details = viewHolder.wineinRegionButton;
            wineName.setText(title);
            vineyardName.setText(vineyard);
            details.setTag(wineNameinJSON);
    }

    @Override
    public int getItemCount(){ return wineTitles.size(); }
}
