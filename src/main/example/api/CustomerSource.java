package example.api;

import example.model.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerSource {
    List<Customer> readCustomers(String path) throws IOException;
}
