import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdapterForTxt implements CustomerSource {
    private final String path;
    private final String splitter;

    public AdapterForTxt(String path, String splitter) {
        this.path = path;
        this.splitter = splitter;
    }

    MyReader myReader = new MyReader();

    @Override
    public List<Customer> read() throws IOException {
        List<Customer> customers = new ArrayList<>();
        for (String line : myReader.readLines(path)) {
            String[] parts = line.split(splitter);
            LocalDateTime orderDate = LocalDateTime.parse(parts[0]);
            String companyName = parts[1];
            double orderCount = Double.parseDouble(parts[2]);
            customers.add(new Customer(orderDate, companyName, orderCount));
        }
        return customers;
    }

}
