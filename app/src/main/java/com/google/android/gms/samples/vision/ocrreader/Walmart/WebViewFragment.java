package com.google.android.gms.samples.vision.ocrreader.Walmart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.samples.vision.ocrreader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {
    String url = "";
    String queryUrl = "";
    private ArrayList<String> name = new ArrayList<String>() ;
    private ArrayList<String> salePrice = new ArrayList<String>();
    private ArrayList<String> image = new ArrayList<String>();
    WalApiAdapter adapter;
    private  RequestQueue requestQueue;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        url = getArguments().getString("urlText");
        System.out.println(url);
        queryUrl = "http://api.walmartlabs.com/v1/search?apiKey=paz7qrfkd8s8u8msfhpmh58z&query="+url;
        System.out.println(queryUrl);
        data(queryUrl);
        System.out.println(name);;
        adapter = new WalApiAdapter(getContext(), name, salePrice, image);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }

    public void data(String queryUrl) {
        try {
            StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,
                    queryUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String strresponse) {
                            try {
                                JSONObject response = new JSONObject(strresponse);
                                String a1 = response.getString("items");
                                JSONArray a1obj = new JSONArray(a1);
                                for (int j = 0; j < a1obj.length(); j++) {
                                    JSONObject a2obj = a1obj.getJSONObject(j);
                                    String a2 = a2obj.getString("name");
                                    name.add(a2);
                                    String sp = a2obj.getString("salePrice");
                                    salePrice.add("Price: $"+sp);
                                    String im = a2obj.getString("thumbnailImage");
                                    image.add(im);

                                    }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Request.Priority getPriority() {
                    return Request.Priority.IMMEDIATE;
                }
            };
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
