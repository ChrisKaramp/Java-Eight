import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Get_data_from_file {

    // define a list of records for use with .csv data file
    public static List<List<String>> dataFileRecords = new ArrayList<>();
    // define a 2d array of the same data as above for use with widgets
    public static String[][] dataFile2D;

    // fetches all data from file AND sorts them into a list
    // sorted data list can be used to sort data file as well
    // sorted data array is produced from sorted data list
    public static void fetch_all_data(String dataFilePath) throws IOException {

        System.out.println("fetch_all_data");

        dataFileRecords.clear();
        
        try (BufferedReader dataFileBufferedReader = new BufferedReader(new FileReader(dataFilePath))) {
            String dataFileLine;
            while ((dataFileLine = dataFileBufferedReader.readLine()) != null) {
                String[] year_values = dataFileLine.split(Constants.COMMA_DELIMITER);
                dataFileRecords.add(Arrays.asList(year_values));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data file.");
            JOptionPane.showMessageDialog(null,"An error occurred while reading the data file.");
            throw e;
        }

        // sort by year descending
        dataFileRecords.sort((year2, year1) -> year1.get(0).compareTo(year2.get(0)));

        // build sorted 2d array of data using list above
        dataFile2D = new String[dataFileRecords.size()][];
        int i = 0;
        for (List<String> dataFileRecord : dataFileRecords) {
            dataFile2D[i++] = dataFileRecord.toArray(String[]::new);
        }
    }
}