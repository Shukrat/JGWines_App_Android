package com.jgwines.JGWinesPortfolio;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shukrat on 8/25/2016.
 */
public class Helper_JSONReader {
    private JSONObject jsonObj;
    private JSONArray jsonArray;
    private Context context;
    private String fileName;


    public Helper_JSONReader(String fileName, Context context){
        this.context = context;
        this.fileName = fileName;
    }

    public JSONObject getJsonObj(){
        String json;

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(
                    context.getResources().getIdentifier(fileName, "raw", context.getPackageName()))));

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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException j){
                j.printStackTrace();
            }
        }
        return jsonObj;
    }

    public JSONArray getJsonArr(String arrayName){
        String json;

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(
                    context.getResources().getIdentifier(fileName, "raw", context.getPackageName()))));

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
                jsonArray = jsonObj.getJSONArray(arrayName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException j){
                j.printStackTrace();
            }
        }

        return jsonArray;
    }
}
