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

public class ResultFragment extends Fragment {

    private View view;
    private Button newGame;
    private TextView low, fours, fives, sixes, sevens, eights, nines, tens, elevens, twelves;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_result, container, false);
       low.findViewById(R.id.scoreLow);
       fours.findViewById(R.id.score4);
       fives.findViewById(R.id.score5);
       sixes.findViewById(R.id.score6);
       sevens.findViewById(R.id.score7);
       eights.findViewById(R.id.score8);
       nines.findViewById(R.id.score9);
       tens.findViewById(R.id.score10);
       elevens.findViewById(R.id.score11);
       twelves.findViewById(R.id.score12);
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

}
