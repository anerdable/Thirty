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
    private boolean idle, used;

    public Die(){
        this.value = random.nextInt(6) + 1;
        this.idle = false;
        this.used = false;
    }

    public Die(int value, boolean idle, boolean used){
        this.value = value;
        this.idle = idle;
        this.used = used;
    }

    public int getValue(){
        return value;
    }

    public boolean isIdle(){
        return idle;
    }

    public boolean isUsed(){
        return used;
    }

    public void roll(){
        value = random.nextInt(6) + 1;
    }

    public void switchIdle(){
        idle = !idle;
    }

    public void setUsed(){
        used = true;
    }

    public void reset(){
        value = 0;
        idle = false;
        used = false;
    }

}
