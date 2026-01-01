import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

public class Bottom_panel_bd_model extends JPanel {
    //// define panel components to be used and placed into
    // just four labels
    JLabel totalBudgetExpensesJL = new JLabel("ΣΥΝΟΛΟ ΕΞΟΔΩΝ ΚΡΑΤΙΚΟΥ ΠΡΟΫΠΟΛΟΓΙΣΜΟΥ");
    JLabel totalRegularBudgetExpensesJL = new JLabel("0");
    JLabel totalPublicInvestmentsBudgetExpensesJL = new JLabel("0");
    JLabel generalTotalBudgetExpensesJL = new JLabel("0");
  
    // panel object constructor
    public Bottom_panel_bd_model(String dataFilePath) throws IOException {
        // configure panel layout
        this.setLayout(new GridLayout(1, 4, 5, 5));

        // place components on panel
        this.add(totalBudgetExpensesJL);
        this.add(totalRegularBudgetExpensesJL);
        this.add(totalPublicInvestmentsBudgetExpensesJL);
        this.add(generalTotalBudgetExpensesJL);

    }
    
}