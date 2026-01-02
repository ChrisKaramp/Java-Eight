import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

public class Mass_update {
    
    //private static final String[] dataFile1D = new String[Constants.ENDING_POINT];

    public static void write_sorted_data_into_file(String dataFilePath, ArrayList<Panel_using_array> panelsToBeRan, Top_panel_bo_model panelWithYearSelectLCB) throws IOException {
        // get selected year from year selection combobox in top panel
        String yearSelected = (String) panelWithYearSelectLCB.getYearSelectLCB().getSelectedItem();

        // write over, NOT append
        boolean appendFlag = false;
        
        // fetch all data from file (sorted)
        File_data_getter fileDataGetter = new File_data_getter(dataFilePath);
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
        Widget_getter widgetGetter =
            new Widget_getter(panelsToBeRan, yearSelected);
        String[] halfDataFile1D;
        halfDataFile1D = widgetGetter.getDataFile1D();
        
        if (halfDataFile1D != null) {
            //System.out.println("DATA FILE 1D FROM WIDGET GETTER:");
            for (int halfIndex = 0; halfIndex < halfDataFile1D.length; halfIndex++) {
                //System.out.println("HALF element " + halfIndex + ": " + halfDataFile1D[halfIndex]);
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
            //System.out.println(dataFileRecord);
            // set and add delimiter in record
            String dataFileRecordWithDelimiter = dataFileRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
            // append or write (and replace) data record into data file
            Add_record_to_file.write_record(dataFilePath, dataFileRecordWithDelimiter, appendFlag);
            // at the end of headers record, switch from "write" to "append"
            if (dataFileRecords.indexOf(dataFileRecord) == 0) {
                appendFlag = true;
            }
        }

        // for every panel
        for (Panel_using_array panel : panelsToBeRan) {
            // and spinner on this panel
            for (Listening_spinner spinner : panel.getListeningSpinnersToBeCreated()) {
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
