import java.io.IOException;
import javax.swing.*;


public class Top_panel_bo_model extends JPanel {

    //// define panel components to be used and placed into
    // countries selection components
    JLabel comparisonJL = new JLabel("Σύγκριση:");
    String[] countries = { "France", "Italy", "Germany", "USA", "UK" };
    JComboBox<String> countryselectJCB = new JComboBox<>(countries);
    // mass save components
    Listening_button massDataSaveLB = new Listening_button("Μαζική αποθήκευση", "mass save");
    // year selection components
    JLabel yearJL = new JLabel("Έτος:");
    static JComboBox<String> yearselectJCB = new JComboBox<>();
    Listening_button yearAddLB = new Listening_button("Προσθήκη έτους ->",  "add year");
    static JTextField yearaddJTF = new JTextField("2000");
    // budget distribution launch components
    Listening_button budgetDistributionLaunchLB
        = new Listening_button("Κατανομή", "launch budget distribution");


    
    // panel object constructor
    public Top_panel_bo_model(String dataFilePath) throws IOException {
        // place components on panel
        this.add(comparisonJL);
        this.add(countryselectJCB);
        this.add(massDataSaveLB);
        this.add(yearJL);
        this.add(yearselectJCB);
        for ( String year: Get_years.Get_years_into_strings_array(dataFilePath)) {
            yearselectJCB.addItem(year);
        }
        this.add(yearAddLB);
        this.add(yearaddJTF);
        this.add(budgetDistributionLaunchLB);
    }
}
