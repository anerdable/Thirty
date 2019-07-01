package com.example.thirty.model;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.lang.Integer.parseInt;

public class Counter {

    private String mTarget;
    private List<Integer> mValues = new ArrayList<>();
    private List<Die> mDice;
    private List<List<Integer>> allCombinations = new ArrayList<>();
    private int result;

    public Counter(List<Die> dice, String target){
        mDice = dice;
        mTarget = target;
        this.result = 0;
        for (Die die : mDice){
            mValues.add(die.getValue());
        }
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
                List<Integer> values = new ArrayList<>();
                for (Die die : mDice){
                    values.add(die.getValue());
                }
                findAllCombinations(new ArrayList<>(values),parseInt(mTarget));
                break;
        }
        return result;
    }

    private void findAllCombinations(List<Integer> values, int target) {
        findAllCombinationsRecursive(values, target, new ArrayList<Integer>());
        result += findBestCombination(allCombinations);
    }

    private void findAllCombinationsRecursive(List<Integer> values, int target, List<Integer> combination) {
        int toAdd = 0;

        for (int i : combination) {
            toAdd += i;
        }

        if (toAdd == target) {
            allCombinations.add(combination);
        }

        if (toAdd > target) {
            return;
        }

        for(int i = 0; i < values.size(); i++) {
            List<Integer> remaining = new ArrayList<>();
            int n = values.get(i);
            for (int j = i+1; j < values.size(); j++) {
                remaining.add(values.get(j));
            }
            List<Integer> copy = new ArrayList<>(combination);
            copy.add(n);
            findAllCombinationsRecursive(remaining, target, copy);
        }
    }
    private int findBestCombination(List<List<Integer>> combinations) {
        int toAdd = 0;

        // Sort all the lists by length to count the shortest lists first, to use as many combinations as possible
        Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> list1, List<Integer> list2) {
                return list1.size() - list2.size();
            }
        };
        Collections.sort(combinations, comparator);

        Log.d("hoppsan", " det här är alla kombinationer " + combinations);

        for (List<Integer> combination : combinations){
            while (mValues.size() > 0){
                if (valid(combination)){
                    Log.d("ja", "den här funkar " + combination);
                    toAdd += sumUpCombination(combination);
                    generateNewValues(combination);
                } else {
                    Log.d("nej", "den här funkar inte " + combination);
                    break;
                }
            }
        }

        return toAdd;
    }

    private boolean valid(List<Integer> combination) {
        boolean valid = false;
        List<Integer> copyValues = new ArrayList<>(mValues);

        if (!copyValues.containsAll(combination)) {
            return false;
        }

        Collections.sort(copyValues);
        Collections.sort(combination);

        Log.d("hej", " det här är värdena " + copyValues);
        Log.d("hej", " det här är kopian " + combination);

        if (copyValues.size() > combination.size()) {
            List<Integer> removed = new ArrayList<>();
            for (Integer i : combination){
                copyValues.remove(i);
                removed.add(i);
            } if (removed.size() + copyValues.size() > mValues.size()){
                    valid = false;
            } else {
                valid = true;
            }
        } else if (copyValues.size() == combination.size()) {
            if (combination.containsAll(copyValues)) {
                for (int i = 0; i < combination.size(); i++) {
                    if (combination.get(i) == copyValues.get(i)) {
                        valid = true;
                    } else {
                        i++;
                        valid = false;
                    }
                }
            }
        }

        return valid;
    }

    private int sumUpCombination(List<Integer> combination){
        int toAdd = 0;
        for (int i : combination){
            toAdd += i;
        }
        return toAdd;
    }

    private void generateNewValues(List<Integer> combination){
        Log.d("hej", combination +" kombinationen som nyss användes ");
        for (Integer i : combination){
            mValues.remove(i);
        }
        Log.d("hallå", " här är nya värden " + mValues);
    }

    public int getResult(){
        return result;
    }

}
