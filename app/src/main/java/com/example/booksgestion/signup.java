package com.example.booksgestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksgestion.entities.User;

public class signup extends AppCompatActivity {
    EditText email;
    EditText pass,confirmpass,userName;
    DataHelper db = new DataHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userName=findViewById(R.id.newUsername);
        pass = findViewById(R.id.newPassword);
        email = findViewById(R.id.newEmail);
        confirmpass=findViewById(R.id.ConfirmPassword);
        Button signupbtn = findViewById(R.id.btnsignup);
        Button tologin = findViewById(R.id.btntoLogin);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                String user = userName.getText().toString();
                String confirm = confirmpass.getText().toString();
                long result = 0;
                if (!ispassmatch(userPass, confirm)) {
                    Toast.makeText(getApplicationContext(), "passwords do no match", Toast.LENGTH_SHORT).show();
                } else {
                    User newUser = new User(-1, userEmail, userPass, user);
                    result = db.insertUser(newUser);
                }
                if (result == -1) {
                    Toast.makeText(getApplicationContext(), "there's a problem check again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "signing up succesful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signup.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected Boolean ispassmatch(String pass1,String confirmpass){
        if(pass1.equals(confirmpass)){
            return true;
        }else{
            return false;
        }
    }
}