package com.example.thirty.model;

import android.view.View;

public class Die {
    private int value;
    private boolean idle, used;

    public Die(int value, boolean idle, boolean used){
        this.value = value;
        this.idle = idle;
        this.used = used;
    }

    public int getValue(){
        return value;
    }

    public boolean getIdle(){
        return idle;
    }

    public boolean getUsed(){
        return used;
    }

    public void setValue(int newValue){
        value = newValue;
    }

    public void setIdle(boolean newIdle){
        idle = newIdle;
    }

    public void setUsed(boolean newUsed){
        used = newUsed;
    }

    public void reset(){
        value = 0;
        idle = false;
        used = false;
    }

}
