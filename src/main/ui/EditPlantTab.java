package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Plant;
import model.PlantCollection;

import java.awt.*;
import java.util.ArrayList;

public class EditPlantTab extends Tabs {
    private static final int columnLabel = 420;
    private static final int columnField = 520;

    private JPanel componentPane;

    private JLabel title;
    private JLabel selectionHeading;
    private JLabel listHeading;   
    
    private PlantCollection collection;
    private String plantName;

    private DefaultTableModel tableModel;
    
    private JFormattedTextField nameField;
    private JFormattedTextField selectionField;
    private JFormattedTextField subTypeField;
    private JComboBox<String> categoryField;
    private JFormattedTextField wateringFreqField;
    private JComboBox<String> sunlightField;
    private JFormattedTextField soilTypeField;
    private JFormattedTextField fertilizerField;

    private JTextArea newNoteArea;
    private final String[] columnNames = {"Notes"};
    private JTable list;
    private JScrollPane newNoteScrollPane;

    private JButton enterButton;
    private JButton removeButton;
    private JButton addButton;    
    private JButton saveButton;

    // Edit Plant Tab of the Planting Planner application
    public EditPlantTab(PlantCollection collection) {
        setupBackgroundPanel("images/editPlantTabBackground.png");
        
        componentPane = new JPanel(null);
        componentPane.setOpaque(false);

        this.collection = collection;

        title = new JLabel("Edit a Plant");
        styleTitle(title, 265, 40);

        selectionHeading = new JLabel("Select a plant:");
        styleHeading(selectionHeading, 90, 135);

        createPlantFields();

        enterButton = new JButton("Enter");
        styleButton(enterButton, 254,185);

        enterButton.addActionListener(e -> {
            plantName = selectionField.getText();
            addAllFields(plantName);
        });

        listHeading = new JLabel("Edit Notes:");
        styleHeading(listHeading, 90, 270);

        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);
        list = new JTable(tableModel);
        list.setFont(new Font("Cambria", Font.BOLD, 14));
        JScrollPane listScrollPane = new JScrollPane(list);
        styleScrollPane(listScrollPane);
        listScrollPane.setBounds(90,330,240,100);

        JTableHeader header = list.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setBackground(new Color(131, 210, 230));

        removeButton = new JButton("Remove");
        styleButton(removeButton, 215,437);
        removeButton.addActionListener(e -> {
            collection.getPlant(plantName).removeNote(list.getSelectedRow() + 1);
            tableModel.setDataVector(notesToArray(collection.getPlant(plantName).getNotes()), columnNames);
        });

        newNoteArea = new JTextArea();
        newNoteScrollPane = styleNotes(newNoteArea, 90, 483);
        
        addButton = new JButton("Add");
        styleButton(addButton, 112,437);
        addButton.addActionListener(e -> {
            String note = newNoteArea.getText();
            collection.getPlant(plantName).addNote(note);
            tableModel.setDataVector(notesToArray(collection.getPlant(plantName).getNotes()), columnNames);
            newNoteArea.setText("");
        });

        saveButton = new JButton("Save");
        styleButton(saveButton, 498,506);

        saveButton.addActionListener(e -> {
            updateAllFields(plantName);
        });


