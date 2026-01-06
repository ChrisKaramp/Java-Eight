package gr.aueb.javaeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides other classes with years existing in a data file
 */
public class YearProvider {
    /**
     * 
     * @param dataFilePath absolute path to data file
     * @return an array of strings containing years
     * @throws IOException I/O error exception
     */
    public static String[] GetYearsIntoStringsArray (String dataFilePath) throws IOException {
        // create empty array of strings to be used as year drop down menu data
        ArrayList<String> yearsList = new ArrayList<>();

        // fetch all data from file (sorted)
        FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
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
