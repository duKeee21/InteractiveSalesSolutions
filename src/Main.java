import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePathTxt = "discount_day.txt";
        String filePath = "discount_day_without_ext";

        AdapterForTxt adapterForTxt = new AdapterForTxt(filePathTxt);
        DiscountCalculator discountCalculator = new DiscountCalculator(200,0.5,0.05);

        List<Customer> customers = adapterForTxt.read();
        customers.sort(null);

        ArrayList<Double> orderedCement = new ArrayList<>();
        for (Customer current : customers) {
            orderedCement.add(current.getOrderCount());
        }

        ArrayList<Double> money = discountCalculator.calculateOrder(orderedCement);

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println(c.getCompanyName() + " " + money.get(i));

        }

    }
}