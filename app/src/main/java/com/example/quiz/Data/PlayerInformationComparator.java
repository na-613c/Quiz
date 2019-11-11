package com.example.quiz.Data;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               07.11.2019             *
 ***************************************/

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