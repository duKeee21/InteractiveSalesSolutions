package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class MergerCustomersFrom2files {

    public List<Customer> mergeCustomers(List<Customer> customersFromFile1, List<Customer> customersFromFile2) {

        List<Customer> mergedCustomers = new ArrayList<>();
        mergedCustomers.addAll(customersFromFile1);
        mergedCustomers.addAll(customersFromFile2);

        mergedCustomers.sort(null);

        return mergedCustomers;
    }
}
