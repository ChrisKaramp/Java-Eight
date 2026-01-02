import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Add_year {

    public static void add_year_into_data_file(String dataFilePath, String yearEntered, Object somePanel) throws IOException {
        // check if year already exists
        if ( Arrays.asList(Get_years.Get_years_into_strings_array(dataFilePath)).contains(yearEntered) ) {
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
            Add_record_to_file.write_record(dataFilePath, newYearRecordWithDelimiter, appendFlag);

            // Sort all data file by doing a mass update
            Sort_and_save_data.write_sorted_data_into_file(dataFilePath);

            //// add new year into year selection drop down menu SORTED
            // empty year selection drop down menu
            ((Top_panel_bo_model) somePanel).getYearSelectLCB().removeAllItems();
            // refill year selection drop down menu
            for ( String year : Get_years.Get_years_into_strings_array(dataFilePath)) {
                ((Top_panel_bo_model) somePanel).getYearSelectLCB().addItem(year);
            }
            // notify user
            JOptionPane.showMessageDialog
                (null, yearEntered + " --> Year added successfully.");
        }
    }
}
