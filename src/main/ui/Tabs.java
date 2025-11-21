package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Plant;

import java.awt.*;

@ExcludeFromJacocoGeneratedReport
public abstract class Tabs {
    protected JPanel panel;
    protected Image backgroundImage;

    protected void collectionLoadedPopUp(String fileName) {
        JLabel imageLabel = new JLabel(new ImageIcon(fileName));

        JFrame popUp = new JFrame();            
        popUp.getContentPane().add(imageLabel);

        Image icon = new ImageIcon("images/icon.png").getImage();
        popUp.setIconImage(icon);

        popUp.pack();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);

        new javax.swing.Timer(3000, evt -> popUp.dispose()).start();
    }

    //MODIFIES: this
    //EFFECTS: Changes the appearance of the button
    protected void styleButton(JButton button, int posX, int posY) {
        button.setBounds(posX, posY, 200, 40);
        button.setFont(new Font("Cambria", Font.BOLD, 20));
        button.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    //MODIFIES: this
    //EFFECTS: Changes the appearance of the title
    protected void styleTitle(JLabel title, int posX, int posY) {
        title.setFont(new Font("Gabriola", Font.BOLD, 55));
        title.setBounds(posX, posY, 400, 60);
        title.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        title.setForeground(new Color(0, 0, 0));
        title.setOpaque(false);
    }

    //MODIFIES: this
    //EFFECTS: Constructs a panel with background image
    protected void setupBackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
        
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }            
        };

        panel.setLayout(new BorderLayout()); 
        
    }

    //MODIFIES: this
    //EFFECTS: Converts the chosen categories indicated with numbers to strings
    protected String categoryToString(Plant p) {
        String category;
        switch (p.getCategory()) {
            case 1:
                category = "Structurals";
                break;
            case 2:
                category = "Flowers";
                break;
            case 3:
                category = "Foods";
                break;
            default:
                category = "";
        }
        return category;
    }

    //MODIFIES: this
    //EFFECTS: Changes the chosen categories for sunlight to strings
    protected String sunlightToString(Plant p) {
        String sunlight;
        switch (p.getCareInfo().getSunlight()) {
            case 1:
                sunlight = "Full sun";
                break;
            case 2:
                sunlight = "Partial sun";
                break;
            case 3:
                sunlight = "Shade";
                break;
            default:
                sunlight = "";
        }
        return sunlight;
    }

    //EFFECTS: converts category to corresponding integer
    protected int categoryToInt(String categoryString) {
        if (categoryString.equals("Flowers")) {
            return 2;
        } else if (categoryString.equals("Foods")) {
            return 3;
        }
        return 1;
    }

    //EFFECTS: converts sunlight to corresponding integer
    protected int sunlightToInt(String sunlightString) {
        if (sunlightString.equals("Partial sun")) {
            return 2;
        } else if (sunlightString.equals("Shade")) {
            return 3;
        }
        return 1;
    }

}
