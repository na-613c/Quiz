package com.example.quiz.Request;

import android.util.Log;

import com.example.quiz.Data.QuizListModel;

import java.util.concurrent.TimeUnit;

public class Expectation {

    public void timeOut() throws InterruptedException {

        TimeUnit.SECONDS.sleep(1);
        try {
            if (QuizListModel.quizList.size() != 10) this.timeOut();
        } catch (Exception e) {
            Log.d("err", "timeOut() : " + e);
        }

    }

}
