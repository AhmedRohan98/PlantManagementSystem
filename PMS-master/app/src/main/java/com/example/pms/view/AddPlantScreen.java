package com.example.pms.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pms.Controller.AHNMS;
import com.example.pms.Exceptions.ValueException;
import com.example.pms.Model.Plant;
import com.example.pms.R;

public class AddPlantScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        EditText potName;
        EditText plantName;
        EditText waterLevel;
        EditText fertilizerLevel;
        EditText lightIntensity;
        Button addBtn;
        String plantType;
    AHNMS potsController;
    private Spinner spinner;
    private static final String[] paths = {"Daffoldils", "Sunflower", "Rose", "Cactus", "Lilly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_screen);
        potName = (EditText) findViewById(R.id.tiet_add_plant_potName);
        plantName = (EditText) findViewById(R.id.tiet_add_plant_nickname);
        waterLevel = (EditText) findViewById(R.id.tiet_add_plant_water);
        fertilizerLevel = (EditText) findViewById(R.id.tiet_add_plant_fertilizer);
        lightIntensity = (EditText) findViewById(R.id.tiet_add_plant_sunlight);
        addBtn = (Button) findViewById(R.id.addBtn);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(AddPlantScreen.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AddPlantScreen.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(potName.getText().toString() == "" || plantName.getText().toString() == "" || waterLevel.getText().toString() == "" || fertilizerLevel.getText().toString() == "" || lightIntensity.getText().toString() == ""){
                    Toast.makeText(AddPlantScreen.this,"Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    validateLevel();
                } catch (ValueException e) {
                    Toast.makeText(AddPlantScreen.this,"Please enter value of water level/light intensity greater than 10", Toast.LENGTH_SHORT).show();
                    return;
                }
                Plant plant =  new Plant();
                plant.setWaterLevel(Double.parseDouble(waterLevel.getText().toString()));
                plant.setCurrentLightIntensity(Double.parseDouble(lightIntensity.getText().toString()));
                plant.setCurrentWaterLevel(Double.parseDouble(waterLevel.getText().toString()));
                plant.setFertilizerLevel(Double.parseDouble(fertilizerLevel.getText().toString()));
                plant.setLightIntensity(Double.parseDouble(lightIntensity.getText().toString()));
                plant.setPlantName(plantName.getText().toString());
                plant.setPlanttype(plantType);
                 potsController = AHNMS.getInstance();
                potsController.addPot(potName.getText().toString(),plant,AddPlantScreen.this);
                makeThread();
                finish();

            }
        });
    }

    public void validateLevel() throws ValueException {
        int waterLevelInt = Integer.parseInt(waterLevel.getText().toString());
        int lightLevelInt = Integer.parseInt(lightIntensity.getText().toString());
        if(waterLevelInt <=10 || lightLevelInt <=10){
            throw  new ValueException();
        }
    }

    public void makeThread(){
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void run() {
               int size = potsController.getNoOfPots();
             // int potId = potsController.getPotId(size-1);
                while (true) {

               // if(potsController.checkPot(potId)){
                  Plant plant =  potsController.retrieveInfo(size-1);
                  if(plant != null){
                      if(plant.getCurrentWaterLevel() == 10){
                            sendNotification( AddPlantScreen.this, plant);
                      }
                     // sendNotification(AddPlantScreen.this,plant);
                  }
                    potsController.updateLevel(size-1);
               // }




                    try {
                        // i[0]++;
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//


                }
            }
        }.start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                plantType = "daffodils";
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                plantType = "sunflower";
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                plantType = "rose";
                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                plantType = "cactus";
                break;
            case 4:
                // Whatever you want to happen when the thrid item gets selected
                plantType = "lilly";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendNotification(Context context, Plant plant) {
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