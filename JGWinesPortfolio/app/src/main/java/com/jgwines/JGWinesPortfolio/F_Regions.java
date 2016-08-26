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

    public static F_News newInstance() {
        return new F_News();
    }

    public F_Regions(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_regions, container, false);
        regionsRecycler = (RecyclerView) mView.findViewById(R.id.rvRegionsList);
        Helper_JSONReader jsonReader = new Helper_JSONReader("regions", getActivity());
        Helper_JSONReader jsonReader2 = new Helper_JSONReader("wines", getActivity());
        regionsArr = jsonReader.getJsonArr("regions");
        winesObj = jsonReader2.getJsonObj();

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
