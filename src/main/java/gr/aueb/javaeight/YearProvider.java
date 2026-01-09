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
        for (int i = 1; i < dataFileRecords.size(); i++) {
            List<String> dataFileRecord = dataFileRecords.get(i);
            // first value of record is year
            String year = dataFileRecord.get(0);
            yearsList.add(year);
        }
        // convert ArrayList to String[]
        return yearsList.toArray(String[]::new);
    }
}
