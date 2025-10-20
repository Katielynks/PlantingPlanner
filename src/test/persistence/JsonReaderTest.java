package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Plant;
import model.PlantCollection;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PlantCollection pc = reader.read();
            fail("IOException expected");            
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlantCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlantCollection.json");
        try {
            PlantCollection pc = reader.read();
            assertEquals(0, pc.getCollection().size());
        } catch (IOException e) {
            fail("Couldn't read from the file.");
        }
    }

    @Test
    void testReaderGeneralPlantCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlantCollection.json");
        try {
            PlantCollection pc = reader.read();
            assertEquals(2, pc.getCollection().size());
            List<Plant> plants = pc.getCollection();

            ArrayList<String> notesPlant1 = new ArrayList<String>();
            notesPlant1.add("needs warm space");
            notesPlant1.add("grows quickly");

            checkPlant("cactus", "aloe", 1, "2025-10-19", notesPlant1, plants.get(0));
            checkPlantInfo("weekly", 2, "sand", "none", plants.get(0));

            ArrayList<String> notesPlant2 = new ArrayList<String>();
            notesPlant2.add("likes direct sun");
            notesPlant2.add("needs ventilation");
            notesPlant2.add("can get mold");

            checkPlant("rose", "tea", 2, "2025-10-20", notesPlant2, plants.get(1));
            checkPlantInfo("biweekly", 1, "clay", "mulch", plants.get(1));
        } catch (IOException e) {
            fail("Couldn't read from the file.");
        }
    }
    
}

