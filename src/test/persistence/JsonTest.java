package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import java.util.ArrayList;
import model.Plant;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkPlant(String name, String subType, int category, 
                                String lastWatered, ArrayList<String> notesOfPlant, Plant plant) {
        assertEquals(name, plant.getName());
        assertEquals(subType, plant.getSubtype());
        assertEquals(category, plant.getCategory());
        assertEquals(lastWatered, plant.getLastWatered());
        assertEquals(notesOfPlant, plant.getNotes());
    }

    protected void checkPlantInfo(String wateringFreq, int sunlight, String soilType, String fertilizer, Plant plant) {
        assertEquals(wateringFreq, plant.getCareInfo().getWateringFreq());
        assertEquals(sunlight, plant.getCareInfo().getSunlight());
        assertEquals(soilType, plant.getCareInfo().getSoilType());
        assertEquals(fertilizer, plant.getCareInfo().getFertilizer());
    }

}
