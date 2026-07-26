package service;

import adapter.AdapterHash;
import adapter.AdapterPipe;
import api.CustomerSource;
import io.MyReader;
import io.MyWriterToFileTxt;
import model.Customer;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderCalculationService {

    private final CustomerSource customersHash;
    private final CustomerSource customersPipe;
    private final MyReader myReader;
    private final OrderExtractionService orderExtractionService;

    public OrderCalculationService() {
        this.customersHash = new AdapterHash();
        this.customersPipe = new AdapterPipe();
        this.myReader = new MyReader();
        this.orderExtractionService = new OrderExtractionService();
    }


    public void calculateOrders(String filePath, DiscountConfig config) throws IOException {

        String firstLine = myReader.readFirstLine(filePath);

        CustomerSource adapter;

        if (firstLine.contains("#")) {

            adapter = customersHash;
        } else {

            adapter = customersPipe;
        }

        List<Customer> customersFromFile = adapter.readCustomers(filePath);

        List<Customer> sortedCustomers = customersFromFile.stream()
                .sorted(Comparator.comparing(Customer::getOrderDate).reversed())
                .toList();

        List<Double> allOrderCounts = orderExtractionService.extractOrder(sortedCustomers);

        DiscountCalculator discountCalculator = new DiscountCalculator(config);
        List<Double> allMoney = discountCalculator.calculateOrder(allOrderCounts);

        ConversionForResult conversionForResult = new ConversionForResult();

        Map<String, Double> result = conversionForResult.conversion(sortedCustomers, allMoney);

        MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
        myWriterToFileTxt.write(result);
    }
}