package service;

import adapter.AdapterHash;
import adapter.AdapterPipe;
import api.CustomerSource;
import io.MyWriterToFileTxt;
import model.Customer;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderCalculationService {

    private final CustomerSource customersHash;
    private final CustomerSource customersPipe;
    private final MergerCustomersFrom2files merger;
    private final OrderExtractionService orderExtractionService;

    public OrderCalculationService() {
        this.customersHash = new AdapterHash();
        this.customersPipe = new AdapterPipe();
        this.merger = new MergerCustomersFrom2files();
        this.orderExtractionService = new OrderExtractionService();
    }


    public void calculateOrders(String filePathHash, String filePathPipe, DiscountConfig config) throws IOException {

        DiscountCalculator discountCalculator = new DiscountCalculator(config);

        List<Customer> customersFileTxt = customersHash.readCustomers(filePathHash);
        List<Customer> customersFile = customersPipe.readCustomers(filePathPipe);

        List<Customer> mergedCustomers = merger.mergeCustomers(customersFile, customersFileTxt);

        List<Customer> sortedCustomers = mergedCustomers.stream()
                .sorted(Comparator.comparing(Customer::getOrderDate).reversed())
                .toList();

        List<Double> allOrderCounts = orderExtractionService.extractOrder(sortedCustomers);
        List<Double> allMoney = discountCalculator.calculateOrder(allOrderCounts);

        ConversionForResult conversionForResult = new ConversionForResult();

        Map<String, Double> result = conversionForResult.conversion(mergedCustomers, allMoney);

        MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
        myWriterToFileTxt.write(result);
    }
}