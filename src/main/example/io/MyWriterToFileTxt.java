package example.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MyWriterToFileTxt {

    public void write(Map<String, Double> map) throws IOException {
        String now = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy"))
                .replace("T", "_")
                .replace(":", "-");

        Path path = Paths.get(now);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                writer.write(entry.getKey() + " — " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
