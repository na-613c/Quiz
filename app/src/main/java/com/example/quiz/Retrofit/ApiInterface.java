package com.example.quiz.Retrofit;

import com.example.quiz.Retrofit.Model.RetroModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET()
    Observable<RetroModel> getString(@Url String str);

}

