package com.jgwines.JGWinesPortfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Shukrat on 8/22/2016.
 */
public class F_Regions extends Fragment {
    private JSONArray regionsArr;
    private JSONObject winesObj;
    private RecyclerView regionsRecycler;
    private Helper_JSONReader_Singleton jsonReader_singleton;

    public static F_Regions newInstance() {
        return new F_Regions();
    }

    public F_Regions(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        View mView = inflater.inflate(R.layout.fragment_regions, container, false);
        regionsRecycler = (RecyclerView) mView.findViewById(R.id.rvRegionsList);
        regionsArr = jsonReader_singleton.getJSONArrayFromFile("regions", "regions");
        winesObj = jsonReader_singleton.getJSONObjFromFile("wines");

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Adapter_Regions regionsAdapter = new Adapter_Regions(regionsArr, winesObj, getContext());
        RecyclerView.LayoutManager regionsLayoutManager = new LinearLayoutManager(getActivity());
        regionsRecycler.setAdapter(regionsAdapter);
        regionsRecycler.setLayoutManager(regionsLayoutManager);
    }
}
