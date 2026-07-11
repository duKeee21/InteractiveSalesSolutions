import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePathTxt = "discount_day.txt";
        String filePath = "discount_day_without_ext";

        AdapterForTxt adapterForTxt = new AdapterForTxt(filePath, "#");
        AdapterForTxt adapterForTxt1 = new AdapterForTxt(filePathTxt, "\\|");
        DiscountCalculator discountCalculator = new DiscountCalculator(200, 0.5, 0.05);

        List<Customer> customersFileTxt = adapterForTxt.read();
        List<Customer> customersFile = adapterForTxt1.read();


        List<Customer> customers = new ArrayList<>();
        customers.addAll(customersFileTxt);
        customers.addAll(customersFile);
        customers.sort(null);

        List<Double> orderedCement = new ArrayList<>();
        for (Customer current : customers) {
            orderedCement.add(current.getOrderCount());
        }

        List<Double> money = discountCalculator.calculateOrder(orderedCement);


        Map<String, Double> result = new HashMap<>();
        for (int i = 0; i < customers.size(); i++) {
            String companyName = customers.get(i).getCompanyName();
            Double companySum = money.get(i);
            result.merge(companyName, companySum, Double::sum);
        }

        MyWriterToFileTxt myWriterToFileTxt = new MyWriterToFileTxt();
        myWriterToFileTxt.write(result, "resultFile.txt");

    }
}