package ie.worldconquest.activities;

import android.app.Activity;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.ImageView;

import ie.worldconquest.R;

public class Splash extends Activity {

    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 5000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }
}
