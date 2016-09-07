package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/27/2016.
 */
public class Adapter_Tastings extends RecyclerView.Adapter<Adapter_Tastings.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tastingDate;
        public final TextView tastingTime;
        public final TextView tastingLocation;
        public final TextView tastingPhone;
        public final LinearLayout tastingDetails;
        public final Context mContext;

        public ViewHolder (View itemView, Context context){
            super(itemView);
            mContext = context;
            tastingDate = (TextView) itemView.findViewById(R.id.tastingDate);
            tastingTime = (TextView) itemView.findViewById(R.id.tastingTime);
            tastingLocation = (TextView) itemView.findViewById(R.id.tastingLocation);
            tastingDetails = (LinearLayout) itemView.findViewById(R.id.tastingDetails);
            tastingLocation.setOnClickListener(this);
            tastingPhone = (TextView) itemView.findViewById(R.id.tastingPhone);
        }

        @Override
        public void onClick(View v){
            String location = (String) tastingLocation.getText();
            String locationParsed = location.replace(" ", "+").replace(",", "%2C");
            String uri = "geo:0,0?q=" + locationParsed;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            if(intent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(intent);
            }
        }
    }

    private JSONObject tastingsJSON;
    private JSONArray tastingsKey;
    private final Context mContext;

    public Adapter_Tastings(JSONObject _tastingsJSON, Context context){
        mContext = context;
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

        return new ViewHolder(mView, mContext);
    }

    @Override
    public void onBindViewHolder(Adapter_Tastings.ViewHolder viewHolder, int position){
        TextView mTastingDate = viewHolder.tastingDate;
        TextView mTastingTime = viewHolder.tastingTime;
        TextView mTastingLocation = viewHolder.tastingLocation;
        TextView mTastingPhone = viewHolder.tastingPhone;
        LinearLayout mTastingDetails = viewHolder.tastingDetails;

        try {
            String key = tastingsKey.getString(position);

            mTastingDate.setText(tastingsJSON.getJSONObject(key).getString("date"));
            mTastingLocation.setText(tastingsJSON.getJSONObject(key).getString("location"));
            mTastingTime.setText(tastingsJSON.getJSONObject(key).getString("time"));
            mTastingPhone.setText(tastingsJSON.getJSONObject(key).getString("phone"));
            if(mTastingLocation.getText().equals("") && mTastingPhone.getText().equals("") && mTastingTime.getText().equals("")){
                mTastingDetails.setVisibility(View.GONE);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){ return tastingsKey.length(); }
}
