import CustomerDataSource.CustomerSource;
import adapter.AdapterHash;
import adapter.AdapterPipe;
import model.Customer;
import service.DiscountCalculator;
import io.MyWriterToFileTxt;

void main() throws IOException {
    String filePathPipe = "discount_day.txt";
    String filePathHash = "discount_day_without_ext";

    CustomerSource customersHash = new AdapterHash(filePathHash);
    CustomerSource customersPipe = new AdapterPipe(filePathPipe);

    DiscountCalculator discountCalculator = new DiscountCalculator(50, 0.5, 0.05);

    List<Customer> customersFileTxt = customersPipe.readOrders();
    List<Customer> customersFile = customersHash.readOrders();


    List<Customer> customers = new ArrayList<>();
    customers.addAll(customersFileTxt);
    customers.addAll(customersFile);
    customers.sort(null);

    List<Double> orderedCement = new ArrayList<>();
    for (Customer current : customers) {
        orderedCement.add(current.getOrderCount());
    }

    List<Double> money = discountCalculator.calculateOrder(orderedCement);


    Map<String, Double> result = new HashMap<>();
    for (int i = 0; i < customers.size(); i++) {
        String companyName = customers.get(i).getCompanyName();
        Double companySum = money.get(i);
        result.merge(companyName, companySum, Double::sum);
    }

    MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
    myWriterToFileTxt.write(result, "resultFile.txt");

}