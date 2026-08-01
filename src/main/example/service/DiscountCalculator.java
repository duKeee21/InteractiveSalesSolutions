package example.service;

import java.util.ArrayList;
import java.util.List;

class DiscountCalculator {

    private final DiscountConfig config;

    public DiscountCalculator(DiscountConfig config) {
        this.config = config;
    }

    public List<Double> calculateOrder(List<Double> order) {
        if (order == null || order.isEmpty()) {
            return new ArrayList<>();
        }

        double pricePerKg = config.getPricePerKg();
        double discount = config.getDiscount();
        double discountStep = config.getDiscountStep();

        List<Double> money = new ArrayList<>();
        double currentDiscount = discount;

        for (double orderKg : order) {
            double orderSum = orderKg * pricePerKg * (1 - currentDiscount);
            money.add(orderSum);

            currentDiscount -= discountStep;
            if (currentDiscount < 0) {
                currentDiscount = 0;
            }
        }

        return money;
    }
}
