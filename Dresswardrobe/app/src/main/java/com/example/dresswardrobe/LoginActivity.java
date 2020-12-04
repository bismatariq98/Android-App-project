package com.example.dresswardrobe;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import static android.content.ContentValues.TAG;
import static com.example.dresswardrobe.SharedPrefsActivity.PREFS_NAME;

public class LoginActivity extends AppCompatActivity {
   // List<Login> login = new ArrayList<>();
    ArrayAdapter<String> adapter;
    TextView _tName, _tPassword, Signup;
    private Button b;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=findViewById(R.id.BtnLogin);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        final Gson gson = new Gson();
        _tName = findViewById(R.id.UsrLogin);
        Signup = findViewById(R.id.SignUpAct);
        _tPassword = findViewById(R.id.PassLogin);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String tbName = _tName.getText().toString();
            String tbPass = _tPassword.getText().toString();

                //String tbName = "Zohaib";
       //         String tbPass = "abc123";
                String api_url = "users/Login?UserName=" + tbName + "&Password=" + tbPass;
                String ja = WCFHandler.GetJsonResult(api_url);
                Gson gson = new Gson();
                User user = gson.fromJson(ja, User.class);
                if (user.UserName != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",tbName);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username and password are mismatched please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}