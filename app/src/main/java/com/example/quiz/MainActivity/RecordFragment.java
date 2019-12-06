package com.example.quiz.MainActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.Data.PlayerInformation;
import com.example.quiz.PlayerInformationComparator;
import com.example.quiz.R;
import com.example.quiz.RecyclerView.DataAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends Fragment {


    private List<PlayerInformation> gameRecords = new ArrayList<>();
    private ModFragment modFragment;
    private DatabaseReference myRef;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.record_fragment, null);



        Button btnPlay = (Button) v.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modFragment = new ModFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, modFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list);

        /******************** создаем адаптер *******************/
        final DataAdapter adapter = new DataAdapter(inflater.getContext(), gameRecords);
        /**************** устанавливаем для списка адаптер **************/
        recyclerView.setAdapter(adapter);

        /*************************** firebase ***************************/
        myRef = FirebaseDatabase.getInstance().getReference();
        Query myQuery = myRef;
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {
                PlayerInformation playerInformationFromDb = dataSnapshot.getValue(PlayerInformation.class);

                assert playerInformationFromDb != null;

                gameRecords.add(playerInformationFromDb);

                PlayerInformationComparator playerInformationComparator = new PlayerInformationComparator();
                gameRecords.sort(playerInformationComparator);

                adapter.updateItems();

                int black = Color.rgb(255, 255, 255);
                v.findViewById(R.id.list).setBackgroundColor(black);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }


}