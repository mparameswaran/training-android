package com.madan.training;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by madan on 10/12/13.
 */
public class SplashScreenActivity extends Activity {

    private static int timeOut = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
