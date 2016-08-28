package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/27/2016.
 */
public class Adapter_Tastings extends RecyclerView.Adapter<Adapter_Tastings.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tastingDate;
        public TextView tastingTime;
        public TextView tastingLocation;

        public ViewHolder (View itemView){
            super(itemView);

            tastingDate = (TextView) itemView.findViewById(R.id.tastingDate);
            tastingTime = (TextView) itemView.findViewById(R.id.tastingTime);
            tastingLocation = (TextView) itemView.findViewById(R.id.tastingLocation);
        }
    }

    private JSONObject tastingsJSON;
    private JSONArray tastingsKey;

    public Adapter_Tastings(JSONObject _tastingsJSON){
        try{
            tastingsKey = _tastingsJSON.getJSONArray("key");
            tastingsJSON = _tastingsJSON.getJSONObject("tastings");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public Adapter_Tastings.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mView = inflater.inflate(R.layout.item_tasting, parent, false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(Adapter_Tastings.ViewHolder viewHolder, int position){
        TextView mTastingDate = viewHolder.tastingDate;
        TextView mTastingTime = viewHolder.tastingTime;
        TextView mTastingLocation = viewHolder.tastingLocation;

        try {
            String key = tastingsKey.getString(position);

            mTastingDate.setText(tastingsJSON.getJSONObject(key).getString("date"));
            mTastingLocation.setText(tastingsJSON.getJSONObject(key).getString("location"));
            mTastingTime.setText(tastingsJSON.getJSONObject(key).getString("time"));
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){ return tastingsKey.length(); }
}
