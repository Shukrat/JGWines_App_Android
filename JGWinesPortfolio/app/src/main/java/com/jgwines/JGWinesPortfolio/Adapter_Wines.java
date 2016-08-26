package com.jgwines.JGWinesPortfolio;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/25/2016.
 */
public class Adapter_Wines extends RecyclerView.Adapter<Adapter_Wines.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView wineTextView;
        public TextView vineyardTextView;
        public Button detailsButton;
        private Context mContext;
        private String tag;

        public ViewHolder(final View itemView, Context context) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mContext = context;

            wineTextView = (TextView) itemView.findViewById(R.id.wineName_AllWinesList);
            vineyardTextView = (TextView) itemView.findViewById(R.id.wineVineyard_AllWinesList);
            detailsButton = (Button) itemView.findViewById(R.id.detailsButton_AllWinesList);
            detailsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            tag = (String) detailsButton.getTag();
            Intent i = new Intent(mContext, Activity_WineDisplay.class);
            i.putExtra("key", tag);
            mContext.startActivity(i);
        }
    }

    private JSONObject winesJSON;
    private JSONArray allWinesKey;
    private Context mContext;

    public Adapter_Wines(JSONObject _allWinesJSON, Context context){
        mContext = context;
        try {
            allWinesKey = _allWinesJSON.getJSONArray("key");
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
            String key = allWinesKey.getString(position);

            TextView wineName = viewHolder.wineTextView;
            TextView vineyardName = viewHolder.vineyardTextView;
            Button details = viewHolder.detailsButton;
            wineName.setText(winesJSON.getJSONObject(key).getString("title"));
            vineyardName.setText(winesJSON.getJSONObject(key).getString("vineyard"));
            details.setTag(key);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return allWinesKey.length();
    }
}
