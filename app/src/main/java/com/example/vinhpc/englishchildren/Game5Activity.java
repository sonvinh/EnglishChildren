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

public class Game5Activity extends AppCompatActivity {
    TextView tv_score, tv_time;
    Button btnstart_pause, btnexit;
    ImageView iv_41, iv_42, iv_43, iv_44, iv_45, iv_46, iv_47, iv_48, iv_49, iv_50, iv_51, iv_52, iv_53,iv_54,iv_55,iv_56;

    private static final long START_TIME_IN_MILLIS = 60000;

    Integer[] cardsArray = {101, 102, 103, 104, 105, 106,107,108, 201, 202, 203, 204,205,206,207,208};
    int image101, image102,image103,image104,image105, image106,image107,image108, image201, image202,image203,image204,image205, image206,image207,image208;
    int firstCard, secondCard;
    int clickedFirst, clickedSeconds;
    int cardNumber = 1;

    Intent intentmain ,intentnewgame;

    public int Totalscore = 0;

    private CountDownTimer mCoundowntime;
    private long timeLeftinMilliseconds = START_TIME_IN_MILLIS;
    private boolean mTimerunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        tv_time = (TextView) findViewById(R.id.textView8);
        tv_score = (TextView) findViewById(R.id.textView7);

        btnstart_pause = (Button) findViewById(R.id.button13);
        btnexit = (Button) findViewById(R.id.button14);

