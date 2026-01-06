package gr.aueb.javaeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

/**
 * Keeps data file and year selection menu sorted and updated while adding a new year.
 */
public class YearAdder {
    /**
     * Adds a new year into a data file and, subsequently into a combobox structure. 
     * @param dataFilePath absolute path to data file
     * @param yearEntered year entered
     * @param somePanel panel to be controlled
     * @throws IOException I/O error exception
     */
    public static void addYearIntoDataFile(String dataFilePath, String yearEntered, Object somePanel) throws IOException {
        // check if year already exists
        if ( Arrays.asList(YearProvider.GetYearsIntoStringsArray(dataFilePath)).contains(yearEntered) ) {
            JOptionPane.showMessageDialog(null, yearEntered + " --> Year already exists in data file");
        } else {
            // append, NOT write over
            final boolean appendFlag = true;

            //// form a new year record with zero values
            // add year, then add as many zeros as values (fields)
            List<String> newYearRecord = new ArrayList<>();
            newYearRecord.add(yearEntered);
            for (int i = 1; i < Constants.DATA_FILE_HEADERS.length; i++) {
                newYearRecord.add("0");
            }
            // set and add delimiter in record
            String newYearRecordWithDelimiter = newYearRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));

            // add new year record to data file
            FileRecordAdder.writeRecord(dataFilePath, newYearRecordWithDelimiter, appendFlag);

            // Sort all data file by doing a mass update
            DataSortAndSaveService.WriteSortedDataIntoFile(dataFilePath);

            //// add new year into year selection drop down menu SORTED
            // empty year selection drop down menu
            ((TopPanelBOModel) somePanel).getYearSelectLCB().removeAllItems();
            // refill year selection drop down menu
            for ( String year : YearProvider.GetYearsIntoStringsArray(dataFilePath)) {
                ((TopPanelBOModel) somePanel).getYearSelectLCB().addItem(year);
            }
            // notify user
            JOptionPane.showMessageDialog
                (null, yearEntered + " --> Year added successfully.");
        }
    }
}
