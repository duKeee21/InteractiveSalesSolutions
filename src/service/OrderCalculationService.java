package service;

import adapter.AdapterHash;
import adapter.AdapterPipe;
import api.CustomerSource;
import io.MyWriterToFileTxt;
import model.Customer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderCalculationService {

    private final CustomerSource customersHash;
    private final CustomerSource customersPipe;
    private final MergerCustomersFrom2files merger;
    private final OrderExtractionService orderExtractionService;

    public OrderCalculationService() {
        this.customersHash =  new AdapterHash();
        this.customersPipe = new AdapterPipe();
        this.merger = new MergerCustomersFrom2files();
        this.orderExtractionService = new OrderExtractionService();
    }


    public void calculateOrders(String filePathHash, String filePathPipe, String resultFile, double pricePerKg, double discount, double discountStep) throws IOException {

        DiscountCalculator discountCalculator = new DiscountCalculator(pricePerKg, discount, discountStep);

        List<Customer> customersFileTxt = customersHash.readCustomers(filePathHash);
        List<Customer> customersFile = customersPipe.readCustomers(filePathPipe);

        List<Customer> mergedCustomers = merger.mergeCustomers(customersFile, customersFileTxt);

        List<Double> allOrderCounts = orderExtractionService.extractOrder(mergedCustomers);
        List<Double> allMoney = discountCalculator.calculateOrder(allOrderCounts);

        ConversionForResult conversionForResult = new ConversionForResult();
        Map<String, Double> result = conversionForResult.conversion(mergedCustomers, allMoney);
        MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
        myWriterToFileTxt.write(result, resultFile);
    }
}