package com.example.thirty.activity;

/**
 * MainActivity
 *
 * An Android implementation of the dice game "thirty throws".
 * Development of mobile applications
 * Umeå University, Summer Course 2019
 *
 * Paula D'Cruz
 *
 * Fills a part of the screen with the start fragment with the single purpose to let the player start the game by clicking a button.
 * Toggles the game fragment where the interaction of the game takes place and later shows the result fragment when the game is finished.
 *
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.thirty.R;
import com.example.thirty.fragment.GameFragment;
import com.example.thirty.fragment.ResultFragment;
import com.example.thirty.fragment.StartFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment mStartFragment = new StartFragment();
    final Fragment mGameFragment = new GameFragment();
    final Fragment mResultFragment = new ResultFragment();
    final FragmentManager fm = this.getSupportFragmentManager();

    /**
     * onCreate
     *
     * Sets the first fragment as the start fragment to let the player initialise the game
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm.beginTransaction().add(R.id.fragment_container, mStartFragment).commit();
    }

    /**
     * newGame
     *
     * Replaces the start fragment with the game fragment
     */

    public void newGame(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mGameFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     *
     * gameOver
     *
     * Replaces the game fragment with the result fragment after the game is over.
     *
     * @param result receives the result of the game as an array that is sent in a bundle to the new fragment.
     */

    public void gameOver(int[] result){
        Bundle bundle = new Bundle();
        bundle.putIntArray("params", result);
        mResultFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mResultFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
