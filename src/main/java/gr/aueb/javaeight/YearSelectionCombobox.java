package gr.aueb.javaeight;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;

/**
 * Creates a menu to select year to show data
 */
public class YearSelectionCombobox extends JComboBox<String> {

    /**
     * 
     * @param dataFilePath absolute path to data file
     * @param panelsToBeControlled structure to provide control on panels
     * @throws IOException I/O error exception
     */
    public YearSelectionCombobox
    (String dataFilePath, ArrayList<ArrayBasedPanel> panelsToBeControlled) throws IOException {
        this.addItemListener((ItemEvent evt) -> {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // fill panel spinners with values for the selected year
                if (panelsToBeControlled.size() == 3) {
                    try {
                        // assume left and right panels only (BO window)
                        // bottom panel not needed to be filled
                        // BD window has 5 panels to be controlled, so handled at Bo_distribution_window_model.java
                        // left panel
                        SpinnerValuesInitializer.GetSelectedYearValues
                                (dataFilePath,
                                        this.getSelectedItem().toString(),
                                        panelsToBeControlled.get(1),
                                        Constants.BO_LEFT_PANEL_START+1,
                                        Constants.BO_RIGHT_PANEL_START);
                        // right panel
                        SpinnerValuesInitializer.GetSelectedYearValues
                                (dataFilePath,
                                        this.getSelectedItem().toString(),
                                        panelsToBeControlled.get(2),
                                        Constants.BO_RIGHT_PANEL_START+1,
                                        Constants.BO_BOTTOM_PANEL_START);
                    } catch (IOException ex) {
                        System.getLogger(YearSelectionCombobox.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
        });
    }
    
}
