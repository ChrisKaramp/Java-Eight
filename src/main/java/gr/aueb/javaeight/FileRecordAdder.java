package gr.aueb.javaeight;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Adds a record to a data file.
 * Either overwires, or appends, depending on a certain flag-like argument.
 */
public class FileRecordAdder {
    /**
     * Overwrites or appends a record to a data file.
     * @param dataFilePath absolute path to data file
     * @param dataRecord data record to be written/appended
     * @param appendORWriteFlag flag of appending or overwriting
     * @throws IOException I/O error exception
     */
    public static void writeRecord(String dataFilePath, String dataRecord, boolean appendORWriteFlag) throws IOException {

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