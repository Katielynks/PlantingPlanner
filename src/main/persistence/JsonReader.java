package persistence;

import model.Event;
import model.EventLog;
import model.Plant;
import model.PlantCollection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads PlantCollection from the JSON data stored in file
//Created this class referencing the example project JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads PlantCollection from the file and returns it
    //throws an IOException if an error occurs reading date from file
    public PlantCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded plant collection from file"));
        return parsePlantCollection(jsonObject);
    }

    //EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses plantCollection from JSON object and returns it
    private PlantCollection parsePlantCollection(JSONObject jsonObject) {
        PlantCollection pc = new PlantCollection();
        addPlants(pc, jsonObject);
        return pc;
    }

    //MODIFIES: PlantCollection
    //EFFECTS: parses plants from JSON object and adds them to plantCollection
    private void addPlants(PlantCollection pc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plants");
        for (Object json : jsonArray) {
            JSONObject nextPlant = (JSONObject) json;
            addPlant(pc, nextPlant);

        }
    }

    //MODIFIES: PlantCollection
    //EFFECTS: parses plants from JSON object and adds it to PlantCollection
    private void addPlant(PlantCollection pc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String subtype = jsonObject.getString("subtype");
        int category = jsonObject.getInt("category");
        String lastWatered = jsonObject.getString("lastWatered");
        String wateringFreq = jsonObject.getString("wateringFreq");
        int sunlight = jsonObject.getInt("sunlight");
        String soilType = jsonObject.getString("soilType");
        String fertilizer = jsonObject.getString("fertilizer");
        JSONArray jsonNotes = jsonObject.getJSONArray("notes");

        Plant plant = new Plant(name, subtype, category);
        pc.loadToCollection(plant);
        plant.setLastWatered(lastWatered);
        plant.changeWateringFreqInfo(wateringFreq);
        plant.changeSunlightInfo(sunlight);
        plant.changeSoilTypeInfo(soilType);
        plant.changeFertilizerInfo(fertilizer);

        for (int i = 0; i < jsonNotes.length(); i++) {
            plant.loadNote(jsonNotes.getString(i));
        }

    }
    
}
