package model;

import java.util.ArrayList;

//Represents a plant having a name, species, type, lastWatered date, and corresponding notes
public class Plant {
    private String name;             // name of the plant
    private String subtype;          // subtype of the species
    private int category;            // number corresponding to plant category:
                                     // 1 = Structurals, 2 = Flowers, 3 = Foods    
    private String lastWatered;      // last time the plant was watered 
    private ArrayList<String> notes; // list of notes with info about the plant

    /*
    * REQUIRES: category must be an integer from 1 to 3
    * EFFECTS: Constructs a Plant with given name, subtype, and category;
    *          The lastWatered is set to the current date;
    *          There are no notes in the list for this plant.
    */
    public Plant(String name, String subtype, int category) {
        // stub
    }

    public String getName() {
        return ""; // stub
    }
    
    public String getSubtype() {
        return ""; // stub
    }

    public int getType() {
        return 0; // stub  
    }

    public ArrayList<String> getNotes() {
        return null; // stub  
    }   
    
    public String getLastWatered() {
        return ""; // stub  
    }

    //MODIFIES: this
    //EFFECTS: Changes the lastWatered date to the one specified
    public void setLastWatered(String date) {
    }

    //MODIFIES: this
    //EFFECTS: changes the watered date to the current date
    public void waterPlant() {
        // stub  
    }

    //MODIFIES: this
    //EFFECTS: adds a note about the plant
    public void addNote(String note) {
        // stub  
    }

    //REQUIRES: notes.size() >= 1 and noteNumber is between 1 and notes.size()
    //MODIFIES: this
    //EFFECTS: removes the note about the plant corresponding to its number
    public void removeNote(int noteNumber) {
        // stub  
    }

}
