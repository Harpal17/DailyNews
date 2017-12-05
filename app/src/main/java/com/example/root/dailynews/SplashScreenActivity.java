package com.example.root.dailynews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity
{
    private Context mContext;
    private String TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(mContext, WalkThroughActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Throwable t) {
                    System.out.println(t);
                }
            }
        }, 2000);
    }
}
