import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

public class Top_panel_bd_model extends JPanel {

    //// define panel components to be used and placed into
    // just four labels
    JLabel codeAndServiceJL = new JLabel("ΚΩΔΙΚΟΣ - ΦΟΡΕΑΣ");
    JLabel regularBudgetJL = new JLabel("TΑΚΤΙΚΟΣ ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ");
    JLabel publicInvestmentsBudgetJL = new JLabel("ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ ΔΗΜΟΣΙΩΝ ΕΠΕΝΔΥΣΕΩΝ");
    JLabel generalTotalJL = new JLabel("ΓΕΝΙΚΟ ΣΥΝΟΛΟ");
  
    // panel object constructor
    public Top_panel_bd_model(String dataFilePath) throws IOException {

        // configure panel layout
        this.setLayout(new GridLayout(1, 4, 5, 5));

        // place components on panel
        this.add(codeAndServiceJL);
        this.add(regularBudgetJL);
        this.add(publicInvestmentsBudgetJL);
        this.add(generalTotalJL);
    }
}
