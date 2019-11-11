package com.example.quiz.Data;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               06.11.2019             *
 ***************************************/

import java.io.Serializable;

public class PlayerInformation implements Serializable {

    private String userName;
    private String mod;
    private Integer numberOfPoints;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }


}
