package com.example.thirty.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.thirty.R;
import com.example.thirty.fragment.GameFragment;
import com.example.thirty.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment gameFragment = new GameFragment();
    final Fragment resultFragment = new ResultFragment();
    final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
