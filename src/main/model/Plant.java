package model;

import java.time.LocalDate;
import java.util.ArrayList;

//Represents a plant having a name, species, type, lastWatered date, and corresponding notes
public class Plant {
    private String name;             // name of the plant
    private String subtype;          // subtype of the species
    private int category;            // number corresponding to plant category:
                                     // 1 = Structurals, 2 = Flowers, 3 = Foods    
    private String lastWatered;      // last date the plant was watered 
    private PlantCareInfo careInfo;  // all the information about how to care for the plant
    private ArrayList<String> notes; // list of notes with info about the plant

    LocalDate currentDate = LocalDate.now();

    /*
    * REQUIRES: category must be an integer from 1 to 3
    * EFFECTS: Constructs a Plant with given name, subtype, and category;
    *          The lastWatered is set to the current date;
    *          Initializes careInfo with a new PlantCareInfo object with default values
    *          There are no notes in the list for this plant.
    */
    public Plant(String name, String subtype, int category) {
        this.name = name;
        this.subtype = subtype;
        this.category = category;
        this.lastWatered = currentDate.toString();
        this.careInfo = new PlantCareInfo("none",0, "none", "none");
        this.notes = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }
    
    public String getSubtype() {
        return subtype; 
    }

    public int getCategory() {
        return category;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }   
    
    public String getLastWatered() {
        return lastWatered; 
    }

    public PlantCareInfo getCareInfo() {
        return careInfo;
    }

    //MODIFIES: this
    //EFFECTS: Changes the lastWatered date to the one specified
    public void setLastWatered(String date) {
        lastWatered = date;
    }

    //MODIFIES: this
    //EFFECTS: changes the watered date to the current date
    public void waterPlant() {
        lastWatered = currentDate.toString();
    }

    //MODIFIES: this
    //EFFECTS: adds a note about the plant
    public void addNote(String note) {
        notes.add(note);
    }

    //REQUIRES: notes.size() >= 1 and noteNumber is between 1 and notes.size()
    //MODIFIES: this
    //EFFECTS: removes the note about the plant corresponding to its number
    public void removeNote(int noteNumber) {
        notes.remove(noteNumber - 1);
    }

    //MODIFIES: this, careInfo
    //EFFECTS: changes the WateringFrequency to the new specified one
    public void changeWateringFreqInfo(String newFreq) {
        careInfo.setWateringFreq(newFreq);
    }

    //REQUIRES: newSunlight is an integer from 0 to 3
    //MODIFIES: this, careInfo
    //EFFECTS: changes the Sunlight need to the new specified one
    public void changeSunlightInfo(int newSunlight) {
        careInfo.setSunlight(newSunlight);
    }

    //MODIFIES: this, careInfo
    //EFFECTS: changes the Soil Type to the new specified one
    public void changeSoilTypeInfo(String newType) {
        careInfo.setSoilType(newType);
    }

    //MODIFIES: this, careInfo
    //EFFECTS: changes the Fertilizer to the new specified one
    public void changeFertilizerInfo(String newFertilizer) {
        careInfo.setFertilizer(newFertilizer);
    }
}