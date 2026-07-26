import service.DiscountConfig;
import service.OrderCalculationService;

void main() throws IOException {
    String filePathPipe = "discount_day.txt";
    String filePathHash = "discount_day_without_ext";

    OrderCalculationService calculate = new OrderCalculationService();
    DiscountConfig config = new DiscountConfig(50.0, 0.5, 0.05);

    calculate.calculateOrders(filePathHash, config);
    calculate.calculateOrders(filePathPipe, config);

}