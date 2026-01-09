package gr.aueb.javaeight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Picks between three different types of buttons.
 */
public class ButtonHandler implements ActionListener {
    
    private String buttonType;
    private Object somePanel;
    private String dataFilePath;
    private ArrayList<ArrayBasedPanel> panelsToBeRun;
    private TopPanelBOModel panelContainingYear;
    
    /**
     * Picks button type using a switch/case structure and passes parameters.
     * Safe by default case.
     * @param bt button type
     * @param sp panel to be controlled
     * @param dfp absolute path to data file
     * @param ptbr panels to be run
     * @param pcy panel containing selected year
     */
    public ButtonHandler(String bt, Object sp, String dfp, ArrayList<ArrayBasedPanel> ptbr, TopPanelBOModel pcy) {
        setButtonType(bt);
        setSomePanel(sp);
        setDataFilePath(dfp);
        setPanelsToBeRun(ptbr);
        setPanelContainingYear(pcy);
  	}   

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (getButtonType()) {
                case "add year" -> YearAdder.addYearIntoDataFile
                        (getDataFilePath(), getPanelContainingYear().getYearAddJTF(),
                         getSomePanel());
                case "mass update" -> MassUpdater.writeSortedDataIntoFile
                        (getDataFilePath(), getPanelsToBeRun(), getPanelContainingYear());
                case "launch budget distribution" -> { 
                    BudgetDistributionWindowModel budgetDistributionWindow
                        = new BudgetDistributionWindowModel
                        (Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, getDataFilePath(), getSomePanel());
                    budgetDistributionWindow.setTitle
                        ("Κατανομή Κρατικού Προϋπολογισμού (Ctrl 1/2/3 --> reset) - Επιτρεπόμενες τιμές: " + Integer.MIN_VALUE + " έως " + Integer.MAX_VALUE);
                }
                default -> JOptionPane.showMessageDialog
                        (null, getButtonType());
            }
            } catch (IOException ex) {
                System.out.println("An error occurred while adding year in data file. Exit code: 1.");
                System.exit(1);
            }
    }   

    private String getButtonType() {
        return buttonType;
    }

    private void setButtonType(String bt) {
        this.buttonType = bt;
    }

    private Object getSomePanel() {
        return somePanel;
    }

    private void setSomePanel(Object sp) {
        this.somePanel = sp;
    }

    private String getDataFilePath() {
        return dataFilePath;
    }

    private void setDataFilePath(String dfp) {
        this.dataFilePath = dfp;
    }

    public ArrayList<ArrayBasedPanel> getPanelsToBeRun() {
        return panelsToBeRun;
    }

    private void setPanelsToBeRun(ArrayList<ArrayBasedPanel> panelsToBeRun) {
        this.panelsToBeRun = panelsToBeRun;
    }

    public TopPanelBOModel getPanelContainingYear() {
        return panelContainingYear;
    }

    private void setPanelContainingYear(TopPanelBOModel panelContainingYear) {
        this.panelContainingYear = panelContainingYear;
    }
}   