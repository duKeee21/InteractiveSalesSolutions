package CustomerDataSource;

import model.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerSource {
    List<Customer> readOrders() throws IOException;
}
