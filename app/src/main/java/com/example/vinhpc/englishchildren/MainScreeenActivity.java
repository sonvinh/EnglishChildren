package com.example.vinhpc.englishchildren;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainScreeenActivity extends AppCompatActivity {
    Button btnnew, btnguide, btnhigh, btnexit, btnlogout, btnhistory,btnsound;
    TextView textWelcom;
    Intent intentthread , intentguide, intenthig, intenthistory;
    Music music = new Music();
    SessionManager sessionManager;
    boolean isPlay = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screeen);
        btnnew = (Button)findViewById(R.id.button);
        btnhigh = (Button)findViewById(R.id.button2);
        btnguide = (Button)findViewById(R.id.button3);
        btnexit = (Button)findViewById(R.id.button4);
        btnlogout = (Button)findViewById(R.id.logout);
        textWelcom = (TextView)findViewById(R.id.textView9);
        btnhistory = (Button)findViewById(R.id.button18);
        btnsound = (Button)findViewById(R.id.button19);

        music.MainSound(MainScreeenActivity.this);

        intentguide = new Intent(this, GuideActivity.class);
        intentthread = new Intent(this, TopicActivity.class);
        intenthig = new Intent(this,HighestScoreActivity.class);

        intenthistory = new Intent(this, HistoryActivity.class);

        sessionManager = new SessionManager(this);
        sessionManager.isLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mUsename = user.get(sessionManager.USERNAME);

        textWelcom.setText("Hello"+ "\n" +mUsename);
//        Intent intent = getIntent();
//        String extraUsername = intent.getStringExtra("username");

//        textWelcom.setText("Hello"+" " + extraUsername);
        textWelcom.setTextColor(Color.RED);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentthread);
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
                music.Pause();
            }
        });

        btnhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intenthig);
            }
        });

        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intenthistory);
            }
        });

        btnsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay == false){
                    music.Pause();
                    isPlay = true;
                    btnsound.setBackgroundResource(R.drawable.btnmute);
                }
                else if (isPlay == true){
                    music.MainSound(MainScreeenActivity.this);
                    isPlay = false;
                    btnsound.setBackgroundResource(R.drawable.btnsound);
                }
            }
        });
    }
}
