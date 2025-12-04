public class Organicmainwindow {
    
    public static void main(String[] args) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.load("C:\\Users\\kleio\\Downloads\\programm2\\εργασία\\Java-Eight\\organic_data.csv");
            OrganicTablePanel obj = new OrganicTablePanel();
            OrganicYearData data = loader.getYearData(2024); // Βάλε όποιο έτος θες
            obj.outline();
            obj.sectors();
            obj.buttons(data.getENtries());
            obj.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
