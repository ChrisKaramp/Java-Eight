import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

public class OrganicTablePanel {

    JFrame jf = new JFrame("Οργανική Ταξινόμηση");

    JPanel ministry_JPanel = new JPanel();
    JPanel taktikos_JPanel = new JPanel();
    JPanel ependitikos_JPanel = new JPanel();
    JPanel sunolo_JPanel = new JPanel();

    public void outline() {
        jf.setSize(800, 600);
        jf.setLayout(new GridLayout(1, 4));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void sectors() {   
        ministry_JPanel.setLayout(new GridLayout(30, 1)); // 1 header + 29 rows
        ministry_JPanel.add(new JLabel("Υπουργείο/Φορείς"));
        jf.add(ministry_JPanel);


        taktikos_JPanel.setLayout(new GridLayout(30, 1)); // 1 header + 29 rows
        taktikos_JPanel.add(new JLabel("Τακτικός"));
        jf.add(taktikos_JPanel);


        ependitikos_JPanel.setLayout(new GridLayout(30, 1)); // 1 header + 29 rows
        ependitikos_JPanel.add(new JLabel("Επενδυτικός"));
        jf.add(ependitikos_JPanel);

        sunolo_JPanel.setLayout(new GridLayout(30, 1)); // 1 header + 29 rows
        sunolo_JPanel.add(new JLabel("Σύνολο"));
        jf.add(sunolo_JPanel);
    }


    public void mbuttons(List<OrganicEntries> entriesList) {

        ministry_JPanel.removeAll();
        ministry_JPanel.add(new JLabel("Υπουργεία/Φορείς"));
        for (OrganicEntries o : entriesList) {
            JButton b = new JButton(o.getName());
            ministry_JPanel.add(b);
        }

        // Refresh panel
        ministry_JPanel.revalidate();
        ministry_JPanel.repaint();

        // Refresh frame
        jf.revalidate();
        jf.repaint();
    }
    public void tbuttons(List<OrganicEntries> entries) {
        taktikos_JPanel.removeAll();
        taktikos_JPanel.add(new JLabel("Tακτικός"));
        for (OrganicEntries o : entries) {
            JButton b = new JButton(Double.toString((o.gettakAmount())));
            taktikos_JPanel.add(b);
        }
         // Refresh panel
        taktikos_JPanel.revalidate();
        taktikos_JPanel.repaint();

        // Refresh frame
        jf.revalidate();
        jf.repaint();
    }
    public void ebuttons(List<OrganicEntries> entries) {
        ependitikos_JPanel.removeAll();
        ependitikos_JPanel.add(new JLabel("Επενδυτικός"));
        for (OrganicEntries o : entries) {
            JButton b = new JButton(Double.toString((o.getepenAmount())));
            ependitikos_JPanel.add(b);
        }
         // Refresh panel
        ependitikos_JPanel.revalidate();
        ependitikos_JPanel.repaint();

        // Refresh frame
        jf.revalidate();
        jf.repaint();
    }
        public void sbuttons(List<OrganicEntries> entries) {
        sunolo_JPanel.removeAll();
        sunolo_JPanel.add(new JLabel("Σύνολο"));
        for (OrganicEntries o : entries) {
            JButton b = new JButton(Double.toString((o.getTotalAmount())));
            sunolo_JPanel.add(b);
        }
         // Refresh panel
        sunolo_JPanel.revalidate();
        sunolo_JPanel.repaint();

        // Refresh frame
        jf.revalidate();
        jf.repaint();
    }

    public void show() {
        jf.setVisible(true);
    }
}
