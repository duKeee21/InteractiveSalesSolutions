package example.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyWriterToFileTxtTest {

    private final MyWriterToFileTxt writer = new MyWriterToFileTxt();

    private Path expectedFilePath1;
    private Path expectedFilePath2;

    @AfterEach
    void tearDown() throws IOException {
        if (expectedFilePath1 != null) {
            Files.deleteIfExists(expectedFilePath1);
        }
        if (expectedFilePath2 != null) {
            Files.deleteIfExists(expectedFilePath2);
        }
    }

    @Test
    void testWrite_Success() throws IOException {

        Map<String, Double> data = new LinkedHashMap<>();
        data.put("Apple", 150.50);
        data.put("OnePlus", 80.0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");
        String timeBefore = LocalDateTime.now().format(formatter);
        expectedFilePath1 = Paths.get(timeBefore);

        writer.write(data);

        String timeAfter = LocalDateTime.now().format(formatter);
        expectedFilePath2 = Paths.get(timeAfter);

        Path actualFile = Files.exists(expectedFilePath1) ? expectedFilePath1 : expectedFilePath2;

        assertTrue(Files.exists(actualFile));

        List<String> lines = Files.readAllLines(actualFile);

        assertEquals(2, lines.size());
        assertEquals("Apple — 150.5", lines.get(0));
        assertEquals("OnePlus — 80.0", lines.get(1));
    }

    @Test
    void testWrite_EmptyMap() throws IOException {
        Map<String, Double> emptyData = Map.of();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");
        expectedFilePath1 = Paths.get(LocalDateTime.now().format(formatter));

        writer.write(emptyData);

        expectedFilePath2 = Paths.get(LocalDateTime.now().format(formatter));

        Path actualFile = Files.exists(expectedFilePath1) ? expectedFilePath1 : expectedFilePath2;

        assertTrue(Files.exists(actualFile));

        List<String> lines = Files.readAllLines(actualFile);
        assertTrue(lines.isEmpty());
    }
}