package gr.aueb.javaeight;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import javax.swing.JOptionPane;

/**
 * Starts the application using main method.
 * Creates data folder.
 * Builds data files if ran from IDE.
 * Extracts data files if ran from .jar file.
 */
public class Pfad {
    /**
     * Start the application.
     * Create proper file structures.
     * Create the first window.
     * @param args command-line arguments passed to the program - not used here
     * @throws IOException I/O error exception if an input or output error occurs
     * @throws URISyntaxException if the specified URI is not valid
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        // create data folder and copy csv files from jar to data folder
        JarDataFolderCopier.createDataFolderAndCopyCSVs(Constants.DATA_DIRECTORY_NAME);

        // get created folder name (same name if already existed, either JAR or VSC)
        Path workingOrCreatedDirectory = JarDataFolderCopier.pathOfcreatedOrExistingFolder;
        // convert into string for use with our methods
        String dataFolderAsString = workingOrCreatedDirectory.toString();

        // create absolute path of our data file wich will be created in this folder (VSC)
        Path dataFilePathinWD = workingOrCreatedDirectory.resolve(Constants.FILE_NAME_IN_RESOURCES);
        // convert into string for use with our methods
        String dataFilePathAsString = dataFilePathinWD.toString();

        // if data file does not exist (already from JAR), create and initialize it (VSC)
        if (DataFileBuilder.dataFileCreator(dataFilePathAsString).equalsIgnoreCase("created") ) {
            DataFileBuilder.initialize_data_file(dataFilePathAsString);
            JOptionPane.showMessageDialog(null, "Data file " + dataFilePathAsString + " created and initialized successfully.");
        // else if data file exists, inform user
        } else if (DataFileBuilder.dataFileCreator(dataFilePathAsString).equalsIgnoreCase("exists") ) {
            JOptionPane.showMessageDialog(null, "Data file " + dataFilePathAsString + " already exists at specified path.");
        // else if error occurred during creation, inform user and exit
        } else if (DataFileBuilder.dataFileCreator(dataFilePathAsString).equalsIgnoreCase("error") ) {
            JOptionPane.showMessageDialog
                (null,
                "Could not create data file. Please check file path and permissions.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            System.out.println("An error occurred while creating data file. Exit code: 1.");
            System.exit(1);
        }

        // create budget origin window, dimensions same as screen, defined in Constants class
        BudgetOriginWindowModel budgetOriginWindow = new BudgetOriginWindowModel(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, dataFilePathAsString, dataFolderAsString);
        budgetOriginWindow.setTitle("Προέλευση Κρατικού Προϋπολογισμού (Ctrl L/R --> reset) - Επιτρεπόμενες τιμές: " + Integer.MIN_VALUE + " έως " + Integer.MAX_VALUE);
    }
}
