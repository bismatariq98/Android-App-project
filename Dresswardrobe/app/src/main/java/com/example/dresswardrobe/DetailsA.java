package com.example.dresswardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailsA extends AppCompatActivity   {
   private TextView rv,q1,q2,q3;
   private EditText q4;
   private ImageView ig;
    String dt;
    SharedPreferences prf;
    Button b;
    String username;
    Bitmap _bitmap;
    private Calendar c;
    private Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsa);
        Intent intent = getIntent();
        b = findViewById(R.id.button);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        username = prf.getString("username",null);

//        b.setOnClickListener(view -> {
  //          show_Datepicker();

    //    });
        if(intent.hasExtra("byteArray")) {
            Bitmap _bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);

        }
        String Itmname = intent.getStringExtra("itemname");
        String usename = intent.getStringExtra("username");
        String clr = intent.getStringExtra("clr");
        q2 = findViewById(R.id.tvclr);
        q2.setText("The color is " +clr);
        q3 = findViewById(R.id.tvusritem);
        q3.setText("This item belongs to " +usename);
        q4 = findViewById(R.id.tvdate);
        rv = findViewById(R.id.tvitem);
        rv.setText(Itmname);
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        String dt1 = thisDate;  // Start date
        q1 = findViewById(R.id.tvdate1);

        q1.setText("The current date is " + dt1);
        String dt = "13-09-2020";  // Start date
//        Calendar c = Calendar.getInstance();
  //      c.add(Calendar.DATE, 40);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        //SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        //String output = sdf1.format(c.getTime());
        if(intent.hasExtra("byteArray")) {
            _bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);

        }
        ig = findViewById(R.id.imgup);
        ig.setImageBitmap(_bitmap);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q5 = q4.getText().toString();
                String api_url = "users/ShareItems?ITEM_NAME="+Itmname+"&UserName="+username+"&Sharing_Date="+0+"&Return_Date="+q5+"&USER_TO="+usename;
                String ja = WCFHandler.GetJsonResult(api_url);
                Toast.makeText(getApplicationContext(), "Item borrowed", Toast.LENGTH_LONG).show();
                Intent k = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(k);
                //Log.d(ja, "onClick: ");
            }
        });

    }


    }

