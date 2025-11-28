package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;
import model.PlantCollection;

import java.awt.*;
import java.util.ArrayList;

//Edit tab of the Planting Planner application
@ExcludeFromJacocoGeneratedReport
public class EditPlantTab extends Tabs {
    private static final int columnLabel = 420;
    private static final int columnField = 520;
    private static final Color scrollBkgndColor = new Color(230, 249, 255); 

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

        createHeaders();

        createPlantFields();

        createButtons();

        JScrollPane listScrollPane = createNotesTable();

        styleTableHeader(list);

        newNoteArea = new JTextArea();
        newNoteScrollPane = styleNotes(newNoteArea, 90, 483);

        componentPane.add(title);
        componentPane.add(selectionHeading);
        componentPane.add(listHeading);
        componentPane.add(listScrollPane);
        componentPane.add(newNoteScrollPane);
        panel.add(componentPane);

    }

    //MODIFIES: this
    //EFFECTS: Creates the two headers for this tab
    private void createHeaders() {
        selectionHeading = new JLabel("Select a plant:");
        styleHeading(selectionHeading, 90, 135);

        listHeading = new JLabel("Edit Notes:");
        styleHeading(listHeading, 90, 270);
    }



    //MODIFIES: this
    //EFFECTS: Creates a table with an empty model and returns the scroll pane
    private JScrollPane createNotesTable() {
        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);
        list = new JTable(tableModel);
        list.setFont(new Font("Cambria", Font.BOLD, 14));
        JScrollPane listScrollPane = new JScrollPane(list);
        styleScrollPane(listScrollPane);
        listScrollPane.setBounds(90,330,240,100);

        return listScrollPane;
    }

    //MODIFIES: this
    //EFFECTS: Creates the four buttons that are used in this tab
    public void createButtons() {

        enterButton = new JButton("Enter");
        styleButton(enterButton, 254,185);

        enterButton.addActionListener(e -> enterButtonAction());

        componentPane.add(enterButton);

        removeButton = new JButton("Remove");
        styleButton(removeButton, 215,437);

        removeButton.addActionListener(e -> removeButtonAction());

        componentPane.add(removeButton);

        addButton = new JButton("Add");
        styleButton(addButton, 112,437);
        addButton.addActionListener(e -> addButtonAction());

        componentPane.add(addButton);

        saveButton = new JButton("Save");
        styleButton(saveButton, 500,508);

        saveButton.addActionListener(e -> saveButtonAction());

        componentPane.add(saveButton);

    }

    //MODIFIES: this 
    //EFFECTS: Designs the action for the addButton
    public void addButtonAction() {
        String note = newNoteArea.getText();
        collection.getPlant(plantName).addNote(note);
        tableModel.setDataVector(notesToArray(collection.getPlant(plantName).getNotes()), columnNames);
        newNoteArea.setText("");
    }

    //REQUIRES: list.size() >= 0
    //MODIFIES: this 
    //EFFECTS: Designs the action for the removeButton
    public void removeButtonAction() {
        collection.getPlant(plantName).removeNote(list.getSelectedRow() + 1);
        tableModel.setDataVector(notesToArray(collection.getPlant(plantName).getNotes()), columnNames);
    }

    //MODIFIES: this
    //EFFECTS: Designs the action for the enterButton
    public void enterButtonAction() {
        plantName = selectionField.getText();
        addAllFields(plantName);
    }

    //MODIFIES: this 
    //EFFECTS: Designs the action for the saveButton
    public void saveButtonAction() {
        updateAllFields(plantName);
    }

    //MODIFIES: this
    //EFFECTS: Creates a new plant with new fields and replaces the old plant
    private void updateAllFields(String plantName) {
        String name = nameField.getText();
        String subtype = subTypeField.getText();

        int category = categoryToInt((String) categoryField.getSelectedItem());

        Plant plantUpdated = new Plant(name, subtype, category);

        plantUpdated.changeWateringFreqInfo(wateringFreqField.getText());

        int sunlight = sunlightToInt((String) sunlightField.getSelectedItem());

        plantUpdated.changeSunlightInfo(sunlight);

        plantUpdated.changeSoilTypeInfo(soilTypeField.getText());

        plantUpdated.changeFertilizerInfo(fertilizerField.getText());

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Object value = tableModel.getValueAt(row, 0);
            if (value != null) {
                plantUpdated.loadNote(value.toString());
            }
        }

        collection.removeFromCollection(plantName);

        collection.saveEditedPlantToCollection(plantUpdated);

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
        notes.setBackground(scrollBkgndColor);
        notes.setLineWrap(true);

        JScrollPane notesScrollPane = new JScrollPane(notes);
        notesScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        notesScrollPane.setBounds(posX, posY, 240, 80); 

        return notesScrollPane;
    }

    //MODIFIES: this
    //EFFECTS: Constructs a combo box and adds it to the panel
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

    //MODIFIES: this
    //EFFECTS: Constructs a text field with label and field and adds it to the panel
    private JFormattedTextField createLabeledField(String labelText, int labelX, int labelY, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        styleLabel(label, labelX, labelY);
        JFormattedTextField field = new JFormattedTextField();
        styleField(field, fieldX, fieldY);

        componentPane.add(label);
        componentPane.add(field);
        return field;
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the fields
    public void styleField(JFormattedTextField field, int posX, int posY) {
        field.setBounds(posX, posY,150,30);
        field.setFont(new Font("Cambria", Font.BOLD, 18));
        field.setBackground(scrollBkgndColor);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the combo boxes
    public void styleCategoryComboBox(JComboBox<String> categoryCBox, int posX, int posY, int width) {
        categoryCBox.setBounds(posX, posY, width,30);
        categoryCBox.setFont(new Font("Cambria", Font.BOLD, 18));
        categoryCBox.setBackground(scrollBkgndColor);
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the scroll pane
    private void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setOpaque(true);
        scrollPane.setBackground(new Color(230, 249, 255));
        scrollPane.getViewport().setBackground(scrollBkgndColor);
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

    //MODIFIES: this
    //EFFECTS: Customizes the appearance of the table header
    private void styleTableHeader(JTable table) {
        JTableHeader header = list.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setBackground(new Color(131, 210, 230));
    }

    public JPanel getPanel() {
        return panel;
    }
    
}
