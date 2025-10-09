package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPlantCareInfo {
    PlantCareInfo testPlantCareInfo;

    @BeforeEach
        void runBefore() {
            testPlantCareInfo = new PlantCareInfo("Weekly", 1, "Loamy", "Cedar mulch");
        }

    @Test
        void testConstructor() {
            assertEquals("Weekly", testPlantCareInfo.getWateringFreq());
            assertEquals(1, testPlantCareInfo.getSunlight());
            assertEquals("Loamy", testPlantCareInfo.getSoilType());
            assertEquals("Cedar mulch", testPlantCareInfo.getfertilizer());
        }

}
