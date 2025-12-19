import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Button_handler implements ActionListener {

    private String buttonType;
    private Object somePanel;
    private String dataFilePath;
    private ArrayList<Panel_using_array> panelsToBeRan;
    private Top_panel_bo_model panelContainingYear;

    public Button_handler(String bt, Object sp, String dfp, ArrayList<Panel_using_array> ptbr, Top_panel_bo_model pcy) {
        setButtonType(bt);
        setSomePanel(sp);
        setDataFilePath(dfp);
        setPanelsToBeRan(ptbr);
        setPanelContainingYear(pcy);
  	}   

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (getButtonType()) {
                case "add year" -> Add_year.add_year_into_data_file
                        (getDataFilePath(), Top_panel_bo_model.yearaddJTF.getText(),
                         getSomePanel());
                case "mass update" -> Mass_update.write_sorted_data_into_file
                        (getDataFilePath(), getPanelsToBeRan(), getPanelContainingYear());
                case "launch budget distribution" -> { 
                    Budget_distribution_window_model budgetDistributionWindow
                        = new Budget_distribution_window_model
                        (Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, getDataFilePath(), getSomePanel());
                    budgetDistributionWindow.setTitle
                        ("Κατανομή Κρατικού Προϋπολογισμού");
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

    public ArrayList<Panel_using_array> getPanelsToBeRan() {
        return panelsToBeRan;
    }

    private void setPanelsToBeRan(ArrayList<Panel_using_array> panelsToBeRan) {
        this.panelsToBeRan = panelsToBeRan;
    }

    public Top_panel_bo_model getPanelContainingYear() {
        return panelContainingYear;
    }

    private void setPanelContainingYear(Top_panel_bo_model panelContainingYear) {
        this.panelContainingYear = panelContainingYear;
    }
}   