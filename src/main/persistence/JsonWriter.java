package persistence;

import java.io.FileNotFoundException;

import org.json.*;

import model.PlantCollection;

//Represents a writer that writes JSON representation of PlantCollection to file
public class JsonWriter {


    //EFFECTS: constructs writer to write to file destination
    public JsonWriter(String fileDesination) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: opens writer, throws FileNotFoundException if destination file cannot be opened
    public void open() throws FileNotFoundException {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of PlantCollection to file
    public void write(PlantCollection pc) {
        //stub        
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        //stub
    }
}
