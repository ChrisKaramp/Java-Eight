import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Add_record_to_file {
    
    public static void write_record(String dataFilePath, String dataRecord, boolean appendORWriteFlag) throws IOException {

        try ( FileWriter recordFileWriter = new FileWriter(dataFilePath, appendORWriteFlag); ) {
            recordFileWriter.append(dataRecord + "\n");
            recordFileWriter.close();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
        // something went wrong, notify user
        System.out.println("Could not write to file." + dataFilePath + "Check if it really exists.");
        JOptionPane.showMessageDialog
            (null, "Could not write to file." + dataFilePath + "Check if it really exists.");
        }
    }
}