package com.example.anil.duckit.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anil.duckit.R;
import com.example.anil.duckit.fragments.DetailsFragment;
import com.example.anil.duckit.fragments.MainFragment;
import com.example.anil.duckit.fragments.MainQuestionFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link main fragment on top of the main activity
        FragmentManager manager = getSupportFragmentManager();
        //MainFragment fragment = (MainFragment) manager.findFragmentById(R.id.container_main);
        MainQuestionFragment fragment = (MainQuestionFragment) manager.findFragmentById(R.id.container_main);

        if(fragment == null)
        {
            fragment = new MainQuestionFragment();
            manager.beginTransaction().add(R.id.container_main, fragment).commit();
        }
    }

    public void loadResultsPage()
    {
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, mainFragment).addToBackStack(null).commit();
    }

    public void loadDetailsPage()
    {
        DetailsFragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, detailsFragment).addToBackStack(null).commit();
    }
}
