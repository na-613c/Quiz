package com.example.quiz.Data;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               09.11.2019             *
 ***************************************/

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
