package com.example.pms.Model;

import static junit.framework.TestCase.assertTrue;

import com.example.pms.Exceptions.ValueException;

import org.junit.Before;
import org.junit.Test;

public class SpecieTest {
    Specie sp;
    @Before
    public void setUp() {
        sp = new Specie();
    }

    @Test
    public void setWaterLevel_Test() {
        double expected = 60;
        sp.setWaterLevel(expected);
        assertTrue(sp.getWaterLevel()==expected);
    }

    @Test
    public void setFertilizerLevel_Test() {
        double expected = 60;
        sp.setFertilizerLevel(expected);
        assertTrue(sp.getFertilizerLevel()==expected);
    }

    @Test
    public void setLightIntensity_Test() {
        double expected = 60;
        sp.setLightIntensity(expected);
        assertTrue(sp.getLightIntensity()==expected);
    }
}
