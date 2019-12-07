package com.example.quiz.Data;

import com.example.quiz.Request.Model.Result;

import java.util.ArrayList;
import java.util.List;

public class QuizListModel {

    static public List<Result> quizList;

    public void newQuizList(){
        quizList = new ArrayList<>();
    }

}