        Intent ite = getIntent();
        Totalscore = ite.getIntExtra("Key1", 0);
        intentmain = new Intent(this, MainScreeenActivity.class);
        intentnewgame = new Intent(this,NewGameActivity.class);

        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);
        iv_44 = (ImageView) findViewById(R.id.iv_44);
        iv_45 = (ImageView) findViewById(R.id.iv_45);
        iv_46 = (ImageView) findViewById(R.id.iv_46);
        iv_47 = (ImageView) findViewById(R.id.iv_47);
        iv_48 = (ImageView) findViewById(R.id.iv_48);
        iv_49 = (ImageView) findViewById(R.id.iv_49);
        iv_50 = (ImageView) findViewById(R.id.iv_50);
        iv_51 = (ImageView) findViewById(R.id.iv_51);
        iv_52 = (ImageView) findViewById(R.id.iv_52);
        iv_53 = (ImageView) findViewById(R.id.iv_53);
        iv_54 = (ImageView) findViewById(R.id.iv_54);
        iv_55 = (ImageView) findViewById(R.id.iv_55);
        iv_56 = (ImageView) findViewById(R.id.iv_56);

        iv_41.setTag("0");
        iv_42.setTag("1");
        iv_43.setTag("2");
        iv_44.setTag("3");
        iv_45.setTag("4");
        iv_46.setTag("5");
        iv_47.setTag("6");
        iv_48.setTag("7");
        iv_49.setTag("8");
        iv_50.setTag("9");
        iv_51.setTag("10");
        iv_52.setTag("11");
        iv_53.setTag("12");
        iv_54.setTag("13");
        iv_55.setTag("14");
        iv_56.setTag("15");

        iv_41.setEnabled(false);
        iv_42.setEnabled(false);
        iv_43.setEnabled(false);
        iv_44.setEnabled(false);
        iv_45.setEnabled(false);
        iv_46.setEnabled(false);
        iv_47.setEnabled(false);
        iv_48.setEnabled(false);
        iv_49.setEnabled(false);
        iv_50.setEnabled(false);
        iv_51.setEnabled(false);
        iv_52.setEnabled(false);
        iv_53.setEnabled(false);
        iv_54.setEnabled(false);
        iv_55.setEnabled(false);
        iv_56.setEnabled(false);

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
                iv_41.setEnabled(true);
                iv_42.setEnabled(true);
                iv_43.setEnabled(true);
                iv_44.setEnabled(true);
                iv_45.setEnabled(true);
                iv_46.setEnabled(true);
                iv_47.setEnabled(true);
                iv_48.setEnabled(true);
                iv_49.setEnabled(true);
                iv_50.setEnabled(true);
                iv_51.setEnabled(true);
                iv_52.setEnabled(true);
                iv_53.setEnabled(true);
                iv_54.setEnabled(true);
                iv_55.setEnabled(true);
                iv_56.setEnabled(true);

                if(mTimerunning){
                    stopTimer();
                }else {
                    startTimer();
                }
            }
        });
        Collections.shuffle(Arrays.asList(cardsArray));

        iv_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_41, theCard);
            }
        });
        iv_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_42, theCard);
            }
        });
        iv_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_43, theCard);
            }
        });
        iv_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_44, theCard);
            }
        });
        iv_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_45, theCard);
            }
        });
        iv_46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_46, theCard);
            }
        });
        iv_47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_47, theCard);
            }
        });
        iv_48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_48, theCard);
            }
        });
        iv_49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_49, theCard);
            }
        });
        iv_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_50, theCard);
            }
        });
        iv_51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_51, theCard);
            }
        });
        iv_52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_52, theCard);
            }
        });
        iv_53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_53, theCard);
            }
        });
        iv_54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_54, theCard);
            }
        });
        iv_55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_55, theCard);
            }
        });
        iv_56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(iv_56, theCard);
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
        }else if (cardsArray[card] == 107) {
            iv.setImageResource(image107);
        }else if (cardsArray[card] == 108) {
            iv.setImageResource(image108);
        } else if (cardsArray[card] == 201) {
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
        }else if (cardsArray[card] == 207) {
            iv.setImageResource(image207);
        }else if (cardsArray[card] == 208) {
            iv.setImageResource(image208);
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

            iv_41.setEnabled(false);
            iv_42.setEnabled(false);
            iv_43.setEnabled(false);
            iv_44.setEnabled(false);
            iv_45.setEnabled(false);
            iv_46.setEnabled(false);
            iv_47.setEnabled(false);
            iv_48.setEnabled(false);
            iv_49.setEnabled(false);
            iv_50.setEnabled(false);
            iv_51.setEnabled(false);
            iv_52.setEnabled(false);
            iv_53.setEnabled(false);
            iv_54.setEnabled(false);
            iv_55.setEnabled(false);
            iv_56.setEnabled(false);

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
                iv_41.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_42.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_43.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_44.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_45.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_46.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_47.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_48.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv_49.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_50.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_51.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_52.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 12) {
                iv_53.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 13) {
                iv_54.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 14) {
                iv_55.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 15) {
                iv_56.setVisibility(View.INVISIBLE);
            }

            if (clickedSeconds == 0) {
                iv_41.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 1) {
                iv_42.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 2) {
                iv_43.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 3) {
                iv_44.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 4) {
                iv_45.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 5) {
                iv_46.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 6) {
                iv_47.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 7) {
                iv_48.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 8) {
                iv_49.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 9) {
                iv_50.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 10) {
                iv_51.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 11) {
                iv_52.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 12) {
                iv_53.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 13) {
                iv_54.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 14) {
                iv_55.setVisibility(View.INVISIBLE);
            } else if (clickedSeconds == 15) {
                iv_56.setVisibility(View.INVISIBLE);
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
            iv_41.setImageResource(R.drawable.randomic);
            iv_42.setImageResource(R.drawable.randomic);
            iv_43.setImageResource(R.drawable.randomic);
            iv_44.setImageResource(R.drawable.randomic);
            iv_45.setImageResource(R.drawable.randomic);
            iv_46.setImageResource(R.drawable.randomic);
            iv_47.setImageResource(R.drawable.randomic);
            iv_48.setImageResource(R.drawable.randomic);
            iv_49.setImageResource(R.drawable.randomic);
            iv_50.setImageResource(R.drawable.randomic);
            iv_51.setImageResource(R.drawable.randomic);
            iv_52.setImageResource(R.drawable.randomic);
            iv_53.setImageResource(R.drawable.randomic);
            iv_54.setImageResource(R.drawable.randomic);
            iv_55.setImageResource(R.drawable.randomic);
            iv_56.setImageResource(R.drawable.randomic);

        }
        iv_41.setEnabled(true);
        iv_42.setEnabled(true);
        iv_43.setEnabled(true);
        iv_44.setEnabled(true);
        iv_45.setEnabled(true);
        iv_46.setEnabled(true);
        iv_47.setEnabled(true);
        iv_48.setEnabled(true);
        iv_49.setEnabled(true);
        iv_50.setEnabled(true);
        iv_51.setEnabled(true);
        iv_52.setEnabled(true);
        iv_53.setEnabled(true);
        iv_54.setEnabled(true);
        iv_55.setEnabled(true);
        iv_56.setEnabled(true);

        // check if the player pass the current level
        checkPass();

    }
    private void checkPass(){
        if (iv_41.getVisibility() == View.INVISIBLE &&
                iv_42.getVisibility() == View.INVISIBLE &&
                iv_43.getVisibility() == View.INVISIBLE &&
                iv_44.getVisibility() == View.INVISIBLE &&
                iv_45.getVisibility() == View.INVISIBLE &&
                iv_46.getVisibility() == View.INVISIBLE&&
                iv_47.getVisibility() == View.INVISIBLE&&
                iv_48.getVisibility() == View.INVISIBLE&&
                iv_49.getVisibility() == View.INVISIBLE&&
                iv_50.getVisibility() == View.INVISIBLE&&
                iv_51.getVisibility() == View.INVISIBLE&&
                iv_52.getVisibility() == View.INVISIBLE&&
                iv_53.getVisibility() == View.INVISIBLE&&
                iv_54.getVisibility() == View.INVISIBLE&&
                iv_55.getVisibility() == View.INVISIBLE&&
                iv_56.getVisibility() == View.INVISIBLE){
            AlertDialog.Builder dialogbuider = new AlertDialog.Builder(Game5Activity.this);
            dialogbuider.setMessage("Chúc mừng bạn đã hoàn tất khóa học về động vật và trái cây của English Children Game").setCancelable(false)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            stopTimer(); // stop time when player pass the level
            AlertDialog alert = dialogbuider.create();
            alert.show();
        }
    }
    private void frontOfCardResources() {
        image101 = R.drawable.cow;
        image102 = R.drawable.crab;
        image103 = R.drawable.duck;
        image104 = R.drawable.kiwi;
        image105 = R.drawable.lobster;
        image106 = R.drawable.pimiento;
        image107 = R.drawable.pumpkin;
        image108 = R.drawable.whale;
        image201= R.drawable.wcow;
        image202 = R.drawable.wcrab;
        image203 = R.drawable.wduck;
        image204 = R.drawable.wkiwi;
        image205 = R.drawable.wlobster;
        image206 = R.drawable.wpimiento;
        image207 = R.drawable.wpumpkin;
        image208 = R.drawable.wwhale;
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
                AlertDialog.Builder dialogbuildtimeup = new AlertDialog.Builder(Game5Activity.this);
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
