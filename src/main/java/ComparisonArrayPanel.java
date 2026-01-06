import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Creates a panel where data from country .csv file is being represented.
 */
public class ComparisonArrayPanel extends JPanel {
    /**
     * Places .csv data from data file into a 2D array.
     * Uses array to create and properly form labels to be put on panel.
     * Labels represent data using a certain format.
     * @param filePath
     * @param fromArrayPosition
     * @param toArrayPosition
     * @param gridColumns
     * @param selectedYear
     * @throws IOException
     */
    public ComparisonArrayPanel(
            String filePath,
            int fromArrayPosition,
            int toArrayPosition,
            int gridColumns,
            String selectedYear) throws IOException {

        // get data from file into 2D array
        FileDataProvider fdg = new FileDataProvider(filePath);
        String[][] data = fdg.getDataFile2D();

        // seek and fetch selected year
        int yearRow = -1;
        for (int i = 1; i < data.length; i++) {
            if (data[i][0].equals(selectedYear)) {
                yearRow = i;
                break;
            }
        }

        // configure panel top to bottom layout and gaps
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(4, 6, 4, 6));

        Font font = new Font("SansSerif", Font.PLAIN, 11);

        int col = fromArrayPosition;

        while (col < toArrayPosition) {

            String header = data[0][col];

            // count how many times header repeats (but not more than gridColumns-1)
            int repeatCount = 1;
            while (repeatCount < gridColumns &&
                   col + repeatCount < toArrayPosition &&
                   data[0][col + repeatCount].equals(header)) {
                repeatCount++;
            }

            // create header label
            JLabel headerLabel = new JLabel(header);
            headerLabel.setFont(font);

            // create value labels according to repeat count
            StringBuilder values = new StringBuilder();
            if (yearRow != -1) {
                for (int i = 0; i < repeatCount; i++) {
                    if (i > 0) values.append("  |  ");
                    values.append(data[yearRow][col + i]);
                }
            }

            // assemble value label
            JLabel valueLabel = new JLabel(values.toString());
            valueLabel.setFont(font);
            valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // create row as panel - add header on left, values on right
            JPanel row = new JPanel(new BorderLayout(6, 0));
            row.add(headerLabel, BorderLayout.WEST);
            row.add(valueLabel, BorderLayout.EAST);

            // set maximum size to avoid stretching in Y direction
            row.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    headerLabel.getPreferredSize().height + 2
            ));

            // add row to main panel
            add(row);

            // shift column index by repeat count
            col += repeatCount;
        }
    }
}
