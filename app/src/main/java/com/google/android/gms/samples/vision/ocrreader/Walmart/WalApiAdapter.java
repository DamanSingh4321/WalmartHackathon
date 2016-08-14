package com.google.android.gms.samples.vision.ocrreader.Walmart;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.samples.vision.ocrreader.R;

import java.util.ArrayList;

/**
 * Created by daman on 12/8/16.
 */
public class WalApiAdapter extends RecyclerView.Adapter<WalApiAdapter.MyViewHolder> {
    private ArrayList<String> name;
    private ArrayList<String> price;
    private ArrayList<String> image;
    Context context;

        public WalApiAdapter(Context context, ArrayList<String> name, ArrayList<String> price, ArrayList<String> image){
            this.name = name;
            this.price = price;
            this.image = image;
           this.context = context;
        }

    @Override
    public WalApiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        MyViewHolder holder;
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_web_view, parent, false);
                holder = new MyViewHolder(v);
                holder.mCardView.setTag(holder);
                return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.txtname.setText(name.get(position));
            holder.txtprice.setText(price.get(position));
            holder.webView.loadUrl(image.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
            return name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        TextView txtname;
        TextView txtprice;
        WebView webView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            txtname = (TextView) v.findViewById(R.id.tittle);
            txtprice = (TextView) v.findViewById(R.id.price);
            webView = (WebView) v.findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
        }
    }
}
