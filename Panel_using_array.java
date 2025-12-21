import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;

public class Panel_using_array extends JPanel {

    // create a panel using arrays to hold components

    public Panel_using_array(String dataFilePath, int fromArrayPosition, int toArrayPosition, int gridColumns, boolean labelComponentsFirst) throws IOException {

        // define rows in grid number
        final int componentsRowNumber = toArrayPosition - fromArrayPosition;
        final int gridRows = componentsRowNumber / (gridColumns - 1);

        // set layout
        this.setLayout(new GridLayout(gridRows, gridColumns, 6, 6));

        // define rest components positions in array
        int restComponentsPositionFrom = fromArrayPosition;
        int restComponentsPositionTo = toArrayPosition;
        int restComponentsRowNumber = componentsRowNumber;

        // check if label components are placed first
        if (labelComponentsFirst) {

            // declare label array
            JLabel[] labelsToBeCreated = new JLabel[gridColumns];

            // fill label array (1 + (gridColumns-1) labels)
            // first label indicates the data field name
            labelsToBeCreated[0] = new JLabel(Constants.DATA_FILE_HEADERS[fromArrayPosition]);
            labelsToBeCreated[0].setOpaque(true);
            labelsToBeCreated[0].setBackground(new Color(230, 220, 240));
            labelsToBeCreated[0].setPreferredSize(new Dimension(0, 45));

            // rest labels indicate values
            for (int lbc = 1; lbc < gridColumns; lbc++) {
                labelsToBeCreated[lbc] = new JLabel("0");
                labelsToBeCreated[lbc].setOpaque(true);
                labelsToBeCreated[lbc].setBackground(new Color(230, 220, 240));
                labelsToBeCreated[lbc].setPreferredSize(new Dimension(0, 45));
            }

            // place label components at panel start
            for (int lbp = 0; lbp < gridColumns; lbp++) {
                this.add(labelsToBeCreated[lbp]);
            }

            // re-define rest components positions in array
            restComponentsPositionFrom = fromArrayPosition + gridColumns - 1;
            restComponentsRowNumber = componentsRowNumber - (gridColumns - 1);
        }

        // declare rest (listening buttons and jspinners) component arrays
        Listening_button[] listeningButtonsToBeCreated = new Listening_button[restComponentsRowNumber];
        JSpinner[] spinnersToBeCreated = new JSpinner[restComponentsRowNumber];

        // fill component arrays
        for (int i = restComponentsPositionFrom; i < restComponentsPositionTo; i++) {

            listeningButtonsToBeCreated[i - restComponentsPositionFrom]
                    = new Listening_button(
                            Constants.DATA_FILE_HEADERS[i],
                            "mass save"
                    );

            spinnersToBeCreated[i - restComponentsPositionFrom]
                    = new JSpinner(
                            new SpinnerNumberModel(
                                    0,
                                    Integer.MIN_VALUE,
                                    Integer.MAX_VALUE,
                                    1
                            )
                    );
        }

        // place components on panel using arrays
        for (int j = 0; j < restComponentsRowNumber; j = j + gridColumns - 1) {

            // place ONE listening button
            this.add(listeningButtonsToBeCreated[j]);

            // place ONE OR MORE spinners, according to gridColumns
            for (int k = j; k < j + gridColumns - 1; k++) {
                this.add(spinnersToBeCreated[k]);
            }
        }
    }
}