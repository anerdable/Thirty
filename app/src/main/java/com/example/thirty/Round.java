package com.example.thirty;

public class Round {
    private int round;
    private int roundResult;

    public Round(int round, int roundResult){
        this.round = round;
        this.roundResult = roundResult;
    }

    public int getRound(){
        return round;
    }

    public void setRound(int nextRound){
        round = nextRound;
    }

    public int getRoundResult(){
        return roundResult;
    }
}
