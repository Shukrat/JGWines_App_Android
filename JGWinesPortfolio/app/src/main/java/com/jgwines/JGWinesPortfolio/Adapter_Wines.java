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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shukrat on 8/25/2016.
 */
public class Adapter_Wines extends RecyclerView.Adapter<Adapter_Wines.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wineTextView;
        public final TextView vineyardTextView;
        public final LinearLayout wineItemLayout;
        public final ImageView wineColor;
        private final Context mContext;
        private String tag;

        public ViewHolder(final View itemView, Context context) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mContext = context;

            wineTextView = (TextView) itemView.findViewById(R.id.wineName_AllWinesList);
            vineyardTextView = (TextView) itemView.findViewById(R.id.wineVineyard_AllWinesList);
            wineColor = (ImageView) itemView.findViewById(R.id.wineColor_WineItem);
            wineItemLayout = (LinearLayout) itemView.findViewById(R.id.wineItemLayout);
            wineItemLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            tag = (String) wineItemLayout.getTag();
            Intent i = new Intent(mContext, Activity_WineDisplay.class);
            i.putExtra("key", tag);
            mContext.startActivity(i);
        }
    }

    private JSONObject winesJSON;
    private ArrayList<String> winesToDisplay;
    private final Context mContext;

    public Adapter_Wines(JSONObject _allWinesJSON, ArrayList<String> winesToDisplay, Context context){
        mContext = context;
        this.winesToDisplay = winesToDisplay;
        try {
            winesJSON = _allWinesJSON.getJSONObject("wines");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public Adapter_Wines.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wineView = inflater.inflate(R.layout.item_wine, parent, false);

        return new ViewHolder(wineView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_Wines.ViewHolder viewHolder, int position) {
        try {
            String key = winesToDisplay.get(position);

            TextView wineName = viewHolder.wineTextView;
            TextView vineyardName = viewHolder.vineyardTextView;
            LinearLayout wineItemLayout = viewHolder.wineItemLayout;
            ImageView wineColor = viewHolder.wineColor;

            if(key != null) {
                wineName.setText(winesJSON.getJSONObject(key).getString("title"));
                vineyardName.setText(winesJSON.getJSONObject(key).getString("vineyard"));
                wineItemLayout.setTag(key);

                // Set color of the dot next to each wine item
                String type = winesJSON.getJSONObject(key).getString("type");
                switch (type) {
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
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return winesToDisplay.size();
    }
}
