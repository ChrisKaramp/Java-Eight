package gr.aueb.javaeight;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel used to be the bottom panel of Budget Origin frame/window.
 */
public class BottomPanelBOModel extends JPanel {
    /**
     * Creates a new panel using certain components.
     * @param dataFilePath absolute path to data file
     * @param panelsToBeControlled structure to provide control on panels
     * @throws IOException I/O error exception
     */    
    public BottomPanelBOModel (String dataFilePath, ArrayList<ArrayBasedPanel> panelsToBeControlled) throws IOException {
        // set panel layout
        this.setLayout(new GridLayout(2, 2, 2, 2));

        //// define panel components to be used and placed into
        // budget result components
        final JLabel budgetResultJL = new JLabel("Αποτέλεσμα Κρατικού Προϋπολογισμού (Έσοδα - Έξοδα):");
        JLabel showBudgetResultJL = new JLabel("EUR" + " 0.00");
        // budget cover components
        final JLabel budgetResultCoverJL = new JLabel("Κάλυψη με Ταμειακά Διαθέσιμα:");
        JLabel showbudgetResultCoverJL = new JLabel("EUR" + " 0.00");

        // calculate budget result
        Integer budgetResult = 
            Integer.valueOf(panelsToBeControlled.get(0).getJlabelsToBeCreated()[1].getText());
        // subtract right (expenses) subtotal
        budgetResult -=
            Integer.valueOf(panelsToBeControlled.get(1).getJlabelsToBeCreated()[1].getText());
                    
        Integer budgetResultCover = budgetResult * (-1);
        
        // initialize labels
        showBudgetResultJL.setText(budgetResult.toString());
        showbudgetResultCoverJL.setText(budgetResultCover.toString());

        // place components on panel
        this.add(budgetResultJL);
        this.add(showBudgetResultJL);
        this.add(budgetResultCoverJL);
        this.add(showbudgetResultCoverJL);
    }
}
