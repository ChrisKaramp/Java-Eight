import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JComboBox;

public class Comparison_combobox extends JComboBox<String> {
    // prevent launching listener when created
    private boolean initialized = false;

    // combobox object constructor
    public Comparison_combobox(Listening_combobox yearSelect, String dataFilePath, String dataFolder) throws IOException {

        this.addItemListener((ItemEvent evt) -> {

            // do nothing if it is the first launch
            if (!initialized) {
                return;
            }

            if (evt.getStateChange() == ItemEvent.SELECTED) {

                // get csv file of selected country including extension
                String selectedCountry = this.getSelectedItem().toString();
                
                // convert datafolder from String into Path
                Path dataFolderAsPath = Path.of(dataFolder).toAbsolutePath();

                // assemble selected country csv file path as Path
                Path foreignCountryDataFilePath = dataFolderAsPath.resolve(selectedCountry);

                // invoke proper comparison frame (window) with selected country data file path
                try {
                    
                    // get selected year
                    String selectedYear = yearSelect.getSelectedItem().toString();

                    // launch country comparison window for selected country and year - budget origin
                    Country_comparison_window_model countryComparisonWindowBO
                        = new Country_comparison_window_model
                        (Constants.FRAME_WIDTH,
                        Constants.FRAME_HEIGHT,
                        dataFilePath,
                        foreignCountryDataFilePath.toString(),
                        Constants.BO_LEFT_PANEL_START,
                        Constants.BD_NESTED_PANEL1_START,
                        2,
                        selectedYear);
                    countryComparisonWindowBO.setTitle
                        ("Προέλευση προϋπολογισμού: σύγκριση με " + selectedCountry + " για το έτος " + selectedYear);
                    
                    // launch country comparison window for selected country and year - budget destination
                    Country_comparison_window_model countryComparisonWindowBD
                        = new Country_comparison_window_model
                        (Constants.FRAME_WIDTH,
                        Constants.FRAME_HEIGHT,
                        dataFilePath,
                        foreignCountryDataFilePath.toString(),
                        Constants.BD_NESTED_PANEL1_START,
                        Constants.ENDING_POINT,
                        4,
                        selectedYear);
                    countryComparisonWindowBD.setTitle
                        ("Κατανομή προϋπολογισμού: σύγκριση με " + selectedCountry + " για το έτος " + selectedYear);
                } catch (IOException ex) {
                    System.getLogger(Comparison_combobox.class.getName()).
                        log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });
    }

    public void setInitialized() {
        this.initialized = true;
    }
}
