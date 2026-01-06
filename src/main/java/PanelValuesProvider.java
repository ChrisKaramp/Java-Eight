import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Gets values from panel components and uses them to update data file.
 * Triggered when a mass update button pressed.
 */
public class PanelValuesProvider {
    
    private String[] dataFile1D;
    
    /**
     * Runs panels, then panel components and gets their values if not header.
     * Executes complex algorithm, determining frame, panel and component type.
     * @param panelsToBeRan
     * @param yearSelected
     */
    public PanelValuesProvider(ArrayList<ArrayBasedPanel> panelsToBeRan, String yearSelected) {
        if (panelsToBeRan == null || panelsToBeRan.isEmpty()) {
            this.dataFile1D = null;
            JOptionPane.showMessageDialog
                (null,
                "No widgets found.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } else {
            this.dataFile1D = new String[Constants.ENDING_POINT];
            // first element is year selected
            this.dataFile1D[0] = yearSelected;
            setDataFile1D(dataFile1D);
            // Component Index Over All Panels (of this frame)
            int cioap = 0;
            // loop through panels
            for (int currentPanelIndex = 0;
                currentPanelIndex < panelsToBeRan.size();
                currentPanelIndex++) {
                // loop through components of the current panel
                // only count JLabels and Listening Spinners
                // don't count JButtons, they don't contain values
                // Component Index Over This Panel
                int ciotp = 0;
                for (Component component : panelsToBeRan.get(currentPanelIndex).getComponents()) {

                    // if JLabel
                    switch (component) {
                        case JLabel jlabel -> {
                            if (currentPanelIndex == 0) {
                                // this is a bottom panel with TOTAL or Title labels
                                if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 2) {
                                    // panel has 2 columns --> Budget Origin frame
                                    if (ciotp > 0) {
                                        // skip first label (description), get only total value labels
                                        this.dataFile1D[(cioap - 1) + Constants.BO_BOTTOM_PANEL_START] = jlabel.getText();
                                    }
                                } else if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 4) {
                                    // panel has 4 columns --> Budget Distribution frame
                                    if (ciotp > 0) {
                                        // skip first label (description), get only total value labels
                                        this.dataFile1D[(cioap - 1) + Constants.BD_BOTTOM_PANEL_START] = jlabel.getText();
                                    }
                                }
                                cioap++;
                                ciotp++;
                            } else { // not a bottom panel - labels contain SUBTOTALS or Titles
                                // if panel has 2 columns --> Budget Origin frame
                                if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 2) {                            
                                    if (ciotp > 0) {
                                    // skip first JLabel in panel (it's the title)
                                    dataFile1D[(cioap - 1) - currentPanelIndex] = jlabel.getText();
                                    }
                                } else if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 4) {
                                    // panel has 4 columns --> Budget Distribution frame
                                    if (ciotp > 0) {
                                    // skip first JLabel in panel (it's the title)
                                        if (currentPanelIndex == 2) {
                                            // nested panel 2
                                            dataFile1D[(ciotp + Constants.BD_NESTED_PANEL2_START) - 1] = jlabel.getText();
                                        } else if (currentPanelIndex == 3) {
                                            // nested panel 3
                                            dataFile1D[(ciotp + Constants.BD_NESTED_PANEL3_START) - 1] = jlabel.getText();
                                        }
                                    }
                                }   
                                cioap++;
                                ciotp++;
                            }
                        }
                        case CalculationsSpinner listening_spinner -> {
                            // if panel has 2 columns --> Budget Origin frame
                            if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 2) {                            
                                if (ciotp > 0) {
                                // skip first JLabel in panel (it's the title)
                                dataFile1D[(cioap - 1) - currentPanelIndex] = listening_spinner.getValue().toString();
                                }
                            } else if (panelsToBeRan.get(currentPanelIndex).getPanelGridColumns() == 4) {
                                // panel has 4 columns --> Budget Distribution frame
                                switch (currentPanelIndex) {
                                    case 1:
                                        // nested panel 1
                                        dataFile1D[(ciotp + Constants.BD_NESTED_PANEL1_START)] = listening_spinner.getValue().toString();
                                        break;
                                    case 2:
                                        // nested panel 2
                                        dataFile1D[(ciotp + Constants.BD_NESTED_PANEL2_START) - 1] = listening_spinner.getValue().toString();
                                        break;
                                    case 3:
                                        // nested panel 3
                                        dataFile1D[(ciotp + Constants.BD_NESTED_PANEL3_START) - 1] = listening_spinner.getValue().toString();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            cioap++;
                            ciotp++;
                        }
                        default -> {
                        }
                    }
                }
            }
            setDataFile1D(dataFile1D);
        }

    }

    public String[] getDataFile1D() {
        return dataFile1D;
    }

    private void setDataFile1D(String[] dataFile1D) {
        this.dataFile1D = dataFile1D;
    }
    
}
