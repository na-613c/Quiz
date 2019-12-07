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
import com.example.quiz.Request.Model.Result;

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
    private Counter pointCounter = Launcher.pointCounter;
    private PlayerInformation playerInformation = Launcher.playerInformation;
    private Result quizInformationForPage;

    private Button btnAnswer0;
    private Button btnAnswer1;
    private Button btnAnswer2;
    private Button btnAnswer3;

    private TextView question;
    private TextView question_number;

    private FrameLayout background;

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

        background = (FrameLayout) view.findViewById(R.id.background);
        background.setBackgroundColor(backColor);

        question = (TextView) view.findViewById(R.id.question);
        question_number = (TextView) view.findViewById(R.id.question_number);

        btnAnswer0 = (Button) view.findViewById(R.id.btnAnswer0);
        btnAnswer1 = (Button) view.findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) view.findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) view.findViewById(R.id.btnAnswer3);

        allBtn.add(btnAnswer0);
        allBtn.add(btnAnswer1);
        allBtn.add(btnAnswer2);
        allBtn.add(btnAnswer3);



        question_number.setText("Question № " + (pageNumber+1));

        quizInformationForPage = QuizListModel.quizList.get(pageNumber);

        question.setText(quizInformationForPage.getQuestion() + "");

        answersArr = quizInformationForPage.getIncorrectAnswers();
        answersArr.add(quizInformationForPage.getCorrectAnswer());

        Collections.shuffle(answersArr);

        /** ************ выводим все ответы на кнопки **********  **/

        for (int j = 0; j < allBtn.size(); j++) {
            allBtn.get(j).setText(answersArr.get(j));
        }


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

                    if (button.getText().toString().equals(quizInformationForPage.getCorrectAnswer())) { // правильный ответ
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
                        if (playerInformation.getUserName().trim().equals(""))
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