package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Shukrat on 8/28/2016.
 */
public class Helper_JSONReader_Singleton {
    private static final Helper_JSONReader_Singleton ourInstance = new Helper_JSONReader_Singleton();

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
        JSONObject jsonObj = new JSONObject();

        // Get internal storage path
        String path = mContext.getFilesDir().getAbsolutePath()+"/"+filename;
        FileInputStream fis;
        String content = "";
        try {
            // Directs inputstream to internal file storage
            // Can be replaced with a directory to raw.res if you have files stored in app that won't change
            fis = new FileInputStream(path);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content += new String(input);
            }
            jsonObj = new JSONObject(content);
            fis.close();
        }
        catch (IOException|JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }

    // Gets a specific array in a JSONFile in internal storage
    public JSONArray getJSONArrayFromFile(String filename, String neededArray){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObj;

        // Get internal storage path
        String path = mContext.getFilesDir().getAbsolutePath()+"/"+filename;
        FileInputStream fis;
        String content = "";
        try {
            // Directs inputstream to internal file storage
            // Can be replaced with a directory to raw.res if you have files stored in app that won't change
            fis = new FileInputStream(path);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content += new String(input);
            }
            jsonObj = new JSONObject(content);
            jsonArray = jsonObj.getJSONArray(neededArray);
            fis.close();
        }
        catch (IOException|JSONException e) {
            e.printStackTrace();
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
