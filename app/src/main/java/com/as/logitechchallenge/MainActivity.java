package com.as.logitechchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.as.logitechchallenge.ui.ChallengeListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(MainActivity.this,ChallengeListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
                    finish();
                }
            }
        };
        timerThread.start();
    }
}
