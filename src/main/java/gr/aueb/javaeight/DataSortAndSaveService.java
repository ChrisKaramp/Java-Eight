package gr.aueb.javaeight;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorts and saves data into data file.
 * Can be used by any class and be handful.
 */
public class DataSortAndSaveService {
    /**
     * Gets data from file.
     * Sorts data in a structure.
     * Rewrites headers record (to re-write file), but appends rest value records.
     * @param dataFilePath absolute path to data file
     * @throws IOException I/O error exception
     */
    public static void WriteSortedDataIntoFile(String dataFilePath) throws IOException {

        // write over, NOT append
        boolean appendFlag = false;
        
        // fetch all data from file (sorted)
        FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
        List<List<String>> dataFileRecords = fileDataGetter.getDataFileRecords();

        // for each record fetched
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
    }

}
