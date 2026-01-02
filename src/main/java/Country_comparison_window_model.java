import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class Country_comparison_window_model extends JFrame {

    // frame object constructor
    public Country_comparison_window_model(int window_width, int window_height, String dataFilePath, String foreignCountryDataFilePath, int startingPoint, int endingPoint, int gridColumns, String selectedYear) throws IOException {

        //// define the TWO comparison panels into this frame will be split
        // define left panel (domestic country - actually, ANY country file)
        Comparison_panel_using_array leftPanelArea = new Comparison_panel_using_array
            (dataFilePath, startingPoint, endingPoint, gridColumns, selectedYear);
        // define right panel (same as above)
        Comparison_panel_using_array rightPanelArea
            = new Comparison_panel_using_array
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