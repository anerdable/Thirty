
/*

package com.example.thirty.fragment;

här ska starttaggen egentligen ligga för kommentaren

 * StartFragment
 * An Android implementation of the dice game "thirty throws".
 * Development of mobile applications
 * Umeå University, Summer Course 2019
 *
 * Paula D'Cruz
 *

 här ska sluttaggen egentligen ligga för kommentaren

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thirty.R;
import com.example.thirty.activity.MainActivity;


public class StartFragment extends Fragment {

    private Button ok;
    private final static String TAG = "StartFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ok = view.findViewById(R.id.play);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = (MainActivity) getActivity();
                ma.newGame();
            }
        });
        return view;
    }
}

*/