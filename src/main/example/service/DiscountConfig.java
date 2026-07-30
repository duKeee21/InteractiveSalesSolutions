package example.service;

public class DiscountConfig {

    private final double pricePerKg;
    private final double discount;
    private final double discountStep;

    public DiscountConfig(double pricePerKg, double discount, double discountStep) {
        this.pricePerKg = pricePerKg;
        this.discount = discount;
        this.discountStep = discountStep;
    }

    double getPricePerKg() {
        return pricePerKg;
    }

    double getDiscount() {
        return discount;
    }

    double getDiscountStep() {
        return discountStep;
    }
}
