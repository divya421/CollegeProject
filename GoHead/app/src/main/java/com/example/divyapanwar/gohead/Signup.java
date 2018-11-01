package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1, e2,e3;
    Button b1,b2;
    Boolean insert, checkEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        db=new DatabaseHelper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=e1.getText().toString();
                String password=e2.getText().toString();
                String confirm=e3.getText().toString();
                if(email.equals("")||password.equals("")||confirm.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(confirm)){
                       checkEmail =db.checkEmail(email);
                        if(checkEmail==true) {
                            insert = db.insert(email, password);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Signup.this, home.class);
                                startActivity(i);
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

    }
}
