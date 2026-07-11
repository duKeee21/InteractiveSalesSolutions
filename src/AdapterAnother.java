//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdapterAnother implements CustomerSource {
//
//    MyReader myReader = new MyReader();
//    String path;
//
//    public AdapterAnother(String path) {
//        this.path = path;
//    }
//
//    @Override
//    public List<Customer> read(char splitter) throws IOException {
//        List<Customer> customers = new ArrayList<>();
//        for (String line : myReader.readLines(path)) {
//            String[] parts = line.split(String.valueOf(splitter));
//            LocalDateTime orderDate = LocalDateTime.parse(parts[0]);
//            String companyName = parts[1];
//            double orderCount = Double.parseDouble(parts[2]);
//            customers.add(new Customer(orderDate, companyName, orderCount));
//        }
//        return customers;
//    }
//}
