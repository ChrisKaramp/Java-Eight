package gr.aueb.javaeight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 * Creates the first window of the application, the Budget Origin Window.
 * Uses a model of one top, two middle (L/R) and one bottom panel.
 * Total four of them.
 */
public class BudgetOriginWindowModel extends JFrame {
    /**
     * Creates a new frame object, placing panel objects on it.
     * @param window_width frame width
     * @param window_height frame height
     * @param dataFilePath absolute path to data file
     * @param dataFolder absolute path to data folder
     * @throws IOException I/O error exception
     */
    public BudgetOriginWindowModel(int window_width, int window_height, String dataFilePath, String dataFolder) throws IOException {
        // define a structure to keep panels connected
        ArrayList<ArrayBasedPanel> panelsToBeControlled = new ArrayList<>();
        //// define the FOUR CROSSED panels into this frame will be split
        // define top panel
        TopPanelBOModel topPanelArea
            = new TopPanelBOModel(dataFilePath, dataFolder, panelsToBeControlled);
        ArrayBasedPanel bottomPanelArea
            = new ArrayBasedPanel
            (dataFilePath, Constants.BO_BOTTOM_PANEL_START, Constants.BD_NESTED_PANEL1_START, 2, true, null, topPanelArea);
        panelsToBeControlled.add(bottomPanelArea);
        // define left panel
        ArrayBasedPanel leftPanelArea = new ArrayBasedPanel
            (dataFilePath, Constants.BO_LEFT_PANEL_START, Constants.BO_RIGHT_PANEL_START, 2, true, panelsToBeControlled, topPanelArea);
        panelsToBeControlled.add(leftPanelArea);
        // define right panel
        ArrayBasedPanel rightPanelArea
            = new ArrayBasedPanel
            (dataFilePath, Constants.BO_RIGHT_PANEL_START, Constants.BO_BOTTOM_PANEL_START, 2, true, panelsToBeControlled, topPanelArea);
        panelsToBeControlled.add(rightPanelArea);

        // initialize left and right panel borders
        Border leftPanelBorder 
            = BorderFactory.createTitledBorder
            ("1. Έσοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση.");
        Border rightPanelBorder
            = BorderFactory.createTitledBorder
            ("2. Έξοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση.");
        
        // configure this four panels window (frame)
        this.setSize(window_width, window_height);
        this.getContentPane().setBackground(Color.blue);
        this.setVisible(true);
        this.setResizable(false);

        // ask user to save data on close, or not, or cancel
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int userChoice =
                    JOptionPane.showConfirmDialog(
                    rootPane,
                    "Θέλετε να αποθηκεύσετε πριν το κλείσιμο;",
                    "Αποθήκευση",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (userChoice == JOptionPane.YES_OPTION) {
                    try {
                        MassUpdater.writeSortedDataIntoFile(dataFilePath, panelsToBeControlled, topPanelArea);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.exit(0);
                } else if (userChoice == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } // if CANCEL or close dialog, do nothing
            }
        });


        //// configure the four panels
        // top panel
        topPanelArea.setBackground(Color.gray);
        topPanelArea.setLayout(new GridLayout(1, 9, 5, 5));
        // left panel
        leftPanelArea.setBackground(Color.green);
        Double leftPanelArea_width_Double = window_width * 0.98; // necessary adjustment
        int leftPanelArea_width = leftPanelArea_width_Double.intValue();
        leftPanelArea.setPreferredSize(new Dimension(leftPanelArea_width/2, window_height));
        leftPanelArea.setBorder(leftPanelBorder);
        // right panel
        rightPanelArea.setBackground(Color.pink);
        rightPanelArea.setPreferredSize(new Dimension(window_width/2, window_height));
        rightPanelArea.setBorder(rightPanelBorder);
        // bottom panel
        bottomPanelArea.setBackground(Color.orange);

        // place panels into main window
        this.add(BorderLayout.NORTH, topPanelArea);
        this.add(BorderLayout.WEST, leftPanelArea);
        leftPanelArea.initShortcut(leftPanelArea, "ResetL", KeyStroke.getKeyStroke("control L"));
        this.add(BorderLayout.EAST, rightPanelArea);
        rightPanelArea.initShortcut(rightPanelArea, "ResetR", KeyStroke.getKeyStroke("control R"));
        this.add(BorderLayout.SOUTH, bottomPanelArea);

        //// fill budget origin window with selected year values
        // fill left panel
        SpinnerValuesInitializer.GetSelectedYearValues
            (dataFilePath,
            topPanelArea.getYearSelectLCB().getSelectedItem().toString(),
            leftPanelArea,
            Constants.BO_LEFT_PANEL_START+1,
            Constants.BO_RIGHT_PANEL_START);
        // fill right panel
        SpinnerValuesInitializer.GetSelectedYearValues
            (dataFilePath,
                topPanelArea.getYearSelectLCB().getSelectedItem().toString(),
                rightPanelArea,
                Constants.BO_RIGHT_PANEL_START+1,
                Constants.BO_BOTTOM_PANEL_START);
    }
}
