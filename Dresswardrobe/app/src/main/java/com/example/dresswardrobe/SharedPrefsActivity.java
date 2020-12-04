package com.example.dresswardrobe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SharedPrefsActivity extends Activity {

    public static final String PREFS_NAME = "MyApp_Settings";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key", "some value");
        editor.commit();

        // Reading from SharedPreferences
        String value = settings.getString("key", "");
        Log.d(TAG, value);
    }
}