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
import android.util.Log;

import com.example.thirty.R;
import com.example.thirty.fragment.GameFragment;
import com.example.thirty.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment mGameFragment = new GameFragment();
    final Fragment mResultFragment = new ResultFragment();
    final FragmentManager fm = this.getSupportFragmentManager();
    private final static String TAG = "MainActivity";

    /**
     * onCreate
     *
     * Lifecycle method that loads the correct fragment into the fragment container
     *
     * @param savedInstanceState saved state
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment;

        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState != null){
            fragment = fm.getFragment(savedInstanceState, String.valueOf(R.id.fragment_container));
        } else {
            fragment = fm.findFragmentById(R.id.fragment_container);
        }

        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_container, mGameFragment)
                    .commit();
        }
        Log.d(TAG, "onCreate() called");
    }

    /**
     * onSaveInstanceState
     *
     * Stores transient bundle data from a fragment
     *
     * @param outState bundle where the saved state is stored
     */

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        getSupportFragmentManager().putFragment(outState,String.valueOf(R.id.fragment_container), fragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    /**
     * newGame
     *
     * Replaces the start fragment with the game fragment
     */

    public void newGame(){
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mGameFragment);
        transaction.addToBackStack("game");
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
        transaction.addToBackStack("result");
        transaction.commit();
    }
}
