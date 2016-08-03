package com.google.android.gms.samples.vision.ocrreader.Walmart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.samples.vision.ocrreader.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WikiFragment extends Fragment {

    WebView wikiWeb;
    String wiki = "";

    public WikiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_wiki, container, false);

        wiki = getArguments().getString("urlText");

        wikiWeb = (WebView) rootView.findViewById(R.id.wikiWebview);
        WebSettings webSettings = wikiWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wikiWeb.loadUrl("https://en.wikipedia.org/wiki/"+wiki);

        return rootView;
    }

}
