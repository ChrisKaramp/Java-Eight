public class OrganicEntries {
    private int code;
    private String name;
    private double takAmount;
    private double epenAmount;
    private double totalAmount;

    public OrganicEntries(int cd, String nm, double taktikos, double ependisi, double total) {
        code = cd;
        name = nm;
        takAmount = taktikos;
        epenAmount = ependisi;
        totalAmount = total;
    }

     public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double gettakAmount() {
        return takAmount;
    }

    public double getepenAmount() {
        return epenAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void settakAmount(double taktikos) {
        takAmount = taktikos;
    }

    public void setPdeAmount(double ependisi) {
        epenAmount = ependisi;
    }

    public void setTotalAmount(double total) {
        totalAmount = total;
    }
}