
import java.io.IOException;
import java.util.List;

public class Mass_update {
    
    public static void write_sorted_data_into_file(String dataFilePath) throws IOException {
        // fetch all data from file, sort and re-write them
        Get_data_from_file.fetch_all_data(Constants.DATA_FILE_PATH);

        for (List<String> dataFileRecord : Get_data_from_file.dataFileRecords) {
           System.out.println(dataFileRecord);
        }
    }

}
