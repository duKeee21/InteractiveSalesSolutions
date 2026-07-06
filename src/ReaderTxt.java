import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReaderTxt  {

    public List<String> readFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            System.out.println(line);
        }
        return lines;
    }

    public void getData() {

    }

    public void getCompanyName() {

    }

    public void getCompanyMoney() {

    }
}