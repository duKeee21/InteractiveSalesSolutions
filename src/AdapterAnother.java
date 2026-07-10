import java.util.List;

public class AdapterAnother implements CustomerSource{



    @Override
    public List<Customer> read() {
        return List.of();
    }
}
