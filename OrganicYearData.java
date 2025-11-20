import java.util.List;
 
public class OrganicYearData {
    private int year;
    private List<OrganicEntry> entries;

    public OrganicYearData(int year, List<OrganicEntry> entries) {
        this.year= year;
        this.entries= entries;
    }
    public int getYear() {
        return year;
    }

    public List <OrganicEntry> getENtries() {
        return entries;

    }

    public OrganicEntry getEntryByCode(int code) {
        for (OrgnaicEntry e: entries) {
            if (e.getCode() == code) return e;
        }
        return null;

    }
    public double getTotalTP() {
        return entries.stream().mapToDouble(OrganicEntry::getTpAmount).sum();

    }

    public double getTotalPDE() {
        return entries.stream().mapToDouble(OrganicEntry::getPdeAmount).sum();
    }

    public double getTotalExpenses() {
        return entries.stream().mapToDouble(OrganicEntry::getTotalAmount).sum();
    }
}



    



    
