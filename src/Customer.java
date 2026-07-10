import java.time.LocalDateTime;

public class Customer implements Comparable<Customer> {
    final LocalDateTime orderDate;
    final String companyName;
    final double orderCount;
//    final double amountOrder;

    public Customer(LocalDateTime orderDate, String companyName, double orderCount) {
        this.orderDate = orderDate;
        this.companyName = companyName;
        this.orderCount = orderCount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getOrderCount() {
        return orderCount;
    }

    @Override
    public int compareTo(Customer customer) {
        return this.orderDate.compareTo(customer.orderDate);
    }
}