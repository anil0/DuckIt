package com.example.anil.duckit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.anil.duckit.R;
import com.example.anil.duckit.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private static EditText questionText;
   // public static ProgressBar progressBar;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public MainQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *


     * @return A new instance of fragment MainQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainQuestionFragment newInstance() {
        MainQuestionFragment fragment = new MainQuestionFragment();


        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, questionText.getText().toString());

        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_question, container, false);

        questionText = (EditText) view.findViewById(R.id.questionEditText);
        //progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Button submitButton = (Button) view.findViewById(R.id.questionButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();

                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, questionText.getText().toString());

                //progressBar.setVisibility(View.VISIBLE);
                mainActivity.loadResultsPage(args);
            }
        });

        return view;
    }

}
