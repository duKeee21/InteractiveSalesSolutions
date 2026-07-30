package example.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyReaderTest {

    private final MyReader reader = new MyReader();
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("myReader_test_", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadLines_Success() throws IOException {
        List<String> expectedLines = List.of("Line 1", "Line 2", "Line 3");
        Files.write(tempFile, expectedLines);

        List<String> actualLines = reader.readLines(tempFile.toString());

        assertEquals(3, actualLines.size());
        assertEquals(expectedLines, actualLines);
    }

    @Test
    void testReadLines_EmptyFile() throws IOException {
        List<String> actualLines = reader.readLines(tempFile.toString());

        assertTrue(actualLines.isEmpty());
    }

    @Test
    void testReadLines_FileNotFound() {
        String notExistingFilePath = "some/non/existing/path_" + System.currentTimeMillis() + ".txt";

        assertThrows(IOException.class, () -> reader.readLines(notExistingFilePath));
    }

    @Test
    void testReadFirstLine_Success() throws IOException {

        Files.write(tempFile, List.of("First Line", "Second Line"));

        String firstLine = reader.readFirstLine(tempFile.toString());

        assertEquals("First Line", firstLine);
    }

    @Test
    void testReadFirstLine_EmptyFile() throws IOException {
        String firstLine = reader.readFirstLine(tempFile.toString());

        assertEquals(" ", firstLine);
    }

    @Test
    void testReadFirstLine_FileNotFound() {
        String notExistingFilePath = "some/non/existing/path_" + System.currentTimeMillis() + ".txt";

        assertThrows(IOException.class, () -> reader.readFirstLine(notExistingFilePath));
    }
}