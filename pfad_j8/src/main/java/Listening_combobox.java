import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class Listening_combobox extends JComboBox<String> {

    // combobox object constructor
    public Listening_combobox
    (String dataFilePath, ArrayList<Panel_using_array> panelsToBeControlled) throws IOException {
        this.addItemListener((ItemEvent evt) -> {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                //String selectedYear = this.getSelectedItem().toString();
                //System.out.println("Selected year: " + selectedYear);
                // fill panel spinners with values for the selected year
                if (panelsToBeControlled.size() == 3) {
                    try {
                        // assume left and right panels only (BO window)
                        // bottom panel not needed to be filled
                        // BD window has 5 panels to be controlled, so handled at Bo_distribution_window_model.java
                        // left panel
                        Widget_filler.Get_selected_year_values
                                (dataFilePath,
                                        this.getSelectedItem().toString(),
                                        panelsToBeControlled.get(1),
                                        Constants.BO_LEFT_PANEL_START+1,
                                        Constants.BO_RIGHT_PANEL_START);
                        // right panel
                        Widget_filler.Get_selected_year_values
                                (dataFilePath,
                                        this.getSelectedItem().toString(),
                                        panelsToBeControlled.get(2),
                                        Constants.BO_RIGHT_PANEL_START+1,
                                        Constants.BO_BOTTOM_PANEL_START);
                    } catch (IOException ex) {
                        System.getLogger(Listening_combobox.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
        });
    }
    
}
