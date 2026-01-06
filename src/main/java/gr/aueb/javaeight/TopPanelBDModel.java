package gr.aueb.javaeight;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel used to be the bottom panel of Budget Distribution frame/window.
 */
public class TopPanelBDModel extends JPanel {

    //// define panel components to be used and placed into
    // just four labels
    JLabel codeAndServiceJL = new JLabel("ΚΩΔΙΚΟΣ - ΦΟΡΕΑΣ");
    JLabel regularBudgetJL = new JLabel("TΑΚΤΙΚΟΣ ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ");
    JLabel publicInvestmentsBudgetJL = new JLabel("ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ ΔΗΜΟΣΙΩΝ ΕΠΕΝΔΥΣΕΩΝ");
    JLabel generalTotalJL = new JLabel("ΓΕΝΙΚΟ ΣΥΝΟΛΟ");
    /**
     * Creates a new panel using certain components.
     * @param dataFilePath absolute path to data file
     * @throws IOException I/O error exception
     */
    public TopPanelBDModel(String dataFilePath) throws IOException {

        // configure panel layout
        this.setLayout(new GridLayout(1, 4, 5, 5));

        // place components on panel
        this.add(codeAndServiceJL);
        this.add(regularBudgetJL);
        this.add(publicInvestmentsBudgetJL);
        this.add(generalTotalJL);
    }
}
