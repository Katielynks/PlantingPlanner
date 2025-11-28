package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represents a list of all plants in the collection with filtering capabilites based on type
public class PlantCollection implements Writable {
    private ArrayList<Plant> collection;  

    //EFFECTS: Constructs an empty collection of plants
    public PlantCollection() {
        this.collection = new ArrayList<Plant>();
    }

    //MODIFIES: this
    //EFFECTS: adds a Plant to the list of all Plants
    public void addToCollection(Plant plant) {
        collection.add(plant);
        EventLog.getInstance().logEvent(new Event("Added plant " + plant.getName() + " to collection"));
    }

    //MODIFIES: this
    //EFFECTS: loads a Plant to the list of all Plants
    public void loadToCollection(Plant plant) {
        collection.add(plant);
    }

    //MODIFIES: this
    //EFFECTS: loads a Plant to the list of all Plants
    public void saveEditedPlantToCollection(Plant plant) {
        collection.add(plant);
        EventLog.getInstance().logEvent(new Event("Edited and saved changes of plant " + plant.getName() + " to collection"));
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

    //EFFECTS: returns a plant to view with same name as specified
    public Plant getPlantToView(String name) {
        EventLog.getInstance().logEvent(new Event("Viewed the plant " + name + " from the collection"));
        return getPlant(name);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("plants", plantsToJson());
        return json;
    }

    //EFFECTS: returns plants in this PlantCollection as a JSON array
    private JSONArray plantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Plant p : collection) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
