package com.example.anil.duckit.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.anil.duckit.R;
import com.example.anil.duckit.fragments.DetailsFragment;
import com.example.anil.duckit.fragments.MainFragment;
import com.example.anil.duckit.fragments.MainQuestionFragment;

public class MainActivity extends AppCompatActivity {

    EditText questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = (EditText) findViewById(R.id.questionEditText);

        //link main fragment on top of the main activity
        FragmentManager manager = getSupportFragmentManager();
        //MainFragment fragment = (MainFragment) manager.findFragmentById(R.id.container_main);
        MainQuestionFragment fragment = (MainQuestionFragment) manager.findFragmentById(R.id.container_main);

        if(fragment == null)
        {
            fragment = MainQuestionFragment.newInstance();
            manager.beginTransaction().add(R.id.container_main, fragment).commit();
        }
    }

    public void loadResultsPage(Bundle args)
    {
        MainFragment mainFragment = MainFragment.newInstance();//new MainFragment();
        mainFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, mainFragment).addToBackStack(null).commit();
    }

    public void loadDetailsPage(Bundle args)
    {
        DetailsFragment detailsFragment = DetailsFragment.newInstance();//new DetailsFragment();
        detailsFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, detailsFragment).addToBackStack(null).commit();
    }
}
