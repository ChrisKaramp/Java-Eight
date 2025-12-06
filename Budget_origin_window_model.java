import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

public class Budget_origin_window_model extends JFrame {

    // frame object constructor
    public Budget_origin_window_model(int window_width, int window_height, String dataFilePath) throws IOException {

        //// define the FOUR CROSSED panels into this frame will be split

        // define the top panel
        JPanel topPanelArea =
            new Top_panel_bo_model(dataFilePath);

        // define the left panel
        JPanel leftPanelArea =
            new Panel_using_array(
                dataFilePath,
                Constants.BO_LEFT_PANEL_START,
                Constants.BO_RIGHT_PANEL_START,
                2,
                true
            );

        // define the right panel
        JPanel rightPanelArea =
            new Panel_using_array(
                dataFilePath,
                Constants.BO_RIGHT_PANEL_START,
                Constants.BD_NESTED_PANEL1_START,
                2,
                true
            );

        // define the bottom panel
        JPanel bottomPanelArea =
            new Bottom_panel_bo_model(dataFilePath);

        // initialize left and right panel borders
        Border leftPanelBorder =
            BorderFactory.createTitledBorder(
                "1. Έσοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση."
            );

        Border rightPanelBorder =
            BorderFactory.createTitledBorder(
                "2. Έξοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση."
            );

        //// configure main window
        this.setSize(window_width, window_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        
        Color mainGray = new Color(240, 240, 240);
        this.getContentPane().setBackground(mainGray);

        //// configure panels
        topPanelArea.setBackground(mainGray);
        topPanelArea.setLayout(new GridLayout(1, 9, 5, 5));

        leftPanelArea.setBackground(mainGray);
        leftPanelArea.setBorder(leftPanelBorder);

        rightPanelArea.setBackground(mainGray);
        rightPanelArea.setBorder(rightPanelBorder);

        bottomPanelArea.setBackground(mainGray);
        bottomPanelArea.setLayout(new GridLayout(2, 2, 2, 2));

        //// place panels
        this.add(BorderLayout.NORTH, topPanelArea);
        this.add(BorderLayout.WEST, leftPanelArea);
        this.add(BorderLayout.EAST, rightPanelArea);
        this.add(BorderLayout.SOUTH, bottomPanelArea);

        this.setVisible(true);
    }
}
