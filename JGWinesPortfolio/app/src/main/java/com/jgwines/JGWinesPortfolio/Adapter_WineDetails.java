package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shukrat on 8/28/2016.
 */
public class Adapter_WineDetails extends RecyclerView.Adapter<Adapter_WineDetails.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView wineDetailTitle;
        public TextView wineDetailContent;
        public LinearLayout wineDetailLayout;

        public ViewHolder(View itemView){
            super(itemView);

            wineDetailLayout = (LinearLayout) itemView.findViewById(R.id.detailLayout_WineDisplay);
            wineDetailTitle = (TextView) itemView.findViewById(R.id.detailTitle_WineDisplay);
            wineDetailContent = (TextView) itemView.findViewById(R.id.detailContent_WineDisplay);
        }
    }

    private JSONObject wineInfoJSON;
    private JSONArray wineDetailsKeyJSONARRAY;
    private ArrayList<String> wineInfoArray = new ArrayList<String>();
    private ArrayList<String> wineInfoKeyArray = new ArrayList<String>();

    public Adapter_WineDetails(JSONObject _wineDetailsJSON, JSONArray _wineDetailsKey){
        wineInfoJSON = _wineDetailsJSON;
        wineDetailsKeyJSONARRAY = _wineDetailsKey;
        parseWineInfo();
    }

    @Override
    public Adapter_WineDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mView = inflater.inflate(R.layout.item_winedetail, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(Adapter_WineDetails.ViewHolder viewHolder, int position){
            String item = wineInfoKeyArray.get(position);

            TextView wineDetailTitle = viewHolder.wineDetailTitle;
            TextView wineDetailContent = viewHolder.wineDetailContent;

            // Add : to section headers
            item+=":";
            wineDetailTitle.setText(item);
            wineDetailContent.setText(wineInfoArray.get(position));
            if(item.equals("short_desc:")){
                wineDetailTitle.setVisibility(View.GONE); // If short_desc, remove title from view.
            }
            else{
                wineDetailTitle.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public int getItemCount() { return wineInfoArray.size(); }

    // Parses the wines.json file to pull out information and store it in Strings created in global space
    public void parseWineInfo(){
        try {
            for(int i = 0; i < wineDetailsKeyJSONARRAY.length(); i++){
                String key = wineDetailsKeyJSONARRAY.getString(i);

                // These values are set elsewhere
                if(key.equals("label_image") || key.equals("region_image") ||
                        key.equals("type") || key.equals("title") || key.equals("vineyard")){
                    continue;
                }
                if(wineInfoJSON.getString(key).equals("")){ // If no content for section, remove
                    continue;
                }
                if(!key.equals("photos")) { // Remove photo section from displaying
                    wineInfoKeyArray.add(key);
                    wineInfoArray.add(wineInfoJSON.getString(key));
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

    }
}
