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
public class WebViewFragment extends Fragment {

    WebView webView;
    String url = "";

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);

        url = getArguments().getString("urlText");

        webView = (WebView) rootView.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //http://www.flipkart.com/search?q=raspberry%20pi&otracker=start&as-show=on&as=off      flipkart format
        //http://www.snapdeal.com/search?keyword=raspberry%20pi&santizedKeyword=&catId=&categoryId=0&suggested=false&vertical=&noOfResults=48&searchState=&clickSrc=go_header&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy
        //snapdeal format
        //http://www.walmart.com/search/?query=panasonic        walmart format

//        webView.loadUrl("https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=" + url + "&rh=i%3Aaps%2Ck%3A" + url);

        webView.loadUrl("http://api.walmartlabs.com/v1/search?apiKey=paz7qrfkd8s8u8msfhpmh58z&query="+url);

        return rootView;

    }

}
