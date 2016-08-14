package com.google.android.gms.samples.vision.ocrreader.Walmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.google.android.gms.samples.vision.ocrreader.R;


public class MainActivity extends FragmentActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter;
    String text = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.mainViewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);

        try {
            text = getIntent().getExtras().get("searchText").toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Bundle wikiBundle = new Bundle();
                    wikiBundle.putString("urlText", text);
                    WikiFragment wikiFrag = new WikiFragment();
                    wikiFrag.setArguments(wikiBundle);
                    return wikiFrag;
                case 1:
                    Bundle urlBundle = new Bundle();
                    urlBundle.putString("urlText", text);
                    WebViewFragment urlFrag = new WebViewFragment();
                    urlFrag.setArguments(urlBundle);
                    return urlFrag;
                case 2:
                    Bundle compBundle = new Bundle();
                    compBundle.putString("urlText", text);
                    WrongFragment compFrag = new WrongFragment();
                    compFrag.setArguments(compBundle);
                    return compFrag;
            }
            return new WrongFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
