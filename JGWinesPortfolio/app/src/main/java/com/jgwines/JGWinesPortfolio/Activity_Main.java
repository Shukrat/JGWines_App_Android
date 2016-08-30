package com.jgwines.JGWinesPortfolio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Activity_Main extends AppCompatActivity implements View.OnClickListener{

    Button b_enterPortfolio;
    Button b_newVintages;
    Button b_regions;
    Button b_allWines;
    Button b_aboutJGW;
    ScrollView fragmentSS;
    Helper_JSONReader_Singleton jsonReader_singleton;
    ServerRequest_VersionCheck checkVersion;
    int fragmentIndicator;

    F_News f_newVintages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        jsonReader_singleton.setContext(this);

        new ServerRequest_VersionCheck(this).execute("version");

        b_enterPortfolio = (Button) findViewById(R.id.enterPortfolio);
        b_enterPortfolio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        setContentView(R.layout.activity_main);
        prepButtons();

        fragmentIndicator = 1;
        f_newVintages = F_News.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentsGoHere, f_newVintages).commit();
        fragmentSS = (ScrollView) findViewById(R.id.fragmentsGoHere);
        fragmentSS.setTag("news");
    }

    @Override
    public void onBackPressed(){
        int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
        if(index >= 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
            String tag = backEntry.getName();
            colorButtons(tag);
        }

        super.onBackPressed();
    }

    public void prepButtons(){
        b_newVintages = (Button) findViewById(R.id.button_newVintages);
        b_regions = (Button) findViewById(R.id.button_regions);
        b_allWines = (Button) findViewById(R.id.button_allWines);
        b_aboutJGW = (Button) findViewById(R.id.button_aboutJGWines);
    }

    public void changeFragment(Fragment fragment, String name){
        FragmentTransaction transactor = getSupportFragmentManager().beginTransaction();
        transactor.replace(R.id.fragmentsGoHere, fragment);
        transactor.addToBackStack(name);
        transactor.commit();
    }

    public void colorButtons(String string){
        b_newVintages.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_regions.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_allWines.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_aboutJGW.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_newVintages.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_regions.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_allWines.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_aboutJGW.setBackgroundResource(R.color.mainUnselectedButtonBG);
        switch (string){
            case "news": {
                b_newVintages.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_newVintages.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case "regions":{
                b_regions.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_regions.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case "allwines":{
                b_allWines.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_allWines.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case "aboutjgw":{
                b_aboutJGW.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_aboutJGW.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            default: break;
        }
    }

    public void switchFragment(View v){
        switch(v.getId()){
            case (R.id.button_newVintages):
                if(fragmentIndicator == 1) break;
                else{
                    changeFragment(f_newVintages, (String)fragmentSS.getTag());
                    fragmentSS.setTag("news");
                    fragmentIndicator = 1;
                }
                break;
            case (R.id.button_regions):
                if(fragmentIndicator == 2) break;
                else{
                    F_Regions f_region = F_Regions.newInstance();

                    changeFragment(f_region, (String)fragmentSS.getTag());
                    fragmentSS.setTag("regions");
                    fragmentIndicator = 2;
                }
                break;
            case (R.id.button_allWines):
                if(fragmentIndicator == 3) break;
                else{
                    F_AllWines f_allwines = F_AllWines.newInstance();

                    changeFragment(f_allwines, (String)fragmentSS.getTag());
                    fragmentSS.setTag("allwines");
                    fragmentIndicator = 3;
                }
                break;
            case (R.id.button_aboutJGWines):
                if(fragmentIndicator == 4) break;
                else{
                    F_AboutJGWines f_aboutjgw = F_AboutJGWines.newInstance();

                    changeFragment(f_aboutjgw, (String)fragmentSS.getTag());
                    fragmentSS.setTag("aboutjgw");
                    fragmentIndicator = 4;
                }
                break;
            default:
                break;
        }
        colorButtons((String) fragmentSS.getTag());
    }
}
