package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

public class Activity_Main extends AppCompatActivity{

    public static class PagerAdapter extends FragmentPagerAdapter{
        private static final int NUM_ITEMS = 4;

        public PagerAdapter(android.support.v4.app.FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public int getCount(){
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    return F_News.newInstance();
                case 1:
                    return F_Regions.newInstance();
                case 2:
                    return F_AllWines.newInstance();
                case 3:
                    return F_AboutJGWines.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0: return "News";
                case 1: return "Regions";
                case 2: return "All\nWines";
                case 3: return "About\nJG Wines";
                default: return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Helper_JSONReader_Singleton jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        jsonReader_singleton.setContext(this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_ViewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                final InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
