package com.example.dresswardrobe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.dresswardrobe.MainActivity.username;

public class listview extends AppCompatActivity {

    private ListView mListview;
    private ArrayList<String> mArrData;
    private SchoolAdapter mAdapter;
    SharedPreferences prf;

    List<String> users;
    String username = "" ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        this.fetchData();

        mListview = (ListView) findViewById(R.id.listSchool);

        // Initialize adapter and set adapter to list view
        mAdapter = new SchoolAdapter(listview.this, users);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
    private void fetchData()
    {
        SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);
        username = prf.getString("username",null);

        String data = WCFHandler.GetJsonResult("users/Getall?UserName="+username);

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        users = gson.fromJson(data, type);
    }
}