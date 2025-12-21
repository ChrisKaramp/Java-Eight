import java.io.IOException;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;


public class Bottom_panel_bo_model extends JPanel {
    
    //// define panel components to be used and placed into
    // budget result components
    String budgetResult = "EUR" + " 0.00";
    JLabel budgetResultJL = new JLabel("Αποτέλεσμα Κρατικού Προϋπολογισμού (Έσοδα - Έξοδα):");
    JLabel showBudgetResultJL = new JLabel(budgetResult);
    String budgetResultCover = "EUR" + " 0.00";
    // budget cover components
    JLabel budgetResultCoverJL = new JLabel("Κάλυψη με Ταμειακά Διαθέσιμα:");
    JLabel showbudgetResultCoverJL = new JLabel(budgetResultCover);

    // panel object constuctor
    public Bottom_panel_bo_model (String dataFilePath) throws IOException {
                // configure panel layout
        this.setLayout(new GridLayout(2, 2, 5, 5));

        // set soft purple background (same as titles)
        Color softPurple = new Color(230, 224, 240);
        this.setBackground(softPurple);

        // make labels opaque so background shows
        budgetResultJL.setOpaque(true);
        showBudgetResultJL.setOpaque(true);
        budgetResultCoverJL.setOpaque(true);
        showbudgetResultCoverJL.setOpaque(true);

        budgetResultJL.setBackground(softPurple);
        showBudgetResultJL.setBackground(softPurple);
        budgetResultCoverJL.setBackground(softPurple);
        showbudgetResultCoverJL.setBackground(softPurple);

        // place components on panel
        this.add(budgetResultJL);
        this.add(showBudgetResultJL);
        this.add(budgetResultCoverJL);
        this.add(showbudgetResultCoverJL);

    }
}
