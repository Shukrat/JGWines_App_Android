package com.jgwines.JGWinesPortfolio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Shukrat on 8/31/2016.
 */
class Helper_ServerImageRequest extends AsyncTask<String, Void, Bitmap> {
    private final ImageView imageView;

    public Helper_ServerImageRequest(ImageView _imageView){
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
        else{
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.main_jgwlogo));
        }
    }
}
