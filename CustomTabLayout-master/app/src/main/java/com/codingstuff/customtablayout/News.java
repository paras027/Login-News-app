package com.codingstuff.customtablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class News extends AppCompatActivity {
    RequestQueue queue;
    recadapter recadapter;
    RecyclerView recyclerView;
    ArrayList<newsmodel> photosses;
    ProgressBar progressBar;
    private static final String SAMPLE_JSON_RESPONSE = "https://newsapi.org/v2/everything?q=keyword&apiKey=1ead3073504b4bc38d4b8ad4f7971d60";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        loaddata();
        Context context=getApplicationContext();
    }

    public  void loaddata(){
        queue= Volley.newRequestQueue(this);
        photosses=new ArrayList<>();
        RecyclerView recyclerView=findViewById(R.id.rv);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                SAMPLE_JSON_RESPONSE,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray f = response.getJSONArray("articles");
                            for (int i = 0; i < f.length(); i++) {
                                JSONObject pos =f.getJSONObject(i);
                                JSONObject prop= pos.getJSONObject("sources");
                                String name = prop.getString("name");
                                String title =pos.getString("title");
                                String desc = pos.getString("description");
                                String url =pos.getString("urlToImage");
                                String time = pos.getString("publishedAt");
                                newsmodel earthquake = new newsmodel(title, desc, time, name, url);
                                photosses.add(earthquake);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                recadapter=new recadapter(getApplicationContext(),photosses);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(recadapter);

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }


}