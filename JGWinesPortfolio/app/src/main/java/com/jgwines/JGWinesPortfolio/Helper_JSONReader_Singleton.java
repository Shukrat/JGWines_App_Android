package com.jgwines.JGWinesPortfolio;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shukrat on 8/28/2016.
 */
public class Helper_JSONReader_Singleton {
    private static Helper_JSONReader_Singleton ourInstance = new Helper_JSONReader_Singleton();

    public static Helper_JSONReader_Singleton getInstance() {
        return ourInstance;
    }

    private Context mContext;

    private Helper_JSONReader_Singleton() {
    }

    public void setContext(Context context){
        mContext = context;
    }

    public JSONObject getJSONObjFromFile(String filename){
        String json;
        JSONObject jsonObj = new JSONObject();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(
                    mContext.getResources().getIdentifier(filename, "raw", mContext.getPackageName()))));

            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); // stop reading
                json = sb.toString();
                jsonObj = new JSONObject(json);
            } catch (IOException|JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObj;
    }

    public JSONObject getJSONObjFromJSON(JSONObject incomingJSON, String neededObj){
        JSONObject returnJSON = new JSONObject();

        try{
            returnJSON = incomingJSON.getJSONObject(neededObj);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return returnJSON;
    }

    public JSONArray getJSONArrayFromFile(String filename, String neededArray){
        String json;
        JSONObject jsonObj;
        JSONArray jsonArray = new JSONArray();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(
                    mContext.getResources().getIdentifier(filename, "raw", mContext.getPackageName()))));

            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); // stop reading
                json = sb.toString();
                jsonObj = new JSONObject(json);
                jsonArray = jsonObj.getJSONArray(neededArray);
            } catch (IOException|JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public JSONArray getJSONArrayFromJSONObject(JSONObject incomingJSON, String neededArray){
        JSONArray returnJSON = new JSONArray();

        try{
            returnJSON = incomingJSON.getJSONArray(neededArray);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return returnJSON;
    }
}
