package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DisplayActivity extends AppCompatActivity {

    private ArrayList<News> news_array = new ArrayList<>();
    private ListView list_view;
    private String json_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        list_view = findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(DisplayActivity.this, R.layout.news_list, news_array);                                    list_view.setAdapter(adapter);
        list_view.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();

        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    json_response = response.body().string();

                    try {
                        JSONObject json_obj = new JSONObject(json_response);
                        JSONArray json_array = json_obj.getJSONArray("data"); // get the whole json array list
                        for (int i = 0; i < json_array.length(); i++) {
                            JSONObject json = json_array.getJSONObject(i);
                            int id = json.getInt("id");
                            String url1 = json.getString("url");
                            String desc = json.getString("description");
                            String heading = json.getString("heading");

                            News news = new News(id, heading, desc, url1);
                            news_array.add(news);
                        }

                        DisplayActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,news_array.get(i).getUrl());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

}