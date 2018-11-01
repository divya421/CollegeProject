package com.example.divyapanwar.gohead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class assignment extends AppCompatActivity {
    Button b1,b2,b3,b4;
   Intent intent,intent2, intent3,intent4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        b1=(Button)findViewById(R.id.button5);
        b2=(Button)findViewById(R.id.button3);
        b3=(Button)findViewById(R.id.button7);
        b4=(Button)findViewById(R.id.button8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent=new Intent(assignment.this,bca.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2=new Intent(assignment.this,bvoc.class);
                startActivity(intent2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3=new Intent(assignment.this,digital.class);
                startActivity(intent3);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent4=new Intent(assignment.this,medical.class);
                startActivity(intent4);
            }
        });

    }
}
