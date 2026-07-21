import api.CustomerSource;
import adapter.AdapterHash;
import adapter.AdapterPipe;
import io.MyWriterToFileTxt;
import model.Customer;
import service.ConversionForResult;
import service.DiscountCalculator;
import service.MergerCustomersFrom2files;
import service.OrderExtractionService;

void main() throws IOException {
    String filePathPipe = "discount_day.txt";
    String filePathHash = "discount_day_without_ext";

    CustomerSource customersHash = new AdapterHash();
    CustomerSource customersPipe = new AdapterPipe();

    List<Customer> customersFileTxt = customersHash.readCustomers(filePathHash);
    List<Customer> customersFile = customersPipe.readCustomers(filePathPipe);

    DiscountCalculator discountCalculator = new DiscountCalculator(50, 0.5, 0.05);

    MergerCustomersFrom2files merger = new MergerCustomersFrom2files();
    List<Customer> mergedCustomers = merger.mergeCustomers(customersFile, customersFileTxt);

    OrderExtractionService orderExtractionService = new OrderExtractionService();
    List<Double> allOrderCounts = orderExtractionService.extractOrder(mergedCustomers);
    List<Double> allMoney = discountCalculator.calculateOrder(allOrderCounts);

    ConversionForResult conversionForResult = new ConversionForResult();
    Map<String, Double> result = conversionForResult.conversion(mergedCustomers, allMoney);

    MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
    myWriterToFileTxt.write(result, "resultFile.txt");
}