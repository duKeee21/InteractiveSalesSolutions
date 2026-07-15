package adapter;

import CustomerDataSource.CustomerSource;
import CustomerDataSource.CustomersReader;
import model.Customer;

import java.io.IOException;
import java.util.List;

public class AdapterHash extends CustomersReader implements CustomerSource {
    String path;

    public AdapterHash(String path) {
        super("#");
        this.path = path;
    }

    @Override
    public List<Customer> readOrders() throws IOException {
        return readCustomers(path);
    }
}
