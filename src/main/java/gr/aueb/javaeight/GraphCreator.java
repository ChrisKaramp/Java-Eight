package gr.aueb.javaeight;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Creates charts of different types using data from .csv file.
 * Pie chart for Budget Origin window.
 * Bar chart for Budget Distribution window. <----------
 * Also picks proper font for greek characters representation.
 */
public class GraphCreator {

    /**
     * Creates a PIE chart for the Budget Origin window.
     * @param dataFilePath absolute path to data file
     * @param yearSelected currently selected year in menu (combobox)
     * @throws IOException I/O error exception
     */
    public static void createPieChart(String dataFilePath, String yearSelected) throws IOException {

        // read file data into a two dimensional array of strings
        FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
        String[][] fileData2D = fileDataGetter.getDataFile2D();

        // fetch currently selected year record
        String[] selectedRecord = null;
        for (String[] record : fileData2D) {
            if (record[0].equals(yearSelected)) {
                selectedRecord = record;
                break;
            }
        }

        // notify user if year not found in records
        if (selectedRecord == null) {
            System.out.println("Year not found in data file: " + yearSelected);
            JOptionPane.showMessageDialog(null, "Year not found in data file: " + yearSelected,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // create frame
        JFrame frame = new JFrame(
                yearSelected
                        + " - Προέλευση Κρατικού Προϋπολογισμού (Currently Saved Data)");
        // frame should not close any other windows
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set full screen for frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame hosts two panels, each panel a pie chart
        frame.setLayout(new GridLayout(1, 2));

        // left panel
        PieChartPanel leftPiePanel = new PieChartPanel(
                selectedRecord,
                Constants.BO_LEFT_PANEL_START,
                Constants.BO_RIGHT_PANEL_START);
        frame.add(leftPiePanel);

        // right panel
        PieChartPanel rightPiePanel = new PieChartPanel(
                selectedRecord,
                Constants.BO_RIGHT_PANEL_START,
                Constants.BO_BOTTOM_PANEL_START);
        frame.add(rightPiePanel);

        frame.setVisible(true);
    }

    /**
     * Creates a BAR chart for the Budget Distribution window.
     * @param dataFilePath absolute path to data file
     * @param yearSelected currently selected year in menu (combobox)
     * @throws IOException I/O error exception
     */
    public static void createBarChart(String dataFilePath, String yearSelected) throws IOException {

    // read file data into a two dimensional array of strings
    FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
    String[][] fileData2D = fileDataGetter.getDataFile2D();

    // fetch currently selected year record
    String[] selectedRecord = null;
    for (String[] record : fileData2D) {
        if (record[0].equals(yearSelected)) {
            selectedRecord = record;
            break;
        }
    }

    // notify user if year not found in records
    if (selectedRecord == null) {
        System.out.println("Year not found in data file: " + yearSelected);
        JOptionPane.showMessageDialog(
                null,
                "Year not found in data file: " + yearSelected,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // create frame
    JFrame frame = new JFrame(
            yearSelected
            + " - Κατανομή Κρατικού Προϋπολογισμού (Currently Saved Data)"
    );

    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // left panel
    BarChartPanel leftPanel = new BarChartPanel(
            selectedRecord,
            Constants.BD_NESTED_PANEL1_START,
            Constants.BD_NESTED_PANEL2_START,
            "ΑΡΧΕΣ"
    );


    // middle panel
    String middleTitle =
            Constants.DATA_FILE_HEADERS[Constants.BD_NESTED_PANEL2_START].toUpperCase();

    BarChartPanel middlePanel = new BarChartPanel(
            selectedRecord,
            Constants.BD_NESTED_PANEL2_START,
            Constants.BD_NESTED_PANEL3_START,
            middleTitle
    );

    // right panel
    String rightTitle =
            Constants.DATA_FILE_HEADERS[Constants.BD_NESTED_PANEL3_START].toUpperCase();

    BarChartPanel rightPanel = new BarChartPanel(
            selectedRecord,
            Constants.BD_NESTED_PANEL3_START,
            Constants.BD_BOTTOM_PANEL_START,
            rightTitle
    );
    
    // set layout
    frame.setLayout(new GridBagLayout());

    // left panel 20%
    GridBagConstraints gbcLeft = new GridBagConstraints();
    gbcLeft.fill = GridBagConstraints.BOTH;
    gbcLeft.gridx = 0;
    gbcLeft.gridy = 0;
    gbcLeft.weightx = 0.2;
    gbcLeft.weighty = 1.0;
    gbcLeft.insets = new Insets(5,5,5,5);
    frame.add(leftPanel, gbcLeft);

    // middle panel 50%
    GridBagConstraints gbcMiddle = new GridBagConstraints();
    gbcMiddle.fill = GridBagConstraints.BOTH;
    gbcMiddle.gridx = 1;
    gbcMiddle.gridy = 0;
    gbcMiddle.weightx = 0.5;
    gbcMiddle.weighty = 1.0;
    gbcMiddle.insets = new Insets(5,5,5,5);
    frame.add(middlePanel, gbcMiddle);

    // right panel 30%
    GridBagConstraints gbcRight = new GridBagConstraints();
    gbcRight.fill = GridBagConstraints.BOTH;
    gbcRight.gridx = 2;
    gbcRight.gridy = 0;
    gbcRight.weightx = 0.3;
    gbcRight.weighty = 1.0;
    gbcRight.insets = new Insets(5,5,5,5);
    frame.add(rightPanel, gbcRight);

    frame.setVisible(true);
}

}
