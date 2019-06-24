package com.example.thirty.model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Counter {

    private String mScore;
    private List<Die> mDice;
    private int result;
    private List<Die> unused = new ArrayList<>();

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
                findBestCombination(4);
                break;
            case "5":
                findBestCombination(5);
                break;
            case "6":
                findBestCombination(6);
                break;
            case "7":
                findBestCombination(7);
                break;
            case "8":
                findBestCombination(8);
                break;
            case "9":
                findBestCombination(9);
                break;
            case "10":
                findBestCombination(10);
                break;
            case "11":
                findBestCombination(11);
                break;
            case "12":
                findBestCombination(12);
                break;

        }
        return result;
    }

    private int findBestCombination(int points){
        for (Die die : mDice) {
            if (die.getValue() == points) {
                result += die.getValue();
                Log.d("hejdå", "tar bort " + die.getValue());
            } else {
                unused.add(die);
            }
        }
        int toAdd = combineUnused(unused);
        return result += toAdd;
    }

    private int combineUnused(List<Die> dice){
        int toAdd = 0;
        return toAdd;
    }


    public int getResult(){
        return result;
    }

}
