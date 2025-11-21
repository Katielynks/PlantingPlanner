package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;
import model.PlantCollection;

import java.awt.*;

// New Plant Tab of the Planting Planner application
@ExcludeFromJacocoGeneratedReport
public class NewPlantTab extends Tabs {
    private static final int column1 = 125;
    private static final int column2 = 420;
    private static final Color BkgndColor = new Color(230, 249, 255); 
    
    private JLabel title;
    private JPanel componentPane;

    private PlantCollection collection;

    private JFormattedTextField nameField;
    private JFormattedTextField subTypeField;
    private JComboBox<String> categoryField;

    private JLabel careInformationHeading;
    private JFormattedTextField wateringFreqField;
    private JComboBox<String> sunlightField;
    private JFormattedTextField soilTypeField;
    private JFormattedTextField fertilizerField;

    private JLabel notesHeading;   
    private JTextArea notes;
    
    private JButton saveButton;


    //Constructs a new tab with title, headings, labels, and fields
    public NewPlantTab(PlantCollection collection) {
        setupBackgroundPanel("images/newPlantTabBackground.png");
        componentPane = new JPanel(null);
        componentPane.setOpaque(false);

        this.collection = collection;

        title = new JLabel("Create New Plant");
        styleTitle(title, 215, 40);

        careInformationHeading = new JLabel("Care Information");
        styleHeading(careInformationHeading,430, 170);

        createPlantFields();

        createNotesSection(column1, 445);
        
        saveButton = new JButton("Save to Collection");
        styleButton(saveButton, 440,500);

        saveButton.addActionListener(e -> {
            createPlant();
            clearAllFields();
        });


        componentPane.add(title);
        componentPane.add(careInformationHeading);
        componentPane.add(saveButton);
        panel.add(componentPane);
    }


    //MODIFIES: this
    //EFFECTS: Creates all plant fields with labels and corresponding fields
    private void createPlantFields() {
        nameField = createLabeledField("Name:", column1, 190, 195, 190);

        subTypeField = createLabeledField("Subtype:", column1, 255, 215, 255);

        String[] categories = {"Structurals", "Flowers", "Foods"};
        categoryField = createLabeledComboBox("Category:", categories, column1, 320, 222, 320, 141);

        wateringFreqField = createLabeledField("Watering frequency:", column2, 240, 515, 270);

        String[] sunCategories = {"Full sun", "Partial sun", "Shade"};
        sunlightField = createLabeledComboBox("Sunlight:", sunCategories, column2, 320, 515, 320, 150);

        soilTypeField = createLabeledField("Soil Type:", column2, 370, 515, 370);

        fertilizerField = createLabeledField("Fertilizer:", column2, 420, 515, 420);

    }

    //EFFECTS: Constructs a text field with label and field
    private JFormattedTextField createLabeledField(String labelText, int labelX, int labelY, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        styleLabel(label, labelX, labelY);
        JFormattedTextField field = new JFormattedTextField();
        styleField(field, fieldX, fieldY);

        componentPane.add(label);
        componentPane.add(field);
        return field;
    }

    //EFFECTS: Constructs a combo box
    private JComboBox<String> createLabeledComboBox(String labelText, 
                                                    String[] options, 
                                                    int labelX, 
                                                    int labelY, 
                                                    int comboX, 
                                                    int comboY, 
                                                    int comboWidth) {
        JLabel label = new JLabel(labelText);
        styleLabel(label, labelX, labelY);
        JComboBox<String> comboBox = new JComboBox<>(options);
        styleCategoryComboBox(comboBox, comboX, comboY, comboWidth);

        componentPane.add(label);
        componentPane.add(comboBox);

        return comboBox;

    }

