package gr.aueb.javaeight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 * Creates a window that holds a couple of panels.
 * Each panel hosts data of a country.
 */
public class CountryComparisonWindowModel extends JFrame {
    /**
     * Creates window using parameters given.
     * Creates panel objects.
     * Places panels on itself.
     * Configures them and itself.
     * @param window_width frame width
     * @param window_height frame height
     * @param dataFilePath absolute path to data file
     * @param foreignCountryDataFilePath absolute path to a country's data file
     * @param startingPoint start point of panel data in headers array in Constants.java
     * @param endingPoint end point of panel data in headers array in Constants.java
     * @param gridColumns number of columns (1+N)
     * @param selectedYear year selected
     * @throws IOException I/O error exception
     */
    public CountryComparisonWindowModel(int window_width, int window_height, String dataFilePath, String foreignCountryDataFilePath, int startingPoint, int endingPoint, int gridColumns, String selectedYear) throws IOException {

        //// define the TWO comparison panels into this frame will be split
        // define left panel (domestic country - actually, ANY country file)
        ComparisonArrayPanel leftPanelArea = new ComparisonArrayPanel
            (dataFilePath, startingPoint, endingPoint, gridColumns, selectedYear);
        // define right panel (same as above)
        ComparisonArrayPanel rightPanelArea
            = new ComparisonArrayPanel
            (foreignCountryDataFilePath, startingPoint, endingPoint, gridColumns, selectedYear);

        // initialize left and right panel borders
        Border leftPanelBorder 
            = BorderFactory.createTitledBorder
            ("Greece");
        Border rightPanelBorder
            = BorderFactory.createTitledBorder
            ("Foreign Country");
        
        // configure this four panels window (frame)
        this.setSize(window_width, window_height);
        this.getContentPane().setBackground(Color.blue);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        //// configure the two panels
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

        // place panels into main window
        this.add(BorderLayout.WEST, leftPanelArea);
        this.add(BorderLayout.EAST, rightPanelArea);
    }
}