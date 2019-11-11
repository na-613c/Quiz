package com.example.quiz.Retrofit;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               02.11.2019             *
 ***************************************/

import retrofit2.Call;
import retrofit2.http.GET;

public interface HardInterface {

    @GET("api.php?amount=10&category=27&difficulty=hard&type=multiple")
    Call<String> getString();

}
