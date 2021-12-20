package com.example.pms.Controller;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.pms.Database.OwnerDatabase;
import com.example.pms.Model.Plant;
import com.example.pms.Model.Pot;
import com.example.pms.R;
import com.example.pms.view.AddPlantScreen;

import java.util.List;

public class AHNMS {
    private List<Pot> pots = null;
    private static AHNMS INSTANCE;

    public static AHNMS getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new AHNMS();
        }
        return INSTANCE;
    }
    public void init(Context context){
        if(pots == null){
            Credentials user = Credentials.getInstance();
            OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
            pots = db.potDao().getAllPots(user.getUid());
            for(int i=0;i<pots.size();++i){
                makeThread(i,context);
            }
        }

    }

    public int getPotId(int idx){
        return pots.get(idx).getPotId();
    }
    public boolean checkPot(int id){
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                return true;
            }
        }
        return false;
    }

    public void updateLevel(int index){
        if(pots.isEmpty()){
            return;
        }
        if(index >= pots.size()){
            return;
        }
        pots.get(index).updateLevel();
    }

    public String getPotName(int idx){
        return pots.get(idx).getPotName();
    }

    public int getNoOfPots(){
        return pots.size();
    }

    public void addPot(String name, Plant plant, Context context){
        Pot pot = new Pot();
        pot.setPotName(name);
        pot.setPlant(plant);
        Credentials user = Credentials.getInstance();
        pot.setUid(user.getUid());

        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        db.potDao().insertPot(pot);
        int potid = db.potDao().getPotId(name);
        pot.setPotId(potid);
        pots.add(pot);
       //pots.get(pots.size()-1).setPotId(potid);

    }

    public void removePot(int idx,Context context){

       // int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        db.potDao().delete(pots.get(idx));
//        for(int i=0;i<pots.size();++i){
//            if(pots.get(i).getPotId() == id){
//                db.potDao().delete(pots.get(i));
//                idx = i;
//                break;
//            }
//        }
        pots.remove(idx);
    }
    public void modifyPot(int id,Plant plant,Context context){
        //int idx = -1;
       // String potName;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){

                db.potDao().delete(pots.get(i));
                //potName = pots.get(i).getPotName();
                pots.get(i).modifyPlant(plant);
                db.potDao().insertPot(pots.get(i));
               // idx = i;
                break;
            }
        }
       // pots.remove(idx);
    }
    public void removePlant(int id,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                db.potDao().delete(pots.get(i));
                idx = i;
                Log.d("index", i + "");
                break;
            }
        }
//        pots.get(idx).removePlant();
//        pots.remove(idx);
    }
    public void addPlant(int id,Plant plant){
        int idx = -1;
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                pots.get(i).addPlant(plant);
                break;
            }
        }
    }
    public void manageFertilizationLevel(int id,double fertilizationLevel,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateFertilizerLevel(fertilizationLevel);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }
    public void manageWaterLevel(int id,double waterLevel,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateWaterLevel(waterLevel);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }
    public void manageLightIntensity(int id,double lightIntensity,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateLightIntensity(lightIntensity);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }

    public Plant retrieveInfo(int index){
//        for(int i=0;i<pots.size();++i){
//            if(pots.get(i).getPotId() == id){
//                // db.potDao().delete(pots.get(i));
//               return pots.get(i).getPlant();
//
//            }
//        }
        if(pots == null){
            return null;
        }
        if(pots.isEmpty()){
            return null;
        }
        if(index< pots.size()){
            return pots.get(index).getPlant();
        }
        return null;
    }

    public void makeThread(int idx,Context context){
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void run() {

                while (true) {

                    Plant plant = pots.get(idx).getPlant();
                    if(plant.getCurrentWaterLevel() <= 10){
                        sendNotification(context , plant);
                    }

                   pots.get(idx).updateLevel();



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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendNotification(Context context, Plant plant) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new NotificationCompat.Builder(context, notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(context);
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
