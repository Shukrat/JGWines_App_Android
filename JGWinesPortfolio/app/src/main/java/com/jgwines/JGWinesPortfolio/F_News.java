package com.jgwines.JGWinesPortfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

/**
 * Created by Shukrat on 8/19/2016.
 */
public class F_News extends Fragment {
    private RecyclerView newVintageRecycler;
    private RecyclerView tastingsRecycler;
    private Helper_JSONReader_Singleton jsonReader_singleton;

    public static F_News newInstance() {
        return new F_News();
    }

    public F_News() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        View mView = inflater.inflate(R.layout.fragment_news, container, false);
        newVintageRecycler = (RecyclerView) mView.findViewById(R.id.rvNewVintages);
        tastingsRecycler = (RecyclerView) mView.findViewById(R.id.rvTastings);
        jsonReader_singleton.getJSONObjFromFile("wines");

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        JSONObject allWinesObj = jsonReader_singleton.getJSONObjFromFile("wines");
        JSONObject tastingsObj = jsonReader_singleton.getJSONObjFromFile("tastings");

        Adapter_NewVintage newsAdapter = new Adapter_NewVintage(allWinesObj, getContext());
        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity());
        newVintageRecycler.setAdapter(newsAdapter);
        newVintageRecycler.setLayoutManager(newsLayoutManager);

        Adapter_Tastings tastingsAdapter = new Adapter_Tastings(tastingsObj, getContext());
        RecyclerView.LayoutManager tastingsLayoutManager = new LinearLayoutManager(getActivity());
        tastingsRecycler.setAdapter(tastingsAdapter);
        tastingsRecycler.setLayoutManager(tastingsLayoutManager);

    }
}
