package com.example.thirty.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;
import com.example.thirty.R;
import com.example.thirty.activity.MainActivity;
import com.example.thirty.model.Die;
import java.util.*;

import static android.graphics.Color.RED;

public class GameFragment extends Fragment {

    private int round;
    private int totalRound;
    private Button roll;
    private List<Die> dice = new ArrayList<>();
    private Context mContext;
    private Random random = new Random();
    private GridLayout gl, gl2;

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
        gl = view.findViewById(R.id.gl);
        gl2 = view.findViewById(R.id.gl2);

        round = 1;
        totalRound = 1;
        setDice();

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

    private void setDice(){
        dice.clear();
        for (int i = 0; i < 6; i++){
            Die die = new Die(1, false, false);
            dice.add(die);
        }
        setImages();
    }

    private void rollDice(){
        for (Die die : dice){
            if(die.isIdle()){
                return;
            } else {
                int randomNumber = random.nextInt(6) + 1;
                die.setValue(randomNumber);
            }
        }
        setImages();
    }

    private void setImages(){
        gl.removeAllViews();
        gl2.removeAllViews();
        for (Die die : dice){
            int index = dice.indexOf(die);
            ImageView image = new ImageView(mContext);
            image.setPadding(10, 10, 10, 10);
            int res = getResources().getIdentifier("die" + die.getValue(), "drawable", "com.example.thirty");
            image.setImageResource(res);
            switch(index){
                case 0: case 1: case 2:
                    gl.addView(image);
                    break;
                case 3: case 4: case 5:
                    gl2.addView(image);
                    break;
            }

        }
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
