package com.example.quiz;

import com.example.quiz.Data.PlayerInformation;

import java.util.Comparator;

public class PlayerInformationComparator implements Comparator<PlayerInformation> {

    public int compare(PlayerInformation player1, PlayerInformation player2) {

        if (player1.getNumberOfPoints().equals(player2.getNumberOfPoints())) {
            return 0;
        }

        if (player1.getNumberOfPoints() < player2.getNumberOfPoints()) {
            return 1;
        } else {
            return -1;
        }
    }
}