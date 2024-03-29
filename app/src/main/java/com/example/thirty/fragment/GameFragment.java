package com.example.thirty.fragment;

/*
 * GameFragment
 *
 * An Android implementation of the dice game "thirty throws".
 * Development of mobile applications
 * Umeå University, Summer Course 2019
 *
 * Paula D'Cruz
 *
 * This is a controller class for the game view. It reacts to the player's interactions with the game,
 * updates the game model class and changes the view objects depending on the model class' updates.
 *
 *
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.thirty.R;
import com.example.thirty.activity.MainActivity;
import com.example.thirty.model.Counter;
import com.example.thirty.model.Die;
import com.example.thirty.model.Game;

import java.util.*;

import static android.graphics.Color.RED;

public class GameFragment extends Fragment implements View.OnClickListener {

    private Game mGame;
    private Button roll, score;
    private ImageButton die1, die2, die3, die4, die5, die6;
    private Spinner spinner;
    private List<ImageButton> images = new ArrayList<>();
    private List<Die> dice = new ArrayList<>();
    private Context mContext;
    private static final String GAME_PARCEL = "com.example.thirty.model.game";

    /**
     * onAttach
     *
     * called once the fragment is associated with its activity.
     *
     * @param context the context that the fragment can be found in.
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * onCreate
     *
     * Handles restoring state by receiving parcelable
     * data and extras when available.
     * @param savedInstanceState Bundle: saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            mGame = savedInstanceState.getParcelable(GAME_PARCEL);
            dice = mGame.getDice();
        } else {
            for (int i = 0; i < 6; i++){
                Die die = new Die(1);
                dice.add(die);
            }
            mGame = new Game(dice);
        }

    }

    /**
     * onCreateView
     *
     * this method sets up the entire game view. it constructs all UI artifacts such as ImageButtons, Buttons for rolling
     * and scoring and the spinner to choose point target from.
     *
     * @param inflater the layoutInflator object that is used to inflate view to the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        die1 = view.findViewById(R.id.die_1);
        die1.setOnClickListener(this);
        die2 = view.findViewById(R.id.die_2);
        die2.setOnClickListener(this);
        die3 = view.findViewById(R.id.die_3);
        die3.setOnClickListener(this);
        die4 = view.findViewById(R.id.die_4);
        die4.setOnClickListener(this);
        die5 = view.findViewById(R.id.die_5);
        die5.setOnClickListener(this);
        die6 = view.findViewById(R.id.die_6);
        die6.setOnClickListener(this);
        roll = view.findViewById(R.id.rollButton);
        spinner = view.findViewById(R.id.spinner);
        score = view.findViewById(R.id.scoreButton);
        images.add(die1);
        images.add(die2);
        images.add(die3);
        images.add(die4);
        images.add(die5);
        images.add(die6);
        setRound();
        return view;
    }

    /**
     * onSaveInstanceState
     *
     * mGame is prepared here for transient storage in a bundle on
     * configuration change such as rotation.
     * @param outState bundle in which to place saved state.
     */

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable(GAME_PARCEL, mGame);
    }

    /**
     * setRound
     *
     * Updates the UI with correct image of each die's value (all 1 in the first set).
     *
     */

    private void setRound(){
        attachListeners();
        setImages();
        if (mGame.getRoll() == 0){
            score.setEnabled(false);
            for (ImageButton ib : images){
                ib.setClickable(false);
            }
        }
        setSpinner();
    }

    private void attachListeners(){
        if (mGame.getRoll() > 2){
            roll.setEnabled(false);
        } else if (mGame.gameOver) {
            gameOver();
        } else {
            roll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    roll();
                }
            });
        }

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                score();
            }
        });

    }

    /**
     * onClick
     *
     * generic onClick method that acts the same for all ImageButtons in the view.
     *
     * @param v takes the view as a parameter to be able to identify the element that was clicked.
     */

    @Override
    public void onClick(View v) {
        react(v.getId());
    }

    /**
     * setImages
     *
     * Updates the UI with correct die image depending on its value, as well as a RED background indicating if the die is
     * set as idle by the user.
     */

    private void setImages(){
        for (Die die : dice){
            int index = dice.indexOf(die);
            int res = getResources().getIdentifier("die" + die.getValue(), "drawable", "com.example.thirty");
            switch(index){
                case 0:
                    die1.setImageResource(res);
                    if (die.isIdle()){
                        die1.setBackgroundColor(RED);
                    }
                    break;
                case 1:
                    die2.setImageResource(res);
                    if (die.isIdle()){
                        die2.setBackgroundColor(RED);
                    }
                    break;
                case 2:
                    die3.setImageResource(res);
                    if (die.isIdle()){
                        die3.setBackgroundColor(RED);
                    }
                    break;
                case 3:
                    die4.setImageResource(res);
                    if (die.isIdle()){
                        die4.setBackgroundColor(RED);
                    }
                    break;
                case 4:
                    die5.setImageResource(res);
                    if (die.isIdle()){
                        die5.setBackgroundColor(RED);
                    }
                    break;
                case 5:
                    die6.setImageResource(res);
                    if (die.isIdle()){
                        die6.setBackgroundColor(RED);
                    }
                    break;
            }
        }
    }

    /**
     * roll
     *
     * Takes the set of dice that was created in the beginning of the game and rolls each die that is
     * assessed a random value through the model class.
     * Also toggles the roll button as unclickable if the round is finished to let the user choose a score.
     * Updates the ImageButtons according to the new value of the dice.
     */

    private void roll(){
        if (mGame.getRoll() >= 2){
            roll.setEnabled(false);
        }

        for (ImageButton ib : images) {
            ib.setClickable(true);
        }

        spinner.setSelection(0);
        score.setEnabled(true);
        dice = mGame.newRoll();
        setImages();
    }

    /**
     *
     * score
     *
     * takes the element from the spinner that was chosen by the user and calculates the score via
     * the counter class. Sends the data to the game class to update the score.
     * Toggles which buttons are clickable and resets the spinner to the first selection.
     * changes the background colours for the dice.
     * checks if the game is over and if it is, calls the method to finish the game.
     *
     */

    private void score(){
        String position = String.valueOf(spinner.getSelectedItem());
        final MainActivity ma = (MainActivity) getActivity();
        if (position == "Choose score"){
            Toast.makeText(ma, "You have to choose a score", Toast.LENGTH_SHORT).show();
        }  else {
            int points = new Counter(dice, position).getResult();
            mGame.setScore(position, points);
            roll.setEnabled(true);
            score.setEnabled(false);
            spinner.setSelection(0);
            for (ImageButton ib : images){
                ib.setBackgroundColor(0);
                ib.setClickable(false);
            }
            if (mGame.gameOver){
                Log.d("hej", " game is over ");
                gameOver();
            }
        }
    }


    /**
     * react
     *
     * Makes each die react to button click and toggles its idle state as well as marking it with a red background.
     *
     * @param id takes the id of the die that was clicked to react to the correct ImageButton
     */

    private void react(int id){
        switch(id){
            case R.id.die_1:
                dice.get(0).switchIdle();
                if (dice.get(0).isIdle()){
                    die1.setBackgroundColor(RED);
                } else {
                    die1.setBackgroundColor(0);
                }
                break;
            case R.id.die_2:
                 dice.get(1).switchIdle();
                 if (dice.get(1).isIdle()){
                     die2.setBackgroundColor(RED);
                 } else {
                     die2.setBackgroundColor(0);
                 }
                 break;
            case R.id.die_3:
                dice.get(2).switchIdle();
                if (dice.get(2).isIdle()){
                    die3.setBackgroundColor(RED);
                } else {
                    die3.setBackgroundColor(0);
                }
                break;
            case R.id.die_4:
                dice.get(3).switchIdle();
                if (dice.get(3).isIdle()){
                    die4.setBackgroundColor(RED);
                } else {
                    die4.setBackgroundColor(0);
                }
                break;
            case R.id.die_5:
                dice.get(4).switchIdle();
                if (dice.get(4).isIdle()){
                    die5.setBackgroundColor(RED);
                } else {
                    die5.setBackgroundColor(0);
                }
                break;
            case R.id.die_6:
                dice.get(5).switchIdle();
                if (dice.get(5).isIdle()){
                    die6.setBackgroundColor(RED);
                } else {
                    die6.setBackgroundColor(0);
                }
                break;
        }
    }

    /**
     * setSpinner
     *
     * updates the spinner view based on the users choice. Calculates the score each time the user
     * selects a spinner item to give them an indication of how many points they'll get from each option, but only
     * shows it as a Toast message. the user have to press score to actually receive the points.
     *
     */

    private void setSpinner() {
        List<String> options = mGame.getOptions();
        final MainActivity ma = (MainActivity) getActivity();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, options);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int position = spinner.getSelectedItemPosition();
                int points = new Counter(dice, mGame.getOptions().get(position)).getResult();
                if (position != 0){
                    Toast.makeText(ma, "poäng för " + mGame.getOptions().get(position) + " är " + points, Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * mandatory call, not used.
             *
             * @param parent
             */

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }

    /**
     *
     * gameOver
     *
     * when the game is over, this method is called. Changes the text of the "roll" button to "see result", and
     * changes the click listener to call the main activity's gameOver method. sends the score of the game to main activity.
     *
     */
    private void gameOver(){
            mGame.gameOver();
            roll.setText("See result");
            final MainActivity ma = (MainActivity) getActivity();
            roll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ma.gameOver(mGame.getScore());
                }
            });
        }

    }