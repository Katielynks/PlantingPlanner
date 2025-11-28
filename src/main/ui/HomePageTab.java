package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;
import model.PlantCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Home Page Tab of the Planting Planner application
@ExcludeFromJacocoGeneratedReport
public class HomePageTab extends Tabs {
    private JPanel panel;
    private JLabel title;
    private JPanel componentPane;
    private Image backgroundImage; 
    private JButton b1;
    private JButton b2;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private static final String JSON_STORE = "./data/plantCollection.json";
    private PlantCollection collection;

    // Constructs a new Home page tab
    public HomePageTab(PlantCollection collection) {
        backgroundImage = new ImageIcon("images/homePageBackground.png").getImage();
        
        this.collection = collection;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }            
        };
        
        title = new JLabel("Planting Planner", SwingConstants.CENTER);
        styleTitle(title);

        componentPane = new JPanel();
        styleComponentPane();
        componentPane.add(title);
        addSpacing(16, componentPane);

        createButtons();
        
        panel.add(componentPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: Creates the buttons, adjusting appearance, and providing actions
    private void createButtons() {
        b1 = new JButton("Load Collection");
        styleButton(b1);
        button1Action(b1);

        b2 = new JButton("Save Collection");
        styleButton(b2);
        button2Action(b2);

        componentPane.add(b1);
        addSpacing(19, componentPane);
        componentPane.add(b2);
    }

    //MODIFIES: this
    //EFFECTS: Sets the layout, border, and opacity of the component page
    private void styleComponentPane() {
        componentPane.setLayout(new BoxLayout(componentPane, BoxLayout.PAGE_AXIS));
        componentPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        componentPane.setOpaque(false);
    }

    //MODIFIES: this
    //EFFECTS: loads the collection from file
    private void button1Action(JButton b1) {
        jsonReader = new JsonReader(JSON_STORE);

        b1.addActionListener(e -> {
            try {
                PlantCollection loaded = jsonReader.read();
                for (Plant p : loaded.getCollection()) {
                    collection.loadToCollection(p);
                }
                collectionLoadedPopUp("images/dialogue1.png");
            } catch (IOException n) {
                collectionLoadedPopUp("images/dialogue2.png");
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: saves the collection to file
    private void button2Action(JButton b2) {
        jsonWriter = new JsonWriter(JSON_STORE);

        b2.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(collection);
                jsonWriter.close();  
                collectionLoadedPopUp("images/dialogueFileSaved.png");
            } catch (FileNotFoundException n) {
                collectionLoadedPopUp("images/dialogueFileNotFound.png");
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: Adds spacing between various components on the panel
    private void addSpacing(int height, JPanel componentPane) {
        componentPane.add(Box.createRigidArea(new Dimension(0, height)));
    }

    //MODIFIES: this
    //EFFECTS: Customizes the title
    private void styleTitle(JLabel title) {
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Gabriola", Font.BOLD, 70));
        title.setBorder(BorderFactory.createEmptyBorder(125, 0, 0, 0));
        title.setForeground(new Color(0, 0, 0));
        title.setOpaque(false);
    }

    //MODIFIES: this
    //EFFECTS: Customizes the buttons that are center aligned
    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Cambria", Font.BOLD, 20));
        button.setBorder(BorderFactory.createEmptyBorder(11,40,12,40));
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }


    public JPanel getPanel() {
        return panel;
    }

    public PlantCollection getCollection() {
        return collection;
    }
    
}
