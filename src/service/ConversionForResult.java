package service;

import model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ConversionForResult {
    protected Map<String, Double> conversion(List<Customer> customers, List<Double> discounts) {

        Map<String, Double> result = new HashMap<>();
        for (int i = 0; i < customers.size(); i++) {
            String companyName = customers.get(i).getCompanyName();
            Double companySum = discounts.get(i);
            result.merge(companyName, companySum, Double::sum);
        }
        return result;
    }
}
