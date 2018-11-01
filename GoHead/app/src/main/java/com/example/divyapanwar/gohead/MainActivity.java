package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  ProgressBar pr;
  int status=0;
  Handler hd=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pr=(ProgressBar)findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 100) {
                    status++;
                    SystemClock.sleep(50);
                    hd.post(new Runnable() {
                        @Override
                        public void run() {
                            pr.setProgress(status);
                        }
                    });
                }
                hd.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this, Signup.class);
                        startActivity(i);
                    }
                });
            }
        }).start();


  }
}
