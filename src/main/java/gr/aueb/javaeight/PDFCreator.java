package gr.aueb.javaeight;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 * Creates budget report in PDF format using Apache PDFBox.
 * Also picks proper font for greek characters representation.
 */
public class PDFCreator {

    private static final float MARGIN = 40;
    private static final float ROW_HEIGHT = 10;
    private static final float HEADER_COL_WIDTH = 400;
    private static final float VALUE_COL_WIDTH = 40;
    private static final float FONT_SIZE = 7;

    /**
     * 
     * @param dataFilePath absolute path to data file
     * @throws IOException I/O error exception
     */
    public static void createPDF(String dataFilePath) throws IOException, URISyntaxException {

        // load csv data in 2d array
        FileDataProvider provider = new FileDataProvider(dataFilePath);
        String[][] data = provider.getDataFile2D();

        String[] headers = data[0];

        // create groups if any (in our case, 3-header), inserts them into structure
        List<HeaderGroup> groups = new ArrayList<>();
        for (int i = 1; i < headers.length; ) {
            String header = headers[i];
            int count = 1;
            while (i + count < headers.length && headers[i + count].equals(header)) {
                count++;
            }
            groups.add(new HeaderGroup(header, i, count));
            i += count;
        }

        try (PDDocument document = new PDDocument()) {


            // for every year in data file
            for (int row = 1; row < data.length; row++) {

                // reset 3-header written check
                boolean tripleHeaderWritten = false;

                PDPage page = new PDPage(PDRectangle.A4);
                page.setRotation(0);
                document.addPage(page);

                try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                    float pageHeight = page.getMediaBox().getHeight();
                    float y = pageHeight - MARGIN;
                    // load font
                    PDType0Font font = PDType0Font.load(
                            document,
                            PDFCreator.class.getResourceAsStream("/fonts/DejaVuSans.ttf")
                    );  PDType0Font fontBold = font;
                    // write year
                    writeText(cs, "Έτος: " + data[row][0], MARGIN, y, fontBold);
                    y -= ROW_HEIGHT * 1.5f;
                    // loop through groups
                    for (HeaderGroup g : groups) {
                        float x = MARGIN;
                        
						// if 3 headers group and headers not written yet
						if (g.count == 3 && !tripleHeaderWritten) {

							// write a new line
							y -= ROW_HEIGHT;

							float subX = MARGIN + HEADER_COL_WIDTH;

							// write 3-headers
							writeText(cs, "Τακτικός", subX, y, fontBold);
							writeText(cs, "Δημ. Επενδ.", subX + VALUE_COL_WIDTH, y, fontBold);
							writeText(cs, "Γεν. Σύνολο", subX + 2 * VALUE_COL_WIDTH, y, fontBold);

                            y -= ROW_HEIGHT;
                            
                            // only write triple header once
                            tripleHeaderWritten = true;
						}

						// write data line
						writeText(cs, g.name, x, y, fontBold);
						x += HEADER_COL_WIDTH;

						for (int i = 0; i < g.count; i++) {
							writeText(cs, data[row][g.startIndex + i], x, y, font);
							x += VALUE_COL_WIDTH;
						}

						y -= ROW_HEIGHT;
                    }
                }
            }

            Path out = Paths.get(System.getProperty("user.dir")).resolve("report.pdf");

            // Save PDF
            //Files.createDirectories(out.getParent());
            document.save(out.toFile());

            JOptionPane.showMessageDialog(null,
                    "PDF report created at: " + out);

            // Optional: open PDF automatically if supported
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(out.toFile());
            }
        }
    }

    /**
     * Writes text into PDF file.
     * @param cs content stream
     * @param text text to be added
     * @param x offset coordinate
     * @param y offset coordinate
     * @param font font type
     * @throws IOException
     */
    private static void writeText(PDPageContentStream cs,
                                  String text,
                                  float x,
                                  float y,
                                  PDType0Font font) throws IOException {
        cs.beginText();
        cs.setFont(font, FONT_SIZE);
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
    }

    /**
     * Creates header groups if any.
    */
    static class HeaderGroup {
        String name;
        int startIndex;
        int count;

        /**
         * 
         * @param name header group name
         * @param startIndex header group start index
         * @param count header group counter
         */
        HeaderGroup(String name, int startIndex, int count) {
            this.name = name;
            this.startIndex = startIndex;
            this.count = count;
        }
    }
}
