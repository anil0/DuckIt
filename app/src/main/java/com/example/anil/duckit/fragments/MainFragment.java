package com.example.anil.duckit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.duckit.R;
import com.example.anil.duckit.activities.MainActivity;
import com.example.anil.duckit.stackoverflow.Answer;
import com.example.anil.duckit.stackoverflow.StackOverflowAysncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String question;
    private String mParam2;
    private static Answer a = null;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *


     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();


        StackOverflowAysncTask task = new StackOverflowAysncTask();
        try {
            a = task.execute("why is it faster to process a sorted array than an unsorted array").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            question = getArguments().getString(ARG_PARAM1);
            Log.v("QUESTION", question);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //start asynctask to get stackoverflow answer


        TextView stackoverflowText = (TextView) view.findViewById(R.id.stackoverflow_text);
        //remove html
        Document doc = Jsoup.parse(a.getBody().toString());
        stackoverflowText.setText(doc.text());

        CardView btn = (CardView)view.findViewById(R.id.card_stackoverflow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();

                //pass stackoverflow answer object to details screen
                System.out.println("THE MAIN CALL TO GET ANSWER -------------------------");
                //System.out.println( a.getBody() );

                Bundle args = new Bundle();
                args.putString("ANSWER", a.getBody().toString());

                mainActivity.loadDetailsPage(args);
            }
        });

        return view;
    }

}
