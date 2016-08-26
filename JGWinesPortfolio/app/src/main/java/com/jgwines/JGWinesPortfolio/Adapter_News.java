package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/26/2016.
 */
public class Adapter_News extends RecyclerView.Adapter<Adapter_News.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView newVintageTitle;
        public TextView newVintageVineyard;
        public ImageView newVintageLabel;
        private Context mContext;
        private String tag;

        public ViewHolder(View itemView, Context context){
            super(itemView);
            mContext = context;

            newVintageTitle = (TextView) itemView.findViewById(R.id.wineTitle_News);
            newVintageVineyard = (TextView) itemView.findViewById(R.id.vineyard_News);
            newVintageLabel = (ImageView) itemView.findViewById(R.id.labelImage_News);
        }

        @Override
        public void onClick(View v){

        }
    }

    private JSONObject winesJSON;
    private JSONArray newWines;
    private Context mContext;

    public Adapter_News(JSONObject _allWinesJSON, Context context){
        mContext = context;
        try{
            newWines = _allWinesJSON.getJSONArray("new");
            winesJSON = _allWinesJSON.getJSONObject("wines");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public Adapter_News.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newView = inflater.inflate(R.layout.item_newvintage, parent, false);

        return new ViewHolder(newView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_News.ViewHolder viewHolder, int position){
        try{
            String key = newWines.getString(position);

            TextView wineName = viewHolder.newVintageTitle;
            TextView vineyardName = viewHolder.newVintageVineyard;
            wineName.setText(winesJSON.getJSONObject(key).getString("title"));
            vineyardName.setText(winesJSON.getJSONObject(key).getString("vineyard"));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){ return newWines.length(); }
}
