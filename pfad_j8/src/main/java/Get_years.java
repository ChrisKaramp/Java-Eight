import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Get_years {
    
    public static String[] Get_years_into_strings_array (String dataFilePath) throws IOException {
        // create empty array of strings to be used as year drop down menu data
        ArrayList<String> yearsList = new ArrayList<>();

        // fetch all data from file (sorted)
        File_data_getter fileDataGetter = new File_data_getter(dataFilePath);
        List<List<String>> dataFileRecords = fileDataGetter.getDataFileRecords();

        // for each record fetched
        for (List<String> dataFileRecord : dataFileRecords) {
            // for each string in record
            for (String recordYear : dataFileRecord) {
                // if it is not headers record
                if (dataFileRecords.indexOf(dataFileRecord) > 0) {
                    // if it is the first value, it's a year value
                    if (dataFileRecord.indexOf(recordYear) == 0) {
                        yearsList.add(recordYear);
                    }
                }
            }
        }
        // convert ArrayList to String[]
        return yearsList.toArray(String[]::new);
    }
}
