package com.jgwines.JGWinesPortfolio;

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

    public void colorButtons(int i){
        b_newVintages.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_regions.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_allWines.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_aboutJGW.setTextColor(getResources().getColor(R.color.backgroundColor));
        b_newVintages.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_regions.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_allWines.setBackgroundResource(R.color.mainUnselectedButtonBG);
        b_aboutJGW.setBackgroundResource(R.color.mainUnselectedButtonBG);
        switch (i){
            case 1: {
                b_newVintages.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_newVintages.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case 2:{
                b_regions.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_regions.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case 3:{
                b_allWines.setTextColor(getResources().getColor(R.color.mainUnselectedButtonBG));
                b_allWines.setBackgroundResource(R.color.backgroundColor);
                break;
            }
            case 4:{
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
                    changeFragment(f_newVintages);
                    fragmentIndicator = 1;
                }
                break;
            case (R.id.button_regions):
                if(fragmentIndicator == 2) break;
                else{
                    F_Regions f_region = new F_Regions();

                    changeFragment(f_region);
                    fragmentIndicator = 2;
                }
                break;
            case (R.id.button_allWines):
                if(fragmentIndicator == 3) break;
                else{
                    F_AllWines f_allwines = new F_AllWines();

                    changeFragment(f_allwines);
                    fragmentIndicator = 3;
                }
                break;
            case (R.id.button_aboutJGWines):
                if(fragmentIndicator == 4) break;
                else{
                    F_AboutJGWines f_aboutjgw = new F_AboutJGWines();

                    changeFragment(f_aboutjgw);
                    fragmentIndicator = 4;
                }
                break;
            default:
                break;
        }
        colorButtons(fragmentIndicator);
    }
}
