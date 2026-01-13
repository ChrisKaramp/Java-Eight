package gr.aueb.javaeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/**
 * Performs mass update of all values in GUI.
 * Can be used for "horizontal" update as well.
 */
public class MassUpdater {
    /**
     * Gets current data from file into structure.
     * Gets current values from components.
     * Puts these values in structure.
     * Updates proper record according to selected year.
     * Rewrites updated data into file.
     * Notifies user in case of any failure.
     * @param dataFilePath absolute path to data file
     * @param panelsToBeRun panels to be run
     * @param panelWithYearSelectLCB a reference to year selection panel
     * @throws IOException I/O error exception
     */
    public static void writeSortedDataIntoFile(String dataFilePath, ArrayList<ArrayBasedPanel> panelsToBeRun, TopPanelBOModel panelWithYearSelectLCB) throws IOException {
        // get selected year from year selection combobox in top panel
        String yearSelected = (String) panelWithYearSelectLCB.getYearSelectYSC().getSelectedItem();

        // write over, NOT append
        boolean appendFlag = false;
        
        // fetch all data from file into a list (sorted)
        FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
        List<List<String>> dataFileRecords = fileDataGetter.getDataFileRecords();

        // from dataFileRecords, fetch (one) record that matches selected year
        List<String> matchingRecord = new ArrayList<>();
        for (int recordIndex = 1; recordIndex < dataFileRecords.size(); recordIndex++) {
            List<String> dataFileRecord = dataFileRecords.get(recordIndex);
            if ( dataFileRecord.get(0).equalsIgnoreCase(yearSelected) ) {
                matchingRecord = dataFileRecord;
            }
        }

        // get data from widgets
        PanelValuesProvider widgetGetter =
            new PanelValuesProvider(panelsToBeRun, yearSelected);
        String[] halfDataFile1D;
        halfDataFile1D = widgetGetter.getDataFile1D();
        
        if (halfDataFile1D != null) {
            for (int halfIndex = 0; halfIndex < halfDataFile1D.length; halfIndex++) {
                if ( halfDataFile1D[halfIndex] != null ) {
                    matchingRecord.set(halfIndex, halfDataFile1D[halfIndex]);
                }
            }
        } else {
            System.out.println("DATA FILE 1D ARRAY FROM WIDGET GETTER IS NULL.");
            JOptionPane.showMessageDialog
                (null, "Data from widget getter is null",
                "Error", JOptionPane.ERROR_MESSAGE);
        }

        // write all data into data file, replacing old data
        for (List<String> dataFileRecord : dataFileRecords) {
            // set and add delimiter in record
            String dataFileRecordWithDelimiter = dataFileRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
            // append or write (and replace) data record into data file
            FileRecordAdder.writeRecord(dataFilePath, dataFileRecordWithDelimiter, appendFlag);
            // at the end of headers record, switch from "write" to "append"
            if (dataFileRecords.indexOf(dataFileRecord) == 0) {
                appendFlag = true;
            }
        }

        // for every panel
        for (ArrayBasedPanel panel : panelsToBeRun) {
            // and spinner on this panel
            for (CalculationsSpinner spinner : panel.getListeningSpinnersToBeCreated()) {
                // reset spinner color when mass update pressed
                // avoid casting to jspinner
                JComponent editor = spinner.getEditor();
                if (editor instanceof JSpinner.DefaultEditor def) {
                    JFormattedTextField tf = def.getTextField();
                    tf.setBackground(null);
                    tf.setForeground(null);
                }
            }
        }
    }
}
