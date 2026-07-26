package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyReader {

    public List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public String readFirstLine(String filePath) throws IOException {
        try (var lines = Files.lines(Paths.get(filePath))) {
            return lines.findFirst().orElse(" ");
        }
    }
}
