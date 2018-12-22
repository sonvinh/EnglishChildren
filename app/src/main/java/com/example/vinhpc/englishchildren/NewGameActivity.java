package com.example.vinhpc.englishchildren;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewGameActivity extends AppCompatActivity {
    // save score
    Date datetime = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("E yyyy.MM.dd 'vào' hh:mm:ss a");
    private static String URL_SAVESCORE = "http://ecgame.000webhostapp.com/savescore.php";
    String score = "";
    SessionManager sessionManager;
    // ----- end save score

    TextView tv_score, tv_time;
    Button btnstart_pause, btnexit;
    ImageView iv_11, iv_12, iv_13, iv_14;

    private static final long START_TIME_IN_MILLIS = 60000;
    /// Array of images
    Integer[] cardsArray = {101, 102,  201, 202};
    /// actual images
    int image101, image102,  image201, image202;
    int firstCard, secondCard;
    int clickedFirst, clickedSeconds;
    int cardNumber = 1;

    Intent intentmain, intentnewgame, intentgame2;

    public int Totalscore = 0;

    private CountDownTimer mCoundowntime;
    private long timeLeftinMilliseconds = START_TIME_IN_MILLIS;
    private boolean mTimerunning;
    Music music = new Music();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_game);
        tv_time = (TextView) findViewById(R.id.textView2);
        tv_score = (TextView) findViewById(R.id.textView);

        btnstart_pause = (Button) findViewById(R.id.button5);
        btnexit = (Button) findViewById(R.id.button6);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);


        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");


        /// initial Enable = false
        iv_11.setEnabled(false);
        iv_12.setEnabled(false);
        iv_13.setEnabled(false);
        iv_14.setEnabled(false);
        btnexit.setEnabled(false);

        /// main_activity intent
        intentmain = new Intent(this, MainScreeenActivity.class);
        intentnewgame = new Intent(this, NewGameActivity.class);
        intentgame2 = new Intent(this, Game2Activity.class);

        /// change color score
        tv_score.setTextColor(0xFFF06D2F);
        tv_time.setTextColor(0xFFF06D2F);
        tv_score.setText("Điểm của bạn:" + Totalscore);


        ///load cards image
        frontOfCardResources();
        /// start time
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogbuil = new AlertDialog.Builder(NewGameActivity.this);
                dialogbuil.setMessage("Bạn có muốn lưu điểm không ?").setCancelable(false)
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                score = String.valueOf(Totalscore);
                                SaveS(score);
                                startActivity(intentmain);
                                finish();
                            }
                        }).setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intentmain);
                        finish();
                    }
                });
                stopTimer(); // stop time when player pass the level
                AlertDialog alert = dialogbuil.create();
                alert.show();
            }
        });

        btnstart_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enable to click image view

                iv_11.setEnabled(true);
                iv_12.setEnabled(true);
                iv_13.setEnabled(true);
                iv_14.setEnabled(true);
                btnexit.setEnabled(true);
                // start timer
                if (mTimerunning){
                    stopTimer();
                    iv_11.setEnabled(false);
                    iv_12.setEnabled(false);
                    iv_13.setEnabled(false);
                    iv_14.setEnabled(false);
                } else {
                    startTimer();
                    iv_11.setEnabled(true);
                    iv_12.setEnabled(true);
                    iv_13.setEnabled(true);
                    iv_14.setEnabled(true);
                }

            }
        });

        ///shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));

        /// Event Image
        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(NewGameActivity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_11, theCard);

            }
        });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(NewGameActivity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_12, theCard);
            }
        });
        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(NewGameActivity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_13, theCard);
            }
        });
        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(NewGameActivity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_14, theCard);
            }
        });
    }
    private void doStuff(ImageView iv, int card) {
        /// set the correct image to the imageview
        if (cardsArray[card] == 101) {
            iv.setImageResource(image101);
        } else if (cardsArray[card] == 102) {
            iv.setImageResource(image102);
        } else if (cardsArray[card] == 201) {
            iv.setImageResource(image201);
        } else if (cardsArray[card] == 202) {
            iv.setImageResource(image202);
        }
        /// check which image is selected and save it into temporary variables
        if (cardNumber == 1) {
            firstCard = cardsArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;
            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSeconds = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    /// check if the selected image are equal
                    checkImg();
                }
            }, 1000);

        }
    }

    private void checkImg() {
        if (firstCard == secondCard) {
            music.Windraw(NewGameActivity.this);
            if (clickedFirst == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            }

            if (clickedSeconds == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            }

            if(timeLeftinMilliseconds >= START_TIME_IN_MILLIS - 10000){
                Totalscore += 5;
            }
            else if(timeLeftinMilliseconds >= START_TIME_IN_MILLIS - 20000){
                Totalscore +=3;
            }
            else if(timeLeftinMilliseconds >= START_TIME_IN_MILLIS - 40000){
                Totalscore +=2;
            }
            else if(timeLeftinMilliseconds >= START_TIME_IN_MILLIS - 60000){
                Totalscore +=1;
            }

            tv_score.setText("Điểm của bạn: " + Totalscore);


        } else {
            iv_11.setImageResource(R.drawable.randomic);
            iv_12.setImageResource(R.drawable.randomic);
            iv_13.setImageResource(R.drawable.randomic);
            iv_14.setImageResource(R.drawable.randomic);

        }
        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);


        // check if the player pass the current level
        checkPass();

    }

    private void checkPass(){
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE){
                music.WinGame(NewGameActivity.this);
            AlertDialog.Builder dialogbuider = new AlertDialog.Builder(NewGameActivity.this);
            dialogbuider.setMessage("Chúc mừng bạn đã vượt qua vòng 1").setCancelable(false)
                    .setNegativeButton("Tiếp Tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intentgame2.putExtra("Key1", Totalscore);
                            finish();
                            startActivity(intentgame2);
                        }
                    });
            stopTimer(); // stop time when player pass the level
            AlertDialog alert = dialogbuider.create();
            alert.show();
        }
    }
    private void frontOfCardResources() {
        image101 = R.drawable.rabbit;
        image102 = R.drawable.chicken;
        image201 = R.drawable.wrabbit;
        image202 = R.drawable.wchicken;
    }

    public void startTimer(){
        mCoundowntime = new CountDownTimer(timeLeftinMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                tv_time.setText("Hết Giờ!!!");
                mTimerunning = false;
                music.Lose(NewGameActivity.this);
                AlertDialog.Builder dialogbuildtimeup = new AlertDialog.Builder(NewGameActivity.this);
                dialogbuildtimeup.setMessage("Thua rồi! Bạn có muốn chơi lại không ?").setCancelable(false)
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Totalscore = 0;
                                startActivity(intentnewgame);
                            }
                        }).setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = String.valueOf(Totalscore);
                        SaveS(score);
                        startActivity(intentmain);
                        finish();
                    }
                });
                AlertDialog alerttimeup = dialogbuildtimeup.create();
                alerttimeup.show();
            }
        }.start();
        mTimerunning = true;
        btnstart_pause.setBackgroundResource(R.drawable.btnpause);

    }
    public void stopTimer(){
        mCoundowntime.cancel();
        mTimerunning = false;
        btnstart_pause.setBackgroundResource(R.drawable.btnplay);
    }
    public void updateTimer(){
        int minute = (int) (timeLeftinMilliseconds / 1000 )/60;
        int seconds = (int)(timeLeftinMilliseconds / 1000 )%60;

        String timeLeftTextFormat = String.format(Locale.getDefault(),"%02d:%02d", minute, seconds);
        tv_time.setText(timeLeftTextFormat);
    }

    // Save score
    public void SaveS(final String score){
        final String time = sf.format(datetime);

        sessionManager = new SessionManager(this);
        sessionManager.isLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String username = user.get(sessionManager.USERNAME);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVESCORE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success =  jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(NewGameActivity.this, "Save Success!", Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NewGameActivity.this, "Save Error!" + e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(RegisterActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("score", score);
                params.put("time", time);
                params.put("username", username);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
