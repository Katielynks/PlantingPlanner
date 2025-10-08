package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
