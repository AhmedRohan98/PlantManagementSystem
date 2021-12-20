package com.example.pms.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pms.Controller.Credentials;
import com.example.pms.R;

public class SignUp extends AppCompatActivity {

    Button signInBtn;
    Button signUpBtn;
    EditText userName;
    EditText password;
    EditText confirmPass;


    Credentials ownerCredential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ownerCredential = Credentials.getInstance();

        signInBtn = (Button) findViewById(R.id.signInBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);



        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {boolean signUpSuccess;

                if(password.getText().toString().compareTo(confirmPass.getText().toString()) != 0){
                    confirmPass.setError("does not match password");

                }
                else{
                    signUpSuccess = ownerCredential.signUp(userName.getText().toString(),password.getText().toString(),SignUp.this);
                    if(!signUpSuccess){
                        password.setError("Password must be greater than 8 characters");
                        userName.setError("Username must be greater than 5 characters");
                    }
                    else{
                        //Toast.makeText(this,"SignUp successfu")
                        Intent i1 = new Intent(SignUp.this, firstScreen.class);
                       // finishAffinity();
                        startActivity(i1);
                    }
                }


            }
        });
    }
}