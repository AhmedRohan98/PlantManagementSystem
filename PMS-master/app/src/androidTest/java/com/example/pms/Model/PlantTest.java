package com.example.pms.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlantTest {
    Plant pt;
    @Before
    public void setUp() {
        pt = new Plant();
    }
    @Test
    public void setPlantType_Test() {
        String expected = "Rose";
        pt.setPlanttype(expected);
        assertEquals(expected,pt.getPlanttype());
    }

    @Test
    public void setPlantID_Test() {
        int expected = 1;
        pt.setId(expected);
        assertEquals(expected,pt.getId());
    }

    @Test
    public void setCurrentWaterLevel_Test() {
        int expected = 40;
        pt.setCurrentWaterLevel(expected);
        assertTrue(expected==pt.getCurrentWaterLevel());
    }

    @Test
    public void setCurrentLightIntensity_Test() {
        int expected = 40;
        pt.setCurrentLightIntensity(expected);
        assertTrue(expected==pt.getCurrentLightIntensity());
    }

    @Test
    public void setPlantName_Test() {
        String expected = "Plant1";
        pt.setPlantName(expected);
        assertEquals(expected,pt.getPlantName());
    }
}
