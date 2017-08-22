package com.example.anil.duckit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.anil.duckit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String body;
    private String mParam2;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
        //Bundle args = fragment.getArguments();//new Bundle();
        //String question = args.getString(ARG_PARAM1);
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        //Log.v("QUESTION",question);
      //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            body = getArguments().getString("ANSWER");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        final WebView webView = (WebView) view.findViewById(R.id.detailWebView);

        System.out.println("THIS IS THE DETAILS SCREEN AND HERE IS THE BODY PASSED");

        //System.out.println(body);

        String htmlBody = "<html>" + body + "</html>";

        //jsoup remove href links so no new pages can be opened
        Document doc = Jsoup.parse(htmlBody);

        Elements link = doc.select("a");

        System.out.println("HREFS----------------");
        for (Element element : link) {
            //System.out.println(element.attr("href"));

            doc.html(doc.html().replace(element.attr("href").toString(), "#"));
        }

        System.out.println("BODY-----------------");
        //System.out.println(doc.toString());

        webView.loadData(doc.toString(), "text/html", null);

        return view;
    }

}
