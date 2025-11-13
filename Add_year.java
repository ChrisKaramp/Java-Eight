import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Add_year {

    public static void add_year_into_data_file(String dataFilePath, String yearEntered) throws IOException {
        
    // form a new year record with zero values
    // add year, then add as many zeros as values (fields)
    List<String> newYearRecord = new ArrayList<>();
    newYearRecord.add(yearEntered);
    for (int i = 1; i < Constants.DATA_FILE_HEADERS.length; i++) {
        newYearRecord.add("0");
    }
    // set and add delimiter in record
    String newYearRecordWithDelimiter = newYearRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));

    // add new year record to data file
    Add_record_to_file.append_record(dataFilePath, newYearRecordWithDelimiter);

    // Sort all data file by doing a mass update
    Mass_update.write_sorted_data_into_file(dataFilePath);
    }
}
