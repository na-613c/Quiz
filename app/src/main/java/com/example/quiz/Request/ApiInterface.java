package com.example.quiz.Request;

import com.example.quiz.Request.Model.RetroModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET()
    Observable<RetroModel> getString(@Url String str);

}

