package persistence;

import java.io.*;

import model.PlantCollection;
import org.json.JSONObject;

//Represents a writer that writes JSON representation of PlantCollection to file
//Created this class referencing the example project JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private String destination;
    private PrintWriter writer;

    //EFFECTS: constructs writer to write to file destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer, throws FileNotFoundException if destination file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of PlantCollection to file
    public void write(PlantCollection pc) {
        JSONObject json = pc.toJson();
        saveToFile(json.toString(TAB));      
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
