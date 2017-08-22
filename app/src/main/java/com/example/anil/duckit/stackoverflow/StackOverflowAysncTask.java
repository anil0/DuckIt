package com.example.anil.duckit.stackoverflow;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.anil.duckit.fragments.MainQuestionFragment;


/**
 * Created by anil on 22/08/2017.
 */

public class StackOverflowAysncTask extends AsyncTask<String, Integer, Answer>
{

    @Override
    protected Answer doInBackground(String... params)
    {
        StackOverflowMain stackOverflowRequest = new StackOverflowMain();
        Answer answer = stackOverflowRequest.searchForQuestion(params[0]);

        System.out.println("INSIDE DO IN BACKGROUND");

        return answer;
    }

    @Override
    protected void onPostExecute(Answer answer)
    {
        System.out.println("INSIDE ONPOSTEXECUTE");
       // MainQuestionFragment.progressBar.setVisibility(View.GONE);
    }

//    @Override
//    protected void onProgressUpdate(Integer... values)
//    {
//        super.onProgressUpdate(values);
//
//        System.out.println("UPDATING BAR: - " + values[0]);
//        MainQuestionFragment.progressBar.setProgress(values[0]);
//    }

//    @Override
//    protected void onPreExecute()
//    {
//        super.onPreExecute();
//
//        MainQuestionFragment.progressBar.setMax(100);
//    }
}
