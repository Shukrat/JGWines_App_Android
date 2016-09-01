package com.jgwines.JGWinesPortfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shukrat on 8/22/2016.
 */
public class F_AboutJGWines extends Fragment {


    public static F_AboutJGWines newInstance() {
        return new F_AboutJGWines();
    }

    public F_AboutJGWines() {
    }

    TextView call;
    TextView email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_aboutjgwines, container, false);
        email = (TextView) mView.findViewById(R.id.email);
        call = (TextView) mView.findViewById(R.id.call);
        call.setTag("8458683203");

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailLine = (String) email.getText();
                String uri = "mailto:" + emailLine + "?subject=" + Uri.encode("JG Wines Inquiry");
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setType("text/plain");
                i.setData(Uri.parse(uri));
                getContext().startActivity(Intent.createChooser(i, "Send using..."));
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = (String) call.getTag();
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+uri));
                getContext().startActivity(Intent.createChooser(i, "Call using..."));
            }
        });
        // Inflate the layout for this fragment
        return mView;
    }
}
