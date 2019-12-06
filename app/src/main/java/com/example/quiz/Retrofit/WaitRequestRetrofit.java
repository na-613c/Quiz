package com.example.quiz.Retrofit;

import android.util.Log;

import com.example.quiz.Data.Launcher;
import com.example.quiz.Data.PlayerInformation;
import com.example.quiz.Data.QuizListModel;

import java.util.concurrent.TimeUnit;

import static com.example.quiz.MainActivity.LoadFragment.internetMessage;

public class WaitRequestRetrofit {

    private Integer time = 0;
    private Retrofit retrofit = Launcher.retrofit;
    private PlayerInformation playerInformation = Launcher.playerInformation;

    public void timeOut() throws InterruptedException {

        request();

        TimeUnit.SECONDS.sleep(1);
        try {
            if (QuizListModel.quizList.size() != 10) this.timeOut();
        } catch (Exception e) {
            Log.d("err", "timeOut() : " + e);
        }

    }

    private void request() {
        time++;
        if (time.equals(5)) {
            time = 0;
            String mod = playerInformation.getMod();
            retrofit.getResponse(mod);

            internetMessage();
            Log.d("__________", " new request");

        }
    }


}
