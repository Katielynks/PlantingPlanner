package ui;

import model.Plant;
import model.PlantCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Planting Planner application
@ExcludeFromJacocoGeneratedReport
public class PlantApp {
    private static final String JSON_STORE = "./data/plantCollection.json";
    private PlantCollection collection;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the planting planner application
    public PlantApp() throws FileNotFoundException{
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPlantApp();
    }


    //MODIFIES: this
    //EFFECTS: displays menu and processes user input
    private void runPlantApp() {   //Note: Created this method referencing the example project TellerApp
        boolean continueProgram = true;
        String command = null;

        init();

        System.out.println("Welcome to Planting Planner!");

        while (continueProgram) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continueProgram = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Goodbye, thank you for using Planting Planner!");

    }


    //MODIFIES: this
    //EFFECTS: processes the user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addNewPlant();
        } else if (command.equals("r")) {
            removePlant();
        } else if (command.equals("v")) {
            viewPlant();
        } else if (command.equals("l")) {
            viewplantCollection();
        } else if (command.equals("e")) {
            editplantInformation();
        } else if (command.equals("s")) {
            saveCollection();
        } else if (command.equals("e")) {
            loadCollection();
        } else {
            System.out.println("Sorry, your selection is not valid.");
        }
    }


    //MODIFIES: this
    //EFFECTS: initializes collection and creates new scanner
    private void init() {
        collection = new PlantCollection();
        input = new Scanner(System.in);
    }


    //EFFECTS: displays menu of various options to users
    private void displayMenu() {
        System.out.println("-------------------------------------------");
        System.out.println("Please select from the following options:");
        System.out.println("a - Add a new plant");
        System.out.println("r - Remove a plant");
        System.out.println("v - View a plant");
        System.out.println("l - View a list of plants");
        System.out.println("e - Edit plant information");
        System.out.println("s - Save collection to file");
        System.out.println("d - Load collection from file");
        System.out.println("q - quit");
        System.out.println("-------------------------------------------");
    }


    //MODIFIES: this
    //EFFECTS: adds a new plant to the collection 
    private void addNewPlant() {
        System.out.println("What is the name of your plant?");
        String plantName = input.next();

        System.out.println("What is the subtype of your plant?");
        String plantSubtype = input.next();

        System.out.println("Choose a category your plant belongs to (1 = Structurals, 2 = Flowers, 3 = Foods):");
        int plantCategory = input.nextInt();

        Plant newPlant = new Plant(plantName, plantSubtype, plantCategory);

        addMoreInfo(newPlant);

        collection.addToCollection(newPlant);
        System.out.println("Your plant " + plantName + " has been added to the collection.");
    }


    //MODIFIES: this
    //EFFECTS: adds more information about plant
    private void addMoreInfo(Plant newPlant) {
        System.out.println("Would you like to add more information about this plant?");
        System.out.println("c - Add care information");
        System.out.println("n - Add notes");
        System.out.println("a - Nothing (continue)");

        boolean done = false;
        while (!done) {
            String userSelection = input.next();

            switch (userSelection) {
                case "c":
                    addCareInfo(newPlant);
                    done = true;
                    break;
                case "n":
                    addNotes(newPlant);
                    done = true;
                    break;
                case "a":
                    done = true;
                    break;
                default:
                    System.out.println("Sorry, your selection is not valid.");
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: updated the care info corresponding to the plant
    private void addCareInfo(Plant newPlant) {
        System.out.println("What is the frequency of watering needed?");
        String answer1 = input.next();
        newPlant.changeWateringFreqInfo(answer1);

        System.out.println("Select how much sun this plant needs:");
        System.out.println("0 = none, 1 = full sun, 2 = partial sun, 3 = shade");
        while (true) {
            int answer2 = input.nextInt();
            if (answer2 >= 0 && answer2 <= 3) {
                newPlant.changeSunlightInfo(answer2);
                break;
            } else {
                System.out.println("Sorry, your selection is not valid. Try again.");
            }

        }

        System.out.println("What type of soil is needed?");
        String answer3 = input.next();
        newPlant.changeSoilTypeInfo(answer3);

        System.out.println("What type of fertilizer is needed?");
        String answer4 = input.next();
        newPlant.changeFertilizerInfo(answer4);

        System.out.println("All plant care information for " + newPlant.getName() + " has been updated.");    
    }


    //MODIFIES: this
    //EFFECTS: adds notes to the plant 
    private void addNotes(Plant newPlant) {
        Boolean keepAddingNotes = true;

        input.nextLine();

        while (keepAddingNotes) {
            System.out.println("Please provide the note you want to add:");
            String note = input.nextLine();
            newPlant.addNote(note);
            System.out.println("Would you like to add another note? y = yes, n = no");
            String userContinue = input.nextLine();
            if (userContinue.equals("n")) {
                keepAddingNotes = false;
            }
        }

    }


    //MODIFIES: this
    //EFFECTS: removes a plant from the collection 
    private void removePlant() {
        System.out.println("Please type the name of the plant you want to remove:");
        String plantToRemove = input.next();
        collection.removeFromCollection(plantToRemove);
        System.out.println(plantToRemove + " has been removed!");
    }


    //EFFECTS: provides a view for specified plant from the collection 
    private void viewPlant() {
        System.out.println("Please provide the name of the plant you want to view:");
        String plantName = input.next();
        Plant plantToView = collection.getPlant(plantName);
        if (plantToView == null) {
            System.out.println("No plant named " + plantName + " was found.");
        } else {
            System.out.println("Here is the information about " + plantToView.getName());
            System.out.println("Name: " + plantToView.getName());
            System.out.println("Subtype: " + plantToView.getSubtype());
            System.out.println("Category: " + categoryToString(plantToView));
            System.out.println("Last watered date: " + plantToView.getLastWatered());

            System.out.println("Watering Frequency: " + plantToView.getCareInfo().getWateringFreq());
            System.out.println("Sunlight : " + sunlightToString(plantToView));
            System.out.println("Soil Type: " + plantToView.getCareInfo().getSoilType());
            System.out.println("Fertilizer: " + plantToView.getCareInfo().getFertilizer());
            System.out.println("Notes: ");
            for (String n : plantToView.getNotes()) {
                System.out.println(n);
            }

        }
    }


    private String categoryToString(Plant p) {
        String category;
        switch (p.getCategory()) {
            case 1:
                category = "structurals";
                break;
            case 2:
                category = "flowers";
                break;
            case 3:
                category = "foods";
                break;
            default:
                category = "none";
        }
        return category;
    }


    private String sunlightToString(Plant p) {
        String sunlight;
        switch (p.getCareInfo().getSunlight()) {
            case 1:
                sunlight = "full sun";
                break;
            case 2:
                sunlight = "partial sun";
                break;
            case 3:
                sunlight = "shade";
                break;
            default:
                sunlight = "none";
        }
        return sunlight;
    }


    //EFFECTS: provides a list of the names in the specified collection of plants
    private void viewplantCollection() {
        System.out.println("Please select one of the viewing options below:");
        System.out.println("a - view all plants");
        System.out.println("s - view only structurals");
        System.out.println("w - view only flowers");
        System.out.println("f - view only foods");

        String userSelection =  input.next();

        System.out.println("Here is the list: ");
        
        if (userSelection.equals("a")) {
            printPlants(collection.getCollection());
        } else if (userSelection.equals("s")) {
            printPlants(collection.filterStructurals());

        } else if (userSelection.equals("w")) {
            printPlants(collection.filterFlowers());

        } else if (userSelection.equals("f")) {
            printPlants(collection.filterFood());

        }
    }


    //EFFECTS: prints the names of all the plants in the list
    private void printPlants(ArrayList<Plant> collection) {
        if (collection.isEmpty()) {
            System.out.println("There are no plants in the collection.");
            return;
        }

        for (Plant p : collection) {
            System.out.println(p.getName());
        }

    }


    //MODIFIES: this
    //EFFECTS: edits information about the plant
    private void editplantInformation() {
        System.out.println("Type the name of the plant you want to edit for:");
        String plantName = input.next();
        Plant plantToEdit = collection.getPlant(plantName);
        if (plantToEdit == null) {
            System.out.println("No plant named " + plantName + " was found.");
        } else {
            System.out.println("Select which of the following you want to do:");
            System.out.println("a - add note");
            System.out.println("r - remove note");

            while (true) {
                String userSelection = input.next();
                if (userSelection.equals("a")) {
                    addNotes(plantToEdit);
                    break;
                } else if (userSelection.equals("r")) {
                    removePlantNote(plantToEdit);
                    break;
                } else {
                    System.out.println("Sorry, your selection is not valid. Try again.");
                }

            }

        }

    }

    //EFFECTS: saves the collection to file
    private void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();  
            System.out.println("Your collection has been saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    //EFFECTS: loads the collection from file
    private void loadCollection() {
        try {
            collection = jsonReader.read();
            System.out.println("Your collection was loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a note from the plant
    private void removePlantNote(Plant plantToEdit) {
        System.out.println("Provide the note number you want to remove:");
        System.out.println("Notes: ");
        for (String n : plantToEdit.getNotes()) {
            System.out.println(n);
        }
        int noteNumber = input.nextInt();
        plantToEdit.removeNote(noteNumber);
        System.out.println("Note number " + noteNumber + " has been removed!");
    }

}
