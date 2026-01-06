package gr.aueb.javaeight;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Gets file names of data folder to be put on country comparison combobox.
 */
public class FileNameProvider {

    // define proper structure to put file names
    private List<String> fileNames = new ArrayList<>();

    /**
     * Gets filenames of an extension (probably .csv) in a given folder.
     * Notifies user in case of failure.
     * @param givenFolder folder to be run
     * @param fileWildCard extension of files to be provided
     */
    FileNameProvider(String givenFolder, String fileWildCard) {

        // define an object of folder type
        File filesFolder = new File(givenFolder);

        // check if this folder exists
        if (!(filesFolder.exists()) || !(filesFolder.isDirectory())){
            JOptionPane.showMessageDialog
                (null,
                "Directory\n" + filesFolder + "\ndoes not exist.",
                "Error with data folder",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // define a file filter
        FileFilter filesOfAnExtension = (File file) -> {
            return file.getName().toLowerCase().endsWith(fileWildCard.toLowerCase());
        };

        // define an array of file objects
        File[] listOfFiles = filesFolder.listFiles(filesOfAnExtension);
        if (listOfFiles == null) {
            JOptionPane.showMessageDialog
                (null,
                "File names list for\n" + filesFolder + "\nis null.",
                "Error with file names list",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // assemble list of file names to be returned
        for (File extensionFile : listOfFiles) {
            if (extensionFile.isFile()) {
                this.fileNames.add(extensionFile.getName());
            }
        }
    }

    // filenames getter
    public List<String> getFileNames() {
        return fileNames;
    }
}
