package gr.aueb.javaeight;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates a panel used to be the top panel of Budget Origin frame/window.
 */
public class TopPanelBOModel extends JPanel {

    //// define panel components to be used and placed into
    // countries selection components
    private final JLabel comparisonJL = new JLabel("Σύγκριση:");
    private final ComparisonCombobox countrySelectLCB;
    // mass update components
    private final InteractiveButton massDataSaveLB;
    // year selection components
    private final JLabel yearJL = new JLabel("Έτος:");
    private final YearSelectionCombobox yearSelectLCB;
    // year add components
    private final InteractiveButton yearAddLB;
    static JTextField yearaddJTF = new JTextField("2000");
    // budget distribution launch components
    private final InteractiveButton budgetDistributionLaunchLB;

    /**
     * Creates a new panel using certain components.
     * @param dataFilePath absolute path to data file
     * @param dataFolder absolute path to data folder
     * @param panelsToBeControlled structure to provide control on panels
     * @throws IOException I/O error exception
     */
    public TopPanelBOModel(String dataFilePath, String dataFolder, ArrayList<ArrayBasedPanel> panelsToBeControlled) throws IOException {

        //// initialize components
        // initialize mass data save button
        this.massDataSaveLB =
            new InteractiveButton
            ("Μαζική αποθήκευση",
            "mass update",
            this,
            dataFilePath,
            panelsToBeControlled,
            this);
        // initialize year selection combobox
        this.yearSelectLCB = new YearSelectionCombobox(dataFilePath, panelsToBeControlled);
        // initialize year adding button
        this.yearAddLB =
            new InteractiveButton
            ("Προσθήκη έτους",
            "add year",
            this,
            dataFilePath,
            panelsToBeControlled,
            this);
        // initialize budget distribution window launching button
        this.budgetDistributionLaunchLB =
        new InteractiveButton
        ("Κατανομή",
        "launch budget distribution",
        this,
        dataFilePath,
        panelsToBeControlled,
        this);
        // initialize country selection combobox to have access at year selection combobox
        this.countrySelectLCB = new ComparisonCombobox(this.yearSelectLCB, dataFilePath, dataFolder);
        
        //// place components on panel
        this.add(comparisonJL);
        FileNameProvider countryFileNames = new FileNameProvider(dataFolder, Constants.CSV_FILE_EXTENSION);
        for (String country: countryFileNames.getFileNames()) {
            countrySelectLCB.addItem(country);
        }
        this.add(countrySelectLCB);
        countrySelectLCB.setInitialized();
        this.add(massDataSaveLB);
        this.add(yearJL);
        this.add(yearSelectLCB);
        for (String year: YearProvider.GetYearsIntoStringsArray(dataFilePath)) {
            yearSelectLCB.addItem(year);
        }
        this.add(yearAddLB);
        this.add(yearaddJTF);
        this.add(budgetDistributionLaunchLB);
    }

    public YearSelectionCombobox getYearSelectLCB() {
        return yearSelectLCB;
    }

    public ComparisonCombobox getCountrySelectLCB() {
        return countrySelectLCB;
    }
}
