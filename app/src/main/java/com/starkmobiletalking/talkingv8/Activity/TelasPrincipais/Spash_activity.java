package com.starkmobiletalking.talkingv8.Activity.TelasPrincipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.starkmobiletalking.talkingv8.R;

import java.util.Timer;
import java.util.TimerTask;

public class Spash_activity extends AppCompatActivity {
    private final Timer timer = new Timer();
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        timerTask= new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotomainActivict();
                    }
                });
            }
        };
        timer.schedule(timerTask,1000);
    }
    private void gotomainActivict() {
        Intent intent= new Intent(Spash_activity.this, Login_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}