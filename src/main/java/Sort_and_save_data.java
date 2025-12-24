
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Sort_and_save_data {
    
    public static void write_sorted_data_into_file(String dataFilePath) throws IOException {

        // print test data
        //System.out.println("SORT AND SAVE DATA IN FILE");

        // write over, NOT append
        boolean appendFlag = false;
        
        // fetch all data from file (sorted)
        File_data_getter fileDataGetter = new File_data_getter(dataFilePath);
        List<List<String>> dataFileRecords = fileDataGetter.getDataFileRecords();

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
    }

}
