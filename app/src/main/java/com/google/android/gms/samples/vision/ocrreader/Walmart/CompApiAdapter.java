package com.google.android.gms.samples.vision.ocrreader.Walmart;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.google.android.gms.samples.vision.ocrreader.R;
import java.util.ArrayList;

/**
 * Created by daman on 13/8/16.
 */ 
public class CompApiAdapter extends RecyclerView.Adapter<CompApiAdapter.MyViewHolder> {
        private ArrayList<String> name2;
    private ArrayList<String> title2;
        private ArrayList<String> price2;
        private ArrayList<String> image2;
        Context context;

        public CompApiAdapter(Context context, ArrayList<String> name2, ArrayList<String> title2, ArrayList<String> price2, ArrayList<String> image2){
            this.name2 = name2;
            this.title2 = title2;
            this.price2 = price2;
            this.image2 = image2;
            this.context = context;
        }

        @Override
        public CompApiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            MyViewHolder holder;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_compare, parent, false);
            holder = new MyViewHolder(v);
            holder.mCardView.setTag(holder);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            try {
                holder.txtsource2.setText(name2.get(position));
                holder.txtname2.setText(title2.get(position));
                holder.txtprice2.setText(price2.get(position));
                holder.webView2.loadUrl(image2.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return name2.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder{
            public CardView mCardView;
            TextView txtsource2;
            TextView txtname2;
            TextView txtprice2;
            WebView webView2;
            public MyViewHolder(View v) {
                super(v);
                mCardView = (CardView) v.findViewById(R.id.card_view2);
                txtsource2 = (TextView) v.findViewById(R.id.source2);
                txtname2 = (TextView) v.findViewById(R.id.tittle2);
                txtprice2 = (TextView) v.findViewById(R.id.price2);
                webView2 = (WebView) v.findViewById(R.id.webview2);
                webView2.getSettings().setJavaScriptEnabled(true);
                webView2.getSettings().setDomStorageEnabled(true);
            }
        }

    }
