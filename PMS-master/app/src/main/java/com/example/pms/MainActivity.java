package com.example.pms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pms.Controller.Credentials;
import com.example.pms.view.SignUp;
import com.example.pms.view.firstScreen;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    Button signIn;
    EditText userName;
    EditText password;
    Credentials ownerCredential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ownerCredential = Credentials.getInstance();

        signUp = (Button) findViewById(R.id.signUpBtn);
        signIn = (Button) findViewById(R.id.signInBtn);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
       // userName.setText("abdullah");
       // password.setText("123456789");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, SignUp.class);
                startActivity(i1);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean signInSuccess;
              signInSuccess =  ownerCredential.logIn(userName.getText().toString(),password.getText().toString(),MainActivity.this);
                if(!signInSuccess){
                    Toast.makeText(MainActivity.this,"password incorrect",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(this,"SignUp successfu")
                    Intent i1 = new Intent(MainActivity.this, firstScreen.class);
                  //  finishAffinity();
                    startActivity(i1);
                }
            }
        });
    }
}