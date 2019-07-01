package com.example.thirty.fragment;

/*
 * ResultFragment
 *
 * An Android implementation of the dice game "thirty throws".
 * Development of mobile applications
 * Umeå University, Summer Course 2019
 *
 * Paula D'Cruz
 *
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.thirty.R;
import com.example.thirty.activity.MainActivity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This fragment updates the result view.
 *
 */

public class ResultFragment extends Fragment {

    private View view;
    private int[] mParam1;
    private List<TextView> textViews = new ArrayList<>();
    private Button newGame;
    private TextView low, fours, fives, sixes, sevens, eights, nines, tens, elevens, twelves, totalScore;


    /**
     *onCreateView
     *
     *
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_result, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getIntArray("params");
        }
       low = view.findViewById(R.id.scoreLow);
       textViews.add(low);
       fours = view.findViewById(R.id.score4);
       textViews.add(fours);
       fives = view.findViewById(R.id.score5);
       textViews.add(fives);
       sixes = view.findViewById(R.id.score6);
       textViews.add(sixes);
       sevens = view.findViewById(R.id.score7);
       textViews.add(sevens);
       eights = view.findViewById(R.id.score8);
       textViews.add(eights);
       nines = view.findViewById(R.id.score9);
       textViews.add(nines);
       tens = view.findViewById(R.id.score10);
       textViews.add(tens);
       elevens = view.findViewById(R.id.score11);
       textViews.add(elevens);
       twelves = view.findViewById(R.id.score12);
       textViews.add(twelves);
       totalScore = view.findViewById(R.id.totalScore);
       getScore();
       newGame = view.findViewById(R.id.newGame);
       newGame.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MainActivity ma = (MainActivity) getActivity();
               ma.newGame();
           }
       });
       return view;
    }

    /**
     * getScore
     *
     * takes the score from the bundle that was taken in as a parameter and appends the result to the correct
     * TextView element on the fragment view.
     *
     * Calculates the total score of the game and appends that number to the last TextView for total result.
     *
     */

    private void getScore(){
        Integer tot = 0;
        low.append(" " + mParam1[0]);
        fours.append(" " + mParam1[1]);
        fives.append(" " + mParam1[2]);
        sixes.append(" " + mParam1[3]);
        sevens.append(" " + mParam1[4]);
        eights.append(" " + mParam1[5]);
        nines.append(" " + mParam1[6]);
        tens.append(" " + mParam1[7]);
        elevens.append(" " + mParam1[8]);
        twelves.append(" " + mParam1[9]);
        for (Integer i : mParam1){
            tot += i;
        }
        totalScore.append(tot.toString());
    }

}
