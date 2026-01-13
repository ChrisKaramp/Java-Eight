package gr.aueb.javaeight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Creates a panel that draws a pie chart on itself.
 * Gets data from a single data line from a 2D array of strings.
 * Array provided contains data from file.
 * Overrides paintComponent method of its super(super) class JPanel(JComponent).
 */
public class PieChartPanel extends JPanel {

    // structures used
    private final String[] dataRecord;
    private final int startIndex;
    private final int endIndex;
    private final Random rand = new Random();

    // font constant sizes
    private static final int PIE_CHART_TITLE_FONT_SIZE = 16;
    private static final int PIE_CHART_LABEL_FONT_SIZE = 10;

    /**
     * Draws a pie chart on a panel.
     * @param dataRecord data values to be used
     * @param startIndex headers start point at Constants.java file
     * @param endIndex headers end point at Constants.java file
     */
    public PieChartPanel(String[] dataRecord, int startIndex, int endIndex) {
        this.dataRecord = dataRecord;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // parse values from string to double
        double total = 0;
        for (int i = startIndex + 1; i < endIndex; i++) {
            double value = parseDoubleSafe(dataRecord[i]);
            if (value > 0)
                total += value;
        }

        // create necessary structures
        List<String> labels = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        List<Color> colors = new ArrayList<>();

        // set presentation format for decimals
        DecimalFormat df = new DecimalFormat("0.00");

        // add headers on the one side
        for (int i = startIndex + 1; i < endIndex; i++) {
            double value = parseDoubleSafe(dataRecord[i]);
            // set percentage for positive values, random color
            if (value >= 0) {
                String pctFormat = df.format(value / total * 100) + "%, ";
                if (value == 0) {
                    pctFormat = "0.00%";
                }
                labels.add(
                        Constants.DATA_FILE_HEADERS[i]
                        + " ("
                        + pctFormat
                        + df.format(value) + ")"
                );
                values.add(value);
                colors.add(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            } else {
                // set notification symbols and red color for negative values
                labels.add(Constants.DATA_FILE_HEADERS[i] + " (---, " + df.format(value) + ")");
                values.add(0.0);
                colors.add(Color.RED);
            }
        }

        // set panel dimensions
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // set pie chart size and coordinates
        int pieSize = Math.min(panelWidth, panelHeight) / 2 - 20;
        int pieX = 20;
        int pieY = (panelHeight - pieSize) / 2;

        // draw pie chart angles
        double startAngle = 0;
        for (int i = 0; i < values.size(); i++) {
            double angle = values.get(i) > 0 ? values.get(i) / total * 360 : 0;
            g2.setColor(colors.get(i));
            g2.fillArc(pieX, pieY, pieSize, pieSize, (int) Math.round(startAngle), (int) Math.round(angle));
            startAngle += angle;
        }

        // add pie chart title
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, PIE_CHART_TITLE_FONT_SIZE));
        String title = Constants.DATA_FILE_HEADERS[startIndex] + " (" + dataRecord[startIndex] + ")";
        g2.drawString(title, pieX, pieY - 10);

        // put labels on the right side of the panel
        g2.setFont(new Font("SansSerif", Font.PLAIN, PIE_CHART_LABEL_FONT_SIZE));
        int labelX = pieX + pieSize + 20;
        int labelY = pieY;
        int lineHeight = 20;

        // draw labels and their values
        for (int i = 0; i < labels.size(); i++) {
            g2.setColor(values.get(i) > 0 ? colors.get(i) : Color.RED);
            g2.drawString(labels.get(i), labelX, labelY);
            labelY += lineHeight;
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
