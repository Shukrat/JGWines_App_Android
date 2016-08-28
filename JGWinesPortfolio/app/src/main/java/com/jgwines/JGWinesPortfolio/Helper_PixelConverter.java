package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Shukrat on 8/27/2016.
 */
public class Helper_PixelConverter {
    DisplayMetrics displayMetrics;
    Context mContext;

    public Helper_PixelConverter(Context context){
        mContext = context;
    }

    /* Formula credit to Shyam Bhimani
    http://stackoverflow.com/a/8490361
     */

    public int getPixelfromDP(int dp){
        displayMetrics = mContext.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density)+0.5);

    }

    public int getDPfromPixel(int px){
        displayMetrics = mContext.getResources().getDisplayMetrics();
        return (int) ((px/displayMetrics.density)+0.5);
    }
}
