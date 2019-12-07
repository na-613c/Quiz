package com.example.quiz.Data;

import com.example.quiz.Request.NetworkRequest;

public class Launcher {

    public static PlayerInformation playerInformation;
    public static Counter answerCounter;
    public static Counter pointCounter;
    public static NetworkRequest networkRequest;


    public static void init() {
        playerInformation = new PlayerInformation();
        answerCounter = new Counter();
        pointCounter = new Counter();
        networkRequest = new NetworkRequest();

    }
}
