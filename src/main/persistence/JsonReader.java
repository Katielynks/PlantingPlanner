package persistence;

import model.PlantCollection;

import java.io.IOException;
import org.json.JSONObject;

//Represents a reader that reads PlantCollection from the JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        //stub
    }

    //EFFECTS: reads PlantCollection from the file and returns it
    //throws an IOException if an error occurs reading date from file
    public PlantCollection read() throws IOException {
        return null;  //stub
    }

    //EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        return "";  //stub
    }

    //EFFECTS: parses plantCollection from JSON object and returns it
    private PlantCollection parsePlantCollection(JSONObject jsonObject) {
        return null;  //stub
    }

    //MODIFIES: PlantCollection
    //EFFECTS: parses plants from JSON object and adds them to plantCollection
    private void addPlants(PlantCollection pc, JSONObject jsonObject) {
        //stub
    }

    //MODIFIES: PlantCollection
    //EFFECTS: parses plants from JSON object and adds it to PlantCollection
    private void addPlant(PlantCollection pc, JSONObject iJsonObject) {
        //stub
    }
    
}
