package com.example.quiz.MainActivity;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.Data.Launcher;
import com.example.quiz.Data.PlayerInformation;
import com.example.quiz.Retrofit.WaitRequestRetrofit;
import com.example.quiz.GameActivity.GameActivity;
import com.example.quiz.R;
import com.example.quiz.Retrofit.Retrofit;

import java.util.ArrayList;

public class ModFragment extends Fragment {

    private LoadFragment loadFragment;
    private FragmentTransaction ft;

    private Button btnPlayEasy;
    private Button btnPlayMedium;
    private Button btnPlayHard;

    private ArrayList<Button> modBtn = new ArrayList<>();

    private TextView name;

    private PlayerInformation playerInformation;
    private Retrofit retrofit;
    private String userName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mod_fragment, null);


        Launcher.init();
        playerInformation = Launcher.playerInformation;
        retrofit = Launcher.retrofit;

        btnPlayEasy = (Button) v.findViewById(R.id.btnPlayEasy);
        btnPlayMedium = (Button) v.findViewById(R.id.btnPlayMedium);
        btnPlayHard = (Button) v.findViewById(R.id.btnPlayHard);

        modBtn.add(btnPlayEasy);
        modBtn.add(btnPlayMedium);
        modBtn.add(btnPlayHard);

        /** ****************** Выполнение Retrofit ******************* **/
        @SuppressLint("StaticFieldLeak")
        class TaskToQueryTheNetwork extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Void doInBackground(Void... params) {
                /** ****************** фрагмент загрузки ******************* **/
                retrofit.getResponse(playerInformation.getMod());
                loadFragment = new LoadFragment();
                Bundle bundle = new Bundle();

                loadFragment.setArguments(bundle);
                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.frgmCont, loadFragment);
                ft.commit();

                /** ****************** Запуск ожидания загрузки ******************* **/
                WaitRequestRetrofit waitRequest = new WaitRequestRetrofit();

                try {
                    waitRequest.timeOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        }

        final TaskToQueryTheNetwork taskToQueryTheNetwork = new TaskToQueryTheNetwork();


        btnPlayEasy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = (TextView) getActivity().findViewById(R.id.name);
                disableButtons();

                userName = name.getText().toString();

                playerInformation.setUserName(userName);
                playerInformation.setMod("Easy");

                taskToQueryTheNetwork.execute();

            }
        });


        btnPlayMedium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = (TextView) getActivity().findViewById(R.id.name);

                disableButtons();

                userName = name.getText().toString();

                playerInformation.setUserName(userName);
                playerInformation.setMod("Medium");

                taskToQueryTheNetwork.execute();

            }
        });


        btnPlayHard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = (TextView) getActivity().findViewById(R.id.name);

                disableButtons();

                userName = name.getText().toString();

                playerInformation.setUserName(userName);
                playerInformation.setMod("Hard");

                taskToQueryTheNetwork.execute();
            }
        });

        return v;
    }

    public void disableButtons() {
        for (Button i : modBtn) {

            /** ********************** прячем клаву и делаем кнопки не нажимаемые ************************ **/
            try {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(i.getWindowToken(), 0);

            } catch (Exception e) {
                e.printStackTrace();
            }

            i.setEnabled(false);
        }
    }




}