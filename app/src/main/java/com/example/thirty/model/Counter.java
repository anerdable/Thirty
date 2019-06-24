package com.example.thirty.model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Counter {

    private String mTarget;
    private List<Die> mDice;
    private int result;
    private List<Die> unused = new ArrayList<>();

    public Counter(List<Die> dice, String target){
        mDice = dice;
        mTarget = target;
        this.result = 0;
        countValue();
    }

    public int countValue(){
        switch(mTarget){
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
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
                findBestCombination();
                break;

        }
        return result;
    }

    private int findBestCombination() {
        int target = parseInt(mTarget);
        for (Die die : mDice) {
            if (die.getValue() == target) {
                result += die.getValue();
            } else if (die.getValue() < target) {
                unused.add(die);
            }
        }
        int toAdd = 0;
        for (Die d : unused) {
            Log.d("hej", "unused " + d.getValue() + " " + unused.size());
        }
        switch (unused.size()) {
            case 0:
            case 1:
                break;
            case 2:
                toAdd = combineFromTwo(unused.get(0).getValue(), unused.get(1).getValue());
                break;
            case 3:
                toAdd = combineFromThree(unused.get(0).getValue(), unused.get(1).getValue(), unused.get(2).getValue());
                break;
            case 4:
                toAdd = combineFromFour(unused.get(0).getValue(), unused.get(1).getValue(), unused.get(2).getValue(), unused.get(3).getValue());
                break;
            case 5:
                toAdd = combineFromFive(unused.get(0).getValue(), unused.get(1).getValue(), unused.get(2).getValue(), unused.get(3).getValue(), unused.get(4).getValue());
                break;
            case 6:
                toAdd = combineFromSix(unused.get(0).getValue(), unused.get(1).getValue(), unused.get(2).getValue(), unused.get(3).getValue(), unused.get(4).getValue(), unused.get(5).getValue());
                break;
        }
        return result += toAdd;
    }

    private int combineFromTwo(int one, int two){
        int target = parseInt(mTarget);
        if (one + two == target){
            return one + two;
        }
        return 0;
    }

    private int combineFromThree(int one, int two, int three){
        int combination = 0;
        int target = parseInt(mTarget);
        if (combineFromTwo(one, two) == target){
            combination = combineFromTwo(one, two);
        } else if (combineFromTwo(one, three) == target) {
            combination = combineFromTwo(one, three);
        } else if (combineFromTwo(two, three) == target) {
            combination = combineFromTwo(two, three);
        } else if (one + two + three == target) {
            combination = one + two + three;
        }
        return combination;
    }

    private int combineFromFour(int one, int two, int three, int four){
        int combination = 0;
        int target = parseInt(mTarget);
        if (combineFromThree(one, two, three) == target){
            combination = combineFromThree(one, two, three);
        } else if (combineFromThree(one, two, four) == target){
            combination = combineFromThree(one, two, four);
        } else if (combineFromThree(one, three, four) == target){
            combination = combineFromThree(one, three, four);
        } else if (combineFromThree(two, three, four) == target){
            combination = combineFromThree(two, three, four);
        } else if (one + two + three + four == target){
            combination = one + two + three + four;
        }
        return combination;
    }

    private int combineFromFive(int one, int two, int three, int four, int five){
        int combination = 0;
        int target = parseInt(mTarget);
        if (combineFromFour(one, two, three, four) == target){
            combination = combineFromFour(one, two, three, four);
        } else if (combineFromFour(one, two, three, five) == target){
            combination =combineFromFour(one, two, three, five);
        } else if (combineFromFour(one, two, four, five) == target){
            combination = combineFromFour(one, two, four, five);
        } else if (combineFromFour(one, three, four, five) == target){
            combination = combineFromFour(one, three, four, five);
        } else if (combineFromFour(two, three, four, five) == target){
            combination =combineFromFour(two, three, four, five);
        } else if (one + two + three + four + five == target){
            combination = one + two + three + four + five;
        }
        return combination;
    }

    private int combineFromSix(int one, int two, int three, int four, int five, int six){
        int combination = 0;
        int target = parseInt(mTarget);
        if (combineFromFive(one, two, three, four, five) == target){
            combination = combineFromFive(one, two, three, four, five);
        } else if (combineFromFive(one, two, three, four, six) == target){
            combination = combineFromFive(one, two, three, four, six);
        } else if (combineFromFive(one, two, three, five, six) == target){
            combination = combineFromFive(one, two, three, five, six);
        } else if (combineFromFive(one, two, four, five, six) == target){
            combination = combineFromFive(one, two, four, five, six);
        } else if (combineFromFive(one, three, four, five, six) == target){
            combination = combineFromFive(one, three, four, five, six);
        } else if (combineFromFive(two, three, four, five, six) == target){
            combination = combineFromFive(two, three, four, five, six);
        } else if (one + two + three + four + five + six == target){
            combination = one + two + three + four + five + six;
        }
        return combination;
    }


    public int getResult(){
        return result;
    }

}
