package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/25/2016.
 */
public class Adapter_Regions extends RecyclerView.Adapter<Adapter_Regions.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView regionTextView;
        public Button regionButton;
        public RecyclerView wineinRegion;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            regionTextView = (TextView) itemView.findViewById(R.id.regionName_RegionsList);
            regionButton = (Button) itemView.findViewById(R.id.regionButton_RegionsList);
            wineinRegion = (RecyclerView) itemView.findViewById(R.id.rvWinesinRegion);
            regionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            if(wineinRegion.getVisibility() == View.GONE) {
                wineinRegion.setVisibility(View.VISIBLE);
                regionButton.setText("Hide Wines");
            }
            else if(wineinRegion.getVisibility() == View.VISIBLE){
                wineinRegion.setVisibility(View.GONE);
                regionButton.setText("Show Wines");
            }
        }
    }

    private Context mContext;
    private JSONArray regions;
    private JSONObject wines;

    public Adapter_Regions(JSONArray regionsJson, JSONObject winesJson, Context context) {
        mContext = context;
        wines = winesJson;
        regions = regionsJson;
    }

    @Override
    public Adapter_Regions.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View regionView = inflater.inflate(R.layout.item_region, parent, false);

        return new ViewHolder(regionView);
    }

    @Override
    public void onBindViewHolder(Adapter_Regions.ViewHolder viewHolder, int position) {
        try {
            String regionTitle = regions.getString(position);

            TextView regionName = viewHolder.regionTextView;
            Button regionButton = viewHolder.regionButton;
            RecyclerView winesinRegion = viewHolder.wineinRegion;

            winesinRegion.setVisibility(View.GONE);
            regionName.setText(regionTitle);
            regionButton.setTag(regionTitle);

            winesinRegion.setAdapter(new Adapter_WinesinRegion(wines, regionTitle, mContext));
            RecyclerView.LayoutManager winesinRegionLM = new LinearLayoutManager(mContext);
            winesinRegion.setLayoutManager(winesinRegionLM);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return regions.length();
    }
}
