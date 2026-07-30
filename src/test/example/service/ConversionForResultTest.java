package example.service;

import example.model.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConversionForResultTest {

    private final ConversionForResult converter = new ConversionForResult();

    @Test
    void testConversion_Success_DiscountsByCompany() {
        LocalDateTime now = LocalDateTime.now();
        List<Customer> customers = List.of(
                new Customer(now, "Vytaz", 10.0),
                new Customer(now, "Chizh", 20.0),
                new Customer(now, "Vytaz", 15.0)
        );
        List<Double> discounts = List.of(100.0, 50.0, 200.0);

        Map<String, Double> result = converter.conversion(customers, discounts);

        assertEquals(2, result.size());
        assertEquals(300.0, result.get("Vytaz"), 0.001);
        assertEquals(50.0, result.get("Chizh"), 0.001);
    }

    @Test
    void testConversion_EmptyLists() {
        List<Customer> customers = List.of();
        List<Double> discounts = List.of();

        Map<String, Double> result = converter.conversion(customers, discounts);

        assertTrue(result.isEmpty());
    }

    @Test
    void testConversion_SingleCustomer() {
        Customer customer = new Customer(LocalDateTime.now(), "Polaris", 5.0);
        List<Customer> customers = List.of(customer);
        List<Double> discounts = List.of(75.5);

        Map<String, Double> result = converter.conversion(customers, discounts);

        assertEquals(1, result.size());
        assertEquals(75.5, result.get("Polaris"), 0.001);
    }

    @Test
    void testConversion_DiscountsShorterThanCustomers_ThrowsException() {
        List<Customer> customers = List.of(
                new Customer(LocalDateTime.now(), "Google", 10.0),
                new Customer(LocalDateTime.now(), "Yandex", 20.0)
        );
        List<Double> discounts = List.of(100.0);

        assertThrows(IndexOutOfBoundsException.class, () -> converter.conversion(customers, discounts));
    }

    @Test
    void testConversion_DiscountsLongerThanCustomers_IgnoresExtraDiscounts() {
        List<Customer> customers = List.of(
                new Customer(LocalDateTime.now(), "Hyundai", 10.0)
        );
        List<Double> discounts = List.of(100.0, 500.0, 900.0);

        Map<String, Double> result = converter.conversion(customers, discounts);

        assertEquals(1, result.size());
        assertEquals(100.0, result.get("Hyundai"), 0.001);
    }
}