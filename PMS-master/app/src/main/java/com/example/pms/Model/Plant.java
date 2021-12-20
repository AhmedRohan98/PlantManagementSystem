package com.example.pms.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plant extends Specie{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String plantName;
    private double currentWaterLevel;
    private double currentLightIntensity;
    public Plant(){
        super();
    }

    public String getPlanttype() {
        return planttype;
    }

    public void setPlanttype(String planttype) {
        this.planttype = planttype;
    }

    private String planttype;

//    public Plant(String name, double waterLevel, double fertilizerLevel,double lightIntensity){
//        super(waterLevel,fertilizerLevel,lightIntensity);
//        this.plantName = name;
//        this.currentLightIntensity = lightIntensity;
//        this.currentWaterLevel = waterLevel;
//
//
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String name) {
        this.plantName = name;
    }

    public double getCurrentWaterLevel() {
        return currentWaterLevel;
    }

    public void setCurrentWaterLevel(double currentWaterLevel) {
        this.currentWaterLevel = currentWaterLevel;
    }

    public double getCurrentLightIntensity() {
        return currentLightIntensity;
    }

    public void setCurrentLightIntensity(double currentLightIntensity) {
        this.currentLightIntensity = currentLightIntensity;
    }
}
