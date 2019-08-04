package com.example.thirty.model;

/**
 * Game
 *
 * This is a model class of the game. It contains all logic required for the game.
 *
 */

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.parseInt;

public class Game implements Parcelable {

    private static final int ROUNDS = 10;
    private int roll, round;
    private int[] score;
    private List<Die> dice;
    private List<String> options;
    public boolean gameOver;

    /**
     * Game
     *
     * constructor used to create a new game.
     *
     * @param newDice, takes in a set consisting of 6 dice, since the game requires it.
     */

    public Game(List<Die> newDice){
        this.dice = newDice;
        this.roll = 0;
        this.round = 0;
        this.score = new int[ROUNDS];
        this.gameOver = false;
        enableOptions();
    }

    /**
     * Game
     *
     * protected constructor to recreate states using parcels for transient state storage.
     *
     * @param in contains the object that should be recreated
     */

    protected Game(Parcel in) {
        roll = in.readInt();
        round = in.readInt();
        dice = new ArrayList<>();
        in.readTypedList(dice, Die.CREATOR);
        score = in.createIntArray();
        options = in.createStringArrayList();
        gameOver = in.readInt() == 1;
    }

    /**
     * Parcelable.Creator
     *
     * static method that calls the protected constructor to recreate state.
     *
     */

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    /**
     * enableOptions
     *
     * Fills a list with all possible scores for the game (4-12 + Low)
     *
     */

    private void enableOptions(){
        options = new ArrayList<>();
        options.add("Choose score");
        options.add("Low");
        for (int i = 4; i < 13; i++){
            options.add("" + i + "");
        }
    }

    /**
     * getOptions
     *
     * @return all the options that are in the list
     */

    public List<String> getOptions(){
        return options;
    }

    /**
     * removeOption
     *
     * @param option takes the option that was chosen by the player and removes it from the list, as each
     *               option is only available once during a game
     */

    public void removeOption(String option){
        options.remove(option);
    }

    /**
     * getRoll
     *
     * @return what roll the player is on for the current round
     */

    public int getRoll(){
        return roll;
    }

    /**
     * getRound
     *
     * @return what round the player is on in the game, currently never used.
     */

    public int getRound(){
        return round;
    }

    /**
     * getScore
     *
     * @return the array with the score for each option
     */

    public int[] getScore(){
        return score;
    }

    /**
     * getDice
     *
     * @return the dice that are being used in the game
     */

    public List<Die> getDice(){
        return dice;
    }

    /**
     * setScore
     *
     * takes the score that the player was awarded and fills it into the array of the player's overall score
     * for the game.
     * calls the method to remove the option that was chosen by the player.
     * calls the method to start a new round.
     *
     * @param index takes the position that was chosen by the user of how they wish to score the current round
     * @param points takes that points that the player gets from the chosen option
     */

    public void setScore(String index, int points){
        int i;
        removeOption(index);
        newRound();
        if (index.equals("Low")){
            i = 0;
        } else {
            i = parseInt(index) - 3;
        }
        score[i] = points;
    }

    /**
     * newRoll
     *
     * checks how many rolls have been made. If more than 2, a new round is triggered.
     * increases an int to keep track of how many rolls have been made.
     * calls the roll method on each die that is NOT set idle by the player
     * to assign it a new randomised value between 1-6.
     *
     * @return the set of dice with new values for the dice that were not set idle by the player.
     */

    public List<Die> newRoll(){
        if (roll > 2){
            newRound();
        }
        roll++;
        for (Die die : dice){
            if (!die.isIdle()){
                die.roll();
            }
        }
        return dice;
    }

    /**
     *
     * newRound
     *
     * increases an int that keeps track of how many rounds the player have completed.
     * sets roll to 0 to indicate that 0 rolls have been made in the new round.
     * when the player has completed 10 rounds, the game is over and the gameOver method is called     *
     *
     */

    public void newRound(){
        if (round == 1){
            gameOver = true;
        } else {
            for (Die die: dice){
                die.reset();
            }
            round++;
            roll = 0;
        }
    }

    /**
     * gameOver
     *
     * when a game is over, this method is called. It sets the roll and round to 0, and all dice values to 0.
     * If any die was set as idle, it will be reset to non-idle.
     *
     */

    public void gameOver(){
        roll = 0;
        round = 0;
        for (Integer i : score){
            i = 0;
        }
        for (Die die : dice){
            die.reset();
        }
    }

    /**
     * describeContents
     *
     * mandatory method for Parcelable, not used
     *
     * @return 0, not used
     */

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel
     *
     * this method writes round, roll and score into a parcel.
     *
     * @param dest this is where all data is being written to
     * @param flags
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(round);
        dest.writeInt(roll);
        dest.writeTypedList(dice);
        dest.writeIntArray(score);
        dest.writeInt(gameOver ? 1 : 0);
    }

    public String toString(){
        return round + " " + dice;
    }

}
