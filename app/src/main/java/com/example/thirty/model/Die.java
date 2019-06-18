package com.example.thirty.model;

import android.view.View;

import java.util.Random;

public class Die implements View.OnClickListener {
    private int value;
    private boolean idle, used;

    public Die(boolean idle, boolean used){
        Random random = new Random();
        this.value = random.nextInt(6) + 1;
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

    @Override
    public void onClick(View v) {

    }
}
