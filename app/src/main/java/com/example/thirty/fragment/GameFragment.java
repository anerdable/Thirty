package com.example.thirty.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thirty.R;
import com.example.thirty.activity.MainActivity;
import com.example.thirty.model.Dice;
import com.example.thirty.model.Die;

public class GameFragment extends Fragment {

    private int round;
    private int totalRound;
    private Button roll;
    private ImageView image1, image2, image3, image4, image5, image6;
    private Dice dice = new Dice();
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        roll = view.findViewById(R.id.rollButton);

        image1 = view.findViewById(R.id.die1);
        image2 = view.findViewById(R.id.die2);
        image3 = view.findViewById(R.id.die3);
        image4 = view.findViewById(R.id.die4);
        image5 = view.findViewById(R.id.die5);
        image6 = view.findViewById(R.id.die6);

        round = 1;
        totalRound = 1;
        rollDice();

        roll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (round > 2){
                    chooseScore();
                }
                rollDice();
                round++;
            }
        });

        return view;
    }

    private void rollDice(){
        dice.clear();
        for (int i = 0; i < 6; i++){
            Die die = new Die(false, false);
            dice.add(die);
        }

        int res1 = getResources().getIdentifier("die" + dice.get(0).getValue(), "drawable", "com.example.thirty");
        int res2 = getResources().getIdentifier("die" + dice.get(1).getValue(), "drawable", "com.example.thirty");
        int res3 = getResources().getIdentifier("die" + dice.get(2).getValue(), "drawable", "com.example.thirty");
        int res4 = getResources().getIdentifier("die" + dice.get(3).getValue(), "drawable", "com.example.thirty");
        int res5 = getResources().getIdentifier("die" + dice.get(4).getValue(), "drawable", "com.example.thirty");
        int res6 = getResources().getIdentifier("die" + dice.get(5).getValue(), "drawable", "com.example.thirty");

        image1.setImageResource(res1);
        image2.setImageResource(res2);
        image3.setImageResource(res3);
        image4.setImageResource(res4);
        image5.setImageResource(res5);
        image6.setImageResource(res6);
    }

    private void chooseScore(){
        if (totalRound > 2){
            gameOver();
        }
        showAlertDialog();
        round = 0;
        totalRound++;
    }

    private void gameOver(){
        MainActivity ma = (MainActivity) getActivity();
        ma.gameOver();
        Toast.makeText(ma, "game over", Toast.LENGTH_SHORT).show();
    }

    private void showAlertDialog() {
        final MainActivity ma = (MainActivity) getActivity();
        GridView gridView = new GridView(mContext);
        String[] options = new String[13];

        options[0] = "Low";
        for (int i = 1; i < 13; i++){
            options[i] = "" + i + "";
        }

        gridView.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, options));
        gridView.setNumColumns(3);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ma, "position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(gridView);
        builder.setTitle("Choose your score");
        builder.show();

    }
}
