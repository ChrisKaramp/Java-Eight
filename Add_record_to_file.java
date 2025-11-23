import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Add_record_to_file {
    
    public static void append_record(String datafilepath, String datarecord) throws IOException {

        try ( FileWriter record_FileWriter = new FileWriter(datafilepath, true); ) {
            record_FileWriter.append(datarecord + "\n");
            record_FileWriter.close();
            System.out.println("appended:" + datarecord);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
        System.out.println("Could not write to file. Check if it really exists.");
        }
    }
}
