package ui;

import javax.swing.*;
import java.awt.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.PlantCollection;

@ExcludeFromJacocoGeneratedReport
public class PlantAppUI extends JFrame {
    private JTabbedPane tabbedPane;
    private Image icon; 
    private PlantCollection collection;

    public PlantAppUI() {
        super("Planting Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        icon = new ImageIcon("images/icon.png").getImage();
        setIconImage(icon);

        collection = new PlantCollection();

        setPreferredSize(new Dimension(800, 700));
        setLayout(new FlowLayout());
        getContentPane().setLayout(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Cambria", Font.BOLD, 16));
        tabbedPane.setBackground(new Color (131, 210, 230));
        tabbedPane.setForeground(new Color(0,0,0));

        tabbedPane.addTab("Home", new HomePageTab(collection).getPanel());
        tabbedPane.addTab("Create New Plant", new NewPlantTab(collection).getPanel());
        tabbedPane.addTab("View Plant", new ViewPlantTab(collection).getPanel());
        tabbedPane.addTab("Edit Plant", new EditPlantTab(collection).getPanel());
        tabbedPane.addTab("View Collections", new ViewCollectionsTab(collection).getPanel());

        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new PlantAppUI();
    }
}