package com.example.quiz;

import com.example.quiz.Data.Launcher;
import com.example.quiz.Data.PlayerInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {

    private PlayerInformation playerInformation = Launcher.playerInformation;
    private DatabaseReference myRef;

    public void writeToDatabase() {
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.push().setValue(playerInformation);

    }
}
