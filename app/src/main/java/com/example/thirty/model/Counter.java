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
        List<Integer> diceValue = new ArrayList<>();
        for (Die die : mDice){
            diceValue.add(die.getValue());
        }
        switch(mScore){
            case "Choose score":
                break;
            case "Low":
                for (Integer i : diceValue){
                    if (i < 4){
                        result += i;
                    }
                }
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
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
