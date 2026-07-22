import service.OrderCalculationService;

void main() throws IOException {
    String filePathPipe = "discount_day.txt";
    String filePathHash = "discount_day_without_ext";
    String resultFile = "resultFile4";
    OrderCalculationService calculate = new OrderCalculationService();
    calculate.calculateOrders(filePathHash, filePathPipe, resultFile, 50, 0.5, 0.05);

}