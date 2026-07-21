package adapter;

import api.CustomerSource;
import io.MyReader;
import model.Customer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdapterHash implements CustomerSource {

    MyReader myReader = new MyReader();

    @Override
    public List<Customer> readCustomers(String path) throws IOException {
        List<Customer> customers = new ArrayList<>();
        for (String line : myReader.readLines(path)) {
            String[] parts = line.split("#");
            LocalDateTime orderDate = LocalDateTime.parse(parts[0]);
            String companyName = parts[1];
            double orderCount = Double.parseDouble(parts[2]);
            customers.add(new Customer(orderDate, companyName, orderCount));
        }
        return customers;
    }

}
