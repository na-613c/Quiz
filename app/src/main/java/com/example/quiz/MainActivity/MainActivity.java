package com.example.quiz.MainActivity;


import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;

public class MainActivity extends AppCompatActivity {


    private StartFragment startFragment;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFragment = new StartFragment();

        ft = getFragmentManager().beginTransaction();
        ft.add(R.id.frgmCont, startFragment);
        ft.commit();


    }

}
