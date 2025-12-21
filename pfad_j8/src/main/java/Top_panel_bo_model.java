import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Top_panel_bo_model extends JPanel {

    //// define panel components to be used and placed into
    // countries selection components
    private final JLabel comparisonJL = new JLabel("Σύγκριση:");
    String[] countries = { "France", "Italy", "Germany", "USA", "UK" };
    JComboBox<String> countryselectJCB = new JComboBox<>(countries);
    // mass update components
    private Listening_button massDataSaveLB;
    // year selection components
    private final JLabel yearJL = new JLabel("Έτος:");
    private Listening_combobox yearSelectLCB;
    // year add components
    Listening_button yearAddLB;
    static JTextField yearaddJTF = new JTextField("2000");
    // budget distribution launch components
    Listening_button budgetDistributionLaunchLB;

    // panel object constructor
    public Top_panel_bo_model(String dataFilePath, ArrayList<Panel_using_array> panelsToBeControlled) throws IOException {
        // initialize year selection combobox with reference to a panel_using_array type panel
        this.massDataSaveLB = new Listening_button("Μαζική αποθήκευση", "mass update", this, dataFilePath, panelsToBeControlled, this);
        this.yearSelectLCB = new Listening_combobox(dataFilePath, panelsToBeControlled);
        this.yearAddLB = new Listening_button("Προσθήκη έτους", "add year", this, dataFilePath, panelsToBeControlled, this);
        this.budgetDistributionLaunchLB = new Listening_button("Κατανομή", "launch budget distribution", this, dataFilePath, panelsToBeControlled, this);

        // place components on panel
        this.add(comparisonJL);
        this.add(countryselectJCB);
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

    public void setYearSelectLCB(Listening_combobox yearSelectLCB) {
        this.yearSelectLCB = yearSelectLCB;
    }

    public Listening_button getMassDataSaveLB() {
        return massDataSaveLB;
    }

    public void setMassDataSaveLB(Listening_button massDataSaveLB) {
        this.massDataSaveLB = massDataSaveLB;
    }
}
