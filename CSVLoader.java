
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVLoader {

    private Map<Integer, OrganicYearData> yearMap = new HashMap<>();

    public void load(String filename) throws IOException {

        Map<Integer, List<OrganicEntries>> tempYearMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
<<<<<<< HEAD

        // Προσπέραση header αν υπάρχει
=======
        
>>>>>>> 590dfd02f9ba3faf7aa0b1a63bd057fb5f102734
        line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int year = Integer.parseInt(parts[0].trim());
            int code = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            double tp = Double.parseDouble(parts[3].trim());
            double pde = Double.parseDouble(parts[4].trim());
            double total = Double.parseDouble(parts[5].trim());

            OrganicEntries entry = new OrganicEntries(code, name, tp, pde, total);

            tempYearMap.putIfAbsent(year, new ArrayList<>());
            tempYearMap.get(year).add(entry);
        }

        reader.close();

        // Μετατρέπουμε από List σε OrganicYearData
        for (int year : tempYearMap.keySet()) {
            yearMap.put(year, new OrganicYearData(year, tempYearMap.get(year)));
        }
    }

    public OrganicYearData getYearData(int year) {
        return yearMap.get(year);
    }

    public Set<Integer> getAvailableYears() {
        return yearMap.keySet();
    }
}
