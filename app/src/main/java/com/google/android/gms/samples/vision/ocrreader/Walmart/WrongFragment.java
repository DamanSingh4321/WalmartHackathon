package com.google.android.gms.samples.vision.ocrreader.Walmart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.samples.vision.ocrreader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WrongFragment extends Fragment {
        String url = "";
        String queryUrl = "";
        private ArrayList<String> name2 = new ArrayList<String>() ;
        private ArrayList<String> salePrice2 = new ArrayList<String>();
        private ArrayList<String> image2 = new ArrayList<String>();
    private ArrayList<String> title2 = new ArrayList<String>();
        CompApiAdapter adapter;
        private RequestQueue requestQueue;

        public WrongFragment() {
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
            View rootView = inflater.inflate(R.layout.fragment_wrong, container, false);
            url = getArguments().getString("urlText");
            if (url.endsWith(",")) {
                url = url.substring(0, url.length() - 1);
            }
            System.out.println(url);
            queryUrl = "http://api.dataweave.in/v1/price_intelligence/findProduct/?api_key=b20a79e582ee4953ceccf41ac28aa08d&product="+
                    url+"&page=1&per_page=10";
            System.out.println(queryUrl);
            data(queryUrl);
            System.out.println(name2);;
            adapter = new CompApiAdapter(getContext(), name2, title2, salePrice2, image2);
            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.my_recycler_view2);
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
                                    String a1 = response.getString("data");
                                    JSONArray a1obj = new JSONArray(a1);
                                    for (int j = 0; j < a1obj.length(); j++) {
                                        JSONObject a2obj = a1obj.getJSONObject(j);
                                        String a2 = a2obj.getString("source");
                                        name2.add(a2);
                                        String title = a2obj.getString("title");
                                        title2.add(title);
                                        String sp = a2obj.getString("available_price");
                                        salePrice2.add("Price: Rs."+sp);
                                        String im = a2obj.getString("thumbnail");
                                        image2.add(im);

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

