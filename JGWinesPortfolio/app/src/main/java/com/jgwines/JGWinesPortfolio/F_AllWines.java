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
 * Created by Shukrat on 8/22/2016.
 */
public class F_AllWines extends Fragment{
    private RecyclerView winesRecycler;
    private Helper_JSONReader jsonReader;


    public static F_AllWines newInstance() {
        return new F_AllWines();
    }

    public F_AllWines() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_allwines, container, false);
        winesRecycler = (RecyclerView) mView.findViewById(R.id.rvWineList);
        jsonReader = new Helper_JSONReader("wines", getActivity());

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JSONObject allWinesObj = jsonReader.getJsonObj();

        Adapter_Wines winesAdapter = new Adapter_Wines(allWinesObj, getContext());
        RecyclerView.LayoutManager winesLayoutManager = new LinearLayoutManager(getActivity());
        winesRecycler.setAdapter(winesAdapter);
        winesRecycler.setLayoutManager(winesLayoutManager);
    }
}
