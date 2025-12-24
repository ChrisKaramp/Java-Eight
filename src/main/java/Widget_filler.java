import java.io.IOException;
import javax.swing.JOptionPane;

public class Widget_filler {
    
    private static String[] selectedYearValues;

    public static void Get_selected_year_values(String dataFilePath, String yearSelected, Panel_using_array pua, int fromHeader, int toHeader) throws IOException {

        //System.out.println("Get_selected_year_values");
        //System.out.println(yearSelected);

        // fetch all data from file (sorted)
        File_data_getter fileDataGetter = new File_data_getter(dataFilePath);
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

    private static void Put_selected_year_values(Panel_using_array pua, int fromHeader, int toHeader) {
        for (int i = fromHeader; i < toHeader; i++) {
            //System.out.println(i + " " + selectedYearValues[i] + " " + pua.getListeningSpinnersToBeCreated()[i - fromHeader].getValue());
            pua.getListeningSpinnersToBeCreated()[i - fromHeader].
                setValue(Integer.valueOf(selectedYearValues[i]));
        }
    }
}
