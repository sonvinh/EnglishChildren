package com.example.vinhpc.englishchildren;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

public class Game2Activity extends AppCompatActivity {
    // save score
    Date datetime = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("E yyyy.MM.dd 'vào' hh:mm:ss a");
    private static String URL_SAVESCORE = "http://ecgame.000webhostapp.com/savescore.php";
    String score = "";
    SessionManager sessionManager;
    // ----- end save score

    TextView tv_score, tv_time;
    Button btnstart_pause, btnexit;;
    ImageView iv_15, iv_16, iv_17, iv_18, iv_19, iv_20;

    private static final long START_TIME_IN_MILLIS = 60000;

    Integer[] cardsArray = {101, 102, 103,  201, 202,203};

    int image101, image102,image103,  image201, image202,image203;
    int firstCard, secondCard;
    int clickedFirst, clickedSeconds;
    int cardNumber = 1;

    Intent intentmain ,intentnewgame, intentgame3;
    int Totalscore;

    private CountDownTimer mCoundowntime;
    private long timeLeftinMilliseconds = START_TIME_IN_MILLIS;
    private boolean mTimerunning;
    Music music = new Music();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game2);
        Intent ite = getIntent();
        Totalscore = ite.getIntExtra("Key1", 0);
        tv_time = (TextView) findViewById(R.id.textViewLv2);
        tv_score = (TextView) findViewById(R.id.textViewLv);

        btnstart_pause = (Button) findViewById(R.id.button7);
        btnexit = (Button) findViewById(R.id.button8);

        intentmain = new Intent(this, MainScreeenActivity.class);
        intentnewgame = new Intent(this,NewGameActivity.class);
        intentgame3 = new Intent(this, Game3Activity.class);

        iv_15 = (ImageView) findViewById(R.id.iv_15);
        iv_16 = (ImageView) findViewById(R.id.iv_16);
        iv_17 = (ImageView) findViewById(R.id.iv_17);
        iv_18 = (ImageView) findViewById(R.id.iv_18);
        iv_19 = (ImageView) findViewById(R.id.iv_19);
        iv_20 = (ImageView) findViewById(R.id.iv_20);

        iv_15.setTag("0");
        iv_16.setTag("1");
        iv_17.setTag("2");
        iv_18.setTag("3");
        iv_19.setTag("4");
        iv_20.setTag("5");

        iv_15.setEnabled(false);
        iv_16.setEnabled(false);
        iv_17.setEnabled(false);
        iv_18.setEnabled(false);
        iv_19.setEnabled(false);
        iv_20.setEnabled(false);
        btnexit.setEnabled(false);

        tv_score.setTextColor(0xFFF06D2F);
        tv_time.setTextColor(0xFFF06D2F);
        tv_score.setText("Điểm của bạn:" + Totalscore);

        frontOfCardResources();

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogbuild = new AlertDialog.Builder(Game2Activity.this);
                dialogbuild.setMessage("Bạn có muốn lưu điểm không ?").setCancelable(false)
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
                AlertDialog alert = dialogbuild.create();
                alert.show();
            }
        });

        btnstart_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_15.setEnabled(true);
                iv_17.setEnabled(true);
                iv_16.setEnabled(true);
                iv_18.setEnabled(true);
                iv_19.setEnabled(true);
                iv_20.setEnabled(true);
                btnexit.setEnabled(true);

                if(mTimerunning){
                    stopTimer();
                    iv_15.setEnabled(false);
                    iv_16.setEnabled(false);
                    iv_17.setEnabled(false);
                    iv_18.setEnabled(false);
                    iv_19.setEnabled(false);
                    iv_20.setEnabled(false);
                }else {
                    startTimer();
                    iv_15.setEnabled(true);
                    iv_16.setEnabled(true);
                    iv_17.setEnabled(true);
                    iv_18.setEnabled(true);
                    iv_19.setEnabled(true);
                    iv_20.setEnabled(true);
                }
            }
        });
        ///shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));

        /// Event Image
        iv_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_15, theCard);
            }
        });
        iv_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_16, theCard);
            }
        });
        iv_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_17, theCard);
            }
        });
        iv_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_18, theCard);
            }
        });
        iv_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_19, theCard);
            }
        });
        iv_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(Game2Activity.this);
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_20, theCard);
            }
        });
    }
    private void doStuff(ImageView iv, int card) {
        /// set the correct image to the imageview
        if (cardsArray[card] == 101) {
            iv.setImageResource(image101);
        } else if (cardsArray[card] == 102) {
            iv.setImageResource(image102);
        } else if (cardsArray[card] == 103) {
            iv.setImageResource(image103);
        } else if (cardsArray[card] == 201) {
            iv.setImageResource(image201);
        }else if (cardsArray[card] == 202) {
            iv.setImageResource(image202);
        }else if (cardsArray[card] == 203) {
            iv.setImageResource(image203);
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

            iv_15.setEnabled(false);
            iv_16.setEnabled(false);
            iv_17.setEnabled(false);
            iv_18.setEnabled(false);
            iv_19.setEnabled(false);
            iv_20.setEnabled(false);

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
            music.Windraw(Game2Activity.this);
            if (clickedFirst == 0) {
                iv_15.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_16.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_17.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_18.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_19.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_20.setVisibility(View.INVISIBLE);
            }

            if (clickedSeconds == 0) {
                iv_15.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 1) {
                iv_16.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 2) {
                iv_17.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 3) {
                iv_18.setVisibility(View.INVISIBLE);
            }else if (clickedSeconds == 4) {
                iv_19.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 5) {
                iv_20.setVisibility(View.INVISIBLE);
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
            iv_15.setImageResource(R.drawable.randomic);
            iv_16.setImageResource(R.drawable.randomic);
            iv_17.setImageResource(R.drawable.randomic);
            iv_18.setImageResource(R.drawable.randomic);
            iv_19.setImageResource(R.drawable.randomic);
            iv_20.setImageResource(R.drawable.randomic);

        }
        iv_15.setEnabled(true);
        iv_16.setEnabled(true);
        iv_17.setEnabled(true);
        iv_18.setEnabled(true);
        iv_19.setEnabled(true);
        iv_20.setEnabled(true);

        // check if the player pass the current level
        checkPass();
    }
    private void checkPass(){
        if (iv_15.getVisibility() == View.INVISIBLE &&
                iv_16.getVisibility() == View.INVISIBLE &&
                iv_17.getVisibility() == View.INVISIBLE &&
                iv_18.getVisibility() == View.INVISIBLE &&
                iv_19.getVisibility() == View.INVISIBLE &&
                iv_20.getVisibility() == View.INVISIBLE){
            music.WinGame(Game2Activity.this);
            AlertDialog.Builder dialogbuiderd = new AlertDialog.Builder(Game2Activity.this);
            dialogbuiderd.setMessage("Chúc mừng bạn đã vượt qua vòng 2").setCancelable(false)
                    .setNegativeButton("Tiếp Tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intentgame3.putExtra("Key1",Totalscore);
                            finish();
                            startActivity(intentgame3);
                        }
                    });
            stopTimer(); // stop time when player pass the level
            AlertDialog alert = dialogbuiderd.create();
            alert.show();
        }
    }
    private void frontOfCardResources() {
        image101 = R.drawable.bird;
        image102 = R.drawable.mouse;
        image103 = R.drawable.cat;
        image201 = R.drawable.wbird;
        image202 = R.drawable.wmouse;
        image203 = R.drawable.wcat;
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
                music.Lose(Game2Activity.this);
                AlertDialog.Builder dialogbuildtimeup = new AlertDialog.Builder(Game2Activity.this);
                dialogbuildtimeup.setMessage("Thua rồi! Bạn có muốn chơi lại không ?").setCancelable(false)
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Totalscore = 0;
                                finish();
                                startActivity(intentnewgame);
                            }
                        }).setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = String.valueOf(Totalscore);
                        SaveS(score);
                        finish();
                        startActivity(intentmain);
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
                                Toast.makeText(Game2Activity.this, "Save Success!", Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Game2Activity.this, "Save Error!" + e.toString(), Toast.LENGTH_SHORT).show();

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
