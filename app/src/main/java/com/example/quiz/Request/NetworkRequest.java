package com.example.quiz.Request;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quiz.Data.QuizListModel;
import com.example.quiz.Request.Model.Result;
import com.example.quiz.Request.Model.RetroModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.quiz.MainActivity.LoadFragment.internetMessage;

public class NetworkRequest {

    private Observable<RetroModel> observable;
    private int requestCounter = 299;

    public void getResponse(String mod) {

        retrofit2.Retrofit retrofit;

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())     //В 2.0.0+, вам нужно явно указать, что вы хотите конвертер Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //RXjava2 */
                .build();

        QuizListModel quizListModel = new QuizListModel();
        quizListModel.newQuizList();

        ApiInterface api = retrofit.create(ApiInterface.class);

        observable = api.getString("api.php?amount=10&category=27&difficulty=" + mod.toLowerCase() + "&type=multiple");

        observable.subscribeOn(Schedulers.newThread()) //отдаем новый тред для работы в background
                .observeOn(AndroidSchedulers.mainThread()) //говорим, что обсервить хотим в main thread
                .subscribe(new Observer<RetroModel>() {

                   @Override
                   public void onSubscribe(Disposable d) {
                       Log.d("__rx_1", "onSubscribe" + d.toString());
                   }

                   @Override
                   public void onNext(com.example.quiz.Request.Model.RetroModel value) {

                       for (int i = 0; i < 10; i++) {
                           Result resultQuizList = value.getResults().get(i);

                           resultQuizList.setQuestion(fixHtmlText(resultQuizList.getQuestion()));
                           resultQuizList.setCorrectAnswer(fixHtmlText(resultQuizList.getCorrectAnswer()));

                           List<String> newIncorrectAnswers = new ArrayList<>();
                           for (int j = 0; j < 3; j++) {
                               String incorrectAnswer = resultQuizList.getIncorrectAnswers().get(j);
                               newIncorrectAnswers.add(fixHtmlText(incorrectAnswer));
                           }
                           resultQuizList.setIncorrectAnswers(newIncorrectAnswers);

                           QuizListModel.quizList.add(resultQuizList); //заполняем список для квиза
                       }


                       Log.d("__rx_2", "onNext");
                   }

                   @Override
                   public void onError(Throwable e) {
                       String errorInternetConnection = "java.net.UnknownHostException: Unable to resolve host \"opentdb.com\": No address associated with hostname";

                       if (e.toString().equals(errorInternetConnection)) {

                           requestCounter++;

                           if (requestCounter == 300) {
                               requestCounter = 0;
                               internetMessage();
                           }

                           getResponse(mod);
                       }
                       Log.d("__rx_3", " onError № " + requestCounter + " " + e);

                   }

                   @Override
                   public void onComplete() {
                       Log.d("__rx_4", "onComplete");
                   }
               });


    }

    @NonNull
    private String fixHtmlText(String string) {
        return string.replaceAll("&rsquo;", "’")
                     .replaceAll("\u0101", "a")
                     .replaceAll("\u014d", "o")
                     .replaceAll("&quot;", "“")
                     .replaceAll("&#039;", "'");
    }


}