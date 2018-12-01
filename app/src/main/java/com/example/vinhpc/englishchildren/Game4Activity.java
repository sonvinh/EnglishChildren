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

public class Game4Activity extends AppCompatActivity {
    TextView tv_score, tv_time;
    Button btnstart_pause, btnexit;
    ImageView iv_29, iv_30, iv_31, iv_32, iv_33, iv_34, iv_35, iv_36, iv_37, iv_38, iv_39, iv_40;

    private static final long START_TIME_IN_MILLIS = 60000;

    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204,205,206};

    int image101, image102,image103,image104,image105, image106, image201, image202,image203,image204,image205, image206;
    int firstCard, secondCard;
    int clickedFirst, clickedSeconds;
    int cardNumber = 1;

    Intent intentmain ,intentnewgame,intentgame5;

    public int Totalscore = 0;

    private CountDownTimer mCoundowntime;
    private long timeLeftinMilliseconds = START_TIME_IN_MILLIS;
    private boolean mTimerunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        tv_time = (TextView) findViewById(R.id.textView6);
        tv_score = (TextView) findViewById(R.id.textView5);

        btnstart_pause = (Button) findViewById(R.id.button11);
        btnexit = (Button) findViewById(R.id.button12);

        Intent ite = getIntent();
        Totalscore = ite.getIntExtra("Key1", 0);
        intentmain = new Intent(this, MainScreeenActivity.class);
        intentnewgame = new Intent(this,NewGameActivity.class);
        intentgame5 = new Intent(this,Game5Activity.class);

        iv_29 = (ImageView) findViewById(R.id.iv_29);
        iv_30 = (ImageView) findViewById(R.id.iv_30);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_34 = (ImageView) findViewById(R.id.iv_34);
        iv_35 = (ImageView) findViewById(R.id.iv_35);
        iv_36 = (ImageView) findViewById(R.id.iv_36);
        iv_37 = (ImageView) findViewById(R.id.iv_37);
        iv_38 = (ImageView) findViewById(R.id.iv_38);
        iv_39 = (ImageView) findViewById(R.id.iv_39);
        iv_40 = (ImageView) findViewById(R.id.iv_40);


        iv_29.setTag("0");
        iv_30.setTag("1");
        iv_31.setTag("2");
        iv_32.setTag("3");
        iv_33.setTag("4");
        iv_34.setTag("5");
        iv_35.setTag("6");
        iv_36.setTag("7");
        iv_37.setTag("8");
        iv_38.setTag("9");
        iv_39.setTag("10");
        iv_40.setTag("11");


        iv_29.setEnabled(false);
        iv_30.setEnabled(false);
        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        iv_34.setEnabled(false);
        iv_35.setEnabled(false);
        iv_36.setEnabled(false);
        iv_37.setEnabled(false);
        iv_38.setEnabled(false);
        iv_39.setEnabled(false);
        iv_40.setEnabled(false);

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
                iv_29.setEnabled(true);
                iv_30.setEnabled(true);
                iv_31.setEnabled(true);
                iv_32.setEnabled(true);
                iv_33.setEnabled(true);
                iv_34.setEnabled(true);
                iv_35.setEnabled(true);
                iv_36.setEnabled(true);
                iv_37.setEnabled(true);
                iv_38.setEnabled(true);
                iv_39.setEnabled(true);
                iv_40.setEnabled(true);

                if(mTimerunning){
                    stopTimer();
                }else {
                    startTimer();
                }
            }
        });

        ///shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));
        /// Event Image
        iv_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_29, theCard);
            }
        });
        iv_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_30, theCard);
            }
        });
        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_31, theCard);
            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_32, theCard);
            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_33, theCard);
            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_34, theCard);
            }
        });
        iv_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_35, theCard);
            }
        });
        iv_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_36, theCard);
            }
        });
        iv_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_37, theCard);
            }
        });
        iv_38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_38, theCard);
            }
        });
        iv_39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_39, theCard);
            }
        });
        iv_40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_40, theCard);
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
        } else if (cardsArray[card] == 105) {
            iv.setImageResource(image105);
        }else if (cardsArray[card] == 106) {
            iv.setImageResource(image106);
        }else if (cardsArray[card] == 201) {
            iv.setImageResource(image201);
        }else if (cardsArray[card] == 202) {
            iv.setImageResource(image202);
        }else if (cardsArray[card] == 203) {
            iv.setImageResource(image203);
        }else if (cardsArray[card] == 204) {
            iv.setImageResource(image204);
        }else if (cardsArray[card] == 205) {
            iv.setImageResource(image205);
        }else if (cardsArray[card] == 206) {
            iv.setImageResource(image206);
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

            iv_29.setEnabled(false);
            iv_30.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);
            iv_35.setEnabled(false);
            iv_36.setEnabled(false);
            iv_37.setEnabled(false);
            iv_38.setEnabled(false);
            iv_39.setEnabled(false);
            iv_40.setEnabled(false);

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
                iv_29.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_30.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_34.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_35.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_36.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv_37.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_38.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_39.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_40.setVisibility(View.INVISIBLE);
            }

            if (clickedSeconds == 0) {
                iv_29.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 1) {
                iv_30.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 2) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 3) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 4) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 5) {
                iv_34.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 6) {
                iv_35.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 7) {
                iv_36.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 8) {
                iv_37.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 9) {
                iv_38.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 10) {
                iv_39.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 11) {
                iv_40.setVisibility(View.INVISIBLE);
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
            iv_29.setImageResource(R.drawable.randomic);
            iv_30.setImageResource(R.drawable.randomic);
            iv_31.setImageResource(R.drawable.randomic);
            iv_32.setImageResource(R.drawable.randomic);
            iv_33.setImageResource(R.drawable.randomic);
            iv_34.setImageResource(R.drawable.randomic);
            iv_35.setImageResource(R.drawable.randomic);
            iv_36.setImageResource(R.drawable.randomic);
            iv_37.setImageResource(R.drawable.randomic);
            iv_38.setImageResource(R.drawable.randomic);
            iv_39.setImageResource(R.drawable.randomic);
            iv_40.setImageResource(R.drawable.randomic);

        }
        iv_29.setEnabled(true);
        iv_30.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);
        iv_35.setEnabled(true);
        iv_36.setEnabled(true);
        iv_37.setEnabled(true);
        iv_38.setEnabled(true);
        iv_39.setEnabled(true);
        iv_40.setEnabled(true);

        // check if the player pass the current level
        checkPass();

    }
    private void checkPass(){
        if (iv_29.getVisibility() == View.INVISIBLE &&
                iv_30.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE&&
                iv_35.getVisibility() == View.INVISIBLE&&
                iv_36.getVisibility() == View.INVISIBLE&&
                iv_37.getVisibility() == View.INVISIBLE&&
                iv_38.getVisibility() == View.INVISIBLE&&
                iv_39.getVisibility() == View.INVISIBLE&&
                iv_40.getVisibility() == View.INVISIBLE){
            AlertDialog.Builder dialogbuider = new AlertDialog.Builder(Game4Activity.this);
            dialogbuider.setMessage("Chúc mừng bạn đã vượt qua vòng 4").setCancelable(false)
                    .setNegativeButton("Tiếp Tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            intentgame5.putExtra("Key1", Totalscore);
                            startActivity(intentgame5);
                        }
                    });
            stopTimer(); // stop time when player pass the level
            AlertDialog alert = dialogbuider.create();
            alert.show();
        }
    }
    private void frontOfCardResources() {
        image101 = R.drawable.lemon;
        image102 = R.drawable.frog;
        image103 = R.drawable.fish;
        image104 = R.drawable.dolphin;
        image105 = R.drawable.dog;
        image106 = R.drawable.horse;
        image201= R.drawable.wlemon;
        image202 = R.drawable.wfrog;
        image203 = R.drawable.wfish;
        image204 = R.drawable.wdolphin;
        image205 = R.drawable.wdog;
        image206 = R.drawable.whorse;
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
                AlertDialog.Builder dialogbuildtimeup = new AlertDialog.Builder(Game4Activity.this);
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
