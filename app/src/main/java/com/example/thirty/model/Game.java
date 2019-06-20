package com.example.thirty.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int ROUNDS = 10;
    private int roll, round;
    private int[] score;
    private List<Die> dice;
    private List<String> options;
    private boolean gameOver;

    public Game(List<Die> newDice){
        this.dice = newDice;
        this.roll = 0;
        this.round = 0;
        this.score = new int[ROUNDS];
        enableOptions();
    }

    private void enableOptions(){
        options = new ArrayList<>();
        options.add("Choose score");
        options.add("Low");
        for (int i = 4; i < 13; i++){
            options.add("" + i + "");
        }
    }

    public List<String> getOptions(){
        return options;
    }

    public void removeOption(int option){
        options.remove(option);
    }

    public int getRoll(){
        return roll;
    }

    public int getRound(){
        return round;
    }

    public int[] getScore(){
        return score;
    }

    public List<Die> getDice(){
        return dice;
    }

    public void setScore(int index, int points){
        Log.d("hej", "poäng för " + index + " är " + points);
        score[index] = points;
    }

    public List<Die> newRoll(){
        if (roll > 2){
            newRound();
        }
        roll++;
        Log.d("Hej", "nytt kast" + roll);
        for (Die die : dice){
            if (!die.isIdle()){
                die.roll();
            }
        }
        return dice;
    }

    public void newRound(){
        Log.d("Hej", "ny runda" + round);
        round++;
        roll = 0;
    }

    public boolean gameOver(){
        Log.d("hej", "game over");
        roll = 0;
        round = 0;
        for (Integer i : score){
            i = 0;
        }
        return gameOver;
    }



}
