package com.example.hp.ceaserplus.ActivityClasses;

/**
 * Created by HP on 3/27/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;

public class SplashScreen extends Activity
{

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2300;
    TextView verTXT;

    RelativeLayout mainLO;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mainLO= (RelativeLayout) findViewById(R.id.mainSplashLO);
        anim= AnimationUtils.loadAnimation(this,R.anim.slidedown);
        verTXT= (TextView) findViewById(R.id.verNoTXT);
        verTXT.setText("Version :"+SessionClass.versionNO);
        mainLO.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}