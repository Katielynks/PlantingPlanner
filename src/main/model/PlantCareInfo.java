package model;

//Represents all the care information about the plant 
public class PlantCareInfo {
    private String wateringFreq;    // How often plant should be watered
    private int sunlight;           // 0 = none, 1 = full sun, 2 = partial sun, 3 = shade
    private String soilType;        // Type of soil plant needs 
    private String fertilizer;      // Preferred fertilizer for the plant


    //REQUIRES: sunlight should be an integer from 0 - 3
    //EFFECTS: Constructs on object with all the information to take care of the plant
    public PlantCareInfo(String wateringFreq, int sunlight, String soiltype, String fertilizer) {
        //stub
    }

    public String getWateringFreq() {
        return ""; //stub
    }

    public int getSunlight() {
        return 0; //stub
    }

    public String getSoilType() {
        return ""; //stub
    }

    public String getfertilizer() {
        return ""; //stub
    }
    
    public void setWateringFreq(String frequency) {
        //stub
    }

    public void setSunlight(int sunlight) {
        //stub
    }

    public void setSoilType(String type) {
        //stub
    }

    public void setFertilizer(String fertilizer) {
        //stub
    }

}