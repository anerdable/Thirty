package com.example.thirty.model;

import java.util.ArrayList;
import java.util.List;

public class Counter {

    private String mScore;
    private List<Die> mDice;
    private int result;

    public Counter(List<Die> dice, String score){
        mDice = dice;
        mScore = score;
        this.result = 0;
        countValue();
    }

    public int countValue(){
        switch(mScore){
            case "Choose score":
                break;
            case "Low":
                for (Die die : mDice){
                    if (die.getValue() < 4){
                        result += die.getValue();
                    }
                }
                break;
            case "4":
                for (Die die : mDice){
                    if (die.getValue() == 4){
                        result += die.getValue();
                        die.setUsed();
                    }
                }
                break;
            case "5":
                for (Die die : mDice){
                    if (die.getValue() == 5){
                        result += die.getValue();
                        die.setUsed();
                    }
                }
                break;
            case "6":
                for (Die die : mDice){
                    if (die.getValue() == 6){
                        result += die.getValue();
                        die.setUsed();
                    }
                }
                break;
            case "7":
                break;
            case "8":
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;

        }
        return result;
    }

    public int getResult(){
        return result;
    }

}
