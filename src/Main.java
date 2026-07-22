import service.CalculateAllOrder;

void main() throws IOException {
    String filePathPipe = "discount_day.txt";
    String filePathHash = "discount_day_without_ext";
    String resultFile = "resultFile2";
    CalculateAllOrder calculate = new CalculateAllOrder();
    calculate.calculate(filePathHash, filePathPipe, resultFile, 50, 0.5, 0.05);

}