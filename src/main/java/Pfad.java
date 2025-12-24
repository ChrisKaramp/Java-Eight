import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class Pfad {
    public static void main(String[] args) throws IOException, URISyntaxException {

        // create data folder and copy csv files from jar to data folder
        Copy_from_jar_to_data_folder.createDataFolderAndCopyCSVs(Constants.DATA_DIRECTORY);

        // if data file does not exist, create and initialize it
        if (Build_data_file.create_data_file(Constants.DATA_FILE_PATH).equalsIgnoreCase("created") ) {
            Build_data_file.initialize_data_file(Constants.DATA_FILE_PATH);
            //System.out.println("Data file initialized successfully.");
            JOptionPane.showMessageDialog(null, "Data file " + Constants.DATA_FILE_PATH + " created and initialized successfully.");
        // else if data file exists, inform user
        } else if (Build_data_file.create_data_file(Constants.DATA_FILE_PATH).equalsIgnoreCase("exists") ) {
            JOptionPane.showMessageDialog(null, "Data file " + Constants.DATA_FILE_PATH + " already exists at specified path.");
        // else if error occurred during creation, inform user and exit
        } else if (Build_data_file.create_data_file(Constants.DATA_FILE_PATH).equalsIgnoreCase("error") ) {
            JOptionPane.showMessageDialog
                (null,
                "Could not create data file. Please check file path and permissions.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            System.out.println("An error occurred while creating data file. Exit code: 1.");
            System.exit(1);
        }

        // create budget origin window, dimensions same as screen, defined in Constants class
        Budget_origin_window_model budgetOriginWindow = new Budget_origin_window_model(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, Constants.DATA_FILE_PATH);
        budgetOriginWindow.setTitle("Προέλευση Κρατικού Προϋπολογισμού (Ctrl L/R --> reset)");
    }
}
