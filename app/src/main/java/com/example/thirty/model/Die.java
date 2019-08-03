package com.example.thirty.model;

/*
 * Die
 * An Android implementation of the dice game "thirty throws".
 * Development of mobile applications
 * Umeå University, Summer Course 2019
 *
 * Paula D'Cruz
 *
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Die implements Parcelable {

    private Random random = new Random();
    private int value;
    private boolean idle;
    private final static String TAG = "Die";

    /**
     * Die
     *
     * constructor to create a new die object.
     *
     * @param value is always 1 because the constructor creates six dice with value 1 at the beginning of a game.
     */

    public Die(int value){
        this.value = value;
        this.idle = false;
    }

    /**
     * Die
     *
     * protected constructor to recreate states using parcels for transient state storage.
     *
     * @param in contains the object that should be recreated
     */

    protected Die(Parcel in) {
        value = in.readInt();
        idle = in.readInt() == 1;
    }

    /**
     * Parcelable.Creator
     *
     * static method that calls the protected constructor to recreate state.
     *
     */

    public static final Creator<Die> CREATOR = new Creator<Die>() {
        @Override
        public Die createFromParcel(Parcel in) {
            return new Die(in);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };

    /**
     * getValue
     *
     * @return the value of the die. used when calculating the score of the game.
     */

    public int getValue(){
        return value;
    }

    /**
     * isIdle
     *
     * @return whether or not the die is set to idle (meaning it won't change value the next time the player
     * presses roll)
     */

    public boolean isIdle(){
        return idle;
    }

    /**
     * roll
     *
     * gives the die a new random value between 1 - 6.
     *
     */

    public void roll(){
        value = random.nextInt(6) + 1;
    }

    /**
     * switchIdle
     *
     * toggle the idle state of the die
     * used when clicking a die, and when a round is over.
     */

    public void switchIdle(){
        idle = !idle;
    }

    /**
     * reset
     *
     * sets the value of the die to 0 and it's idle state to false.
     * used when the game is over.
     *
     */

    public void reset(){
        value = 0;
        idle = false;
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
        dest.writeInt(value);
        dest.writeInt((idle ? 1 : 0));
    }
}
