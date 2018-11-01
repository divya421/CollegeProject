package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class utsav extends AppCompatActivity {
    private  EditText email,password;
private Button signup,login;
private FirebaseAuth auth;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utsav);
        auth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.editText5);
        password=(EditText)findViewById(R.id.editText6);
        signup=(Button) findViewById(R.id.signup);
        login=(Button) findViewById(R.id.login);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(utsav.this,utsavlogin.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if(TextUtils.isEmpty(em)){
                    Toast.makeText(getApplicationContext(),"please enter the email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"please enter the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"password is too short",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(utsav.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Authentication failed",Toast.LENGTH_SHORT).show();
                        }
                        else  {Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(utsav.this,party.class));
                        finish();}
                    }
                });
            }
        });
    }
    protected void onResume() {

        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
