package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText e1, e2;
Button b1;
Boolean check;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                         check = db.emailPassword(email, password);
                         if (check == true) {
                           Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(Login.this, home.class);
                           startActivity(i);
                         }
                         else
                        Toast.makeText(getApplicationContext(), "wrong email or password", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
