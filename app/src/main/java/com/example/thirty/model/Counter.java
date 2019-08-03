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
    private final static String TAG = "Counter";

    /**
     * Counter
     *
     * constructor to make a new counter
     *
     * @param dice takes the set of dice that the player currently has, to calculate the score from
     * @param target takes the target score that the user has chosen from the spinner, to decide which
     *               score the counter is trying to reach
     */

    public Counter(List<Die> dice, String target){
        mDice = dice;
        mTarget = target;
        this.result = 0;
        for (Die die : mDice){
            mValues.add(die.getValue());
        }
        countValue();
    }

    /**
     * countValue
     *
     * this is the first method that is being called, it's a switch case that calls different methods
     * depending on which score the player chose.
     *
     * @return the score that the player can achieve from this set of dice, for the chosen target score
     */

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

    /**
     * findAllCombinations
     *
     * this is a helper method that will continue sending data to a new method.
     * it will send the data retrieved from the first method to a second method to calculate the result of the round
     *
     * @param values these are the values of the dice stores as Integers in an arrayList, instead of Die objects.
     *               The reason for this is to make it easier to count with Integers rather than calling the
     *               getValue() method from the Die object every time you need the number it represents.
     * @param target this is the integer target number that the player is trying to reach as many points as possible of
     */

    private void findAllCombinations(List<Integer> values, int target) {
        findAllCombinationsRecursive(values, target, new ArrayList<Integer>());
        result += findBestCombination(allCombinations);
    }

    /**
     * findAllCombinationsRecursive
     *
     * a recursive method to find all combinations that can be made with the set of dice that the player
     * is currently displayed with.
     *
     * @param values an arrayList containing the values of the dice set
     * @param target the target score that the player chose in the spinner
     * @param combination this is a list where each combination will be stored each time the method is called. All combinations are then added to a list allCombinations.
     */

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

    /**
     * findBestCombination
     *
     * after all combinations have been found from the set of dice, it's time to choose the best ones, i.e the ones
     * using the least amount of dice (so you can use as many combinations as possible). This method makes sure
     * each die is only used once.
     *
     * @param combinations these are all combinations that were found in the previous method findAllCombinations
     * @return the integer points that are achieved from using the best (shortest) combinations, using each die only once.
     */
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

    /**
     * valid
     *
     * this is a helper method to check whether a combination is valid or not. It checks to see that all values
     * used in the combination are unused values.
     *
     * @param combination the combination to check (a list containing the integers used in the combination)
     * @return true or false depending on if it's a valid combination or not
     */

    private boolean valid(List<Integer> combination) {
        boolean valid = false;
        List<Integer> copyValues = new ArrayList<>(mValues);

        if (!copyValues.containsAll(combination)) {
            return false;
        }

        Collections.sort(copyValues);
        Collections.sort(combination);

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

    /**
     * sumUpCombination
     *
     * from this method all values in a combination is added to an int holder representing the final score of the combination
     *
     * @param combination takes the combination of which you want to calculate the score from
     * @return an integer holding a value that is all values of the combination added together.
     */

    private int sumUpCombination(List<Integer> combination){
        int toAdd = 0;
        for (int i : combination){
            toAdd += i;
        }
        return toAdd;
    }

    /**
     * generateNewValues
     *
     * each time a combination has been used, this method is called to update a list to check which dice are
     * left to be used to calculate combinations from
     *
     * @param combination this is the combination that has just been used, and those dice are removed so they can't be used again
     */

    private void generateNewValues(List<Integer> combination){
        Log.d("hej", combination +" kombinationen som nyss användes ");
        for (Integer i : combination){
            mValues.remove(i);
        }
        Log.d("hallå", " här är nya värden " + mValues);
    }

    /**
     * getResult
     *
     * a getter method to get the result from when a Counter object has calculated the result of a set of dice.
     * Used when displaying how many points a user can achieve from choosing a value from the spinner.
     * The result is displayed in a Toast message and later in the result fragment.
     *
     * @return the result of the calculations from the set of dice and the target score the player wanted to schieve.
     */

    public int getResult(){
        return result;
    }

}
