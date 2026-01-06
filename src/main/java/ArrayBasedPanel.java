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

/**
 * Creates a panel using arrays to hold components.
 * Uses 1+N gridcolumns algorithm.
 */
public class ArrayBasedPanel extends JPanel {

    private final CalculationsSpinner[] listeningSpinnersToBeCreated;
    private final JLabel[] jlabelsToBeCreated;
    private final int panelGridColumns;
    
    /**
     * Creates components and places them on panel using an array.
     * Gets data from array defined in Constants.java class.
     * Uses arguments that provide limits, column numbers and more information.
     * @param dataFilePath
     * @param fromArrayPosition
     * @param toArrayPosition
     * @param gridColumns
     * @param labelComponentsFirst
     * @param panelsToBeControlled
     * @param panelContainingYear
     * @throws IOException
     */
    public ArrayBasedPanel(String dataFilePath, int fromArrayPosition, int toArrayPosition, int gridColumns, boolean labelComponentsFirst, ArrayList<ArrayBasedPanel> panelsToBeControlled, TopPanelBOModel panelContainingYear) throws IOException {
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
        InteractiveButton[] listeningButtonsToBeCreated = new InteractiveButton[restComponentsRowNumber];
        listeningSpinnersToBeCreated = new CalculationsSpinner[restComponentsRowNumber];

        // fill component arrays
        for (int i = restComponentsPositionFrom; i < restComponentsPositionTo; i++) {
            listeningButtonsToBeCreated[i-restComponentsPositionFrom]
                = new InteractiveButton 
                    (Constants.DATA_FILE_HEADERS[i], "mass update", this, dataFilePath, panelsToBeControlled, panelContainingYear);
            listeningSpinnersToBeCreated[i-restComponentsPositionFrom]
                = new CalculationsSpinner(gridColumns, this, panelsToBeControlled);
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

    public CalculationsSpinner[] getListeningSpinnersToBeCreated() {
        return listeningSpinnersToBeCreated;
    }

    public JLabel[] getJlabelsToBeCreated() {
        return jlabelsToBeCreated;
    }

    public int getPanelGridColumns() {
        return panelGridColumns;
    }

    public void initShortcut(ArrayBasedPanel thisPanel, String actionName, KeyStroke definedKeyStroke) {
        JRootPane root = SwingUtilities.getRootPane(thisPanel);
        if (root != null) {
            root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(definedKeyStroke, actionName);
            root.getActionMap().put(actionName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (CalculationsSpinner currentListeninSpinner : listeningSpinnersToBeCreated){
                        currentListeninSpinner.setValue(0);
                    }
                }
            });
        }
    }
}
