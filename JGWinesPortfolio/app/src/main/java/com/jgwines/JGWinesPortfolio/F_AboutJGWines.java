package com.jgwines.JGWinesPortfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shukrat on 8/22/2016.
 */
public class F_AboutJGWines extends Fragment {


    public static F_AboutJGWines newInstance() {
        return new F_AboutJGWines();
    }

    public F_AboutJGWines() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newvintages, container, false);


    }


}
