package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        public final TextView wineinRegionTitle;
        public final TextView wineinRegionVineyard;
        public final LinearLayout wineinRegionLayout;
        public final ImageView wineColor;
        private final Context mContext;
        private String tag;

        public ViewHolder(View itemView, Context context){
            super(itemView);
            mContext = context;

            wineinRegionLayout = (LinearLayout) itemView.findViewById(R.id.wineItemLayout);
            wineinRegionTitle = (TextView) itemView.findViewById(R.id.wineName_AllWinesList);
            wineinRegionVineyard = (TextView) itemView.findViewById(R.id.wineVineyard_AllWinesList);
            wineColor = (ImageView) itemView.findViewById(R.id.wineColor_WineItem);
            wineinRegionLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            tag = (String) wineinRegionLayout.getTag();
            Intent i = new Intent(mContext, Activity_WineDisplay.class);
            i.putExtra("key", tag);
            mContext.startActivity(i);
        }
    }

    private final Context mContext;
    private final ArrayList<String> wineinJSON = new ArrayList<>();
    private final ArrayList<String> wineTitles = new ArrayList<>();
    private final ArrayList<String> wineVineyards = new ArrayList<>();
    private final ArrayList<String> wineTypes = new ArrayList<>();

    public Adapter_WinesinRegion(JSONObject _allWinesJSON, String region, Context context){
        mContext = context;
        try{
            JSONArray key = _allWinesJSON.getJSONArray("key");
            JSONObject winesJSON = _allWinesJSON.getJSONObject("wines");

            for(int i = 0; i < key.length(); i++){
                String wine = key.getString(i);
                if(winesJSON.getJSONObject(wine).get("Region").equals(region)){
                    wineinJSON.add(wine);
                    wineTitles.add((String) winesJSON.getJSONObject(wine).get("title"));
                    wineVineyards.add((String) winesJSON.getJSONObject(wine).get("vineyard"));
                    wineTypes.add((String) winesJSON.getJSONObject(wine).get("type"));
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

        View mView = inflater.inflate(R.layout.item_wine, parent, false);
        return new ViewHolder(mView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_WinesinRegion.ViewHolder viewHolder, int position){
        String wineNameinJSON = wineinJSON.get(position);
        String title = wineTitles.get(position);
        String vineyard = wineVineyards.get(position);
        String type = wineTypes.get(position);

        TextView wineName = viewHolder.wineinRegionTitle;
        TextView vineyardName = viewHolder.wineinRegionVineyard;
        LinearLayout wineinRegionLL = viewHolder.wineinRegionLayout;
        ImageView wineColor = viewHolder.wineColor;

        wineName.setText(title);
        vineyardName.setText(vineyard);
        wineinRegionLL.setTag(wineNameinJSON);

        switch(type){
            case "rose":
                wineColor.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.rosewine));
                break;
            case "white":
                wineColor.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.whitewine));
                break;
            case "red":
                wineColor.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.redwine));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount(){ return wineTitles.size(); }
}
