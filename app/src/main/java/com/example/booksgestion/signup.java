package com.example.booksgestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText email;
    EditText pass,confirmpass,userName;
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
                if (!ispassmatch(userPass,confirm)){
                    Toast.makeText(getApplicationContext(),"passwords do no match",Toast.LENGTH_SHORT).show();
                }else{

                }

            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,signup.class);
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