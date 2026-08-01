package example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCalculatorTest {

    @Mock
    private DiscountConfig config;

    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator(config);
    }

    @Test
    void testCalculateOrder_NullOrEmptyInput_ReturnsEmptyList() {
        assertTrue(calculator.calculateOrder(null).isEmpty());
        assertTrue(calculator.calculateOrder(List.of()).isEmpty());
    }

    @Test
    void testCalculateOrder_StepByStepDiscountReduction() {
        when(config.getPricePerKg()).thenReturn(100.0);
        when(config.getDiscount()).thenReturn(0.2);
        when(config.getDiscountStep()).thenReturn(0.1);

        List<Double> order = List.of(10.0, 5.0, 2.0);

        List<Double> result = calculator.calculateOrder(order);

        assertEquals(3, result.size());
        assertEquals(800.0, result.get(0), 0.001);
        assertEquals(450.0, result.get(1), 0.001);
        assertEquals(200.0, result.get(2), 0.001);
    }

    @Test
    void testCalculateOrder_DiscountDoesNotDropBelowZero() {
        when(config.getPricePerKg()).thenReturn(10.0);
        when(config.getDiscount()).thenReturn(0.1);
        when(config.getDiscountStep()).thenReturn(0.1);

        List<Double> order = List.of(1.0, 1.0, 1.0);
        List<Double> result = calculator.calculateOrder(order);

        assertEquals(3, result.size());
        assertEquals(9.0, result.get(0), 0.001);
        assertEquals(10.0, result.get(1), 0.001);
        assertEquals(10.0, result.get(2), 0.001);
    }

    @Test
    void testCalculateOrder_ZeroPrice() {
        when(config.getPricePerKg()).thenReturn(0.0);
        when(config.getDiscount()).thenReturn(0.5);
        when(config.getDiscountStep()).thenReturn(0.1);

        List<Double> result = calculator.calculateOrder(List.of(10.0, 20.0));

        assertEquals(2, result.size());
        assertEquals(0.0, result.get(0), 0.001);
        assertEquals(0.0, result.get(1), 0.001);
    }
}