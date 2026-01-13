package gr.aueb.javaeight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Creates a panel that draws a bar chart on itself.
 * Gets data from a single data line from a 2D array of strings.
 * Array provided contains data from file.
 * Overrides paintComponent method of its super(super) class JPanel(JComponent).
 */
public class BarChartPanel extends JPanel {

    private final String[] dataRecord;
    private final int startIndex;
    private final int endIndex;
    private final String title;

    private static final int TITLE_FONT_SIZE = 16;
    private static final int LABEL_FONT_SIZE = 10;

    private static final Color COLOR_1 = Color.YELLOW;
    private static final Color COLOR_2 = Color.ORANGE;
    private static final Color COLOR_3 = Color.BLUE;

    /**
     * Draws a bar chart on a panel.
     * @param dataRecord data values to be used
     * @param startIndex headers start point at Constants.java file
     * @param endIndex headers end point at Constants.java file
     * @param title title on panel
     */
    public BarChartPanel(String[] dataRecord,
                         int startIndex,
                         int endIndex,
                         String title) {

        this.dataRecord = dataRecord;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.title = title;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // ===== 1. Συλλογή δεδομένων =====
        List<String> labels = new ArrayList<>();
        List<Double> v1 = new ArrayList<>();
        List<Double> v2 = new ArrayList<>();
        List<Double> v3 = new ArrayList<>();

        int safeEnd = Math.min(endIndex, dataRecord.length);

        for (int i = startIndex; i < safeEnd; i += 3) {
            if (i + 2 >= dataRecord.length)
                break;
            labels.add(Constants.DATA_FILE_HEADERS[i]);
            double value1 = parseDoubleSafe(dataRecord[i]);
            double value2 = parseDoubleSafe(dataRecord[i + 1]);
            v1.add(value1);
            v2.add(value2);
            v3.add(value1 + value2);
        }

        // ===== 2. Guards =====
        int groupCount = labels.size();
        if (groupCount == 0) {
            return;
        }

        double maxValue = v3.stream().mapToDouble(Double::doubleValue).max().orElse(1);

        // ===== 2. Διαστάσεις =====
        int width = getWidth();
        int height = getHeight();

        int topMargin = 50;
        int bottomMargin = 50;
        int leftMargin = 80;
        int rightMargin = 40;

        int chartHeight = height - topMargin - bottomMargin;
        int chartWidth = width - leftMargin - rightMargin;

        groupCount = labels.size();
        int groupWidth = chartWidth / groupCount;
        int barWidth = groupWidth / 4;

        // ===== 3. Τίτλος =====
        g2.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        g2.setColor(Color.BLACK);
        g2.drawString(title, leftMargin, topMargin - 20);

        // ===== 4. Bars =====
        g2.setFont(new Font("SansSerif", Font.PLAIN, LABEL_FONT_SIZE));
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < groupCount; i++) {

            int baseX = leftMargin + i * groupWidth;

            int h1 = (int) (v1.get(i) / maxValue * chartHeight);
            int h2 = (int) (v2.get(i) / maxValue * chartHeight);
            int h3 = (int) (v3.get(i) / maxValue * chartHeight);

            int yBase = topMargin + chartHeight;

            // bar 1
            g2.setColor(COLOR_1);
            g2.fillRect(baseX, yBase - h1, barWidth, h1);

            // bar 2
            g2.setColor(COLOR_2);
            g2.fillRect(baseX + barWidth + 2, yBase - h2, barWidth, h2);

            // bar 3
            g2.setColor(COLOR_3);
            g2.fillRect(baseX + 2 * (barWidth + 2), yBase - h3, barWidth, h3);

            // label
            g2.setColor(Color.BLACK);
            g2.drawString(
                    df.format(v3.get(i)),
                    baseX + barWidth,
                    yBase - h3 - 5);
        }
    }
    
    /**
     * Parses a string to double
     * @param s the string to be parsed
     * @return a double, or 0 in case of any error
     */
    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
