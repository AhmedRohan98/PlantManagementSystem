package com.example.pms.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pms.Controller.AHNMS;
import com.example.pms.Controller.Credentials;
import com.example.pms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class firstScreen extends AppCompatActivity {


    RecyclerView r1;
    MyOwnAdapter ad;
    FloatingActionButton addBtn;
    TextView heading;
    AHNMS potsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
      AHNMS  potsController = AHNMS.getInstance();
      potsController.init(firstScreen.this);        //getting pots list from database
        r1 = (RecyclerView) findViewById(R.id.myRec);
        heading = (TextView) findViewById(R.id.heading);
        Credentials user = Credentials.getInstance();
        String name = user.getName();

        heading.setText(user.getName() + "'s Plants");
        addBtn = (FloatingActionButton) findViewById(R.id.addbtn);
        ad = new MyOwnAdapter(firstScreen.this);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(firstScreen.this, AddPlantScreen.class);
                startActivity(i1);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
//        ad = new MyOwnAdapter(firstScreen.this);
//        r1.setAdapter(ad);
//        r1.setLayoutManager(new LinearLayoutManager(this));
            ad.notifyDataSetChanged();

    }
}