    //EFFECTS: Constructs a notes section with heading and scroll
    private void createNotesSection(int posX, int posY) {
        notes = new JTextArea(5, 20);
        notes.setLineWrap(true);
        notes.setEditable(true);

        JScrollPane notesScrollPane = styleNotes(notes, column1,445);

        notesHeading = new JLabel("Notes");
        styleHeading(notesHeading,200, 385);

        componentPane.add(notesHeading);
        componentPane.add(notesScrollPane);
    }

    //EFFECTS: Changes appearance of the headings
    public void styleHeading(JLabel heading, int posX, int posY) {
        heading.setFont(new Font("Gabriola", Font.BOLD, 35));
        heading.setBounds(posX, posY, 400, 60);
        heading.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        heading.setForeground(new Color(0, 0, 0));
        heading.setOpaque(false);
    }

    //EFFECTS: Changes appearance of labels next to each field
    public void styleLabel(JLabel labelName, int posX, int posY) {
        labelName.setFont(new Font("Cambria", Font.BOLD, 20));
        labelName.setForeground(new Color(0, 0, 0));
        labelName.setOpaque(false);
        labelName.setBounds(posX, posY, 200, 30);
    }

    //EFFECTS: Changes appearance of the fields
    public void styleField(JFormattedTextField field, int posX, int posY) {
        field.setBounds(posX, posY,150,30);
        field.setFont(new Font("Cambria", Font.BOLD, 18));
        field.setBackground(BkgndColor);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    } 

    //EFFECTS: Changes appearance of the combo boxes
    public void styleCategoryComboBox(JComboBox<String> categoryCBox, int posX, int posY, int width) {
        categoryCBox.setBounds(posX, posY, width,30);
        categoryCBox.setFont(new Font("Cambria", Font.BOLD, 18));
        categoryCBox.setBackground(BkgndColor);
    }

    //EFFECTS: Changes appearance of the notes text area
    public JScrollPane styleNotes(JTextArea notes, int posX, int posY) {
        notes.setFont(new Font("Cambria", Font.BOLD, 18));
        notes.setBackground(BkgndColor);
        notes.setLineWrap(true);

        JScrollPane notesScrollPane = new JScrollPane(notes);
        notesScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        notesScrollPane.setBounds(posX, posY, 240, 80); 

        return notesScrollPane;
    }

    //MODIFIES: this
    //EFFECTS: Creates a new plant with the user input in the fields
    private void createPlant() {
        String name = nameField.getText();
        String subtype = subTypeField.getText();

        int category = categoryToInt((String) categoryField.getSelectedItem());

        Plant newPlant = new Plant(name, subtype, category);

        newPlant.changeWateringFreqInfo(wateringFreqField.getText());

        int sunlight = sunlightToInt((String) sunlightField.getSelectedItem());

        newPlant.changeSunlightInfo(sunlight);

        newPlant.changeSoilTypeInfo(soilTypeField.getText());

        newPlant.changeFertilizerInfo(fertilizerField.getText());

        newPlant.addNote(notes.getText());

        collection.addToCollection(newPlant);

        collectionLoadedPopUp("images/dialoguePlantAdded.png");

    }

    //EFFECTS: converts category to corresponding integer
    private int categoryToInt(String categoryString) {
        if (categoryString.equals("Flowers")) {
            return 2;
        } else if (categoryString.equals("Foods")) {
            return 3;
        }
        return 1;
    }

    //EFFECTS: converts sunlight to corresponding integer
    private int sunlightToInt(String sunlightString) {
        if (sunlightString.equals("Partial sun")) {
            return 2;
        } else if (sunlightString.equals("Shade")) {
            return 3;
        }
        return 1;
    }

    //EFFECTS: clears the user input in all fields
    private void clearAllFields() {
        nameField.setText("");
        subTypeField.setText("");
        categoryField.setSelectedIndex(0);
        wateringFreqField.setText("");
        sunlightField.setSelectedIndex(0);
        soilTypeField.setText("");
        fertilizerField.setText("");
        notes.setText("");
    }

    public JPanel getPanel() {
        return panel;
    }
    
}
