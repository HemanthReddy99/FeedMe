package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button b;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.student_email_login_text_view);
        e2 = findViewById(R.id.student_password_login_text_view);
        b = findViewById(R.id.student_login_button);
        t = findViewById(R.id.register);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = e1.getText().toString();
                String password = e2.getText().toString();


                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Profile.class);
                    startActivity(intent);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Please enter details",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}