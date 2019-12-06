package com.example.quiz.MainActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quiz.R;

public class StartFragment  extends Fragment {

    private ModFragment modFragment;
    private RecordFragment recordFragment;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fragment, null);

        Button btnPlay = (Button) v.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modFragment = new ModFragment();

                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.frgmCont, modFragment);
                ft.commit();
            }
        });

        Button btnRecords = (Button) v.findViewById(R.id.btnRecords);
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

        return v;
    }


}