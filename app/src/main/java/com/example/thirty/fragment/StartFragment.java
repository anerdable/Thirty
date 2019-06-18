package com.example.thirty.fragment;

import android.content.Context;
import android.net.Uri;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
