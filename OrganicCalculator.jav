public class OrganicCalculator {

    private OrganicYearData data;

    public OrganicCalculator(OrganicYearData data) {
        this.data = data;
    }

    public void applyNewAmounts(double newTP, double newPDE) {

        double oldTP = data.getTotalTP();
        double oldPDE = data.getTotalPDE();

        if (oldTP == 0 || oldPDE == 0) {
            System.out.println("Σφάλμα: το οργανικό αρχείο έχει μηδενικές τιμές.");
            return;
        }

        for (OrganicEntries entry : data.getEntries()) {

            //Υπολογισμός Τακτικού 
            double pctTP = entry.gettakAmount() / oldTP;    // αναλογία παλιού ΤΠ
            double newTak = pctTP * newTP;                  // νέο ποσό ΤΠ
            entry.settakAmount(newTak);

            //Υπολογισμός ΠΔΕ 
            double pctPDE = entry.getepenAmount() / oldPDE; // αναλογία παλιού ΠΔΕ
            double newEpen = pctPDE * newPDE;               // νέο ποσό ΠΔΕ
            entry.setPdeAmount(newEpen);

            // νέο σύνολο
            entry.setTotalAmount(newTak + newEpen);
        }
    }
}