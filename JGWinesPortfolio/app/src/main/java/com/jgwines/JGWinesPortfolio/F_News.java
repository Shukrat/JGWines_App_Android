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
 * Created by Shukrat on 8/19/2016.
 */
public class F_News extends Fragment {
    private RecyclerView newVintageRecycler;
    private Helper_JSONReader jsonReader;

    public static F_News newInstance() {
        return new F_News();
    }

    public F_News() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_newvintages, container, false);
        newVintageRecycler = (RecyclerView) mView.findViewById(R.id.rvNewVintages);
        jsonReader = new Helper_JSONReader("wines", getActivity());

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        JSONObject allWinesObj = jsonReader.getJsonObj();

        Adapter_News newsAdapter = new Adapter_News(allWinesObj, getContext());
        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity());
        newVintageRecycler.setAdapter(newsAdapter);
        newVintageRecycler.setLayoutManager(newsLayoutManager);

    }
}
