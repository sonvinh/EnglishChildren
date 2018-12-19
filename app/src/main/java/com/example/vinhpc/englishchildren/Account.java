package com.example.vinhpc.englishchildren;

public class Account {
    public String username;
    public int score;

    public Account (String usernames, int scores){
        this.username = usernames;
        this.score = scores;
    }
    public String getUsername(){
        return  username;
    }
    public int getScore(){
        return score;
    }
}
