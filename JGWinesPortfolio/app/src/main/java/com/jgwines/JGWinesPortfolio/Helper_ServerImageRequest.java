package com.jgwines.JGWinesPortfolio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Shukrat on 8/31/2016.
 */
public class Helper_ServerImageRequest extends AsyncTask<String, Void, Bitmap> {
    private Context mContext;
    private ImageView imageView;

    public Helper_ServerImageRequest(Context context, ImageView _imageView){
        mContext = context;
        imageView = _imageView;
    }

    protected Bitmap doInBackground(String... strings){
        String askFor = strings[0];
        Bitmap labelImage = null;
        try {
            String url = "http://www.jgwines.com/builderFiles/label_images/" + askFor + ".jpg";

            InputStream in = new java.net.URL(url).openStream();
            labelImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelImage;

    }

    protected void onPostExecute(Bitmap bitmap){
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
