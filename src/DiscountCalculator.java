import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {

    private final double pricePerKg;
    private double discount;
    private final double discountStep;

    public DiscountCalculator(double pricePerKg, double discount, double discountStep) {
        this.pricePerKg = pricePerKg;
        this.discount = discount;
        this.discountStep = discountStep;
    }

    public List<Double> calculateOrder(List<Double> order) {
        if (order == null || order.isEmpty()) {
            return new ArrayList<>();
        }

        List<Double> money = new ArrayList<>();
        double currentDiscount = discount;

        for (double orderKg : order) {
            double orderSum = orderKg * pricePerKg * (1 - discount);
            money.add(orderSum);

            currentDiscount -= discountStep;
            if (discount < 0) {
                discount = 0;
            }
        }

        return money;
    }

}
