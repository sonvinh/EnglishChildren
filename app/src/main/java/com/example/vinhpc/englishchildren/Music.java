package com.example.vinhpc.englishchildren;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    MediaPlayer Nhacnen;
    MediaPlayer Click;
    MediaPlayer WinGame;
    MediaPlayer Windraw;
    MediaPlayer Lose;

    public void ClickSound(Context context){
        Click = MediaPlayer.create(context, R.raw.click);
        Click.start();
    }
    public void MainSound (Context context){
        Nhacnen = MediaPlayer.create(context, R.raw.game);
        Nhacnen.start();

        Nhacnen.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Nhacnen.start();
            }
        });
    }
    public void Pause(){
        Nhacnen.pause();
    }
    public void Windraw(Context context){
        Windraw = MediaPlayer.create(context, R.raw.nice);
        Windraw.start();
    }
    public  void WinGame(Context context){
        WinGame = MediaPlayer.create(context, R.raw.wingame1);
        WinGame.start();
    }
    public void Lose (Context context){
        Lose = MediaPlayer.create(context, R.raw.lose);
        Lose.start();
    }
}
