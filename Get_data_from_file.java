import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Get_data_from_file {

    public static List<List<String>> dataFileRecords = new ArrayList<>();

    // fetches all data from file AND sorts them into an array
    // sorted data array can be used to sort data file as well
    public static void fetch_all_data(String dataFilePath) throws IOException {

        try (BufferedReader dataFileBufferedReader = new BufferedReader(new FileReader(dataFilePath))) {
            String dataFileLine;
            while ((dataFileLine = dataFileBufferedReader.readLine()) != null) {
                String[] year_values = dataFileLine.split(Constants.COMMA_DELIMITER);
                dataFileRecords.add(Arrays.asList(year_values));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data file.");
            throw e;
        }

        // print before sorting
        System.out.println(dataFileRecords);
        // sort by year descending
        dataFileRecords.sort((year2, year1) -> year1.get(0).compareTo(year2.get(0)));

        // print after sorting
        for (List<String> dataFileRecord : dataFileRecords) {
           System.out.println(dataFileRecord);
        }
        
    }

}