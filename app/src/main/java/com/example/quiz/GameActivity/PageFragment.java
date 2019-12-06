package com.example.quiz.GameActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.quiz.Data.Counter;
import com.example.quiz.Data.Launcher;
import com.example.quiz.Data.PlayerInformation;
import com.example.quiz.Data.QuizListModel;
import com.example.quiz.Firebase;
import com.example.quiz.MainActivity.MainActivity;
import com.example.quiz.R;
import com.example.quiz.Retrofit.Model.Result;
import com.example.quiz.Retrofit.Retrofit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PageFragment extends Fragment {

    private ArrayList<Button> allBtn = new ArrayList<>();

    private List<String> answersArr = new ArrayList<>();

    private int pageNumber;
    private int backColor;

    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private Counter answerCounter = Launcher.answerCounter;
    private PlayerInformation playerInformation = Launcher.playerInformation;
    private Counter pointCounter = Launcher.pointCounter;
    private Retrofit retrofit = Launcher.retrofit;


    public PageFragment() {
    }


    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER); // номер страницы

        Random rnd = new Random();
        backColor = Color.argb(250, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, null);


        FrameLayout background = (FrameLayout) view.findViewById(R.id.background);
        background.setBackgroundColor(backColor);

        final Button btnAnswer0 = (Button) view.findViewById(R.id.btnAnswer0);
        final Button btnAnswer1 = (Button) view.findViewById(R.id.btnAnswer1);
        final Button btnAnswer2 = (Button) view.findViewById(R.id.btnAnswer2);
        final Button btnAnswer3 = (Button) view.findViewById(R.id.btnAnswer3);
        final TextView question = (TextView) view.findViewById(R.id.question);
        final TextView question_number = (TextView) view.findViewById(R.id.question_number);


        Result quizInformationForPage = QuizListModel.quizList.get(pageNumber);


        question_number.setText("Question № " + ++pageNumber);
        question.setText(quizInformationForPage.getQuestion() + "");


        answersArr = quizInformationForPage.getIncorrectAnswers();
        answersArr.add(quizInformationForPage.getCategory());

        Collections.shuffle(answersArr);

        /** ************ выводим все ответы на кнопки **********  **/


        btnAnswer0.setText(answersArr.get(0));
        btnAnswer1.setText(answersArr.get(1));
        btnAnswer2.setText(answersArr.get(2));
        btnAnswer3.setText(answersArr.get(3));


        allBtn.add(btnAnswer0);
        allBtn.add(btnAnswer1);
        allBtn.add(btnAnswer2);
        allBtn.add(btnAnswer3);


        for (Button i : allBtn) {
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (view.getId()) {
                        case R.id.btnAnswer0:
                            check(btnAnswer0);
                            break;
                        case R.id.btnAnswer1:
                            check(btnAnswer1);
                            break;
                        case R.id.btnAnswer2:
                            check(btnAnswer2);
                            break;
                        case R.id.btnAnswer3:
                            check(btnAnswer3);
                            break;
                    }
                }


                private void check(Button button) {
                    disableButtons(allBtn);
                    int green = Color.rgb(100, 255, 0);
                    int red = Color.rgb(255, 0, 0);

                    if (button.getText().toString().equals(quizInformationForPage.getCorrectAnswer() + "")) { // правильный ответ
                        button.setBackgroundColor(green);
                        /** ****** счётчик ответов правильных ответов величился ****** **/
                        pointCounter.setCounter(pointCounter.getCounter() + 1);

                    } else { // ошибка
                        button.setBackgroundColor(red);
                        /** ****** счётчик правильных ответов не увеличился ****** **/
                        pointCounter.setCounter(pointCounter.getCounter());
                    }
                    /** ****** счётчик отвтов на вопросы ****** **/
                    answerCounter.setCounter(answerCounter.getCounter() + 1);

                    /** ****************** когда ответил на последний вопрос* ******************* **/
                    if (answerCounter.getCounter() >= 10) {

                        playerInformation.setNumberOfPoints(pointCounter.getCounter());

                        /** ****************** добавляем имя, если оно не указанно ******************* **/
                        if (playerInformation.getUserName().equals(""))
                            playerInformation.setUserName("NoName");

                        Firebase firebase = new Firebase();
                        firebase.writeToDatabase();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }

                public void disableButtons(ArrayList<Button> allBtn) {
                    for (Button i : allBtn) {
                        i.setEnabled(false);
                    }
                }


            });
        }


        return view;
    }


}