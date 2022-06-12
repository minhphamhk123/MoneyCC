package com.example.assignment.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.example.assignment.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements OnProgressBarListener {
    Timer timer;
    ImageView imgsplash;
    NumberProgressBar numberProgressBar;
    TextView txtpower,txtten;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        anhXa();
        Animation();
        sharedPreferences = getSharedPreferences("lock", MODE_PRIVATE);

        numberProgressBar.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressBar.incrementProgressBy(1);
                    }
                });
            }
        }, 500, 50);

    }

    private void anhXa(){
        imgsplash = findViewById(R.id.imgsplash);
        numberProgressBar = findViewById(R.id.progressbar);
        txtpower = findViewById(R.id.txtten);
        txtten  = findViewById(R.id.txttacgia);
    }
    private void Animation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        imgsplash.startAnimation(animation);
        txtten.startAnimation(animation);
        txtpower.startAnimation(animation);
        numberProgressBar.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onProgressChange(int current, int max) {
        if(current==max){
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            timer.cancel();
            finish();
        }
    }
}
