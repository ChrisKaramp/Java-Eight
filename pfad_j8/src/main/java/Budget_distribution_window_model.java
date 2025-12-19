import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Budget_distribution_window_model extends JFrame {

    // frame object constructor
    public Budget_distribution_window_model(int window_width, int window_height, String dataFilePath, Object somePanel) throws IOException {
        // define a structure to keep panels connected
        ArrayList<Panel_using_array> panelsToBeControlled = new ArrayList<>();
        //// define the FIVE VERTICAL panels into this frame will be split
        // define the top panel
        JPanel topSmallPanelArea
            = new Top_panel_bd_model(dataFilePath);
        // define the middle panel wich will be split into THREE nested panels
        JPanel middlePanelArea
            = new JPanel();
        // define the bottom panel - will be controlled
        Panel_using_array bottomSmallPanelArea
            = new Panel_using_array
            (dataFilePath, Constants.BD_BOTTOM_PANEL_START, Constants.ENDING_POINT, 4, true, null, ((Top_panel_bo_model) somePanel));
        panelsToBeControlled.add(bottomSmallPanelArea);
        // define the THREE nested panels inside the middle panel
        Panel_using_array nestedPanel1Area
            = new Panel_using_array
            (dataFilePath, Constants.BD_NESTED_PANEL1_START, Constants.BD_NESTED_PANEL2_START, 4, false, panelsToBeControlled, ((Top_panel_bo_model) somePanel));
        panelsToBeControlled.add(nestedPanel1Area);
        Panel_using_array nestedPanel2Area
            = new Panel_using_array
            (dataFilePath, Constants.BD_NESTED_PANEL2_START, Constants.BD_NESTED_PANEL3_START, 4, true, panelsToBeControlled, ((Top_panel_bo_model) somePanel));
        panelsToBeControlled.add(nestedPanel2Area);
        Panel_using_array nestedPanel3Area
            = new Panel_using_array
            (dataFilePath, Constants.BD_NESTED_PANEL3_START, Constants.BD_BOTTOM_PANEL_START, 4, true, panelsToBeControlled, ((Top_panel_bo_model) somePanel));
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
        middlePanelArea.add(BorderLayout.CENTER, nestedPanel2Area);
        middlePanelArea.add(BorderLayout.SOUTH, nestedPanel3Area);

        //// fill budget distibution window with selected year values
        // nested panel 1
        Widget_filler.Get_selected_year_values
            (dataFilePath, ((Top_panel_bo_model) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel1Area, Constants.BD_NESTED_PANEL1_START, Constants.BD_NESTED_PANEL2_START);
        // nested panel 2
        Widget_filler.Get_selected_year_values
            (dataFilePath, ((Top_panel_bo_model) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel2Area, Constants.BD_NESTED_PANEL2_START+3, Constants.BD_NESTED_PANEL3_START);
        // nested panel 3
        Widget_filler.Get_selected_year_values
            (dataFilePath, ((Top_panel_bo_model) somePanel).getYearSelectLCB().getSelectedItem().toString(), nestedPanel3Area, Constants.BD_NESTED_PANEL3_START+3, Constants.BD_BOTTOM_PANEL_START);
    }
}
