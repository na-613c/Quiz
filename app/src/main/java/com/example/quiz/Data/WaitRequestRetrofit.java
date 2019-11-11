package com.example.quiz.Data;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               08.11.2019             *
 ***************************************/

import android.util.Log;

import com.example.quiz.Retrofit.Retrofit;

import java.util.concurrent.TimeUnit;

public class WaitRequestRetrofit {

    private Integer time = 0;
    private Retrofit retrofit = Launcher.retrofit;
    private PlayerInformation playerInformation = Launcher.playerInformation;

    public void timeOut() throws InterruptedException {
        Retrofit retrofit = Launcher.retrofit;

        request();

        TimeUnit.SECONDS.sleep(1);
        try {
            if (retrofit.getListAllAnswers().size() != 10) this.timeOut();
        } catch (Exception e) {
            Log.d("err", "timeOut() : " + e);
        }

    }

    private void request() {
        time++;
        if (time == 15) {
            time = 0;
            String mod = playerInformation.getMod();
            retrofit.getResponse(mod);

            Log.d("__________", " new request");

        }
    }


}
