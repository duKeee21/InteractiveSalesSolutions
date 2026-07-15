package CustomerDataSource;

import io.MyReader;
import model.Customer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomersReader {
    String splitter;
    MyReader myReader = new MyReader();

    public CustomersReader(String splitter) {
        this.splitter = splitter;
    }

    public List<Customer> readCustomers(String path) throws IOException {
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
