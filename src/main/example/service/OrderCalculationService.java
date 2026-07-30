package example.service;

import example.adapter.AdapterHash;
import example.adapter.AdapterPipe;
import example.api.CustomerSource;
import example.io.MyReader;
import example.io.MyWriterToFileTxt;
import example.model.Customer;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderCalculationService {

    private final CustomerSource customersHash;
    private final CustomerSource customersPipe;
    private final MyReader myReader;
    private final OrderExtractionService orderExtractionService;
    private final MyWriterToFileTxt myWriterToFileTxt;


    public OrderCalculationService() {
        this(new AdapterHash(), new AdapterPipe(), new MyReader(), new OrderExtractionService(), new MyWriterToFileTxt());
    }

    public OrderCalculationService(CustomerSource customersHash,
                                   CustomerSource customersPipe,
                                   MyReader myReader,
                                   OrderExtractionService orderExtractionService,
                                   MyWriterToFileTxt myWriterToFileTxt) {
        this.customersHash = customersHash;
        this.customersPipe = customersPipe;
        this.myReader = myReader;
        this.orderExtractionService = orderExtractionService;
        this.myWriterToFileTxt = myWriterToFileTxt;
    }

    public void calculateOrders(String filePath, DiscountConfig config) throws IOException {

        String firstLine = myReader.readFirstLine(filePath);

        CustomerSource adapter = firstLine.contains("#") ? customersHash : customersPipe;

        List<Customer> customersFromFile = adapter.readCustomers(filePath);

        List<Customer> sortedCustomers = customersFromFile.stream()
                .sorted(Comparator.comparing(Customer::getOrderDate).reversed())
                .toList();

        List<Double> allOrderCounts = orderExtractionService.extractOrder(sortedCustomers);

        DiscountCalculator discountCalculator = new DiscountCalculator(config);
        List<Double> allMoney = discountCalculator.calculateOrder(allOrderCounts);

        ConversionForResult conversionForResult = new ConversionForResult();

        Map<String, Double> result = conversionForResult.conversion(sortedCustomers, allMoney);

        myWriterToFileTxt.write(result);
    }
}