import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Build_data_file {
    
    public static String create_data_file(String datafilepath) {
        try {
            File mydatafile = new File(datafilepath);
            if (mydatafile.createNewFile()) {
                System.out.println("File created: " + mydatafile.getName());
                System.out.println("File path: " + mydatafile.getAbsolutePath());
                return "created";
            } else {
                System.out.println("File already exists.");
                System.out.println("File path: " + mydatafile.getAbsolutePath());
                return "exists";
            }
        } catch (IOException e) {
        System.out.println("An error occurred. File was not created.");
        return "error";
        }
    }

    public static void initialize_data_file(String datafilepath) throws IOException {

    // get year of now
    int year_of_now_int = Year.now().getValue();
    String year_of_now_string = Integer.toString(year_of_now_int);

    // form TWO data records (headers and values) to add to empty data file

    // form headers record first
    List<String> headers_record_araylist = new ArrayList<>();
    headers_record_araylist.addAll(Arrays.asList(Constants.DATA_FILE_HEADERS));
    // set and add delimiter in headers record
    String headers_record = headers_record_araylist.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
    System.out.println(headers_record);
    
    // form values record now
    // add year, then add as many zeros as headers (fields)
    List<String> values_record_araylist = new ArrayList<>();
    values_record_araylist.add(year_of_now_string);
    for (int i = 1; i < Constants.DATA_FILE_HEADERS.length; i++) {
        values_record_araylist.add("0");
    }
    // set and add delimiter in record
    String values_record = values_record_araylist.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
    System.out.println(values_record);

    // add headers record to data file
    Add_record_to_file.append_record(datafilepath, headers_record);
    // add values record to data file
    Add_record_to_file.append_record(datafilepath, values_record);
    }
}
