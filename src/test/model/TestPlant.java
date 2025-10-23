package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestPlant {
    Plant testPlant;
    LocalDate currentDate = LocalDate.now();
    String note1 = "Needs good air circulation to prevent mildew";
    String note2 = "Regular deadheading needed";
    String note3 = "Needs good air circulation to prevent mildew";
    
    @BeforeEach
    void runBefore() {
        testPlant = new Plant("tea rose", "Oregold", 2);
    }

    @Test
    void testConstructor() {
        assertEquals("tea rose", testPlant.getName());
        assertEquals("Oregold", testPlant.getSubtype());
        assertEquals(2, testPlant.getCategory());
        assertEquals(currentDate.toString(), testPlant.getLastWatered());
        assertEquals("none", testPlant.getCareInfo().getWateringFreq());
        assertEquals(0, testPlant.getCareInfo().getSunlight());
        assertEquals("none", testPlant.getCareInfo().getSoilType());
        assertEquals("none", testPlant.getCareInfo().getFertilizer());
        assertTrue(testPlant.getNotes().isEmpty());
        
    }

    @Test
    void testWaterPlant() {
        assertEquals(currentDate.toString(), testPlant.getLastWatered());
        testPlant.setLastWatered("2025-10-05");
        assertEquals("2025-10-05", testPlant.getLastWatered());
        testPlant.waterPlant();
        assertEquals(currentDate.toString(), testPlant.getLastWatered());
    }    
    
    @Test
    void testAddNote() {
        testPlant.addNote(note1);
        assertEquals(1, testPlant.getNotes().size());
    }

    @Test
    void testAddMultipleNotes() {
        testPlant.addNote(note1);
        assertEquals(1, testPlant.getNotes().size());
        testPlant.addNote(note2);
        assertEquals(2, testPlant.getNotes().size());
        testPlant.addNote(note3);
        assertEquals(3, testPlant.getNotes().size());
    }

    @Test
    void testRemoveNote() {
        testPlant.addNote(note1);
        testPlant.addNote(note2);
        assertEquals(2, testPlant.getNotes().size());
        testPlant.removeNote(2);
        assertEquals(1, testPlant.getNotes().size());
    }

    @Test
    void testchangeWateringFreqInfo() {
        assertEquals("none", testPlant.getCareInfo().getWateringFreq());
        testPlant.changeWateringFreqInfo("weekly");
        assertEquals("weekly", testPlant.getCareInfo().getWateringFreq());
    }

    @Test
    void testchangeSunlightInfo() {
        assertEquals(0, testPlant.getCareInfo().getSunlight());
        testPlant.changeSunlightInfo(1);
        assertEquals(1, testPlant.getCareInfo().getSunlight());
    }

    @Test
    void testchangeSoilTypeInfo() {
        assertEquals("none", testPlant.getCareInfo().getSoilType());
        testPlant.changeSoilTypeInfo("loam");
        assertEquals("loam", testPlant.getCareInfo().getSoilType());
    }

    @Test
    void testchangeFertilizerInfo() {
        assertEquals("none", testPlant.getCareInfo().getFertilizer());
        testPlant.changeFertilizerInfo("cedar mulch");
        assertEquals("cedar mulch", testPlant.getCareInfo().getFertilizer());
    }

    @Test
    void testToJson() {
        testPlant.setLastWatered("2025-10-19");
        testPlant.changeWateringFreqInfo("weekly");
        testPlant.changeSunlightInfo(2);
        testPlant.changeSoilTypeInfo("sand");
        testPlant.changeFertilizerInfo("none");
        testPlant.addNote(note1);
        testPlant.addNote(note2);
        testPlant.addNote(note3);

        JSONObject jsonTest = new JSONObject();
        jsonTest.put("name", "tea rose");
        jsonTest.put("subtype", "Oregold");
        jsonTest.put("category", 2);
        jsonTest.put("lastWatered", "2025-10-19");
        jsonTest.put("wateringFreq", "weekly");
        jsonTest.put("sunlight", 2);
        jsonTest.put("soilType", "sand");
        jsonTest.put("fertilizer", "none");
        JSONArray notes = new JSONArray();
        notes.put(note1);
        notes.put(note2);
        notes.put(note3);
        jsonTest.put("notes", notes);

        assertEquals(jsonTest.toString(), testPlant.toJson().toString());

    }

}
