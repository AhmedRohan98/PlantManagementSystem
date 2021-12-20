package com.example.pms.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pms.Controller.AHNMS;
import com.example.pms.MainActivity;
import com.example.pms.Model.Plant;
import com.example.pms.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlantDetailScreen extends AppCompatActivity {

    TextView plantName;
    TextView waterLevel;
    TextView fertilizerLevel;
    TextView sunlightIntensity;
    AHNMS potsController;
    ImageView plantImg;
    Button deleteBtn;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail_screen);
        waterLevel = (TextView) findViewById(R.id.waterTxt);
        plantName = (TextView) findViewById(R.id.plantName);
        fertilizerLevel = (TextView) findViewById(R.id.fertilizerText);
        sunlightIntensity = (TextView) findViewById(R.id.sunlightText);
        plantImg = (ImageView) findViewById(R.id.plantImg);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        potsController = AHNMS.getInstance();
        Bundle b1 = getIntent().getExtras();
        index = b1.getInt("idx");
        Plant plant = potsController.retrieveInfo(index);
        if (plant != null) {
            plantName.setText(plant.getPlantName() );
            if(plant.getPlanttype().compareTo("daffodils") == 0){
                plantImg.setImageResource(R.drawable.daffoldils);
            }
            else if(plant.getPlanttype().compareTo("sunflower") == 0){
                plantImg.setImageResource(R.drawable.sunflower);
            }
            else if(plant.getPlanttype().compareTo("rose") == 0){
                plantImg.setImageResource(R.drawable.rose);
            }
            else if(plant.getPlanttype().compareTo("cactus") == 0){
                plantImg.setImageResource(R.drawable.cactus);
            }
            else if(plant.getPlanttype().compareTo("lilly") == 0){
                plantImg.setImageResource(R.drawable.lily);
            }

            Thread t1 =   new Thread() {
                @Override
                public void run() {
                    while (true) {


                        runOnUiThread(new Runnable() {

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void run() {
                                if(plant!= null){
                                    if(plant.getCurrentWaterLevel() == 10){
                                      //  sendNotification( PlantDetailScreen.this, plant);
                                    }
                                    waterLevel.setText("Water Level: " + ((plant.getCurrentWaterLevel()<= 10) ? "Adding Water" : plant.getCurrentWaterLevel() ));
                                    fertilizerLevel.setText("Fertilization Level: " + plant.getFertilizerLevel());
                                    sunlightIntensity.setText("Light Intensity: " + ((plant.getCurrentLightIntensity() < 40) ? "on" : "off"));
                                }

                            }
                        });
                        // potsController.updateLevel(index);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            };
            t1.start();
        }



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potsController.removePot(index,PlantDetailScreen.this);
             //   t1.interrupt();
                Intent i1 = new Intent(PlantDetailScreen.this, firstScreen.class);
              //  finishAffinity();
                startActivity(i1);
               // finish();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendNotification(Context context,Plant plant) {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(getApplicationContext());
        }

        builder = builder
                .setSmallIcon(R.drawable.sunflower)
                .setContentTitle("Water Time")
                .setContentText("Watering your Plant: " + plant.getPlantName())
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true);
        notificationManager.notify(0, builder.build());
    }
}