package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout allexams = findViewById(R.id.studenttotalexams);
        allexams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,MainActivity.class);
                startActivity(i);
            }
        });

        TextView logout = findViewById(R.id.profilelogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent student_logout = new Intent(getApplicationContext(), Login.class);
                student_logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(student_logout);
            }
        });
    }
}