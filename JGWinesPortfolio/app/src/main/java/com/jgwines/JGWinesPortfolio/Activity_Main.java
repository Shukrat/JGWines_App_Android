package com.jgwines.JGWinesPortfolio;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Main extends AppCompatActivity implements View.OnClickListener{

    Button b_enterPortfolio;
    Button b_newVintages;
    Button b_regions;
    Button b_allWines;
    Button b_aboutJGW;
    int fragmentIndicator;
    FragmentTransaction transactor;
    F_News f_newVintages;
    F_Regions f_region;
    F_AllWines f_allwines;
    F_AboutJGWines f_aboutjgw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        b_enterPortfolio = (Button) findViewById(R.id.enterPortfolio);
        b_enterPortfolio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        setContentView(R.layout.activity_main);
        prepButtons();

        fragmentIndicator = 1;
        f_newVintages = new F_News();
        f_region = new F_Regions();
        f_allwines = new F_AllWines();
        f_aboutjgw = new F_AboutJGWines();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentsGoHere, f_newVintages).commit();
    }

    public void prepButtons(){
        b_newVintages = (Button) findViewById(R.id.button_newVintages);
        b_regions = (Button) findViewById(R.id.button_regions);
        b_allWines = (Button) findViewById(R.id.button_allWines);
        b_aboutJGW = (Button) findViewById(R.id.button_aboutJGWines);
    }

    public void changeFragment(Fragment fragment){
        transactor = getSupportFragmentManager().beginTransaction();
        transactor.replace(R.id.fragmentsGoHere, fragment);
        transactor.addToBackStack(null);
        transactor.commit();
    }

    public void switchFragment(View v){
        switch(v.getId()){
            case (R.id.button_newVintages):
                if(fragmentIndicator == 1) break;
                else{
                    changeFragment(f_newVintages);

                    b_newVintages.setTextColor(Color.parseColor("#FFFFFF"));
                    b_regions.setTextColor(Color.parseColor("#000000"));
                    b_allWines.setTextColor(Color.parseColor("#000000"));
                    b_aboutJGW.setTextColor(Color.parseColor("#000000"));
                    b_newVintages.setBackgroundColor(Color.parseColor("#000000"));
                    b_regions.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_allWines.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_aboutJGW.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    fragmentIndicator = 1;
                }
                break;
            case (R.id.button_regions):
                if(fragmentIndicator == 2) break;
                else{
                    changeFragment(f_region);

                    b_newVintages.setTextColor(Color.parseColor("#000000"));
                    b_regions.setTextColor(Color.parseColor("#FFFFFF"));
                    b_allWines.setTextColor(Color.parseColor("#000000"));
                    b_aboutJGW.setTextColor(Color.parseColor("#000000"));
                    b_newVintages.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_regions.setBackgroundColor(Color.parseColor("#000000"));
                    b_allWines.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_aboutJGW.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    fragmentIndicator = 2;
                }
                break;
            case (R.id.button_allWines):
                if(fragmentIndicator == 3) break;
                else{
                    changeFragment(f_allwines);

                    b_newVintages.setTextColor(Color.parseColor("#000000"));
                    b_regions.setTextColor(Color.parseColor("#000000"));
                    b_allWines.setTextColor(Color.parseColor("#FFFFFF"));
                    b_aboutJGW.setTextColor(Color.parseColor("#000000"));
                    b_newVintages.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_regions.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_allWines.setBackgroundColor(Color.parseColor("#000000"));
                    b_aboutJGW.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    fragmentIndicator = 3;
                }
                break;
            case (R.id.button_aboutJGWines):
                if(fragmentIndicator == 4) break;
                else{
                    changeFragment(f_aboutjgw);

                    b_newVintages.setTextColor(Color.parseColor("#000000"));
                    b_regions.setTextColor(Color.parseColor("#000000"));
                    b_allWines.setTextColor(Color.parseColor("#000000"));
                    b_aboutJGW.setTextColor(Color.parseColor("#FFFFFF"));
                    b_newVintages.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_regions.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_allWines.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    b_aboutJGW.setBackgroundColor(Color.parseColor("#000000"));

                    fragmentIndicator = 4;
                }
                break;
            default:
                break;
        }
    }
}
