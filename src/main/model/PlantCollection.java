package model;

import java.util.ArrayList;
import java.time.LocalDate;

//Represents a list of all plants in the collection with filtering capabilites based on type
public class PlantCollection {
    public ArrayList<Plant> Collection;  


    //EFFECTS: Constructs an empty collection of plants
    public PlantCollection() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds a Plant to the list of all Plants
    public void addToCollection(Plant plant) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: removes a Plant with specified name from this list of all Plants
    public void removeFromCollection(String name) {
        //stub
    }

    //EFFECTS: returns a plant with same name as specified
    public Plant getPlant(String name) {
        return null; //stub
    }

    //EFFECTS: returns the entire collection of plants
    public ArrayList<Plant> getCollection() {
        return null; //stub
    }

    //EFFECTS: returns the collection of plants with type Structural
    public ArrayList<Plant> filterStructurals() {
        return null; //stub
    }

    //EFFECTS: returns the collection of plants with type Flowers
    public ArrayList<Plant> filterFlowers() {
        return null; //stub
    }

    //EFFECTS: returns the collection of plants with type Foods
    public ArrayList<Plant> filterFood() {
        return null; //stub
    }

}
