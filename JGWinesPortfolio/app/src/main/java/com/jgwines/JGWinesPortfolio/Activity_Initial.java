package com.jgwines.JGWinesPortfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Shukrat on 8/31/2016.
 */
public class Activity_Initial extends AppCompatActivity implements Interface_ServerFileRequestResponse{
    private final Helper_ServerFileRequest fileChecker = new Helper_ServerFileRequest(this);

    private Button b_enterPortfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fileChecker.delegate = this;
        Helper_JSONReader_Singleton jsonReader_singleton = Helper_JSONReader_Singleton.getInstance();
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
        switch (output) {
            case "match":
                b_enterPortfolio.setVisibility(View.VISIBLE);
                break;
            case "mismatch":
                String[] getJSONs = {"tastings", "regions", "wines"};
                for (String getJSON : getJSONs) {
                    Helper_ServerFileRequest downloadnewJSON = new Helper_ServerFileRequest(this);
                    downloadnewJSON.delegate = this;
                    downloadnewJSON.execute(getJSON);
                }
                break;
            case "No Server Connection":
                Toast.makeText(this, "Unable to check for updates. Most recent version of portfolio loaded.", Toast.LENGTH_LONG).show();
                b_enterPortfolio.setVisibility(View.VISIBLE);
                break;
        }
    }
}
