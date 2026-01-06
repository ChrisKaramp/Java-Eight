package gr.aueb.javaeight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Creates the second window of the application, the Budget Distribution Window.
 * Uses a model of one top, three nested and one bottom panel.
 * Total five of them.
 */
public class BudgetDistributionWindowModel extends JFrame {
    /**
     * Creates a new frame object, placing panel objects on it.
     * @param window_width frame width
     * @param window_height frame height
     * @param dataFilePath absolute path to data file
     * @param somePanel panel to be controlled
     * @throws IOException I/O error exception
     */
    public BudgetDistributionWindowModel(int window_width, int window_height, String dataFilePath, Object somePanel) throws IOException {
        // define a structure to keep panels connected
        ArrayList<ArrayBasedPanel> panelsToBeControlled = new ArrayList<>();
        //// define the FIVE VERTICAL panels into this frame will be split
        // define the top panel
        JPanel topSmallPanelArea
            = new TopPanelBDModel(dataFilePath);
        // define the middle panel wich will be split into THREE nested panels
        JPanel middlePanelArea
            = new JPanel();
        // define the bottom panel - will be controlled
        ArrayBasedPanel bottomSmallPanelArea
            = new ArrayBasedPanel
            (dataFilePath, Constants.BD_BOTTOM_PANEL_START, Constants.ENDING_POINT, 4, true, null, ((TopPanelBOModel) somePanel));
        panelsToBeControlled.add(bottomSmallPanelArea);
        // define the THREE nested panels inside the middle panel
        ArrayBasedPanel nestedPanel1Area
            = new ArrayBasedPanel
            (dataFilePath, Constants.BD_NESTED_PANEL1_START, Constants.BD_NESTED_PANEL2_START, 4, false, panelsToBeControlled, ((TopPanelBOModel) somePanel));
        panelsToBeControlled.add(nestedPanel1Area);
        ArrayBasedPanel nestedPanel2Area
            = new ArrayBasedPanel
            (dataFilePath, Constants.BD_NESTED_PANEL2_START, Constants.BD_NESTED_PANEL3_START, 4, true, panelsToBeControlled, ((TopPanelBOModel) somePanel));
        panelsToBeControlled.add(nestedPanel2Area);
        ArrayBasedPanel nestedPanel3Area
            = new ArrayBasedPanel
            (dataFilePath, Constants.BD_NESTED_PANEL3_START, Constants.BD_BOTTOM_PANEL_START, 4, true, panelsToBeControlled, ((TopPanelBOModel) somePanel));
        panelsToBeControlled.add(nestedPanel3Area);

        // configure this FIVE VERTICAL PANELS window (frame)
        this.setSize(window_width, window_height);
        this.getContentPane().setBackground(Color.blue);
        this.setVisible(true);
        this.setResizable(false);

        //// configure the FIVE panels (and the middle one, used to nest the three middle panels)
        // top panel
        topSmallPanelArea.setBackground(Color.gray);
        // middle panel to nest three panels
        middlePanelArea.setBackground(Color.cyan);
        // bottom panel
        bottomSmallPanelArea.setBackground(Color.pink);
        // nested panel1 inside middle panel
        nestedPanel1Area.setBackground(Color.orange);
        // nested panel2 inside middle panel
        nestedPanel2Area.setBackground(Color.green);
        // nested panel3 inside middle panel
        nestedPanel3Area.setBackground(Color.yellow);

        // place panels into main window
        this.add(BorderLayout.NORTH, topSmallPanelArea);
        this.add(BorderLayout.CENTER, middlePanelArea);
        this.add(BorderLayout.SOUTH, bottomSmallPanelArea);
        // place three nested panels into middle panel
        middlePanelArea.setLayout(new BorderLayout());
        middlePanelArea.add(BorderLayout.NORTH, nestedPanel1Area);
        nestedPanel1Area.initShortcut(nestedPanel1Area, "ResetN1", KeyStroke.getKeyStroke("control 1"));
        middlePanelArea.add(BorderLayout.CENTER, nestedPanel2Area);
        nestedPanel2Area.initShortcut(nestedPanel2Area, "ResetN2", KeyStroke.getKeyStroke("control 2"));
        middlePanelArea.add(BorderLayout.SOUTH, nestedPanel3Area);
        nestedPanel3Area.initShortcut(nestedPanel3Area, "ResetN3", KeyStroke.getKeyStroke("control 3"));


        //// fill budget distibution window with selected year values
        // nested panel 1
        SpinnerValuesInitializer.GetSelectedYearValues
            (dataFilePath, ((TopPanelBOModel) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel1Area, Constants.BD_NESTED_PANEL1_START, Constants.BD_NESTED_PANEL2_START);
        // nested panel 2
        SpinnerValuesInitializer.GetSelectedYearValues
            (dataFilePath, ((TopPanelBOModel) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel2Area, Constants.BD_NESTED_PANEL2_START+3, Constants.BD_NESTED_PANEL3_START);
        // nested panel 3
        SpinnerValuesInitializer.GetSelectedYearValues
            (dataFilePath, ((TopPanelBOModel) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel3Area, Constants.BD_NESTED_PANEL3_START+3, Constants.BD_BOTTOM_PANEL_START);
    }
}
