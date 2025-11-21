package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;
import model.PlantCollection;

import java.awt.*;

@ExcludeFromJacocoGeneratedReport
public class ViewPlantTab extends Tabs {
    private static final  int column1 = 125;
    private static final  int column2 = 420;
    private static final Color BkgndColor = new Color(230, 249, 255); 

    private JLabel title;
    private JPanel componentPane;

    private JLabel instructionsHeading;
    private JLabel notesHeading;

    private JFormattedTextField selectionField;
    private JFormattedTextField nameField;
    private JFormattedTextField subTypeField;
    private JFormattedTextField categoryField;
    private JFormattedTextField wateringFreqField;
    private JFormattedTextField sunlightField;
    private JFormattedTextField soilTypeField;
    private JFormattedTextField fertilizerField;
    private JTextArea notes;
    
    private JButton enterButton;

    private PlantCollection collection;
    private String plantName;


    //Constructs a new tab with title, headings, labels, and fields
    public ViewPlantTab(PlantCollection collection) {

        setupBackgroundPanel("images/viewPlantTabBackground.png");
        componentPane = new JPanel(null);
        componentPane.setOpaque(false);

        this.collection = collection;

        title = new JLabel("View a Plant");
        styleTitle(title, 268, 40);

        instructionsHeading = new JLabel("Insert name of plant:");
        styleLabel(instructionsHeading,160, 150);

        createPlantFields();

        createNotesSection(column1, 445);
        
        enterButton = new JButton("Enter");
        styleButton(enterButton, 470,145);

        enterButton.addActionListener(e -> {
            plantName = selectionField.getText();
            addAllFields(plantName);
        });

        componentPane.add(title);
        componentPane.add(instructionsHeading);
        componentPane.add(enterButton);
        panel.add(componentPane);
    }


    //MODIFIES: this
    //EFFECTS: Creates all plant fields with labels and corresponding fields
    private void createPlantFields() {
        selectionField = createLabeledField("", 365, 150, 365, 150);

        nameField = createLabeledField("Name:", column1, 260, 195, 260);

        subTypeField = createLabeledField("Subtype:", column1, 310, 215, 310);

        categoryField = createLabeledField("Category:", column1, 360, 222, 360);

        wateringFreqField = createLabeledField("Watering frequency:", column2, 260, 515, 295);

        sunlightField = createLabeledField("Sunlight:", column2, 350, 515, 350);

        soilTypeField = createLabeledField("Soil Type:", column2, 410, 515, 410);

        fertilizerField = createLabeledField("Fertilizer:", column2, 470, 515, 470);
    }

    //MODIFIES: this
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
    
    //MODIFIES: this
    //EFFECTS: adds all the fields according to the inputted plant name
    private void addAllFields(String plantName) {
        Plant plantToView = collection.getPlant(plantName);
        if (plantToView == null) {
            collectionLoadedPopUp("images/dialogueNotFound.png");
        } else {
            nameField.setText(plantToView.getName());
            subTypeField.setText(plantToView.getSubtype());
            categoryField.setText(categoryToString(plantToView));
            wateringFreqField.setText(plantToView.getCareInfo().getWateringFreq());
            sunlightField.setText(sunlightToString(plantToView));
            soilTypeField.setText(plantToView.getCareInfo().getSoilType());
            fertilizerField.setText(plantToView.getCareInfo().getFertilizer());
            String notesText = "";
            for (String n : plantToView.getNotes()) {
                notesText = notesText + n + "\n";
            }
            notes.setText(notesText);
        }

    }

    //MODIFIES: this
    //EFFECTS: Constructs a notes section with heading and scroll
    private void createNotesSection(int posX, int posY) {
        notes = new JTextArea(5, 20);
        notes.setLineWrap(true);
        notes.setEditable(true);

        JScrollPane notesScrollPane = styleNotes(notes, column1,445);

        notesHeading = new JLabel("Notes");
        styleLabel(notesHeading,200, 410);

        componentPane.add(notesHeading);
        componentPane.add(notesScrollPane);
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the headings
    public void styleHeading(JLabel heading, int posX, int posY) {
        heading.setFont(new Font("Gabriola", Font.BOLD, 35));
        heading.setBounds(posX, posY, 400, 60);
        heading.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        heading.setForeground(new Color(0, 0, 0));
        heading.setOpaque(false);
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of labels next to each field
    public void styleLabel(JLabel labelName, int posX, int posY) {
        labelName.setFont(new Font("Cambria", Font.BOLD, 20));
        labelName.setForeground(new Color(0, 0, 0));
        labelName.setOpaque(false);
        labelName.setBounds(posX, posY, 200, 30);
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the fields
    public void styleField(JFormattedTextField field, int posX, int posY) {
        field.setBounds(posX, posY,150,30);
        field.setFont(new Font("Cambria", Font.BOLD, 18));
        field.setBackground(BkgndColor);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    //MODIFIES: this
    //EFFECTS: Changes appearance of the combo boxes
    public void styleCategoryComboBox(JComboBox<String> categoryCBox, int posX, int posY, int width) {
        categoryCBox.setBounds(posX, posY, width,30);
        categoryCBox.setFont(new Font("Cambria", Font.BOLD, 18));
        categoryCBox.setBackground(BkgndColor);
    }

    //MODIFIES: this
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

    public JPanel getPanel() {
        return panel;
    }
    
    
}
