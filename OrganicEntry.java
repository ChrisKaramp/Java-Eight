public class OrganicEntry {

    private int index;
    private String code;
    private String name;
    private double per;
    private double per_budgetTA;
    private double per_buddgetPDE;

    private void loadDataFromIndex(int i) {
        switch(i) {
            case 1:
            code = "1001" ;
            name = "Προεδρία της Δημοκρατίας ";
            per = 0.0006;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 2:
            code = "1003 " ;
            name = "Βουλή των Ελλήνων";
            per = 0.0182;
            per_budgetTA = 0.987;
            per_buddgetPDE = 0.013;
            break;

            case 3:
            code = "1004 " ;
            name = "Προεδρία της Κυβέρνησης";
            per = 0.0055;
            per_budgetTA = 0.751;
            per_buddgetPDE = 0.249;
            break;

            case 4:
            code = "1007 " ;
            name = "Υπουργείο Εσωτερικών";
            per = 0.004;
            per_budgetTA = 0.9;
            per_buddgetPDE = 0.1;
            break;

            case 5:
            code = "1009 " ;
            name = "Υπουργείο Εξωτερικών";
            per = 0.0003;
            per_budgetTA = 0.95;
            per_buddgetPDE = 0.05;
            break;

            case 6:
            code = "1011" ;
            name = "Υπουργείο Εθνικής Αμύνης";
            per = 0.008;
            per_budgetTA = 0.995;
            per_buddgetPDE = 0.005;
            break;

            case 7:
            code = " 1015" ;
            name = "Υπουργείο Υγείας";
            per = 0.005;
            per_budgetTA = 0.94;
            per_buddgetPDE = 0.06;
            break;

            case 8:
            code = "1017" ;
            name = "Υπουργείο Δικαιοσύνης ";
            per = 0.0007;
            per_budgetTA = 0.9;
            per_buddgetPDE = 0.1;
            break;

            case 9:
            code = "1019" ;
            name = "Υπουργείο Παιδείας και θρησκευμάτων";
            per = 0.007;
            per_budgetTA = 0.9;
            per_buddgetPDE = 0.1;
            break;

            case 10:
            code = "1021" ;
            name = "Υπουργείο Πολιτισμού και Αθλητισμού";
            per = 0.0005;
            per_budgetTA = 0.81;
            per_buddgetPDE = 0.19;
            break;

            case 11:
            code = "1023" ;
            name = "Υπουργείο Οικονομικών";
            per = 0.932;
            per_budgetTA = 0.999;
            per_buddgetPDE = 0.001;
            break;

            case 12:
            code = "1029" ;
            name = " Υπουργείο Αγροτικής Ανάπτυξης και Τροφίμων";
            per = 0.001;
            per_budgetTA = 0.18;
            per_buddgetPDE = 0.82;
            break;

            case 13:
            code = "1031 " ;
            name = "Υπουργείο Περιβάλλοντος και Ενέργειας";
            per = 0.007;
            per_budgetTA = 0.14;
            per_buddgetPDE = 0.86;
            break;

            case 14:
            code = "1033" ;
            name = "Υπουργείο Εργασίας και Κοινωνικών Υποθέσεων";
            per = 0.026;
            per_budgetTA = 0.97;
            per_buddgetPDE = 0.03;
            break;

            case 15:
            code = "1035" ;
            name = "Υπουργείο Ανάπτυξης και Επενδύσεων";
            per = 0.003;
            per_budgetTA = 0.06;
            per_buddgetPDE = 0.94;
            break;

            case 16:
            code = "1039" ;
            name = " Υπουργείο Υποδομών και Μεταφορών";
            per = 0.0025;
            per_budgetTA = 0.37;
            per_buddgetPDE = 0.63;
            break;

            case 17:
            code = "1041" ;
            name = "Υπουργείο Ναυτιλίας και Νησιωτικής Πολιτικής ";
            per = 0.0005 ;
            per_budgetTA = 0.62;
            per_buddgetPDE = 0.38;
            break;

            case 18:
            code = "1045" ;
            name = " Υπουργείο Τουρισμού ";
            per = 0.0001;
            per_budgetTA = 0.47;
            per_buddgetPDE = 0.53;
            break;

            case 19:
            code = "1053" ;
            name = "Υπουργείο Ψηφιακής Διακυβέρνησης ";
            per = 0.001;
            per_budgetTA = 0.26;
            per_buddgetPDE = 0.74;
            break;

            case 20:
            code = "1055" ;
            name = "Υπουργείο Μετανάστασευης και Ασύλου";
            per = 0.0003;
            per_budgetTA = 0.39;
            per_buddgetPDE = 0.61;
            break;

            case 21:
            code = "1057" ;
            name = "Υπουργείο Προστασίας του Πολίτη";
            per = 0.0024;
            per_budgetTA = 0.97;
            per_buddgetPDE = 0.03;
            break;

            case 22:
            code = "1059" ;
            name = "Υπουργείο Κλιματικής Κρίσης και Πολιτικής Προστασίας";
            per = 0.0007;
            per_budgetTA = 0.9;
            per_buddgetPDE = 0.1;
            break;

            case 23:
            code = "1901" ;
            name = "Αποκεντρωμένη Διοίκηση Αττικής";
            per = 0.00002;
            per_budgetTA = 1 ;
            per_buddgetPDE = 0;
            break;

            case 24:
            code = "1902 " ;
            name = "Αποκεντρωμένη Διοίκηση Θεσσαλίας- Στερεάς Ελλάδας";
            per = 0.00003;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 25:
            code = "1903" ;
            name = "Αποκεντρωμένη Διοίκηση Ηπείρου- Δυτικής Μακεδονίας";
            per = 0.00002;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 26:
            code = "1904" ;
            name = "Αποκεντρωμένη Διοίκηση Πελοποννήσου- Δυτικής Ελλάδας και Ιονίου";
            per = 0.00003;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 27:
            code = "1905" ;
            name = "Αποκεντρωμένη Διοίκηση Αιγαίου";
            per = 0.00001;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 28:
            code = "1906" ;
            name = "Αποκεντρωμένη Διοίκηση Κρήτης";
            per = 0.00001;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

            case 29:
            code = "1907 " ;
            name = "Αποκεντρωμένη Διοίκηση Μακεδονίας- Θράκης";
            per = 0.00004;
            per_budgetTA = 1;
            per_buddgetPDE = 0;
            break;

        }
    }
}

