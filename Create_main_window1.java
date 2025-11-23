import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.*;

public class Create_main_window1 {

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
    public void configure_window(int window_width, int window_height) {

        // print test data
        System.out.println("IN BUILD MAIN WINDOW METHOD");
        System.out.println(Get_data_from_file.data_file_records);

        // configure the main "Budget Origin" WINDOW as frame
        main_window.setSize(window_width, window_height);
        main_window.getContentPane().setBackground(Color.blue);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setVisible(true);
        main_window.setResizable(false);
        //main_window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // configure the four PANELS
        year_JPanel.setBackground(Color.gray);
        income_JPanel.setBackground(Color.green);
        Double income_jpanel_width_Double = window_width * 0.98;
        int income_jpanel_width = income_jpanel_width_Double.intValue();
        income_JPanel.setPreferredSize(new Dimension(income_jpanel_width/2, window_height));
        income_JPanel.setLayout(new GridLayout(12, 2, 5, 5));
        income_JPanel.setBorder(income_Border);
        expenses_JPanel.setBackground(Color.pink);
        expenses_JPanel.setPreferredSize(new Dimension(window_width/2, window_height));
        expenses_JPanel.setLayout(new GridLayout(16,2, 5, 5));
        expenses_JPanel.setBorder(expenses_Border);
        results_JPanel.setBackground(Color.orange);
        results_JPanel.setLayout(new GridLayout(2, 2, 2, 2));

        // place panels into main window
        main_window.add(BorderLayout.NORTH, year_JPanel);
        main_window.add(BorderLayout.WEST, income_JPanel);
        main_window.add(BorderLayout.EAST, expenses_JPanel);
        main_window.add(BorderLayout.SOUTH, results_JPanel);

        // place components into year (top) panel
        year_JPanel.add(comparison_JLabel);
        year_JPanel.add(countryselect_JComboBox);
        year_JPanel.add(datasave_JButton);
        year_JPanel.add(year_JLabel);
        year_JPanel.add(yearselect_JComboBox);
        year_JPanel.add(yearadd_JButton);
        year_JPanel.add(yearadd_JTextField);

        // place components into income (left) panel
        income_JPanel.add(income1_JLabel);
        income_JPanel.add(showincome1_JLabel);
        income_JPanel.add(taxes11_JButton);
        income_JPanel.add(taxes11_JSpinner);
        income_JPanel.add(socialcontributions12_JButton);
        income_JPanel.add(socialcontributions12_JSpinner);
        income_JPanel.add(transfers13_JButton);
        income_JPanel.add(transfers13_JSpinner);
        income_JPanel.add(gssaless14_JButton);
        income_JPanel.add(gssaless14_JSpinner);
        income_JPanel.add(othercurrentincome15_JButton);
        income_JPanel.add(othercurrentincome15_JSpinner);
        income_JPanel.add(incomefixedassets31_JButton);
        income_JPanel.add(incomefixedassets31_JSpinner);
        income_JPanel.add(debtsecurities43_JButton);
        income_JPanel.add(debtsecurities43_JSpinner);
        income_JPanel.add(incomeequityandinvestments45_JButton);
        income_JPanel.add(incomeequityandinvestments45_JSpinner);
        income_JPanel.add(currencydepositliabilities52_JButton);
        income_JPanel.add(currencydepositliabilities52_JSpinner);
        income_JPanel.add(incomedebtsecuritiesliabilities53_JButton);
        income_JPanel.add(incomedebtsecuritiesliabilities53_JSpinner);
        income_JPanel.add(incomeloans54_JButton);
        income_JPanel.add(incomeloans54_JSpinner);

        // place components into expenses (right) panel
        expenses_JPanel.add(expenses2_JLabel);
        expenses_JPanel.add(showexpenses2_JLabel);
        expenses_JPanel.add(employeebenefits21_JButton);
        expenses_JPanel.add(emploeebenefits21_JSpinner);
        expenses_JPanel.add(socialbenefits22_JButton);
        expenses_JPanel.add(socialbenefits22_JSpinner);
        expenses_JPanel.add(transfers23_JButton);
        expenses_JPanel.add(transfers23_JSpinner);
        expenses_JPanel.add(gspurchases24_JButton);
        expenses_JPanel.add(gspurchases24_JSpinner);
        expenses_JPanel.add(subsidies25_JButton);
        expenses_JPanel.add(subsidies25_JSpinner);
        expenses_JPanel.add(interest26_JButton);
        expenses_JPanel.add(interest26_JSpinner);
        expenses_JPanel.add(otherexpenses27_JButton);
        expenses_JPanel.add(otherexpenses27_JSpinner);
        expenses_JPanel.add(appropriationsunderallocation29_JButton);
        expenses_JPanel.add(appropriationsunderallocation29_JSpinner);
        expenses_JPanel.add(expensesfixedassets31_JButton);
        expenses_JPanel.add(expensesfixedassets31_JSpinner);
        expenses_JPanel.add(valuables33_JButton);
        expenses_JPanel.add(valuables33_JSpinner);
        expenses_JPanel.add(expensesloans44_JButton);
        expenses_JPanel.add(expensesloans44_JSpinner);
        expenses_JPanel.add(expensesequityandinvestments45_JButton);
        expenses_JPanel.add(expensesequityandinvestments45_JSpinner);
        expenses_JPanel.add(expensesdebtsecuritiesliabilities53_JButton);
        expenses_JPanel.add(expensesdebtsecuritiesliabilities53_JSpinner);
        expenses_JPanel.add(expensesloans54_JButton);
        expenses_JPanel.add(expensesloans54_JSpinner);
        expenses_JPanel.add(financiaderivativesl57_JButton);
        expenses_JPanel.add(financialderivatives57_JSpinner);

        // place components into results (bottom) panel
        results_JPanel.add(budgetresult_JLabel);
        results_JPanel.add(showrbudgetesult_JLabel);
        results_JPanel.add(budgetresultcover_JLabel);
        results_JPanel.add(showbudgetresultcover_JLabel);

    }
}

