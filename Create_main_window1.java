import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.*;

public class Create_main_window {

    // Note: no private methods or attributes in this class
    // Other classes MAY change element features using an instance created
    
    // define main frame
    JFrame main_window = new JFrame("Προέλευση Κρατικού Προϋπολογισμού");

    // define the four panels into wich main frame will be split
    JPanel year_JPanel = new JPanel();
    JPanel income_JPanel = new JPanel();
    JPanel expenses_JPanel = new JPanel();
    JPanel results_JPanel = new JPanel();

    // define year selection panel (top side) components and widgets to be used and placed into
    JLabel comparison_JLabel = new JLabel("Σύγκριση:");
    String[] countries = { "France", "Italy", "Germany", "USA", "UK" };
    JComboBox<String> countryselect_JComboBox = new JComboBox<>(countries);
    JButton datasave_JButton = new JButton("Μαζική αποθήκευση");
    JLabel year_JLabel = new JLabel("Έτος:");
    String[] years = { "2025","2024", "2023","2022","2021","2020" };
    JComboBox<String> yearselect_JComboBox = new JComboBox<>(years);
    Listening_button yearadd_JButton = new Listening_button("Προσθήκη έτους ->",  "add year");
    static JTextField yearadd_JTextField = new JTextField("2000");

    // define income panel (left side) components and widgets to be used and placed into
    JLabel income1_JLabel = new JLabel("1. ΈΣΟΔΑ ΣΥΝΟΛΟ");
    JLabel showincome1_JLabel = new JLabel("EUR" + " 0.00");
    Listening_button taxes11_JButton = new Listening_button("11. Φόροι",  "horizontal save");
    JSpinner taxes11_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton socialcontributions12_JButton = new JButton("12. Κοινωνικές εισφορές");
    JSpinner socialcontributions12_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton transfers13_JButton = new JButton("13. Μεταβιβάσεις (έσοδα)");
    JSpinner transfers13_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton gssaless14_JButton = new JButton("14. Πωλήσεις αγαθών και υπηρεσιών");    
    JSpinner gssaless14_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
     JButton othercurrentincome15_JButton = new JButton("15. Λοιπά τρέχοντα έσοδα");    
    JSpinner othercurrentincome15_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton incomefixedassets31_JButton = new JButton("31. Πάγια περιουσιακά στοιχεία (έσοδα)");    
    JSpinner incomefixedassets31_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton debtsecurities43_JButton = new JButton("43. Χρεωστικοί τίτλοι");    
    JSpinner debtsecurities43_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton incomeequityandinvestments45_JButton = new JButton("45. Συμμετοχές και επενδύσεις (έσοδα)");    
    JSpinner incomeequityandinvestments45_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton currencydepositliabilities52_JButton = new JButton("52. Υποχρεώσεις από καταθέσεις και νομίσματα");    
    JSpinner currencydepositliabilities52_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton incomedebtsecuritiesliabilities53_JButton = new JButton("53. Χρεωστικοί τίτλοι (υποχρεώσεις - έσοδα)");
    JSpinner incomedebtsecuritiesliabilities53_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton incomeloans54_JButton = new JButton("54. Δάνεια (έσοδα)");
    JSpinner incomeloans54_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));   
    Border income_Border = BorderFactory.createTitledBorder("1. Έσοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση.");

    // define expenses panel (right side) components and widgets to be used and placed into
    JLabel expenses2_JLabel = new JLabel("2. ΈΞΟΔΑ ΣΥΝΟΛΟ");
    JLabel showexpenses2_JLabel = new JLabel("EUR" + " 0.00");
    JButton employeebenefits21_JButton = new JButton("21. Παροχές εργαζομένων");
    JSpinner emploeebenefits21_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton socialbenefits22_JButton = new JButton("22. Κοινωνικές παροχές");
    JSpinner socialbenefits22_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton transfers23_JButton = new JButton("23. Μεταβιβάσεις (έξοδα)");
    JSpinner transfers23_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton gspurchases24_JButton = new JButton("24. Αγορές αγαθών και υπηρεσιών");
    JSpinner gspurchases24_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton subsidies25_JButton = new JButton("25. Επιχορηγήσεις -Επιδοτήσεις");
    JSpinner subsidies25_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton interest26_JButton = new JButton("26. Τόκοι");
    JSpinner interest26_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton otherexpenses27_JButton = new JButton("27. Λοιπά έξοδα");
    JSpinner otherexpenses27_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton appropriationsunderallocation29_JButton = new JButton("29. Πιστώσεις υπό κατανομή");
    JSpinner appropriationsunderallocation29_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton expensesfixedassets31_JButton = new JButton("31. Πάγια περιουσιακά στοιχεία (έξοδα)");
    JSpinner expensesfixedassets31_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton valuables33_JButton = new JButton("33. Τιμαλφή περιουσία");
    JSpinner valuables33_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton expensesloans44_JButton = new JButton("44. Δάνεια κωδικού 44 (έξοδα)");
    JSpinner expensesloans44_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton expensesequityandinvestments45_JButton = new JButton("45. Συμμετοχές και επενδύσεις (έξοδα)");
    JSpinner expensesequityandinvestments45_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton expensesdebtsecuritiesliabilities53_JButton = new JButton("53. Χρεωστικοί τίτλοι (υποχρεώσεις - έξοδα)");
    JSpinner expensesdebtsecuritiesliabilities53_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton expensesloans54_JButton = new JButton("54. Δάνεια (έξοδα)");
    JSpinner expensesloans54_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
    JButton financiaderivativesl57_JButton = new JButton("57. Χρηματοοικονομικά παράγωγα");
    JSpinner financialderivatives57_JSpinner = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));   
    Border expenses_Border = BorderFactory.createTitledBorder("2. Έξοδα (EUR) - Πληκτρολογήστε το ποσό ή αλλάξτε το με τα βελάκια. Πατήστε για καταχώρηση.");

    // define results panel (bottom side) components and widgets to be used and placed into
    String budget_result = "EUR" + " 0.00";
    JLabel budgetresult_JLabel = new JLabel("Αποτέλεσμα Κρατικού Προϋπολογισμού (Έσοδα - Έξοδα):");
    JLabel showrbudgetesult_JLabel = new JLabel(budget_result);
    String budget_result_cover = "EUR" + " 0.00";
    JLabel budgetresultcover_JLabel = new JLabel("Κάλυψη με Ταμειακά Διαθέσιμα:");
    JLabel showbudgetresultcover_JLabel = new JLabel(budget_result_cover);

