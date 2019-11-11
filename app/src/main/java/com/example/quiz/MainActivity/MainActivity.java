package com.example.quiz.MainActivity;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               06.11.2019             *
 ***************************************/

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;

public class MainActivity extends AppCompatActivity {


    private ModFragment modFragment;
    private RecordFragment recordFragment;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modFragment = new ModFragment();

                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.frgmCont, modFragment);
                ft.commit();
            }
        });

        Button btnRecords = (Button) findViewById(R.id.btnRecords);
        btnRecords.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recordFragment = new RecordFragment();
                Bundle bundle = new Bundle();

                recordFragment.setArguments(bundle);
                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.frgmCont, recordFragment);
                ft.commit();
            }
        });


    }

}
