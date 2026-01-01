import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Panel_using_array extends JPanel {

    // create a panel using arrays to hold components

    private final Listening_spinner[] listeningSpinnersToBeCreated;
    private final JLabel[] jlabelsToBeCreated;
    private final int panelGridColumns;

    public Panel_using_array(String dataFilePath, int fromArrayPosition, int toArrayPosition, int gridColumns, boolean labelComponentsFirst, ArrayList<Panel_using_array> panelsToBeControlled, Top_panel_bo_model panelContainingYear) throws IOException {
        // define rows in grid number
        final int componentsRowNumber = toArrayPosition - fromArrayPosition;
        final int gridRows = componentsRowNumber / (gridColumns-1);
        this.panelGridColumns = gridColumns;

        // set layout
        this.setLayout(new GridLayout(gridRows, gridColumns));

        // define rest components positions in array
        int restComponentsPositionFrom = fromArrayPosition;
        int restComponentsPositionTo = toArrayPosition;
        int restComponentsRowNumber = componentsRowNumber;

        // create label array - used under condition below
        jlabelsToBeCreated = new JLabel[gridColumns];

        // check if label components are placed first
        if (labelComponentsFirst) {
            // fill label array (1 + (gridColumns-1) labels)
            // first label indicates the data field name
            jlabelsToBeCreated[0] = new JLabel(Constants.DATA_FILE_HEADERS[fromArrayPosition]);
            // rest labels indicate values
            for (int lbc = 1; lbc < gridColumns; lbc++) {
                jlabelsToBeCreated[lbc] = new JLabel("0");
            }
            // place label components at panel start
            for (int lbp = 0; lbp < gridColumns; lbp++) {
                this.add(jlabelsToBeCreated[lbp]);
            }
            // re-define rest components positions in array
            restComponentsPositionFrom = fromArrayPosition + gridColumns-1;
            restComponentsRowNumber = componentsRowNumber - (gridColumns-1);
        }

        // declare rest (listening buttons and jspinners) component arrays
        Listening_button[] listeningButtonsToBeCreated = new Listening_button[restComponentsRowNumber];
        listeningSpinnersToBeCreated = new Listening_spinner[restComponentsRowNumber];

        // fill component arrays
        for (int i = restComponentsPositionFrom; i < restComponentsPositionTo; i++) {
            listeningButtonsToBeCreated[i-restComponentsPositionFrom]
                = new Listening_button 
                    (Constants.DATA_FILE_HEADERS[i], "mass update", this, dataFilePath, panelsToBeControlled, panelContainingYear);
            listeningSpinnersToBeCreated[i-restComponentsPositionFrom]
                = new Listening_spinner(gridColumns, this, panelsToBeControlled);
        }

        // place components on panel using arrays
        for (int j = 0; j < restComponentsRowNumber; j = j + gridColumns - 1) {
            // place ONE listening button
            this.add(listeningButtonsToBeCreated[j]);
            // place ONE OR MORE spinners, according to gridColumns
            for (int k = j; k < j + gridColumns - 1; k++) {
                this.add(listeningSpinnersToBeCreated[k]);
            }
        }

    }

    public Listening_spinner[] getListeningSpinnersToBeCreated() {
        return listeningSpinnersToBeCreated;
    }

    public JLabel[] getJlabelsToBeCreated() {
        return jlabelsToBeCreated;
    }

    public int getPanelGridColumns() {
        return panelGridColumns;
    }

    public void initShortcut(Panel_using_array thisPanel, String actionName, KeyStroke definedKeyStroke) {
        JRootPane root = SwingUtilities.getRootPane(thisPanel);
        if (root != null) {
            root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(definedKeyStroke, actionName);
            root.getActionMap().put(actionName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(definedKeyStroke + " PRESSED");
                    for (Listening_spinner currentListeninSpinner : listeningSpinnersToBeCreated){
                        currentListeninSpinner.setValue(0);
                    }
                }
            });
        }
    }
}
