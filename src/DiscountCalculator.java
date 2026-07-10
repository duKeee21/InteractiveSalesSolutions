import java.util.ArrayList;

public class DiscountCalculator {

    private double pricePerKg;
    private double discount;
    private double discountStep;

    public DiscountCalculator(double pricePerKg, double discount, double discountStep) {
        this.pricePerKg = pricePerKg;
        this.discount = discount;
        this.discountStep = discountStep;
    }

    public ArrayList<Double> calculateOrder(ArrayList<Double> order) {
        if (order == null || order.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Double> money = new ArrayList<>();
        double currentDiscount = discount;

        for (int i = 0; i < order.size(); i++) {
            double orderKg = order.get(i);
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
