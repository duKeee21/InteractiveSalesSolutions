package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class OrderExtractionService {

    public List<Double> extractOrder(List<Customer> customers) {
        List<Double> orderedCement = new ArrayList<>();
        for (Customer current : customers) {
            orderedCement.add(current.getOrderCount());
        }
        return orderedCement;
    }
}