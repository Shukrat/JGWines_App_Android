package com.jgwines.JGWinesPortfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Shukrat on 8/22/2016.
 */
public class F_AllWines extends Fragment{
    private RecyclerView winesRecycler;
    private Adapter_Wines winesAdapter;
    private JSONArray winesKey;
    private Helper_JSONReader_Singleton jsonReader_singleton;
    private ArrayMap<String, String> searchTerms = new ArrayMap<>();
    private ArrayList<String> winesToDisplay = new ArrayList<>();


    public static F_AllWines newInstance() {
        return new F_AllWines();
    }

    public F_AllWines() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        final View mView = inflater.inflate(R.layout.fragment_allwines, container, false);
        winesRecycler = (RecyclerView) mView.findViewById(R.id.rvWineList);
        SearchView searchView = (SearchView) mView.findViewById(R.id.allWines_Search);
        searchView.setIconified(false);
        searchView.clearFocus();

        try {
            Field searchField = SearchView.class.getDeclaredField("mCloseButton");
            searchField.setAccessible(true);
            ImageView mSearchCloseButton = (ImageView) searchField.get(searchView);
            if(mSearchCloseButton != null){
                mSearchCloseButton.setEnabled(false);
                mSearchCloseButton.setImageAlpha(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                if(newText.equals("")){
                    winesToDisplay.clear();
                    try {
                        for (int i = 0; i < winesKey.length(); i++) {
                            winesToDisplay.add(winesKey.getString(i));
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                    winesAdapter.notifyDataSetChanged();
                    winesRecycler.setAdapter(winesAdapter);
                    return true;
                }
                else{
                    // Wipe wines to display and repopulate by comparison
                    winesToDisplay.clear();
                    // Search thru array map keys, if a match, return value to ArrayList for recyclerview
                    for(int i = 0; i < searchTerms.size(); i++){
                        if(searchTerms.keyAt(i).contains(newText)){
                            winesToDisplay.add(searchTerms.valueAt(i));
                        }
                    }
                    winesAdapter.notifyDataSetChanged();
                    winesRecycler.setAdapter(winesAdapter);
                    return true;
                }
            }
        });

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JSONObject allWinesObj = jsonReader_singleton.getJSONObjFromFile("wines");
        winesKey = jsonReader_singleton.getJSONArrayFromFile("wines", "key");

        try {
            JSONObject wines = allWinesObj.getJSONObject("wines");
            for (int i = 0; i < winesKey.length(); i++) {
                winesToDisplay.add(winesKey.getString(i));
            }
            for(int i = 0; i < winesToDisplay.size(); i++){
                String neededWine = winesToDisplay.get(i);
                String title = wines.getJSONObject(neededWine).getString("title");
                String vineyard = wines.getJSONObject(neededWine).getString("vineyard");
                String type = wines.getJSONObject(neededWine).getString("type");
                String searchable = title + " " + vineyard + " " + type;
                searchable = searchable.toLowerCase();
                searchTerms.put(searchable, winesToDisplay.get(i));
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        winesAdapter = new Adapter_Wines(allWinesObj, winesToDisplay, getContext());
        RecyclerView.LayoutManager winesLayoutManager = new LinearLayoutManager(getActivity());
        winesRecycler.setAdapter(winesAdapter);
        winesRecycler.setLayoutManager(winesLayoutManager);
    }
}
