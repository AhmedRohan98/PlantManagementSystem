package com.example.pms.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Specie {
    //@ColumnInfo(name = "waterLevel")
    protected double waterLevel;
    protected double fertilizerLevel;
    protected double lightIntensity;

//    public Specie(double waterLevel, double fertilizerLevel,double lightIntensity){
//        this.waterLevel = waterLevel;
//        this.fertilizerLevel = fertilizerLevel;
//        this.lightIntensity = lightIntensity;
//    }



    public double getWaterLevel() {
        return waterLevel;
    }

    public double getFertilizerLevel() {
        return fertilizerLevel;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setFertilizerLevel(double fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    public void setLightIntensity(double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}
