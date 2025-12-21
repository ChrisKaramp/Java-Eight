import java.util.List;
 
public class OrganicYearData {
    private int year;
    private List<OrganicEntries> entries;

    public OrganicYearData(int year, List<OrganicEntries> entries) {
        this.year= year;
        this.entries= entries;
    }
    public int getYear() {
        return year;
    }

    public List <OrganicEntries> getEntries() {
        return entries;

    }

    public OrganicEntries getEntryByCode(int code) {
        for (OrganicEntries e: entries) {
            if (e.getCode() == code) return e;
        }
        return null;

    }
    public double getTotalTP() {
        return entries.stream().mapToDouble(OrganicEntries::gettakAmount).sum();

    }

    public double getTotalPDE() {
        return entries.stream().mapToDouble(OrganicEntries::getepenAmount).sum();
    }

    public double getTotalExpenses() {
        return entries.stream().mapToDouble(OrganicEntries::getTotalAmount).sum();
    }
}



    



    
