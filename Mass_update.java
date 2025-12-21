
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Mass_update {
    
    public static void write_sorted_data_into_file(String dataFilePath, ArrayList<Panel_using_array> panelsToBeRan, Top_panel_bo_model panelWithYearSelectLCB) throws IOException {
        // get selected year from year selection combobox in top panel
        String yearSelected = (String) panelWithYearSelectLCB.getYearSelectLCB().getSelectedItem();

        // write over, NOT append
        boolean appendFlag = false;
        
        // fetch all data from file (sorted)
        File_data_getter fileDataGetter = new File_data_getter(dataFilePath);
        List<List<String>> dataFileRecords = fileDataGetter.getDataFileRecords();
        //String[][] dataFile2D = fileDataGetter.getDataFile2D();

        // for each record fetched
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

        // TEST FOR Widget_getter class

        Widget_getter widgetGetter = new Widget_getter(panelsToBeRan, yearSelected);
        String[] dataFile1D = widgetGetter.getDataFile1D();
        
        if (dataFile1D != null) {
            System.out.println("DATA FILE 1D FROM WIDGET GETTER:");
            for (int jj = 0; jj < dataFile1D.length; jj++) {
                System.out.println("element " + jj + ": " + dataFile1D[jj]);
            }
        } else {
            System.out.println("DATA FILE 1D ARRAY FROM WIDGET GETTER IS NULL.");
            JOptionPane.showMessageDialog
                (null,
                "Data from widget getter is null",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

}

/*
            for (int ii = 0; ii < dataFile2D.length; ii++) {
                System.out.println("----------------------");
                System.out.println("record: " + ii);
                for (int jj = 0; jj < dataFile2D[ii].length; jj++) {
                    System.out.print(dataFile2D[ii][jj] + " ");
                }
                System.out.println();
            }
*/