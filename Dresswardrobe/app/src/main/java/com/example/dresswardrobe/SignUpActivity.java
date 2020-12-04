package com.example.dresswardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


public class SignUpActivity extends AppCompatActivity {

    @NotEmpty
    TextView _tbUName, _tbFName, _tbLName, _tbPass;

    private RadioGroup _rg;
    private RadioButton _rbutton;
    private Button b;
    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        _tbUName = findViewById(R.id.unReg);
        _tbFName = findViewById(R.id.fnReg);
        _tbLName = findViewById(R.id.lnReg);
        _tbPass = findViewById(R.id.pwdReg);
        _rg = findViewById(R.id.radGen);
      int _selectedbtn = _rg.getCheckedRadioButtonId();
      _rbutton = findViewById(_selectedbtn);
        b = findViewById(R.id.BtnSignUp);
        b.setOnClickListener(v -> {
            String _username = _tbUName.getText().toString();
            String _firstname = _tbFName.getText().toString();
            String __lastname = _tbLName.getText().toString();
            String _password = _tbPass.getText().toString();
            String M = "M";
            String api_url = "users/Register?UserName=" + _username + "&FirstName=" + _firstname + "&LastName=" + __lastname + "&Password=" + _password + "&Gender=" +M ;
            String ja = WCFHandler.GetJsonResult(api_url);
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            Toast.makeText(getApplicationContext(), "Sign up was successful", Toast.LENGTH_SHORT).show();
                startActivity(i);
        });

    }

}


