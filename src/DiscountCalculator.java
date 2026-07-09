import java.util.ArrayList;

public class DiscountCalculator {
    public static ArrayList<Double> calculateOrder(ArrayList<Double> order) {
        if (order == null || order.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Double> money = new ArrayList<>();
        double discountStart = 0.5;

        for (int i = 0; i < order.size(); i++) {
            double price = order.get(i);
            double orderSum = price * (1 - discountStart);
            money.add(orderSum);

            discountStart -= 0.05;
            if (discountStart < 0) {
                discountStart = 0;
            }
        }

        return money;
    }

}
