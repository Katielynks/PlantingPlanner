package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Plant;
import model.PlantCollection;

import java.awt.*;
import java.util.ArrayList;

public class ViewCollectionsTab extends Tabs {
    private JLabel title;
    private JPanel componentPane;

    private JRadioButton optionAll;
    private JRadioButton optionStructurals;
    private JRadioButton optionFlowers;
    private JRadioButton optionFoods;

    private PlantCollection collection;
    private JLabel selectionHeading;
    private JLabel listHeading;
    private JPanel radioButtons;

    private int column1 = 30;
    private DefaultTableModel tableModel;
    private final String[] columnNames = {"Name",
                                          "Subtype"};
    private JTable list;

    public ViewCollectionsTab(PlantCollection collection) {
        setupBackgroundPanel("images/viewCollectionsTabBackground.png");
        
        componentPane = new JPanel(null);
        componentPane.setOpaque(false);

        this.collection = collection;

        title = new JLabel("View Collections");
        styleTitle(title, 250, 40);

        selectionHeading = new JLabel("Selection:");
        styleHeading(selectionHeading, 100, 150);

        listHeading = new JLabel("Collection:");
        styleHeading(listHeading, 410, 150);

        radioButtons = createRadioButtons();

        tableModel = new DefaultTableModel(listToArray(collection.getCollection()), columnNames);
        list = new JTable(tableModel);
        list.setFont(new Font("Cambria", Font.BOLD, 16));
        JScrollPane listScrollPane = new JScrollPane(list);
        styleScrollPane(listScrollPane);
        listScrollPane.setBounds(410,205,275,300);



        JTableHeader header = list.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setBackground(new Color (131, 210, 230));

        componentPane.add(title);
        componentPane.add(selectionHeading);
        componentPane.add(listHeading);
        componentPane.add(radioButtons);
        componentPane.add(listScrollPane);
        panel.add(componentPane);

    }

    private void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setOpaque(true);
        scrollPane.setBackground(new Color(230, 249, 255));
        scrollPane.getViewport().setBackground(new Color(230, 249, 255));
    }

    private Object [][] listToArray(ArrayList<Plant> collection) {
        Object[][] dataCollection = new Object[collection.size()][2];
        
        for (int i = 0; i < collection.size(); i++) {
            Plant p = collection.get(i);

            dataCollection[i][0] = p.getName();
            dataCollection[i][1] = p.getSubtype();
        }

        return dataCollection;

    }

    //EFFECTS: Generates the radiobuttons and text associated
    private JPanel createRadioButtons() {
        optionAll = new JRadioButton("All Plants");
        styleButton(optionAll, column1, 70);
        optionAll.addActionListener(e -> {
            listHeading.setText("All Plants: ");
            tableModel.setDataVector(listToArray(collection.getCollection()), columnNames);

        });

        optionStructurals = new JRadioButton("Structurals");
        styleButton(optionStructurals, column1, 110);
        optionStructurals.addActionListener(e -> {
            listHeading.setText("Structural Plants: ");
            tableModel.setDataVector(listToArray(collection.filterStructurals()), columnNames);

        });

        optionFlowers = new JRadioButton("Flowers");
        styleButton(optionFlowers, column1, 150);
        optionFlowers.addActionListener(e -> {
            listHeading.setText("Flowering Plants: ");
            tableModel.setDataVector(listToArray(collection.filterFlowers()), columnNames);

        });

        optionFoods = new JRadioButton("Foods");
        styleButton(optionFoods, column1, 190);
        optionFoods.addActionListener(e -> {
            listHeading.setText("Food Producing Plants: ");
            tableModel.setDataVector(listToArray(collection.filterFood()), columnNames);

        });

        ButtonGroup group = new ButtonGroup();
        group.add(optionAll);
        group.add(optionStructurals);
        group.add(optionFlowers);
        group.add(optionFoods);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setOpaque(false);
        radioButtonPanel.setLayout(null);
        radioButtonPanel.setBounds(100,150,220,350);
        radioButtonPanel.add(optionAll);
        radioButtonPanel.add(optionStructurals);
        radioButtonPanel.add(optionFlowers);
        radioButtonPanel.add(optionFoods);

        return radioButtonPanel;

    }

    //EFFECTS: Changes appearance of the radio buttons
    private void styleButton(JRadioButton button, int posX, int posY) {
        button.setBounds(posX,posY,150,30);
        button.setOpaque(false);
        button.setFont(new Font("Cambria", Font.BOLD, 18));
        button.setIconTextGap(10);

        ImageIcon unchecked = new ImageIcon("images/unchecked.png");
        ImageIcon checked = new ImageIcon("images/checked.png");
        
        Image uncheckedScaledImage = unchecked.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image checkedScaledImage = checked.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);

        ImageIcon uncheckedScaledIcon = new ImageIcon(uncheckedScaledImage);
        ImageIcon checkedScaledIcon = new ImageIcon(checkedScaledImage);
        button.setIcon(uncheckedScaledIcon);
        button.setSelectedIcon(checkedScaledIcon);
        
    }

    //EFFECTS: Changes appearance of the headings
    private void styleHeading(JLabel heading, int posX, int posY) {
        heading.setFont(new Font("Gabriola", Font.BOLD, 35));
        heading.setBounds(posX, posY, 400, 60);
        heading.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        heading.setForeground(new Color(0, 0, 0));
        heading.setOpaque(false);
    }

    public JPanel getPanel() {
        return panel;
    }
    
}
