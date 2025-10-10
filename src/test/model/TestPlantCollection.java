package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPlantCollection {
    public PlantCollection testCollection1;
    public PlantCollection testCollection2;
    public Plant plant1;
    public Plant plant2;
    public Plant plant3;
    public Plant plant4;
    public Plant plant5;
    public Plant plant6;

    @BeforeEach
    void runBefore() {
        testCollection1 = new PlantCollection();
        testCollection2 = new PlantCollection();

        plant1 = new Plant("palm tree", "dwarf palmetto", 1);        
        plant2 = new Plant("tea rose", "oregold", 2);
        plant3 = new Plant("orchid", "moth", 2);
        plant4 = new Plant("blueberry bush", "bluecrop", 3);
        plant5 = new Plant("raspberry bush", "summer prelude", 3);
        plant6 = new Plant("cabbage", "red cabbage", 3);

        testCollection2.addToCollection(plant1);
        testCollection2.addToCollection(plant2);
        testCollection2.addToCollection(plant3);
        testCollection2.addToCollection(plant4);
        testCollection2.addToCollection(plant5);
        testCollection2.addToCollection(plant6);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCollection1.getCollection().size());       
    }

    @Test 
    void testAddToCollectionOnce() {
        testCollection1.addToCollection(plant1);  
        assertEquals(1, testCollection1.getCollection().size());
        assertEquals(plant1, testCollection1.getPlant("palm tree"));
    }

    @Test
    void testAddToCollectionMultipleTimes() {
        assertEquals(6, testCollection2.getCollection().size());
        assertEquals(plant1, testCollection2.getPlant("palm tree"));
        assertEquals(plant3, testCollection2.getPlant("orchid"));
        assertEquals(plant6, testCollection2.getPlant("cabbage"));
        assertEquals(null, testCollection2.getPlant("none"));
    }

    @Test
    void testRemoveFromCollection() {
        assertEquals(6, testCollection2.getCollection().size());
        testCollection2.removeFromCollection("tea rose");
        assertEquals(5, testCollection2.getCollection().size());
        testCollection2.removeFromCollection("none");
        assertEquals(5, testCollection2.getCollection().size());
    }
    
    @Test
    void testFilterStructurals() {
        assertEquals(1, testCollection2.filterStructurals().size());
    }

    @Test
    void testFilterFlowers() {
        assertEquals(2, testCollection2.filterFlowers().size());
    }

    @Test
    void testFilterFood() {
        assertEquals(3, testCollection2.filterFood().size());
    }
}
