import java.io.IOException;
import java.util.List;

public interface CustomerSource {
    List<Customer> read() throws IOException;
}
