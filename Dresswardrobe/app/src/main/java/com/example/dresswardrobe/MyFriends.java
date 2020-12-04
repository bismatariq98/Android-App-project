package com.example.dresswardrobe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyFriends extends AppCompatActivity {
    ListView listView;
    TextView tv1;
    EditText inputSearch;
    List<String> users;
    String username = "" ;
    SharedPreferences prf;
    String frndname;

    ArrayAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        LinearLayout containerLayout = (LinearLayout) findViewById(R.id.container);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);
        listView = findViewById(R.id.listView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MyFriends.this.listAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        this.fetchData();
        AlertDialog.Builder builder = new AlertDialog.Builder(MyFriends.this);
        builder.setTitle("Click on a button to perform an action");

        tv1 = findViewById(R.id.textName);

        Intent intent = getIntent();
        username = intent.getStringExtra("uname");
        listAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, users);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                        frndname =users.get(position);
                        Intent intent1 = new Intent(getApplicationContext(),SpecificUsers.class);
                        intent1.putExtra("fname",frndname);
                        intent1.putExtra("uname",username);
                        startActivity(intent1);

                    }
                });

    }

    private void fetchData()
    {
        SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);
        username = prf.getString("username",null);

        String data = WCFHandler.GetJsonResult("users/GetFriends?UserName=" + username);

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        users = gson.fromJson(data, type);
    }

}



