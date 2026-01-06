import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Initializes spinners with proper values from data file.
 * Calculates (sub)totals automatically.
 */
public class SpinnerValuesInitializer {
    
    private static String[] selectedYearValues;
    
    /**
     * Gets data from file.
     * Fetches proper record according to selected year, into structure.
     * Puts record values from structure to proper spinners of current frame panels.
     * (Sub)totals are automatically calculated, so only spinners are initialized.
     * @param dataFilePath
     * @param yearSelected
     * @param pua
     * @param fromHeader
     * @param toHeader
     * @throws IOException
     */
    public static void GetSelectedYearValues(String dataFilePath, String yearSelected, ArrayBasedPanel pua, int fromHeader, int toHeader) throws IOException {

        // fetch all data from file (sorted)
        FileDataProvider fileDataGetter = new FileDataProvider(dataFilePath);
        String[][] dataFile2D = fileDataGetter.getDataFile2D();
 
        for ( String[] record : dataFile2D) {
            if (record[0].equalsIgnoreCase(yearSelected) ) {
                selectedYearValues = record;
            }
        }

        if (selectedYearValues == null || selectedYearValues.length == 0) {
            // something went wrong, notify user
            JOptionPane.showMessageDialog
                (null, yearSelected + " --> not found.");
        } else {
            Put_selected_year_values(pua, fromHeader, toHeader);
        }

    }

    private static void Put_selected_year_values(ArrayBasedPanel pua, int fromHeader, int toHeader) {
        for (int i = fromHeader; i < toHeader; i++) {
            pua.getListeningSpinnersToBeCreated()[i - fromHeader].
                setValue(Integer.valueOf(selectedYearValues[i]));
        }
    }
}
