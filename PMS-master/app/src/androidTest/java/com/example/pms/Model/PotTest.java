package com.example.pms.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PotTest {
    Pot pot;
    Plant pt;
    @Before
    public void setUp() {
        pot = new Pot();
        pt = new Plant();
    }

    @Test
    public void setPotID_Test() {
        int expected = 0;
        pot.setPotId(expected);
        assertEquals(pot.getPotId(),expected);
    }

    @Test
    public void setPotName_Test() {
        String expected = "Pot1";
        pot.setPotName(expected);
        assertEquals(expected,pot.getPotName());
    }

    @Test
    public void setPlant_Test() {
        pot.setPlant(pt);
        assertEquals(pot.getPlant(),pt);
    }

    @Test
    public void ModifyPlant_Test() {
        Plant pt2 = new Plant();
        pot.setPlant(pt);
        pot.modifyPlant(pt2);
        assertEquals(pot.getPlant(),pt2);
    }

    @Test
    public void AddPlant_Test() {
        Plant pt2 = new Plant();
        pot.setPlant(pt);
        pot.addPlant(pt2);
        assertEquals(pot.getPlant(),pt2);
    }

    @Test
    public void RemovePlant_Test() {
        pot.setPlant(pt);
        //assertEquals(pot.getPlant(),pt);
        pot.removePlant();
        assertFalse(pot.getPlant()==pt);
    }

    @Test
    public void UpdateLevel_Test() {
        double water = 0;
        double sunlight = 0;


            pt.setWaterLevel(40);

            pt.setLightIntensity(40);


        pt.setCurrentWaterLevel(water);
        pt.setCurrentLightIntensity(sunlight);

        pot.setPlant(pt);

        pot.updateLevel();

        assertTrue(pot.getPlant().getCurrentWaterLevel()==pot.getPlant().getWaterLevel() && pot.getPlant().getCurrentLightIntensity()==pot.getPlant().getLightIntensity());
    }

    @Test
    public void UpdateLevel_Test2() {
        double water = 30;
        double sunlight = 30;


            pt.setWaterLevel(40);

            pt.setLightIntensity(40);


        pt.setCurrentWaterLevel(water);
        pt.setCurrentLightIntensity(sunlight);

        pot.setPlant(pt);

        pot.updateLevel();

        assertTrue(pot.getPlant().getCurrentWaterLevel()==20 && pot.getPlant().getCurrentLightIntensity()==20);
    }

    @Test
    public void UpdateFertilierLevel_Test() {


            pt.setFertilizerLevel(30);


        pot.setPlant(pt);
        pot.updateFertilizerLevel(pot.getPlant().getFertilizerLevel());

        assertTrue(pot.getPlant().getFertilizerLevel()==30);
    }

    @Test
    public void UpdateWaterLevel_Test() {

            pt.setWaterLevel(40);


        pot.setPlant(pt);
        pot.updateWaterLevel(pot.getPlant().getWaterLevel());

        assertTrue(pot.getPlant().getWaterLevel()==40);
    }

    @Test
    public void UpdateLightIntensity_Test() {

            pt.setLightIntensity(50);


        pot.setPlant(pt);
        pot.updateLightIntensity(pot.getPlant().getLightIntensity());

        assertTrue(pot.getPlant().getLightIntensity()==50);
    }

}
