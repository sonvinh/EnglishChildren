package com.example.vinhpc.englishchildren;

public class Account {
    public String username;
    public int score;
    public String time;

    public Account (String usernames, int scores, String times){
        this.username = usernames;
        this.score = scores;
        this.time = times;
    }
    public String getUsername(){
        return  username;
    }
    public int getScore(){
        return score;
    }
    public String getTime(){
        return time;
    }
}
