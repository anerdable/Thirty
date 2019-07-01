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

import java.util.Random;

public class Die {

    private Random random = new Random();
    private int value;
    private boolean idle;

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

}
