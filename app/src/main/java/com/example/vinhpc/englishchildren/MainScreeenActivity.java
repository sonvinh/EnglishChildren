package com.example.vinhpc.englishchildren;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreeenActivity extends AppCompatActivity {
    Button btnnew, btnguide, btnhigh, btnexit;
    Intent intentnew , intentguide, intenthig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screeen);
        btnnew = (Button)findViewById(R.id.button);
        btnhigh = (Button)findViewById(R.id.button2);
        btnguide = (Button)findViewById(R.id.button3);
        btnexit = (Button)findViewById(R.id.button4);
        intentguide = new Intent(this, GuideActivity.class);
        intentnew = new Intent(this, NewGameActivity.class);
        intenthig = new Intent(this,HighestScoreActivity.class);

        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentnew);
            }
        });

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentguide);
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        btnhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intenthig);
            }
        });
    }
}
