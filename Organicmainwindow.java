public class Organicmainwindow {
    
    public static void main(String[] args) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.load("/Users/macuser/Documents/ΔΕΤ/Statebudget/Java-Eight/organic_data.csv");
            OrganicTablePanel obj = new OrganicTablePanel();
            OrganicYearData data = loader.getYearData(2022); // Βάλε όποιο έτος θες
            obj.outline();
            obj.sectors();
            obj.mbuttons(data.getEntries());
            obj.tbuttons(data.getEntries());
            obj.ebuttons(data.getEntries());
            obj.sbuttons(data.getEntries());
            obj.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


