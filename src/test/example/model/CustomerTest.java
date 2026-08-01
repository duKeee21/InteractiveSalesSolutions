package example.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testConstructorAndGetters_Success() {
        LocalDateTime expectedDate = LocalDateTime.of(2026, 7, 30, 15, 0);
        String expectedCompanyName = "yKul Corp";
        double expectedOrderCount = 1250.75;

        Customer customer = new Customer(expectedDate, expectedCompanyName, expectedOrderCount);

        assertEquals(expectedDate, customer.getOrderDate());
        assertEquals(expectedCompanyName, customer.getCompanyName());
        assertEquals(expectedOrderCount, customer.getOrderCount());
    }

    @Test
    void testConstructorWithNullValues() {
        double expectedOrderCount = 0.0;

        Customer customer = new Customer(null, null, expectedOrderCount);

        assertNull(customer.getOrderDate());
        assertNull(customer.getCompanyName());
        assertEquals(expectedOrderCount, customer.getOrderCount());
    }
}
