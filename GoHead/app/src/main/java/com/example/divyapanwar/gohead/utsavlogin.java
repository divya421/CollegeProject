package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class utsavlogin extends AppCompatActivity {
    EditText email,password;
   Button login;
   FirebaseAuth auth;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        setContentView(R.layout.activity_utsavlogin);
        auth=FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.login2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        login.setOnClickListener(new View.OnClickListener() {
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

                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(utsavlogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Authentication failed"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                        else  {Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(utsavlogin.this,party.class));
                            finish();}
                    }
                });
            }
        });
    }



}
