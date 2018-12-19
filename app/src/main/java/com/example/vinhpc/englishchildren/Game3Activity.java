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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class Game3Activity extends AppCompatActivity {
    TextView tv_score, tv_time;
    Button btnstart_pause, btnexit;
    ImageView iv_21, iv_22, iv_23, iv_24, iv_25, iv_26, iv_27, iv_28;

    private static final long START_TIME_IN_MILLIS = 60000;

    Integer[] cardsArray = {101, 102, 103, 104, 201, 202, 203, 204};

    int image101, image102,image103,image104,  image201, image202,image203,image204;
    int firstCard, secondCard;
    int clickedFirst, clickedSeconds;
    int cardNumber = 1;


    Intent intentmain ,intentnewgame, intentgame4;

    public int Totalscore = 0;

    private CountDownTimer mCoundowntime;
    private long timeLeftinMilliseconds = START_TIME_IN_MILLIS;
    private boolean mTimerunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        tv_time = (TextView) findViewById(R.id.textView4);
        tv_score = (TextView) findViewById(R.id.textView3);

        btnstart_pause = (Button) findViewById(R.id.button9);
        btnexit = (Button) findViewById(R.id.button10);
        Intent ite = getIntent();
        Totalscore = ite.getIntExtra("Key1",0);
        intentmain = new Intent(this, MainScreeenActivity.class);
        intentnewgame = new Intent(this,NewGameActivity.class);
        intentgame4 = new Intent(this,Game4Activity.class);


        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);
        iv_25 = (ImageView) findViewById(R.id.iv_25);
        iv_26 = (ImageView) findViewById(R.id.iv_26);
        iv_27 = (ImageView) findViewById(R.id.iv_27);
        iv_28 = (ImageView) findViewById(R.id.iv_28);

        iv_21.setTag("0");
        iv_22.setTag("1");
        iv_23.setTag("2");
        iv_24.setTag("3");
        iv_25.setTag("4");
        iv_26.setTag("5");
        iv_27.setTag("6");
        iv_28.setTag("7");


        iv_21.setEnabled(false);
        iv_22.setEnabled(false);
        iv_23.setEnabled(false);
        iv_24.setEnabled(false);
        iv_25.setEnabled(false);
        iv_26.setEnabled(false);
        iv_27.setEnabled(false);
        iv_28.setEnabled(false);

        tv_score.setTextColor(Color.WHITE);
        tv_time.setTextColor(Color.WHITE);
        tv_score.setText("Điểm của bạn:" + Totalscore);

        frontOfCardResources();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(intentmain);
            }
        });

        btnstart_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_21.setEnabled(true);
                iv_22.setEnabled(true);
                iv_23.setEnabled(true);
                iv_24.setEnabled(true);
                iv_25.setEnabled(true);
                iv_26.setEnabled(true);
                iv_27.setEnabled(true);
                iv_28.setEnabled(true);

                if(mTimerunning){
                    stopTimer();

                    iv_21.setEnabled(false);
                    iv_22.setEnabled(false);
                    iv_23.setEnabled(false);
                    iv_24.setEnabled(false);
                    iv_25.setEnabled(false);
                    iv_26.setEnabled(false);
                    iv_27.setEnabled(false);
                    iv_28.setEnabled(false);
                }else {
                    startTimer();
                    iv_21.setEnabled(true);
                    iv_22.setEnabled(true);
                    iv_23.setEnabled(true);
                    iv_24.setEnabled(true);
                    iv_25.setEnabled(true);
                    iv_26.setEnabled(true);
                    iv_27.setEnabled(true);
                    iv_28.setEnabled(true);
                }
            }
        });
        ///shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));
        /// Event Image
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_21, theCard);
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_22, theCard);
            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_23, theCard);
            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_24, theCard);
            }
        });
        iv_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_25, theCard);
            }
        });
        iv_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_26, theCard);
            }
        });
        iv_27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_27, theCard);
            }
        });
        iv_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_28, theCard);
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
        }else if (cardsArray[card] == 104) {
            iv.setImageResource(image104);
        } else if (cardsArray[card] == 201) {
            iv.setImageResource(image201);
        }else if (cardsArray[card] == 202) {
            iv.setImageResource(image202);
        }else if (cardsArray[card] == 203) {
            iv.setImageResource(image203);
        }else if (cardsArray[card] == 204) {
            iv.setImageResource(image204);
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

            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_25.setEnabled(false);
            iv_26.setEnabled(false);
            iv_27.setEnabled(false);
            iv_28.setEnabled(false);

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
            if (clickedFirst == 0) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_25.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_26.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 6) {
                iv_27.setVisibility(View.INVISIBLE);
            }else if (clickedFirst == 7) {
                iv_28.setVisibility(View.INVISIBLE);
            }

            if (clickedSeconds == 0) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 1) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 2) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 3) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 4) {
                iv_25.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 5) {
                iv_26.setVisibility(View.INVISIBLE);
            }else if (clickedSeconds == 6) {
                iv_27.setVisibility(View.INVISIBLE);
            }else if (clickedSeconds == 7) {
                iv_28.setVisibility(View.INVISIBLE);
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
            iv_21.setImageResource(R.drawable.randomic);
            iv_22.setImageResource(R.drawable.randomic);
            iv_23.setImageResource(R.drawable.randomic);
            iv_24.setImageResource(R.drawable.randomic);
            iv_25.setImageResource(R.drawable.randomic);
            iv_26.setImageResource(R.drawable.randomic);
            iv_27.setImageResource(R.drawable.randomic);
            iv_28.setImageResource(R.drawable.randomic);

        }
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_25.setEnabled(true);
        iv_26.setEnabled(true);
        iv_27.setEnabled(true);
        iv_28.setEnabled(true);

        // check if the player pass the current level
        checkPass();
    }
    private void checkPass(){
        if (iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&
                iv_25.getVisibility() == View.INVISIBLE &&
                iv_26.getVisibility() == View.INVISIBLE&&
                iv_27.getVisibility() == View.INVISIBLE&&
                iv_28.getVisibility() == View.INVISIBLE){
            AlertDialog.Builder dialogbuider = new AlertDialog.Builder(Game3Activity.this);
            dialogbuider.setMessage("Chúc mừng bạn đã vượt qua vòng 3").setCancelable(false)
                    .setNegativeButton("Tiếp Tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            intentgame4.putExtra("Key1", Totalscore);
                            startActivity(intentgame4);
                        }
                    });
            stopTimer(); // stop time when player pass the level
            AlertDialog alert = dialogbuider.create();
            alert.show();
        }
    }
    private void frontOfCardResources() {
        image101 = R.drawable.shark;
        image102 = R.drawable.sheep;
        image103 = R.drawable.pig;
        image104 = R.drawable.turtle;
        image201 = R.drawable.wshark;
        image202 = R.drawable.wsheep;
        image203 = R.drawable.wpig;
        image204 = R.drawable.wturtle;
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
                AlertDialog.Builder dialogbuildtimeup = new AlertDialog.Builder(Game3Activity.this);
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
                        finish();
                        startActivity(intentmain);
                    }
                });
                AlertDialog alerttimeup = dialogbuildtimeup.create();
                alerttimeup.show();
            }
        }.start();
        mTimerunning = true;
        btnstart_pause.setText("Tạm Dừng");

    }
    public void stopTimer(){
        mCoundowntime.cancel();
        mTimerunning = false;
        btnstart_pause.setText("Bắt Đầu");
    }
    public void updateTimer(){
        int minute = (int) (timeLeftinMilliseconds / 1000 )/60;
        int seconds = (int)(timeLeftinMilliseconds / 1000 )%60;

        String timeLeftTextFormat = String.format(Locale.getDefault(),"%02d:%02d", minute, seconds);
        tv_time.setText(timeLeftTextFormat);
    }
}
