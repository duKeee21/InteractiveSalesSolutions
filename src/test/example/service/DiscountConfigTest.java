package example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountConfigTest {

    @Test
    void testConstructorAndGetters_Success() {
        double expectedPrice = 150.0;
        double expectedDiscount = 0.20;
        double expectedDiscountStep = 0.05;

        DiscountConfig config = new DiscountConfig(expectedPrice, expectedDiscount, expectedDiscountStep);

        assertEquals(expectedPrice, config.getPricePerKg(), 0.001);
        assertEquals(expectedDiscount, config.getDiscount(), 0.001);
        assertEquals(expectedDiscountStep, config.getDiscountStep(), 0.001);
    }

    @Test
    void testConstructorWithZeroAndNegativeValues() {
        double expectedPrice = 0.0;
        double expectedDiscount = 0.0;
        double expectedDiscountStep = -0.01;

        DiscountConfig config = new DiscountConfig(expectedPrice, expectedDiscount, expectedDiscountStep);

        assertEquals(expectedPrice, config.getPricePerKg(), 0.001);
        assertEquals(expectedDiscount, config.getDiscount(), 0.001);
        assertEquals(expectedDiscountStep, config.getDiscountStep(), 0.001);
    }
}