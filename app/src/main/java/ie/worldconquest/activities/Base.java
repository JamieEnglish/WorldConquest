package ie.worldconquest.activities;

import ie.worldconquest.R;
import ie.worldconquest.fragments.TripFragment;
import ie.worldconquest.main.WorldConquestApp;
import ie.worldconquest.models.Trip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Base extends AppCompatActivity {

    public WorldConquestApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        app = (WorldConquestApp) getApplication();
    }

    //put methods OnCreateOptionsMenu, menuHome


    public void menuInfo(MenuItem m) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.appAbout))
                .setMessage(getString(R.string.appDesc)
                        + "\n\n"
                        + getString(R.string.appMoreInfo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we could put some code here too
                    }
                })
                .show();
    }
}
