package com.example.thirty.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm.beginTransaction().add(R.id.fragment_container, mStartFragment).commit();
    }

    public void newGame(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mGameFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void gameOver(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mResultFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
