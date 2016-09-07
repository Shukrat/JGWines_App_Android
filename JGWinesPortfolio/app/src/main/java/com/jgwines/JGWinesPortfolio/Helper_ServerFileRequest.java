package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Shukrat on 8/29/2016.
 */
class Helper_ServerFileRequest extends AsyncTask<String, Void, String> {
    public Interface_ServerFileRequestResponse delegate = null;
    private final Context mContext;
    private final Helper_JSONReader_Singleton jsonReader_singleton;

    public Helper_ServerFileRequest(Context context){
        mContext = context;
        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
    }

    protected String doInBackground(String... strings) {
        String askFor = strings[0];
        String path = mContext.getFilesDir().getAbsolutePath()+"/"+askFor;

        try {
            String url = "http://www.jgwines.com/builderFiles/" + askFor + ".json";
            URL myurl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line).append("\n");
            }
            reader.close();
            conn.disconnect();

            if(askFor.equals("version")) {
                JSONObject temp = new JSONObject(sb.toString());
                int version = temp.getInt("version");
                File versionInFiles = new File(path);

                if (versionInFiles.exists()) {
                    if (jsonReader_singleton.getJSONObjFromFile("version").getInt("version") != version) {
                        FileOutputStream outputStream;

                        outputStream = mContext.openFileOutput(askFor, Context.MODE_PRIVATE);
                        outputStream.write(sb.toString().getBytes());
                        outputStream.close();
                        return "mismatch";
                    } else {
                        return "match";
                    }
                }else {
                    String tempContent = sb.toString();
                    FileOutputStream outputStream;

                    outputStream = mContext.openFileOutput(askFor, Context.MODE_PRIVATE);
                    outputStream.write(tempContent.getBytes());
                    outputStream.close();
                    return "mismatch";
                }
            }
            else{
                File delete = new File(path);
                Boolean deleted = delete.delete();
                FileOutputStream outputStream;

                outputStream = mContext.openFileOutput(askFor, Context.MODE_PRIVATE);
                outputStream.write(sb.toString().getBytes());
                outputStream.close();
                if(askFor.equals("wines")){
                    return "match";
                }
                else {
                    return "neutral";
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "No Server Connection";
        }
    }

    protected void onPostExecute(String string) {
        delegate.processFinished(string);
    }
}
