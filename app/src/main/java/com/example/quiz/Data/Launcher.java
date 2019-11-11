package com.example.quiz.Data;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               07.11.2019             *
 ***************************************/

import com.example.quiz.Retrofit.Retrofit;

public class Launcher {

    public static PlayerInformation playerInformation;
    public static AnswerCounter answerCounter;
    public static PointCounter pointCounter;
    public static Retrofit retrofit;


    public static void init() {
        playerInformation = new PlayerInformation();
        answerCounter = new AnswerCounter();
        pointCounter = new PointCounter();
        retrofit = new Retrofit();

    }
}
