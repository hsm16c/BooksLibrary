package com.example.booksgestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText pass;
    DataHelper db = new DataHelper(this);
    private boolean userexists ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username=findViewById(R.id.editUsername);
        pass = findViewById(R.id.editPassword);

        Button login= findViewById(R.id.btnLogin);
        Button toSignup = findViewById(R.id.btntoSignUp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = username.getText().toString();
                String userPass = pass.getText().toString();
                userexists=db.checkUser(userEmail,userPass);
                if (userexists){
                    Toast.makeText(getApplicationContext(), "welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,MainDashboard.class);
                    intent.putExtra("user",userEmail);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        toSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
                finish();
            }
        });

    }
}