package com.example.pms.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.example.pms.Model.Plant;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AHNMSTest {

    private static AHNMS ahnms;
    private static Credentials credentials;
    private static String username;
    private static String password;
    private static Context context;

    private static Plant testplant;
    private static double initialLightIntensity;
    private static double initalFertilizationLevel;
    private static double initalWaterLevel;


    @BeforeClass
    public static void setUpBeforeAll() throws Exception {

        ahnms = AHNMS.getInstance();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        credentials = Credentials.getInstance();
        username = "test123";
        password = "ab434433gtgtc";
        credentials.logIn(username, password, context);
        ahnms.init(context);


        initialLightIntensity = 0.5;
        initalFertilizationLevel = 0.1;
        initalWaterLevel = 0.3;
    }

    @Before
    public void setupBeforeEach() {
        testplant = new Plant();
        testplant.setPlantName("Test Plant");
        testplant.setCurrentLightIntensity(initialLightIntensity);
        testplant.setFertilizerLevel(initalFertilizationLevel);
        testplant.setWaterLevel(initalWaterLevel);

        ahnms.addPot("Test Pot", testplant, context);
        int id = ahnms.getPotId(0);

        ahnms.addPlant(id, testplant);

    }

    @Test
    public void manageFertilization() {

        double newFertilizationLevel = 0.9;
        int id = ahnms.getPotId(0);
        Plant retrieve = ahnms.retrieveInfo(0);
        assertEquals(initalFertilizationLevel, retrieve.getFertilizerLevel(), 1.0);
        ahnms.manageFertilizationLevel(id, newFertilizationLevel, context);
        assertEquals(newFertilizationLevel, retrieve.getFertilizerLevel(), 1.0);
    }

    @Test
    public void manageWaterLevel() {

        double newWaterLevel = 0.75;
        int id = ahnms.getPotId(0);
        Plant retrieve = ahnms.retrieveInfo(0);
        assertEquals(initalWaterLevel, retrieve.getWaterLevel(), 1.0);
        ahnms.manageWaterLevel(id, newWaterLevel, context);
        assertEquals(newWaterLevel, retrieve.getWaterLevel(), 1.0);
    }

    @Test
    public void manageLightIntensity() {

        double newLightIntensity = 0.88;
        int id = ahnms.getPotId(0);
        Plant retrieve = ahnms.retrieveInfo(0);
        assertEquals(initialLightIntensity, retrieve.getLightIntensity(), 1.0);
        ahnms.manageLightIntensity(id, newLightIntensity, context);
        assertEquals(newLightIntensity, retrieve.getLightIntensity(), 1.0);
    }

    @Test
    public void removePlant() {

        int id = ahnms.getPotId(0);
        ahnms.removePlant(id, context);
        assertNull(ahnms.retrieveInfo(id));
    }

    @After
    public void afterEach(){
        ahnms.removePot(0, context);
    }


}