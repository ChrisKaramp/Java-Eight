import java.awt.GridLayout;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class OrganicTablePanel {
     Locale greek = new Locale("el", "GR");
    NumberFormat nf = NumberFormat.getNumberInstance(greek);

    JFrame jf = new JFrame("Οργανική Ταξινόμηση");

    JPanel ministry_JPanel = new JPanel();
    JPanel taktikos_JPanel = new JPanel();
    JPanel ependitikos_JPanel = new JPanel();
    JPanel sunolo_JPanel = new JPanel();
    public OrganicTablePanel() {
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(0);
    }

    public void outline() {
        jf.setSize(800, 600);
        jf.setLayout(new GridLayout(1, 4));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   public void sectors() {   

    // === ΥΠΟΥΡΓΕΙΑ PANEL ===
    JPanel ministryColumn = new JPanel(new BorderLayout());
    JLabel mTitle = new JLabel("Υπουργείο/Φορείς", SwingConstants.CENTER);
    mTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
    mTitle.setOpaque(true);
    mTitle.setBackground(new Color(220, 220, 240));
    mTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    ministryColumn.add(mTitle, BorderLayout.NORTH);

    ministry_JPanel.setLayout(new GridLayout(29, 1));
    ministryColumn.add(ministry_JPanel, BorderLayout.CENTER);
    jf.add(ministryColumn);


    // === ΤΑΚΤΙΚΟΣ PANEL ===
    JPanel taktikosColumn = new JPanel(new BorderLayout());
    JLabel tTitle = new JLabel("Τακτικός", SwingConstants.CENTER);
    tTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
    tTitle.setOpaque(true);
    tTitle.setBackground(new Color(220, 220, 240));
    tTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    taktikosColumn.add(tTitle, BorderLayout.NORTH);

    taktikos_JPanel.setLayout(new GridLayout(29, 1));
    taktikosColumn.add(taktikos_JPanel, BorderLayout.CENTER);
    jf.add(taktikosColumn);


    // === ΕΠΕΝΔΥΤΙΚΟΣ PANEL ===
    JPanel epColumn = new JPanel(new BorderLayout());
    JLabel eTitle = new JLabel("Επενδυτικός", SwingConstants.CENTER);
    eTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
    eTitle.setOpaque(true);
    eTitle.setBackground(new Color(220, 220, 240));
    eTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    epColumn.add(eTitle, BorderLayout.NORTH);

    ependitikos_JPanel.setLayout(new GridLayout(29, 1));
    epColumn.add(ependitikos_JPanel, BorderLayout.CENTER);
    jf.add(epColumn);


    // === ΣΥΝΟΛΟ PANEL ===
    JPanel sumColumn = new JPanel(new BorderLayout());
    JLabel sTitle = new JLabel("Σύνολο", SwingConstants.CENTER);
    sTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
    sTitle.setOpaque(true);
    sTitle.setBackground(new Color(220, 220, 240));
    sTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    sumColumn.add(sTitle, BorderLayout.NORTH);

    sunolo_JPanel.setLayout(new GridLayout(29, 1));
    sumColumn.add(sunolo_JPanel, BorderLayout.CENTER);
    jf.add(sumColumn);
}



    public void mbuttons(List<OrganicEntries> entriesList) {
        ministry_JPanel.removeAll();
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
        for (OrganicEntries o : entries) {
            String formatted = nf.format(o.gettakAmount());
            JButton b = new JButton(formatted);
            taktikos_JPanel.add(b);
        }
        taktikos_JPanel.revalidate();
        taktikos_JPanel.repaint();

        jf.revalidate();
        jf.repaint();
    }
    public void ebuttons(List<OrganicEntries> entries) {
        ependitikos_JPanel.removeAll();
        for (OrganicEntries o : entries) {
            String formatted = nf.format(o.getepenAmount());
            JButton b = new JButton(formatted);
            ependitikos_JPanel.add(b);
        }
        ependitikos_JPanel.revalidate();
        ependitikos_JPanel.repaint();
        jf.revalidate();
        jf.repaint();
    }
        public void sbuttons(List<OrganicEntries> entries) {
        sunolo_JPanel.removeAll();
  
        for (OrganicEntries o : entries) {
            String formatted = nf.format(o.getTotalAmount());
            JButton b = new JButton(formatted);
            sunolo_JPanel.add(b);
            b.setFocusPainted(false);
        }
        sunolo_JPanel.revalidate();
        sunolo_JPanel.repaint();

        jf.revalidate();
        jf.repaint();
    }

    public void show() {
        jf.setVisible(true);
    }
}