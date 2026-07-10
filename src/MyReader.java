import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyReader {
    public List<String> readLines(String filePath) throws IOException {
        return new ArrayList<>(Files.readAllLines(Paths.get(filePath)));
    }

}
