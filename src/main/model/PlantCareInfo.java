package model;

//Represents all the care information about the plant 
public class PlantCareInfo {
    private String wateringFreq;    // How often plant should be watered
    private int sunlight;           // 0 = none, 1 = full sun, 2 = partial sun, 3 = shade
    private String soilType;        // Type of soil plant needs 
    private String fertilizer;      // Preferred fertilizer for the plant


    //REQUIRES: sunlight should be an integer from 0 - 3
    //EFFECTS: Constructs on object with all the information to take care of the plant
    public PlantCareInfo(String wateringFreq, int sunlight, String soilType, String fertilizer) {
        this.wateringFreq = wateringFreq;
        this.sunlight = sunlight;
        this.soilType = soilType;
        this.fertilizer = fertilizer;
    }

    public String getWateringFreq() {
        return wateringFreq;
    }

    public int getSunlight() {
        return sunlight;
    }

    public String getSoilType() {
        return soilType;
    }

    public String getfertilizer() {
        return fertilizer;
    }
    
    public void setWateringFreq(String newWateringFreq) {
        wateringFreq = newWateringFreq;
    }

    public void setSunlight(int newSunlight) {
        sunlight = newSunlight;
    }

    public void setSoilType(String newSoiltype) {
        soilType = newSoiltype;
    }

    public void setFertilizer(String newFertilizer) {
        fertilizer = newFertilizer;
    }

}