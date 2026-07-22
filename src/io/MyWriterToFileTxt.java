package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MyWriterToFileTxt {

    public void write(Map<String, Double> map, String filePath) throws IOException {

        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                writer.write(entry.getKey() + "---" + entry.getValue());
                writer.newLine();
            }
        }
    }
}
