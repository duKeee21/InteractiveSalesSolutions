import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class CustomerReader {
    char splitter = '|';
    LocalDateTime date;

    public static ArrayList<Customer> readAndSort(String filePath, char splitter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        ArrayList<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath));) {



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
