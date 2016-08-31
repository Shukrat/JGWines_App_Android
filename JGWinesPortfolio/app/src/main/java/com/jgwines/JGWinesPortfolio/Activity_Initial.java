package com.jgwines.JGWinesPortfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Shukrat on 8/31/2016.
 */
public class Activity_Initial extends AppCompatActivity implements Interface_ServerFileRequestResponse{
    Helper_ServerFileRequest fileChecker = new Helper_ServerFileRequest(this);
    Helper_JSONReader_Singleton jsonReader_singleton;

    Button b_enterPortfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fileChecker.delegate = this;
        jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
        jsonReader_singleton.setContext(this);

        fileChecker.execute("version");

        b_enterPortfolio = (Button) findViewById(R.id.enterPortfolio);
        b_enterPortfolio.setVisibility(View.GONE);
        b_enterPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Activity_Main.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public void processFinished(String output){
        if(output.equals("match")){
            b_enterPortfolio.setVisibility(View.VISIBLE);
        }
        else if(output.equals("mismatch")){
            String[] getJSONs = {"tastings", "regions", "wines"};
            for(int i = 0; i < getJSONs.length; i++) {
                Helper_ServerFileRequest downloadnewJSON = new Helper_ServerFileRequest(this);
                downloadnewJSON.delegate = this;
                downloadnewJSON.execute(getJSONs[i]);
            }
        }
        else{}
    }
}
