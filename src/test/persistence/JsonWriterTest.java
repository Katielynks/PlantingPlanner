package persistence;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;
import model.PlantCollection;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyPlantCollection() {
        try {
            PlantCollection pc = new PlantCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlantCollection.json");
            writer.open();
            writer.write(pc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlantCollection.json");
            pc = reader.read();
            assertEquals(0, pc.getCollection().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    
    @Test
    @SuppressWarnings("methodlength")
    void testWriterGeneralPlantCollection() {
        try {
            PlantCollection pc = new PlantCollection();
            Plant plant1 = new Plant("cactus", "aloe", 1);
            ArrayList<String> notesPlant1 = new ArrayList<>();
            plant1.setLastWatered("2025-10-19");
            String note1 = "needs warm space";
            String note2 = "grows quickly";
            plant1.addNote(note1);
            plant1.addNote(note2);
            notesPlant1.add(note1);
            notesPlant1.add(note2);
            plant1.changeWateringFreqInfo("weekly");
            plant1.changeSunlightInfo(2);
            plant1.changeSoilTypeInfo("sand");
            plant1.changeFertilizerInfo("none");

            Plant plant2 = new Plant("rose", "tea", 2);
            plant2.setLastWatered("2025-10-20");
            ArrayList<String> notesPlant2 = new ArrayList<>();
            String note3 = "likes direct sun";
            String note4 = "needs ventilation";
            String note5 = "can get mold";
            plant2.addNote(note3);
            plant2.addNote(note4);
            plant2.addNote(note5);
            notesPlant2.add(note3);
            notesPlant2.add(note4);
            notesPlant2.add(note5);
            plant2.changeWateringFreqInfo("biweekly");
            plant2.changeSunlightInfo(1);
            plant2.changeSoilTypeInfo("clay");
            plant2.changeFertilizerInfo("mulch");

            pc.addToCollection(plant1);
            pc.addToCollection(plant2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlantCollection.json");
            writer.open();
            writer.write(pc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlantCollection.json");
            pc = reader.read();
            ArrayList<Plant> plants = pc.getCollection();
            assertEquals(2, plants.size());

            checkPlant("cactus", "aloe", 1, "2025-10-19", notesPlant1, plants.get(0));
            checkPlantInfo("weekly", 2, "sand", "none", plants.get(0));

            checkPlant("rose", "tea", 2, "2025-10-20", notesPlant2, plants.get(1));
            checkPlantInfo("biweekly", 1, "clay", "mulch", plants.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}
            