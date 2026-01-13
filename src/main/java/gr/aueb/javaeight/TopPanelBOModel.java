package gr.aueb.javaeight;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Creates a panel used to be the top panel of Budget Origin frame/window.
 */
public class TopPanelBOModel extends JPanel {

    //// define panel components to be used and placed into
    // countries selection label and combobox
    private final JLabel comparisonJL = new JLabel("Σύγκριση:");
    private final ComparisonCombobox countrySelectCC;
    // mass update button
    private final InteractiveButton massDataSaveIB;
    // year selection label and combobox
    private final JLabel yearJL = new JLabel("Έτος:");
    private final YearSelectionCombobox yearSelectYSC;
    // year add button and textfield
    private final InteractiveButton yearAddIB;
    private JTextField yearaddJTF = new JTextField();
    // budget distribution launch button
    private final InteractiveButton budgetDistributionLaunchIB;
    // PDF creation button
    private final InteractiveButton pdfCreationIB;
    // Graph creation button
    private final InteractiveButton graphCreationIB;

    /**
     * Creates a new panel using certain components.
     * @param dataFilePath absolute path to data file
     * @param dataFolder absolute path to data folder
     * @param panelsToBeControlled structure to provide control on panels
     * @throws IOException I/O error exception
     */
    public TopPanelBOModel(String dataFilePath, String dataFolder, ArrayList<ArrayBasedPanel> panelsToBeControlled)
            throws IOException {

        //// initialize components
        // initialize mass data save button
        this.massDataSaveIB = new InteractiveButton("Μαζική αποθήκευση",
                "mass update",
                this,
                dataFilePath,
                panelsToBeControlled,
                this);
        // initialize year selection combobox
        this.yearSelectYSC = new YearSelectionCombobox(dataFilePath, panelsToBeControlled);
        // initialize year adding button
        this.yearAddIB = new InteractiveButton("Προσθήκη έτους",
                "add year",
                this,
                dataFilePath,
                panelsToBeControlled,
                this);
        // initialize budget distribution window launching button
        this.budgetDistributionLaunchIB = new InteractiveButton("Κατανομή",
                "launch budget distribution",
                this,
                dataFilePath,
                panelsToBeControlled,
                this);
        // initialize budget distribution window launching button
        this.pdfCreationIB = new InteractiveButton("PDF",
                "pdf",
                this,
                dataFilePath,
                panelsToBeControlled,
                this);
                        // initialize budget distribution window launching button
        this.graphCreationIB = new InteractiveButton("Graph",
                "graph",
                this,
                dataFilePath,
                panelsToBeControlled,
                this);
        // initialize country selection combobox to have access at year selection combobox
        this.countrySelectCC = new ComparisonCombobox(this.yearSelectYSC, dataFilePath, dataFolder);
        // restrict year textfield input to digits only
        yearaddJTF.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null)
                    return;

                String newText = new StringBuilder(getText(0, getLength()))
                        .insert(offs, str)
                        .toString();

                if (newText.matches("-?\\d*")) {
                    super.insertString(offs, str, a);
                }
            }
        });

        //// place components on panel
        this.add(comparisonJL);
        FileNameProvider countryFileNames = new FileNameProvider(dataFolder, Constants.CSV_FILE_EXTENSION);
        for (String country : countryFileNames.getFileNames()) {
            countrySelectCC.addItem(country);
        }
        this.add(countrySelectCC);
        countrySelectCC.setInitialized();
        this.add(massDataSaveIB);
        this.add(yearJL);
        this.add(yearSelectYSC);
        for (String year : YearProvider.GetYearsIntoStringsArray(dataFilePath)) {
            yearSelectYSC.addItem(year);
        }
        this.add(yearAddIB);
        this.add(yearaddJTF);
        this.add(budgetDistributionLaunchIB);
        this.add(pdfCreationIB);
        this.add(graphCreationIB);
    }

    //// define getters
    public YearSelectionCombobox getYearSelectYSC() {
        return yearSelectYSC;
    }

    public ComparisonCombobox getCountrySelectCC() {
        return countrySelectCC;
    }

    public String getYearAddJTF(){
        return yearaddJTF.getText();
    }
}
