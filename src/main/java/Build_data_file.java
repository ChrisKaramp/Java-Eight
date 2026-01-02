import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Build_data_file {
    
    public static String create_data_file(String dataFilePath) {
        try {
            File dataFile = new File(dataFilePath);
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getName());
                System.out.println("File path: " + dataFile.getAbsolutePath());
                return "created";
            } else {
                return "exists";
            }
        } catch (IOException e) {
        System.out.println("An error occurred. File was not created.");
        return "error";
        }
    }

    public static void initialize_data_file(String dataFilePath) throws IOException {
    
    // append, NOT write over
    final boolean appendFlag = true;

    // get year of now
    int yearOfNowAsInt = Year.now().getValue();
    String yearOfNowAsString = Integer.toString(yearOfNowAsInt);

    // form TWO data records (headers and values) to add to empty data file

    // form headers record first
    List<String> headersRecord = new ArrayList<>();
    headersRecord.addAll(Arrays.asList(Constants.DATA_FILE_HEADERS));
    // set and add delimiter in headers record
    String headersRecordWithDelimiter = headersRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
    System.out.println(headersRecordWithDelimiter);
    
    // form values record now
    // add year, then add as many zeros as headers (fields)
    List<String> valuesRecord = new ArrayList<>();
    valuesRecord.add(yearOfNowAsString);
    for (int i = 1; i < Constants.DATA_FILE_HEADERS.length; i++) {
        valuesRecord.add("0");
    }
    // set and add delimiter in record
    String valuesRecordWithDelimiter = valuesRecord.stream().collect(Collectors.joining(Constants.COMMA_DELIMITER));
    System.out.println(valuesRecordWithDelimiter);

    // add headers record to data file
    Add_record_to_file.write_record(dataFilePath, headersRecordWithDelimiter, appendFlag);
    // add values record to data file
    Add_record_to_file.write_record(dataFilePath, valuesRecordWithDelimiter, appendFlag);
    }
}
