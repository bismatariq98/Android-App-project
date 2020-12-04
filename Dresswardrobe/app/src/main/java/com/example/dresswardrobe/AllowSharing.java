package com.example.dresswardrobe;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Base64;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllowSharing extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener  {
    ListView listView;

    List<String> users;
    final String[] id = {"0"};
    CheckBox chbx;
    Button upd;
    TextView _tvl;
Context c ;
    ArrayList<String> lst = new ArrayList();
    private Adapter_lst listAdapter1;
    Intent intent = getIntent();
    String uname = " ";
    String id_ = " ";
     GlobalItems g = new GlobalItems();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_sharing);
        // g = (GlobalItems) getIntent().getSerializableExtra("GlobalItems");
        Intent intentt = getIntent();

            id_ = intentt.getStringExtra("id");

uname = intentt.getStringExtra("uname");





        listView = findViewById(R.id.lstshare);
        c = this;
        chbx = findViewById(R.id.chbxshare);
        _tvl = findViewById(R.id.textusr);

        this.fetchData();

        listAdapter1 = new Adapter_lst(this, R.id.textusr, users);

        listView.setAdapter(listAdapter1);
        upd = findViewById(R.id.UploadBtn);


        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendsharing();
                Toast.makeText(getApplicationContext(), "Picture uploaded successfully", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("uname",uname);
                startActivity(i);
  }
            });








}
private void sendsharing(){

    ClassSharing s = new ClassSharing(Integer.parseInt(id_), uname, lst);

    try {
        Gson gson = new Gson();
        String jsonString2 = gson.toJson(s);
        JSONObject sh = new JSONObject();
        try {
            sh = new JSONObject(jsonString2);
        } catch (JSONException err) {
            Log.d("Error", err.toString());
        }

        String URL2 = WCFHandler.webUrl+"users/ShareIt";
        RequestQueue requestQueue2 = Volley.newRequestQueue(c);
        final String requestBody2 = jsonString2.toString();

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                URL2, sh, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody2 == null ? null : requestBody2.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody2, "utf-8");
                    return null;
                }
            }
        };

        requestQueue2.add(jsonObjectRequest2);

    } catch (Exception e) {
    }




}
            private void fetchData()
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String UserName = preferences.getString("UserName", uname);

        String data = WCFHandler.GetJsonResult("users/GetFriends?UserName="+UserName);

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        users = gson.fromJson(data, type);





    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int position = listView.getPositionForView(compoundButton);

        if (position != ListView.INVALID_POSITION){

            String itm = users.get(position);
            if(b){lst.add(itm);}
            else{lst.remove(itm);}


         //   Toast.makeText(this, "Selected : "+ itm, Toast.LENGTH_SHORT).show();
        }
    }
}