        componentPane.add(title);
        componentPane.add(selectionHeading);
        componentPane.add(enterButton);
        componentPane.add(listHeading);
        componentPane.add(listScrollPane);
        componentPane.add(addButton);
        componentPane.add(removeButton);
        componentPane.add(saveButton);
        componentPane.add(newNoteScrollPane);
        panel.add(componentPane);

    }

    //MODIFIES: this
    //EFFECTS: Creates a new plant with new fields and replaces the old plant
    private void updateAllFields(String plantName) {
        String name = nameField.getText();
        String subtype = subTypeField.getText();
        String categoryString = (String) categoryField.getSelectedItem();
        int category = 1;
        if (categoryString.equals("Partial sun")) {
            category = 2;
        } else if (categoryString.equals("Shade")) {
            category = 3;
        }

        Plant plantUpdated = new Plant(name, subtype, category);

        plantUpdated.changeWateringFreqInfo(wateringFreqField.getText());

        String sunlightString = (String) sunlightField.getSelectedItem();
        int sunlight = 1;
        if (sunlightString.equals("Flowers")) {
            sunlight = 2;
        } else if (sunlightString.equals("Foods")) {
            sunlight = 3;
        }
        plantUpdated.changeSunlightInfo(sunlight);

        plantUpdated.changeSoilTypeInfo(soilTypeField.getText());

        plantUpdated.changeFertilizerInfo(fertilizerField.getText());

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Object value = tableModel.getValueAt(row, 0);
            if (value != null) {
                plantUpdated.addNote(value.toString());
            }
        }

        collection.removeFromCollection(plantName);

        collection.addToCollection(plantUpdated);

        collectionLoadedPopUp("images/dialoguePlantUpdated.png");
    }

    //MODIFIES: this
    //EFFECTS: Creates all plant fields with labels and corresponding fields
    private void createPlantFields() {
        selectionField = createLabeledField("", 265, 150, 93, 190);

        nameField = createLabeledField("Name:", columnLabel, 150, columnField, 150);

        subTypeField = createLabeledField("Subtype:", columnLabel, 190, columnField, 190);

        String[] categories = {"Structurals", "Flowers", "Foods"};
        categoryField = createLabeledComboBox("Category:", categories, columnLabel, 230, columnField, 230, 150);

        wateringFreqField = createLabeledField("Watering frequency:", columnLabel, 270, columnField, 300);

        String[] sunCategories = {"Full sun", "Partial sun", "Shade"};
        sunlightField = createLabeledComboBox("Sunlight:", sunCategories, columnLabel, 340, columnField, 340, 150);


        soilTypeField = createLabeledField("Soil Type:", columnLabel, 380, columnField, 380);

        fertilizerField = createLabeledField("Fertilizer:", columnLabel, 420, columnField, 420);
    }

    //MODIFIES: this
    //EFFECTS: adds all the fields according to the inputted plant name
    private void addAllFields(String plantName) {
        Plant plantToView = collection.getPlant(plantName);
        if (plantToView == null) {
            collectionLoadedPopUp("images/dialogueNotFound.png");
        } else {
            nameField.setText(plantToView.getName());
            subTypeField.setText(plantToView.getSubtype());
            categoryField.setSelectedItem(categoryToString(plantToView));
            wateringFreqField.setText(plantToView.getCareInfo().getWateringFreq());
            sunlightField.setSelectedItem(sunlightToString(plantToView));
            soilTypeField.setText(plantToView.getCareInfo().getSoilType());
            fertilizerField.setText(plantToView.getCareInfo().getFertilizer());
            
            tableModel.setDataVector(notesToArray(collection.getPlant(plantName).getNotes()), columnNames);
        }

    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the notes text area
    public JScrollPane styleNotes(JTextArea notes, int posX, int posY) {
        notes.setFont(new Font("Cambria", Font.BOLD, 18));
        notes.setBackground(new Color(230, 249, 255));
        notes.setLineWrap(true);

        JScrollPane notesScrollPane = new JScrollPane(notes);
        notesScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        notesScrollPane.setBounds(posX, posY, 240, 80); 

        return notesScrollPane;
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

    //EFFECTS: Changes appearance of the fields
    public void styleField(JFormattedTextField field, int posX, int posY) {
        field.setBounds(posX, posY,150,30);
        field.setFont(new Font("Cambria", Font.BOLD, 18));
        field.setBackground(new Color(230, 249, 255));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    //EFFECTS: Changes appearance of the combo boxes
    public void styleCategoryComboBox(JComboBox<String> categoryCBox, int posX, int posY, int width) {
        categoryCBox.setBounds(posX, posY, width,30);
        categoryCBox.setFont(new Font("Cambria", Font.BOLD, 18));
        categoryCBox.setBackground(new Color(230, 249, 255));
    }


    private void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setOpaque(true);
        scrollPane.setBackground(new Color(230, 249, 255));
        scrollPane.getViewport().setBackground(new Color(230, 249, 255));
    }

    private Object [][] notesToArray(ArrayList<String> notes) {
        Object[][] dataNotes = new Object[notes.size()][1];
        
        for (int i = 0; i < notes.size(); i++) {
            String n = notes.get(i);
            dataNotes[i][0] = n;
        }
        return dataNotes;
    }


    @Override 
    protected void styleButton(JButton button, int posX, int posY) { 
        super.styleButton(button, posX, posY);
        button.setBounds(posX, posY, 90, 40);
        button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    //EFFECTS: Changes appearance of the headings
    private void styleHeading(JLabel heading, int posX, int posY) {
        heading.setFont(new Font("Gabriola", Font.BOLD, 35));
        heading.setBounds(posX, posY, 400, 60);
        heading.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        heading.setForeground(new Color(0, 0, 0));
        heading.setOpaque(false);
    }

    //EFFECTS: Changes appearance of labels next to each field
    private void styleLabel(JLabel labelName, int posX, int posY) {
        labelName.setFont(new Font("Cambria", Font.BOLD, 20));
        labelName.setForeground(new Color(0, 0, 0));
        labelName.setOpaque(false);
        labelName.setBounds(posX, posY, 200, 30);
    }

    public JPanel getPanel() {
        return panel;
    }
    
}
