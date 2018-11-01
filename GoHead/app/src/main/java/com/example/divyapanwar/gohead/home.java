package com.example.divyapanwar.gohead;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity {
    public DrawerLayout draw;
    private ActionBarDrawerToggle toggle;
    ImageView im,im1,im2,im3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        im=(ImageView)findViewById(R.id.fest);
        im1=(ImageView)findViewById(R.id.depo);
        im2=(ImageView)findViewById(R.id.assign);
        im3=(ImageView)findViewById(R.id.corner);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(home.this,utsav.class);
                startActivity(in);
            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(home.this, assignment.class);
                startActivity(in2);
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=new Intent(home.this,department.class);
                startActivity(in3);
            }
        });
        draw=(DrawerLayout)findViewById(R.id.drawer);
        toggle= new ActionBarDrawerToggle(this ,draw, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        draw.addDrawerListener(toggle);
        toggle.syncState();
        final NavigationView nav=(NavigationView)findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.utsav){
                    Intent intent=new Intent(home.this,utsav.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
