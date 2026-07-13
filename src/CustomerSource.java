import java.io.IOException;
import java.util.List;

public interface CustomerSource {
    List<Customer> readCustomers() throws IOException;
}
