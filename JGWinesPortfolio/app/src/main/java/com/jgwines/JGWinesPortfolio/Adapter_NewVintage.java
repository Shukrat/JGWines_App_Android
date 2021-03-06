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

/**
 * Created by Shukrat on 8/26/2016.
 */
public class Adapter_NewVintage extends RecyclerView.Adapter<Adapter_NewVintage.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView newVintageTitle;
        public final TextView newVintageVineyard;
        public final LinearLayout newVintageLL;
        public final ImageView wineColor;
        private final Context mContext;

        public ViewHolder(View itemView, Context context){
            super(itemView);
            mContext = context;

            newVintageTitle = (TextView) itemView.findViewById(R.id.wineName_AllWinesList);
            newVintageVineyard = (TextView) itemView.findViewById(R.id.wineVineyard_AllWinesList);
            wineColor = (ImageView) itemView.findViewById(R.id.wineColor_WineItem);
            newVintageLL = (LinearLayout) itemView.findViewById(R.id.wineItemLayout);
            newVintageLL.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            String key = (String) newVintageLL.getTag();
            Intent i = new Intent(mContext, Activity_WineDisplay.class);
            i.putExtra("key", key);
            mContext.startActivity(i);
        }
    }

    private JSONObject winesJSON;
    private JSONArray newWines;
    private final Context mContext;

    public Adapter_NewVintage(JSONObject _allWinesJSON, Context context){
        mContext = context;
        try{
            newWines = _allWinesJSON.getJSONArray("new");
            winesJSON = _allWinesJSON.getJSONObject("wines");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public Adapter_NewVintage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newView = inflater.inflate(R.layout.item_wine, parent, false);

        return new ViewHolder(newView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_NewVintage.ViewHolder viewHolder, int position){
        try{
            String key = newWines.getString(position);

            LinearLayout newVintage = viewHolder.newVintageLL;
            TextView wineName = viewHolder.newVintageTitle;
            TextView vineyardName = viewHolder.newVintageVineyard;
            ImageView wineColor = viewHolder.wineColor;

            wineName.setText(winesJSON.getJSONObject(key).getString("title"));
            vineyardName.setText(winesJSON.getJSONObject(key).getString("vineyard"));
            newVintage.setTag(key);

            String type = winesJSON.getJSONObject(key).getString("type");
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
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){ return newWines.length(); }
}
