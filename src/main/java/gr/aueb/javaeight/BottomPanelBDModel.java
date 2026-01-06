package gr.aueb.javaeight;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel used to be the bottom panel of Budget Distribution frame/window.
 */
public class BottomPanelBDModel extends JPanel {
    //// define panel components to be used and placed into
    // just four labels
    JLabel totalBudgetExpensesJL = new JLabel("ΣΥΝΟΛΟ ΕΞΟΔΩΝ ΚΡΑΤΙΚΟΥ ΠΡΟΫΠΟΛΟΓΙΣΜΟΥ");
    JLabel totalRegularBudgetExpensesJL = new JLabel("0");
    JLabel totalPublicInvestmentsBudgetExpensesJL = new JLabel("0");
    JLabel generalTotalBudgetExpensesJL = new JLabel("0");
  
    /**
     * Creates a new panel using certain components.
     * @param dataFilePath absolute path to data file
     * @throws IOException I/O error exception
     */
    public BottomPanelBDModel(String dataFilePath) throws IOException {
        // configure panel layout
        this.setLayout(new GridLayout(1, 4, 5, 5));

        // place components on panel
        this.add(totalBudgetExpensesJL);
        this.add(totalRegularBudgetExpensesJL);
        this.add(totalPublicInvestmentsBudgetExpensesJL);
        this.add(generalTotalBudgetExpensesJL);

    }
    
}
