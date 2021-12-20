package com.example.pms.Model;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pot {

    static int index = 0;
    @PrimaryKey(autoGenerate = true)
    private int potId;
    private String potName;
    @Embedded
    private Plant plant;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }



    public void setPotId(int potId) {
        this.potId = potId;
    }

    public int getPotId() {
        return potId;
    }



    public String getPotName() {
        return potName;
    }

    public void setPotName(String name) {
        this.potName = name;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void modifyPlant(Plant plant){
        this.plant = plant;
    }
    public void removePlant(){this.plant = null; }
    public void addPlant(Plant plant){
        this.plant = plant;
    }
    public void updateLevel(){
        double water = plant.getCurrentWaterLevel();
        double sunlight = plant.getCurrentLightIntensity();
        if(water <= 0 ){
            plant.setCurrentWaterLevel(plant.getWaterLevel());
        }
        else{
            plant.setCurrentWaterLevel(plant.getCurrentWaterLevel() - 10);
        }
        if(sunlight <= 10){
            plant.setCurrentLightIntensity(plant.getLightIntensity() );
        }
        else{
            plant.setCurrentLightIntensity(plant.getCurrentLightIntensity() - 10);
        }


    }


    public void updateFertilizerLevel(double fertilizerLevel){
        plant.setFertilizerLevel(fertilizerLevel);
    }
    public void updateWaterLevel(double waterLevel){
        plant.setWaterLevel(waterLevel);
    }
    public void updateLightIntensity(double lightIntensity){
        plant.setLightIntensity(lightIntensity);
    }
}
