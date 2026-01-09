package gr.aueb.javaeight;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class DataFileBuilderTest {

    @Test
    void dataFileCreator_createsFileSuccessfully() throws IOException {
        // Δημιουργία προσωρινού αρχείου για το test
        File tempFile = Files.createTempFile("datafile_test_", ".csv").toFile();
        // το σβήνουμε για να ελέγξουμε τη δημιουργία
        tempFile.delete();

        String result = DataFileBuilder.dataFileCreator(tempFile.getAbsolutePath());

        assertEquals("created", result);
        assertTrue(tempFile.exists());

        // cleanup
        tempFile.delete();
    }

    @Test
    void dataFileCreator_returnsExistsIfFileAlreadyExists() throws IOException {
        File tempFile = Files.createTempFile("datafile_test_", ".csv").toFile();

        String result = DataFileBuilder.dataFileCreator(tempFile.getAbsolutePath());

        assertEquals("exists", result);

        // cleanup
        tempFile.delete();
    }
}
