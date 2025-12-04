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
        ministry_JPanel.setBackground(Color.LIGHT_GRAY);
        ministry_JPanel.setLayout(new BoxLayout(ministry_JPanel, BoxLayout.Y_AXIS));
        ministry_JPanel.add(new JLabel("Υπουργεία/Φορείς"));
        jf.add(ministry_JPanel);

        taktikos_JPanel.setBackground(Color.LIGHT_GRAY);
        taktikos_JPanel.add(new JLabel("Τακτικός"));
        jf.add(taktikos_JPanel);

        ependitikos_JPanel.setBackground(Color.LIGHT_GRAY);
        ependitikos_JPanel.add(new JLabel("Επενδυτικός"));
        jf.add(ependitikos_JPanel);

        sunolo_JPanel.setBackground(Color.LIGHT_GRAY);
        sunolo_JPanel.add(new JLabel("Σύνολο"));
        jf.add(sunolo_JPanel);
    }

    public void buttons(List<OrganicEntries> entriesList) {

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

    public void show() {
        jf.setVisible(true);
    }
}
