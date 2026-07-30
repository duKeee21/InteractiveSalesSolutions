package example.service;

import example.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderExtractionServiceTest {

    private OrderExtractionService orderExtractionService;

    @BeforeEach
    void setUp() {
        orderExtractionService = new OrderExtractionService();
    }

    @Test
    void extractOrder_WithValidCustomers_ShouldReturnOrderCounts() {
        Customer customer1 = new Customer(LocalDateTime.now(), "Customer1", 100.0);
        Customer customer2 = new Customer(LocalDateTime.now(), "Customer2", 200.0);
        Customer customer3 = new Customer(LocalDateTime.now(), "Customer3", 300.0);

        List<Customer> customers = List.of(customer1, customer2, customer3);

        List<Double> result = orderExtractionService.extractOrder(customers);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(100.0, result.get(0), 0.001);
        assertEquals(200.0, result.get(1), 0.001);
        assertEquals(300.0, result.get(2), 0.001);
    }

    @Test
    void extractOrder_WithEmptyCustomerList_ShouldReturnEmptyList() {
        List<Double> result = orderExtractionService.extractOrder(Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void extractOrder_WithSingleCustomer_ShouldReturnSingleElementList() {
        Customer customer = new Customer(LocalDateTime.now(), "Test", 150.5);

        List<Double> result = orderExtractionService.extractOrder(List.of(customer));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(150.5, result.getFirst(), 0.001);
    }

    @Test
    void extractOrder_WithZeroOrderCount_ShouldReturnZero() {
        Customer customer = new Customer(LocalDateTime.now(), "Test", 0.0);

        List<Double> result = orderExtractionService.extractOrder(List.of(customer));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(0.0, result.getFirst(), 0.001);
    }

    @Test
    void extractOrder_WithNegativeOrderCount_ShouldReturnNegative() {
        Customer customer = new Customer(LocalDateTime.now(), "Test", -50.0);

        List<Double> result = orderExtractionService.extractOrder(List.of(customer));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(-50.0, result.getFirst(), 0.001);
    }


    @Test
    void extractOrder_ShouldReturnNewArrayListInstance() {
        Customer customer = new Customer(LocalDateTime.now(), "Test", 100.0);
        List<Customer> customers = List.of(customer);

        List<Double> result1 = orderExtractionService.extractOrder(customers);
        List<Double> result2 = orderExtractionService.extractOrder(customers);

        assertNotSame(result1, result2);
        assertEquals(result1, result2);
    }

    @Test
    void extractOrder_WithNullCustomer_ShouldThrowException() {
        List<Customer> customers = Arrays.asList(new Customer(LocalDateTime.now(), "Test", 100.0), null);

        assertThrows(NullPointerException.class, () -> orderExtractionService.extractOrder(customers));
    }

    @Test
    void extractOrder_WithMultipleCustomersDifferentOrderCounts_ShouldMaintainOrder() {
        Customer customer1 = new Customer(LocalDateTime.now(), "Customer1", 500.0);
        Customer customer2 = new Customer(LocalDateTime.now(), "Customer2", 100.0);
        Customer customer3 = new Customer(LocalDateTime.now(), "Customer3", 300.0);

        List<Customer> customers = List.of(customer1, customer2, customer3);

        List<Double> result = orderExtractionService.extractOrder(customers);

        assertEquals(500.0, result.get(0), 0.001);
        assertEquals(100.0, result.get(1), 0.001);
        assertEquals(300.0, result.get(2), 0.001);
    }
}