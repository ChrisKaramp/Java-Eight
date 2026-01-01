import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Top_panel_bo_model extends JPanel {

    //// define panel components to be used and placed into
    // countries selection components
    private final JLabel comparisonJL = new JLabel("Σύγκριση:");
    private final Comparison_combobox countrySelectLCB;
    // mass update components
    private final Listening_button massDataSaveLB;
    // year selection components
    private final JLabel yearJL = new JLabel("Έτος:");
    private final Listening_combobox yearSelectLCB;
    // year add components
    private final Listening_button yearAddLB;
    static JTextField yearaddJTF = new JTextField("2000");
    // budget distribution launch components
    private final Listening_button budgetDistributionLaunchLB;

    // panel object constructor
    public Top_panel_bo_model(String dataFilePath, String dataFolder, ArrayList<Panel_using_array> panelsToBeControlled) throws IOException {

        //// initialize components
        // initialize mass data save button
        this.massDataSaveLB =
            new Listening_button
            ("Μαζική αποθήκευση",
            "mass update",
            this,
            dataFilePath,
            panelsToBeControlled,
            this);
        // initialize year selection combobox
        this.yearSelectLCB = new Listening_combobox(dataFilePath, panelsToBeControlled);
        // initialize year adding button
        this.yearAddLB =
            new Listening_button
            ("Προσθήκη έτους",
            "add year",
            this,
            dataFilePath,
            panelsToBeControlled,
            this);
        // initialize budget distribution window launching button
        this.budgetDistributionLaunchLB =
        new Listening_button
        ("Κατανομή",
        "launch budget distribution",
        this,
        dataFilePath,
        panelsToBeControlled,
        this);
        // initialize country selection combobox to have access at year selection combobox
        this.countrySelectLCB = new Comparison_combobox(this.yearSelectLCB, dataFilePath, dataFolder);
        
        //// place components on panel
        this.add(comparisonJL);
        File_names_getter countryFileNames = new File_names_getter(dataFolder, Constants.CSV_FILE_EXTENSION);
        for (String country: countryFileNames.getFileNames()) {
            countrySelectLCB.addItem(country);
        }
        this.add(countrySelectLCB);
        countrySelectLCB.setInitialized();
        this.add(massDataSaveLB);
        this.add(yearJL);
        this.add(yearSelectLCB);
        for (String year: Get_years.Get_years_into_strings_array(dataFilePath)) {
            yearSelectLCB.addItem(year);
        }
        this.add(yearAddLB);
        this.add(yearaddJTF);
        this.add(budgetDistributionLaunchLB);
    }

    public Listening_combobox getYearSelectLCB() {
        return yearSelectLCB;
    }

    public Comparison_combobox getCountrySelectLCB() {
        return countrySelectLCB;
    }
}

