package model;

import java.util.ArrayList;

//Represents a list of all plants in the collection with filtering capabilites based on type
public class PlantCollection {
    private ArrayList<Plant> collection;  

    //EFFECTS: Constructs an empty collection of plants
    public PlantCollection() {
        this.collection = new ArrayList<Plant>();
    }

    //MODIFIES: this
    //EFFECTS: adds a Plant to the list of all Plants
    public void addToCollection(Plant plant) {
        collection.add(plant);
    }

    //MODIFIES: this
    //EFFECTS: removes a Plant with specified name from this list of all Plants
    public void removeFromCollection(String name) {
        Plant toRemove = null;
        for (Plant p : collection) {
            if (name.equals(p.getName())) {
                toRemove = p;
                break;
            }
        }
        collection.remove(toRemove);
    }

    //EFFECTS: returns a plant with same name as specified
    public Plant getPlant(String name) {
        Plant foundplant = null;
        for (Plant p : collection) {
            if (p.getName().equals(name)) {
                foundplant = p;
                break;
            }
        }
        return foundplant;
    }

    //EFFECTS: returns the entire collection of plants
    public ArrayList<Plant> getCollection() {
        return collection;
    }

    //EFFECTS: returns the collection of plants with category Structural
    public ArrayList<Plant> filterStructurals() {
        ArrayList<Plant> structuralPlants = new ArrayList<>();
        for (Plant p : collection) {
            if (p.getCategory() == 1) {
                structuralPlants.add(p);
            }
        }
        return structuralPlants;
    }

    //EFFECTS: returns the collection of plants with category Flowers
    public ArrayList<Plant> filterFlowers() {
        ArrayList<Plant> flowerPlants = new ArrayList<>();
        for (Plant p : collection) {
            if (p.getCategory() == 2) {
                flowerPlants.add(p);
            }
        }
        return flowerPlants;
    }

    //EFFECTS: returns the collection of plants with category Foods
    public ArrayList<Plant> filterFood() {
        ArrayList<Plant> foodPlants = new ArrayList<>();
        for (Plant p : collection) {
            if (p.getCategory() == 3) {
                foodPlants.add(p);
            }
        }
        return foodPlants;
    }

}
