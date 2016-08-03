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

        try {
            text = getIntent().getExtras().get("searchText").toString();
        }catch (NullPointerException e){
            e.printStackTrace();
        }



    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new WebViewFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
