package com.example.quiz.Data;

import com.example.quiz.Retrofit.Retrofit;

public class Launcher {

    public static PlayerInformation playerInformation;
    public static Counter answerCounter;
    public static Counter pointCounter;
    public static Retrofit retrofit;


    public static void init() {
        playerInformation = new PlayerInformation();
        answerCounter = new Counter();
        pointCounter = new Counter();
        retrofit = new Retrofit();

    }
}
